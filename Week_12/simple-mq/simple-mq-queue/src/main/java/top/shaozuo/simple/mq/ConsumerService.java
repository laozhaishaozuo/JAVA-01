package top.shaozuo.simple.mq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

/*
 * @author shaozuo
 */
@Service
public class ConsumerService {

	@JmsListener(destination = "${myqueue}")
	public void handleMessage(String name) {
		System.out.println("成功接受Name:" + name);
	}
}