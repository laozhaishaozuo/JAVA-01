package io.kimmking.kmq.core;

public final class Kmq {

	public Kmq(String topic, int capacity) {
		this.topic = topic;
		this.capacity = capacity;
		this.queue = new Queue(capacity);
	}

	private String topic;

	private int capacity;

	private Queue queue;

	public boolean send(KmqMessage message) {
		return queue.offer(message);
	}

	public KmqMessage poll(int offset) {
		return queue.get(offset);
	}

}
