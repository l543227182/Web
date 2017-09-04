package cn.lcstudio.lucene.Dao;

import cn.lcstudio.bean.IteyeBean;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;



public class LuceneTest {
	luceneDao dao=new luceneDao();
	@Test
	public void testAdd(){
		

		try {
			FileInputStream file=new FileInputStream(new File("C:\\Users\\lc\\Desktop\\freemaker","iteyeBeans"+1+".src"));
			ObjectInputStream ois;
			ois = new ObjectInputStream(file);
			List<IteyeBean> list=(List<IteyeBean>) ois.readObject();			
			for(int i=0;i<list.size();i++){
				dao.addObject(list.get(i));
				//System.out.println(list.get(i).getTitle());
			}
			ois.close();
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void searchIteyeBean(){
		try {			
			List<IteyeBean> list=dao.SearchIteyeBean("Mark！Android最佳的开源库集锦", 0, 10);
			System.out.println(list.get(0).getTitle()+"\n"+list.get(0).getSummary());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
