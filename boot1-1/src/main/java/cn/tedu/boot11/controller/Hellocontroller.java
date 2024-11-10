package cn.tedu.boot11.controller;

import cn.tedu.boot11.vo.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.print.Printable;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class Hellocontroller {

//    @RequestMapping("/hello")
//    public void hello(HttpServletResponse response) throws IOException {
//        System.out.println("服务器接收到了请求");
////        设置响应类型
//        response.setContentType("text/html;charset=utf-8");
//
//        PrintWriter pw=response.getWriter();
//        pw.write("你好  我是服务器！<b>我是标签</b>");
//        pw.close();
//
//    }

    @RequestMapping("/hello")
    @ResponseBody //此注解可以通过返回值的方式给客户端响应数据
    public String hello(HttpServletRequest request){
        String info=request.getParameter("info");
        return "你好 我是服务器<b>我是标签</b> 我是参数："+info;
    }

//    @RequestMapping("reg")
//    @ResponseBody
//    public String reg(String username,String password,String nick,int age){
//        System.out.println("username="+username+",password="+password+",nick="+nick+",age="+age);
//        return "成功！";
//    }

    @RequestMapping("/reg")
    @ResponseBody
    public String reg(User user){
        System.out.println("user = " + user);
        return "成功！";
    }



}
