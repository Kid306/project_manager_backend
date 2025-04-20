package com.mdp.safe.client.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 组织 com.qqkj  顶级模块 mdp 大模块 sys  小模块 <br> 
 * 实体 Qx所有属性名: <br>
 *	qxId,qxName,categoryId;<br>
 * 表 ADMIN.sys_qx 权限定义的所有字段名: <br>
 *	qx_id,qx_name,category_id;<br>
 * 当前主键(包括多主键):<br>
 *	qx_id;<br>
 */
@Data
public class Qx  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId
	/**
	 * 权限编号,主键
	 */
	String qxId;
  	
	/**
	 * 权限名称
	 */ 
	String qxName;

	/**
	 * 权限归属模块编号
	 */
	String moduleId;

	/**
	 * 权限类型
	 */
	String qxType;

	/**
	 * 权限sql
	 */
	String qxSql;

	/**
	 * 权限表达式
	 */
	String qxRegex;
}