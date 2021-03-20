package io.kimmking.rpcfx.demo.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResolver;
import io.kimmking.rpcfx.api.RpcfxResponse;
import io.kimmking.rpcfx.demo.api.OrderService;
import io.kimmking.rpcfx.demo.api.UserService;
import io.kimmking.rpcfx.server.RpcfxInvoker;

@SpringBootApplication
@RestController
public class RpcfxServerApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(RpcfxServerApplication.class, args);
    }

    @Autowired
    RpcfxInvoker invoker;

    @PostMapping("/")
    public RpcfxResponse invoke(@RequestBody RpcfxRequest request) {
        return invoker.invoke(request);
    }

    @Bean
    public RpcfxInvoker createInvoker(@Autowired RpcfxResolver resolver) {
        return new RpcfxInvoker(resolver);
    }

    @Bean
    public RpcfxResolver createResolver() {
        ReflectResolver resolver = new ReflectResolver();
        resolver.add(UserService.class.getName(), UserServiceImpl.class);
        resolver.add(OrderService.class.getName(), OrderServiceImpl.class);
        return resolver;
    }

    // 能否去掉name
    //

    // annotation
    /*
    @Bean(name = "io.kimmking.rpcfx.demo.api.UserService")
    public UserService createUserService() {
        return new UserServiceImpl();
    }
    
    @Bean(name = "io.kimmking.rpcfx.demo.api.OrderService")
    public OrderService createOrderService() {
        return new OrderServiceImpl();
    }*/

}
