package dao;

import entity.Message;
import entity.PageDeal;
import mybatis.Mybatis;

import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageDaoImpl implements MessageDao{
    public Message findByMessageId(int messageId){
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement="mapper.MessageMapper.findByMessageId";
        Message message = session.selectOne(statement,messageId);
        session.close();
        return message;
    }
    
    public List<Map<String,String>> findUMessage(PageDeal pageDeal){
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement="mapper.MessageMapper.findUMessage";
        List<Message>messages = session.selectList(statement,pageDeal.getIfm());

        statement="mapper.MessageMapper.findByReceiverId";
        List<Message> messages1=session.selectList(statement,pageDeal.getIfm());
        session.close();

        List<Map<String,String>> senders = new ArrayList<Map<String,String>>();
        for(int i=0;i<messages.size();i++){
            String temp=messages.get(i).getSenderId();
            boolean f=true;
            for (int j=0;j<senders.size();j++){
                if(senders.get(j).get("sender").equals(temp)) {
                    int count=Integer.parseInt(senders.get(j).get("count"));
                    count++;
                    senders.get(j).put("count",Integer.toString(count));
                    f=false;
                }
            }
            if(f){
                Map<String,String> map = new HashMap<String,String>();
                map.put("sender",temp);
                int count=1;
                map.put("count",Integer.toString(count));
                senders.add(map);
            }
        }

        for(int i=0;i<senders.size()-1;i++){
            for(int j=i+1;j<senders.size();j++){
                int count1=Integer.parseInt(senders.get(i).get("count"));
                int count2=Integer.parseInt(senders.get(j).get("count"));
                if(count1<count2){
                    Collections.swap(senders,i,j);
                }
            }
        }

        for (int i=0;i<messages1.size();i++){
            String temp=messages1.get(i).getSenderId();
            boolean f=true;
            for (int j=0;j<senders.size();j++){
                if(senders.get(j).get("sender").equals(temp)) {
                    f=false;
                }
            }
            if (f){
                Map<String,String> map = new HashMap<String,String>();
                map.put("sender",temp);
                int count=0;
                map.put("count",Integer.toString(count));
                senders.add(map);
            }
        }

        pageDeal.setTotalCount(senders.size());
        int count=senders.size()/pageDeal.getPageSize();
        if(senders.size()%pageDeal.getPageSize()!=0){
            count++;
        }
        pageDeal.setTotalPage(count);

        List<Map<String,String>> newsenders = new ArrayList<Map<String,String>>();
        int number1=(pageDeal.getCurPage()-1)*pageDeal.getPageSize();
        int number2=pageDeal.getCurPage()*pageDeal.getPageSize()-1;
        if(number2>=senders.size()){
            number2=senders.size()-1;
        }
        for(int i=number1;i<=number2;i++){
            Map<String,String> map = senders.get(i);
            newsenders.add(map);
        }

        return newsenders;
    }
    public List<Message> findBySenderId(String senderId){
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement="mapper.MessageMapper.findBySenderId";
        List<Message>messages = session.selectList(statement, senderId);
        session.close();
        return messages;
    }
    public List<Message> findByReceiverId(String receiverId){
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement="mapper.MessageMapper.findByReceiverId";
        List<Message>messages = session.selectList(statement, receiverId);
        session.close();
        return messages;
    }
    public List<Message> findBySenderAndReceiver(String senderId,String receiverId){
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        Map map=new HashMap();
        map.put("senderId",senderId);
        map.put("receiverId",receiverId);
        String statement="mapper.MessageMapper.findBySenderAndReceiver";
        List<Message>messages = session.selectList(statement, map);
        session.close();
        return messages;
    }
    public List<Message> findByStatus(int status){
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement="mapper.MessageMapper.findByStatus";
        List<Message>messages = session.selectList(statement, status);
        session.close();
        return messages;
    }
    public List<Message> findByCommodityId(int commodityId){
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement="mapper.MessageMapper.findByCommodityId";
        List<Message>messages = session.selectList(statement, commodityId);
        session.close();
        return messages;
    }
    public List<Message> findAllMessage(){
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement="mapper.MessageMapper.findAllMessage";
        List<Message>messages = session.selectList(statement);
        session.close();
        return messages;
    }
    public boolean addMessage(Message message){
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement = "mapper.MessageMapper.addMessage";
        int count = session.insert(statement,message);
        session.commit();
        session.close();
        if(count == 0){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean updateMessage(Message message){
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement = "mapper.MessageMapper.updateMessage";
        int count = session.update(statement, message);
        session.commit();
        session.close();
        if(count == 0){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean deleteByMessageId(int messageId){
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement = "mapper.MessageMapper.deleteByMessageId";
        int count = session.delete(statement, messageId);
        session.commit();
        session.close();
        if(count == 0){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean deleteBySenderId(String senderId){
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement = "mapper.MessageMapper.deleteBySenderId";
        int count = session.delete(statement,senderId);
        session.commit();
        session.close();
        if(count == 0){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean deleteByReceiverId(String receiverId){
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement = "mapper.MessageMapper.deleteByReceiverId";
        int count = session.delete(statement,receiverId);
        session.commit();
        session.close();
        if(count == 0){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean deleteByStatus(int status){
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement = "mapper.MessageMapper.deleteByStatus";
        int count = session.delete(statement,status);
        session.commit();
        session.close();
        if(count == 0){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean deleteByCommodityId(int commodityId){
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement = "mapper.MessageMapper.deleteByCommodityId";
        int count = session.delete(statement,commodityId);
        session.commit();
        session.close();
        if(count == 0){
            return false;
        }
        else{
            return true;
        }
    }
}
