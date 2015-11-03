package cn.disruptive.security;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import cn.disruptive.common.util.SpringConfigTool;
import cn.disruptive.security.service.SecurityService;

/**
 * 
 * Description：自定义前端页面标签，控制菜单、按钮是否可见
 * Data：		2015年9月30日
 */
public class SecurityTag extends TagSupport {

	private static final long serialVersionUID = 830337909736368829L;

	private Integer userId; 		// 外部传入的参数：用户id
	private String resourceCode; 	// 资源码（类名.方法）

	public int doStartTag() throws JspException {
		SecurityService securityService = SpringConfigTool.getBean("securityService");
		if (securityService.judgeAuthority(userId, resourceCode)) { 	// 权限判断
			return EVAL_PAGE;
		}
		return SKIP_BODY;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}

}