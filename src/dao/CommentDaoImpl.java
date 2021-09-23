package dao;

import entity.Comment;
import entity.PageDeal;
import mybatis.Mybatis;

import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentDaoImpl implements CommentDao{
    public List<Comment> findByCommodityId(int commodityId){
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement="mapper.CommentMapper.findByCommodityId";
        List<Comment> comments=session.selectList(statement,commodityId);
        session.close();
        return comments;
    }
    public List<Comment> findByTopicId(int topicId){
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement="mapper.CommentMapper.findByTopicId";
        List<Comment> comments=session.selectList(statement,topicId);
        session.close();
        return comments;
    }
    public List<Comment> findType3(PageDeal pageDeal){
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement="mapper.CommentMapper.findType3";
        List<Comment> comments=session.selectList(statement,pageDeal);
        session.close();
        return comments;
    }
    public List<Comment> findByCommentId(int commentId){
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement="mapper.CommentMapper.findByCommentId";
        List<Comment> comments=session.selectList(statement,commentId);
        session.close();
        return comments;
    }
    public List<Comment> findById(String id){
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement="mapper.CommentMapper.findById";
        List<Comment> comments = session.selectList(statement,id);
        session.close();
        return comments;
    }
    
    public List<Comment> findByCmdtIdAndTpcId(int cmdtId, int topicId){
    	Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();
    	String statement = "mapper.CommentMapper.findByCmdtIdAndTpcId";
        
    	Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("commodityId",cmdtId);
        map.put("topicId",topicId);
        List<Comment> comments = session.selectList(statement,map);
        session.close();
        return comments;
        
        
    }
    
    public List<Comment> findByDeId(String deId){
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement="mapper.CommentMapper.findByDeId";
        List<Comment> comments=session.selectList(statement,deId);
        session.close();
        return comments;
    }
    public List<Comment> findByType(String type){
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement="mapper.CommentMapper.findByType";
        List<Comment> comments=session.selectList(statement,type);
        session.close();
        return comments;
    }
    public List<Comment> findAllComment(){
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement="mapper.CommentMapper.findAllComment";
        List<Comment> comments=session.selectList(statement);
        session.close();
        return comments;
    }

    public Integer findByLastTopic(int commodityId){
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement="mapper.CommentMapper.findByLastTopic";
        Integer recId = session.selectOne(statement, commodityId);
        if (recId == null)
            return 0;
        session.close();
        return recId;
    }
    public Integer findByLastComment(int commodityId,int topicId){
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        Map<String, Integer> map=new HashMap<String, Integer>();
        map.put("commodityId",commodityId);
        map.put("topicId",topicId);
        String statement = "mapper.CommentMapper.findByLastComment";
        Integer recId=session.selectOne(statement, map);
        if (recId == null)
            return 0;
        session.close();
        return recId;
    }
    
    public Comment findByPriKey(int cmdtId, int topicId, int comId){
    	Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();
        Map<String, Integer> map=new HashMap<String, Integer>();
        map.put("commodityId",cmdtId);
        map.put("topicId",topicId);
        map.put("commentId",comId);
        String statement = "mapper.CommentMapper.findByPriKey";
        Comment comment = session.selectOne(statement, map);
        session.close();
        return comment;

    }

    public boolean addComment(Comment comment){
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement = "mapper.CommentMapper.addComment";
        int count = session.insert(statement,comment);
        session.commit();
        session.close();
        if(count == 0){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean updateComment(Comment comment){
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement = "mapper.CommentMapper.updateComment";
        int count = session.update(statement, comment);
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

        String statement = "mapper.CommentMapper.deleteByCommodityId";
        int count = session.delete(statement, commodityId);
        session.commit();
        session.close();
        if(count == 0){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean deleteByTopicId(int commodityId,int topicId){
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        Map<String, Integer> map=new HashMap<String, Integer>();
        map.put("commodityId",commodityId);
        map.put("topicId",topicId);
        String statement = "mapper.CommentMapper.deleteByTopicId";
        int count = session.delete(statement,map);
        session.commit();
        session.close();
        if(count == 0){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean deleteByCommentId(int commodityId,int topicId,int commentId){
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        Map<String, Integer> map=new HashMap<String, Integer>();
        map.put("commodityId",commodityId);
        map.put("topicId",topicId);
        map.put("commentId",commentId);
        String statement = "mapper.CommentMapper.deleteByCommentId";
        int count = session.delete(statement,map);
        session.commit();
        session.close();
        if(count == 0){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean deleteById(String id){
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement = "mapper.CommentMapper.deleteById";
        int count = session.delete(statement,id);
        session.commit();
        session.close();
        if(count == 0){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean deleteByDeId(String deId){
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement = "mapper.CommentMapper.deleteByDeId";
        int count = session.delete(statement,deId);
        session.commit();
        session.close();
        if(count == 0){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean deleteByType(int type){
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement = "mapper.CommentMapper.deleteByType";
        int count = session.delete(statement,type);
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
