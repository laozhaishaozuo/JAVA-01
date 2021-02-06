package top.shaozuo.geektime.java01.week04;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 判断是否已经获得结果
 * 
 * @author shaozuo
 *
 */
public class HomeWork02 {

    private static volatile boolean flag = false;

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        long start = System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        ExecutorService executor = Executors.newSingleThreadExecutor();
        // 异步执行 下面方法

        // int result = sum(); //这是得到的返回值

        final Map<String, Integer> ans = new HashMap<>();
        executor.execute(new Runnable() {

            @Override
            public void run() {
                int rs = HomeWork02.sum();
                ans.put("result", rs);
                flag = true;
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
