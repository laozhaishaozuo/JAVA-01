package io.kimmking.rpcfx.demo.consumer;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.kimmking.rpcfx.client.Rpcfx;
import io.kimmking.rpcfx.demo.api.Order;
import io.kimmking.rpcfx.demo.api.OrderService;
import io.kimmking.rpcfx.demo.api.User;
import io.kimmking.rpcfx.demo.api.UserService;

@SpringBootApplication
public class RpcfxClientApplication {

	// 二方库
	// 三方库 lib
	// nexus, userserivce -> userdao -> user
	//

	public static void main(String[] args) {

		// UserService service = new xxx();
		// service.findById

		UserService userService = Rpcfx.create(UserService.class, "http://localhost:8081/xstream/");
		System.out.println(userService.findById(1));
		User user = userService.findById(1);
		System.out.println("find user id=1 from server: " + user.getName());

		OrderService orderService = Rpcfx.create(OrderService.class,
		        "http://localhost:8081/xstream/");
		Order order = orderService.findOrderById(1992129);
		System.out.println(
		        String.format("find order name=%s, amount=%f", order.getName(), order.getAmount()));

	}
}
