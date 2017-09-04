package cn.lcstudio.front.service;

import cn.itcast.common.page.Pagination;
import cn.lcstudio.bean.FileInfo;

import java.util.List;

public interface FileService {
	public  FileInfo  getFileByID(String id);
	public Pagination getFileByUserID(Integer  pageNum,String UserID);
	public void delFilebyUserID(String UserID);
	public void delFilebyID(String id);
	public void addFile(List<FileInfo> list);
	public void UpdateFile(FileInfo fileInfo);
	public List<FileInfo> getFilelistById(String[] fileids);
	public Pagination SearchFiles(String KeyWords ,Integer pageNum,String UserID);	
	public Pagination getFileByAdmin(Integer  pageNum,String UserID);
	public Pagination getFileAtFront(Integer  pageNum);
}
