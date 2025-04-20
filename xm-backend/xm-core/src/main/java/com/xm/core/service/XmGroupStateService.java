package com.xm.core.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.service.BaseService;
import com.xm.core.entity.XmGroupState;
import com.xm.core.mapper.XmGroupStateMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br> 
 * 组织 com.qqkj  顶级模块 xm 大模块 core 小模块 <br>
 * 实体 XmProjectGroupState 表 XM.xm_group_state 当前主键(包括多主键): id; 
 ***/
@Service("xm.core.xmGroupStateService")
public class XmGroupStateService extends BaseService<XmGroupStateMapper, XmGroupState> {
	
	/**
	 * 计算bug、task、测试案例、等数据
	 * @param projectId
	 * @return
	 */
	public int loadTasksToXmProjectGroupState(String projectId) {
		 
		return baseMapper.loadTasksToXmProjectGroupState( projectId);
	}

	public List<Map<String, Object>> selectListMapByWhere(IPage page, QueryWrapper<XmGroupState> qw, Map<String, Object> ext) {
		return baseMapper.selectListMapByWhere(page,qw,ext);
	}
}

