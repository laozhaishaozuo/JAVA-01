package io.kimmking.rpcfx.client;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;

import com.alibaba.fastjson.JSON;

import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResponse;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.AttributeKey;

/**
 * 使用 netty+http
 * 
 * @author shaozuo
 *
 */
public class NettyHttpRemoteClient implements RemoteClient {

    NioEventLoopGroup workerGroup = new NioEventLoopGroup();

    @Override
    public RpcfxResponse post(RpcfxRequest req, String url) throws IOException {
        try {
            CountDownLatch latch = new CountDownLatch(1);
            URI uri = URI.create(url);
            Bootstrap bootstrap = createBootstrap(latch);
            Channel channel = bootstrap.connect(uri.getHost(), uri.getPort()).sync().channel();

            FullHttpRequest request = createRequest(req, uri);
            ChannelFuture future = channel.writeAndFlush(request).sync();
            channel.closeFuture().sync();
            AttributeKey<String> key = AttributeKey.valueOf("result");
            String result = future.channel().attr(key).get();
            System.out.println(result);
            return JSON.parseObject(result, RpcfxResponse.class);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }
        return null;
    }

    private FullHttpRequest createRequest(RpcfxRequest req, URI uri) {
        DefaultFullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1,
                HttpMethod.POST, uri.getPath());
        byte[] content = JSON.toJSONString(req).getBytes();
        request.content().writeBytes(content);
        request.headers().set(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON);
        request.headers().set(HttpHeaderNames.HOST, uri.getHost());
        request.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        request.headers().set(HttpHeaderNames.CONTENT_LENGTH, request.content().readableBytes());
        return request;
    }

    private Bootstrap createBootstrap(CountDownLatch latch) {
        return new Bootstrap().group(workerGroup).channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel sc) throws Exception {
                        // HttpClientCodec 相当于同时添加了HttpResponseDecoder和HttpRequestEncoder
                        sc.pipeline().addLast(new HttpClientCodec());
                        sc.pipeline().addLast(new HttpObjectAggregator(512 * 1024));
                        sc.pipeline().addLast(new HttpClientHandler(latch));
                    }
                });
    }

    private static class HttpClientHandler extends SimpleChannelInboundHandler<FullHttpResponse> {

        private CountDownLatch latch;

        public HttpClientHandler(CountDownLatch latch) {
            this.latch = new CountDownLatch(1);
        }

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, FullHttpResponse msg)
                throws Exception {
            String result = msg.content().toString(StandardCharsets.UTF_8);
            AttributeKey<String> key = AttributeKey.valueOf("result");
            ctx.channel().attr(key).set(result);
            latch.countDown();
            ctx.close();
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            ByteBuf buf = Unpooled.copiedBuffer(cause.getMessage().getBytes());
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                    HttpResponseStatus.BAD_GATEWAY, buf);
            response.headers().add(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON);
            ctx.channel().writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
            ctx.close();
        }

    }
}
