package com.mdp.qx;

import java.lang.annotation.*;

/**	范围权限:<br>
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
 *  <br>如  DeptFilter( ignore=DataLvl.myDept )  默认 DataLvl.noDef 代表 操作者如果级别低于DataLvl.myDept，可以按 myDept 操作数据，如果操作者级别高于myDept的，按操作者拥有的级别操作数据
 *  <br>如  DeptFilter( ignore=DataLvl.subDept )   代表 操作者如果级别低于DataLvl.subDept，可以按 subDept 操作数据，如果操作者级别高于subDept的，按操作者拥有的级别操作数据
 *  <br>如  DeptFilter( ignore=DataLvl.branch )   代表 操作者如果级别低于DataLvl.branch，可以按 branch 操作数据，如果操作者级别高于branch的，按操作者拥有的级别操作数据
 *  <br>如  DeptFilter( ignore=DataLvl.alowAll )   代表 操作者可以操作任意数据，不做现在
 *  <br>如  DeptFilter( ignore=DataLvl.noDef   )  || DeptFilter( ignore=DataLvl.noDef   )  代表按操作者实际拥有的级别进行操作数据
 *  <br>如  DeptFilter( min=DataLvl.subDept   ) 默认 DataLvl.noDef 代表 操作者只能操作本级及下级单位数据
 *  <br>如  DeptFilter( onlySameBranchCheck=true   )  默认 false,代表 操作者无论处于什么等级，都可以操作本机构数据，高于 DataLvl.branch 的操作者还可以跨机构操作
 *
 *  <br>如  DeptFilter( deptFieldName=“deptid”  ) 默认 deptid 代表 从参数中获取 字段名为deptid的数据作为部门号
 *  <br>如  DeptFilter( branchFieldName=“branchId”  )  默认 branchId 代表 从参数中获取 branchId的数据作为机构号
 *  <br>如  DeptFilter( pdeptFieldName=“pdeptid”  )  默认 pdeptid 代表 从参数中获取 pdeptid 的数据作为上级机构号
 *  <br>如  DeptFilter( checkPdept=true  )  默认 false 代表 上送参数中同时存在上下级关系的部门参数时使用,配合 pdeptFieldName使用
 * */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented 
public @interface DeptFilter {
	
	/**	范围权限:<br>
	 *  
	 * <br>DataLvl.alowAll 可以操作任何机构数据不受限制
	 * <br>DataLvl.branch 只要同机构即有权限操作
	 * <br>DataLvl.p2 上两级部门及本部门有权限操作
	 * <br>DataLvl.p1 上一级部门及本部门有权限操作
	 * <br>DataLvl.subDept 本部门及下属部门数据
	 * <br>DataLvl.myDept 可以操作本部门数据，不包括下属部门
	 * 
	 * <br>级别高低：
	 *  <br> allowAll > branch > p2 >p1 > subDept >myDept
	 *  
	 *  <br>使用举例：
	 *  <br>如  DeptFilter( ignore=DataLvl.myDept )   代表 操作者如果级别低于DataLvl.myDept，可以按 myDept 操作数据，如果操作者级别高于myDept的，按操作者拥有的级别操作数据
	 *  <br>如  DeptFilter( ignore=DataLvl.subDept )   代表 操作者如果级别低于DataLvl.subDept，可以按 subDept 操作数据，如果操作者级别高于subDept的，按操作者拥有的级别操作数据
	 *  <br>如  DeptFilter( ignore=DataLvl.branch )   代表 操作者如果级别低于DataLvl.branch，可以按 branch 操作数据，如果操作者级别高于branch的，按操作者拥有的级别操作数据
	 *  <br>如  DeptFilter( ignore=DataLvl.alowAll )   代表 操作者可以操作任意数据，不做现在
	 *  <br>如  DeptFilter( ignore=DataLvl.noDef   )  || DeptFilter( ignore=DataLvl.noDef   )  代表按操作者实际拥有的级别进行操作数据
	 * */
	DataLvl ignore() default DataLvl.noDef;

	/**
	 * 操作者要求具有的最低等级要求，低于该配置的操作者不允许操作数据
	 * @return
	 */
	DataLvl min() default DataLvl.noDef;

	/**
	 * 是否只检查机构，只要同机构，无论用户什么等级，都可以操作
	 * @return
	 */
	boolean onlySameBranchCheck() default false;

	/**
	 * 当没检测到部门字段时，是否报错，
	 * true 则报错，false 则继续执行，用于后端自己实现上下级范围控制逻辑时
	 * @return
	 */
	boolean rejectOnDeptidIsNull() default true;
	
	/**部门参数名 如 deptid **/
	String deptFieldName() default "deptid"; 
	
	/**公司参数名 如branchId，当 scope=Scope.branch 时使用*/
	String branchFieldName() default "branchId"; 
	
	/**
	 * 方法上的参数索引位置 如 addDept(a0{p1,p2,p3},a1{deptid,pdeptid},a2{p4,p5,p6}),deptid参数在 a1上，index应填写1
	 * @return
	 */
	int index() default 0;
	
	/**上送参数中同时存在上下级关系的部门参数时使用,配合 pdeptFieldName使用**/
	boolean checkPdept() default false;
	
	/**当checkPdept为true时，上级部门参数名 如 pdeptid ,上送参数中同时存在上下级关系的部门参数时使用**/
	String pdeptFieldName() default "pdeptid";
}