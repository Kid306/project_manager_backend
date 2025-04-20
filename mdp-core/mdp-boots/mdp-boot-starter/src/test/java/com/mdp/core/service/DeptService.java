package com.mdp.core.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mdp.core.entity.Dept;
import com.mdp.core.mapper.DeptMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br>
 * 
 * 组织 com.qqkj<br>
 * 顶级模块 mdp<br>
 * 大模块 sys<br>
 * 小模块 <br>
 * 表 ADMIN.sys_dept sys_dept<br>
 * 实体 Dept<br>
 * 表是指数据库结构中的表,实体是指java类型中的实体类<br>
 * 当前实体所有属性名:<br>
 *	deptid,deptname,pdeptid,depttype,regionId,regionName,address,contactPerson,areaCode,telephone,mobilephone,sfax,email,state,createPerson,createDate,loperatePerson,loperateDate,deptManager,inChargeManager,isactive,isSubcompany,deptShortname;<br>
 * 当前表的所有字段名:<br>
 *	deptid,deptname,pdeptid,depttype,region_id,region_name,address,contact_person,area_code,telephone,mobilephone,sfax,email,state,create_person,create_date,loperate_person,loperate_date,dept_manager,in_charge_manager,isactive,is_subcompany,dept_shortname;<br>
 * 当前主键(包括多主键):<br>
 *	deptid;<br>
 ***/
@Service("mdp.core.deptService")
public class DeptService extends ServiceImpl<DeptMapper,Dept> {

	@Override
	public boolean updateBatchById(Collection<Dept> entityList, int batchSize) {
		return super.updateBatchById(entityList, batchSize);
	}
}

