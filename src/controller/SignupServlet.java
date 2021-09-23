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
        String userId = request.getParameter("uId");  //�����ѧ��
        String userName = request.getParameter("uName");   //������ǳ�
        String password = request.getParameter("password");   //���������
        String passwordConfirm = request.getParameter("passwordConfirm");   //���������
        String college = request.getParameter("college");   //�����ѧԺ
        User newUser = new User(userId, userName, password, college);

        if(!password.equals(passwordConfirm)){ //�����������벻һ��
            request.setAttribute("msg","ע��ʧ��");
            response.setCharacterEncoding("GBK");
            PrintWriter pw=response.getWriter();
            pw.println("<script type=\"text/javascript\">");
            pw.println("alert(\"�����������벻һ�£����������룡\");");
            pw.println("open(\"register.jsp\",\"_self\");");
            pw.println("</script>");
            pw.close();
        }else if(userService.addUser(newUser)){//ע��ɹ�
                /***************��ת����ҳ******************/
            request.setAttribute("msg","ע��ɹ�");
            response.setCharacterEncoding("GBK");
            PrintWriter pw=response.getWriter();
            pw.println("<script type=\"text/javascript\">");
            pw.println("alert(\"ע��ɹ���\");");
            pw.println("open(\"login.jsp\",\"_self\");");
            pw.println("</script>");
            pw.close();
        }else{//�û���ע��
            request.setAttribute("msg","ע��ʧ��");
            response.setCharacterEncoding("GBK");
            PrintWriter pw=response.getWriter();
            pw.println("<script type=\"text/javascript\">");
            pw.println("alert(\"���Ѿ�ע����˺ţ���ֱ�ӵ�¼��\");");
            pw.println("open(\"register.jsp\",\"_self\");");
            pw.println("</script>");
            pw.close();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
