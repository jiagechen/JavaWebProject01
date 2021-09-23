package service;

import java.util.List;

import dao.CommodityDaoImpl;
import dao.SensitiveWord;
import entity.Commodity;
import entity.PageCommodity;

public class CommodityService {
	/*
	 * 1、增加一个商品 
	 * 2、按ID查询一个商品
	 * 3、根据ID删除一个商品
	 * 4、根据ID修改商品信息
	 * 5、根据关键字查询当前页商品信息
	 * 6、查询最近一个商品的ID
	 * 
	 */

	//1、增加一个商品
	public boolean addCommodity(Commodity cmdt){		
		if(cmdt.getDescription().length()>300)
			return false;
		SensitiveWord sensitive = new SensitiveWord();
		String name = cmdt.getCommodityName();
		if(!sensitive.findWord(name))
			return false;
		CommodityDaoImpl temp = new CommodityDaoImpl();
		temp.addCommodity(cmdt);
		return true;
	}
	
	//2、按ID查询一个商品
	public Commodity findCommodity(int id){
		CommodityDaoImpl cmdt = new CommodityDaoImpl();
		return cmdt.findByCommodityId(id);
	}
	
	//3、根据ID删除一个商品
	public boolean deleteCommodity(int id){
		CommodityDaoImpl temp = new CommodityDaoImpl();
		Commodity cmdt = temp.findByCommodityId(id);
		//商品状态 0表示准备发，1表示正在审核，2表示正在交易，3表示正在出售，4表示交易完成，
		//        5表示审核未通过
		if(cmdt==null||cmdt.getCommodityStatus()==2)
			return false;
		temp.deleteByCommodityId(id);
		return true;
	}
	
	//4、根据ID修改商品信息
	public boolean updateCommodity(Commodity cmdt){
		if(cmdt.getDescription().length()>300)
			return false;
		SensitiveWord sensitive = new SensitiveWord();
		String name = cmdt.getCommodityName();
		if(!sensitive.findWord(name))
			return false;
		CommodityDaoImpl temp = new CommodityDaoImpl();
		temp.updateCommodity(cmdt);
		return true;
	}
	
	//5、根据关键字查询当前页商品信息
	public List<Commodity> findCurPageCmdt(PageCommodity page){
		List<Commodity> cmdts;//接收返回结果
		CommodityDaoImpl find = new CommodityDaoImpl();
		int totalCount,totalPage;
		PageCommodity temp;
		if(page.getKey()==null){//查询全部商品
			temp = new PageCommodity(1,1000000);
			cmdts = find.findAllCommodity(temp);
			totalCount = cmdts.size();		
			cmdts = find.findAllCommodity(page);
		}
		else if(page.getKey().equals("name")){//按商品名查询
			page.setIfm("%"+page.getIfm()+"%");
			temp = new PageCommodity(1,1000000,"name",page.getIfm());
			cmdts = find.findByCommodityName(temp);
			totalCount = cmdts.size();
			cmdts = find.findByCommodityName(page);
		}
		else if(page.getKey().equals("sellId")){//按出售者ID查询
			temp = new PageCommodity(1,1000000,"sellId",page.getIfm(),page.getIfm1());
			cmdts = find.findBySellId(temp);
			totalCount = cmdts.size();		
			cmdts = find.findBySellId(page);
		}
		else if(page.getKey().equals("type")){//按商品类型查询
			temp = new PageCommodity(1,1000000,"type",page.getIfm());
			cmdts = find.findByCommodityType(temp);
			totalCount = cmdts.size();		
			cmdts = find.findByCommodityType(page);
		}
		else if(page.getKey().equals("status")){//按商品状态查询
			temp = new PageCommodity(1,1000000,"status",page.getIfm());
			cmdts = find.findByCommodityStatus(temp);
			totalCount = cmdts.size();		
			cmdts = find.findByCommodityStatus(page);
		}
		else {//按商品价格区间查询
			temp = new PageCommodity(1,1000000,"status",page.getIfm(),page.getIfm1());
			cmdts = find.findByPrice(temp);
			totalCount = cmdts.size();		
			cmdts = find.findByPrice(page);
		}
		totalPage = totalCount % page.getPageSize() == 0 ? 
				totalCount / page.getPageSize() : 
				totalCount / page.getPageSize() + 1;
		page.setTotalCount(totalCount);
		page.setTotalPage(totalPage);
		return cmdts;			
	}
	
	//6、查询最近一个商品的ID
	public  Integer findNewCommodityId(){
		CommodityDaoImpl cmdt=new CommodityDaoImpl();
		return cmdt.findNewCommodityId();
	}
	
	//7、查询某个用户的所有出售物品
	public List<Commodity> findCurPageMyCmdt(PageCommodity page){
		List<Commodity> cmdts;//接收返回结果
		CommodityDaoImpl find = new CommodityDaoImpl();
		int totalCount,totalPage;
		PageCommodity temp;
		temp = new PageCommodity(1,1000000,null,page.getIfm());
		cmdts = find.findBySellId(temp);
		totalCount = cmdts.size();		
		cmdts = find.findBySellId(page);		
		totalPage = totalCount % page.getPageSize() == 0 ? 
				totalCount / page.getPageSize() : 
				totalCount / page.getPageSize() + 1;
		page.setTotalCount(totalCount);
		page.setTotalPage(totalPage);
		return cmdts;			
	}
	
	
	
	public static void main(String[]args){
		CommodityService test = new CommodityService();
		Commodity cmdt;
		
	    //1、增加一个商品
		String type[] = {"学习用品","生活用品","电子产品","运动器件"};
		for(int i = 1; i < 50; i++){
			cmdt = new Commodity("test"+i,type[i%4],4,99.9,"655","很好的商品",null);
			
			System.out.println(test.addCommodity(cmdt));//运行结果正确
		}
		    
		/**/
		
		/*
    	//2、按ID查询一个商品
		cmdt = test.findCommodity(19);
		System.out.println(cmdt.getCommodityName()+" "+cmdt.getCommodityType()+
				" "+cmdt.getDescription()+" "+cmdt.getPrice());	
		*/
		
		/*
		//3、根据ID删除一个商品
		if(test.deleteCommodity(19))
			System.out.println("删除成功");
		else
			System.out.println("删除失败");
        */
		
		/*
		//4、根据ID修改商品信息
		cmdt = new Commodity(5,"小米路由器","电子产品",4,56,"618","宿舍里多了一个,用不着",null);
        if(test.updateCommodity(cmdt))
		    System.out.println("修改成功");
        else
		    System.out.println("修改失败");
		*/

		/*
		//5、根据关键字查询当前页商品信息
		List<Commodity> cmdts;
		//PageCommodity pageCmdt = new PageCommodity(2,5);//查询全部商品
		PageCommodity pageCmdt = new PageCommodity(2,40,"name","商品");//按出售者ID查询
		//PageCommodity pageCmdt = new PageCommodity(2,5,"sellId","623");//按出售者ID查询
		//PageCommodity pageCmdt = new PageCommodity(1,5,"price","20","50");//按价格查询
		cmdts = test.findCurPageCmdt(pageCmdt);
		System.out.println("总记录数为："+pageCmdt.getTotalCount()+" 总页数为："+pageCmdt.getTotalPage());
		for(int i = 0; i < cmdts.size(); i++){
			cmdt = cmdts.get(i);
			System.out.println(cmdt.getCommodityName()+" "+cmdt.getCommodityType()+
					" "+cmdt.getDescription()+" "+cmdt.getPrice());		
		}
		*/

	}

}
