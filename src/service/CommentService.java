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
	 * 1������һ������
	 * 2��ɾ��һ������
	 * 3����ѯĳ��Ʒ��ȫ������
	 * 4����ѯһ������
	 * 5����ѯ��������
	 * 6���������۵�����
	 * 7����ѯ��ǰҳͶ����Ϣ
	 * 
	 * */
	
	//1������һ������
	public boolean addComment(Comment com){
		if(com.getContent().length()>300)
			return false;
		CommentDaoImpl add = new CommentDaoImpl();
		if(com.getType()==1){//����һ������
			int topicId = add.findByLastTopic(com.getCommodityId()) + 1;
			com.setTopicId(topicId);
			com.setCommentId(1);
			
			String content = com.getContent();
			SensitiveWord sensitive = new SensitiveWord();
			if(!sensitive.findWord(content))//���дʼ��
				return false;
		}
		else{//������������
			int comId = add.findByLastComment(com.getCommodityId(), 
					com.getTopicId()) + 1;
			com.setCommentId(comId);
			
			String content = com.getContent();
			SensitiveWord sensitive = new SensitiveWord();
			if(sensitive.findWord(content))//���дʼ��
				return false;
		}
		add.addComment(com);
		return true;
	}
	
	//2��ɾ��һ������
	public void deleteComment(int cmdtId, int topicId, int comId){
		CommentDaoImpl delete = new CommentDaoImpl();
		delete.deleteByCommentId(cmdtId, topicId, comId);
	}
	//3����ѯĳ��Ʒ����������
	public List<Comment> findCmdtComs(int cmdtId){
		CommentDaoImpl find = new CommentDaoImpl();
		return find.findByCommodityId(cmdtId);
	}
	
	//4����ѯһ������
	public List<Comment> findComments(int cmdtId, int topicId){
		CommentDaoImpl find = new CommentDaoImpl();
		return find.findByCmdtIdAndTpcId(cmdtId, topicId);
	}
	
	//5����ѯ��������
	public Comment findComment(int cmdtId, int topicId, int comId){
		CommentDaoImpl find = new CommentDaoImpl();
		return find.findByPriKey(cmdtId, topicId, comId);
	}
	
	//6���������۵�����
	public void addLikeNum(int cmdtId, int topicId, int comId){
		CommentDaoImpl update = new CommentDaoImpl();
        Comment com = update.findByPriKey(cmdtId, topicId, comId);
        com.setLikeNum(com.getLikeNum() + 1);
        update.updateComment(com);
	}
	//7����ѯ��ǰҳͶ����Ϣ
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
	//8������һ������
	public void updateComment(Comment comment){
		CommentDaoImpl update = new CommentDaoImpl();
		update.updateComment(comment);
	}
	
	public static void main (String[]args){
		CommentService test = new CommentService();
		Comment comment;
	    Timestamp time = new Timestamp(System.currentTimeMillis());
        
	    
		//1������һ������	
		comment = new Comment(156,"�¶�������������","623","����",time,1,0);//һ������
		//comment = new Comment(10,"�¶�������������",1,"618","��ʵ����ô��",
				//"655","ȷʵ�ܺ�",time,2,0);//��������
		System.out.print(test.addComment(comment));//���н������
		/**/
	    
	    /*
		//2��ɾ������
		//test.deleteComment(10, 1, 1);//ɾ��һ�����ۣ����¶�������Ҳһ��ɾ����
		test.deleteComment(12, 1, 3);//ɾ��һ�����ۣ����¶�������Ҳһ��ɾ����
		*/
		
		
	    //3����ѯһ������
	    List<Comment> comments = test.findComments(10, 1);
	    for(int i = 0; i < comments.size(); i++){
	    	comment = comments.get(i);
	    	System.out.println(comment.getCommodityName()+" "+comment.getContent()
	    			+" "+comment.getDeContent());
	    }
	    /**/
		
		/*
		//4����ѯһ�����ۣ���ѯ�������ۣ�
		comment = test.findComment(10, 1, 2);
		System.out.println(comment.getCommodityName()+" "+comment.getContent()
				+" "+comment.getTime());
		*/
	    
	    //5���������۵�����
	    //test.addLikeNum(9, 1, 1);
	    
	   
		
	}

}
