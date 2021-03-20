package io.kimmking.rpcfx.exception;

/**
 * Rpcfx 异常
 * 
 * @author shaozuo
 *
 */
public class RpcfxException extends RuntimeException {
    /**
     * 
     */
    private static final long serialVersionUID = -29274961962586672L;

    public RpcfxException() {
        super();
    }

    public RpcfxException(String message) {
        super(message);
    }

    public RpcfxException(String message, Throwable cause) {
        super(message, cause);
    }

    public RpcfxException(Throwable cause) {
        super(cause);
    }

    protected RpcfxException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
