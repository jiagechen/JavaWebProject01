package dao;

import entity.Comment;
import entity.PageDeal;

import java.util.List;

public interface CommentDao {
    public List<Comment> findByCommodityId(int commodityId);
    public List<Comment> findByTopicId(int topicId);
    public List<Comment> findByCommentId(int commentId);
    public List<Comment> findById(String id);
    public List<Comment> findType3(PageDeal pageDeal);
    public List<Comment> findByDeId(String deId);
    public List<Comment> findByType(String type);
    public List<Comment> findAllComment();
    public boolean addComment(Comment comment);
    public boolean updateComment(Comment comment);
    public boolean deleteByCommodityId(int commodityId);
    public boolean deleteByTopicId(int commodityId,int topicId);
    public boolean deleteByCommentId(int commodityId,int topicId,int commentId);
    public Integer findByLastTopic(int commodityId);
    public Integer findByLastComment(int commodityId,int topicId);
    public Comment findByPriKey(int cmdtId, int topicId, int comId);

    
    public boolean deleteById(String id);
    public boolean deleteByDeId(String deId);
    public boolean deleteByType(int type);
}
