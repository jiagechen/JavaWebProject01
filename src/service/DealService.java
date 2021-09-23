package service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import dao.DealDaoImpl;
import entity.Deal;
import entity.PageDeal;

public class DealService {
	/*
	 * 1、增加一条交易信息
	 * 2、删除一条交易信息
	 * 3、修改一条交易信息
	 * 4、查询一条交易信息
	 * 5、根据关键字查询当前页面交易信息
	 * 
	 * */
	
	//1、增加一条交易信息
	public void addDeal(Deal deal){
		DealDaoImpl add = new DealDaoImpl();
		add.addDeal(deal);
	}
	
	//2、根据商品ID删除一条交易信息
	public boolean deleteDeal(int id){
		DealDaoImpl check = new DealDaoImpl();
		Deal deal = check.findByCommodityId(id);
		if(deal==null)
			return false;
		check.deleteByCommodityId(id);
		return true;
	}
	
	//3、修改一条交易信息
	public void updateDeal(Deal deal){
		DealDaoImpl updt = new DealDaoImpl();
		updt.updateDeal(deal);
	}
	
	//4、查询一条交易信息
	public Deal findDeal(int id){
		DealDaoImpl find = new DealDaoImpl();
		return find.findByCommodityId(id);
	}
	
    //5、根据关键字查询当前页交易信息
	public List<Deal> findCurPageDeal(PageDeal page){
		List<Deal> deals;//接收返回结果
		DealDaoImpl find = new DealDaoImpl();
		int totalCount,totalPage;
		PageDeal temp;
		if(page.getKey()==null){//查询全部交易
			temp = new PageDeal(1,1000000);
			deals = find.findAllDeal(temp);
			totalCount = deals.size();		
			deals = find.findAllDeal(page);
		}
		else if(page.getKey()=="sellId"){//根据出售者ID查询
			temp = new PageDeal(1,1000000,"sellId",page.getIfm());
			deals = find.findBySellId(temp);
			totalCount = deals.size();		
			deals = find.findBySellId(page);
		}
		else if(page.getKey()=="buyId"){//根据购买者ID查询
			temp = new PageDeal(1,1000000,"buyId",page.getIfm());
			deals = find.findByBuyId(temp);
			totalCount = deals.size();		
			deals = find.findByBuyId(page);
		}
		else{//根据交易状态查询
			temp = new PageDeal(1,1000000,"status",page.getIfm());
			deals = find.findByDealStatus(temp);
			totalCount = deals.size();		
			deals = find.findByDealStatus(page);
		}
		totalPage = totalCount % page.getPageSize() == 0 ? 
				totalCount / page.getPageSize() : 
				totalCount / page.getPageSize() + 1;
		page.setTotalCount(totalCount);
		page.setTotalPage(totalPage);
		return deals;		
	}
		
	public static void main(String[]args){
		DealService test = new DealService();
		Deal deal;
		
		/*
		//1、增加一条交易信息
	    Timestamp dealTime = new Timestamp(System.currentTimeMillis());
		deal = new Deal(8,"口红","623","655",dealTime,1);
		test.addDeal(deal);//运行结果正确
		*/
		
		/*
		//2、根据商品ID删除一条交易信息
		if(test.deleteDeal(11))
			System.out.println("删除成功");
		else
			System.out.println("无法删除正在交易的订单");
		*/
		
		
		//3、修改一条交易信息
		Timestamp changeTime = new Timestamp(System.currentTimeMillis());
		deal = new Deal(8,"口红","623","655",changeTime,2);
		test.updateDeal(deal);//运行结果正确
		/**/
		
		/*
		//4、查询一条交易信息
		deal = test.findDeal(5);
		System.out.println(deal.getCommodityName()+" "+deal.getSellId()+
				" "+deal.getDealTime()+" "+deal.getDealStatus());
		*/
		
		/**/
		//5、根据关键字查询当前页交易信息
		List<Deal> deals;
		PageDeal pageDeal = new PageDeal(2,5);
		//PageDeal pageDeal = new PageDeal(1,3,"status","1");
		deals = test.findCurPageDeal(pageDeal);
		System.out.println("总记录数为："+pageDeal.getTotalCount()+" 总页数为："+pageDeal.getTotalPage());
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		for(int i = 0; i < deals.size(); i++){
			deal = deals.get(i);
			System.out.println(deal.getCommodityName()+" "+deal.getSellId()+
					" "+df.format(deal.getDealTime())+" "+deal.getDealStatus());		
		}
		
	}	

}
