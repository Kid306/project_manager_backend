package com.mdp.core.api;

import java.util.List;
 
  
/***
 * 序列号格式化工具，如给定 {time}{date:yyyyMMddHHmmssS}{rand:2}  结果为：14768373247162016101908352471601<br>
 * {time}                  					--> 1476837324716<br>
 * {date:yyyyMMddHHmmssS}  					--> 20161019083524716<br>
 * {date62:yyyyMMddHHmmssS}                 --> aldkfdjfkgjla 将日期按62进制表示{DATE62:yyyyMMddHHmmssSS} <br>
 * {rand:2}               					--> 01<br>
 * {time}{date:yyyyMMddHHmmssS}{rand:2}		--> 14768373247162016101908352471601<br>
 * 
 **/
public interface Sequence { 
	
	/**
	 * 资金账号生成
	 * @return
	 */
	public String getAccountId();
	
	/**
	 * 用户账号生成
	 * @return
	 */
	public String getUserid(); 
	/**
	 * 交易流水号 －本系统内部使用
	 * @return
	 */
	public String getTxFlowNo();
	/***
	 * 全局事务跟踪号，用于在各个系统中流转，保持交易的连贯性。
	 * @return
	 */
	public String getGlobalFlowNo(); 
	
	/**
	 * 生成某个表对应某个字段的序号
	 * @param tableName
	 * @param colunm
	 * @return
	 */
	public String getTablePK(String tableName, String colunm);

	/**
	 * 生成机构编号序列号
	 * @return
	 */
	public String getBranchId();
 
	 /**
	  * 批量生成一批序列号
	  * @param tableName
	  * @param colunm
	  * @param count
	  * @return
	  */
	public List<String> batchGenerate(String tableName, String colunm,int count); 
	
	public void clear();
	 
	public String getSessionKey() ; 
	
	public  String getReqFlowNo() ;
	

	public String getSmsCode(String phoneNo,int length);
	public boolean validateSmsCode(String phoneNo,String code) ;
	/***
	 * 序列号格式化工具，如给定 {time}{date:yyyyMMddHHmmssS}{rand:2}  结果为：14768373247162016101908352471601<br>
	 * {time}                  					--> 1476837324716<br>
	 * {date:yyyyMMddHHmmssS}  					--> 20161019083524716<br>
	 * {date62:yyyyMMddHHmmssS}                 --> aldkfdjfkgjla 将日期按62进制表示{DATE62:yyyyMMddHHmmssSS} <br>
	 * {rand:2}               					--> 01<br>
	 * {time}{date:yyyyMMddHHmmssS}{rand:2}		--> 14768373247162016101908352471601<br>
	 * 
	 **/
	public String getCommonNo(String format);
}
