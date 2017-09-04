package cn.lcstudio.front.mapper;

import cn.lcstudio.bean.Users;

import java.util.List;

public interface UsersDao {
	public void deleteUser(String[] ids);
	public Users getUser(String LOGIN_NAME);
	public Users getUserByID(String ID);
	public void  addUser(Users user);
	public void updateUser(Users user);
	public int getCount();
	public List<Users> getPageList(Users a);
	public Users loginUser(Users user);
	
}
