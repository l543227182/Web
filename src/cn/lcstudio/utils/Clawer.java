package cn.lcstudio.utils;


import cn.lcstudio.bean.IteyeBean;
import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Clawer {
	
	public static void main(String[] args) throws Exception {
		for(int j=3;j<=3;j++){
		String url="http://www.iteye.com/news?page="+j;
		List<IteyeBean> beans=new ArrayList<IteyeBean>();
		Response response = Jsoup.connect(url).header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0").timeout(3000).execute();		
		//System.out.println(response.body());
		Document d=Jsoup.parse(response.body());
		Elements elements=d.select(".content");
		System.out.println(elements.get(0));
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
			System.out.println(j+"  :  "+i);
		}
		FileOutputStream file=new FileOutputStream(new File("C:\\Users\\lc\\Desktop\\freemaker","iteyeBeans"+j+".src"));
		ObjectOutputStream oob=new ObjectOutputStream(file);
		oob.writeObject(beans);
		oob.close();
		file.close();
		}
	}	
		
	public static List<IteyeBean> CompareList(List<IteyeBean> sqlData,List<IteyeBean> newData){
		return null;
	}
	public static List<IteyeBean> UpdateData() throws IOException, SQLException{
		
			String url="http://www.iteye.com/news?page="+1;
			List<IteyeBean> beans=new ArrayList<IteyeBean>();
			Response response = Jsoup.connect(url).header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0").timeout(3000).execute();		
			//System.out.println(response.body());
			Document d=Jsoup.parse(response.body());
			Elements elements=d.select(".content");
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
				System.out.println("dataSize:"+beans.size());	
			}
			
			return beans;
			
	}
	private static String ConnectUrl(String url) throws IOException {
		Connection.Response response = Jsoup.connect(url).
				header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0)"
						+ " Gecko/20100101 Firefox/33.0")
				.timeout(10000).execute();			
		String body=response.body();
		return body;
	}
	
	
	
}
