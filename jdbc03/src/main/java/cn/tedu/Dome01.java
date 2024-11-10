package cn.tedu;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Dome01 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入用户名");
        String username=scanner.nextLine();
//        获取连接
        try(Connection conn=DBUtils.getConn()) {
            Statement s=conn.createStatement();
            ResultSet rs=s.executeQuery("select id from user where username='"+username+"'");
            if(rs.next()){
                System.out.println("用户名已存在");
                return;//结束方法
            }
            System.out.println("请输入密码：");
            String password=scanner.nextLine();
            System.out.println("请输入昵称：");
            String nick=scanner.nextLine();
            s.executeUpdate("insert into user(username,password,nick) values('"+username+"','"+password+"','"+nick+"')");
            System.out.println("注册成功！");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
