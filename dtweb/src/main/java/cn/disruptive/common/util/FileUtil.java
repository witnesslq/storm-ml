package cn.disruptive.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * 文件操作工具类
 * @author wanglizhi
 *
 */
public class FileUtil {
	
	/**
	 * 根据文件返回文件类型
	 * @param file
	 * @return
	 */
	@SuppressWarnings("unused")
	private static String getFileType(File file){
		String s[] = file.getName().split("[.]");
		return s[s.length > 0 ? s.length - 1 : 0];
	}
	
	
	/**
	 * 获取文件后缀名称
	 * @param filename
	 * @return
	 */
	public static String getFileType(String fileName) {
		if ((fileName != null) && (fileName.length() > 0)) {
			int dot = fileName.lastIndexOf('.');
			if ((dot > -1) && (dot < (fileName.length() - 1))) {
				return fileName.substring(dot + 1);
			}
		}
		return fileName;
	}
	/**
	 * 生成随机文件名称
	 * @param fileName
	 * @return yyyyMMddhhmmssSSS.***
	 */
	public static String createRandomFileName(String fileName){

		return DateUtils.formatMs(new Date())+"."+FileUtil.getFileType(fileName);
		
	}
	
	public static Set<String> sets = new HashSet<String>();  
	  
  
    /** 
     * 过滤MP3文件 
     *  
     * @param strPath 
     */  
    public static void refreshFileList(String strPath) {  
        File dir = new File(strPath);  
        File[] files = dir.listFiles();  
        if (files == null) {  
            return;  
        }  
        for (int i = 0; i < files.length; i++) {  
            if (files[i].isDirectory()) {  
                refreshFileList(files[i].getAbsolutePath());  
            } else {  
                String strFilePath = files[i].getAbsolutePath().toLowerCase();  
                String strName = files[i].getName();  
                if (strName.endsWith(".mp3")) {  
                    boolean bFlag = sets.add(strName);  
                    if (bFlag == Boolean.FALSE) {  
                        // 删除重复文件  
                        removeFile(strFilePath);  
                    }  
                }  
                // System.out.println("FILE_PATH:" + strFilePath + "|strName:" +  
                // strName);  
            }  
        }  
    }  
  
    /** 
     * 创建文件夹 
     *  
     * @param strFilePath 
     *            文件夹路径 
     */  
    public boolean mkdirFolder(String strFilePath) {  
        boolean bFlag = false;  
        try {  
            File file = new File(strFilePath.toString());  
            if (!file.exists()) {  
                bFlag = file.mkdir();  
            }  
        } catch (Exception e) {  
        	System.out.println("新建目录操作出错" + e.getLocalizedMessage());  
            e.printStackTrace();  
        }  
        return bFlag;  
    }  
  
    public boolean createFile(String strFilePath, String strFileContent) {  
        boolean bFlag = false;  
        try {  
            File file = new File(strFilePath.toString());  
            if (!file.exists()) {  
                bFlag = file.createNewFile();  
            }  
            if (bFlag == Boolean.TRUE) {  
                FileWriter fw = new FileWriter(file);  
                PrintWriter pw = new PrintWriter(fw);  
                pw.println(strFileContent.toString());  
                pw.close();  
            }  
        } catch (Exception e) {  
        	System.out.println("新建文件操作出错" + e.getLocalizedMessage());  
            e.printStackTrace();  
        }  
        return bFlag;  
    }  
  
    /** 
     * 删除文件 
     *  
     * @param strFilePath 
     * @return 
     */  
    public static boolean removeFile(String strFilePath) {  
        boolean result = false;  
        if (strFilePath == null || "".equals(strFilePath)) {  
            return result;  
        }  
        File file = new File(strFilePath);  
        if (file.isFile() && file.exists()) {  
            result = file.delete();  
            if (result == Boolean.TRUE) {  
            	System.out.println("[REMOE_FILE:" + strFilePath + "删除成功!]");  
            } else {  
            	System.out.println("[REMOE_FILE:" + strFilePath + "删除失败]");  
            }  
        }  
        return result;  
    }  
  
