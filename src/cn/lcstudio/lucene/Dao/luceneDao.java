package cn.lcstudio.lucene.Dao;

import cn.lcstudio.bean.IteyeBean;
import cn.lcstudio.lucene.utils.luceneUtils;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Highlighter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class luceneDao {

	public void addObject(IteyeBean bean) throws IOException{
		IndexWriter iw=luceneUtils.getIndexWriter();
		iw.addDocument(luceneUtils.toDocument(bean));
		iw.close();
	}

	public void delObject(String[] ids){
        try {
            IndexWriter indexWriter = luceneUtils.getIndexWriter();
            for(int i=0;i<ids.length;i++){
                Term term =new Term("id" ,ids[i]);
                indexWriter.deleteDocuments(term);
            }
            indexWriter.commit();
            indexWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	public List<IteyeBean> SearchIteyeBean(String keyWord,int start ,int rows) {
		try {
			//System.out.println("SearchIteyeBean!!");
			IndexSearcher indexSearcher = luceneUtils.getIndexSearcher();
			QueryParser queryParser = luceneUtils.getMultiFieldQueryParser(new String[] { "title", "summary" });
			Query query = queryParser.parse(keyWord);
			
			TopDocs topDocs  = indexSearcher.search(query, null, 100);
			Highlighter highlighter = luceneUtils.getHighlighter(query);
			
			List<IteyeBean> resultList = new ArrayList<IteyeBean>();
			int endResult=Math.min(topDocs.totalHits,start+rows);
			for(int i = 0; i < endResult; i++){
				ScoreDoc scoreDoc = topDocs.scoreDocs[i];
				int docSn = scoreDoc.doc;
				Document doc = indexSearcher.doc(docSn);				
				// 锟斤拷锟斤拷
				luceneUtils.highlight(doc, "title", highlighter);
				luceneUtils.highlight(doc, "summary", highlighter);
				
				IteyeBean ib = luceneUtils.DocToIteyeBean(doc);
				
				resultList.add(ib);
			}
			return resultList;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
		
	}
	
}
