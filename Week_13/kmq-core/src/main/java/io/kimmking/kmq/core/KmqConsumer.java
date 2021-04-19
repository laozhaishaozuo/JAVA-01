package io.kimmking.kmq.core;

import java.util.HashMap;

public class KmqConsumer<T> {

	private final KmqBroker broker;

	private Kmq kmq;

	private int offset;

	public KmqConsumer(KmqBroker broker) {
		this.broker = broker;
	}

	public void subscribe(String topic) {
		this.kmq = this.broker.findKmq(topic);
		if (null == kmq)
			throw new RuntimeException("Topic[" + topic + "] doesn't exist.");
	}

	public KmqMessage<T> poll() {
		KmqMessage msg = kmq.poll(offset);
		if (msg != null) {
			msg.setHeaders(new HashMap<>());
			msg.getHeaders().put("offset", offset);
			offset++;
		}
		return msg;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

}
