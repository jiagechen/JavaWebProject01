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
	
    public User findById(String id){                                        //通过ID找到一条用户信息
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement="mapper.UserMapper.findById";
        User user = session.selectOne(statement,id);
        session.close();
        return user;
    }
    public List<User> findByStatus(PageUser pageUser){                     //通过用户状态查询用户信息
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement = "mapper.UserMapper.findByStatus";
        List<User> users = session.selectList(statement,pageUser);
        session.close();
        return users;
    }
    public List<User> findBySchool(PageUser pageUser){                     //通过用户状态查询用户信息
        Mybatis mybatis = new Mybatis();
        SqlSession session =  mybatis.getSqlSession();

        String statement = "mapper.UserMapper.findBySchool";
        List<User> users = session.selectList(statement,pageUser);
        session.close();
        return users;
    }
    public List<User> findAllUser(PageUser pageUser){                                    //找到所有用户信息,状态从上往下为0：封号，1：管理员，2：学生
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
    public boolean updateUser(User user){                       //更新一条用户信息
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
