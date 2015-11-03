/**
 * @author zhujl
 *
 * 2015-4-9
 */
package cn.disruptive.common.util;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * 
 *
 * 
 */
public class PoiUtil {
	
	
	//获取xlsx的总行数和总sheet数
	 public static int readXlsx(InputStream inputstream) throws Exception {  
	        int errType = 0;  
	        XSSFWorkbook hssfworkbook = new XSSFWorkbook(inputstream);  
	        System.out.println("共有sheet:"+hssfworkbook.getNumberOfSheets());
	        for(int a=0;a<hssfworkbook.getNumberOfSheets();a++){
	            XSSFSheet hssfsheet = hssfworkbook.getSheetAt(a);
	        if (hssfsheet != null) {  
	            //int totalrows = hssfsheet.getPhysicalNumberOfRows();// --获取sheet总行数  
	            for (int i = 0; i <= hssfsheet.getLastRowNum();i++) { 
	                Row r = hssfsheet.getRow(i); 
	                if(r == null){ 
	                	System.out.println("是空行");
	                    // 如果是空行（即没有任何数据、格式），直接把它以下的数据往上移动 
	                	//hssfsheet.shiftRows(i+1, hssfsheet.getLastRowNum(),-1); 
	                    continue; 
	                } 
	                boolean flag = false; 
	                for(Cell c:r){ 
	                    if(c.getCellType() != Cell.CELL_TYPE_BLANK){ 
	                        flag = true; 
	                        break; 
	                    } 
	                } 
	                if(flag){ 
	                    i++; 
	                    continue; 
	                } 
	                else{//如果是空白行（即可能没有数据，但是有一定格式） 
	                    if(i == hssfsheet.getLastRowNum())//如果到了最后一行，直接将那一行remove掉 
	                    	hssfsheet.removeRow(r); 
	                    else//如果还没到最后一行，则数据往上移一行 
	                    	hssfsheet.shiftRows(i+1, hssfsheet.getLastRowNum(),-1); 
	                } 
	            } 
	            errType+=hssfsheet.getLastRowNum()+1;
	        } 
	        }
	        return errType;  
	    }  
	 public static void main(String[] args) throws Exception {
		 int errType=readXlsx(new FileInputStream("E:\\测试DEMO\\测试.xlsx"));
	}
}
