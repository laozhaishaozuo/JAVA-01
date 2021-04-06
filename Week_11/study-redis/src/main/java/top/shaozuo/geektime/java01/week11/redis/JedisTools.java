package top.shaozuo.geektime.java01.week11.redis;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.hutool.setting.dialect.Props;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.exceptions.JedisException;
import redis.clients.jedis.params.SetParams;

public class JedisTools {

	private static final Logger logger = LoggerFactory.getLogger(JedisTools.class);

	private static JedisPool jedisPool = null;

	static {
		Props props = new Props("redis.properties");
		// 读取配置文件

		String host = props.getStr("redis.host", "localhost");
		// 获取端口号
		int port = props.getInt("redis.port", 6379);

		// 获取最大连接数
		int maxTotal = props.getInt("redis.maxTotal", GenericObjectPoolConfig.DEFAULT_MAX_TOTAL);
		// 获取闲时连接数
		int maxIdle = props.getInt("redis.maxIdle", GenericObjectPoolConfig.DEFAULT_MAX_IDLE);
		//
		int minIdle = props.getInt("redis.minIdle", GenericObjectPoolConfig.DEFAULT_MIN_IDLE);

		// 创建连接池配置对象
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxIdle(maxIdle);
		jedisPoolConfig.setMaxTotal(maxTotal);
		jedisPoolConfig.setMinIdle(minIdle);

		// 创建连接池对象
		jedisPool = new JedisPool(jedisPoolConfig, host, port);
	}

	public static Jedis getResource() {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
		} catch (JedisException e) {
			logger.error("获取jedis失败:{}", e.getMessage());
			throw e;
		}
		return jedis;
	}

	public static void close(Jedis jedis) {
		if (jedis != null) {
			jedis.close();
		}
	}

	/**
	 * 获取String类型的数据
	 * @param key
	 * @return 返回特定key的值
	 */
	public static String get(String key) {
		String value = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			value = jedis.get(key);
		} catch (Exception e) {
			logger.error("获取 key:{} 失败，{}", key, e.getMessage());
		}
		return value;
	}

	/**
	 * 设置key=value 过期时间为 cacheSeconds 秒
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 * @param cacheSeconds
	 *            超时时间（秒），0为不超时
	 * @return
	 */
	public static String set(String key, String value, int cacheSeconds) {
		String result = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			if (cacheSeconds <= 0) {
				result = jedis.set(key, value, SetParams.setParams());
			} else {
				result = jedis.set(key, value, SetParams.setParams().ex(cacheSeconds));
			}
			logger.debug("set {} = {}", key, value);
		} catch (Exception e) {
			logger.warn("set {} = {}", key, value, e);
		} finally {
			close(jedis);
		}
		return result;
	}

	/**
	 * 设置 key = value 没有过期时间
	 * 
	 * @param key
	 * @param value
	 */
	public static void set(String key, String value) {
		set(key, value, 0);
	}

	public static Long del(String key) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			return jedis.del(key);
		} catch (Exception e) {
			logger.warn("del {} 失败，原因 ：{}", key, e.getMessage());
		} finally {
			close(jedis);
		}
		return 0L;
	}

	public static Long del(String... key) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			return jedis.del(key);
		} catch (Exception e) {
			logger.warn("del {} 失败，原因 ：{}", key, e.getMessage());
		} finally {
			close(jedis);
		}
		return 0L;
	}

	public static Set<String> like(String pattern) {
		Jedis jedis = null;
		ScanParams params = new ScanParams().match(pattern);
		try {
			jedis = JedisTools.getResource();
			ScanResult<String> result = jedis.scan(ScanParams.SCAN_POINTER_START, params);
			String cursor = result.getCursor();
			Set<String> keys = new HashSet<>();
			do {
				keys.addAll(result.getResult());
				result = jedis.scan(cursor, params);
				cursor = result.getCursor();
			} while (!"0".equals(cursor));// 第一次就可能返回"0"
			return keys;
		} finally {
			close(jedis);
		}
	}
}
