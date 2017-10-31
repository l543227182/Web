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
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TimeTask implements Job {

    private static Logger logger = Logger.getLogger(TimeTask.class);

    @Autowired
    private IteyeBeanService beanService;

    @Autowired
    private StaticPageService pageService;

    @Autowired
    private luceneDao dao;

    /**
     * @param
     * @return void
     * @throws
     * @Description: TODO
     * @author lc
     * @date 2016锟斤拷11锟斤拷13锟斤拷 锟斤拷锟斤拷4:49:43
     */

    public void addIteye() {
        try {
            List<IteyeBean> list = (List<IteyeBean>) Clawer.UpdateData();
            List<IteyeBean> dblist = (List<IteyeBean>) beanService.getListBean(1, false, 5).getList();
            for (int j = 0; j < dblist.size(); j++) {
                for (int i = 0; i < list.size(); i++) {
                    IteyeBean bean = list.get(i);
                    if (computeSemblance.compute(bean.getTitle().trim(), dblist.get(j).getTitle().trim()) > 0.9) {
                        list.remove(i);
                    }
                }
            }
            for (int i = 0; i < list.size(); i++) {
                IteyeBean bean = list.get(i);
                beanService.addBean(bean);
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("bean", bean);
                pageService.productIndex(map, bean.getId());
                dao.addObject(bean);
            }
            System.out.println(list.size());
            logger.info("爬取数据:" + "   " + (list.size()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearRepeatData() {
        List<IteyeBean> all = beanService.getAll();
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        List<String> repeatData = new ArrayList<>();
        for (int i = 0; i < all.size(); i++) {
            String title = all.get(i).getTitle();
            if (map.containsKey(title)) {
                repeatData.add(all.get(i).getId() + "");
                Integer integer = map.get(title);
                map.put(title, integer++);
                logger.info("finding the same Obj:" + title + "   " + integer + "    id:" + all.get(i).getId());
                continue;
            } else {
                map.put(title, 0);
            }
        }

        // 根据ID删除相应的bean
        String[] sameIds = repeatData.toArray(new String[repeatData.size()]);
        if (sameIds.length > 0)
            beanService.delBean(sameIds);
    }

    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        // TODO Auto-generated method stub
        System.out.println("execute mission start !");
    }
}
