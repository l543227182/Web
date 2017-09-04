package cn.lcstudio.front.service;

import cn.lcstudio.bean.Roles;

import java.util.List;


public interface rolesService {
	public List<Roles> getAllRoles();
	public Roles getRolesByID(Integer ID);
	public void AddRoles(Roles roles);
	public void delRoles(Integer[] selItem);
	public void modifyRoles(Roles roles);
}
