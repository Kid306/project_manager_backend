package com.mdp.sys.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.service.BaseService;
import com.mdp.core.utils.BaseUtils;
import com.mdp.sys.entity.DeptLocation;
import com.mdp.sys.mapper.DeptLocationMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br> 
 * 组织 com.qqkj  顶级模块 mdp 大模块 sys 小模块 <br>
 * 实体 DeptLocation 表 ADMIN.sys_dept_location 当前主键(包括多主键): deptid; 
 ***/
@Service("mdp.sys.deptLocationService")
public class DeptLocationService extends BaseService<DeptLocationMapper, DeptLocation> {

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
	
	/** 请在此类添加自定义函数 */
	public void addAndEditDeptLocation(Map<String,Object> deptLocation){
		Map<String,Object> dept = (Map<String,Object>)deptLocation.get("deptLocation");
		if(dept!=null) {
		DeptLocation fromMap = BaseUtils.fromMap(dept, DeptLocation.class);
		this.deleteByPk(fromMap);
		this.insert(fromMap);
		}
	}
	public DeptLocation getDeptLocation(Map<String,Object> map){
		String id = (String)map.get("deptid");
		DeptLocation deptLocation = new DeptLocation();
		deptLocation.setDeptid(id);
		DeptLocation selectOneObject = this.selectOneObject(deptLocation);
		return selectOneObject;
	}
}

