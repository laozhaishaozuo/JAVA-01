package io.kimmking.rpcfx.client;

import java.io.IOException;

import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResponse;

/**
 * 实际对远程服务的访问
 * 
 * @author shaozuo
 *
 */
public interface RemoteClient {
    RpcfxResponse post(RpcfxRequest req, String url) throws IOException;
}
