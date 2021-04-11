package top.shaozuo.simple.mq;

import javax.jms.Topic;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

@Configuration
@EnableJms
public class ActiveMqConfig {
	@Value("${mytopic}")
	private String topicName;

	//topic
	@Bean
	public Topic topic() {
		return new ActiveMQTopic(topicName);
	}
}