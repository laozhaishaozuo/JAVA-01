package top.shaozuo.geektime.java01.week11.redis;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

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

	@Test
	void del_orders() {
		Set<String> keys = JedisTools.like("order:id:*");
		System.out.println(keys);
		int count = keys.size();
		if (count > 0) {
			Long delCount = JedisTools.del(keys.toArray(new String[0]));
			assertEquals(count, delCount.intValue());
		}
	}
}
