package com.mdp;

import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    public static String WHERE_REG="^(?i) ?WHERE ";

    public static String ORDER_BY_REG="(?i) ?ORDER BY ((?!.*[)])|(?!.* ORDER BY )).*$";

    public static String GROUP_BY_REG="(?i) ?GROUP BY ((?!.*[)])|(?!.* GROUP BY )).*$";

    public static String trimWhere(String sql){
        if(sql!=null){
            sql=sql.replaceAll(WHERE_REG,"");
            sql=sql.replaceAll(ORDER_BY_REG,"");
            sql=sql.replaceAll(GROUP_BY_REG,"");
            return sql;
        }else {
            return "";
        }
    }
    public static String trimOrderBy(String sql,String defaultOrderBy){
        String defVal= StringUtils.hasText(defaultOrderBy)?defaultOrderBy:"";
        if(sql!=null){
            Pattern pattern=Pattern.compile(ORDER_BY_REG);
            Matcher matcher=pattern.matcher(sql);
            while (matcher.find()){
                String sql2=matcher.group();
                sql2=sql2.replaceAll(GROUP_BY_REG,"");
                return sql2;
            }
            return defVal;
        }else {
            return defVal;
        }
    }
    public static String trimGroupBy(String sql,String defaultGroupBy){
        String defVal= StringUtils.hasText(defaultGroupBy)?defaultGroupBy:"";
        if(sql!=null){
            Pattern pattern=Pattern.compile(GROUP_BY_REG);
            Matcher matcher=pattern.matcher(sql);
            while (matcher.find()){
                return matcher.group();
            }
            return defVal;
        }else {
            return defVal;
        }
    }
    public static void main(String[] args) {
        String sql="WHERE ((id = ?) order by xxxxx ) Order By aaa desc group by dddd";
        System.out.println("sql==>"+ trimWhere(sql));

        String sql2="WHERE ((id = ?)  )  group by dddd";
        System.out.println("sql==>"+ trimOrderBy(sql2," order by res.time_ asc"));


        String sql3="WHERE ((id = ?) order by xxxxx ) Order By aaa desc group by dddd";
        System.out.println("sql==>"+ trimGroupBy(sql3,""));




    }
}
