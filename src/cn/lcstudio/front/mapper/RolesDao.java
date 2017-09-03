package cn.lcstudio.front.mapper;

import java.util.List;

import cn.lcstudio.bean.Roles;
public interface RolesDao {

	public List<Roles> getAllRoles();
	public Roles getRolesByID(Integer ID);
	public void AddRoles(Roles roles);
	public void modifyRoles(Roles roles);
	public void delRoles(Integer[] selItem);	
}
