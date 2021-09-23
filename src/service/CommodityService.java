package service;

import java.util.List;

import dao.CommodityDaoImpl;
import dao.SensitiveWord;
import entity.Commodity;
import entity.PageCommodity;

public class CommodityService {
	/*
	 * 1������һ����Ʒ 
	 * 2����ID��ѯһ����Ʒ
	 * 3������IDɾ��һ����Ʒ
	 * 4������ID�޸���Ʒ��Ϣ
	 * 5�����ݹؼ��ֲ�ѯ��ǰҳ��Ʒ��Ϣ
	 * 6����ѯ���һ����Ʒ��ID
	 * 
	 */

	//1������һ����Ʒ
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
	
	//2����ID��ѯһ����Ʒ
	public Commodity findCommodity(int id){
		CommodityDaoImpl cmdt = new CommodityDaoImpl();
		return cmdt.findByCommodityId(id);
	}
	
	//3������IDɾ��һ����Ʒ
	public boolean deleteCommodity(int id){
		CommodityDaoImpl temp = new CommodityDaoImpl();
		Commodity cmdt = temp.findByCommodityId(id);
		//��Ʒ״̬ 0��ʾ׼������1��ʾ������ˣ�2��ʾ���ڽ��ף�3��ʾ���ڳ��ۣ�4��ʾ������ɣ�
		//        5��ʾ���δͨ��
		if(cmdt==null||cmdt.getCommodityStatus()==2)
			return false;
		temp.deleteByCommodityId(id);
		return true;
	}
	
	//4������ID�޸���Ʒ��Ϣ
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
	
	//5�����ݹؼ��ֲ�ѯ��ǰҳ��Ʒ��Ϣ
	public List<Commodity> findCurPageCmdt(PageCommodity page){
		List<Commodity> cmdts;//���շ��ؽ��
		CommodityDaoImpl find = new CommodityDaoImpl();
		int totalCount,totalPage;
		PageCommodity temp;
		if(page.getKey()==null){//��ѯȫ����Ʒ
			temp = new PageCommodity(1,1000000);
			cmdts = find.findAllCommodity(temp);
			totalCount = cmdts.size();		
			cmdts = find.findAllCommodity(page);
		}
		else if(page.getKey().equals("name")){//����Ʒ����ѯ
			page.setIfm("%"+page.getIfm()+"%");
			temp = new PageCommodity(1,1000000,"name",page.getIfm());
			cmdts = find.findByCommodityName(temp);
			totalCount = cmdts.size();
			cmdts = find.findByCommodityName(page);
		}
		else if(page.getKey().equals("sellId")){//��������ID��ѯ
			temp = new PageCommodity(1,1000000,"sellId",page.getIfm(),page.getIfm1());
			cmdts = find.findBySellId(temp);
			totalCount = cmdts.size();		
			cmdts = find.findBySellId(page);
		}
		else if(page.getKey().equals("type")){//����Ʒ���Ͳ�ѯ
			temp = new PageCommodity(1,1000000,"type",page.getIfm());
			cmdts = find.findByCommodityType(temp);
			totalCount = cmdts.size();		
			cmdts = find.findByCommodityType(page);
		}
		else if(page.getKey().equals("status")){//����Ʒ״̬��ѯ
			temp = new PageCommodity(1,1000000,"status",page.getIfm());
			cmdts = find.findByCommodityStatus(temp);
			totalCount = cmdts.size();		
			cmdts = find.findByCommodityStatus(page);
		}
		else {//����Ʒ�۸������ѯ
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
	
	//6����ѯ���һ����Ʒ��ID
	public  Integer findNewCommodityId(){
		CommodityDaoImpl cmdt=new CommodityDaoImpl();
		return cmdt.findNewCommodityId();
	}
	
	//7����ѯĳ���û������г�����Ʒ
	public List<Commodity> findCurPageMyCmdt(PageCommodity page){
		List<Commodity> cmdts;//���շ��ؽ��
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
		
	    //1������һ����Ʒ
		String type[] = {"ѧϰ��Ʒ","������Ʒ","���Ӳ�Ʒ","�˶�����"};
		for(int i = 1; i < 50; i++){
			cmdt = new Commodity("test"+i,type[i%4],4,99.9,"655","�ܺõ���Ʒ",null);
			
			System.out.println(test.addCommodity(cmdt));//���н����ȷ
		}
		    
		/**/
		
		/*
    	//2����ID��ѯһ����Ʒ
		cmdt = test.findCommodity(19);
		System.out.println(cmdt.getCommodityName()+" "+cmdt.getCommodityType()+
				" "+cmdt.getDescription()+" "+cmdt.getPrice());	
		*/
		
		/*
		//3������IDɾ��һ����Ʒ
		if(test.deleteCommodity(19))
			System.out.println("ɾ���ɹ�");
		else
			System.out.println("ɾ��ʧ��");
        */
		
		/*
		//4������ID�޸���Ʒ��Ϣ
		cmdt = new Commodity(5,"С��·����","���Ӳ�Ʒ",4,56,"618","���������һ��,�ò���",null);
        if(test.updateCommodity(cmdt))
		    System.out.println("�޸ĳɹ�");
        else
		    System.out.println("�޸�ʧ��");
		*/

		/*
		//5�����ݹؼ��ֲ�ѯ��ǰҳ��Ʒ��Ϣ
		List<Commodity> cmdts;
		//PageCommodity pageCmdt = new PageCommodity(2,5);//��ѯȫ����Ʒ
		PageCommodity pageCmdt = new PageCommodity(2,40,"name","��Ʒ");//��������ID��ѯ
		//PageCommodity pageCmdt = new PageCommodity(2,5,"sellId","623");//��������ID��ѯ
		//PageCommodity pageCmdt = new PageCommodity(1,5,"price","20","50");//���۸��ѯ
		cmdts = test.findCurPageCmdt(pageCmdt);
		System.out.println("�ܼ�¼��Ϊ��"+pageCmdt.getTotalCount()+" ��ҳ��Ϊ��"+pageCmdt.getTotalPage());
		for(int i = 0; i < cmdts.size(); i++){
			cmdt = cmdts.get(i);
			System.out.println(cmdt.getCommodityName()+" "+cmdt.getCommodityType()+
					" "+cmdt.getDescription()+" "+cmdt.getPrice());		
		}
		*/

	}

}
