package top.shaozuo.geektime.java01.week04;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 适用 CountDownLatch
 * 
 * @author shaozuo
 *
 */
public class HomeWork03 {

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        ExecutorService executor = Executors.newSingleThreadExecutor();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        // 异步执行 下面方法

        // int result = sum(); //这是得到的返回值

        final Map<String, Integer> ans = new HashMap<>();
        executor.execute(() -> {
            ans.put("result", HomeWork03.sum());
            countDownLatch.countDown();
        });

        countDownLatch.await();
        int result = ans.get("result");
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + result);

        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

        // 然后退出main线程
        executor.shutdown();
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2)
            return 1;
        return fibo(a - 1) + fibo(a - 2);
    }
}
