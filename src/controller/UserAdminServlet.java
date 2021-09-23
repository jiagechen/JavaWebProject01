package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.UserService;
import entity.PageUser;
import entity.User;

public class UserAdminServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		String operation = request.getParameter("operation");
		UserService service = new UserService();

		if("restrict".equals(operation)){//���
			String uId = request.getParameter("uId");
			User user = service.findUser(uId);
			user.setStatus(0);
			service.updateIfm(user);
			out.flush();//��ջ���
	        out.println("<script>");//���script��ǩ
	        out.println("alert('��ųɹ�');");//js��䣺���alert���
	        out.println("window.location='UserAdmin';");//js��䣺�����ҳ�������
	        out.println("</script>");//���script��β��ǩ
		}
		else if("remove".equals(operation)){//���
			String uId = request.getParameter("uId");
			User user = service.findUser(uId);
			user.setStatus(2);
			user.setReputation(100);
			service.updateIfm(user);
			out.flush();//��ջ���
	        out.println("<script>");//���script��ǩ
	        out.println("alert('���ɹ�');");//js��䣺���alert���
	        out.println("window.location='UserAdmin';");//js��䣺�����ҳ�������
	        out.println("</script>");//���script��β��ǩ
		}
		else{
			String curPage = request.getParameter("curPage");
			if(curPage==null)
				curPage = "1";
		
			PageUser page = new PageUser(Integer.valueOf(curPage), 10);
			List<User> users = service.findSomeUser(page);
			int totalPage = page.getTotalPage();//
			session.setAttribute("users", users);
			session.setAttribute("curPage", curPage);
			session.setAttribute("totalPage", totalPage);
			response.sendRedirect("userAdmin.jsp");

		}
		out.flush();
		out.close();
	}

}
