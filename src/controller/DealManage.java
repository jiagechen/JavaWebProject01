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
		//operation共有以下几种类型
		//delete表示删除，post表示上传，delivery表示发货，buy表示购买
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
				service.deleteCommodity(Integer.valueOf(cmdtId));//删除商品信息
				serviceDeal.deleteDeal(Integer.valueOf(cmdtId));//删除交易信息
				out.flush();//清空缓存
		        out.println("<script>");//输出script标签
		        out.println("alert('删除成功');");//js语句：输出alert语句
		        out.println("window.location='DealAdmin';");//js语句：输出网页回退语句
		        out.println("</script>");//输出script结尾标签
			}
			else{
				out.flush();//清空缓存
		        out.println("<script>");//输出script标签
		        out.println("alert('不能删除正在进行的交易信息');");//js语句：输出alert语句
		        out.println("history.back();");//js语句：输出网页回退语句
		        out.println("</script>");//输出script结尾标签
			}
		}
		else{//购买商品
			Commodity cmdt = service.findCommodity(Integer.valueOf(cmdtId));
			cmdt.setCommodityStatus(2);
			service.updateCommodity(cmdt);//更新商品状态信息
			String cmdtName = cmdt.getCommodityName();
			String sellId = cmdt.getSellId();
			String buyId = ((User)session.getAttribute("user")).getId();
			Timestamp time = new Timestamp(System.currentTimeMillis());
			Deal deal = new Deal(Integer.valueOf(cmdtId), cmdtName, sellId, buyId, time, 1);
			DealService serviceDeal = new DealService();
			serviceDeal.addDeal(deal);//添加交易信息
			User user = (User)session.getAttribute("user");
			String type = cmdt.getCommodityType();
			user.setPreference(type);
			UserService serviceUser = new UserService();
			serviceUser.updateIfm(user);//更新个人偏好
			out.flush();//清空缓存
	        out.println("<script>");//输出script标签
	        out.println("alert('购买申请提交成功，等待商家确认');");//js语句：输出alert语句
	        out.println("window.location='CommodityInfo';");//js语句：输出网页回退语句
	        out.println("</script>");//输出script结尾标签
		}
		
		out.flush();
		out.close();
	}

}
