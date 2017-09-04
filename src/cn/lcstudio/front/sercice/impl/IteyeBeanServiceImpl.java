package cn.lcstudio.front.sercice.impl;

import cn.itcast.common.page.Pagination;
import cn.lcstudio.bean.IteyeBean;
import cn.lcstudio.front.mapper.IteyeBeanDao;
import cn.lcstudio.front.service.IteyeBeanService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class IteyeBeanServiceImpl implements IteyeBeanService{

	@Resource
	private IteyeBeanDao beanDao;
	@Override
	public int addBean(IteyeBean bean) {
		// TODO Auto-generated method stub
	
		return 	beanDao.addBean(bean);
	}

	@Override
	public void delBean(String[] id) {
		// TODO Auto-generated method stub
		beanDao.delBeans(id);
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

	

}
