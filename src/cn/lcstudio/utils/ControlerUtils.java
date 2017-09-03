package cn.lcstudio.utils;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.lcstudio.admin.controller.adminController;
import cn.lcstudio.bean.IteyeBean;
import cn.lcstudio.bean.Users;

public class ControlerUtils {
	private static Logger logger = Logger.getLogger(ControlerUtils.class); 
	/**
	 * 
	 * @Description: TODO
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author lc
	 * @date 2016年11月3日 下午4:22:55
	 */
	public static int getRandomID(){
		return new Random().nextInt(9999999);
	}
	/**
	 * 
	 * @Description: TODO
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author lc
	 * @date 2016年10月19日 下午10:14:49
	 */
	public final static String CreateUUID(){
		return UUID.randomUUID().toString();
	}
    public final static String MD5(String pwd) {
        //用于加密的字符
        char md5String[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F' };
        try {
            //使用平台的默认字符集将此 String 编码为 byte序列，并将结果存储到一个新的 byte数组中
            byte[] btInput = pwd.getBytes();
             
            //信息摘要是安全的单向哈希函数，它接收任意大小的数据，并输出固定长度的哈希值。
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
             
            //MessageDigest对象通过使用 update方法处理数据， 使用指定的byte数组更新摘要
            mdInst.update(btInput);
             
            // 摘要更新之后，通过调用digest（）执行哈希计算，获得密文
            byte[] md = mdInst.digest();
             
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {   //  i = 0
                byte byte0 = md[i];  //95
                str[k++] = md5String[byte0 >>> 4 & 0xf];    //    5 
                str[k++] = md5String[byte0 & 0xf];   //   F
            }
             
            //返回经过加密后的字符串
            return new String(str);
             
        } catch (Exception e) {
            return null;
        }
    }
    
	public static boolean validateUser(Users user) {
		
		if(user==null)
			return false;
		
		String username=user.getUsername();
		String password=user.getPassword();
		
		if(username==""||username==null)
			return false;
		if(password==""||password==null)
			return false;
		
		return true;
	}
	/**
	 * 
	 * @Description: TODO
	 * @param @param user
	 * @param @return   
	 * @return boolean  
	 * @throws
	 * @author lc
	 * @date 2016年10月19日 下午10:14:58
	 */
	public static boolean validateRegUser(Users user) {
		// TODO Auto-generated method stub
		if(!validateUser(user)){
			return false;
		}
		if(user.getEmail()==null||user.getEmail()=="")
			return false;
		if(user.getRealname()==null||user.getRealname()=="")
			return false;		
			return true;
	}
	
	/**
	 * 
	 * @Description: TODO
	 * @param @param filename
	 * @param @param savePath
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author lc
	 * @date 2016年10月25日 下午5:35:56
	 */
	public static  String makePath(String filename,String savePath){
		
		int hashcode = filename.hashCode();
		int dir1 = hashcode&0xf;  //0--15
		int dir2 = (hashcode&0xf0)>>4;  //0-15
		
		String dir = savePath + "\\" + dir1 + "\\" + dir2;  //upload\2\3  upload\3\5
		
		File file = new File(dir);
		if(!file.exists()){
			file.mkdirs();
		}
		return dir;
	}
	
	/**
	 * 
	 * @Description: TODO
	 * @param @return
	 * @param @throws Exception   
	 * @return List<IteyeBean>  
	 * @throws
	 * @author lc
	 * @date 2016年11月9日 上午11:23:28
	 */
	public static List<IteyeBean> ClawerUtils() throws Exception{
		String url="http://www.iteye.com/news?page=1";
		List<IteyeBean> beans=new ArrayList<IteyeBean>();
		Response response = Jsoup.connect(url).header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0").timeout(3000).execute();		
		//System.out.println(response.body());
		Document d=Jsoup.parse(response.body());
		Elements elements=d.select(".content");
		System.out.println(elements.get(0));
		logger.info(new Date());
		for(int i=0;i<elements.size();i++){
			Element e=elements.get(i);
			IteyeBean bean=new IteyeBean();
			bean.setSummary(e.select("div > div").get(0).toString());
			bean.setAuthor(e.select(".news_info").select("a").attr("title"));
			bean.setDate(e.select(".news_info").select("span[class=date]").text());
		  //bean.setNews_tag(e.select("div[class=news_tag]").toString());
			bean.setSrcUrl(e.select("h3 > a").attr("href"));
			bean.setTitle(e.select("h3 > a").attr("title"));
			String body=ConnectUrl("http://www.iteye.com"+bean.getSrcUrl());
			body=Jsoup.parse(body).select(".news").select("#news_content").toString();
			bean.setBody(body);
			beans.add(bean);
			System.out.println(":  "+i);			
		}	
	
		return beans;
	}
	private static String ConnectUrl(String url) throws IOException {
		Connection.Response response = Jsoup.connect(url).
				header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0)"
						+ " Gecko/20100101 Firefox/33.0").timeout(10000).execute();			
		String body=response.body();
		return body;
	}
}


