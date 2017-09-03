package cn.lcstudio.front.service;

import java.util.List;

import cn.lcstudio.bean.Roles;


public interface rolesService {
	public List<Roles> getAllRoles();
	public Roles getRolesByID(Integer ID);
	public void AddRoles(Roles roles);
	public void delRoles(Integer[] selItem);
	public void modifyRoles(Roles roles);
}
