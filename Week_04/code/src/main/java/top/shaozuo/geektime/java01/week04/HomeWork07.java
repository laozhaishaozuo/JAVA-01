package top.shaozuo.geektime.java01.week04;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 适用 ReentrantLock
 * 
 * @author shaozuo
 *
 */
public class HomeWork07 {

    private static class Worker {
        private volatile Integer value = null;
        private ReentrantLock lock = new ReentrantLock();
        private Condition success = lock.newCondition();

        public void sum() {
            lock.lock();
            try {
                value = HomeWork07.sum();
                success.signal();
            } finally {
                lock.unlock();
            }

        }

        public Integer get() throws InterruptedException {
            lock.lock();
            try {
                while (value == null) {
                    success.await();
                }
                return value;
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        Worker worker = new Worker();
        new Thread(() -> worker.sum()).start();
        // 异步执行 下面方法

        int result = worker.get();
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
