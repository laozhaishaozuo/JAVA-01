package io.kimmking.rpcfx.api;

import lombok.Data;

@Data
public class RpcfxResponse {
	private Object result;
	/**
	 * 序列化类型
	 */
	private String serType;
	private boolean status;
	private Exception exception;
}
