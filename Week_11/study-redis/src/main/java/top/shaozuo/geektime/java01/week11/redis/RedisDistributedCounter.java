package top.shaozuo.geektime.java01.week11.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

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
