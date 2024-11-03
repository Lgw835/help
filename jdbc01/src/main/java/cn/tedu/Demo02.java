package cn.tedu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo02 {
    public static void main(String[] args) throws SQLException {
        //得到链接对象
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/empdb?characterEncoding=utf8&serverTimezone=Asia/Shanghai",
                "root","root");
        //创建执行SQL语句的对象
        Statement s = conn.createStatement();
        //执行SQL语句
        s.execute("drop table jdbct1");
        //关闭资源
        conn.close();
        System.out.println("执行完成!");

    }
}
