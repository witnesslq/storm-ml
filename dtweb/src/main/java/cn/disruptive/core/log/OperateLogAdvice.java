package cn.disruptive.core.log;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import cn.disruptive.core.annotation.OperateLog;

/**
 * @ClassName: OperateLogAdvice    
 * @Description: 记录用户操作日志   
 */
public class OperateLogAdvice implements MethodBeforeAdvice ,AfterReturningAdvice{
	public final Logger logger = LoggerFactory.getLogger(OperateLogAdvice.class);
//	@Resource
//	private SecurityService securityService;
	@Override
	public void before(Method method, Object[] arg, Object target) throws Throwable {
		logger.debug("方法执行前调用日志");
		//判断方法是否注解了UserOperateLog  
        OperateLog anno = method.getAnnotation(OperateLog.class);  
        if (anno == null)  
            return;  
          
        String defaultMessage = anno.value();  
        String methodName = target.getClass().getName() + "." + method.getName();  
        logger.info(methodName+defaultMessage);
	}
	@Override
	public void afterReturning(Object returnObj, Method method, Object[] arg, Object target) throws Throwable {
		logger.debug("方法执行后调用日志");
		OperateLog anno = method.getAnnotation(OperateLog.class);  
        if (anno == null)  
            return;  
        
        String defaultMessage = anno.value();  
//        int[] param=anno.param();
//        if(param.length>0){
//        	defaultMessage.ge
//        }
        String methodName = target.getClass().getName() + "." + method.getName();  
        logger.info(methodName+defaultMessage);
//        MyUserDetails userDetails=SpringSecurityUtils.getCurrentUser();
//        securityService.saveOperateLog(userDetails.getUserId(), userDetails.getName(),defaultMessage,methodName);
	}

}
