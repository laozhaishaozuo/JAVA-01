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
		Jedis jedis = null;
		String keySuffix = genPrefix();
		String key = StrUtil.format("{}:id:{}", bizKey, keySuffix);
		try {
			jedis = JedisTools.getResource();
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
