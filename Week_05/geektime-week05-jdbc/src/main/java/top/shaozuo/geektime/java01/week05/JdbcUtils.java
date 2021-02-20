package top.shaozuo.geektime.java01.week05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcUtils {

    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/oauth?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8";
    private static final String USER = "spring-boot";
    private static final String PASSWORD = "123456";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
    }
}