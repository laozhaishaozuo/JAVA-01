package top.shaozuo.geektime.java01.week05;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcTemplateStudentDaoImpl implements StudentDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int insert(Student student) {
        String sql = "insert into student(id, name) values(?, ?)";
        return jdbcTemplate.update(sql, student.getId(), student.getName());
    }

    @Override
    public int deleteById(Integer id) {
        String sql = "delete from student where id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int updateById(Student student) {
        String sql = "update student set name = ? where id = ?";
        return jdbcTemplate.update(sql, student.getName(), student.getId());
    }

    @Override
    public Student queryById(Integer id) {
        String sql = "select * from student where id = ?";
        List<Student> list = jdbcTemplate.query(sql, new RowMapper<Student>() {
            @Override
            public Student mapRow(ResultSet resultSet, int i) throws SQLException {
                return new Student(resultSet.getInt("id"), resultSet.getString("name"));
            }
        }, id);
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }
}