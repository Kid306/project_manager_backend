package com.mdp.dev.entity;

import com.mdp.core.JdbcTypeMapping;
import com.mdp.core.entity.DmField;
import com.mdp.core.utils.EntityUtils;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.support.JdbcUtils;

@Data
public class CodeGenField extends DmField {

	private String tableRemarks="";
 	private String chinaName;//字段的所属类的中文名字,来自字段中文名或者字段注释.
	public String fullJavaClassName;//字段的所属类的全名,包括包名 如java.lang.String
	public String simpleJavaClassName;//字段的所属类的名名,不包括包名 如String
	public String bigColumnName;//首字母大写
	public String camelsColumnName;//驼峰命名法
	public String bigCamelsColumnName;//首字母大写的驼峰命名法
	public String colType;//前端数据类型，一般指js,通过simpleJavaClassName进行转换


    /**
     * 指示此列是否是自动递增
     * YES -- 该列是自动递增的
     * NO -- 该列不是自动递增
     * 空字串--- 不能确定该列是否自动递增
     */
    //String isAutoincrement = rs.getString("IS_AUTOINCREMENT");   //该参数测试报错

	public Object testValue;



	public CodeGenField() {}


	public void setColumnName(String columnName) {
		this.columnName = columnName;
		this.bigColumnName=columnName.substring(0, 1).toUpperCase()+this.columnName.substring(1);
		this.camelsColumnName=JdbcUtils.convertUnderscoreNameToPropertyName(columnName);
		this.bigCamelsColumnName=camelsColumnName.substring(0, 1).toUpperCase()+camelsColumnName.substring(1);
	}
	public void setColumnSize(int columnSize) {
		if(columnSize>=1000){
			this.columnSize=1000;
			return;
		}
		this.columnSize = columnSize;
	}

	public void setChinaName(String chinaName) {
		if(chinaName==null){
			this.chinaName="";
		}else{
			this.chinaName = chinaName.replaceAll("[ \\t\\n\\x0B\\f\\r]\\S*", "");
		}
	}
 

	public static void main(String[] args) {
		CodeGenField fi=new CodeGenField();
		fi.setColumnName("_T__CC_DD_");
		System.out.println(fi.camelsColumnName + "------"+fi.bigCamelsColumnName);
	}


	public void setTableRemarks(String tableRemarks) {
		if(tableRemarks==null){
			this.tableRemarks="";
		}else{
 			this.tableRemarks=tableRemarks.replaceAll("[ \\t\\n\\x0B\\f\\r]\\S*", "");
		}
	}

	public static CodeGenField fromTableField(DmField tableField){
		CodeGenField cgf=new CodeGenField();
		BeanUtils.copyProperties(tableField,cgf);
		cgf.setFullJavaClassName(JdbcTypeMapping.jdbcType2javaType(tableField.getDataTypeName()).getName());
		cgf.setSimpleJavaClassName(JdbcTypeMapping.jdbcType2javaType(tableField.getDataTypeName()).getSimpleName());
		if(tableField.columnSize>100000){
			cgf.setColumnSize(100000);
		}else{
			cgf.setColumnSize(tableField.columnSize);
		}

		cgf.setColumnName(tableField.columnName);
		cgf.setChinaName(EntityUtils.parseFieldName(tableField.getRemarks()));
		return cgf;
	}


	
}
