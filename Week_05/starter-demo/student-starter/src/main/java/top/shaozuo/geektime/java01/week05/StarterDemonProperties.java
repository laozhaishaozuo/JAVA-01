package top.shaozuo.geektime.java01.week05;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "starter.demon")
public class StarterDemonProperties {
    private List<Student> students;
    private Student student;
}
