package com.mdp.core.utils;

import org.junit.Test;

public class TestDateUtils {
	@Test
	public  void testGetDate(){
		String dateStr1=DateUtils.getDate("yyyy-MM-dd HH:mm:ss");
		System.out.println(dateStr1);
		String dateStr2=DateUtils.getDate("yyyy-MM-dd");
		System.out.println(dateStr2);
		String dateStr3=DateUtils.getDate("yyyy-MM-dd HH");
		System.out.println(dateStr3);
		String dateStr4=DateUtils.getDate("yyyy-MM-dd HH-mm-ss");
		System.out.println(dateStr4);
	}
	
	
}
