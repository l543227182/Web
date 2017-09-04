package cn.lcstudio.front.sercice.impl;

import cn.itcast.common.page.Pagination;
import cn.lcstudio.bean.FileInfo;
import cn.lcstudio.front.mapper.FileDao;
import cn.lcstudio.front.service.FileService;
import cn.lcstudio.utils.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
@Transactional
@Service
public class FileServiceImpl  implements FileService{

	@Resource
	private FileDao  fileDao;
	
	@Override
	public FileInfo getFileByID(String id) {
		// TODO Auto-generated method stub
		return fileDao.getFileByID(id);
	}

	@Override
	public Pagination getFileByUserID(Integer pageNum,String UserID) {
		// TODO Auto-generated method stub
		int fileRecord=fileDao.getCountByUser(UserID);
		FileInfo  fileInfo=new FileInfo();
		fileInfo.setUserID(UserID);
		fileInfo.setPageNo(pageNum);
		fileInfo.setRecords(fileRecord);
		fileInfo.setPageSize(8);
		Pagination p=new Pagination();		
		p.setPageNo(pageNum);
		p.setPageSize(8);
		p.setTotalCount(fileRecord);
		if(p.getTotalPage()<pageNum){
			fileInfo.setPageNo(1);
		}
		List<FileInfo> list=fileDao.getFileByUserID(fileInfo);		
		p.setList(list);		
		return p;
	}
	@Override
	public void addFile(List<FileInfo> list) {
		// TODO Auto-generated method stub
		for(FileInfo file:list)
			fileDao.addFile(file);
	}

	@Override
	public void UpdateFile(FileInfo fileInfo) {
		// TODO Auto-generated method stub
		fileDao.UpdateFile(fileInfo);
	}

	@Override
	public List<FileInfo> getFilelistById(String[] fileids) {
		// TODO Auto-generated method stub
		return fileDao.getFilelistById(fileids);
	}

	@Override
	public void delFilebyUserID(String UserID) {
		// TODO Auto-generated method stub
		fileDao.delFilebyUserID(UserID);
	}

	@Override
	public void delFilebyID(String id) {
		// TODO Auto-generated method stub
		fileDao.delFilebyID(id);
	}
	
	@Override
	public Pagination SearchFiles(String KeyWords, Integer pageNum, String UserID) {
		// TODO Auto-generated method stub
		
		FileInfo fileInfo=new FileInfo();
		fileInfo.setUserID(UserID);
		fileInfo.setKeyWords(KeyWords);
		int count=fileDao.getCountBySearch(fileInfo);
		fileInfo.setPageNo(pageNum);
		fileInfo.setPageSize(8);
		fileInfo.setRecords(count);
		List<FileInfo> list=fileDao.SearchFiles(fileInfo);
		Pagination pagination=new Pagination(pageNum, 8, list.size());
		pagination.setList(list);
		pagination.setTotalCount(count);
		return pagination;
	}

	@Override
	public Pagination getFileByAdmin(Integer pageNum, String UserID) {
		int fileRecord=fileDao.getCountByAdmin();
		FileInfo  fileInfo=new FileInfo();
		fileInfo.setUserID(UserID);
		fileInfo.setPageNo(pageNum);
		fileInfo.setRecords(fileRecord);
		fileInfo.setPageSize(8);
		Pagination p=new Pagination();		
		p.setPageNo(pageNum);
		p.setPageSize(8);
		p.setTotalCount(fileRecord);
		if(p.getTotalPage()<pageNum){
			fileInfo.setPageNo(1);
		}
		List<FileInfo> list=fileDao.getFileByAdmin(fileInfo);
		p.setList(list);		
		return p;
	}

	@Override
	public Pagination getFileAtFront(Integer pageNum) {
		// TODO Auto-generated method stub
		
		int fileRecord=fileDao.getCountByOpen();
		FileInfo  fileInfo=new FileInfo();		
		fileInfo.setPageNo(pageNum);
		fileInfo.setRecords(fileRecord);
		fileInfo.setPageSize(8);
		
		Pagination p=new Pagination();		
		p.setPageNo(pageNum);
		p.setPageSize(8);
		p.setTotalCount(fileRecord);
		if(p.getTotalPage()<pageNum){
			fileInfo.setPageNo(1);
		}
		List<FileInfo> list=fileDao.getFileAtFront(fileInfo);
		FileUtils.dealFileName(list);
		p.setList(list);		
		return p;
	}

	

}
