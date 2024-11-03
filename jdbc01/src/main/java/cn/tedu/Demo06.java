package cn.tedu;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo06 {
    public static void main(String[] args) {
        //把所有用户名和昵称查询出来在控制台输出
        try (Connection conn = DBUtils.getConn()){
            Statement s = conn.createStatement();
            //执行查询的SQL语句
            ResultSet rs =
                    s.executeQuery("select username,nick from user");
            while(rs.next()){
                String username = rs.getString(1);
                String nick = rs.getString(2);
                System.out.println(username+":"+nick);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
