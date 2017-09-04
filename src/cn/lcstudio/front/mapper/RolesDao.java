package cn.lcstudio.front.mapper;

import cn.lcstudio.bean.Roles;

import java.util.List;
public interface RolesDao {

	public List<Roles> getAllRoles();
	public Roles getRolesByID(Integer ID);
	public void AddRoles(Roles roles);
	public void modifyRoles(Roles roles);
	public void delRoles(Integer[] selItem);	
}
