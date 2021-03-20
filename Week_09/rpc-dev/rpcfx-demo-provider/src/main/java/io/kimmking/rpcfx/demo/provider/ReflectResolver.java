package io.kimmking.rpcfx.demo.provider;

import java.util.HashMap;

import io.kimmking.rpcfx.api.RpcfxResolver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReflectResolver implements RpcfxResolver {

    private HashMap<String, Object> services = new HashMap<>();

    public void add(String serviceClass, Class<?> serviceImpl) {
        try {
            services.put(serviceClass, serviceImpl.newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T resolve(String serviceClass) {
        log.info("通过反射获取Bean");
        return (T) services.get(serviceClass);
    }
}
