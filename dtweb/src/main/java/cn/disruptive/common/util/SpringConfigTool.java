package cn.disruptive.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringConfigTool implements ApplicationContextAware {
	 private static ApplicationContext context = null;
	 
	 @Override
	 public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		 context = applicationContext;
	 }

	 /**  
	  * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.  
	  */  
	@SuppressWarnings("unchecked")
	public synchronized static <T> T getBean(String name) {   
		return (T) context.getBean(name);   
	}   
}
