package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.CommentService;
import service.CommodityService;
import service.UserService;
import entity.Comment;
import entity.Commodity;
import entity.PageDeal;
import entity.User;

public class CmpltAdminServlet extends HttpServlet {

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
		
		CommentService serviceCom = new CommentService();		
		String curPage = (String)request.getParameter("curPage");
		if(curPage==null)
			curPage = "1";
		String operation = request.getParameter("operation");
		if("find".equals(operation)){//查询一条投诉的商品信息
			String cmdtId = request.getParameter("cmdtId");
			CommodityService serviceCmdt = new CommodityService();
			Commodity cmdt = serviceCmdt.findCommodity(Integer.valueOf(cmdtId));
			session.setAttribute("cmdt", cmdt);
			String commentId = request.getParameter("commentId");
			String content = (serviceCom.findComment(Integer.valueOf(cmdtId), -1,
					Integer.valueOf(commentId))).getContent();
			session.setAttribute("content", content);
			request.getRequestDispatcher("/complaint.jsp").forward(request,response);

		}else if("handle".equals(operation)){//处理一条投诉
			String cmdtId = request.getParameter("cmdtId");
			CommodityService serviceCmdt = new CommodityService();
			Commodity cmdt = serviceCmdt.findCommodity(Integer.valueOf(cmdtId));
            String sellId = cmdt.getSellId();
			UserService serviceUser = new UserService();
			User user = serviceUser.findUser(sellId);
			user.setReputation(user.getReputation()-10);
			serviceUser.updateIfm(user);//更新用户信誉
			String commentId = request.getParameter("commentId");
			Comment com = (serviceCom.findComment(Integer.valueOf(cmdtId), -1, 
					Integer.valueOf(commentId)));
			com.setType(4);
			serviceCom.updateComment(com);//更新评论状态（未读 -> 已读）
			
			out.flush();//清空缓存
	        out.println("<script>");//输出script标签
	        out.println("alert('处理成功');");//js语句：输出alert语句
	        out.println("window.location='CmpltAdmin';");//js语句：输出网页回退语句
	        out.println("</script>");//输出script结尾标签
		}else if("cancle".equals(operation)){//忽略一条投诉
			String cmdtId = request.getParameter("cmdtId");
			String commentId = request.getParameter("commentId");
			Comment com = (serviceCom.findComment(Integer.valueOf(cmdtId), -1,
					Integer.valueOf(commentId)));
			com.setType(4);
			serviceCom.updateComment(com);//更新评论状态（未读 -> 已读）
			
			out.flush();//清空缓存
	        out.println("<script>");//输出script标签
	        out.println("alert('该投诉已忽略');");//js语句：输出alert语句
	        out.println("window.location='CmpltAdmin';");//js语句：输出网页回退语句
	        out.println("</script>");//输出script结尾标签
		}else if("delete".equals(operation)){
			String cmdtId = request.getParameter("cmdtId");
			String commentId = request.getParameter("commentId");
			serviceCom.deleteComment(Integer.valueOf(cmdtId), -1, Integer.valueOf(commentId));

			out.flush();//清空缓存
	        out.println("<script>");//输出script标签
	        out.println("alert('删除成功');");//js语句：输出alert语句
	        out.println("window.location='CmpltAdmin';");//js语句：输出网页回退语句
	        out.println("</script>");//输出script结尾标签
		}
		else{
			PageDeal page = new PageDeal(Integer.parseInt(curPage),8);
			List<Comment> comments = serviceCom.findType3(page);
			session.setAttribute("comments", comments);
			page.setCurPage(Integer.parseInt(curPage));
			session.setAttribute("page", page);
			request.getRequestDispatcher("/complaintAdmin.jsp").forward(request,response);
		}	
		out.flush();
		out.close();
	}

}
