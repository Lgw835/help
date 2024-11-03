package cn.tedu;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo04 {
    public static void main(String[] args) {
        //得到链接对象
        try (Connection conn = DBUtils.getConn()){
            //得到执行SQL语句的对象
            Statement s = conn.createStatement();
            //执行查询
            ResultSet rs = s.executeQuery("select name,job from emp");
            while(rs.next()){
                //通过查询到字段的名字获取数据
//                String name = rs.getString("name");
//                String job = rs.getString("job");
                //通过查询到字段的位置获取数据
                String name = rs.getString(1);
                String job = rs.getString(2);
                System.out.println(name+":"+job);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
