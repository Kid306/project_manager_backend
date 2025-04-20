package com.mdp.qx;

import java.lang.annotation.*;

/**
 * 在方法上使用，用于权限控制，拥有指定的权限编码，才允许访问
 * 尽量补齐 权限名称/权限分类编号/权限分类名称，系统启动后会自动同步到数据库权限表
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
/**
 * 注解在方法上，将当用户访问该方法时，将检查用户是否拥有qxId指定的权限码。有则放行，无则拒绝访问
 * <br>系统在启动时会检查所有有hasQx注解的信息同步qxId,qxName，categoryId三个字段信息到 数据库adm.sys_qx中，无需后台手工配置，
 * <br>因此，注解请尽量写明白qxName的含义，请确保后套已经配置对应的菜单分类
 * <br>使用举例： @HasQx(value="sys_user_add",name="新增用户管理",categoryId="adm_sys",categoryName="组织管理")
 */
public @interface HasQx {

	/**
	 * 权限编码，全局唯一，一般以url进行短编码 如url= sys/user/add -> sys_user_add
	 * <br>请尽量编码足够细分，防止根系统中其它权限编码冲突
	 * <br>对应 adm.sys_qx.qx_id字段
	 * @return
	 */
	String  value();

	/**
	 * 权限名称，一般以本交易/或者方法的实际功能名字
	 * 如 新增后台管理用户、修改用户的角色等
	 * <br>对应 adm.sys_qx.qx_name 字段
	 * @return
	 */
	String name() default "";

	/**
	 * 权限归属模块编号 关联模块表，请确保后台中已配置了相应模块
	 * <br>对应 adm.sys_qx.moduleId 字段
	 * @return
	 */
	String moduleId() default "common";

	/**
	 * 模块分类名称
	 * @return
	 */
	String moduleName() default "通用";


}