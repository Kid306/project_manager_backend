package com.mdp.safe.client.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 组织 com.qqkj  顶级模块 mdp 大模块 menu  小模块 <br> 
 * 实体 MenuDef所有属性名: <br>
 *	id,pid,accUrl,msort,category,icon,mname,rpath,rid,isShow,menuType,operQxId;<br>
 * 表 ADMIN.menu_def 前端功能菜单表的所有字段名: <br>
 *	id,pid,acc_url,msort,category,icon,mname,rpath,rid,is_show,menu_type,oper_qx_id;<br>
 * 当前主键(包括多主键):<br>
 *	id;<br>
 */
@Schema(description="前端功能菜单表")
@Data
public class Menu implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId
	@Schema(description="菜单编号,主键")
	String id;
  	
	
	@Schema(description="上级菜单编号")
	String pid;
	
	@Schema(description="访问路径")
	String accUrl;
	
	@Schema(description="菜单顺序")
	String msort;
	
	@Schema(description="菜单图标")
	String icon;
	
	@Schema(description="菜单名称")
	String mname;
	
	@Schema(description="路由PATH")
	String rpath;
	
	@Schema(description="路由编号")
	String rid;
	
	@Schema(description="是否显示")
	String isShow;
	
	@Schema(description="菜单类型1菜单2按钮")
	String menuType;

	/**
	 * 支持版本0-免费版可用，1-企业版。假如企业属于企业版，则可以适用免费版和企业版的功能。假如企业为免费版，则只可以使用免费版功能
	 */
	String supVers;

	/**
	 *  菜单大类-指向menu_module.mcate
	 */
	String mcate;

	/**
	 * 模块编号-指向menu_module.id
	 */
	String moduleId;

	/**
	 * 权限类型c-新增，r-读，u-更新，d-删除，p-打印
	 */
	String qxType;

	/**
	 * 对应后台的url地址，支持ant表达式匹配，用于后台接口调用时权限控制,多个逗号分隔,多个为or关系
	 */
	String apiRules;

}