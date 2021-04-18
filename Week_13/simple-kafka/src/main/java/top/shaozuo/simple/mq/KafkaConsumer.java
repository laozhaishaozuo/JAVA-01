package top.shaozuo.simple.mq;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

	private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

	@KafkaListener(topics = { "test" })
	public void onMessage(ConsumerRecord<?, ?> record) {
		logger.info("主题：{}，分区：{}，内容：{}", record.topic(), record.partition(), record.value());
	}
}