package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Message;
import entity.User;
import service.MessageService;

public class MessageChatServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		String senderId = ((User)session.getAttribute("user")).getId();
		String recieverId = request.getParameter("recieverId");
		if(recieverId == null)
			recieverId = (String)session.getAttribute("recieverId");
		
		/*
		if(senderId==null || recieverId==null){//仅用于测试
			senderId = "655";
			recieverId = "623";
			session.setAttribute("senderId", senderId);
			session.setAttribute("recieverId", recieverId);
			session.setAttribute("commodityId", "8");
			session.setAttribute("commodityName", "口红");
		}
		*/
		
		session.setAttribute("senderId", senderId);
		session.setAttribute("recieverId", recieverId);

		
		MessageService service = new MessageService();
		if("update".equals(request.getParameter("operation")))
		    service.updateMessage(recieverId, senderId);//更新所有未读私信为已读
		List<Message> messages = service.findNews(senderId, recieverId);
		session.setAttribute("messages", messages);
		response.sendRedirect("messageChat.jsp");
		
		out.flush();
		out.close();
	}

}
