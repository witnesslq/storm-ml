package cn.disruptive.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.disruptive.common.util.JCaptchaValidate;
import cn.disruptive.common.util.MessagePush;
import cn.disruptive.common.util.SessionManage;
import cn.disruptive.core.controller.BaseController;
import cn.disruptive.core.session.AuthUser;
import cn.disruptive.web.service.LoginService;

/**
 * 
 * Description：登录
 * Data：		2015年9月11日
 */
@Controller
public class LoginController extends BaseController {
	
	@Resource
	private LoginService loginService;

	@RequestMapping("/login")
	public String loginInit(ModelMap modelMap) {
		logger.debug("进入客服登录页");
		return "login";
	}

	@RequestMapping("/")
	public String index(ModelMap modelMap) {
		logger.debug("主页");
		return "login";
	}
	
	/**
	 * @param loginApp 应用id
	 * @param loginName
	 * @param loginPwd
	 * @param loginFlag
	 * @param loginCode
	 * @param response
	 * @return
	 */
	@RequestMapping("/loginSubmit")
	@ResponseBody
	public Object loginSubmit(@RequestParam String loginName, @RequestParam String loginPwd, @RequestParam String randCode) {
		logger.debug("执行登录操作,loginName={},loginPwd={}", new Object[] { loginName, loginPwd });
		// 判断登录
		Map<String, Object> map = new HashMap<String, Object>();
		if (!JCaptchaValidate.validateCaptchaCode(getRequest(), randCode, true)) {
			logger.debug("验证码输入不正确");
			map.put("flag", false);
			map.put("msg", "验证码输入不正确");
			return map;
		}
		AuthUser auth = SessionManage.getSessionUser(getRequest());
		if (auth == null) {
			auth = new AuthUser();
		}

		logger.debug("注册用户登录");
		auth = loginService.login(loginName, loginPwd, auth);

		if (auth.getUserId() != null) {
			logger.debug("登录成功");
			map.put("flag", true);
			map.put("url", "/manage/alert/allInfo");
			SessionManage.setSessionUser(getRequest(), auth);
			return map;
		} else {
			logger.debug("用户或密码错误");
			map.put("msg", "用户名或者密码错误");
			map.put("flag", false);
			return map;
		}
	}
	
	@RequestMapping("/logout")
	public String logout(ModelMap modelMap) {
		logger.debug("退出登录");
		HttpSession session = getRequest().getSession();
		session.invalidate();
		return "redirect:/login";
	}

	@RequestMapping("/user/login")
	public String userLogin() {
		logger.debug("拦截登录");
		return "login";
	}

	@RequestMapping("/loginInput")
	public String loginInput() {
		logger.debug("拦截登录");
		return "login";
	}

	@RequestMapping("/loginExpired")
	public String loginExpired() {
		logger.debug("拦截登录");
		return "redirect:/logout";
	}

	@RequestMapping("/pushMsg")
	@ResponseBody
	public Object pushMsg(String sceneCode, String message) throws Exception {
		logger.debug("主动推送消息,sceneCode={},message={}", sceneCode, message);
		Map<String, Object> map = new HashMap<String, Object>();
		String userid = "" + 2;
		if (StringUtils.isNotBlank(message)) {
			message = new String(message.getBytes("ISO-8859-1"), "UTF-8").trim();
		}
		new MessagePush().pushAlertMsg(userid, sceneCode, message);
		map.put("flag", true);
		return map;
	}
}
