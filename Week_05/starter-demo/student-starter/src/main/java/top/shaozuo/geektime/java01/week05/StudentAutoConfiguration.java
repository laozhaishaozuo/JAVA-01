package top.shaozuo.geektime.java01.week05;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentAutoConfiguration {

    @Autowired
    private StarterDemonProperties starterDemonProperties;

    @Bean
    @ConditionalOnMissingBean
    public Klass klass() {
        List<Student> students = starterDemonProperties.getStudents();
        return new Klass(students);
    }

    @Bean
    @ConditionalOnMissingBean
    public Student student() {
        return starterDemonProperties.getStudent();
    }

    @Bean
    @ConditionalOnMissingBean
    public School school(Klass klass, Student student) {
        return new School(klass, student);
    }
}
