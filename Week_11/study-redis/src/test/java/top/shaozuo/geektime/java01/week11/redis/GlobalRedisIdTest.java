package top.shaozuo.geektime.java01.week11.redis;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.Test;

class GlobalRedisIdTest {

	@Test
	void testGenId() throws InterruptedException {
		ExecutorService executor = Executors.newFixedThreadPool(4);
		for (int i = 0; i < 1_0000; i++) {
			executor.submit(() -> System.out.println(GlobalRedisId.genId("order")));
		}
		Thread.sleep(10000);
	}

}
