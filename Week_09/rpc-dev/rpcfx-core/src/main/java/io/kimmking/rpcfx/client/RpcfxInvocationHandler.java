package io.kimmking.rpcfx.client;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.alibaba.fastjson.JSON;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import io.kimmking.rpcfx.api.Filter;
import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResponse;
import io.kimmking.rpcfx.client.remote.OkHttpRemoteClient;
import io.kimmking.rpcfx.exception.RpcfxException;

public class RpcfxInvocationHandler implements InvocationHandler {

	private RemoteClient remoteClient;
	private final Class<?> serviceClass;
	private final String url;
	private final Filter[] filters;
	private XStream xstream = new XStream(new DomDriver());

	public <T> RpcfxInvocationHandler(Class<T> serviceClass, String url, Filter... filters) {
		this(serviceClass, url, new OkHttpRemoteClient(), filters);
	}

	public <T> RpcfxInvocationHandler(Class<T> serviceClass, String url, RemoteClient remoteClient,
	        Filter... filters) {
		this.serviceClass = serviceClass;
		this.url = url;
		this.filters = filters;
		this.remoteClient = remoteClient;
	}

	// 可以尝试，自己去写对象序列化，二进制还是文本的，，，
	// rpcfx是xml自定义序列化、反序列化，json: code.google.com/p/rpcfx
	// int byte char float double long bool
	// [], data class

	@Override
	public Object invoke(Object proxy, Method method, Object[] params) throws Throwable {

		// 加filter地方之二
		// mock == true, new Student("hubao");

		RpcfxRequest request = new RpcfxRequest();
		request.setServiceClass(this.serviceClass.getName());
		request.setMethod(method.getName());
		request.setParams(params);

		if (null != filters) {
			for (Filter filter : filters) {
				if (!filter.filter(request)) {
					return null;
				}
			}
		}

		RpcfxResponse response = post(request, url);

		// 加filter地方之三
		// Student.setTeacher("cuijing");

		// 这里判断response.status，处理异常
		// 考虑封装一个全局的RpcfxException
		if (!response.isStatus()) {// 服务端异常
			RpcfxException rpcfxException = (RpcfxException) response.getException();
			System.out.println(rpcfxException.getMessage());
			throw rpcfxException;
		}
		return JSON.parse(response.getResult().toString());
	}

	private RpcfxResponse post(RpcfxRequest req, String url) throws IOException {
		return remoteClient.post(req, url);
	}
}