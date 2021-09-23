package controller;

import entity.User;
import service.UserService;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/validateIDServlet.do")
public class ValidateIDServlet extends HttpServlet {
    private UserService userService=new UserService();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String uname=req.getParameter("uname");
        User user = userService.findUser(uname);
        String info="";
        //String info = new String(str.getBytes("用户名已被占用,请重新输入！"), "utf-8");
        if(user!=null){
            info="用户名已被占用,请重新输入！";
        }else{
            info="正确";
        }
//        System.out.println(uname);
//        System.out.println(info);
        resp.getWriter().print(info);
    }
}
