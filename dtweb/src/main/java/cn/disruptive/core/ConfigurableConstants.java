package cn.disruptive.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * @ClassName: ConfigurableConstants    
 * @Description: TODO   
 */
public class ConfigurableConstants {
	protected static final Log logger = LogFactory.getLog(ConfigurableConstants.class);
	protected static Properties _prop = new Properties();

	protected static void init(String propertyFileName, String encode) {
		InputStream in = null;
		try {
			in = ConfigurableConstants.class.getClassLoader()
					.getResourceAsStream(propertyFileName);
			if (in == null) {
				return;
			}
			Reader rd = new InputStreamReader(in);
			if (encode != null) {
				rd = new InputStreamReader(in, encode);
			}
			_prop.load(rd);
		} catch (IOException e) {
			logger.error("load " + propertyFileName + " into Constants error!");
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					logger.error("close " + propertyFileName + " error!");
				}
		}
	}

	protected static String getProperty(String key, String defaultValue) {
		return _prop.getProperty(key, defaultValue);
	}
}
