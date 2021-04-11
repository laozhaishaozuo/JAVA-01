package top.shaozuo.simple.mq;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

@Configuration
@EnableJms
public class ActiveMqConfig {
	@Value("${myqueue}")
	private String myQueue;

	//队列
	@Bean
	public ActiveMQQueue queue() {
		return new ActiveMQQueue(myQueue);
	}

}