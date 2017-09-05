package cn.lcstudio.admin.controller;

import cn.lcstudio.bean.Permission;
import cn.lcstudio.bean.Roles;
import cn.lcstudio.front.mapper.PermissionDao;
import cn.lcstudio.front.service.rolesService;
import cn.lcstudio.utils.ControlerUtils;
import cn.lcstudio.utils.privilegeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin/roles")
@privilegeValue(16)
public class RolesController {

	@Autowired
	private rolesService rolesService;
	
	@Autowired
	private PermissionDao dao;
	/**
	 * @Description: 列出权限类型
	 * @param @param request
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author lcD
	 * @date 2016年10月9日 上午9:49:51
	 */

	@RequestMapping("/listRoles")
	public String listAllRoles(HttpServletRequest request){
		List<Roles> roles=rolesService.getAllRoles();
		//将存储在remark的权限值去除
		for(int i=0;i<roles.size();i++){
			Roles role=roles.get(i);
			role.setREMARK(role.getREMARK().split("#")[0]);
		}	
		request.setAttribute("roles", roles);
		return "/admin/roles/RolesCenter";
	}
	
	@RequestMapping("/toAddRolesPage")
	public String toAddRolesPage(HttpServletRequest request){
		List<Permission> list=dao.getListPermission();
		request.setAttribute("p", list);
		return "/admin/roles/AddRoles";
	}
	@RequestMapping("/addRoles")
	public String addRole(String selectPermission,Roles roles,
			String[] addPermission,HttpServletRequest request){
		System.out.println(addPermission);
		roles.setID(ControlerUtils.getRandomID());
		StringBuffer bf=new StringBuffer(roles.getREMARK());
		bf.append('#');
		if(selectPermission==null){		
			roles.setPID(274877906943L);				   
		}else{
			int l=addPermission.length;
			long sum=0;
			for(int i=0;i<l;i++){
				sum+=(Long.parseLong(addPermission[i]));
				bf.append(addPermission[i]);
				bf.append('@');
			}
			roles.setPID(sum);
		 }
		roles.setREMARK(bf.toString());
		String str=bf.toString();
		String reg[]=str.split("#");
		System.out.println(reg[0]+"   "+reg[1]);
		String[] reg2=reg[1].split("@");
		System.out.println(reg2);
		rolesService.AddRoles(roles);
		return "redirect:listRoles.do";
	}
    

	@RequestMapping("/delRoles")
	public String DelRoles(Integer[] selItem){	
		int l=selItem.length;
		if(l==0)
			return "redirect:listRoles.do";
		for(int i=0;i<l;i++){
			if(selItem[i]==2){
				if(l==2){
					return "redirect:listRoles.do";
				}else{
					if(i==0){
						selItem[i]=selItem[i+1];
					}else{
						selItem[i]=selItem[i-1];
					}
				}
			}
		}		
		rolesService.delRoles(selItem);
		return "redirect:listRoles.do";
	}
	
	
	@RequestMapping("/ModifyRolesPage")
	public String ModifyRolesPage(String  rolesID,HttpServletRequest request){
		if(rolesID.trim()=="2"){
			return "redirect:listRoles.do";
		}
		Roles roles=rolesService.getRolesByID(Integer.valueOf(rolesID));
		List<Permission> list=dao.getListPermission();		
		request.setAttribute("p", list);
		if(roles.getPID()!=274877906943L){
			String str=roles.getREMARK().split("#")[1];
			String[] permissionValue=str.split("@");
			request.setAttribute("permission", permissionValue);
		}		
		roles.setREMARK(roles.getREMARK().split("#")[0]);
		request.setAttribute("role", roles);
		return "/admin/roles/ModifyRoles";
	}
	
	
	@RequestMapping("/ModifyRoles")
	public String ModifyRoles(String selectPermission,Roles roles,
			String[] addPermission,HttpServletRequest request){	
		if(roles!=null&&roles.getID()==2){
			return "redirect:listRoles.do";
		}
		StringBuffer bf=new StringBuffer(roles.getREMARK());
		bf.append('#');
		if(selectPermission==null){		
			roles.setPID(274877906943L);					
		}else{
			if(addPermission!=null){
			int l=addPermission.length;
			long sum=0;
			for(int i=0;i<l;i++){
				sum+=(Long.parseLong(addPermission[i]));
				bf.append(addPermission[i]);				
				bf.append('@');
			}
			roles.setPID(sum);
			}
		 }
		roles.setREMARK(bf.toString());		
		rolesService.modifyRoles(roles);
		return "redirect:listRoles.do";
	}
}
