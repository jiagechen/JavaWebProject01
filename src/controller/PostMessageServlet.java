package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.MessageService;
import entity.Commodity;
import entity.Message;
import entity.User;

public class PostMessageServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		String senderId = (String)session.getAttribute("senderId");
		String recieveId = (String)session.getAttribute("recieverId");
        User user = (User)session.getAttribute("user");
		Commodity cmdt = (Commodity)session.getAttribute("cmdt");
		if(cmdt==null){
			String content = request.getParameter("content");	
			Timestamp postTime = new Timestamp(System.currentTimeMillis());			
			Message message = new Message(content, senderId, recieveId, 
					postTime, 1, -1, null);
			MessageService service = new MessageService();
			service.addNews(message);
		}else{
			String cmdtName = cmdt.getCommodityName();
			Integer cmdtId = cmdt.getCommodityId();
			String content = request.getParameter("content");	
			Timestamp postTime = new Timestamp(System.currentTimeMillis());		
			Message message = new Message(content, senderId, recieveId, 
					postTime, 1, Integer.valueOf(cmdtId), cmdtName);
			MessageService service = new MessageService();
			service.addNews(message);
		}
		response.sendRedirect("MessageChat");
	
		out.flush();
		out.close();
	}

}
