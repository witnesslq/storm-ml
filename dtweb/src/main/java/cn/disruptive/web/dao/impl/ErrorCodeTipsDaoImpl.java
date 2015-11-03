package cn.disruptive.web.dao.impl;

import org.springframework.stereotype.Repository;

import cn.disruptive.core.dao.impl.BaseDaoImpl;
import cn.disruptive.web.dao.ErrorCodeTipsDao;
import cn.disruptive.web.po.ErrorCodeTips;


/**
 * 
 * Description：错误码处理建议
 * Data：		2015年9月18日
 */
@Repository("errorCodeTipsDao")
public class ErrorCodeTipsDaoImpl extends BaseDaoImpl<ErrorCodeTips, Integer> implements ErrorCodeTipsDao {

}
