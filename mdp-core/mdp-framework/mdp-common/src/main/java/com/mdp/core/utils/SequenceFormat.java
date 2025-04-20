package com.mdp.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/***
 * 序列号格式化工具，如给定 {time}{date:yyyyMMddHHmmssS}{rand:2}  结果为：14768373247162016101908352471601<br>
 * {time}                  					--> 1476837324716<br>
 * {date:yyyyMMddHHmmssS}  					--> 20161019083524716<br>
 * {date62:yyyyMMddHHmmssS}                 --> aldkfdjfkgjla 将日期按62进制表示{DATE62:yyyyMMddHHmmssSS} <br>
 * {rand:2}               					--> 01<br>
 * {time}{date:yyyyMMddHHmmssS}{rand:2}		--> 14768373247162016101908352471601<br>
 * 
 **/
public class SequenceFormat {
	/**{time}**/
	private static final String TIME = "time";

	/**{time}**/
	private static final String TIMESTAMP = "timestamp";

	/**{DATE:yyyyMMddHHmmssSS}***/
	private static final String DATE = "date";
	/**将日期按62进制表示{DATE62:yyyyMMddHHmmssSS}***/
	private static final String DATE62 = "date62";
	/**随机数字{rand:位数}**/
	private static final String RAND = "rand";
	/**随机数字及字母{rands:位数}**/
	private static final String RANDS = "rands";

	
	public static String parse ( String input ) {
		
		Pattern pattern = Pattern.compile( "\\{([^\\}]+)\\}", Pattern.CASE_INSENSITIVE  );
		Matcher matcher = pattern.matcher(input);
		
		Date date=new Date();
		StringBuffer sb = new StringBuffer();
		
		while ( matcher.find() ) {
			
			matcher.appendReplacement(sb, SequenceFormat.getString( matcher.group( 1 ),date ) );
			
		}
		
		matcher.appendTail(sb);
		
		return sb.toString().toUpperCase();
	}
	
	/**
	 * 格式化路径, 把windows路径替换成标准路径
	 * @param input 待格式化的路径
	 * @return 格式化后的路径
	 */
	public static String format ( String input ) {
		
		return input.replace( "\\", "/" );
		
	}

	public static String parse ( String input, String filename ) {
	
		Pattern pattern = Pattern.compile( "\\{([^\\}]+)\\}", Pattern.CASE_INSENSITIVE  );
		Matcher matcher = pattern.matcher(input);
		String matchStr = null;
		

		StringBuffer sb = new StringBuffer();
		Date date=new Date();
		while ( matcher.find() ) {
			
			matchStr = matcher.group( 1 );
			if ( matchStr.indexOf( "filename" ) != -1 ) {
				filename = filename.replace( "$", "\\$" ).replaceAll( "[\\/:*?\"<>|]", "" );
				matcher.appendReplacement(sb, filename );
			} else {
				matcher.appendReplacement(sb, SequenceFormat.getString( matchStr,date ) );
			}
			
		}
		
		matcher.appendTail(sb);
		
		return sb.toString();
	}
		
	private static String getString ( String pattern ,Date date) {
		String patternStr=pattern.toLowerCase();
		// time 处理
		if ( patternStr.indexOf( SequenceFormat.TIMESTAMP ) != -1 ) {
			return SequenceFormat.getTimestamp();
		}else if ( patternStr.indexOf( SequenceFormat.TIME ) != -1 ) {
			return SequenceFormat.getTime();
		}else if ( patternStr.indexOf( SequenceFormat.DATE62 ) != -1 ) {
			return SequenceFormat.getDate62(pattern,date);
		} else if ( patternStr.indexOf( SequenceFormat.DATE ) != -1 ) {
			return SequenceFormat.getDate(pattern,date);
		} else if ( patternStr.indexOf( SequenceFormat.RANDS ) != -1 ) {
			return SequenceFormat.getStringRandom( pattern );
		} else if ( patternStr.indexOf( SequenceFormat.RAND ) != -1 ) {
			return SequenceFormat.getRandom( pattern );
		}
		
		return pattern;
		
	}

