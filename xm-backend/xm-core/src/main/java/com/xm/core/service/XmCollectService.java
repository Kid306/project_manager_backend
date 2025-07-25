package com.xm.core.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.service.BaseService;
import com.xm.calc.service.XmCalcServiceApi;
import com.xm.core.entity.XmCollect;
import com.xm.core.mapper.XmCollectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
/**
 * @author maimeng-mdp code-gen
 * @since 2023-10-3
 */
@Service
public class XmCollectService extends BaseService<XmCollectMapper,XmCollect> {
	static Logger logger =LoggerFactory.getLogger(XmCollectService.class);

	@Autowired
	XmCalcServiceApi xmCalcServiceApi;

	/**
	 * 自定义查询，支持多表关联
	 * @param page 分页条件
	 * @param ew 一定要，并且必须加@Param("ew")注解
	 * @param ext 如果xml中需要根据某些值进行特殊处理，可以通过这个进行传递，非必须，注解也可以不加
	 * @return
	 */
	public List<Map<String,Object>> selectListMapByWhere(IPage page, QueryWrapper ew, Map<String,Object> ext){
		return baseMapper.selectListMapByWhere(page,ew,ext);
	}

    public List<Map<String, Object>> selectListMapByWhereWithState(IPage page, QueryWrapper<XmCollect> ew, Map<String, Object> ext) {
		return baseMapper.selectListMapByWhereWithState(page,ew,ext);
	}
	public int loadProjectStateToXmCollectState(String collectId) {
		xmCalcServiceApi.loadProjectStateToXmCollectState( collectId );
		return 1;
	}
	public void load(){
	}
}

