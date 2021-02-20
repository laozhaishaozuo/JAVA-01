package top.shaozuo.geektime.java01.week05;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GetBeanByAnnotationXml {

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("students.xml");
        StudentService service = (StudentService) ctx.getBean("studentService");
        System.out.println(service.getStudent().getId());
        System.out.println(service.getStudent().getName());
    }
}
