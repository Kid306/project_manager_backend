package com.mdp.plat.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.err.BizException;
import com.mdp.core.service.BaseService;
import com.mdp.plat.entity.Platform;
import com.mdp.plat.mapper.PlatformMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br> 
 * 组织 com  顶级模块 mdp 大模块 plat 小模块 <br>
 * 实体 Platform 表 ADM.plat_platform 当前主键(包括多主键): id; 
 ***/
@Service("mdp.plat.platformService")
public class PlatformService extends BaseService<PlatformMapper,Platform> {
	static Logger logger =LoggerFactory.getLogger(PlatformService.class);

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
	@Transactional
	public void editPlatform(Platform platform) {
		Platform platformDb=this.selectOneObject(platform);
		if(platform==null){
			throw new BizException("平台不存在");
		}

		if(platform.getShopId()!=null && !platform.getShopId().equals(platformDb.getShopId())){//修改了平台商户编号，变更原来的所有的商户的平台编号

		}
		this.updateSomeFieldByPk(platform);
	}
	/** 请在此类添加自定义函数 */

}

