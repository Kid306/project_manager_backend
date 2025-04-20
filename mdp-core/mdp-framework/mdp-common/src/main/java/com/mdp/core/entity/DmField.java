package com.mdp.core.entity;

import lombok.Data;

@Data
public class DmField {
 	public String tableName;  //表名
	public String columnName;  //列名  
	public int dataType ;     //对应的java.sql.Types的SQL类型(列类型ID)     
	public String dataTypeName ;  //java.sql.Types类型名称(列类型名称)
	public int columnSize ;  //列大小  
	public int decimalDigits;  //小数位数 
	public int numPrecRadix ;  //基数（通常是10或2） --未知
    /**
     *  0 (columnNoNulls) - 该列不允许为空
     *  1 (columnNullable) - 该列允许为空
     *  2 (columnNullableUnknown) - 不确定该列是否为空
     */
	public int nullAble ;  //是否允许为null  
	public String remarks="";  //列描述
	public String columnDef;  //默认值  
	public int charOctetLength ;    // 对于 char 类型，该长度是列中的最大字节数 
	public int ordinalPosition ;   //表中列的索引（从1开始）  
    /** 
     * ISO规则用来确定某一列的是否可为空(等同于NULLABLE的值:[ 0:'YES'; 1:'NO'; 2:''; ])
     * YES -- 该列可以有空值; 
     * NO -- 该列不能为空;
     * 空字符串--- 不知道该列是否可为空
     */  
	public String isNullAble ;  
      
    /** 
     * 指示此列是否是自动递增 
     * YES -- 该列是自动递增的
     * NO -- 该列不是自动递增
     * 空字串--- 不能确定该列是否自动递增
     */  
    //String isAutoincrement = rs.getString("IS_AUTOINCREMENT");   //该参数测试报错    

	public boolean isPk=false;

	public DmField() {}

	public void setRemarks(String remarks) {
		if(remarks==null){
			this.remarks="";
		}else{
			this.remarks = remarks.replaceAll("[ \\t\\n\\x0B\\f\\r]\\S*", "");
		}
		
	}
	
}
