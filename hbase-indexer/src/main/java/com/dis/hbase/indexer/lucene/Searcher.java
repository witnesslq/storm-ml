package com.dis.hbase.indexer.lucene;

import java.io.File;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class Searcher {

	public static String search(String key, int n) {
		Directory directory = null;
		IndexReader reader = null;
		IndexSearcher searchear = null;
		StringBuilder result=new StringBuilder();
		Analyzer a = new StandardAnalyzer();
		try {
			directory = FSDirectory.open(new File("E:/index_test").toPath());
			reader = DirectoryReader.open(directory);
			searchear = new IndexSearcher(reader);
			QueryParser parser = new QueryParser("ip", a);
			Query query = parser.parse(key);
			//TopDocs topDocs = searchear.search(query, n);
			TopDocs topDocs = searchear.search(query, n);
			
			//System.out.println("总共匹配多少个：" + topDocs.totalHits);
			result.append("总共匹配多少个：" + topDocs.totalHits+"\n");
			ScoreDoc[] hits = topDocs.scoreDocs;
			// 应该与topDocs.totalHits相同
			System.out.println("多少条数据：" + hits.length);
			for (ScoreDoc scoreDoc : hits) {
				
				//System.out.println("匹配得分：" + scoreDoc.score);
				result.append("匹配得分：" + scoreDoc.score+"\n");
				//System.out.println("文档索引ID：" + scoreDoc.doc);
				result.append("文档索引ID：" + scoreDoc.doc+"\n");
				Document document = searchear.doc(scoreDoc.doc);
				System.out.println(document.get("rowKey"));
				System.out.println(document.get("wtime"));
				//System.out.println(document.get("path"));
				result.append(document.get("path")+"\n");
				result.append(document.get("id")+"\n");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (reader != null) {
					reader.close();
				}
			} catch (Exception e) {

			} finally {
				try {
					if (directory != null) {
						directory.close();
					}
				} catch (Exception e) {

				}
			}
		}
		
		return result.toString();
		
		
	}

	
	public static String search(String key,int index,int pageSize){
		Directory directory = null;
		IndexReader reader = null;
		IndexSearcher searchear = null;
		StringBuilder result=new StringBuilder();
		Analyzer a = new StandardAnalyzer();
		try {
			directory = FSDirectory.open(new File("E:/index_test").toPath());
			reader = DirectoryReader.open(directory);
			searchear = new IndexSearcher(reader);
			QueryParser parser = new QueryParser("ip", a);
			Query query = parser.parse(key);
			//TopDocs topDocs = searchear.search(query, n);
			TopDocs topDocs = searchear.search(query, (index)*pageSize);
			result.append("总共匹配多少个：" + topDocs.totalHits+"\n");
			ScoreDoc[] hits = topDocs.scoreDocs;
			int begin=pageSize*(index-1);
			int end = Math.min(begin+pageSize,hits.length );
			//System.out.println("总共匹配多少个：" + topDocs.totalHits);
			
			// 应该与topDocs.totalHits相同
			System.out.println("多少条数据：" + hits.length);
			for (int i=begin;i<end;i++) {
				ScoreDoc scoreDoc = hits[i];
				
				//System.out.println("匹配得分：" + scoreDoc.score);
				result.append("匹配得分：" + scoreDoc.score+"\n");
				//System.out.println("文档索引ID：" + scoreDoc.doc);
				result.append("文档索引ID：" + scoreDoc.doc+"\n");
				Document document = searchear.doc(scoreDoc.doc);
				System.out.println(document.get("rowKey"));
				System.out.println(document.get("wtime"));
				//System.out.println(document.get("path"));
				result.append(document.get("path")+"\n");
				result.append(document.get("id")+"\n");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (reader != null) {
					reader.close();
				}
			} catch (Exception e) {

			} finally {
				try {
					if (directory != null) {
						directory.close();
					}
				} catch (Exception e) {

				}
			}
		}
		
		return result.toString();
	}
	public static void main(String[] args) {
		//search("localhost", 50);
		search("localhost",100000,50);

	}

	
	
}
