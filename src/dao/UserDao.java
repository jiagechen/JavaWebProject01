package dao;

import entity.PageUser;
import entity.User;
import java.util.List;

public interface UserDao {
	public List<User> findSomeUser(PageUser pageUser);
    public User findById(String id);
    public List<User> findByStatus(PageUser pageUser);
    public List<User> findBySchool(PageUser pageUser);
    public List<User> findAllUser(PageUser pageUser);
    public boolean addUser(User user);
    public boolean updateUser(User user);
    public boolean deleteUser(String id);
    
}