	private static String getTimestamp () {
		return System.currentTimeMillis() + "";
	}
	private static String getTime () {
		return (System.currentTimeMillis()/1000)+"";
	}
	private static String getFullYear (Date date) {
		return new SimpleDateFormat( "yyyy" ).format( date );
	}
	
	private static String getYear (Date date) {
		return new SimpleDateFormat( "yy" ).format( date );
	}
	
	private static String getMonth (Date date) {
		return new SimpleDateFormat( "MM" ).format( date );
	}
	
	private static String getDay (Date date) {
		return new SimpleDateFormat( "dd" ).format( date );
	}
	
	private static String getHour (Date date) {
		return new SimpleDateFormat( "HH" ).format( date );
	}
	
	private static String getMinute (Date date) {
		return new SimpleDateFormat( "mm" ).format( date );
	}
	
	private static String getSecond (Date date) {
		return new SimpleDateFormat( "ss" ).format( date );
	}
	private static String getDate (String pattern,Date date) {
		pattern = pattern.split( ":" )[ 1 ].trim();
		return new SimpleDateFormat(pattern).format( date );
	}
	private static String getDate62(String pattern,Date date) {
		String[] patterns = pattern.split( ":" );
		if(patterns.length>1){
			String val=new SimpleDateFormat(patterns[1]).format( date );
			val=val.replaceAll("[^\\d]","");
			return TentoN.tentoN(Long.valueOf(val), 62);
		}else{
			return TentoN.tentoN(((System.currentTimeMillis()-1723866585037L)), 62);
		}
	}
	
	
	private static String getRandom ( String pattern ) {
		
		int length = 0;
		pattern = pattern.split( ":" )[ 1 ].trim();
		
		length = Integer.parseInt( pattern );
		
		return ( Math.random() + "" ).substring( 2, length+2 ).replaceAll("0", "8");
		
	}
	private static String getStringRandom ( String pattern ) {
		
		int length = 0;
		pattern = pattern.split( ":" )[ 1 ].trim();
		
		length = Integer.parseInt( pattern );
		
		return getStringRandom(length );
		
	}
	
	public static void main(String[] args) {
		
		String pattern ="{time}";
		String pattern2 ="{date:yyyyMMddHHmmss}";
		String pattern3 ="{date:yyyyMMddHHmmssS}";
		String pattern4 ="{date62}{rands:2}{rand:2}";
		String pattern5 ="{timestamp}";
		String result=SequenceFormat.parse(pattern);
		String result2=SequenceFormat.parse(pattern2);
		String result3=SequenceFormat.parse(pattern3);
		String result4=SequenceFormat.parse(pattern4);
		String result5=SequenceFormat.parse(pattern5);
		//System.out.println(result);
		//System.out.println(result2);
		//System.out.println(result3);
		//System.out.println(result4);
		
		long a = System.currentTimeMillis();  
		System.out.println(a);
		for(int i = 0;i<1000;i++){
			result4=SequenceFormat.parse(pattern4);
			System.out.println(result4);
		}
		long b = System.currentTimeMillis();  
        System.out.println("毫秒："+(b-a));  
        System.out.println(b);
        System.out.println(Math.random());

	}
	
    //生成随机数字和字母,  
    public static String getStringRandom(int length) {  
          
        String val = "";  
        Random random = new Random();  
          
        //参数length，表示生成几位随机数  
        for(int i = 0; i < length; i++) {  
              
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";  
            //输出字母还是数字  
            if( "char".equalsIgnoreCase(charOrNum) ) {  
                //输出是大写字母还是小写字母  
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;  
                char c= (char)(random.nextInt(26) + temp);  
                if(c=='o'||c=='O'){
                	c='p';
                }else if(c=='l'||c=='L'){
                	c='k';
                }else if(c=='i' || c=='I'){
					c='h';
				}else if(c=='w' || c=='W'){
					c='v';
				}else if(c=='m' || c=='M'){
					c='k';
				}
                val+=c;
            } else if( "num".equalsIgnoreCase(charOrNum) ) {  
            	int no=random.nextInt(10);
            	if(no<2){
            		no=2;
            	}
                val += String.valueOf(no);  
            }  
        }  
        return val;  
    }  
    
    
    
}
