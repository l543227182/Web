package cn.lcstudio.front.mapper;

import cn.lcstudio.bean.FileInfo;

import java.util.List;

public interface FileDao {
	public  FileInfo  getFileByID(String id);
	public List<FileInfo> getFileByUserID(FileInfo fileinfo);
	public void delFilebyUserID(String UserID);
	public void delFilebyID(String id);
	public void addFile(FileInfo fileInfo);
	public void UpdateFile(FileInfo fileInfo);
	public int getCountByUser(String UserID);
	public int getCountByAdmin();
	public int getCountBySearch(FileInfo fileinfo);
	public int getCountByOpen();
	public List<FileInfo> getFilelistById(String[] fileids);
	public List<FileInfo> SearchFiles(FileInfo fileInfo);
	public List<FileInfo> getFileByAdmin(FileInfo fileInfo);
	public List<FileInfo> getFileAtFront(FileInfo fileInfo);
}
