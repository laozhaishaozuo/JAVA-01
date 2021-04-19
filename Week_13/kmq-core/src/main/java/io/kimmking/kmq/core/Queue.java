package io.kimmking.kmq.core;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Queue {

	private final Lock lock = new ReentrantLock();

	private final Condition condition = lock.newCondition();

	//队列中元素的个数
	private final AtomicInteger count = new AtomicInteger(0);

	private KmqMessage[] values;

	private int offset;

	private final int capacity;

	public Queue(int capacity) {
		this.capacity = capacity;
		values = new KmqMessage[capacity];
	}

	public boolean offer(KmqMessage msg) {
		lock.lock();
		try {
			if (count.get() == capacity) {
				condition.await();
				System.out.println("阻塞线程被唤醒了");
			}
			values[offset++] = msg;
			count.getAndIncrement();
			//System.out.println(String.format("向队列中添加了一个元素%s", msg));
			condition.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		return true;
	}

	public KmqMessage get(int consumerOffset) {
		if (consumerOffset > offset) {
			return null;
		}
		return values[consumerOffset];
	}
}
