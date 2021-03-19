package top.shaozuo.geektime.java01.week03.gateway02.filter;

import java.nio.charset.StandardCharsets;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;

public class HeaderHttpResponseFilter implements HttpResponseFilter {
    @Override
    public void filter(FullHttpResponse response) {
        //FullHttpResponse 返回了一个新的 response 在目前的情况下无法改变其值
        response.headers().set("shaozuo", "res");
        ByteBuf old = response.content();
        ByteBuf appended = Unpooled
                .wrappedBuffer("appended Infomation:hahah".getBytes(StandardCharsets.UTF_8));
        CompositeByteBuf newBuf = Unpooled.compositeBuffer();
        newBuf.addComponent(old);
        newBuf.addComponent(appended);
        response.replace(newBuf);
        System.out.println("he");
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
    }
}
