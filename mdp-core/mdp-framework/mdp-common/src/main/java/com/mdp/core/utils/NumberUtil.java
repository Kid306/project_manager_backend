package com.mdp.core.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberUtil {



	/**
	 * 比较b1 是否 大于 b2
	 * @param b1
	 * @param b2
	 * @param scale 保留小数位数进行比较
	 * @return
	 */
	public static boolean gt(BigDecimal b1,BigDecimal b2,int scale){
		return b1.setScale(scale, RoundingMode.HALF_UP).compareTo(b2.setScale(scale,RoundingMode.HALF_UP))>0;
	}
	/**
	 * 比较b1 是否 小于 b2
	 * @param b1
	 * @param b2
	 * @param scale 保留小数位数进行比较
	 * @return
	 */
	public static boolean lt(BigDecimal b1,BigDecimal b2,int scale){
		return b1.setScale(scale,RoundingMode.HALF_UP).compareTo(b2.setScale(scale,RoundingMode.HALF_UP))<0;
	}
	/**
	 * 比较 b1 >= b2 ?
	 * @param b1
	 * @param b2
	 * @param scale 保留小数位数进行比较
	 * @return
	 */
	public static boolean ge(BigDecimal b1,BigDecimal b2,int scale){
		return b1.setScale(scale,RoundingMode.HALF_UP).compareTo(b2.setScale(scale,RoundingMode.HALF_UP))>=0;
	}
	/**
	 * 比较 b1 <= b2 ?
	 * @param b1
	 * @param b2
	 * @param scale 保留小数位数进行比较
	 * @return
	 */
	public static boolean le(BigDecimal b1,BigDecimal b2,int scale){
		return b1.setScale(scale,RoundingMode.HALF_UP).compareTo(b2.setScale(scale,RoundingMode.HALF_UP))<=0;
	}
	
	public static BigDecimal getBigDecimal(Object v){
		if(v==null){
			return null;
		}
		BigDecimal amount=BigDecimal.ZERO;
		Object amountObj=v;
		if(amountObj instanceof BigDecimal){ 
			return (BigDecimal) amountObj;
		}else if(amountObj instanceof Double){
			amount=new BigDecimal(amountObj.toString());
		}else if(amountObj instanceof Long){
			amount=new BigDecimal(amountObj.toString());
		}else if(amountObj instanceof Integer){
			amount=new BigDecimal(amountObj.toString());
		}else if(amountObj instanceof String){
			String a=(String) amountObj;
			if("".equals(a)||"null".equals(a)||"NULL".equals(a)){
				return null;
			}
			amount=new BigDecimal(a);
		}else {
			amount=new BigDecimal((String) amountObj);
		}
		return amount;
	}

	public static Long getLong(Object v,Long defaultValue){
		Long d=getLong(v);
		if(d==null){
			return defaultValue;
		}else {
			return d;
		}
	}

	public static  Long getLong(Object v){
		if(v==null){
			return null;
		}
		Long amount= Long.valueOf(0);
		Object amountObj=v;
		if(amountObj instanceof Integer){
			Integer d=(Integer)amountObj;
			amount=d.longValue();
		}else if(amountObj instanceof Long ){
			Long d=(Long)amountObj;
			amount=d;
		}else if(amountObj instanceof BigDecimal ){
			BigDecimal d=(BigDecimal)amountObj;
			amount=d.longValue();
		}else if(amountObj instanceof Double){
			amount=((Double)amountObj).longValue();
		}else if(amountObj instanceof String){
			String a=(String) amountObj;
			if("".equals(a)||"null".equals(a)||"NULL".equals(a)){
				return null;
			}
			amount=Long.valueOf(a);
		}else{
			amount=Long.valueOf((String) amountObj);
		}
		return amount;
	}
	public static  Integer getInteger(Object v){
		if(v==null){
			return null;
		}
		Integer amount=0;
		Object amountObj=v;
		if(amountObj instanceof Integer){
			amount=(Integer)amountObj;
		}else if(amountObj instanceof Long ){
			Long d=(Long)amountObj;
			amount=d.intValue();
		}else if(amountObj instanceof BigDecimal ){
			BigDecimal d=(BigDecimal)amountObj;
			amount=d.intValue();
		}else if(amountObj instanceof Double){
			amount=((Double)amountObj).intValue();
		}else if(amountObj instanceof String){
			String a=(String) amountObj;
			if("".equals(a)||"null".equals(a)||"NULL".equals(a)){
				return null;
			}
			amount=Integer.valueOf(a);
		}else{
			amount=Integer.valueOf((String) amountObj);
		}
		return amount;
	}
	
	public static BigDecimal getBigDecimal(Object v,BigDecimal defaultValue){
		if(v==null){
			return defaultValue;
		}
		BigDecimal data= getBigDecimal(v);
		if(data==null){
			return defaultValue;
		}else {
			return data;
		}
	}
	
	public static  Integer getInteger(Object v,Integer defaultValue) {
		if (v == null) {
			return defaultValue;
		}
		Integer data = getInteger(v);
		if (data == null) {
			return defaultValue;
		} else {
			return data;
		}
	}
}

