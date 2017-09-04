package cn.lcstudio.test;

import cn.lcstudio.bean.IteyeBean;
import cn.lcstudio.bean.UserArticle;
import cn.lcstudio.bean.Users;
import cn.lcstudio.front.mapper.IteyeBeanDao;
import cn.lcstudio.front.mapper.UserArticleDao;
import cn.lcstudio.front.service.IteyeBeanService;
import cn.lcstudio.front.service.UsersService;
import cn.lcstudio.lucene.Dao.luceneDao;
import cn.lcstudio.utils.Clawer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:application-context.xml"})
public class Test_Dao {
	
	@Autowired
	private UsersService  usersService;
	@Test
	public void TestUser(){
		Users user=new Users();			
		user.setPassword("123456");
		user.setUsername("1");	
		
		//usersService.addUser(user);
		Users uu=usersService.loginUser(user);
		System.out.println(uu);
		// usersService.updateUser(user);
		//System.out.println(u);
	}
	
	@Autowired 
	private IteyeBeanService beanService;
	
	@Autowired
	private IteyeBeanDao beanDao;
	
	

	
	public void addIteye(){
		try {
			
			List<IteyeBean> list=(List<IteyeBean>) Clawer.UpdateData();	
			IteyeBean it=(IteyeBean) beanService.getListBean(1, false, 2).getList().get(0);
			int len=list.size();
			for(int i=0;i<len;i++){
				IteyeBean bean=list.get(i);					
				if(bean.getTitle().trim().equals(it.getTitle().trim())){
					System.out.println("找到相同的标题:"+bean.getTitle());
					break;
				}
				 System.out.println(bean.getTitle());
				 Integer id=beanService.addBean(bean);
				 System.out.println(id);
			}
			System.out.println(list.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	@Test
	public void addAllIteyeBean(){
		List<IteyeBean> beans=beanDao.getAllBean();
		luceneDao dao=new luceneDao();
		int len=beans.size();
		for(int i=0;i<len;i++){
		try {
			dao.addObject(beans.get(i));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	} 
	
	@Autowired
	private UserArticleDao articleDao;
	@Test
	public void Test(){
		UserArticle ua = articleDao.getUA();
		System.out.println(ua.getList().size());
	}
}
