package cn.lcstudio.front.service;

import cn.itcast.common.page.Pagination;
import cn.lcstudio.bean.IteyeBean;

public interface IteyeBeanService {
	public int addBean(IteyeBean bean);
	public void delBean(String[] id);
	public IteyeBean getBeanByID(String id);
	public Pagination getListBean(Integer pageNo,boolean back,Integer pageSize);
	public String[] getAllID();
}
