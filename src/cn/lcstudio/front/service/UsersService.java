package cn.lcstudio.front.service;

import java.util.List;

import cn.itcast.common.page.Pagination;
import cn.lcstudio.bean.Users;



public interface UsersService {
	public void deleteUser(String[] ids);
	public Users getUser(String LOGIN_NAME);
	public Users getUserByID(String ID);
	public void  addUser(Users user);
	public void updateUser(Users user);
	public int getCount();
	public Pagination getPageList(Integer pageNo,String UserID);
	public Users loginUser(Users user);

}
