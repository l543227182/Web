package cn.lcstudio.test;

import cn.lcstudio.bean.IteyeBean;
import cn.lcstudio.bean.UserArticle;
import cn.lcstudio.front.mapper.IteyeBeanDao;
import cn.lcstudio.front.mapper.UserArticleDao;
import cn.lcstudio.front.service.IteyeBeanService;
import cn.lcstudio.front.service.UsersService;
import cn.lcstudio.lucene.Dao.luceneDao;
import cn.lcstudio.utils.Clawer;
import cn.lcstudio.utils.computeSemblance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:application-context.xml"})
public class Test_Dao {
	
	@Autowired
	private UsersService  usersService;
	@Test
	public void TestUser() throws IOException, SQLException {
		IteyeBean it = (IteyeBean) beanService.getListBean(1, false, 5).getList().get(0);
        List<IteyeBean> list = Clawer.UpdateData();
        int len=list.size();
        int i ;
        for(i=0;i<len;i++){
            IteyeBean bean=list.get(i);
            if(computeSemblance.compute(bean.getTitle().trim(),it.getTitle().trim()) > 0.9 ){
                len=i+1;
                list.subList(0, i);
                break;
            }
        }
        System.out.println(i);
    }
	
	@Autowired 
	private IteyeBeanService beanService;
	
	@Autowired
	private IteyeBeanDao beanDao;


	@Test
	public void delIteye (){
		List<IteyeBean> all = beanService.getAll();
		HashMap<String,Integer> map=new HashMap<String,Integer>();
		List<String> repeatData = new ArrayList<>();
		for(int i=0;i<all.size();i++){
			String title = all.get(i).getTitle();
			if (map.containsKey(title)) {
				repeatData.add(all.get(i).getId()+"");
                Integer integer = map.get(title);
                map.put(title, integer++);
                System.out.println(title + "   " +integer+ "    id:" + all.get(i).getId());
                continue;
			} else {
				map.put(title,0);
			}
		}

		// 根据ID删除相应的bean
		String[] str = repeatData.toArray(new String[repeatData.size()]);
	//	beanService.delBean(str);
	}
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
