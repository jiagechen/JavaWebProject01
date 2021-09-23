package controller;

import entity.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Windows10.0 on 2020/9/10.
 */
@WebServlet(name = "SignupServlet")
public class SignupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        UserService userService = new UserService();
        HttpSession session = request.getSession();
        String userId = request.getParameter("uId");  //输入的学号
        String userName = request.getParameter("uName");   //输入的昵称
        String password = request.getParameter("password");   //输入的密码
        String passwordConfirm = request.getParameter("passwordConfirm");   //输入的密码
        String college = request.getParameter("college");   //输入的学院
        User newUser = new User(userId, userName, password, college);

        if(!password.equals(passwordConfirm)){ //两次输入密码不一致
            request.setAttribute("msg","注册失败");
            response.setCharacterEncoding("GBK");
            PrintWriter pw=response.getWriter();
            pw.println("<script type=\"text/javascript\">");
            pw.println("alert(\"两次密码输入不一致！请重新输入！\");");
            pw.println("open(\"register.jsp\",\"_self\");");
            pw.println("</script>");
            pw.close();
        }else if(userService.addUser(newUser)){//注册成功
                /***************跳转到首页******************/
            request.setAttribute("msg","注册成功");
            response.setCharacterEncoding("GBK");
            PrintWriter pw=response.getWriter();
            pw.println("<script type=\"text/javascript\">");
            pw.println("alert(\"注册成功！\");");
            pw.println("open(\"login.jsp\",\"_self\");");
            pw.println("</script>");
            pw.close();
        }else{//用户已注册
            request.setAttribute("msg","注册失败");
            response.setCharacterEncoding("GBK");
            PrintWriter pw=response.getWriter();
            pw.println("<script type=\"text/javascript\">");
            pw.println("alert(\"您已经注册过账号，请直接登录！\");");
            pw.println("open(\"register.jsp\",\"_self\");");
            pw.println("</script>");
            pw.close();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
