package top.shaozuo.geektime.java01.week05;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringStarterDemonApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication
                .run(SpringStarterDemonApplication.class, args);
        Student bean = ctx.getBean(Student.class);
        System.out.println(bean);
        Klass klass = ctx.getBean(Klass.class);
        System.out.println(klass);
        School school = ctx.getBean(School.class);
        System.out.println(school);
    }

}
