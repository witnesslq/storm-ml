package com.disruptive.util;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XmlHelper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(XmlHelper.class);
	private static String FILE_PATH;
	private static SAXReader reader;
	private static File file;
	public  static Map<String, String> SQL_MAP = new HashMap<String, String>();
	
	public static void getSql(){
		Document document;
		try {
			FILE_PATH = XmlHelper.class.getClassLoader().getResource("").getPath()
					+ "/" + "mysqlConfig.xml";
			reader = new SAXReader();
			file = new File(FILE_PATH);
			document = reader.read(file);
			Element root = document.getRootElement();
			List<Attribute> attributeList = root.attributes();
			for (Attribute attr : attributeList) {
				System.out.println(attr.getName() + ":" + attr.getValue());
			}
			System.out.println(root.getTextTrim());
		//	SQL_MAP.put(key, root.getText());
		} catch (DocumentException e) {
			LOGGER.error(e.getMessage());
		}
		
	}

	public static void main(String[] args) throws DocumentException {
		XmlHelper.getSql();
	}

}
