package cn.lcstudio.admin.controller;

import cn.itcast.common.page.Pagination;
import cn.lcstudio.bean.FileInfo;
import cn.lcstudio.bean.Users;
import cn.lcstudio.front.service.FileService;
import cn.lcstudio.utils.ControlerUtils;
import cn.lcstudio.utils.FileUtils;
import cn.lcstudio.utils.privilegeValue;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/admin/file")
@privilegeValue(4)
public class FileController{
	
	private static Logger logger = Logger.getLogger(FileController.class); 
	
	@Autowired
	private FileService fileService;
	/**
	 * 
	 * @Description: TODO
	 * @param @param pageNum
	 * @param @param request
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author lc
	 * @date 2016年10月26日 下午11:02:33
	 */
	@RequestMapping("/filePage")
	public String FileListPage(Integer pageNum,HttpServletRequest request){
		pageNum=pageNum==null?1:pageNum;
		
		Users user=(Users)request.getSession().getAttribute("user");	
		Pagination p=null;
		//超级管理员可以查看所有的文件
		if(!user.getROLEID().equals("2")){
			 p=fileService.getFileByUserID(pageNum, user.getId());
		}else{
			 p=fileService.getFileByAdmin(pageNum, user.getId());
		}
		FileUtils.dealFileName((List<FileInfo>) p.getList());
		if(p.getList().size()==0){
			p=null;
		}
		request.setAttribute("p", p);	
		return "admin/file/fileCenter";
	}
	
	@RequestMapping("/uploadPage")
	public String uploadPage(){
		
		return "admin/file/upLoadCenter";
	}
	   /** 
		 * @Description: TODO
		 * @param @param request
		 * @param @param response
		 * @param @return
		 * @param @throws IllegalStateException
		 * @param @throws IOException   
		 * @return String  
		 * @throws
		 * @author 
		 * @date 2016年10月23日 下午7:34:36
		 */
	
		@RequestMapping("/upLoadFiles"	)
		public String upLoadFiles(HttpServletRequest request,HttpServletResponse response,String UserId) throws IllegalStateException, IOException {
			//创建一个通用的多部分解析器
			CommonsMultipartResolver multipartResolver = 
					new CommonsMultipartResolver(request.getSession().getServletContext());
		
			//判断 request 是否有文件上传,即多部分请求
			if(multipartResolver.isMultipart(request)){
				//转换成多部分request  
				MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;				
				//取得request中的所有文件名
				//Enumeration<String> params=multiRequest.getParameterNames();
				// 保存文件信息的list
				List<FileInfo> list=new ArrayList<FileInfo>();
				
				Iterator<String> iter = multiRequest.getFileNames();
				while(iter.hasNext()){
					//记录上传过程起始时的时间，用来计算上传时间
					int pre = (int) System.currentTimeMillis();
					//取得上传文件
					String formName=iter.next();
					MultipartFile file = multiRequest.getFile(formName);
					
					if(file != null){
						//用户自己修改的名字
						String uName=request.getParameter(formName);
						//取得当前上传文件的文件名称
						String myFileName = file.getOriginalFilename();						
						//如果名称不为,说明该文件存在，否则说明该文件不存在
						if(myFileName.trim() !=""){
							System.out.println(myFileName);
							FileInfo fi=new FileInfo();
							fi.setCreateTime(new Date());
							fi.setId(ControlerUtils.CreateUUID());
							fi.setUserID(UserId);
							fi.setSize((int) file.getSize());
							//重命名上传后的文件名
							String paramOpen=request.getParameter("open_"+formName);
							//System.out.println(paramOpen);
							//如果为on表示不公开 
							if(paramOpen!=null){
								fi.setOpen(!paramOpen.equals("on"));;
							}else{
								fi.setOpen(true);
							}
							
							String path="C:\\Users\\lc\\Desktop\\demoUpload\\";
							String ext=FilenameUtils.getExtension(myFileName);
							File localFile=null;
							//修改文件名
							if(uName!=null&&!uName.trim().equals("")){
								myFileName=uName+"_"+fi.getId()+"."+ext;								
								path=ControlerUtils.makePath(myFileName, path);
								fi.setFilename(myFileName);
								fi.setPath(path);							
							}else{
								myFileName=FilenameUtils.getBaseName(myFileName)+"_"+fi.getId()+"."+ext;
								path=ControlerUtils.makePath(myFileName, path);
								fi.setFilename(myFileName);
								fi.setPath(path);								
							}
								list.add(fi);
								System.out.println("上传文件"+path);
								logger.info("上传文件:  "+path);
							//定义上传路径
							localFile= new File(path,myFileName);
							
							file.transferTo(localFile);
						}
					}
					//记录上传该文件后的时间
					int finaltime = (int) System.currentTimeMillis();
					System.out.println((finaltime - pre)/60.00);
				}
				fileService.addFile(list);
				System.out.println("添加文件完成");
			}
			return "redirect:filePage.do";
		}

		
		@RequestMapping("/delFiles")
		public String delFiles(String[] fileids,HttpServletRequest request){
			List<FileInfo> list =fileService.getFilelistById(fileids);
			for(FileInfo file:list){
				String path=file.getPath();
				String filename=file.getFilename();
				File  f=new File(path, filename);
				if(f.exists()){
					f.delete();
				}				
				fileService.delFilebyID(file.getId());
			}
			
			return "redirect:filePage.do";
		}
		
		@RequestMapping("/downloadFile")
		public  void download(HttpServletRequest request,HttpServletResponse response,String fileID) throws Exception {
			
			FileUtils.DownloadFile(request, response, fileService, fileID);
		}

	
		
		@RequestMapping("/toModifFilePage")
		public String toModifFilePage(String FileID,HttpServletRequest request){
			FileInfo  fileInfo=fileService.getFileByID(FileID);
			FileUtils.SplitFileName(fileInfo);
			fileInfo.setFilename(fileInfo.getFilename().split("\\.")[0]);
			request.setAttribute("file", fileInfo);
			return "admin/file/ModifyFile";
		}
		
		@RequestMapping("/ModifyFileInfo")
		public String ModifyFileInfo(String FileName, String open,String fileID){
			if(isNull(FileName,open,fileID)){
				FileInfo oldFile=fileService.getFileByID(fileID);
				File f=new File(oldFile.getPath(),oldFile.getFilename());
				String newFileName=FileName+"_"+fileID+"."+FilenameUtils.getExtension(oldFile.getFilename());
				if(f.exists()){
					f.renameTo(new File(oldFile.getPath(),newFileName));
				}
				FileInfo fileInfo=new FileInfo();
				fileInfo.setFilename(newFileName);
				fileInfo.setId(fileID);
				fileInfo.setOpen(open.trim().equals("1")?true:false);
				fileService.UpdateFile(fileInfo);
			}
			 return "redirect:filePage.do";
		}
		
		public boolean isNull(String... str){
			for(int i=0;i<str.length;i++){
				if(str[i]==null||str[i].trim()==""){
					return false;
				}
			}
			return true;
		}
		
		@RequestMapping("/SearchFiles")
		public String SearchFile(String keyWord,Integer pageNum,HttpServletRequest request){		
			if(isNull(keyWord)){
				pageNum=pageNum==null?1:pageNum;			
				Users user=(Users)request.getSession().getAttribute("user");
				 Pagination pagination= fileService.SearchFiles(keyWord, pageNum, user.getId());
				 FileUtils.dealFileName((List<FileInfo>) pagination.getList());
				 request.setAttribute("p", pagination);
				 return  "admin/file/fileCenter";
			}else{
				return "redirect:filePage.do";
			}
			
		}
}
