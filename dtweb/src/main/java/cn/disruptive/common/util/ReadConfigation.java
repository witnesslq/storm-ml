package cn.disruptive.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;


/**
 * 读取配置文件
 */
public class ReadConfigation {
	    private static String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
	    /**   
	    * 属性文件全名   
	    */   
	    private static final String PFILE =path+"/Config.properties";    
	    /**   
	    * 对应于属性文件的文件对象变量   
	    */   
	    private static File m_file = null;    
	    /**   
	    * 属性文件的最后修改日期   
	    */   
	    private static long m_lastModifiedTime = 0;    
	    /**   
	    * 属性文件所对应的属性对象变量   
	    */   
	    private static Properties m_props = null;    
	    /**   
	    * 本类可能存在的惟一的一个实例   
	    */   
	    private static ReadConfigation m_instance =new ReadConfigation();    
	    /**   
	    * 私有的构造子，用以保证外界无法直接实例化   
	    */   
	    private ReadConfigation()    
	    {    
	        m_file = new File(PFILE);    
	        m_lastModifiedTime = m_file.lastModified();    
	        if(m_lastModifiedTime == 0){    
	            System.err.println(PFILE +" file does not exist!");    
	        }    
	        m_props = new Properties();    
	        try   
	        {    
	        	InputStream in=new FileInputStream(PFILE);
	        	InputStreamReader re=new InputStreamReader(in,"UTF-8");
	        	BufferedReader bf=new BufferedReader(re);  
	            m_props.load(bf);    
	        }    
	        catch(Exception e)    
	        {    
	            e.printStackTrace();    
	        }    
	    }    
	    /**   
	    * 静态工厂方法   
	    * @return 返还ReadConfigation 类的单一实例   
	    */   
	    synchronized public static ReadConfigation getInstance()    
	    {    
	        return m_instance;    
	    }    
	    /**   
	    * 读取一特定的属性项   
	    *   
	    * @param name 属性项的项名   
	    * @param defaultVal 属性项的默认值   
	    * @return 属性项的值（如此项存在）， 默认值（如此项不存在）   
	    */   
	     public static String getConfigItem(String name,String defaultVal)    
	    {    
	        long newTime = m_file.lastModified();    
	        // 检查属性文件是否被其他程序    
	        // 如果是，重新读取此文件    
	        
	        if(newTime == 0)    
	        {    
	            // 属性文件不存在    
	            if(m_lastModifiedTime == 0){    
	                System.err.println(PFILE+ " file does not exist!");    
	            }else{    
	                System.err.println(PFILE+ " file was deleted!!");    
	            }    
	            return defaultVal;    
	        }else if(newTime > m_lastModifiedTime){    
	            // Get rid of the old properties    
	            m_props.clear();    
	            try   
	            {    
	            	InputStream in=new FileInputStream(PFILE);
		        	InputStreamReader re=new InputStreamReader(in,"UTF-8");
		        	BufferedReader bf=new BufferedReader(re);  
		            m_props.load(bf);   
	            }catch(Exception e){    
	                e.printStackTrace();    
	            }    
	        }    
	            m_lastModifiedTime = newTime;    
	            String val = m_props.getProperty(name);    
	            if( val == null )    
	            {    
	                return defaultVal;    
	            }    
	            else   
	            {    
	                return val;    
	            }    
	    }     
	   public static String getConfigItem(String name){    
	        return getConfigItem(name,"");    
	    }    

}
