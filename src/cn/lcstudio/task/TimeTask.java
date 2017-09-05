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
			IteyeBean it=(IteyeBean) beanService.getListBean(1, false, 2).getList().get(0);
			int len=list.size();
			for(int i=0;i<len;i++){
				IteyeBean bean=list.get(i);				
				if(computeSemblance.compute(bean.getTitle().trim(),it.getTitle().trim()) < 0.9 ){
					len=i+1;
					list.subList(0, i);
					break;
				}
				 beanService.addBean(bean);
				 
				 Map<String, Object> map=new HashMap<String, Object>();
				 map.put("bean", bean);
				 pageService.productIndex(map,bean.getId());
				 dao.addObject(bean);
				 				
			}
			System.out.println(list.size());
			logger.info("娣诲姞鏁版嵁涓暟:"+"   "+(len));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		System.out.println("execute mission start !");
	}
}
