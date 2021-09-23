package dao;

import entity.PageUser;
import entity.User;
import mybatis.Mybatis;
import org.apache.ibatis.session.SqlSession;
import java.util.List;

public class UserDaoImpl implements UserDao {
    public List<User> findSomeUser(PageUser pageUser){
        Mybatis mybatis = new Mybatis();
        SqlSession session = mybatis.getSqlSession();

        String statement = "mapper.UserMapper.findSomeUser";
        List<User> users = session.selectList(statement,pageUser);
        session.close();
        return users;
    }
	
    public User findById(String id){                                        //ͨ��ID�ҵ�һ���û���Ϣ
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement="mapper.UserMapper.findById";
        User user = session.selectOne(statement,id);
        session.close();
        return user;
    }
    public List<User> findByStatus(PageUser pageUser){                     //ͨ���û�״̬��ѯ�û���Ϣ
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement = "mapper.UserMapper.findByStatus";
        List<User> users = session.selectList(statement,pageUser);
        session.close();
        return users;
    }
    public List<User> findBySchool(PageUser pageUser){                     //ͨ���û�״̬��ѯ�û���Ϣ
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement = "mapper.UserMapper.findBySchool";
        List<User> users = session.selectList(statement,pageUser);
        session.close();
        return users;
    }
    public List<User> findAllUser(PageUser pageUser){                                    //�ҵ������û���Ϣ,״̬��������Ϊ0����ţ�1������Ա��2��ѧ��
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement = "mapper.UserMapper.findAllUser";
        List<User> users = session.selectList(statement,pageUser);
        session.close();
        return users;
    }
    public boolean addUser(User user){
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement = "mapper.UserMapper.addUser";
        int count = session.insert(statement,user);
        session.commit();
        session.close();
        if(count == 0){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean updateUser(User user){                       //����һ���û���Ϣ
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement = "mapper.UserMapper.updateUser";
        int count = session.update(statement, user);
        session.commit();
        session.close();
        if(count == 0){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean deleteUser(String id){
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement = "mapper.UserMapper.deleteUser";
        int count = session.delete(statement, id);
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
