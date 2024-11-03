package cn.tedu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo02 {
    public static void main(String[] args) throws SQLException {
        Connection conn =
                DriverManager.getConnection("jdbc:mysql://localhost:3306/empdb?"+
                                "characterEncoding=utf8&serverTimezone=Asia/Shanghai",
                        "root", "12345678");
        Statement s = conn.createStatement();
        s.execute("drop table jdbct1");
        conn.close();
        System.out.println("创建完成!");
    }
}
