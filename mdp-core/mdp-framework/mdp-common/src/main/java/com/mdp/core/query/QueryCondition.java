package com.mdp.core.query;

import lombok.Data;

import java.io.Serializable;

@Data
public class QueryCondition implements Serializable {

	private static final long serialVersionUID = 4740166316629191651L;
	/**
	 * 条件编号（主键）
	 */
	String cid;
	/**
	 * 属性名
	 */
	private String property;
	/**
	 * 对应的数据库字段的类型
	 * 支持：int、bigDecimal、short、long、float、double、boolean、string
	 */
	private String colType;
	/**
	 * 默认eq gt/lt/ge/le/eq/ne/left_like/right_like/like/in/nin/between/sql
	 */
	private String sqlOper;
	/**
	 * children中条件连接符 and or，默认and
	 */
	private String sqlLink;

	/**
	 * 如果是in,not in这里以逗号分隔多个,
	 * 如果是between,这里存第一个值，
	 */
	private Object sqlVal;
	/**
	 * 如果是between,这里填结束值
	 */
	Object endVal;
	/**
	 * 前缀，假设存在重复字段，需要通过前缀区分，比如res.branchId=xxxx.
	 * 此处填写 res.
	 */
	String colPrefix;

	/**
	 * 子条件，如果存在children，本实例中只需要link 有值
	 */
	QueryCondition[] children;

}
