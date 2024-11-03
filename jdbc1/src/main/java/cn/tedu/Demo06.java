package cn.tedu;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo06 {
    public static void main(String[] args) {
        try(Connection conn=DBUtils.getConn();) {
            Statement st =conn.createStatement();
            ResultSet rs=st.executeQuery("select username,nick from user");
            while(rs.next()){
                String username = rs.getString(1);
                String nick = rs.getString(2);
                System.out.println(username+":"+nick);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
