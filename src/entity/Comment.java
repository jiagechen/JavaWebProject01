package entity;

import java.sql.Timestamp;

/**
 * Created by Windows10.0 on 2020/9/7.
 */
public class Comment {
    private int commodityId = -1;
    private String commodityName = null;
    private int topicId = -1;
    private int commentId = -1;
    private String id = null;
    private String content = null;
    private String deId = null;
    private String deContent = null;
    private Timestamp time = null;
    private int type = -1;
    private int likeNum = -1;

    public Comment(int commodityId, String commodityName, int topicId, int commentId, String id, String content, String deId, String deContent, Timestamp time, int type, int likeNum) {
        this.commodityId = commodityId;
        this.commodityName = commodityName;
        this.topicId = topicId;
        this.commentId = commentId;
        this.id = id;
        this.content = content;
        this.deId = deId;
        this.deContent = deContent;
        this.time = time;
        this.type = type;
        this.likeNum = likeNum;
    }

    public Comment(int commodityId, String commodityName, String id,
			String content, Timestamp time, int type, int likeNum) {
		super();
		this.commodityId = commodityId;
		this.commodityName = commodityName;
		this.id = id;
		this.content = content;
		this.time = time;
		this.type = type;
		this.likeNum = likeNum;
	}

	public Comment(int commodityId, String commodityName, int topicId,
			String id, String content, String deId, String deContent,
			Timestamp time, int type, int likeNum) {
		super();
		this.commodityId = commodityId;
		this.commodityName = commodityName;
		this.topicId = topicId;
		this.id = id;
		this.content = content;
		this.deId = deId;
		this.deContent = deContent;
		this.time = time;
		this.type = type;
		this.likeNum = likeNum;
	}

	public Comment(int commodityId, String commodityName, String id,
			String deId, String content, Timestamp time, int type) {
		super();
		this.commodityId = commodityId;
		this.commodityName = commodityName;
		this.id = id;
		this.deId = deId;
		this.content = content;
		this.time = time;
		this.type = type;
	}

	public Comment() {
    }

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDeId() {
        return deId;
    }

    public void setDeId(String deId) {
        this.deId = deId;
    }

    public String getDeContent() {
        return deContent;
    }

    public void setDeContent(String deContent) {
        this.deContent = deContent;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }
}
