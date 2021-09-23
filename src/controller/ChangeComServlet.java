package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.CommentService;
import entity.Commodity;

public class ChangeComServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		String operation = request.getParameter("operation");
		String cmdtId = request.getParameter("cmdtId");
		String topicId = request.getParameter("topicId");
		CommentService service = new CommentService();
		
		if(operation.equals("delete"))//É¾³ý
			service.deleteComment(Integer.valueOf(cmdtId), Integer.valueOf(topicId), 1);		
		else//µãÔÞ
			service.addLikeNum(Integer.valueOf(cmdtId), Integer.valueOf(topicId), 1);					
		
		response.sendRedirect("CommodityInfo");
		
		out.flush();
		out.close();
	}

}
