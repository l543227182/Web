package cn.lcstudio.front.sercice.impl;

import cn.itcast.common.page.Pagination;
import cn.lcstudio.bean.IteyeBean;
import cn.lcstudio.front.mapper.IteyeBeanDao;
import cn.lcstudio.front.service.IteyeBeanService;
import cn.lcstudio.lucene.Dao.luceneDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

@Service
public class IteyeBeanServiceImpl implements IteyeBeanService{

    private static Logger logger = Logger.getLogger(IteyeBeanServiceImpl.class);

    @Autowired
	private luceneDao luceneDao;
	@Resource
	private IteyeBeanDao beanDao;

	@Override
	public int addBean(IteyeBean bean) {
		// TODO Auto-generated method stub
	
		return 	beanDao.addBean(bean);
	}

	@Override
	public void delBean(String[] ids) {
		// TODO Auto-generated method stub
		beanDao.delBeans(ids);
        luceneDao.delObject(ids);

        //删除相应的html
		for(int i=0;i<ids.length;i++){
		    File file =new File("/html/" + ids[i]);
		    if(file.exists()){
                logger.info("delete duplicate IteyeBean ,its id is" + ids[i]);
		        file.delete();
            }
        }
	}

	@Override
	public IteyeBean getBeanByID(String id) {
		// TODO Auto-generated method stub
		return beanDao.getBeanByID(id);
	}

	@Override
	public Pagination getListBean(Integer pageNo,boolean back,Integer pageSize) {
		// TODO Auto-generated method stub
		int count=beanDao.getCount();
		IteyeBean bean=new IteyeBean();		
		bean.setPageNo(pageNo);
		bean.setRecords(count);
		bean.setPageSize(pageSize);
		Pagination p=new Pagination(pageNo,pageSize, count);
		if(back){
		 p.setList(beanDao.getListBeanBack(bean));
		}
		else{		 
		 p.setList(beanDao.getListBean(bean));
		}
		return p;
	}

	@Override
	public String[] getAllID() {
		// TODO Auto-generated method stub
		return beanDao.getAllID();
	}

	@Override
	public List<IteyeBean> getAll() {
		return beanDao.getAllBean();
	}
}
