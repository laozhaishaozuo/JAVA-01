package top.shaozuo.geektime.java01.week11.redis;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	RedisDistributedLock redisLock;

	int count = 0;

	@RequestMapping("/index")
	@ResponseBody
	public String index() throws InterruptedException {

		int clientcount = 1000;
		CountDownLatch countDownLatch = new CountDownLatch(clientcount);

		ExecutorService executorService = Executors.newFixedThreadPool(clientcount);
		long start = System.currentTimeMillis();
		for (int i = 0; i < clientcount; i++) {
			final String index = Integer.toString(i);
			executorService.execute(() -> {
				try {
					if (redisLock.lock("lockKey", index, 1000L)) {
						count++;
					}
				} finally {
					redisLock.unLock("lockKey", index);
				}
				countDownLatch.countDown();
			});
		}
		countDownLatch.await();
		long end = System.currentTimeMillis();
		logger.info("执行线程数:{},总耗时:{},count数为:{}", clientcount, end - start, count);
		return "Hello";
	}
}