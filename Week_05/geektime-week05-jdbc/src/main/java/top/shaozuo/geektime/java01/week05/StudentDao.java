package top.shaozuo.geektime.java01.week05;

public interface StudentDao {

    public int insert(Student student);

    public int updateById(Student student);

    public int deleteById(Integer id);

    public Student queryById(Integer id);
}
