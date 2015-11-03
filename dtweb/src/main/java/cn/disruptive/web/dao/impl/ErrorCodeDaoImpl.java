package cn.disruptive.web.dao.impl;

import org.springframework.stereotype.Repository;

import cn.disruptive.core.dao.impl.BaseDaoImpl;
import cn.disruptive.web.dao.ErrorCodeDao;
import cn.disruptive.web.po.ErrorCode;

/**
 * 
 * Description：错误码信息表
 * Data：		2015年9月14日
 */
@Repository("errorCodeDao")
public class ErrorCodeDaoImpl extends BaseDaoImpl<ErrorCode, Integer> implements ErrorCodeDao {

}
