package dao;

import mybatis.Mybatis;
import org.apache.ibatis.session.SqlSession;

public class SensitiveWord {
    public boolean findWord(String word){
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement = "mapper.SensitiveWordMapper.findWord";
        String count = session.selectOne(statement,word);
        session.commit();
        session.close();
        if(count == null){
            return true;
        }
        else{
            return false;
        }
    }
    public static void main(String[] args){
        SensitiveWord aa=new SensitiveWord();
        String a="������";
        if(aa.findWord(a)){
            System.out.println("�������д�");
        }
        else{
            System.out.println("�����д�");
        }
    }
}
