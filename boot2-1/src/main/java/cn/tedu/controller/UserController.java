package cn.tedu.controller;

import cn.tedu.utils.DBUtils;
import cn.tedu.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Controller
public class UserController {

    @RequestMapping("/reg")
    @ResponseBody
    public String reg(User user) {
        System.out.println("user = " + user);

        try(Connection conn = DBUtils.getConn()) {
            String sql = "select id from user where username=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return "用户已存在";
            }
            String insql = "insert into user value(null,?,?,?)";
            PreparedStatement ps2=conn.prepareStatement(insql);
            ps2.setString(1,user.getUsername());
            ps2.setString(2,user.getPassword());
            ps2.setString(3,user.getNick());
            ps2.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return "注册成功！";
    }

    @RequestMapping("/login")
    @ResponseBody
    public String login(User user) {
        try(Connection conn =DBUtils.getConn()) {
            System.out.println("user = " + user);
            String sql = "select password from user where username=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ResultSet re =ps.executeQuery();
            if(re.next()){
                String pw =re.getString(1);
                if(pw.equals(user.getPassword())) {
                    return "登录成功！";
                }else{
                    return "密码错误";
                }
            }else{
                return "用户不存在";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
