package com.mdp.core.utils;

import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	public static String pattern="yyyy-MM-dd";
	public static String datetimePattern="yyyy-MM-dd HH:mm:ss";
	public static String utcPattern="yyyy-MM-dd'T'HH:mm:ss.SSS Z";//类似此种日期  2015-12-7T16:00:00.000Z


	public static SimpleDateFormat sdf=new SimpleDateFormat(pattern);
	
	/**
	 * 获取当前日期的公共类
	 * @return
	 */
	public static Date getNowDate(){
		Date d=new Date();
		return d;
	}
	
	
	/**
	 * 将日期按格式进行格式化,输出字符串
	 * @param date 需要格式化的日期 
	 * @param pattern 如： yyyy-MM-dd HH:mm:ss 或者 yyyy-MM-dd HH 或者  yyyy 或者 MM 或者 HH 或者 yyyyMMddHHmmss等
	 * @return 按pattern格式化的字符串日期 
	 */
	public static String format(Date date,String pattern){
		SimpleDateFormat sdf=new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
	
	/**
	 * 按默认格式返回字符串日期 yyyy-MM-dd 如 2015-10-23
	 * @return
	 */
	public static String getDate(){
		return format(getNowDate(),pattern);
	}
	
	/**
	 * 按指定格式返回当前字符串日期 
	 * @param pattern 如： yyyy-MM-dd HH:mm:ss 或者 yyyy-MM-dd HH 或者  yyyy 或者 MM 或者 HH 或者 yyyyMMddHHmmss等
	 * @return
	 */
	public static String getDate(String pattern){
		return format(getNowDate(),pattern);
	}
	
	/**
	 * 按默认格式返回字符串日期 yyyy-MM-dd HH:mm:ss 如 2015-10-23 15:30:25
	 * @return
	 */
	public static String getDatetime(){
		return format(getNowDate(),pattern);
	}
	/**
	 * 获取长整形的当前时间
	 * @return
	 */
	public static long getTime(){
		return getNowDate().getTime();
	}

	/**
	 * 将字符串类型的日期转成Date对象
	 * @param source 如2015-10-23或者 2015-10-23 15:30:25 或者 2015-12-7T16:00:00.000Z等
	 * @param pattern 格式必须与 source的格式一致，如 2015-10-23对应的 pattern为 yyyy-MM-dd, <br>
	 *        2015-10-23 15:30:25 对应的pattern 为 yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static Date parse(String source,String pattern){
		if(StringUtils.hasText(source)){
			SimpleDateFormat sdf=new SimpleDateFormat(pattern);
			try {
				return sdf.parse(source);
			} catch (ParseException e) {
			}
		}
		
		return null;
	}
	/**
	 * 将字符串类型的日期转成Date对象
	 * @param source 如2015-10-23 20:36:20
	 * @param pattern 格式必须与 source的格式一致，<br>
	 *        2015-10-23 15:30:25 对应的pattern 为 yyyy-MM-dd HH:mm:ss,本函数只支持解析此种格式
	 * @return
	 */
	public static Date parse(String source) {
		if(source==null){
			return null;
		}
		try {
			return DateFormatUtil.pareDate(source);
		}catch (ParseException e){
			throw new RuntimeException(e.getCause());
		}
	}
	
}
