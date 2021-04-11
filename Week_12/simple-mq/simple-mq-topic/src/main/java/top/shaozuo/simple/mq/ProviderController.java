package top.shaozuo.simple.mq;

import javax.jms.Topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @author shaozuo
 */
@RestController
public class ProviderController {

	@Autowired
	private Topic topic;

	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;

	@RequestMapping("sendTopic")
	public void sendTopic(String name) {
		jmsMessagingTemplate.convertAndSend(topic, name);
	}
}