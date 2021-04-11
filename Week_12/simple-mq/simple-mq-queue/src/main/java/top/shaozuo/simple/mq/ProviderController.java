package top.shaozuo.simple.mq;

import javax.jms.Queue;

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
	private Queue queue;

	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;

	@RequestMapping("sendQueue")
	public void send(String name) {
		jmsMessagingTemplate.convertAndSend(queue, name);
	}
}