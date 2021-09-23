package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.CommentService;
import entity.Comment;
import entity.Commodity;
import entity.User;

public class AddComplaintServlet extends HttpServlet {

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
		String buyId, sellId, cmdtName, content;
		int cmdtId;
		if("request".equals(operation))//发表投诉的请求
			response.sendRedirect("complaint.jsp");
		else{
			buyId = ((User)session.getAttribute("user")).getId();
            Commodity cmdt = (Commodity)session.getAttribute("cmdt");
            sellId = cmdt.getSellId();
            cmdtId = cmdt.getCommodityId();
            cmdtName = cmdt.getCommodityName();
            content = request.getParameter("content");
            Timestamp time = new Timestamp(System.currentTimeMillis());
            Comment comment = new Comment(cmdtId, cmdtName, buyId, sellId,
            		content, time, 3);
            CommentService service = new CommentService();
            service.addComment(comment);
            out.flush();//清空缓存
            out.println("<script>");//输出script标签
            out.println("alert('投诉成功');");//js语句：输出alert语句
            out.println("window.location='CommodityInfo';");//js语句：输出网页回退语句
            out.println("</script>");//输出script结尾标签
		}
		
		out.flush();
		out.close();
	}

}
