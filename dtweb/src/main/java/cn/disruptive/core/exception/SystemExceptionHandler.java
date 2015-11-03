package cn.disruptive.core.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class SystemExceptionHandler implements HandlerExceptionResolver {
	private final Logger logger = LoggerFactory.getLogger(SystemExceptionHandler.class);
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		logger.debug("异常拦截器");
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("ex", ex);
		ex.printStackTrace();
		// 根据不同错误转向不同页面
		if(ex instanceof ServiceException) {
			logger.error("业务异常:"+ex.getMessage());
			return new ModelAndView("error/exception", model);
		} else {
			logger.error("错误:"+ex.getMessage());
			return new ModelAndView("error/error", model);
		}
	}

}
