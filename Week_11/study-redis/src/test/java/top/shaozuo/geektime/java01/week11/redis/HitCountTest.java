package top.shaozuo.geektime.java01.week11.redis;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.RandomUtil;
import redis.clients.jedis.Jedis;

class HitCountTest {

	private static Logger logger = LoggerFactory.getLogger(HitCountTest.class);

	List<String> ips = new ArrayList<>(1200);

	@BeforeEach
	void before() {
		ClassPathResource resource = new ClassPathResource("ips.txt");
		FileUtil.readUtf8Lines(resource.getFile(), ips);
	}

	@Test
	void hitCount() {
		String key = "article:id:10";
		Jedis jedis = JedisTools.getResource();
		jedis.del(key);
		Set<String> uniqIps = new HashSet<>();
		// 模拟多次10000次点击
		int multHit = 5_000;
		for (int i = 0; i < multHit; i++) {
			// 使用不同的ip地址模拟
			String ip = ips.get(RandomUtil.randomInt(0, 1000));
			uniqIps.add(ip);
			jedis.pfadd(key, ip);
		}
		long hits = jedis.pfcount(key);
		long uniqIpCount = uniqIps.size();
		logger.info("有{}次点击", multHit);
		logger.info("有{}个ip地址", uniqIpCount);
		logger.info("redis 统计有{}个ip地址", hits);
		logger.info("误差率是{}", (hits - uniqIpCount) * 1.0 / uniqIpCount);
		// assertEquals(hits, hitIps.size()); //pfcount可能有0.81%的差距 不过大致相等
	}
}
