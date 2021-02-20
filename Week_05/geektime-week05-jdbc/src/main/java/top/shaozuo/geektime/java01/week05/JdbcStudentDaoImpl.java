package top.shaozuo.geektime.java01.week05;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcStudentDaoImpl implements StudentDao {

    @Override
    public int insert(Student student) {
        String sql = "insert into student(id, name) values(?, ?)";
        try (Connection conn = JdbcUtils.getConnection();
                PreparedStatement ptmt = conn.prepareStatement(sql);) {
            ptmt.setInt(1, student.getId());
            ptmt.setString(2, student.getName());
            return ptmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateById(Student student) {
        String sql = "update student set name = ? where id = ?";
        try (Connection conn = JdbcUtils.getConnection();
                PreparedStatement ptmt = conn.prepareStatement(sql);) {
            ptmt.setString(1, student.getName());
            ptmt.setInt(2, student.getId());
            return ptmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteById(Integer id) {
        String sql = "delete from student where id = ?";
        try (Connection conn = JdbcUtils.getConnection();
                PreparedStatement ptmt = conn.prepareStatement(sql);) {
            ptmt.setInt(1, id);
            return ptmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Student queryById(Integer id) {
        String sql = "select * from student where id = ?";
        try (Connection conn = JdbcUtils.getConnection();
                PreparedStatement ptmt = conn.prepareStatement(sql);) {
            ptmt.setInt(1, id);
            ResultSet rs = ptmt.executeQuery();
            Student stu = null;
            while (rs.next()) {
                stu = new Student(rs.getInt("id"), rs.getString("name"));
            }
            return stu;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
