package com.mdp.qx;

import java.lang.annotation.*;

/**
 * 在方法上使用，用于控制数据范围访问控制
 * 拥有指定数据级别才允许访问
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented 
public @interface HasDataLvl {
	
	/**	要求操作者拥有的最低的级别，，与min一样的逻辑:<br>
	 *  
	 * <br>DataLvl.alowAll 可以操作任何机构数据不受限制
	 * <br>DataLvl.branch 只要同机构即有权限操作
	 * <br>DataLvl.p2 上两级部门及本部门有权限操作
	 * <br>DataLvl.p1 上一级部门及本部门有权限操作
	 * <br>DataLvl.subDept 本部门及下属部门数据
	 * <br>DataLvl.myDept 可以操作本部门数据，不包括下属部门
	 * 
	 * <br>级别高低：
	 *  <br>allowAll > branch > p2 >p1 > subDept >myDept
	 *  
	 *  <br>使用举例：
	 *  <br>如 HasDataLvl( DataLvl.myDept ) 代表 操作者最少拥有DataLvl.myDept级别的权限才可以通过验证，如果拥有subDept等更高级别的也可以通过验证。
	 * */
	DataLvl value() default DataLvl.noDef;

}