package controller;

import entity.Commodity;
import entity.PageCommodity;
import entity.User;
import service.CommodityService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Windows10.0 on 2020/9/10.
 */
@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
      //  String userId = request.getParameter("userName");   //输入的学号
       // String password = request.getParameter("password");   //输入的密码
        String userId=new String(request.getParameter("userName").getBytes("ISO-8859-1"));//输入的学号
        String password=new String(request.getParameter("password").getBytes("ISO-8859-1"));//输入的密码
        UserService userService=new UserService();
        User user=new User();
        user.setId(userId);
        user.setPassword(password);

        if (userService.loginJudge(user)==1) { //密码正确
            user=userService.findUser(userId);
            if (userService.loginJudge(user)==-2) { //用户被封号
                request.setAttribute("msg", "登录失败");
                response.setCharacterEncoding("GBK");
                PrintWriter pw = response.getWriter();
                pw.println("<script type=\"text/javascript\">");
                pw.println("alert(\"由于多次违规，您的账号已被封禁！\");");
                pw.println("open(\"login.jsp\",\"_self\");");
                pw.println("</script>");
                pw.close();
            }
            //登录成功
            String preference = user.getPreference();
            CommodityService service = new CommodityService();
            PageCommodity page = new PageCommodity(1,3,"type",preference);
            List<Commodity> userLikes = service.findCurPageCmdt(page);
            if(userLikes.size()<3){
            	page = new PageCommodity(1,3);
            	userLikes = service.findCurPageCmdt(page);
            }
            
            session.setAttribute("userLikes", userLikes);//保存推荐商品            
            session.setAttribute("user", user); //登录成功后存在session里，命名为user
            /*****************这里跳转到首页，管理员和学生用户跳转到不同的首页*************************/
            if(user.getStatus()==2){
                response.sendRedirect("userIndex.jsp");
            }else if(user.getStatus()==1){
                response.sendRedirect("adminIndex.jsp");
            }
        } else if (userService.loginJudge(user)==-1){ //密码错误
            request.setAttribute("msg","登录失败");
//           request.getRequestDispatcher("/login.jsp").forward(request,response);
            response.setCharacterEncoding("GBK");
            PrintWriter pw=response.getWriter();
            pw.println("<script type=\"text/javascript\">");
            pw.println("alert(\"密码错误！请重新输入！\");");
            pw.println("open(\"login.jsp\",\"_self\");");
            pw.println("</script>");
            pw.close();
      }else if (userService.loginJudge(user)==0){ //用户不存在
            request.setAttribute("msg","登录失败");
//           request.getRequestDispatcher("/login.jsp").forward(request,response);
            response.setCharacterEncoding("GBK");
            PrintWriter pw=response.getWriter();
            pw.println("<script type=\"text/javascript\">");
            pw.println("alert(\"您输入的用户不存在！欢迎注册！\");");
            pw.println("open(\"login.jsp\",\"_self\");");
            pw.println("</script>");
            pw.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
