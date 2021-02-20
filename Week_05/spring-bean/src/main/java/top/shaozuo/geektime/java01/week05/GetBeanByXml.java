package top.shaozuo.geektime.java01.week05;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GetBeanByXml {

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("students.xml");
        Student student = (Student) ctx.getBean("student");
        System.out.println(student.getId());
        System.out.println(student.getName());
    }
}
