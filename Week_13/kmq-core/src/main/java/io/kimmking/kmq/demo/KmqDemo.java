package io.kimmking.kmq.demo;

import io.kimmking.kmq.core.KmqBroker;
import io.kimmking.kmq.core.KmqConsumer;
import io.kimmking.kmq.core.KmqMessage;
import io.kimmking.kmq.core.KmqProducer;
import lombok.SneakyThrows;

public class KmqDemo {

	@SneakyThrows
	public static void main(String[] args) {

		String topic = "kk.test";
		KmqBroker broker = new KmqBroker();
		broker.createTopic(topic);

		KmqConsumer consumer = broker.createConsumer();
		consumer.subscribe(topic);

		final boolean[] flag = new boolean[1];
		flag[0] = true;
		new Thread(() -> {
			while (flag[0]) {
				KmqMessage<Order> message = consumer.poll();
				if (null != message) {
					System.out.println(message);
				}
			}
			System.out.println("程序退出。");
		}).start();
		KmqConsumer consumer2 = broker.createConsumer();
		consumer2.subscribe(topic);
		consumer2.setOffset(1000);
		new Thread(() -> {
			while (flag[0]) {
				KmqMessage<Order> message2 = consumer2.poll();
				if (null != message2) {
					System.out.println(message2);
				}
			}
			System.out.println("程序退出。");
		}).start();

		KmqProducer producer = broker.createProducer();
		for (int i = 0; i < 1000; i++) {
			Order order = new Order(1000L + i, System.currentTimeMillis(), "USD2CNY", 6.51d);
			producer.send(topic, new KmqMessage(null, order));
		}
		Thread.sleep(500);
		System.out.println("点击任何键，发送一条消息；点击q或e，退出程序。");
		while (true) {
			char c = (char) System.in.read();
			if (c > 20) {
				System.out.println(c);
				producer.send(topic, new KmqMessage(null,
				        new Order(100000L + c, System.currentTimeMillis(), "USD2CNY", 6.52d)));
			}

			if (c == 'q' || c == 'e')
				break;
		}

		flag[0] = false;

	}
}
