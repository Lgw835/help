package cn.tedu;

import java.sql.*;

public class Demo03 {
    public static void main(String[] args) throws SQLException {
        Connection conn =
                DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/empdb?" +
                                "characterEncoding=utf8&serverTimezone=Asia/Shanghai",
                        "root","root");
        Statement s = conn.createStatement();
        //执行插入数据的SQL
//        s.executeUpdate("insert into emp(name) values('Tom')");
        //执行修改的SQL
        //s.executeUpdate("update emp set name='Jerry' where name='Tom'");
        //执行删除的SQL
        //s.executeUpdate("delete from emp where name='Jerry'");
        //执行查询的SQL
        ResultSet rs = s.executeQuery("select name,sal from emp");
        //遍历结果集对象 得到查询回来的所有数据
        //next()方法 询问是否有下一条数据 有则返回true 同时游标往下移动一格
        while(rs.next()){
            //获取结果集对象中得数据 游标指向的是谁 得到的就是谁的数据
            String name = rs.getString("name");
            double sal = rs.getDouble("sal");
            System.out.println(name+":"+sal);
        }
        //关闭资源
        conn.close();
        System.out.println("执行完成!");
    }
}
