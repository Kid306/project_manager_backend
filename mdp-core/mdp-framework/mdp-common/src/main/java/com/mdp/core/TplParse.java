package com.mdp.core;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 模板解析，并注入值；
 * 如输入 SELECT * FROM users WHERE id = '#{user.userid}' AND name = '#{user.username}
 * 输出  SELECT * FROM users WHERE id = 1 AND name = 'Alice'
 * 公共变量支持：
 * 当前用户        #{user.*},如 #{user.userid},#{user.username},#{user.branchId},#{user.deptid}等
 * 当前接口参数     #{*} 如 #{field1},#{field2}等
 * 当前上下文环境变量    #{env.*},#{env.date},#{env.platformBranchId}
 * #
 */
public class TplParse {


        // 正则表达式用于匹配类似 #{variableName} 的动态变量
        private static final Pattern VARIABLE_PATTERN = Pattern.compile("\\#\\{([^}]+)}");

        /**
         *
         * @param tpl
         * @param vars 变量池
         * @return
         */
        public static String parse(String tpl, Map<String,Object> vars,String wrapStr) {
            Matcher matcher = VARIABLE_PATTERN.matcher(tpl);
            StringBuffer sb = new StringBuffer();

            while (matcher.find()) {
                String variable = matcher.group(1); // 获取变量名
                Object value = ExpTools.evaluate(variable,vars); // 获取变量对应的值
                if (value != null) {
                    matcher.appendReplacement(sb, wrapStr+Matcher.quoteReplacement(value.toString())+wrapStr);
                } else {
                    //throw new IllegalArgumentException("Missing value for variable: " + variable);
                }
            }
            matcher.appendTail(sb);

            return sb.toString();
        }

    /**
     *
     * @param tpl
     * @param params 接口变量池
     * @param userAndEnv 用户及环境变量池 {user:{userid:'',username:'',...},env:{date:Date,platformBranchId:''}}
     * @return
     */
    public static String parse(String tpl, Map<String,Object> params,Map<String,Object> userAndEnv,String wrapStr) {
        Matcher matcher = VARIABLE_PATTERN.matcher(tpl);
        StringBuffer sb = new StringBuffer();

        while (matcher.find()) {
            String variable = matcher.group(1); // 获取变量名
            Object value = null;
            if(variable.startsWith("user.")||variable.startsWith("env.")){
                value = ExpTools.evaluate(variable,userAndEnv);
            }else{
                value = ExpTools.evaluate(variable,params);
            }
            if (value != null) {
                matcher.appendReplacement(sb, wrapStr+Matcher.quoteReplacement(value.toString())+wrapStr);
            } else {
                //throw new IllegalArgumentException("Missing value for variable: " + variable);
            }
        }
        matcher.appendTail(sb);

        return sb.toString();
    }
    /**
     *
     * @param tpl
     * @param params 接口变量池
     * @param userAndEnv 用户及环境变量池 {user:{userid:'',username:'',...},env:{date:Date,platformBranchId:''}}
     * @return
     */
    public static String parse(String tpl, Object params,Map<String,Object> userAndEnv,String wrapStr) {
        Matcher matcher = VARIABLE_PATTERN.matcher(tpl);
        StringBuffer sb = new StringBuffer();

        while (matcher.find()) {
            String variable = matcher.group(1); // 获取变量名
            Object value = null;
            if(variable.startsWith("user.")||variable.startsWith("env.")){
                value = ExpTools.evaluate(variable,userAndEnv);
            }else{
                value = ExpTools.getValue(variable,params);
            }
            if (value != null) {
                matcher.appendReplacement(sb, wrapStr+Matcher.quoteReplacement(value.toString())+wrapStr);
            } else {
                //throw new IllegalArgumentException("Missing value for variable: " + variable);
            }
        }
        matcher.appendTail(sb);

        return sb.toString();
    }
    /**
     *
     * @param tpl
     * @param vars 变量池
     * @return
     */
    public static String parse(String tpl, Map<String,Object> vars) {
         return parse(tpl,vars,"");
    }

    /**
     *
     * @param tpl
     * @param params 接口变量池
     * @param userAndEnv 用户及环境变量池 {user:{userid:'',username:'',...},env:{date:Date,platformBranchId:''}}
     * @return
     */
    public static String parse(String tpl, Map<String,Object> params,Map<String,Object> userAndEnv) {
         return parse(tpl,params,userAndEnv,"");
    }
    /**
     *
     * @param tpl
     * @param params 接口变量池
     * @param userAndEnv 用户及环境变量池 {user:{userid:'',username:'',...},env:{date:Date,platformBranchId:''}}
     * @return
     */
    public static String parse(String tpl, Object params,Map<String,Object> userAndEnv) {
         return parse(tpl,params,userAndEnv,"");
    }
        public static void main(String[] args) {
            String sql = "SELECT * FROM users WHERE id = '#{user.userid}' AND name = '#{user.username}'";
            Map<String, Object> params = new HashMap<>();
            Map<String,Object> user=new HashMap<>();
            user.put("userid","1");
            user.put("username","Alice");
            params.put("sex","男");
            params.put("user",user);
            String parsedSQL = parse(sql, params);
            System.out.println(parsedSQL);
            // 输出: SELECT * FROM users WHERE id = 1 AND name = 'Alice'
        }
}
