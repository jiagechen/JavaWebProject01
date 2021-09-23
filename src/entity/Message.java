package entity;

import java.sql.Timestamp;

/**
 * Created by Windows10.0 on 2020/9/7.
 */
public class Message {
    private int messageId = -1;
    private String messageContent = null;
    private String senderId = null;
    private String receiverId = null;
    private Timestamp postTime = null;
    private int status = -1;
    private int commodityId = -1;
    private String commodityName = null;

    public Message(int messageId, String messageContent, String senderId, String receiverId, Timestamp postTime, int commodityId, int status, String commodityName) {
        this.messageId = messageId;
        this.messageContent = messageContent;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.postTime = postTime;
        this.commodityId = commodityId;
        this.status = status;
        this.commodityName = commodityName;
    }

    public Message(String messageContent, String senderId, String receiverId,
			Timestamp postTime, int status, int commodityId,
			String commodityName) {
		super();
		this.messageContent = messageContent;
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.postTime = postTime;
		this.status = status;
		this.commodityId = commodityId;
		this.commodityName = commodityName;
	}

	public Message() {
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Timestamp getPostTime() {
        return postTime;
    }

    public void setPostTime(Timestamp postTime) {
        this.postTime = postTime;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }
}
