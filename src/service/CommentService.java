package service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import dao.CommentDaoImpl;
import dao.SensitiveWord;
import entity.Comment;
import entity.PageCommodity;
import entity.PageDeal;

public class CommentService {
	/*
	 * 1、发布一条评论
	 * 2、删除一级评论
	 * 3、查询某商品的全部评论
	 * 4、查询一级评论
	 * 5、查询二级评论
	 * 6、更新评论点赞数
	 * 7、查询当前页投诉信息
	 * 
	 * */
	
	//1、发布一条评论
	public boolean addComment(Comment com){
		if(com.getContent().length()>300)
			return false;
		CommentDaoImpl add = new CommentDaoImpl();
		if(com.getType()==1){//发布一级评论
			int topicId = add.findByLastTopic(com.getCommodityId()) + 1;
			com.setTopicId(topicId);
			com.setCommentId(1);
			
			String content = com.getContent();
			SensitiveWord sensitive = new SensitiveWord();
			if(!sensitive.findWord(content))//敏感词检测
				return false;
		}
		else{//发布二级评论
			int comId = add.findByLastComment(com.getCommodityId(), 
					com.getTopicId()) + 1;
			com.setCommentId(comId);
			
			String content = com.getContent();
			SensitiveWord sensitive = new SensitiveWord();
			if(sensitive.findWord(content))//敏感词检测
				return false;
		}
		add.addComment(com);
		return true;
	}
	
	//2、删除一条评论
	public void deleteComment(int cmdtId, int topicId, int comId){
		CommentDaoImpl delete = new CommentDaoImpl();
		delete.deleteByCommentId(cmdtId, topicId, comId);
	}
	//3、查询某商品的所有评论
	public List<Comment> findCmdtComs(int cmdtId){
		CommentDaoImpl find = new CommentDaoImpl();
		return find.findByCommodityId(cmdtId);
	}
	
	//4、查询一级评论
	public List<Comment> findComments(int cmdtId, int topicId){
		CommentDaoImpl find = new CommentDaoImpl();
		return find.findByCmdtIdAndTpcId(cmdtId, topicId);
	}
	
	//5、查询二级评论
	public Comment findComment(int cmdtId, int topicId, int comId){
		CommentDaoImpl find = new CommentDaoImpl();
		return find.findByPriKey(cmdtId, topicId, comId);
	}
	
	//6、增加评论点赞数
	public void addLikeNum(int cmdtId, int topicId, int comId){
		CommentDaoImpl update = new CommentDaoImpl();
        Comment com = update.findByPriKey(cmdtId, topicId, comId);
        com.setLikeNum(com.getLikeNum() + 1);
        update.updateComment(com);
	}
	//7、查询当前页投诉信息
	public List<Comment> findType3(PageDeal pageDeal){
		CommentDaoImpl find = new CommentDaoImpl();
		PageDeal temp = new PageDeal(1,1000000);
		List<Comment> comments = find.findType3(temp);
		
		int totalCount = comments.size();		
		int totalPage = totalCount % pageDeal.getPageSize() == 0 ? 
				totalCount / pageDeal.getPageSize() : 
				totalCount / pageDeal.getPageSize() + 1;
		pageDeal.setTotalCount(totalCount);
		pageDeal.setTotalPage(totalPage);
		comments = find.findType3(pageDeal);
		return comments;
	}
	//8、更新一条评论
	public void updateComment(Comment comment){
		CommentDaoImpl update = new CommentDaoImpl();
		update.updateComment(comment);
	}
	
	public static void main (String[]args){
		CommentService test = new CommentService();
		Comment comment;
	    Timestamp time = new Timestamp(System.currentTimeMillis());
        
	    
		//1、发布一条评论	
		comment = new Comment(156,"新东方六级单词书","623","作弊",time,1,0);//一级评论
		//comment = new Comment(10,"新东方六级单词书",1,"618","其实不怎么样",
				//"655","确实很好",time,2,0);//二级评论
		System.out.print(test.addComment(comment));//运行结果正常
		/**/
	    
	    /*
		//2、删除评论
		//test.deleteComment(10, 1, 1);//删除一级评论（其下二级评论也一并删除）
		test.deleteComment(12, 1, 3);//删除一级评论（其下二级评论也一并删除）
		*/
		
		
	    //3、查询一级评论
	    List<Comment> comments = test.findComments(10, 1);
	    for(int i = 0; i < comments.size(); i++){
	    	comment = comments.get(i);
	    	System.out.println(comment.getCommodityName()+" "+comment.getContent()
	    			+" "+comment.getDeContent());
	    }
	    /**/
		
		/*
		//4、查询一条评论（查询二级评论）
		comment = test.findComment(10, 1, 2);
		System.out.println(comment.getCommodityName()+" "+comment.getContent()
				+" "+comment.getTime());
		*/
	    
	    //5、更新评论点赞数
	    //test.addLikeNum(9, 1, 1);
	    
	   
		
	}

}
