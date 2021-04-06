package top.shaozuo.geektime.java01.week11.redis;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class JedisToolsTest {

	@Test
	void test() {
		String key = "shaozuo";
		String value = "laozhai";
		JedisTools.set(key, value);
		String result = JedisTools.get(key);
		assertEquals(value, result);
		long count = JedisTools.del(key);
		assertEquals(1L, count);
	}

}
