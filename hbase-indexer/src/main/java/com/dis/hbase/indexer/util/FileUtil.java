package com.dis.hbase.indexer.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;

import com.dis.hbase.indexer.lucene.CreateIndexer;

public class FileUtil {
	
	public static long fileSize(String path){
		System.out.println(path);
		File file=new File(path);
		if(file.isFile()){
			return file.length();
			
		}else if(file.isDirectory()){
			long size=0;
			if(file.list()!=null && file.list().length>0){
				for(String chFilePath : file.list()){
					size+=fileSize(path+"/"+chFilePath);
					
				}
			}
			return size;
		}
		return 0;
		
	}
	
	
	public static void main(String[] args){
		System.out.println(fileSize("E:/index_test")/(1024*1024.0) +"M");
	}
	public static int fileTotal(String path){
		return 0;
	}
	
	public static void readTxtFile(String filePath){
		try{
			String encoding="UTF-8";
			File file=new File(filePath);
			if(file.isFile() && file.exists()){
				InputStreamReader read=new InputStreamReader(new FileInputStream(file),encoding);
				BufferedReader bufferReader = new BufferedReader(read);
				String lineTxt=null;
				List<Document> docs=new ArrayList<Document>();
				
				int i=0;
				while((lineTxt=bufferReader.readLine())!=null){
					Document doc=new Document();
					doc.add(new Field("contxt", lineTxt, Field.Store.NO,Field.Index.ANALYZED));
					doc.add(new Field("path",filePath,Field.Store.YES,Field.Index.NOT_ANALYZED));
					doc.add(new Field("id",lineTxt.split("\\|")[0]+"-"+Math.random(),Field.Store.YES,Field.Index.NOT_ANALYZED));
					docs.add(doc);
					i++;
					if(i>10000){
						CreateIndexer.index(docs);
						docs.clear();
						i=0;
					}
					
				}
				read.close();
				
			}
		}catch(Exception e){
			
		}
	}
	
	

}
