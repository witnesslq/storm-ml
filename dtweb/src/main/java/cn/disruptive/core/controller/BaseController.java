package cn.disruptive.core.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Controller
public class BaseController {
	public final Logger logger = LoggerFactory.getLogger(getClass());
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		return request;
	}
	public String getClientIpAddr(HttpServletRequest request) {
		String ipstr = request.getHeader("x-forwarded-for");
		if (ipstr == null || ipstr.length() == 0 || "unknown".equalsIgnoreCase(ipstr)) {
			ipstr = request.getHeader("Proxy-Client-IP");
		}
		if (ipstr == null || ipstr.length() == 0 || "unknown".equalsIgnoreCase(ipstr)) {
			ipstr = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipstr == null || ipstr.length() == 0 || "unknown".equalsIgnoreCase(ipstr)) {
			ipstr = request.getRemoteAddr();
		}
		logger.debug("ipstr:"+ipstr);
		return ipstr;
	}
}
