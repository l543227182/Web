package cn.lcstudio.lucene.utils;

import cn.lcstudio.bean.IteyeBean;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.IOException;


public class luceneUtils {

	
	private static Directory directory=null;
	private static IndexWriterConfig  indexWriterConfig=null;
	private static Version version=null;
	private static Analyzer analyzer=null;
	static
	{
		try {
			
			directory=FSDirectory.open(new File(Contants.INDEXURL));
			version=Version.LUCENE_44;
			analyzer=new StandardAnalyzer(version);
			indexWriterConfig=new IndexWriterConfig(version, analyzer);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static Version getVersion() {
		return version;
	}

	public static Analyzer getAnalyzer() {
		return analyzer;
	}
	
	public static IndexWriter getIndexWriter() throws IOException{
		IndexWriter indexWriter=new IndexWriter(directory, indexWriterConfig);
		return indexWriter;
	}
	
	public static IndexSearcher getIndexSearcher() throws Exception{
		IndexReader indexReader=DirectoryReader.open(directory);
		IndexSearcher indexSearcher=new IndexSearcher(indexReader);
		return indexSearcher;
	}
	
	public static Document toDocument(IteyeBean bean){
		Document document=new Document();
		TextField field=new TextField("summary", bean.getSummary(), Store.YES);		
		StringField sf=new StringField("author", bean.getAuthor(), Store.YES);
		TextField field2=new TextField("title", bean.getTitle(),Store.YES);
		IntField intField=new IntField("id", bean.getId(), Store.YES);
		document.add(field);
		document.add(intField);
		document.add(sf);
		document.add(field2);	
		return document;
	}

	public static IteyeBean DocToIteyeBean(Document doc){
		IteyeBean bean=new IteyeBean();
		bean.setSummary(doc.get("summary"));
		bean.setAuthor(doc.get("author"));
		bean.setTitle(doc.get("title"));
		bean.setId(Integer.valueOf(doc.get("id")));
		return bean;
	}
	
	public static QueryParser getMultiFieldQueryParser(String[] strings) {
		// TODO Auto-generated method stub
		return new MultiFieldQueryParser(luceneUtils.getVersion(),strings,luceneUtils.getAnalyzer());
	}

	public static Highlighter getHighlighter(Query query) {
		Formatter formatter = new SimpleHTMLFormatter("<span class='keyword'>", "</span>");
		Scorer scorer = new QueryScorer(query);
		Highlighter highlighter = new Highlighter(formatter, scorer);

		Fragmenter fragmenter = new SimpleFragmenter(150);
		highlighter.setTextFragmenter(fragmenter);

		return highlighter;
	}

	public static void highlight(Document doc, String fieldName, Highlighter highlighter) {
		try {
			String ht = highlighter.getBestFragment(analyzer, fieldName, doc.get(fieldName));
			if (ht == null) {
				int end = Math.min(doc.get(fieldName).length(), 200);
				ht = doc.get(fieldName).substring(0, end);
			}
			doc.removeField(fieldName);
			doc.add(new TextField(fieldName, ht, Store.YES));
			//System.out.println(ht);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
}
