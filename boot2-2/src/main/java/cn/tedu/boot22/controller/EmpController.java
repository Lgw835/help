package cn.tedu.boot22.controller;

import cn.tedu.boot22.utils.DBUtils;
import cn.tedu.boot22.vo.Emp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController //作用：相当于每个方法上都添加了@ResponseBody
public class EmpController {

    @RequestMapping("/insert")
    public String insert(Emp emp) {
        System.out.println("emp = " + emp);

        try(Connection conn = DBUtils.getConn()) {
            String sql = "insert into myemp value(null,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,emp.getName());
            ps.setString(2,emp.getJob());
            ps.setInt(3,emp.getSal());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return "添加完成!<a herf='/'>回到首页</a>";
    }

    @RequestMapping("select")
    public String select() {
        List<Emp> list = new ArrayList<>();
        try(Connection conn =DBUtils.getConn()) {
            String sql="select * from myemp";
            PreparedStatement ps =conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String job = rs.getString(3);
                int sal = rs.getInt(4);
                System.out.println(id + "," + name + "," + job + "," + sal);

                list.add(new Emp(id, name, job, sal));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String html="<table border=1>";
        html+="<caption>员工列表</caption>";
        html+="<tr><th>id</th><th>名字</th><th>工作</th><th>工资</th><th>操作</th></tr>";
        //添加多行员工信息
        for (Emp emp:list) {
            html+="<tr>";
            html+="<td>"+emp.getId()+"</td>";
            html+="<td>"+emp.getName()+"</td>";
            html+="<td>"+emp.getJob()+"</td>";
            html+="<td>"+emp.getSal()+"</td>";
            html+="<td><a href='/delete?id="+emp.getId()+"'>删除</a></td>";
            html+="</tr>";
        }
        html+="</table>";
        return  html;


//        return list.toString();
//        return "查询完成!<a herf='/'>回到首页</a>";
    }


    @RequestMapping("/delete")
    public String delete(int id){
        try (Connection conn = DBUtils.getConn()){
            String sql = "delete from myemp where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();//执行删除
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "删除完成!<a href='/select'>返回列表页面</a>";
    }

    @RequestMapping("/update")
    public String update(Emp emp) {
        try (Connection conn = DBUtils.getConn()) {
            String sql = "update myemp set name=?,job=?,sal=? where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, emp.getName());
            ps.setString(2, emp.getJob());
            ps.setInt(3, emp.getSal());
            ps.setInt(4, emp.getId());
            ps.executeUpdate();//执行更新
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "更新完成!<a href='/select'>返回列表页面</a>";
    }

}
