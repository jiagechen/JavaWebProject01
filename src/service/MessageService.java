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
	 * 1������һ��˽�Ż��ۺ���Ϣ
	 * 2��ɾ��һ��˽�Ż��ۺ���Ϣ
	 * 3����ѯ˽�Ż��ۺ���Ϣ
	 * 4����δ����������չʾ��ǰҳ˽��
	 * 5������δ��˽��Ϊ�Ѷ�
     */
	
	//1������һ��˽��
	public boolean addNews(Message news){
		if(news.getMessageContent().length()>300)
			return false;
		MessageDaoImpl add = new MessageDaoImpl();
		add.addMessage(news);
		return true;
	}
	
	//2��ɾ��һ��˽��
	public void deleteNews(int id){
		MessageDaoImpl delete = new MessageDaoImpl();
		delete.deleteByMessageId(id);
	}
	
	//3����ѯ˽��
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
			if(j>=msgs2.size()){//msgs2��ȫ���鲢���
				msg1 = msgs1.get(i);
				msgs.add(msg1);
				i++;
			}
			else if(i>=msgs1.size()){//msgs1��ȫ���鲢���
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
	
	//4��˽�Ź���
	public List<Map<String,String>> findUMessage(PageDeal pageDeal){
		MessageDaoImpl find = new MessageDaoImpl();
		return find.findUMessage(pageDeal);
	}
	
	//5������δ��˽��Ϊ�Ѷ�
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
		//1������һ��˽�Ż��ۺ���Ϣ
		Timestamp postTime = new Timestamp(System.currentTimeMillis());
		msg = new Message("�õĺõ�","618","1234",postTime,1,4,"̨��");
		if(test.addNews(msg))
			System.out.println("�����ɹ�");
		else 
			System.out.println("����ʧ��");
		*/
		
		/*
		//2��ɾ��һ��˽�Ż��ۺ���Ϣ
		test.deleteNews(23);
		*/
		
		/*
		//3����ѯ˽�Ż��ۺ���Ϣ
		String sendId = "623", recId = "655";
		List<Message> msgs = test.findNews(sendId, recId);
		for(int i = 0; i < msgs.size(); i++){
			msg = msgs.get(i);
			if(msg.getSenderId().equals(sendId))
			    System.out.println("                    "+msg.getSenderId()+
			    		"˵��"+msg.getMessageContent());
			else
			    System.out.println(msg.getSenderId()+"˵��"+msg.getMessageContent());

		}
		*/
		
		//4����δ����������չʾ��ǰҳ˽��
		PageDeal page = new PageDeal(1,5,null,"618");
		List<Map<String,String>> msgs = test.findUMessage(page);
		for(int i = 0; i < msgs.size(); i++){
			String senderId=msgs.get(i).get("sender");
			int count=Integer.parseInt(msgs.get(i).get("count"));
			System.out.println(senderId+" "+count);
			}
		//System.out.print(page.getTotalPage());	
		
		//5������δ��˽��Ϊ�Ѷ�
		test.updateMessage("0214", "623");
	}
}
