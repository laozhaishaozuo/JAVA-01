package io.kimmking.rpcfx.client;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.parser.ParserConfig;

import io.kimmking.rpcfx.api.Filter;
import io.kimmking.rpcfx.api.LoadBalancer;
import io.kimmking.rpcfx.api.Router;
import io.kimmking.rpcfx.client.proxy.ByteBuddyProxy;

public final class Rpcfx {

	static {
		ParserConfig.getGlobalInstance().addAccept("io.kimmking");
	}

	public static <T, filters> T createFromRegistry(final Class<T> serviceClass, final String zkUrl,
	        Router router, LoadBalancer loadBalance, Filter filter) {

		// 加filte之一

		// curator Provider list from zk
		List<String> invokers = new ArrayList<>();
		// 1. 简单：从zk拿到服务提供的列表
		// 2. 挑战：监听zk的临时节点，根据事件更新这个list（注意，需要做个全局map保持每个服务的提供者List）

		List<String> urls = router.route(invokers);

		String url = loadBalance.select(urls); // router, loadbalance

		return create(serviceClass, url, filter);

	}

	public static <T> T create(final Class<T> serviceClass, final String url, Filter... filters) {
		// 0. 替换动态代理 -> 字节码
		return ByteBuddyProxy.create(serviceClass, url, filters);

	}
}
