package top.shaozuo.simple.mq;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @author shaozuo
 */
@RestController
public class ProviderController {

	private KafkaTemplate<String, Object> kafkaTemplate;

	public ProviderController(KafkaTemplate<?, ?> kafkaTemplate) {
		this.kafkaTemplate = (KafkaTemplate<String, Object>) kafkaTemplate;
	}

	@RequestMapping("sendTopic")
	public void sendTopic(String name) {
		kafkaTemplate.send("test", name);
	}
}