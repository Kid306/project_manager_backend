package com.mdp.core.utils;

import java.math.BigDecimal;
import java.util.Date;

public class JdbcUtils {
	/**
		JDBC 类型	Java 类型
		CHAR	String
		VARCHAR	String
		LONGVARCHAR	String
		NUMERIC	java.math.BigDecimal
		DECIMAL	java.math.BigDecimal
		BIT	boolean
		TINYINT	byte
		SMALLINT	short
		INTEGER	int
		BIGINT	long
		REAL	float
		FLOAT	double
		DOUBLE	double
		BINARY	byte[]
		VARBINARY	byte[]
		LONGVARBINARY	byte[]
		DATE	java.sql.Date 
		TIME	java.sql.Time
		TIMESTAMP	java.sql.Timestamp
		
		 * 将数据库字段类型转换成普通java类型
		 * @param columnType
		 * @return
		 */
		public static String convertColumnTypeToJavaType(String columnType){
			columnType=columnType.toLowerCase();
			String javaClassName="";
			if(columnType.contains("char")){
				javaClassName=String.class.getName();
			}else if("clob".equals(columnType)){
				javaClassName=String.class.getName();
				
			}else if("int".equals(columnType)){
				javaClassName=Integer.class.getName();
				
			}else if("smallint".equals(columnType)){
				javaClassName=Short.class.getName();
			}else if("numeric".equals(columnType)){
				javaClassName=BigDecimal.class.getName();
			}else if("number".equals(columnType)){
				javaClassName=Integer.class.getName();
			}else if("decimal".equals(columnType)){
				javaClassName=BigDecimal.class.getName();
			}else if("integer".equals(columnType)){
				javaClassName=Integer.class.getName();
			}else if("bigint".equals(columnType)){
				javaClassName=Long.class.getName();
			}else if("real".equals(columnType)){
				javaClassName=Float.class.getName();
			}else if("float".equals(columnType)){
				javaClassName=Double.class.getName();
			}else if("double".equals(columnType)){
				javaClassName=Double.class.getName();
			}else if("binary".equals(columnType)){
				javaClassName=String.class.getName();
			}else if("varbinary".equals(columnType)){
				javaClassName=String.class.getName();
			}else if("longvarbinary".equals(columnType)){
				javaClassName=byte[].class.getName();
			}else if("blob".equals(columnType)){
				javaClassName=String.class.getName();
				
			}else if("bit".equals(columnType)){
				javaClassName=boolean.class.getName();
			}else if("boolean".equals(columnType)){
				javaClassName=boolean.class.getName();
			}else if("date".equals(columnType)){
				javaClassName=Date.class.getName();
			}else if(columnType.contains("time")){
				javaClassName=Date.class.getName();
			}else{
				javaClassName=String.class.getName();
				
			}
			return javaClassName;
		}
		
		/**
		JDBC 类型	Java 类型
		CHAR	String
		VARCHAR	String
		LONGVARCHAR	String
		NUMERIC	java.math.BigDecimal
		DECIMAL	java.math.BigDecimal
		BIT	boolean
		TINYINT	byte
		SMALLINT	short
		INTEGER	int
		BIGINT	long
		REAL	float
		FLOAT	double
		DOUBLE	double
		BINARY	byte[]
		VARBINARY	byte[]
		LONGVARBINARY	byte[]
		DATE	java.sql.Date 
		TIME	java.sql.Time
		TIMESTAMP	java.sql.Timestamp
		
		 * 将数据库字段类型转换成普通java类型
		 * @param columnType
		 * @param decimalDigits
		 * @return
		 */
		public static String convertColumnTypeToJavaType(String columnType,int decimalDigits){
			columnType=columnType.toLowerCase();
			String javaClassName="";
			if(columnType.contains("char")){
				javaClassName=String.class.getName();
			}else if("clob".equals(columnType)){
				javaClassName=String.class.getName();
				
			}else if("int".equals(columnType)){
				javaClassName=Integer.class.getName();
				
			}else if("smallint".equals(columnType)){
				javaClassName=Short.class.getName();
			}else if("numeric".equals(columnType)){
				javaClassName=BigDecimal.class.getName();
			}else if("number".equals(columnType) && decimalDigits>0){
				javaClassName=BigDecimal.class.getName();
			}else if("number".equals(columnType) && decimalDigits==0){
				javaClassName=Integer.class.getName();
			}else if("decimal".equals(columnType)){
				javaClassName=BigDecimal.class.getName();
			}else if("integer".equals(columnType)){
				javaClassName=Integer.class.getName();
			}else if("bigint".equals(columnType)){
				javaClassName=Long.class.getName();
			}else if("real".equals(columnType)){
				javaClassName=Float.class.getName();
			}else if("float".equals(columnType)){
				javaClassName=Double.class.getName();
			}else if("double".equals(columnType)){
				javaClassName=Double.class.getName();
			}else if("binary".equals(columnType)){
				javaClassName=String.class.getName();
			}else if("varbinary".equals(columnType)){
				javaClassName=String.class.getName();
			}else if("longvarbinary".equals(columnType)){
				javaClassName=byte[].class.getName();
			}else if("blob".equals(columnType)){
				javaClassName=String.class.getName();
				
			}else if("bit".equals(columnType)){
				javaClassName=boolean.class.getName();
			}else if("boolean".equals(columnType)){
				javaClassName=boolean.class.getName();
			}else if("date".equals(columnType)){
				javaClassName=Date.class.getName();
			}else if(columnType.contains("time")){
				javaClassName=Date.class.getName();
			}else{
				javaClassName=String.class.getName();
				
			}
			return javaClassName;
		}
		
		/**
		 * Convert a column name with underscores to the corresponding property name using "camel case".  A name
		 * like "customer_number" would match a "customerNumber" property name.
		 * @param name the column name to be converted
		 * @return the name using "camel case"
		 */
		public static String convertUnderscoreNameToPropertyName(String name) {
			StringBuilder result = new StringBuilder();
			boolean nextIsUpper = false;
			if (name != null && name.length() > 0) {
				if (name.length() > 1 && name.substring(1,2).equals("_")) {
					result.append(name.substring(0, 1).toUpperCase());
				}
				else {
					result.append(name.substring(0, 1).toLowerCase());
				}
				for (int i = 1; i < name.length(); i++) {
					String s = name.substring(i, i + 1);
					if (s.equals("_")) {
						nextIsUpper = true;
					}
					else {
						if (nextIsUpper) {
							result.append(s.toUpperCase());
							nextIsUpper = false;
						}
						else {
							result.append(s.toLowerCase());
						}
					}
				}
			}
			return result.toString();
		}
}

