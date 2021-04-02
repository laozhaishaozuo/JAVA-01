# 学习笔记

**Week11 作业题目：**

**周日作业：**

**4.（必做）**基于 Redis 封装分布式数据操作：

- 在 Java 中实现一个简单的分布式锁；
- 在 Java 中实现一个分布式计数器，模拟减库存。

**分布式锁关键代码**

```java
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
```



**分布式计数器关键代码**

```java
public class RedisDistributedCounter {

	private static final Logger logger = LoggerFactory.getLogger(RedisDistributedCounter.class);

	private final long limit;
	private final String counterKey;

	private final RedisTemplate<String, String> redisTemplate;

	public RedisDistributedCounter(RedisTemplate<String, String> redisTemplate, String counterKey, long limit) {
		this.limit = limit;
		this.counterKey = counterKey;
		this.redisTemplate = redisTemplate;
	}

	public boolean inc(long nums) {
		long total = redisTemplate.opsForValue().increment(counterKey, nums);
		if (total <= limit) {
			logger.info("数量增加 {} 变为 {}", nums, total);
			return true;
		} else {
			logger.info("数量增加 [{}] 失败, 可增加数量为 {} , 最高数量为 {} ", nums, limit - (total - nums), limit);
			redisTemplate.opsForValue().decrement(counterKey, nums);
			return false;
		}
	}

	public long getTotal() {
		String result = getTotalStr();
		if (result == null) {
			return 0;
		} else {
			return Long.parseLong(result);
		}
	}

	public String getTotalStr() {
		return redisTemplate.opsForValue().get(counterKey);
	}
}
```





