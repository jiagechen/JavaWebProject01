package service;

import java.util.List;

import dao.CommodityDaoImpl;
import dao.UserDaoImpl;
import entity.PageUser;
import entity.User;

public class UserService {
	/*
	 * 1、用户登录判断
	 * 2、用户修改信息
	 * 3、创建用户
	 * 4、删除用户
	 * 5、查询一个用户
	 * 6、按某种方式查询当前页用户
	 * 7、查询非管理员用户 
	 */
	
	//1、用户登录判断
	public int loginJudge(User user){
		UserDaoImpl check = new UserDaoImpl();
		User temp = check.findById(user.getId());	
		if(temp==null)
			return 0;//用户名不存在
		else{
			if(user.getStatus()==0)
				return -2;//用户被封号
			if(!temp.getPassword().equals(user.getPassword()))
				return -1;//用户密码错误
		}
		return 1;//登陆成功
	}
	
	//2、用户修改信息
	public void updateIfm(User user){
		UserDaoImpl change = new UserDaoImpl();
		change.updateUser(user);
	}
	
	//3、创建用户
	public boolean addUser(User user){
		UserDaoImpl check = new UserDaoImpl();
		User temp = check.findById(user.getId());
		if(temp==null){
			return check.addUser(user);
		}
		return false;
	}
	
	//4、删除用户
	public void deleteUser(String id){
		CommodityDaoImpl delCom = new CommodityDaoImpl();
		delCom.deleteBySellId(id);
		UserDaoImpl delUser = new UserDaoImpl();
		delUser.deleteUser(id);
	}
	
	//5、查询一个用户
	public User findUser(String id){
		UserDaoImpl find = new UserDaoImpl();
		return find.findById(id);
	}
	
	//6、按某种方式查询当前页用户
	public List<User> findCurPageUser(PageUser page){
		UserDaoImpl find = new UserDaoImpl();
		List<User> users;
		int totalPage,totalCount;
		PageUser temp;//临时页面，用来统计数据库记录总数
		
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
	
	//查询非管理员用户
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
        //1、用户登录判断
		user = new User("1234","124");
		System.out.println(test.loginJudge(user));
		*/
		
		/*
		//2、修改用户信息
		user = new User("918106850655","朱子剑","root","计算机",2,0,"学习",
				null);
		test.updateIfm(user);//运行结果正常
		*/
		
		/*
		//3、创建用户
		for(int i = 0; i < 10; i++){
			user = new User("0214","辰辰","0","计算机",2,1000,"学习",
					null);
			if(test.addUser(user))
				System.out.println("创建成功");
			else
				System.out.println("用户已存在，创建失败");
		}	
		*/
		
		/*
		//4、删除用户
		String id = "1234";
		test.deleteUser(id);
		*/
		
		/*
		//5、查询一个用户
		String id = "623";
		user = test.findUser(id);
		if(user!=null)
			System.out.println(user.getId()+" "+user.getName()+" "+
					user.getSchool()+" "+user.getStatus());
		else
			System.out.println("未找到该用户");	
		*/
		
		
		//6、按某种方式查询当前页用户
		PageUser page = new PageUser(2,8);//查询全部用户
		//PageUser page = new PageUser(2,8,"status","2");//按用户类型查询
		List<User> users = test.findCurPageUser(page);
		System.out.println("总记录数为："+page.getTotalCount()+
				" 总页数为："+page.getTotalPage());
		for(int i = 0; i < users.size(); i++){
			user = users.get(i);
			System.out.println(user.getId()+" "+user.getName()+" "+
					user.getSchool()+" "+user.getStatus());
		}

		/**/
	}

}
