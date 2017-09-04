package cn.lcstudio.front.mapper;

import cn.lcstudio.bean.IteyeBean;

import java.util.List;

public interface IteyeBeanDao {
	public Integer addBean(IteyeBean bean);
	public void delBeans(String[] id);
	public IteyeBean getBeanByID(String id);
	public List<IteyeBean> getListBean(IteyeBean bean);
	public int getCount();
	public List<IteyeBean> getListBeanBack(IteyeBean bean);
	public String[] getAllID();
	public List<IteyeBean> getAllBean();
}
