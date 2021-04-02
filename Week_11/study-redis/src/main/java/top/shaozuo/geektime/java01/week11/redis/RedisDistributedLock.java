package top.shaozuo.geektime.java01.week11.redis;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.commands.JedisCommands;
import redis.clients.jedis.params.SetParams;

@Component
public class RedisDistributedLock {

	private static final Logger logger = LoggerFactory.getLogger(RedisDistributedLock.class);

	@Resource
	private RedisTemplate<String, String> redisTemplate;

	public static final String UNLOCK_LUA = "if redis.call('get', KEYS[1]) == ARGV[1] "
			+ "then return redis.call('del', KEYS[1]) " + "else return 0 " + "end";

	/**
	 * 获取锁
	 * @param key
	 * @param value
	 * @param expire
	 * @return
	 */
	public boolean lock(String key, String value, Long expire) {
		try {
			RedisCallback<String> callback = (connection) -> {
				JedisCommands commands = (JedisCommands) connection.getNativeConnection();
				return commands.set(key, value, SetParams.setParams().nx().px(expire));
			};

			String result = redisTemplate.execute(callback);

			return !StringUtils.hasLength(result);
		} catch (Exception e) {
			logger.error("获取锁失败", e);
		}

		return false;
	}

	/**
	* 释放锁
	* @param key
	* @param value
	* @return
	*
	*/
	public boolean unLock(String key, String value) {
		List<String> keys = new ArrayList<>();
		keys.add(key);
		List<String> args = new ArrayList<>();
		args.add(value);
		try {
			// 使用lua脚本删除redis中匹配value的key
			RedisCallback<Long> callback = (connection) -> {
				Object nativeConnection = connection.getNativeConnection();
				if (nativeConnection instanceof Jedis) {// 单机模式
					return (Long) ((Jedis) nativeConnection).eval(UNLOCK_LUA, keys, args);
				} else {
					// 模式匹配不上
				}
				return 0L;
			};

			Long result = redisTemplate.execute(callback);

			return result != null && result > 0;
		} catch (Exception e) {
			logger.error("释放锁失败", e);
		} finally {

		}

		return false;
	}

}
