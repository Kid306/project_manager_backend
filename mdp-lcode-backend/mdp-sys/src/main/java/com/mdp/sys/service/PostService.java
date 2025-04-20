package com.mdp.sys.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.service.BaseService;
import com.mdp.sys.entity.Post;
import com.mdp.sys.mapper.PostMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br> 
 * 组织 com.qqkj  顶级模块 mdp 大模块 sys 小模块 post<br>
 * 实体 Post 表 ADMIN.sys_post 当前主键(包括多主键): id; 
 ***/
@Service("mdp.sys.post.postService")
public class PostService extends BaseService<PostMapper, Post> {
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
	public List<Map<String, Object>> listPostNotInDeptid(Map<String, Object> post) {
		
		return baseMapper.listPostNotInDeptid(post);
	}

    /** 请在此类添加自定义函数 */

}