    /** 
     * 删除文件夹(包括文件夹中的文件内容，文件夹) 
     *  
     * @param strFolderPath 
     * @return 
     */  
    public static boolean removeFolder(String strFolderPath) {  
        boolean bFlag = false;  
        try {  
            if (strFolderPath == null || "".equals(strFolderPath)) {  
                return bFlag;  
            }  
            File file = new File(strFolderPath.toString());  
            bFlag = file.delete();  
            if (bFlag == Boolean.TRUE) {  
                System.out.println("[REMOE_FOLDER:" + file.getPath() + "删除成功!]");  
            } else {  
            	System.out.println("[REMOE_FOLDER:" + file.getPath() + "删除失败]");  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return bFlag;  
    }  
  
    /** 
     * 移除所有文件 
     *  
     * @param strPath 
     */  
    public static void removeAllFile(String strPath) {  
        File file = new File(strPath);  
        if (!file.exists()) {  
            return;  
        }  
        if (!file.isDirectory()) {  
            return;  
        }  
        String[] fileList = file.list();  
        File tempFile = null;  
        for (int i = 0; i < fileList.length; i++) {  
            if (strPath.endsWith(File.separator)) {  
                tempFile = new File(strPath + fileList[i]);  
            } else {  
                tempFile = new File(strPath + File.separator + fileList[i]);  
            }  
            if (tempFile.isFile()) {  
                tempFile.delete();  
            }  
            if (tempFile.isDirectory()) {  
                removeAllFile(strPath + "/" + fileList[i]);// 下删除文件夹里面的文件  
                removeFolder(strPath + "/" + fileList[i]);// 删除文件夹  
            }  
        }  
    }  
  
    public static void copyFile(String oldPath, String newPath) {  
        try {  
            int bytesum = 0;  
            int byteread = 0;  
            File oldfile = new File(oldPath);  
            if (oldfile.exists()) { // 文件存在时  
                InputStream inStream = new FileInputStream(oldPath); // 读入原文件  
                FileOutputStream fs = new FileOutputStream(newPath);  
                byte[] buffer = new byte[1444];  
                while ((byteread = inStream.read(buffer)) != -1) {  
                    bytesum += byteread; // 字节数 文件大小  
                    fs.write(buffer, 0, byteread);  
                }  
                inStream.close();
            }  
        } catch (Exception e) {  
            System.out.println("复制单个文件操作出错 ");  
            e.printStackTrace();  
        }  
    }  
  
    public static void copyFolder(String oldPath, String newPath) {  
        try {  
            (new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹  
            File a = new File(oldPath);  
            String[] file = a.list();  
            File temp = null;  
            for (int i = 0; i < file.length; i++) {  
                if (oldPath.endsWith(File.separator)) {  
                    temp = new File(oldPath + file[i]);  
                } else {  
                    temp = new File(oldPath + File.separator + file[i]);  
                }  
                if (temp.isFile()) {  
                    FileInputStream input = new FileInputStream(temp);  
                    FileOutputStream output = new FileOutputStream(newPath  
                            + "/ " + (temp.getName()).toString());  
                    byte[] b = new byte[1024 * 5];  
                    int len;  
                    while ((len = input.read(b)) != -1) {  
                        output.write(b, 0, len);  
                    }  
                    output.flush();  
                    output.close();  
                    input.close();  
                    System.out.println("[COPY_FILE:" + temp.getPath() + "复制文件成功!]");  
                }  
                if (temp.isDirectory()) {// 如果是子文件夹  
                    copyFolder(oldPath + "/ " + file[i], newPath + "/ "  
                            + file[i]);  
                }  
            }  
        } catch (Exception e) {  
            System.out.println("复制整个文件夹内容操作出错 ");  
            e.printStackTrace();  
        }  
    }  
  
    public static void moveFile(String oldPath, String newPath) {  
        copyFile(oldPath, newPath);  
        //removeFile(oldPath);  
    }  
  
    public static void moveFolder(String oldPath, String newPath) {  
        copyFolder(oldPath, newPath);  
        //removeFolder(oldPath);  
    }  

    public static void download(HttpServletRequest request,HttpServletResponse response, String contentType,  
            String filePath) throws Exception {  
        response.setContentType("text/html;charset=UTF-8");  
        request.setCharacterEncoding("UTF-8");  
        BufferedInputStream bis = null;  
        BufferedOutputStream bos = null;  
  
        System.out.println("path="+filePath);
        long fileLength = new File(filePath).length();  
  
        response.setContentType(contentType);  
        response.setHeader("Content-disposition", "attachment; filename="  
                + new String(filePath.split("/")[filePath.split("/").length-1].getBytes("utf-8"), "ISO8859-1"));  
        response.setHeader("Content-Length", String.valueOf(fileLength));  
  
        bis = new BufferedInputStream(new FileInputStream(filePath));  
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
