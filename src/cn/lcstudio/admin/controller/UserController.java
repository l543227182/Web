package cn.lcstudio.admin.controller;

import cn.itcast.common.page.Pagination;
import cn.lcstudio.bean.Roles;
import cn.lcstudio.bean.Users;
import cn.lcstudio.front.service.UsersService;
import cn.lcstudio.front.service.rolesService;
import cn.lcstudio.utils.privilegeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/admin/user")
@Controller
@privilegeValue(32)
public class UserController {
	@Autowired
	private UsersService userservice;
	
	
	@Autowired
	private rolesService rolesService;
	@RequestMapping("/UserManager")
	public String UserCenter(Integer pageNo,HttpServletRequest request){
		pageNo=pageNo==null?1:pageNo;
		Users u=(Users)request.getSession().getAttribute("user");
		Pagination p=userservice.getPageList(pageNo, u.getId());
		request.setAttribute("p", p);
		return "/admin/user/UserCenter";
	}
	
	@RequestMapping("/delUser")
	public String delUser(String[] ids,HttpServletRequest request){
		Users u=(Users) request.getSession().getAttribute("user");
		if(u.getROLEID().equals("2"))
			return "redirect:UserManager.do"; 
		
		userservice.deleteUser(ids);
		return "redirect:UserManager.do";
	}
	
	@RequestMapping("ToModifyRolesPage")
	public String ToModifyRolesPage(String id,HttpServletRequest request){
		Users u=userservice.getUserByID(id);
		List<Roles> list=rolesService.getAllRoles();
		request.setAttribute("u", u);
		request.setAttribute("role", list);
		return "/admin/user/ModifyRoles";
	}
	
	@RequestMapping("ModifyRoles")
	public String ModifyRoles(String UserID,String RoleID,HttpServletRequest request){
		Users u=new Users();
		if(RoleID.equals("2")){
			Users user=(Users) request.getSession().getAttribute("user");
			if(!user.getROLEID().equals("2")){
				return "redirect:UserManager.do";
			}
		}
		u.setId(UserID);
		u.setROLEID(RoleID);
		userservice.updateUser(u);
		return "redirect:UserManager.do";
	}
}
