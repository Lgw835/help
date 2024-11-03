package cn.tedu;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Demo05 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名");
        String username = sc.nextLine();
        System.out.println("请输入密码");
        String password = sc.nextLine();
        System.out.println("请输入昵称");
        String nick = sc.nextLine();
        //得到链接对象
        try (Connection conn = DBUtils.getConn()){
            //创建执行SQL语句的对象
            Statement s = conn.createStatement();
            s.executeUpdate(
                    "insert into user values(null,'"+username+"','"+password+"','"+nick+"')");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
