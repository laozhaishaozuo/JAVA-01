package top.shaozuo.geektime.java01.week05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter
public class StudentService {

    @Autowired
    private Student student;

}
