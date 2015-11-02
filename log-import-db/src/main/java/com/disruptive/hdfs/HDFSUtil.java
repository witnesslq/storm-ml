package com.disruptive.hdfs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

public class HDFSUtil {

	static Configuration conf = new Configuration();
	static FileSystem hdfs;
	static {
		String path = "/etc/hadoop/";
		conf.addResource(new Path(path + "core-site.xml"));
		conf.addResource(new Path(path + "hdfs-site.xml"));
		conf.addResource(new Path(path + "mapred-site.xml"));
		try {
			hdfs = FileSystem.get(conf);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * create a direction
	 * 
	 * @param dir
	 * @throws IOException
	 */
	public void createDir(String dir) throws IOException {

		Path path = new Path(dir);
		hdfs.mkdirs(path);
		System.out.println("new dir \t" + conf.get("fs.default.name") + dir);
	}

	/**
	 * copy from local file to HDFS file
	 * 
	 * @param localSrc
	 * @param hdfsDst
	 * @throws Exception
	 */
	public void copyFile(String localSrc, String hdfsDst) throws Exception {
		Path src = new Path(localSrc);
		Path dst = new Path(hdfsDst);
		hdfs.copyFromLocalFile(src, dst);
		// list all the files in thre current direction
		FileStatus files[] = hdfs.listStatus(dst);
		System.out.println("Upload to \t" + conf.get("fs.default.name")
				+ hdfsDst);
		for (FileStatus file : files) {
			System.out.println(file.getPath());
		}

	}

	/**
	 * create a new file
	 * 
	 * @param fileName
	 * @param fileContent
	 * @throws Exception
	 */
	public void createFile(String fileName, String fileContent)
			throws Exception {
		Path dst = new Path(fileName);
		byte[] bytes = fileContent.getBytes();
		FSDataOutputStream output = hdfs.create(dst);
		output.write(bytes);
		System.out.println("new file \t" + conf.get("fs.default.name")
				+ fileName);
	}

	/**
	 * list all files
	 * 
	 * @param dirName
	 * @throws IOException
	 */
	public void listFiles(String dirName) throws IOException {
		Path f = new Path(dirName);
		FileStatus[] status = hdfs.listStatus(f);
		System.out.println(dirName + " has all files:");
		for (int i = 0; i < status.length; i++) {
			System.out.println(status[i].getPath().toString());
		}
	}

	/**
	 * judge a file existed?and  delete it
	 * @param fileName
	 * @throws Exception
	 */
	public void deleteFile(String fileName) throws Exception {
		Path f = new Path(fileName);
		boolean isExists = hdfs.exists(f);
		if (isExists) { // if exists, delete
			boolean isDel = hdfs.delete(f, true);
			System.out.println(fileName + "  delete? \t" + isDel);
		} else {
			System.out.println(fileName + "  exist? \t" + isExists);
		}
	}
	
	/**
	 *  在core-default.xml中
	 *  使用的CheckSumFileSystem 不是使用 DistributedFileSystem
	 *  
	 *  <property>
	 *    <name>fs.hdfs.impl</name>
	 *    <value>org.apache.hadoop.hdfs.DistributedFileSystem</value>
	 *    <description> The fileSystem for hdfs:uris.</description>
	 *  </property>
	 *  
	 * @param hdfsPath
	 * @param file
	 * @throws Exception
	 */
	
	public void appendContent2(String hdfsPath,File file) throws Exception{
		FSDataOutputStream append=null;
		FileSystem fs=null;
		BufferedReader br=null;
		try{
			conf.set("dfs.support.append", "true");
			fs=FileSystem.get(conf);
			append=fs.append(new Path(hdfsPath));
			br=new BufferedReader(new FileReader(file));
			String line=null;
			while((line=br.readLine())!=null){
				append.writeChars(line);
				append.flush();
			}
		}catch(Exception e){
			throw e;
		}finally{
			append.close();
			br.close();
			
		}
	}
	
	public void appendContent(String hdfsPath,InputStream in) throws Exception{
		FileSystem fs=null;
		OutputStream out=null;
		try{
			conf.set("dfs.support.append", "true");
			fs=FileSystem.get(URI.create(hdfsPath),conf);
			out=fs.append(new Path(hdfsPath));
			IOUtils.copyBytes(in, out, 4096,true);
		}catch(Exception e){
			throw e;
		}finally{
			if(out!=null){
				out.close();
			}
			in.close();
		}
	}

}
