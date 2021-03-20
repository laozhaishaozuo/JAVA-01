package io.kimmking.rpcfx.demo.provider;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import io.kimmking.rpcfx.api.RpcfxResolver;

public class DemoResolver implements RpcfxResolver, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T resolve(String serviceClass) {
        return (T) this.applicationContext.getBean(serviceClass);
    }
}
