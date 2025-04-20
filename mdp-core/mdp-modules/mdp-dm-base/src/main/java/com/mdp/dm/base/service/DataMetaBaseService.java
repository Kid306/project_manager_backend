package com.mdp.dm.base.service;

import com.mdp.core.entity.DmField;
import com.mdp.core.entity.DmTable;

import java.util.List;


/**
 * 
 * @author cyc 将entity的名字改成符合驼峰命名规范的名字 20150215
 * @see org.springframework.jdbc.support.JdbcUtils
 *
 */
public interface DataMetaBaseService {

	public List<DmField> getColumnsInfoFirstCache(String ds, String tableName);
	public List<DmField> getColumnsInfoBySqlTplFirstCache(String ds, String sql,List<Object> varValues);

	public List<DmField> getColumnsInfoBySqlFirstCache(String ds, String sql);

	public List<DmField> getColumnsInfo(String ds,String tableName);

	/**
	 *	获取sql语句的列信息
	 */
	public  List<DmField> getColumnsInfoBySql(String ds,String dsSql);
	/**
	 *	获取sql语句的列信息
	 */
	public  List<DmField> getColumnsInfoBySqlTpl(String ds,String dsSql,List<Object> varValues);

	/**
	 * 获得所有表列表
	 * @param ds
	 * @param tableName
	 * @return
	 */
	public List<DmTable> getTableList(String ds, String tableName);


	/**
	 * 获得一个表的信息
	 * @param ds
	 * @param tableName
	 * @return
	 */
	public  DmTable getTable(String ds, String tableName);

	/**
	 * 获得表的主键列表
	 * @param ds
	 * @param tableName
	 * @return
	 */
	public List<String> getPrimaryKeys(String ds,String tableName);
}