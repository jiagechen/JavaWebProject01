package service;

import java.util.List;

import dao.CommodityDaoImpl;
import dao.UserDaoImpl;
import entity.PageUser;
import entity.User;

public class UserService {
	/*
	 * 1���û���¼�ж�
	 * 2���û��޸���Ϣ
	 * 3�������û�
	 * 4��ɾ���û�
	 * 5����ѯһ���û�
	 * 6����ĳ�ַ�ʽ��ѯ��ǰҳ�û�
	 * 7����ѯ�ǹ���Ա�û� 
	 */
	
	//1���û���¼�ж�
	public int loginJudge(User user){
		UserDaoImpl check = new UserDaoImpl();
		User temp = check.findById(user.getId());	
		if(temp==null)
			return 0;//�û���������
		else{
			if(user.getStatus()==0)
				return -2;//�û������
			if(!temp.getPassword().equals(user.getPassword()))
				return -1;//�û��������
		}
		return 1;//��½�ɹ�
	}
	
	//2���û��޸���Ϣ
	public void updateIfm(User user){
		UserDaoImpl change = new UserDaoImpl();
		change.updateUser(user);
	}
	
	//3�������û�
	public boolean addUser(User user){
		UserDaoImpl check = new UserDaoImpl();
		User temp = check.findById(user.getId());
		if(temp==null){
			return check.addUser(user);
		}
		return false;
	}
	
	//4��ɾ���û�
	public void deleteUser(String id){
		CommodityDaoImpl delCom = new CommodityDaoImpl();
		delCom.deleteBySellId(id);
		UserDaoImpl delUser = new UserDaoImpl();
		delUser.deleteUser(id);
	}
	
	//5����ѯһ���û�
	public User findUser(String id){
		UserDaoImpl find = new UserDaoImpl();
		return find.findById(id);
	}
	
	//6����ĳ�ַ�ʽ��ѯ��ǰҳ�û�
	public List<User> findCurPageUser(PageUser page){
		UserDaoImpl find = new UserDaoImpl();
		List<User> users;
		int totalPage,totalCount;
		PageUser temp;//��ʱҳ�棬����ͳ�����ݿ��¼����
		
		if(page.getKey()==null){			
			temp = new PageUser(1,1000000);
			users = find.findAllUser(temp);
			totalCount = users.size();		
			users = find.findAllUser(page);		
		}						
		else if(page.getKey().equals("school")){
			temp = new PageUser(1,1000000,"school",page.getIfm());
			users = find.findBySchool(temp);
			totalCount = users.size();
			users = find.findBySchool(page);			
		}
		else{
			temp = new PageUser(1,1000000,"status",page.getIfm());
			users = find.findByStatus(temp);
			totalCount = users.size();
			users = find.findByStatus(page);
		}
		totalPage = totalCount % page.getPageSize() == 0 ? 
				totalCount / page.getPageSize() : 
				totalCount / page.getPageSize() + 1;
		page.setTotalCount(totalCount);
		page.setTotalPage(totalPage);
		return users;
	}
	
	//��ѯ�ǹ���Ա�û�
	public List<User> findSomeUser(PageUser pageUser){
		UserDaoImpl find = new UserDaoImpl();
		int totalPage, totalCount;
		PageUser temp;
		temp = new PageUser(1,1000000);
		totalCount = find.findSomeUser(temp).size();
		totalPage = totalCount % pageUser.getPageSize() == 0 ? 
				totalCount / pageUser.getPageSize() : 
				totalCount / pageUser.getPageSize() + 1;
		pageUser.setTotalCount(totalCount);
		pageUser.setTotalPage(totalPage);
		return find.findSomeUser(pageUser);
	}
	
	public static void main(String[]args){
		UserService test = new UserService();
		User user;
		
		/*
        //1���û���¼�ж�
		user = new User("1234","124");
		System.out.println(test.loginJudge(user));
		*/
		
		/*
		//2���޸��û���Ϣ
		user = new User("918106850655","���ӽ�","root","�����",2,0,"ѧϰ",
				null);
		test.updateIfm(user);//���н������
		*/
		
		/*
		//3�������û�
		for(int i = 0; i < 10; i++){
			user = new User("0214","����","0","�����",2,1000,"ѧϰ",
					null);
			if(test.addUser(user))
				System.out.println("�����ɹ�");
			else
				System.out.println("�û��Ѵ��ڣ�����ʧ��");
		}	
		*/
		
		/*
		//4��ɾ���û�
		String id = "1234";
		test.deleteUser(id);
		*/
		
		/*
		//5����ѯһ���û�
		String id = "623";
		user = test.findUser(id);
		if(user!=null)
			System.out.println(user.getId()+" "+user.getName()+" "+
					user.getSchool()+" "+user.getStatus());
		else
			System.out.println("δ�ҵ����û�");	
		*/
		
		
		//6����ĳ�ַ�ʽ��ѯ��ǰҳ�û�
		PageUser page = new PageUser(2,8);//��ѯȫ���û�
		//PageUser page = new PageUser(2,8,"status","2");//���û����Ͳ�ѯ
		List<User> users = test.findCurPageUser(page);
		System.out.println("�ܼ�¼��Ϊ��"+page.getTotalCount()+
				" ��ҳ��Ϊ��"+page.getTotalPage());
		for(int i = 0; i < users.size(); i++){
			user = users.get(i);
			System.out.println(user.getId()+" "+user.getName()+" "+
					user.getSchool()+" "+user.getStatus());
		}

		/**/
	}

}
