package cn.lcstudio.task;


import cn.lcstudio.bean.IteyeBean;
import cn.lcstudio.front.service.IteyeBeanService;
import cn.lcstudio.front.service.StaticPageService;
import cn.lcstudio.lucene.Dao.luceneDao;
import cn.lcstudio.utils.Clawer;
import cn.lcstudio.utils.computeSemblance;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class TimeTask  implements Job{

	private static Logger logger = Logger.getLogger(TimeTask.class); 
	
	@Autowired 
	private IteyeBeanService beanService;
	
	@Autowired
	private StaticPageService pageService;

	@Autowired
	private luceneDao dao;
	/**
	 * 
	 * @Description: TODO
	 * @param    
	 * @return void  
	 * @throws
	 * @author lc
	 * @date 2016锟斤拷11锟斤拷13锟斤拷 锟斤拷锟斤拷4:49:43
	 */

	public void addIteye(){
		try {			
			List<IteyeBean> list=(List<IteyeBean>) Clawer.UpdateData();
			List<IteyeBean> dblist = (List<IteyeBean>) beanService.getListBean(1, false, 5).getList();
			for(int j=0;j<dblist.size();j++) {
				for (int i = 0; i < list.size(); i++) {
					IteyeBean bean = list.get(i);
					if (computeSemblance.compute(bean.getTitle().trim(), dblist.get(j).getTitle().trim()) > 0.9) {
						list.remove(i);
					}
				}
			}
			for(int i=0;i<list.size();i++){
				IteyeBean bean = list.get(i);
				beanService.addBean(bean);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("bean", bean);
				pageService.productIndex(map, bean.getId());
				dao.addObject(bean);
			}
			System.out.println(list.size());
			logger.info("爬取数据:"+"   "+(list.size()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		System.out.println("execute mission start !");
	}
}
