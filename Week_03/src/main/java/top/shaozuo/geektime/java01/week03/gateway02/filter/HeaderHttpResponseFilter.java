package top.shaozuo.geektime.java01.week03.gateway02.filter;

import io.netty.handler.codec.http.FullHttpResponse;

public class HeaderHttpResponseFilter implements HttpResponseFilter {
    @Override
    public void filter(FullHttpResponse response) {
        response.headers().set("shaozuo", "res");
    }
}
