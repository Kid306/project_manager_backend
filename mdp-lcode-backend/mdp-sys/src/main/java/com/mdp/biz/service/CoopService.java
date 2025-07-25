// package com.mdp.biz.service;
//
// import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
// import com.baomidou.mybatisplus.core.metadata.IPage;
// import com.mdp.biz.entity.Coop;
// import com.mdp.biz.mapper.CoopMapper;
// import com.mdp.core.service.BaseService;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.stereotype.Service;
//
// import java.util.List;
// import java.util.Map;
// /**
//  * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br>
//  * 组织 com  顶级模块 mdp 大模块 biz 小模块 <br>
//  * 实体 Coop 表 biz_coop 当前主键(包括多主键): id;
//  ***/
// @Service
// public class CoopService extends BaseService<CoopMapper,Coop> {
// 	static Logger logger =LoggerFactory.getLogger(CoopService.class);
//
// 	/**
// 	 * 自定义查询，支持多表关联
// 	 * @param page 分页条件
// 	 * @param ew 一定要，并且必须加@Param("ew")注解
// 	 * @param ext 如果xml中需要根据某些值进行特殊处理，可以通过这个进行传递，非必须，注解也可以不加
// 	 * @return
// 	 */
// 	public List<Map<String,Object>> selectListMapByWhere(IPage page, QueryWrapper ew, Map<String,Object> ext){
// 		return baseMapper.selectListMapByWhere(page,ew,ext);
// 	}
// }
//
