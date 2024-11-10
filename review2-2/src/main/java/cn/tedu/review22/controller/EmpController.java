package cn.tedu.review22.controller;

import cn.tedu.review22.utils.DBUtils;
import cn.tedu.review22.vo.Emp;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class EmpController {
    @RequestMapping("/add")
    public String add(Emp emp) {
        System.out.println("emp = " + emp);
        try(Connection conn = DBUtils.getConn()) {
            String sql="insert into myemp value(null,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,emp.getName());
            ps.setString(2,emp.getJob());
            ps.setInt(3,emp.getSal());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "已经插入成功,点击返回<a href='/'>首页</a>; 点击返回上一层<a href='/add.html'>添加员工</a>";

    }


    @RequestMapping("/select")
    public String select(){
        List<Emp> list = new ArrayList<>();
        try(Connection conn = DBUtils.getConn()) {
            String sql = "select * from myemp";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Emp emp=new Emp();
                emp.setId(rs.getInt(1));
                emp.setName(rs.getString(2));
                emp.setJob(rs.getString(3));
                emp.setSal(rs.getInt(4));

                System.out.println("emp="+emp);
                list.add(emp);
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

    }

    @RequestMapping("/delete")
    public String delete(int id){
        try(Connection conn = DBUtils.getConn()) {
            String sql = "delete from myemp where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
