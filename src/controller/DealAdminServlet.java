package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.DealService;
import entity.Deal;
import entity.PageDeal;

public class DealAdminServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		String curPage = (String)request.getParameter("curPage");
		if(curPage==null)
			curPage = "1";
		List<Deal> deals;		
		DealService service = new DealService();		
		PageDeal page = new PageDeal(Integer.parseInt(curPage),7);
		deals = service.findCurPageDeal(page);
		session.setAttribute("deals", deals);
		page.setCurPage(Integer.parseInt(curPage));
		session.setAttribute("page", page);
		request.getRequestDispatcher("/dealAdmin.jsp").forward(request,response);

		
		out.flush();
		out.close();
	}

}
