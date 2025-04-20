package com.mdp.core.utils;

import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestSql {

	public static void main(String[] args) {
		String sql="select * from (select * from admin.aaa where aaa=bbbb	ordEr  by a.lv-l_order";
   	 String orderByStr= "bbb asc";
   	 String orderBy="";
   	 String pattern = "\\s+order\\s+by\\s+[a-z.A-Z0-9_-]{1,}((\\s+((asc)||(desc)){1,}\\s*)||(\\s*))$";  
   	Pattern r = Pattern.compile(pattern,Pattern.CASE_INSENSITIVE);
   	 
    Matcher m = r.matcher(sql); 
 
    while (m.find( )) {
        System.out.println("Found value: " + m.group() ); 
        System.out.println(sql.substring(m.start()));  
     }  
		 if(!StringUtils.isEmpty(orderByStr)) { 
			 int i=sql.lastIndexOf(" order ");
   		 if(i>0){
   			 String subSql=sql.substring(i);
   			 if(subSql.toLowerCase().matches("\\s+order\\s+by\\s+.*")){
       			 orderBy=" ,"+orderByStr;
       		 }else{
       			 orderBy=" order by " + orderByStr;
       		 }
   		 }else{
   			 orderBy=" order by " + orderByStr;
   		 }
		 }
		 
		 StringBuffer sb=new StringBuffer();
		 sb.append(sql).append(orderBy);
		 System.out.println(sb.toString());
	}
}
