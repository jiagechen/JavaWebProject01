package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.CommodityService;
import service.DealService;
import service.UserService;
import entity.Commodity;
import entity.Deal;
import entity.PageCommodity;
import entity.PageDeal;
import entity.User;

public class UserInfoServlet extends HttpServlet {

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
		
		String uId = ((User)session.getAttribute("user")).getId();
		if(uId == null)
		    uId  = "623";
		UserService service = new UserService();
		User user = service.findUser(uId);//用户信息
		session.setAttribute("user", user);
		
		//出售相关信息
		String sellCurPage = (String)request.getParameter("sellCurPage");
		if(sellCurPage==null)
			sellCurPage = "1";
		List<Commodity> sellItem;		
		CommodityService serviceSell = new CommodityService();		
		PageCommodity pageSell = new PageCommodity(Integer.parseInt(sellCurPage),10,"sellId",uId);
		sellItem = serviceSell.findCurPageMyCmdt(pageSell);//出售物品信息
		session.setAttribute("sellItem", sellItem);
		pageSell.setCurPage(Integer.parseInt(sellCurPage));
		session.setAttribute("pageSell", pageSell);
		
		
		String buyCurPage = (String)request.getParameter("buyCurPage");
		if(buyCurPage==null)
			buyCurPage = "1";		
		List<Deal> buyItem;	
		DealService serviceBuy = new DealService();
		PageDeal pageBuy = new PageDeal(Integer.parseInt(buyCurPage),10,"buyId",uId);
		buyItem = serviceBuy.findCurPageDeal(pageBuy);//购买物品信息
		session.setAttribute("buyItem", buyItem);
		pageBuy.setCurPage(Integer.parseInt(buyCurPage));
		session.setAttribute("pageBuy", pageBuy);

		request.getRequestDispatcher("/userInfo.jsp").forward(request,response);

		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
