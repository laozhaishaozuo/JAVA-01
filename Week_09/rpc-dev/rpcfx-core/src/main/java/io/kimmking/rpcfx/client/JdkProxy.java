package io.kimmking.rpcfx.client;

import java.lang.reflect.Proxy;

import io.kimmking.rpcfx.api.Filter;
import io.kimmking.rpcfx.client.Rpcfx.RpcfxInvocationHandler;

public class JdkProxy {

	public static <T> T create(final Class<T> serviceClass, final String url, Filter... filters) {

		// 0. 替换动态代理 -> 字节码
		return (T) Proxy.newProxyInstance(Rpcfx.class.getClassLoader(),
		        new Class[] { serviceClass },
		        new RpcfxInvocationHandler(serviceClass, url, filters));

	}
}
