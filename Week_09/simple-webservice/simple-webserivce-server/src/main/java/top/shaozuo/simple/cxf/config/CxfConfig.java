package top.shaozuo.simple.cxf.config;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import top.shaozuo.simple.cxf.service.HelloService;

@Configuration
public class CxfConfig {

	@Bean
	public ServletRegistrationBean<CXFServlet> cxfServlet() {
		return new ServletRegistrationBean<>(new CXFServlet(), "/cxf/*");
	}

	@Bean(name = Bus.DEFAULT_BUS_ID)
	public SpringBus springBus() {
		return new SpringBus();
	}

	/**
	 * @return
	 */
	@Bean
	public Endpoint endpoint(HelloService helloService) {
		System.out.println(helloService.getClass().getName());
		EndpointImpl endpoint = new EndpointImpl(springBus(), helloService);
		endpoint.publish("/helloService");
		return endpoint;
	}

}
