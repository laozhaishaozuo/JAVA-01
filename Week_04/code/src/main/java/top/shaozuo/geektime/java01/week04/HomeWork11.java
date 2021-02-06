package top.shaozuo.geektime.java01.week04;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.LockSupport;

/**
 * 适用 LockSupport
 * 
 * @author shaozuo
 *
 */
public class HomeWork11 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        long start = System.currentTimeMillis();

        final Map<String, Integer> ans = new HashMap<>();
        final Thread main = Thread.currentThread();
        // 在这里创建一个线程或线程池
        new Thread(() -> {
            ans.put("result", HomeWork11.sum());
            LockSupport.unpark(main);
        }).start();
        LockSupport.park(main);
        // 异步执行 下面方法
        int result = ans.get("result");
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + result);

        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

        // 然后退出main线程
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
