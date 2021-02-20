package top.shaozuo.geektime.java01.week05;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class GetBeanByJavaConfig {

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(StudentConfig.class);
        Student student = (Student) ctx.getBean("student");
        System.out.println(student.getId());
        System.out.println(student.getName());
    }
}
