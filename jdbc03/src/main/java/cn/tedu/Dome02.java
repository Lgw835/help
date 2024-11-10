package cn.tedu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Dome02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名");
        String username = sc.nextLine();
        System.out.println("请输入密码");
        String password = sc.nextLine();
        //获取连接
        try (Connection conn = DBUtils.getConn()){
//            Statement s = conn.createStatement();
//            //执行查询 查询符合条件的数量 大于0 代表登录成功
//            String sql = "select count(*) from user where username='"+
//                    username+"' and password='"+password+"'";
//            System.out.println(sql);
//            ResultSet rs = s.executeQuery(sql);

//            这是一个漏洞,容易被破解：
//            请输入用户名：
//            tom
//            请输入密码：
//            ' or '1'='1
//            select count(*) from user where username='tom' and password='' or '1'='1'
//            登录成功


            //解决SQL注入的问题 PreparedStatement带有预编译效果的执行SQL语句的对象
            String sql =
                    "select count(*) from user where username=? and password=?";
            //预编译的对象在创建时将SQL语句进行编译此时用户输入的内容还不在其中
            //编译完之后相当于把SQL语句的逻辑部分锁死, 用户输入的内容只能以值得形式添加到
            //SQL语句中, 这样就能避免原来SQL语句中的逻辑被修改.
            PreparedStatement ps = conn.prepareStatement(sql);
            //替换掉SQL语句中的?     1和2代表的是?的位置
            ps.setString(1,username);
            ps.setString(2,password);
            //执行查询SQL语句  执行查询时不再使用SQL语句
            ResultSet rs = ps.executeQuery();
            rs.next();//游标往下移动, 不管是否登录成功 都需要游标移动
            int count = rs.getInt(1);
            if (count>0){
                System.out.println("登录成功！");
            }else{
                System.out.println("用户名或密码错误!");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
