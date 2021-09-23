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
		if("find".equals(operation)){//��ѯһ��Ͷ�ߵ���Ʒ��Ϣ
			String cmdtId = request.getParameter("cmdtId");
			CommodityService serviceCmdt = new CommodityService();
			Commodity cmdt = serviceCmdt.findCommodity(Integer.valueOf(cmdtId));
			session.setAttribute("cmdt", cmdt);
			String commentId = request.getParameter("commentId");
			String content = (serviceCom.findComment(Integer.valueOf(cmdtId), -1,
					Integer.valueOf(commentId))).getContent();
			session.setAttribute("content", content);
			request.getRequestDispatcher("/complaint.jsp").forward(request,response);

		}else if("handle".equals(operation)){//����һ��Ͷ��
			String cmdtId = request.getParameter("cmdtId");
			CommodityService serviceCmdt = new CommodityService();
			Commodity cmdt = serviceCmdt.findCommodity(Integer.valueOf(cmdtId));
            String sellId = cmdt.getSellId();
			UserService serviceUser = new UserService();
			User user = serviceUser.findUser(sellId);
			user.setReputation(user.getReputation()-10);
			serviceUser.updateIfm(user);//�����û�����
			String commentId = request.getParameter("commentId");
			Comment com = (serviceCom.findComment(Integer.valueOf(cmdtId), -1, 
					Integer.valueOf(commentId)));
			com.setType(4);
			serviceCom.updateComment(com);//��������״̬��δ�� -> �Ѷ���
			
			out.flush();//��ջ���
	        out.println("<script>");//���script��ǩ
	        out.println("alert('����ɹ�');");//js��䣺���alert���
	        out.println("window.location='CmpltAdmin';");//js��䣺�����ҳ�������
	        out.println("</script>");//���script��β��ǩ
		}else if("cancle".equals(operation)){//����һ��Ͷ��
			String cmdtId = request.getParameter("cmdtId");
			String commentId = request.getParameter("commentId");
			Comment com = (serviceCom.findComment(Integer.valueOf(cmdtId), -1,
					Integer.valueOf(commentId)));
			com.setType(4);
			serviceCom.updateComment(com);//��������״̬��δ�� -> �Ѷ���
			
			out.flush();//��ջ���
	        out.println("<script>");//���script��ǩ
	        out.println("alert('��Ͷ���Ѻ���');");//js��䣺���alert���
	        out.println("window.location='CmpltAdmin';");//js��䣺�����ҳ�������
	        out.println("</script>");//���script��β��ǩ
		}else if("delete".equals(operation)){
			String cmdtId = request.getParameter("cmdtId");
			String commentId = request.getParameter("commentId");
			serviceCom.deleteComment(Integer.valueOf(cmdtId), -1, Integer.valueOf(commentId));

			out.flush();//��ջ���
	        out.println("<script>");//���script��ǩ
	        out.println("alert('ɾ���ɹ�');");//js��䣺���alert���
	        out.println("window.location='CmpltAdmin';");//js��䣺�����ҳ�������
	        out.println("</script>");//���script��β��ǩ
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
