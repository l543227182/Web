package cn.lcstudio.utils;

import cn.lcstudio.bean.FileInfo;
import cn.lcstudio.front.service.FileService;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

public class FileUtils {
	
	public static void dealFileName(List<FileInfo> list){
		for(int i=0;i<list.size();i++){
			FileInfo fileInfo=list.get(i);
			fileInfo.setFilename(fileInfo.getFilename().split("_")[0]+"."
						+FilenameUtils.getExtension(fileInfo.getFilename()));
			fileInfo.setSize(fileInfo.getSize()/1024);
		}
	}
	
	public static  void SplitFileName(FileInfo fileInfo) {
		String filename = fileInfo.getFilename();
		String name = filename.split("_")[0];
		String extend=filename.substring(filename.indexOf("."));
		fileInfo.setFilename(name+extend);
	}
	
	public static void DownloadFile(HttpServletRequest request,HttpServletResponse response,
			FileService fileService,String fileID) throws Exception{
		request.setCharacterEncoding("UTF-8");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		
		FileInfo fileInfo=fileService.getFileByID(fileID);		
		String ctxPath = fileInfo.getPath();
		String downLoadPath = ctxPath +"\\"+ fileInfo.getFilename();
		long fileLength = new File(downLoadPath).length();
		SplitFileName(fileInfo);
		response.setHeader("Content-disposition", "attachment; filename="
				+ new String(fileInfo.getFilename().getBytes("utf-8"), "ISO8859-1"));
		response.setHeader("Content-Length", String.valueOf(fileLength));

		bis = new BufferedInputStream(new FileInputStream(downLoadPath));
		bos = new BufferedOutputStream(response.getOutputStream());
		byte[] buff = new byte[2048];
		int bytesRead;
		while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
			bos.write(buff, 0, bytesRead);
		}
		bis.close();
		bos.close();
	}
}
