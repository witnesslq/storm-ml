package com.disruptive.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

public class HDFSHelper {
	
	static Configuration conf=new Configuration();
	static FileSystem hdfs;
	public static final String HADOOP_URL;
	static{
		HADOOP_URL=PropertiesUtil.getPropertyString("hdfs.hadoop.url");
		String path = "";
		path = "/etc/hadoop/conf/";
		conf.addResource(new Path(PropertiesUtil.getPropertyString("hdfs.conf.core.site")));
		conf.addResource(new Path(path + PropertiesUtil.getPropertyString("hdfs.conf.hdfs.site")));
		conf.addResource(new Path(path + PropertiesUtil.getPropertyString("hdfs.conf.mapred.site")));
		
		// conf.addResource(new Path(path+"hbase-site.xml"));
		try {
			hdfs = FileSystem.get(conf);
			// hdfs=Path.
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
		System.out.println("new dir \t" + conf.get("fs.default.name"));
	}

	/**
	 * copy from local file to HDFS file
	 * 
	 * @param localSrc
	 * @param hdfsDst
	 * @throws IOException
	 */
	public static void copyFile(String localSrc, String hdfsDst) throws IOException {
		Path src = new Path(localSrc);
		Path dst = new Path(hdfsDst);
		System.out.println(conf.get("fs.default.name"));
		hdfs.copyFromLocalFile(src, dst);
		// list all the files in the current direction
		FileStatus files[] = hdfs.listStatus(dst);
		System.out.println("Upload to \t " + conf.get("fs.default.name"));
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
	public void createFile(String fileName, String fileContent) throws Exception {
		Path dst = new Path(fileName);
		byte[] bytes = fileContent.getBytes();
		FSDataOutputStream output = hdfs.create(dst);
		output.write(bytes);
		System.out.println("new file \t" + conf.get("fs.default.name"));
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
	 * judge a file existed? and delete it!
	 * 
	 * @param fileName
	 * @throws IOException
	 */
	public void deleteFile(String fileName) throws IOException {
		Path f = new Path(fileName);
		boolean isExists = hdfs.exists(f);
		if (isExists) {
			boolean isDel = hdfs.delete(f);
			System.out.println(fileName + " delete? \t" + isDel);

		} else {
			System.out.println(fileName + "exist? \t" + isExists);
		}
	}

	//
	

	public static void hdfs(String localFilePath) throws Exception {
		URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
		final URL url = new URL(HADOOP_URL);
		final InputStream in = url.openStream();
		OutputStream out = new FileOutputStream(localFilePath);
		IOUtils.copyBytes(in, out, 4096, false);
		out.close();
		in.close();
	}

	/**
	 * 是否存在文件
	 * 
	 * @param hdfsFilePath
	 * @return
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public static boolean isExists(String hdfsFilePath) throws IOException, URISyntaxException {
		FileSystem fileSystem = FileSystem.get(new URI(HADOOP_URL), conf);
		return fileSystem.exists(new Path(hdfsFilePath));
	}

	/**
	 * 创建文件
	 * 
	 * @param hdfsFilePath
	 * @throws IllegalArgumentException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public static void createFile(String hdfsFilePath)
			throws IllegalArgumentException, IOException, URISyntaxException {
		FileSystem fileSystem = FileSystem.get(new URI(HADOOP_URL), conf);
		fileSystem.createNewFile(new Path(hdfsFilePath));
	}

	/**
	 * 上传文件
	 * 
	 * @param localFilePath
	 * @param fileName
	 * @throws Exception
	 */
	public static void upload(String localFilePath, String fileName) throws Exception {
		FileSystem fileSystem = org.apache.hadoop.hdfs.DistributedFileSystem.get(new URI(HADOOP_URL), conf);
		final OutputStream out = fileSystem.create(new Path("/tmp/zhizhen/" + fileName));
		final FileInputStream in = new FileInputStream(localFilePath);
		IOUtils.copyBytes(in, out, 1024, true);
	}

	/**
	 * 文件append
	 * 
	 * @param hdfsFilePath
	 * @param localFilePath
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public static void appendContent(String hdfsFilePath, String localFilePath) throws IOException, URISyntaxException {
		conf.setBoolean("dfs.support.append", true);
//		conf.set("dfs.client.block.write.replace-datanode-on-failure.policy", "NEVER");
//		conf.set("dfs.client.block.write.replace-datanode-on-failure.enable", "true");
		FileSystem fileSystem = FileSystem.get(new URI(HADOOP_URL), conf);
		// 要追加的文件流,inpuath 为文件
		InputStream in = new BufferedInputStream(new FileInputStream(localFilePath));
		OutputStream out = fileSystem.append(new Path(hdfsFilePath));
		IOUtils.copyBytes(in, out, 4096, true);
		
	}
	
	
	
	

}
