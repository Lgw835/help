package cn.tedu;

import java.sql.*;

public class Demo03 {
    public static void main(String[] args) throws SQLException {
        Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/empdb?"+"characterEncoding=utf8&serverTimezone=Asia/Shanghai","root","12345678");
        Statement s=conn.createStatement();
//        执行插入数据库的SQL语句
//        s.execute("insert into emp(name) values('Tom')");
//        执行修改的SQL语句
//        s.execute("update emp set name='Jerry' where name='Tom'");
//        执行删除的SQL语句
//        s.executeUpdate("delete from emp where name='jerry'");
//        执行查询的SQL语句
        ResultSet rs=s.executeQuery("select name,sal from emp");
        while(rs.next()){
            String name=rs.getString("name");
            double sal=rs.getDouble("sal");
            System.out.println(name+":"+sal);
        }

        conn.close();
        System.out.println("执行完成!");
    }
}
