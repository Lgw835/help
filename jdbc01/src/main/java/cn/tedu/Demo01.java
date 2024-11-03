package cn.tedu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo01 {
    public static void main(String[] args) throws SQLException {
        //1. 得到链接对象  异常抛出
        Connection conn =
                DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/empdb?" +
                                "characterEncoding=utf8&serverTimezone=Asia/Shanghai",
                        "root","12345678");
        System.out.println(conn);
        //2. 创建执行SQL语句的对象
        Statement s = conn.createStatement();
        //3. 执行SQL语句 execute执行
        s.execute("create table jdbct1(id int)");
        //4. 关闭资源
        conn.close();
        System.out.println("创建完成!");
    }
}
