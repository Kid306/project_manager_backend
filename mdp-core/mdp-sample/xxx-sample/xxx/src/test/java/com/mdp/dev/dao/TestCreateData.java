package com.mdp.dev.dao;

import com.mdp.dev.entity.CodeGenField;
import junit.framework.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class TestCreateData  {
	
	GenDataDao genDataDao=new GenDataDao();
	@Test
	public void createDataForString(){
		CodeGenField fieldInfo=new CodeGenField();
		fieldInfo.setFullJavaClassName("java.lang.String");
		fieldInfo.setColumnSize(5);
		fieldInfo=genDataDao.createTestData(fieldInfo);
		System.out.println(fieldInfo.getTestValue());
		Assert.assertNotNull(fieldInfo.getTestValue());
	}
	

	
	@Test
	public void createDataForDate(){
		CodeGenField fieldInfo=new CodeGenField();
		fieldInfo.setFullJavaClassName(Date.class.getName());
		fieldInfo.setColumnSize(5);
		fieldInfo=genDataDao.createTestData(fieldInfo);
		System.out.println(fieldInfo.getTestValue());
		Assert.assertNotNull(fieldInfo.getTestValue());
	}
	
	@Test
	public void createDataForBoolean(){
		CodeGenField fieldInfo=new CodeGenField();
		fieldInfo.setFullJavaClassName(boolean.class.getName());
		fieldInfo.setColumnSize(5);
		fieldInfo=genDataDao.createTestData(fieldInfo);
		System.out.println(fieldInfo.getTestValue());
		Assert.assertNotNull(fieldInfo.getTestValue());
	}
	@Test
	public void createDataForInt(){
		CodeGenField fieldInfo=new CodeGenField();
		fieldInfo.setFullJavaClassName(int.class.getName());
		fieldInfo.setColumnSize(10);
		fieldInfo=genDataDao.createTestData(fieldInfo);
		System.out.println(fieldInfo.getTestValue());
		Assert.assertNotNull(fieldInfo.getTestValue());
	}
	
	@Test
	public void createDataForShort(){
		CodeGenField fieldInfo=new CodeGenField();
		fieldInfo.setFullJavaClassName(short.class.getName());
		fieldInfo.setColumnSize(3);
		fieldInfo=genDataDao.createTestData(fieldInfo);
		System.out.println(fieldInfo.getTestValue());
		Assert.assertNotNull(fieldInfo.getTestValue());
	}
	@Test
	public void createDataForFloat(){
		CodeGenField fieldInfo=new CodeGenField();
		fieldInfo.setFullJavaClassName(float.class.getName());
		fieldInfo.setColumnSize(5);
		fieldInfo.setDecimalDigits(2);
		fieldInfo=genDataDao.createTestData(fieldInfo);
		System.out.println(fieldInfo.getTestValue());
		Assert.assertNotNull(fieldInfo.getTestValue());
	}
	
	@Test
	public void createDataForDouble(){
		CodeGenField fieldInfo=new CodeGenField();
		fieldInfo.setFullJavaClassName(double.class.getName());
		fieldInfo.setColumnSize(5);
		fieldInfo.setDecimalDigits(2);
		fieldInfo=genDataDao.createTestData(fieldInfo);
		System.out.println(fieldInfo.getTestValue());
		Assert.assertNotNull(fieldInfo.getTestValue());
	}
	
	@Test
	public void createDataForLong(){
		CodeGenField fieldInfo=new CodeGenField();
		fieldInfo.setFullJavaClassName(long.class.getName());
		fieldInfo.setColumnSize(5);
		fieldInfo.setDecimalDigits(2);
		fieldInfo=genDataDao.createTestData(fieldInfo);
		System.out.println(fieldInfo.getTestValue());
		Assert.assertNotNull(fieldInfo.getTestValue());
	}
	
	@Test
	public void createDataForBigDecimal(){
		CodeGenField fieldInfo=new CodeGenField();
		fieldInfo.setFullJavaClassName(BigDecimal.class.getName());
		fieldInfo.setColumnSize(5);
		fieldInfo.setDecimalDigits(2);
		fieldInfo=genDataDao.createTestData(fieldInfo);
		System.out.println(fieldInfo.getTestValue());
		Assert.assertNotNull(fieldInfo.getTestValue());
	}
	
	@Test
	public void createBatchData(){
		List<List<CodeGenField>> table=createTableWithTestValue(10);
		genDataDao.putTestValueToTable(table);
		Assert.assertNotSame(table.get(0).get(0).testValue, table.get(0).get(1).testValue);
		Assert.assertNotSame(table.get(0).get(0).testValue, table.get(1).get(0).testValue);
	}
	
	public List<List<CodeGenField>>   createTableWithTestValue(int rows){
		List<List<CodeGenField>> table=new ArrayList<List<CodeGenField>>();
			CodeGenField fieldInfo1=new CodeGenField();
			fieldInfo1.setFullJavaClassName(BigDecimal.class.getName());
			fieldInfo1.setColumnSize(5);
			fieldInfo1.setDecimalDigits(2);
			
			CodeGenField fieldInfo2=new CodeGenField();
			fieldInfo2.setFullJavaClassName(Date.class.getName());
			fieldInfo2.setColumnSize(5);
			fieldInfo2.setDecimalDigits(2);
			
			ArrayList<CodeGenField> row1=new ArrayList<CodeGenField>();
			row1.add(fieldInfo1);
			row1.add(fieldInfo2);
			table=genDataDao.createTableWithTestValue(rows, row1);
			
		return table;
	}
	
	@Test
	public void byteClass2(){
		String className=byte[].class.getName();
		System.out.println(className);
		//Assert.assertEquals("date", className);
	}
	@Test
	public void randomValue(){
		Random r=new Random();
		
		Object testValue=r.nextInt((int) Math.pow(10, 2));
		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setGroupingUsed(false);
		nf.setMaximumFractionDigits(0);
		nf.setMaximumIntegerDigits(5);
		testValue=nf.format(testValue);
		System.out.println(testValue);
		System.out.println(r.nextDouble());
		System.out.println(r.nextFloat());
		System.out.println(r.nextLong());
		System.out.println(r.nextInt());
		System.out.println(r.nextInt(5));
		long l=r.nextLong();
		nf.setGroupingUsed(false);
		nf.setMaximumFractionDigits(0);
		nf.setMaximumIntegerDigits(5);
		System.out.println(nf.format(l));
		for (int i = 0; i < 10; i++) {
			l=r.nextLong();
			nf.setGroupingUsed(false);
			nf.setMaximumFractionDigits(1);
			nf.setMaximumIntegerDigits(5);
			System.out.println(l+":"+nf.format(l));
		}
		
	}

}
