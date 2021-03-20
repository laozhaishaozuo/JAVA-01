package io.kimmking.rpcfx.client;

import java.io.IOException;

import com.alibaba.fastjson.JSON;

import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResponse;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class OkHttpRemoteClient implements RemoteClient {

    public static final MediaType JSONTYPE = MediaType.get("application/json; charset=utf-8");
    private OkHttpClient client = new OkHttpClient();

    @Override
    public RpcfxResponse post(RpcfxRequest req, String url) throws IOException {
        // 1.可以复用client
        // 2.尝试使用httpclient或者netty client
        String reqJson = JSON.toJSONString(req);
        System.out.println("req json: " + reqJson);

        final Request request = new Request.Builder().url(url)
                .post(RequestBody.create(JSONTYPE, reqJson)).build();
        String respJson = client.newCall(request).execute().body().string();
        System.out.println("resp json: " + respJson);
        return JSON.parseObject(respJson, RpcfxResponse.class);
    }

}
