package service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import dao.DealDaoImpl;
import entity.Deal;
import entity.PageDeal;

public class DealService {
	/*
	 * 1������һ��������Ϣ
	 * 2��ɾ��һ��������Ϣ
	 * 3���޸�һ��������Ϣ
	 * 4����ѯһ��������Ϣ
	 * 5�����ݹؼ��ֲ�ѯ��ǰҳ�潻����Ϣ
	 * 
	 * */
	
	//1������һ��������Ϣ
	public void addDeal(Deal deal){
		DealDaoImpl add = new DealDaoImpl();
		add.addDeal(deal);
	}
	
	//2��������ƷIDɾ��һ��������Ϣ
	public boolean deleteDeal(int id){
		DealDaoImpl check = new DealDaoImpl();
		Deal deal = check.findByCommodityId(id);
		if(deal==null)
			return false;
		check.deleteByCommodityId(id);
		return true;
	}
	
	//3���޸�һ��������Ϣ
	public void updateDeal(Deal deal){
		DealDaoImpl updt = new DealDaoImpl();
		updt.updateDeal(deal);
	}
	
	//4����ѯһ��������Ϣ
	public Deal findDeal(int id){
		DealDaoImpl find = new DealDaoImpl();
		return find.findByCommodityId(id);
	}
	
    //5�����ݹؼ��ֲ�ѯ��ǰҳ������Ϣ
	public List<Deal> findCurPageDeal(PageDeal page){
		List<Deal> deals;//���շ��ؽ��
		DealDaoImpl find = new DealDaoImpl();
		int totalCount,totalPage;
		PageDeal temp;
		if(page.getKey()==null){//��ѯȫ������
			temp = new PageDeal(1,1000000);
			deals = find.findAllDeal(temp);
			totalCount = deals.size();		
			deals = find.findAllDeal(page);
		}
		else if(page.getKey()=="sellId"){//���ݳ�����ID��ѯ
			temp = new PageDeal(1,1000000,"sellId",page.getIfm());
			deals = find.findBySellId(temp);
			totalCount = deals.size();		
			deals = find.findBySellId(page);
		}
		else if(page.getKey()=="buyId"){//���ݹ�����ID��ѯ
			temp = new PageDeal(1,1000000,"buyId",page.getIfm());
			deals = find.findByBuyId(temp);
			totalCount = deals.size();		
			deals = find.findByBuyId(page);
		}
		else{//���ݽ���״̬��ѯ
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
		//1������һ��������Ϣ
	    Timestamp dealTime = new Timestamp(System.currentTimeMillis());
		deal = new Deal(8,"�ں�","623","655",dealTime,1);
		test.addDeal(deal);//���н����ȷ
		*/
		
		/*
		//2��������ƷIDɾ��һ��������Ϣ
		if(test.deleteDeal(11))
			System.out.println("ɾ���ɹ�");
		else
			System.out.println("�޷�ɾ�����ڽ��׵Ķ���");
		*/
		
		
		//3���޸�һ��������Ϣ
		Timestamp changeTime = new Timestamp(System.currentTimeMillis());
		deal = new Deal(8,"�ں�","623","655",changeTime,2);
		test.updateDeal(deal);//���н����ȷ
		/**/
		
		/*
		//4����ѯһ��������Ϣ
		deal = test.findDeal(5);
		System.out.println(deal.getCommodityName()+" "+deal.getSellId()+
				" "+deal.getDealTime()+" "+deal.getDealStatus());
		*/
		
		/**/
		//5�����ݹؼ��ֲ�ѯ��ǰҳ������Ϣ
		List<Deal> deals;
		PageDeal pageDeal = new PageDeal(2,5);
		//PageDeal pageDeal = new PageDeal(1,3,"status","1");
		deals = test.findCurPageDeal(pageDeal);
		System.out.println("�ܼ�¼��Ϊ��"+pageDeal.getTotalCount()+" ��ҳ��Ϊ��"+pageDeal.getTotalPage());
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		for(int i = 0; i < deals.size(); i++){
			deal = deals.get(i);
			System.out.println(deal.getCommodityName()+" "+deal.getSellId()+
					" "+df.format(deal.getDealTime())+" "+deal.getDealStatus());		
		}
		
	}	

}
