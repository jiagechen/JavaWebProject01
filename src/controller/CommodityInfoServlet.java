package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Comment;
import entity.Commodity;
import service.CommentService;
import service.CommodityService;

public class CommodityInfoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		String cmdtId = request.getParameter("itemId");
		if(cmdtId==null)
			cmdtId = String.valueOf(((Commodity)session.getAttribute("cmdt")).getCommodityId());			
		CommodityService serviceCmdt = new CommodityService();
		Commodity cmdt = serviceCmdt.findCommodity(Integer.valueOf(cmdtId));
		CommentService serviceCom = new CommentService();
		List<Comment> comments = serviceCom.findCmdtComs(Integer.valueOf(cmdtId));
		session.setAttribute("cmdt", cmdt);
		session.setAttribute("comments", comments);
		response.sendRedirect("commodityInfo.jsp");
		
		out.flush();
		out.close();
	}

}
