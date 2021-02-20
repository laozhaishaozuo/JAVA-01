package top.shaozuo.geektime.java01.week05;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class JdbcStudentDaoTest {

    StudentDao studentDao = new JdbcStudentDaoImpl();

    @Test
    void insert() {
        studentDao.deleteById(1);
        Student stu = new Student(1, "shaozuo");
        int count = studentDao.insert(stu);
        assertEquals(1, count);
        stu.setName("laozhai");
        count = studentDao.updateById(stu);
        assertEquals(1, count);
        Student result = studentDao.queryById(1);
        assertEquals(result.getName(), stu.getName());
    }
}
