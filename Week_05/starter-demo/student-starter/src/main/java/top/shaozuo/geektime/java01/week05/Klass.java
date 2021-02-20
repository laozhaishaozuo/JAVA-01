package top.shaozuo.geektime.java01.week05;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Klass {

    List<Student> students;

    public void dong() {
        System.out.println(this.getStudents());
    }

}
