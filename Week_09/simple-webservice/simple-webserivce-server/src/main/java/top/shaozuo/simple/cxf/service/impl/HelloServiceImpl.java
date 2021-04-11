package top.shaozuo.simple.cxf.service.impl;

import javax.jws.WebService;

import org.springframework.stereotype.Service;

import top.shaozuo.simple.cxf.service.HelloService;

@Service("helloService")
@WebService(endpointInterface = "top.shaozuo.simple.cxf.service.HelloService", targetNamespace = "http://service.cxf.simple.shaozuo.top/", name = "helloService")
public class HelloServiceImpl implements HelloService {

	@Override
	public String sayHello(String message) {
		return "Hello, " + message;
	}

}
