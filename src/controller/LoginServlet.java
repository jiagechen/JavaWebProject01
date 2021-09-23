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
      //  String userId = request.getParameter("userName");   //�����ѧ��
       // String password = request.getParameter("password");   //���������
        String userId=new String(request.getParameter("userName").getBytes("ISO-8859-1"));//�����ѧ��
        String password=new String(request.getParameter("password").getBytes("ISO-8859-1"));//���������
        UserService userService=new UserService();
        User user=new User();
        user.setId(userId);
        user.setPassword(password);

        if (userService.loginJudge(user)==1) { //������ȷ
            user=userService.findUser(userId);
            if (userService.loginJudge(user)==-2) { //�û������
                request.setAttribute("msg", "��¼ʧ��");
                response.setCharacterEncoding("GBK");
                PrintWriter pw = response.getWriter();
                pw.println("<script type=\"text/javascript\">");
                pw.println("alert(\"���ڶ��Υ�棬�����˺��ѱ������\");");
                pw.println("open(\"login.jsp\",\"_self\");");
                pw.println("</script>");
                pw.close();
            }
            //��¼�ɹ�
            String preference = user.getPreference();
            CommodityService service = new CommodityService();
            PageCommodity page = new PageCommodity(1,3,"type",preference);
            List<Commodity> userLikes = service.findCurPageCmdt(page);
            if(userLikes.size()<3){
            	page = new PageCommodity(1,3);
            	userLikes = service.findCurPageCmdt(page);
            }
            
            session.setAttribute("userLikes", userLikes);//�����Ƽ���Ʒ            
            session.setAttribute("user", user); //��¼�ɹ������session�����Ϊuser
            /*****************������ת����ҳ������Ա��ѧ���û���ת����ͬ����ҳ*************************/
            if(user.getStatus()==2){
                response.sendRedirect("userIndex.jsp");
            }else if(user.getStatus()==1){
                response.sendRedirect("adminIndex.jsp");
            }
        } else if (userService.loginJudge(user)==-1){ //�������
            request.setAttribute("msg","��¼ʧ��");
//           request.getRequestDispatcher("/login.jsp").forward(request,response);
            response.setCharacterEncoding("GBK");
            PrintWriter pw=response.getWriter();
            pw.println("<script type=\"text/javascript\">");
            pw.println("alert(\"����������������룡\");");
            pw.println("open(\"login.jsp\",\"_self\");");
            pw.println("</script>");
            pw.close();
      }else if (userService.loginJudge(user)==0){ //�û�������
            request.setAttribute("msg","��¼ʧ��");
//           request.getRequestDispatcher("/login.jsp").forward(request,response);
            response.setCharacterEncoding("GBK");
            PrintWriter pw=response.getWriter();
            pw.println("<script type=\"text/javascript\">");
            pw.println("alert(\"��������û������ڣ���ӭע�ᣡ\");");
            pw.println("open(\"login.jsp\",\"_self\");");
            pw.println("</script>");
            pw.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
