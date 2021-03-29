package io.kimmking.rpcfx.api;

import lombok.Data;

@Data
public class RpcfxRequest {
	private String serviceClass;
	private String method;
	private Object[] params;
	/**
	 * 序列化类型
	 */
	private String serType;
}
