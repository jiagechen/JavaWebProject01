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

public class AddCommentServlet extends HttpServlet {

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
		
		String senderId, content;
		senderId = ((User)session.getAttribute("user")).getId();		
		content = request.getParameter("content");
		if (content.length() > 300) {
            out.flush();//��ջ���
            out.println("<script>");//���script��ǩ
            out.println("alert('�������ݹ���');");//js��䣺���alert���
            out.println("window.location='CommodityInfo';");//js��䣺�����ҳ�������
            out.println("</script>");//���script��β��ǩ
        }
		
		Commodity cmdt = (Commodity)session.getAttribute("cmdt");
		Timestamp time = new Timestamp(System.currentTimeMillis());
		Comment com = new Comment(cmdt.getCommodityId(), cmdt.getCommodityName(),
				senderId, content, time, 1, 0);
		CommentService service = new CommentService();
		
		if(service.addComment(com)){
			out.flush();//��ջ���
	        out.println("<script>");//���script��ǩ
	        out.println("alert('���۳ɹ�');");//js��䣺���alert���
	        out.println("window.location='CommodityInfo';");//js��䣺�����ҳ�������
	        out.println("</script>");//���script��β��ǩ
		}
		else{
			out.flush();//��ջ���
	        out.println("<script>");//���script��ǩ
	        out.println("alert('����������ݷǷ�������ʧ��');");//js��䣺���alert���
	        out.println("window.location='CommodityInfo';");//js��䣺�����ҳ�������
	        out.println("</script>");//���script��β��ǩ
		}
        
        
		out.flush();
		out.close();
	}

}
