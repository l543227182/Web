package cn.lcstudio.admin.controller;

import cn.itcast.common.page.Pagination;
import cn.lcstudio.bean.IteyeBean;
import cn.lcstudio.front.mapper.IteyeBeanDao;
import cn.lcstudio.front.service.IteyeBeanService;
import cn.lcstudio.front.service.StaticPageService;
import cn.lcstudio.lucene.Dao.luceneDao;
import cn.lcstudio.task.TimeTask;
import cn.lcstudio.utils.privilegeValue;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/data")
@privilegeValue(64)
public class DataController {

    private static Logger logger = Logger.getLogger(DataController.class);

	@Autowired
	private StaticPageService pageService;
	
	@Autowired
	private IteyeBeanService beanService;

	@Autowired
	private TimeTask timeTask;

	@Autowired
	luceneDao luceneDao;

	@Autowired
	private IteyeBeanDao beanDao;

	@RequestMapping("/rePostData")
	public String rePostData() {
	    try {
            String[] ids=beanDao.getAllID();
            int len = ids.length;
            luceneDao.luceneClear();
            for(int i=0;i<len;i++){
                IteyeBean bean=beanService.getBeanByID(ids[i]);
                if(bean!=null){
                    luceneDao.addObject(bean);
                    Map<String, Object> map=new HashMap<String, Object>();
                    map.put("bean", bean);
                    pageService.productIndex(map, bean.getId());
                }
            }
        }catch (Exception e){
            logger.info("重新发布失败 原因：" + e.getMessage());
        }

		return "redirect:DataCenter.do";
	}
	@RequestMapping("/DataCenter")
	public String DataCenter(Integer pageNo,HttpServletRequest request){
		pageNo=pageNo==null?1:pageNo;
		Pagination p=beanService.getListBean(pageNo,true,8);
		request.setAttribute("p", p);
		return "/admin/data/DataCenter";
	}
	
	@RequestMapping("delData")
	public String delData(String[] ids,String pageNo){
		beanService.delBean(ids);		
		return "redirect:DataCenter.do?pageNo="+pageNo;
	}
	@RequestMapping(value="removeDuplicateBean",method = RequestMethod.POST)
	public String RemoveBean(String pageNo){
		try {
			timeTask.clearRepeatData();
		}catch (Exception e){
			e.printStackTrace();
		}
		return "redirect:DataCenter.do?pageNo="+pageNo;
	}
}
