package top.shaozuo.geektime.java01.week03.gateway02.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

public class HeaderHttpRequestFilter implements HttpRequestFilter {

    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        fullRequest.headers().set("shaozuo", "req");
        fullRequest.headers().set("filter-1", "filter-1");
    }
}
