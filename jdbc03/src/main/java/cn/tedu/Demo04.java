package cn.tedu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Demo04 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String username = sc.nextLine();
        try(Connection conn =DBUtils.getConn()) {
            String sql="Select id from user where username=?";
            PreparedStatement ps =conn.prepareStatement(sql);
            ps.setString(1,username);
            ResultSet rs=ps.executeQuery();
            if (rs.next()) {
                System.out.println("该用户已存在！");
                return;
            }

            System.out.println("输入密码：");
            String pwd = sc.nextLine();
            System.out.println("输入昵称：");
            String nickname = sc.nextLine();

            String sql2="insert into user(username,password,nick) values(?,?,?)";
            ps = conn.prepareStatement(sql2);
            ps.setString(1,username);
            ps.setString(2,pwd);
            ps.setString(3,nickname);

            System.out.println("注册成功!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
