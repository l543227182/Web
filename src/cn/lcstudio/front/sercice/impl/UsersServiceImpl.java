package cn.lcstudio.front.sercice.impl;

import cn.itcast.common.page.Pagination;
import cn.lcstudio.bean.Users;
import cn.lcstudio.front.mapper.UsersDao;
import cn.lcstudio.front.service.FileService;
import cn.lcstudio.front.service.ImageService;
import cn.lcstudio.front.service.UsersService;
import cn.lcstudio.utils.ControlerUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class UsersServiceImpl implements UsersService{

	@Resource
	private UsersDao usersDao;
	
	@Resource
	private ImageService imageService;
	
	@Resource
	private FileService fileService;
	
	
	@Override
	public void deleteUser(String[] ids) {
		// TODO Auto-generated method stub
	
		usersDao.deleteUser(ids);
	}

	@Override
	public Users getUser(String LOGIN_NAME) {
		// TODO Auto-generated method stub
		return usersDao.getUser(LOGIN_NAME);
	}

	@Override
	public Users getUserByID(String ID) {
		// TODO Auto-generated method stub
		return usersDao.getUserByID(ID);
	}

	@Override
	@Transactional
	public void addUser(Users user) {
		// TODO Auto-generated method stub
		
		usersDao.addUser(user);
	}

	@Override
	public void updateUser(Users user) {
		// TODO Auto-generated method stub
		
		if(user.getPassword()!=null){
			int length=user.getPassword().length();
			if(length<6||length>16)return ;
			user.setPassword(ControlerUtils.MD5(user.getPassword()));}
		usersDao.updateUser(user);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return usersDao.getCount();
	}

	@Override
	public Pagination getPageList(Integer pageNo,String UserID) {
		int userCount=usersDao.getCount();
		Users user=new Users();
		
		user.setRecords(userCount);
		user.setPageNo(pageNo);
		user.setPageSize(8);
		Pagination  p=new Pagination(pageNo, 8, userCount);
		List<Users> list=usersDao.getPageList(user);
		p.setList(list);
		return p;
	}

	@Override
	public Users loginUser(Users user) {
		// TODO Auto-generated method stub
		return usersDao.loginUser(user);
	}


}
