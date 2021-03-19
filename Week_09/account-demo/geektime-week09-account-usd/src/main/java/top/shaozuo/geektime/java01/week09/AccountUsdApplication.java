package top.shaozuo.geektime.java01.week09;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({ "classpath:spring-dubbo.xml" })
@MapperScan(basePackages = { "top.shaozuo.geektime.java01.week09.dao" })
public class AccountUsdApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountUsdApplication.class, args);
    }

}
