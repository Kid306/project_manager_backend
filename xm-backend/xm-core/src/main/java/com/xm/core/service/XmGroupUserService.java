package com.xm.core.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.service.BaseService;
import com.xm.core.entity.XmGroupUser;
import com.xm.core.mapper.XmGroupUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br> 
 * 组织 com.qqkj  顶级模块 oa 大模块 xm 小模块 <br>
 * 实体 XmProjectGroupUser 表 XM.xm_group_user 当前主键(包括多主键): id; 
 ***/
@Service("xm.core.xmGroupUserService")
public class XmGroupUserService extends BaseService<XmGroupUserMapper,XmGroupUser> {


	@Autowired
	XmRecordService xmRecordService;
	
	public List<XmGroupUser> selectGroupUserListByProjectId(String projectId) {
		return baseMapper.selectGroupUserListByProjectId( projectId); 
	}

	public List<XmGroupUser> selectGroupUserListByProductId(String productId) {
		return baseMapper.selectGroupUserListByProductId( productId);
	}


	public void doBatchDelete(List<XmGroupUser> canDelUsers) {
		super.batchDelete(canDelUsers);
	}

    public List<Map<String, Object>> selectListMapByWhere(IPage page, QueryWrapper<XmGroupUser> qw, Map<String, Object> params) {
    	return baseMapper.selectListMapByWhere(page,qw,params);
	}

    /** 请在此类添加自定义函数 */

}

