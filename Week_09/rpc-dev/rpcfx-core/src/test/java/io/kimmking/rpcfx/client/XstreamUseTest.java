package io.kimmking.rpcfx.client;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.thoughtworks.xstream.XStream;

import lombok.Data;

class XstreamUseTest {

	@Test
	void test() throws InstantiationException, IllegalAccessException {
		XStream xstream = new XStream();
		String xml = xstream.toXML(new User("1001", "shaouzo"));
		System.out.println(xml);
		User usr = (User) xstream.fromXML(xml);
		assertEquals("1001", usr.getId());

	}

	@Data
	private static class User {
		String id;
		String name;

		User() {

		}

		public User(String id, String name) {
			super();
			this.id = id;
			this.name = name;
		}

	}
}
