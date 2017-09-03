package cn.lcstudio.front.sercice.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.lcstudio.bean.Roles;
import cn.lcstudio.front.mapper.RolesDao;
import cn.lcstudio.front.service.rolesService;

@Service
@Transactional
public class RolesServiceImpl implements rolesService {

	@Resource
	private RolesDao rolesDao;
	@Override
	public List<Roles> getAllRoles() {
		// TODO Auto-generated method stub
		return rolesDao.getAllRoles();
	}
	@Override
	public Roles getRolesByID(Integer ID) {
		// TODO Auto-generated method stub
		return rolesDao.getRolesByID(ID);
	}
	@Override
	public void AddRoles(Roles roles) {
		// TODO Auto-generated method stub
		 rolesDao.AddRoles(roles);
	}
	@Override
	public void delRoles(Integer[] selItem) {
		// TODO Auto-generated method stub
		rolesDao.delRoles(selItem);
	}
	@Override
	public void modifyRoles(Roles roles) {
		// TODO Auto-generated method stub
		rolesDao.modifyRoles(roles);
	}

}
