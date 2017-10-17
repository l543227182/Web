package cn.lcstudio.front.sercice.impl;

import cn.lcstudio.front.service.StaticPageService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.servlet.ServletContext;
import java.io.*;
import java.util.Map;


/**
 * 鐢熸垚闈欐�椤靛疄鐜扮被
 * @author lc
 *
 */
public class StaticPageServiceImpl implements StaticPageService,ServletContextAware{

	private Configuration conf;
	
	public void setFreeMarkerConfigurer(FreeMarkerConfigurer freeMarkerConfigurer) {
		this.conf = freeMarkerConfigurer.getConfiguration();
	}


	//闈欐�鍖栨柟娉�
	public void productIndex(Map<String,Object> root,Integer id){
		//String dir = "C:\Users\lx\workspace\babasport12\";
		//璁剧疆妯℃澘鐨勭洰褰�
		//conf.setDirectoryForTemplateLoading(dir);		
		//杈撳嚭娴�  浠庡唴瀛樺啓鍑哄幓  纾佺洏涓�
		Writer out = null;
		try {
			//璇昏繘鏉� UTF-8  鍐呭瓨涓�
			Template template = conf.getTemplate("ShowArticle.html");
			//鑾峰彇Html鐨勮矾寰�
			String path = getPath("/html/" + id );//278.html
			System.out.println(path);
			File f = new File(path);
			File parentFile = f.getParentFile();
			if(!parentFile.exists()){
				parentFile.mkdirs();
			}
			//杈撳嚭娴�
			out = new OutputStreamWriter(new FileOutputStream(f), "UTF-8");
			//澶勭悊妯℃澘
			template.process(root, out);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(out != null){
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	//鑾峰彇璺緞
	public String getPath(String name){
		return servletContext.getRealPath(name);
	}

	private ServletContext servletContext;

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
}
