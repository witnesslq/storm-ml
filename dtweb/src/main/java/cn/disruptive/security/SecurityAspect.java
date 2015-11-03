package cn.disruptive.security;

import java.lang.reflect.Method;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.disruptive.common.util.SessionManage;
import cn.disruptive.core.session.AuthUser;
import cn.disruptive.security.service.SecurityService;

@Aspect
@Component
public class SecurityAspect extends Exception {

	private static final long serialVersionUID = 5828578061948225107L;
	private static final Logger logger = LoggerFactory.getLogger(SecurityAspect.class);

	@Resource
	private SecurityService securityService;
	// 层切点
	@Pointcut("@annotation(cn.disruptive.security.Security)")
	public void aspect() {
	}

	/**
	 * 
	 * 前置通知 用于拦截service层记户操作权限
	 * 
	 * @throws Exception
	 * 
	 */
	@Before("aspect()")
	public void doBefore(JoinPoint joinPoint) throws Exception {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		// 读取session中的用户
		AuthUser auth = SessionManage.getSessionUser(request);
		// 请求的IP
		String ip = request.getRemoteAddr();
		String resourceCod = joinPoint.getTarget().getClass().getSimpleName() + "." + joinPoint.getSignature().getName();
		String logInfo = getMthods(joinPoint);
		// *========日志输出=========*//
		logger.debug("######前置通知######");
		logger.debug("请求方法:{}()", resourceCod);
		logger.debug("方法描述:{}", logInfo);
		logger.debug("用户id:{}",auth.getUserId());
		logger.debug("请求IP:{}",ip);

		// 权限判断
		boolean flag = securityService.judgeAuthority(auth.getUserId(), resourceCod);
		if (!flag) {
			throw new RuntimeException("权限不足");
		} else {
			logger.debug("权限验证通过");
		}
		// *========数据库记录日志=========*//
		securityService.saveHandleLogs(auth.getUserId(), resourceCod, logInfo, ip);
	}

	@After("aspect()")
	public void doAfter() {
		logger.debug("######后置通知######");
	}

	public static String getMthods(JoinPoint joinPoint) throws Exception {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		String describe = "";
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					describe = method.getAnnotation(Security.class).describe();// 模块名称
					break;
				}
			}
		}
		return describe;
	}
}