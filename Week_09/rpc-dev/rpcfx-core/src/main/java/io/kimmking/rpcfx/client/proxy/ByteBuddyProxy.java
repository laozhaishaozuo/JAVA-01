package io.kimmking.rpcfx.client.proxy;

import java.lang.reflect.InvocationTargetException;

import io.kimmking.rpcfx.api.Filter;
import io.kimmking.rpcfx.client.Rpcfx;
import io.kimmking.rpcfx.client.RpcfxXmlInvocationHandler;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.InvocationHandlerAdapter;

public class ByteBuddyProxy {

	@SuppressWarnings("unchecked")
	public static <T> T create(final Class<T> serviceClass, final String url, Filter... filters) {

		//System.out.println(serviceClass.getCanonicalName());

		//扩展子类
		DynamicType.Unloaded<?> dynamicType = new ByteBuddy().subclass(Object.class)
		        .name(serviceClass.getCanonicalName() + "Impl").implement(serviceClass)
		        .intercept(InvocationHandlerAdapter
		                .of(new RpcfxXmlInvocationHandler(serviceClass, url, filters)))
		        .make();
		//System.out.println(dynamicType.getTypeDescription());
		try {
			return (T) dynamicType.load(Rpcfx.class.getClassLoader()).getLoaded()
			        .getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
		        | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
