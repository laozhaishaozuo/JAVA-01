package io.kimmking.rpcfx.client;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

class ByteBuyddyUseTest {

	@Test
	void test() throws InstantiationException, IllegalAccessException {
		Class<?> dynamicType = new ByteBuddy().subclass(Object.class).name("com.example.HelloWorld")
		        .method(ElementMatchers.named("toString"))
		        .intercept(FixedValue.value("Hello World!")).make()
		        .load(getClass().getClassLoader()).getLoaded();
		System.out.println(dynamicType.getName());
		assertThat(dynamicType.newInstance().toString(), is("Hello World!"));
	}

}
