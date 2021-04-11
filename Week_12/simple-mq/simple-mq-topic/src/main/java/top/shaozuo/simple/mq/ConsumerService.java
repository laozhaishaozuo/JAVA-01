package top.shaozuo.simple.mq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

/*
 * @author shaozuo
 */
@Service
public class ConsumerService {

	@JmsListener(destination = "${mytopic}")
	public void handleTopicMessage1(String name) {
		System.out.println("handleTopicMessage1 成功接受Name:" + name);
	}

	@JmsListener(destination = "${mytopic}")
	public void handleTopicMessage2(String name) {
		System.out.println("handleTopicMessage2 成功接受Name:" + name);
	}
}