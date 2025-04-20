package com.mdp.audit.log.client.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 记录操作日志
 * 
 * 如
 * 
 * @Audit(firstMenu="组织管理",secondMenu="部门管理",func="查询",funcDesc="查询部门列表",OperType=OperType.SELECT) 
 * 
 * @Author qqkj
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuditLog {
	/**
	 * 分类编号 可以等同于系统编号等，在 后台菜单分类中配置
	 * @return
	 */
	String categoryId() default "commmon";
	/**
	 * 分类编号 可以等同于系统名称等，在 后台菜单分类中配置
	 * @return
	 */
	String categoryName() default "通用系统";

	/**
	 * 一级菜单/模块名称
	 * @return
	 */
	String firstMenu() default "";

	/**
	 * 二级菜单/模块名称
	 * @return
	 */
	String secondMenu() default "";

	/**
	 * 功能编号，建议与权限编号/交易码编号等一致，方便互相关联跟踪
	 * @return
	 */
	String func () default "";

	/**
	 * 功能名称/描述等
	 * @return
	 */
	String funcDesc() default "";

	/**
	 * 功能属于增删改查等中的哪一类
	 * @see OperType
	 * @return
	 */
	OperType operType() default OperType.SELECT;

}
