package top.shaozuo.geektime.java01.week04;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 适用 CyclicBarrier
 * 
 * @author shaozuo
 *
 */
public class HomeWork04 {

    private static volatile boolean flag = false;

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        ExecutorService executor = Executors.newSingleThreadExecutor();
        final CyclicBarrier barrier = new CyclicBarrier(1, () -> flag = true);
        // 异步执行 下面方法

        // int result = sum(); //这是得到的返回值

        final Map<String, Integer> ans = new HashMap<>();
        executor.execute(() -> {
            ans.put("result", HomeWork04.sum());
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        while (!flag) {
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
