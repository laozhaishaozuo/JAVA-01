package top.shaozuo.geektime.java01.week11.redis;

import java.util.Date;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import redis.clients.jedis.Jedis;

public class GlobalRedisId {


	/**
	 * 生成格式为 YYYY MM dd
	 * @return
	 */
	public static String genId(String bizKey) {
		return genId(bizKey, 0);
	}

	/**
	 * 生成 bizKey:yyyyMMddHHmmss:00000 格式的key
	 * @param bizKey 业务key
	 * @param expireSeconds 过期时间秒 如果expireSeconds<=0 将被忽略
	 * @return
	 */
	public static String genId(String bizKey, int expireSeconds) {
		Jedis jedis = null;
		String keySuffix = genPrefix();
		String key = StrUtil.format("{}:id:{}", bizKey, keySuffix);
		try {
			jedis = JedisTools.getResource();
			if (!jedis.exists(key).booleanValue()) {
				JedisTools.set(key, "0", expireSeconds);
			}
			Long result = jedis.incr(key);
			String suffix = String.format("%05d", result);
			return keySuffix + suffix;
		} finally {
			JedisTools.close(jedis);
		}
	}

	private static String genPrefix() {
		Date date = DateUtil.date();
		return DateUtil.format(date, DatePattern.PURE_DATETIME_PATTERN);
	}
}
