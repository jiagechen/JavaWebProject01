package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.CommodityService;
import entity.Commodity;
import entity.PageCommodity;

public class CommodityListServlet extends HttpServlet {

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
		String curPageStr = request.getParameter("currentPage");
		PageCommodity page;
		int curPage;
		
		if(curPageStr==null||curPageStr.equals("0"))
			curPage = 1;
		else 
			curPage = Integer.parseInt(curPageStr);
		
		if(operation==null||operation.equals("null")){//初始状态
			page = new PageCommodity(curPage,9);
		}else if(operation.equals("getCmdtByName")){//按物品名查询
			String name = null;
			if(session.getAttribute("key")!=null){
				name = (String) session.getAttribute("key");
			}
			if(request.getParameter("itemName")!=null){
				name = (String) request.getParameter("itemName");
			}
			page = new PageCommodity(curPage,9,"name",name);
			session.setAttribute("key", name);
		}else if(operation.equals("getCmdtByType")){//按物品类别查询
			String type = null;
			if(session.getAttribute("key")!=null){//保存上一次查询的关键字
				type = (String) session.getAttribute("key");
			}
			if(request.getParameter("itemType")!=null){//不为空表示本次进行了查询，否则表示本次进行了分页
				type = (String) request.getParameter("itemType");
			}
			page = new PageCommodity(curPage,9,"type",type);
			session.setAttribute("key", type);
		}else{//按价格区间查询
			int low = 0, high = 1 >> 30;
			if(session.getAttribute("key")!=null){
				low = (Integer) session.getAttribute("low");
				high = (Integer) session.getAttribute("high");
			}							
			if(session.getAttribute("low")!=null && session.getAttribute("high")!=null){
				low = (Integer) session.getAttribute("low");
				high = (Integer) session.getAttribute("high");
			}
			page = new PageCommodity(curPage,9,"lostType",
					Integer.toString(low),Integer.toString(high));
			session.setAttribute("low", low);
			session.setAttribute("high", high);
		}
		CommodityService service = new CommodityService();
		List<Commodity> items = service.findCurPageCmdt(page);
		session.setAttribute("items",items);
		session.setAttribute("page",page);
		session.setAttribute("operation",operation);

		request.getRequestDispatcher("/commodityList.jsp").forward(request,response);

		out.flush();
		out.close();
	}


}
