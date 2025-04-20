package com.mdp.dev.dao;

import com.mdp.dev.entity.CodeGenField;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class GenDataDao {
	
	
	public CodeGenField createTestData(CodeGenField field){
		Random r = new Random();
		Object testValue="";
		if(String.class.getName().equalsIgnoreCase(field.fullJavaClassName)){
			testValue=getRandomString(field.columnSize>4?4:field.columnSize);
		}else if(Date.class.getName().equalsIgnoreCase(field.fullJavaClassName)){
			Calendar c1 = Calendar.getInstance();
	        c1.setTime(new Date());
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd H:m:s");
	        testValue=format.format(c1.getTime());
		}else if(int.class.getName().equalsIgnoreCase(field.fullJavaClassName)){
			testValue=r.nextInt((int) Math.pow(10, field.columnSize>4?4:field.columnSize));
			NumberFormat nf = NumberFormat.getNumberInstance();
			nf.setGroupingUsed(false);
			nf.setMaximumFractionDigits(field.decimalDigits);
			nf.setMaximumIntegerDigits(field.columnSize>4?4:field.columnSize);
			testValue=nf.format(testValue);
		}else if(Integer.class.getName().equalsIgnoreCase(field.fullJavaClassName)){
			testValue=r.nextInt((int) Math.pow(10, field.columnSize>4?4:field.columnSize));
			NumberFormat nf = NumberFormat.getNumberInstance();
			nf.setGroupingUsed(false);
			nf.setMaximumFractionDigits(field.decimalDigits);
			nf.setMaximumIntegerDigits(field.columnSize>4?4:field.columnSize);
			testValue=nf.format(testValue);
		}else if(short.class.getName().equalsIgnoreCase(field.fullJavaClassName)){
			testValue=r.nextInt((int) Math.pow(10, field.columnSize>4?4:field.columnSize));
			NumberFormat nf = NumberFormat.getNumberInstance();
			nf.setGroupingUsed(false);
			nf.setMaximumFractionDigits(field.decimalDigits);
			nf.setMaximumIntegerDigits(field.columnSize>4?4:field.columnSize);
			testValue=nf.format(testValue);
		}else if(BigDecimal.class.getName().equalsIgnoreCase(field.fullJavaClassName)){
			BigDecimal value=BigDecimal.valueOf(r.nextDouble()*(int) Math.pow(10, field.columnSize>4?4:field.columnSize));
			NumberFormat nf = NumberFormat.getNumberInstance();
			nf.setGroupingUsed(false);
			nf.setMaximumFractionDigits(field.decimalDigits);
			nf.setMaximumIntegerDigits(field.columnSize>4?4:field.columnSize);
			testValue=nf.format(value);
		}else if(long.class.getName().equalsIgnoreCase(field.fullJavaClassName)){
			testValue=r.nextInt((int) Math.pow(10, field.columnSize>4?4:field.columnSize));
			NumberFormat nf = NumberFormat.getNumberInstance();
			nf.setGroupingUsed(false);
			nf.setMaximumFractionDigits(field.decimalDigits);
			nf.setMaximumIntegerDigits(field.columnSize>4?4:field.columnSize);
			testValue=nf.format(testValue);
		}else if(float.class.getName().equalsIgnoreCase(field.fullJavaClassName)){
			float value=r.nextFloat()*(int) Math.pow(10, field.columnSize>4?4:field.columnSize);
			NumberFormat nf = NumberFormat.getNumberInstance();
			nf.setGroupingUsed(false);
			nf.setMaximumFractionDigits(field.decimalDigits);
			nf.setMaximumIntegerDigits(field.columnSize>4?4:field.columnSize);
			testValue=nf.format(value);
		}else if(double.class.getName().equalsIgnoreCase(field.fullJavaClassName)){
			double value=r.nextDouble()*(int) Math.pow(10, field.columnSize>4?4:field.columnSize);
			NumberFormat nf = NumberFormat.getNumberInstance();
			nf.setGroupingUsed(false);
			nf.setMaximumFractionDigits(field.decimalDigits);
			nf.setMaximumIntegerDigits(field.columnSize>4?4:field.columnSize);
			testValue=nf.format(value);
		}else if(byte[].class.getName().equalsIgnoreCase(field.fullJavaClassName)){
			testValue=getRandomString(field.columnSize>4?4:field.columnSize);
		}else if(boolean.class.getName().equalsIgnoreCase(field.fullJavaClassName)){
			testValue=r.nextBoolean();
		}else{
			testValue=getRandomString(field.columnSize>4?4:field.columnSize);
		}
		field.testValue=testValue;
		return field;
	}

	public void putTestValueToTable(List<List<CodeGenField>> table ){
		for (int i = 0; i < table.size(); i++) {
			List<CodeGenField> row=table.get(i);
			for (int j = 0; j < row.size(); j++) {
				CodeGenField field=row.get(j);
				field=createTestData(field);
			}
		}
	}
	
	public List<List<CodeGenField>>   createTableWithTestValue(int rows, List<CodeGenField> row){
		List<List<CodeGenField>> table=new ArrayList<List<CodeGenField>>();
		
		for (int i = 0; i < row.size(); i++) {
			CodeGenField fieldInfo=row.get(i);
			fieldInfo=createTestData(fieldInfo);
		}
		table.add(row);
		for (int i = 0; i < rows; i++) {
			ArrayList<CodeGenField> temprow=new ArrayList<CodeGenField>();
			for (int j = 0; j <row.size(); j++) {
				CodeGenField o=row.get(j);
				CodeGenField n=new CodeGenField();
				n.bigColumnName=o.bigColumnName;
				n.setColumnName(o.getColumnName());
				n.columnSize=o.columnSize;
				n.dataTypeName=o.dataTypeName;
				n.decimalDigits=o.decimalDigits;
				n.fullJavaClassName=o.fullJavaClassName;
				n.simpleJavaClassName=o.simpleJavaClassName;
				n=createTestData(n);
				temprow.add(n);
			}
			table.add(temprow);
		}
		return table;
	}
	public static String getRandomString(int length) { //length表示生成字符串的长度  
	    String base = "abcdefghijklmnopqrstuvwxyz0123456789";
	    base=base.toUpperCase()+base;
	    int strLength=base.length();
	    Random random = new Random();     
	    StringBuffer sb = new StringBuffer();     
	    for (int i = 0; i < length; i++) {     
	        int number = random.nextInt(strLength);     
	        sb.append(base.charAt(number));     
	    }     
	    return sb.toString();     
	 }     
}
