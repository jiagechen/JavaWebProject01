package service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import dao.MessageDaoImpl;
import entity.Message;
import entity.PageDeal;

public class MessageService {
	/*
	 * 1、发布一条私信或售后信息
	 * 2、删除一条私信或售后信息
	 * 3、查询私信或售后信息
	 * 4、按未读条数降序展示当前页私信
	 * 5、更新未读私信为已读
     */
	
	//1、发布一条私信
	public boolean addNews(Message news){
		if(news.getMessageContent().length()>300)
			return false;
		MessageDaoImpl add = new MessageDaoImpl();
		add.addMessage(news);
		return true;
	}
	
	//2、删除一条私信
	public void deleteNews(int id){
		MessageDaoImpl delete = new MessageDaoImpl();
		delete.deleteByMessageId(id);
	}
	
	//3、查询私信
	public List<Message> findNews(String sendId, String recId){
		MessageDaoImpl find = new MessageDaoImpl();
		List<Message> msgs, msgs1, msgs2;
		msgs = new ArrayList<Message>();
		
		msgs1 = find.findBySenderAndReceiver(sendId, recId);
		msgs2 = find.findBySenderAndReceiver(recId, sendId);
		
		int i = 0, j = 0;
		while(true){
			if(i>=msgs1.size()&&j>=msgs2.size())
				break;
			Message msg1, msg2;
			if(j>=msgs2.size()){//msgs2以全部归并完毕
				msg1 = msgs1.get(i);
				msgs.add(msg1);
				i++;
			}
			else if(i>=msgs1.size()){//msgs1以全部归并完毕
				msg2 = msgs2.get(j);
				msgs.add(msg2);
				j++;
			}
			else{
				msg1 = msgs1.get(i);
				msg2 = msgs2.get(j);
				if(msg1.getMessageId()<msg2.getMessageId()){
					msgs.add(msg1);
					i++;
				}
				else{	
					msgs.add(msg2);
					j++;
				}		
			}
		}
		return msgs;
	}
	
	//4、私信管理
	public List<Map<String,String>> findUMessage(PageDeal pageDeal){
		MessageDaoImpl find = new MessageDaoImpl();
		return find.findUMessage(pageDeal);
	}
	
	//5、更新未读私信为已读
	public void updateMessage(String senderId, String receiverId){
		MessageDaoImpl update = new MessageDaoImpl();
		List<Message> messages = update.findBySenderAndReceiver(senderId, receiverId);
		for(int i = 0; i < messages.size(); i++){
			Message message = messages.get(i);
			if(message.getStatus() == 1){
				message.setStatus(0);
				update.updateMessage(message);
			}			
		}
	}
	
	public static void main(String[]args){
		MessageService test = new MessageService();
		Message msg;
		
		/*
		//1、发布一条私信或售后信息
		Timestamp postTime = new Timestamp(System.currentTimeMillis());
		msg = new Message("好的好的","618","1234",postTime,1,4,"台灯");
		if(test.addNews(msg))
			System.out.println("发布成功");
		else 
			System.out.println("发布失败");
		*/
		
		/*
		//2、删除一条私信或售后信息
		test.deleteNews(23);
		*/
		
		/*
		//3、查询私信或售后信息
		String sendId = "623", recId = "655";
		List<Message> msgs = test.findNews(sendId, recId);
		for(int i = 0; i < msgs.size(); i++){
			msg = msgs.get(i);
			if(msg.getSenderId().equals(sendId))
			    System.out.println("                    "+msg.getSenderId()+
			    		"说："+msg.getMessageContent());
			else
			    System.out.println(msg.getSenderId()+"说："+msg.getMessageContent());

		}
		*/
		
		//4、按未读条数降序展示当前页私信
		PageDeal page = new PageDeal(1,5,null,"618");
		List<Map<String,String>> msgs = test.findUMessage(page);
		for(int i = 0; i < msgs.size(); i++){
			String senderId=msgs.get(i).get("sender");
			int count=Integer.parseInt(msgs.get(i).get("count"));
			System.out.println(senderId+" "+count);
			}
		//System.out.print(page.getTotalPage());	
		
		//5、更新未读私信为已读
		test.updateMessage("0214", "623");
	}
}
