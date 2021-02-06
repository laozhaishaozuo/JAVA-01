package top.shaozuo.geektime.java01.week04;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 适用 Semaphore
 * 
 * @author shaozuo
 *
 */
public class HomeWork05 {

    private static volatile boolean flag = false;

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        ExecutorService executor = Executors.newSingleThreadExecutor();
        final Semaphore semaphore = new Semaphore(1);
        // 异步执行 下面方法

        // int result = sum(); //这是得到的返回值
        semaphore.acquire();
        final Map<String, Integer> ans = new HashMap<>();
        executor.execute(() -> {
            try {
                ans.put("result", HomeWork05.sum());
            } finally {
                semaphore.release();
            }
        });
        while (semaphore.availablePermits() != 1) {
            //循环等待
        }
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
