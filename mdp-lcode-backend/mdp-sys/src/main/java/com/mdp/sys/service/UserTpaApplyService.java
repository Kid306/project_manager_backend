package com.mdp.sys.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.err.BizException;
import com.mdp.core.service.BaseService;
import com.mdp.core.utils.BaseUtils;
import com.mdp.core.utils.DateUtils;
import com.mdp.sys.entity.User;
import com.mdp.sys.entity.UserTpaApply;
import com.mdp.sys.mapper.UserTpaApplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br>
 * 组织 com.qqkj 顶级模块 mdp 大模块 sys 小模块 <br>
 * 实体 SysUserTpaApply 表 ADM.sys_user_tpa_apply 当前主键(包括多主键): id;
 ***/
@Service("mdp.sys.userTpaApplyService")
public class UserTpaApplyService extends BaseService<UserTpaApplyMapper,UserTpaApply>{
	@Autowired
	private UserService userService;

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
	     
	public UserTpaApply addNew(Map<String, Object> params) {

		UserTpaApply userTpaApply = BaseUtils.fromMap(params,UserTpaApply.class);
		//根据登陆名字进行用户查询
		if (StringUtils.hasText(userTpaApply.getLoginNo())) {
			User customerQueryUserObject=new User();
			customerQueryUserObject.setDisplayUserid(userTpaApply.getLoginNo());
			if(CollectionUtils.isEmpty(userService.selectListByWhere(customerQueryUserObject))) {
				throw new BizException("该后台登录账号不存在");
			}
		}
		//判断是不是申请中
		QueryWrapper<UserTpaApply> qw=new QueryWrapper<>();
		qw.eq("userid",userTpaApply.getUserid());
		qw.eq("branch_id",userTpaApply.getBranchId());
		qw.eq("shop_id",userTpaApply.getShopId());
		qw.eq("location_id",userTpaApply.getLocationId());
		List<UserTpaApply> res=this.list(qw);
		//查询是不是已经在申请中

		if(!CollectionUtils.isEmpty(res)) {
			throw new BizException("正在申请中，请等待");
		}
		userTpaApply.setId(createKey("id"));
		userTpaApply.setCreateDate(DateUtils.getNowDate());
		this.insert(userTpaApply);
		return userTpaApply;
	}

	/** 请在此类添加自定义函数 */

}
