package com.mdp.qx;

import java.lang.annotation.*;


@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
/**
 * 在方法或者类上使用，用于权限控制，检查当前登录机构号是否开通了指定的模块，如果未开通，则拒绝访问，
 * 如果已开通，则根据配置 检查当前登录用户是否有该模块的访问权限
 * <br>使用举例： @HasModule(value="mdp-sys",checkUser=true)
 */
public @interface HasModule {

	/**
	 * 模块编号
	 */
	String[] value() default {};

}