package cn.disruptive.web.controller.manage;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.disruptive.common.util.Encrypt;
import cn.disruptive.common.util.SessionManage;
import cn.disruptive.core.controller.BaseController;
import cn.disruptive.core.session.AuthUser;
import cn.disruptive.web.po.UserInfo;
import cn.disruptive.web.service.UserInfoService;

/**
 * 
 * Description：用户帐号管理
 * Data：		2015年9月12日
 */
@Controller
@RequestMapping("/manage/user")
public class UserInfoController extends BaseController {
	
	@Resource
	private UserInfoService userInfoService;

	@RequestMapping("/getOne")
	public String getOne(ModelMap modelMap) {
		logger.debug("获取登录账户信息");
		return "manage/user/getOne";
	}

	@RequestMapping("/toModifyPwd")
	public String toModifyPwd(ModelMap modelMap) {
		logger.debug("获取登录账户信息");
		return "manage/user/modifyPwd";
	}

	@RequestMapping("/checkPassword")
	public @ResponseBody
	boolean isOldPassword(@RequestParam String oldPassword) {
		logger.debug("验证用户原始密码输入是否正确");
		if (oldPassword != null) {
			// 获取用户对象
			AuthUser auth = SessionManage.getSessionUser(getRequest());
			UserInfo userInfo = userInfoService.getAccountById(auth.getUserId());
			if (Encrypt.encryptMD5(oldPassword).equals(userInfo.getUserPwd())) {
				return true;
			}
			return false;
		}
		return false;
	}

	@RequestMapping("/doSaveChangePwd")
	public String doSaveChangeAccountPwd(@RequestParam String oldPassword, @RequestParam String password, ModelMap modelMap) {
		logger.debug("用户更新密码");
		if (StringUtils.isNotBlank(password)) { // 如果新密码不为空 执行密码修改操作
			AuthUser auth = SessionManage.getSessionUser(getRequest());
			UserInfo userInfo = userInfoService.getAccountById(auth.getUserId());
			if (Encrypt.encryptMD5(oldPassword).equals(userInfo.getUserPwd())) {
				userInfo.setUserPwd(Encrypt.encryptMD5(password));
				userInfoService.updateUserInfo(userInfo);
				modelMap.addAttribute("msg", true);
			}
		}
		return "manage/user/modifyPwd";
	}

}
