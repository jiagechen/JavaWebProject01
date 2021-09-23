package dao;

import entity.Message;
import entity.PageDeal;

import java.util.List;
import java.util.Map;

public interface MessageDao {
    public Message findByMessageId(int messageId);
    public List<Map<String,String>> findUMessage(PageDeal pageDeal);
    public List<Message> findBySenderId(String senderId);
    public List<Message> findByReceiverId(String receiverId);
    public List<Message> findBySenderAndReceiver(String senderId,String receiverId);
    public List<Message> findByStatus(int status);
    public List<Message> findByCommodityId(int commodityId);
    public List<Message> findAllMessage();
    public boolean addMessage(Message message);
    public boolean updateMessage(Message message);
    public boolean deleteByMessageId(int messageId);
    public boolean deleteBySenderId(String senderId);
    public boolean deleteByReceiverId(String receiverId);
    public boolean deleteByStatus(int status);
    public boolean deleteByCommodityId(int commodityId);
}
