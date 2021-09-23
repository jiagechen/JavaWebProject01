package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Commodity;
import entity.Deal;
import entity.User;
import service.CommodityService;
import service.DealService;
import service.UserService;

public class DealManage extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		String operation = request.getParameter("operation");
		String cmdtId = request.getParameter("cmdtId");
		CommodityService service = new CommodityService();
		//operation�������¼�������
		//delete��ʾɾ����post��ʾ�ϴ���delivery��ʾ������buy��ʾ����
		if(operation.equals("delete")){	    
		    service.deleteCommodity(Integer.valueOf(cmdtId));
			request.getRequestDispatcher("UserInfo").forward(request,response);
		}else if(operation.equals("delivery")){
			Commodity cmdt = service.findCommodity(Integer.valueOf(cmdtId));
			cmdt.setCommodityStatus(4);
			service.updateCommodity(cmdt);
			DealService serviceDeal = new DealService();
			Deal deal = serviceDeal.findDeal(Integer.valueOf(cmdtId));
			deal.setDealStatus(2);
			serviceDeal.updateDeal(deal);
			request.getRequestDispatcher("UserInfo").forward(request,response);
		}else if(operation.equals("post")){
			Commodity cmdt = service.findCommodity(Integer.valueOf(cmdtId));
			cmdt.setCommodityStatus(3);
			service.updateCommodity(cmdt);
			request.getRequestDispatcher("UserInfo").forward(request,response);
		}else if(operation.equals("cancle")){
			Commodity cmdt = service.findCommodity(Integer.valueOf(cmdtId));
			cmdt.setCommodityStatus(3);
			service.updateCommodity(cmdt);
			DealService serviceDeal = new DealService();
			serviceDeal.deleteDeal(cmdt.getCommodityId());
			request.getRequestDispatcher("UserInfo").forward(request,response);
		}else if(operation.equals("deleteDeal")){
			DealService serviceDeal = new DealService();
			Deal deal = serviceDeal.findDeal(Integer.valueOf(cmdtId));
			if(deal.getDealStatus()!=1){
				service.deleteCommodity(Integer.valueOf(cmdtId));//ɾ����Ʒ��Ϣ
				serviceDeal.deleteDeal(Integer.valueOf(cmdtId));//ɾ��������Ϣ
				out.flush();//��ջ���
		        out.println("<script>");//���script��ǩ
		        out.println("alert('ɾ���ɹ�');");//js��䣺���alert���
		        out.println("window.location='DealAdmin';");//js��䣺�����ҳ�������
		        out.println("</script>");//���script��β��ǩ
			}
			else{
				out.flush();//��ջ���
		        out.println("<script>");//���script��ǩ
		        out.println("alert('����ɾ�����ڽ��еĽ�����Ϣ');");//js��䣺���alert���
		        out.println("history.back();");//js��䣺�����ҳ�������
		        out.println("</script>");//���script��β��ǩ
			}
		}
		else{//������Ʒ
			Commodity cmdt = service.findCommodity(Integer.valueOf(cmdtId));
			cmdt.setCommodityStatus(2);
			service.updateCommodity(cmdt);//������Ʒ״̬��Ϣ
			String cmdtName = cmdt.getCommodityName();
			String sellId = cmdt.getSellId();
			String buyId = ((User)session.getAttribute("user")).getId();
			Timestamp time = new Timestamp(System.currentTimeMillis());
			Deal deal = new Deal(Integer.valueOf(cmdtId), cmdtName, sellId, buyId, time, 1);
			DealService serviceDeal = new DealService();
			serviceDeal.addDeal(deal);//��ӽ�����Ϣ
			User user = (User)session.getAttribute("user");
			String type = cmdt.getCommodityType();
			user.setPreference(type);
			UserService serviceUser = new UserService();
			serviceUser.updateIfm(user);//���¸���ƫ��
			out.flush();//��ջ���
	        out.println("<script>");//���script��ǩ
	        out.println("alert('���������ύ�ɹ����ȴ��̼�ȷ��');");//js��䣺���alert���
	        out.println("window.location='CommodityInfo';");//js��䣺�����ҳ�������
	        out.println("</script>");//���script��β��ǩ
		}
		
		out.flush();
		out.close();
	}

}
