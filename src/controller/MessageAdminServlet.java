package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.MessageService;
import entity.PageDeal;
import entity.User;

public class MessageAdminServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		String curPage = request.getParameter("curPage");
		if(curPage==null)
			curPage = "1";
		String recieverId = ((User)session.getAttribute("user")).getId();
		PageDeal page = new PageDeal(Integer.valueOf(curPage), 8, null, recieverId);
		MessageService service = new MessageService();
		List<Map<String,String>> messages = service.findUMessage(page);
		session.setAttribute("messages", messages);
        session.setAttribute("page", page);
		response.sendRedirect("message.jsp");
		
		out.flush();
		out.close();
	}

}
