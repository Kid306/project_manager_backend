package com.mdp.sys.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.query.QueryTools;
import com.mdp.core.service.BaseService;
import com.mdp.core.utils.BaseUtils;
import com.mdp.sys.entity.Branch;
import com.mdp.sys.mapper.BranchMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br> 
 * 组织 com  顶级模块 mdp 大模块 sys 小模块 <br>
 * 实体 Branch 表 sys_branch 当前主键(包括多主键): id; 
 ***/
@Service
public class BranchService extends BaseService<BranchMapper,Branch> {
	static Logger logger =LoggerFactory.getLogger(BranchService.class);

	/**
	 * 自定义查询，支持多表关联
	 * @param page 分页条件
	 * @param ew 一定要，并且必须加@Param("ew")注解
	 * @param ext 如果xml中需要根据某些值进行特殊处理，可以通过这个进行传递，非必须，注解也可以不加
	 * @return
	 */
	List<Map<String,Object>> selectListMapByWhere(IPage page, QueryWrapper ew, Map<String,Object> ext){
		return baseMapper.selectListMapByWhere(page,ew,ext);
	}
	public static void main(String[] args) {
		Map<String,Object> itemOption= BaseUtils.map("res.id","xxxxxxxx","item.branchName","111","cuserid22","id222");
		Branch branch=new Branch();
		branch.setBranchName("xxxxxxxxx*");
		QueryWrapper<Branch> qw= QueryTools.initQueryWrapper(new Branch(),itemOption,"res.*");
		System.out.printf(""+qw.getCustomSqlSegment());


		QueryWrapper<Branch> qw2= QueryTools.initQueryWrapper(branch,null,"res.*");
		System.out.printf(""+qw2.getCustomSqlSegment());
	}
}

