package com.mdp.qx;

import java.lang.annotation.*;

/**
 * 在方法上使用，用于权限控制
 * 拥有指定角色之一才可以访问
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HasRole {
	
	/**
	 * 角色编号列表
	 * */
	String[] roles() default {"user"};
	
}