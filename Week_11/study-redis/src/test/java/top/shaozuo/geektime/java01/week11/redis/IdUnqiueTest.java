package top.shaozuo.geektime.java01.week11.redis;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import cn.hutool.core.util.RandomUtil;
import redis.clients.jedis.Jedis;

class IdUnqiueTest {

	@Test
	void test() {
		String key = "ids";
		Jedis jedis = JedisTools.getResource();
		jedis.del(key);
		Set<Integer> ids = new HashSet<>();
		// 模拟ID去重
		for (int i = 0; i < 1_0000; i++) {
			int id = RandomUtil.randomInt(1, 2000);
			ids.add(id);
			// int hash = HashUtil.fnvHash(id); //如果id不是数字，可以使用hash值
			jedis.setbit(key, id, true);
		}
		Long count = jedis.bitcount(key);
		System.out.println(count);
		System.out.println(ids.size());
		assertEquals(ids.size(), count);
	}

}
