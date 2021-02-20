package top.shaozuo.geektime.java01.week05;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

    @Bean
    public Student student() {
        return new Student(2, "laozhai");
    }
}
