package io.kimmking.rpcfx.demo.provider;

import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

import io.kimmking.rpcfx.demo.api.User;
import io.kimmking.rpcfx.demo.api.UserService;

class UserServiceImplTest {

	private UserService userService = new UserServiceImpl();

	@Test
	void test() throws Exception {
		Method method = userService.getClass().getMethod("findById", int.class);
		User user = (User) method.invoke(userService, new Object[] { 1 });
	}

}
