package cn.disruptive.web.dao.impl;

import org.springframework.stereotype.Repository;

import cn.disruptive.core.dao.impl.BaseDaoImpl;
import cn.disruptive.web.dao.SceneDao;
import cn.disruptive.web.po.Scene;

/**
 * 
 * Description：交易场景列表
 * Data： 		2015年9月12日
 */
@Repository("sceneDao")
public class SceneDaoImpl extends BaseDaoImpl<Scene, Integer> implements SceneDao {

}
