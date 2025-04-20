package com.mdp.dm.tools;

import com.mdp.core.ExpTools;
import com.mdp.dm.vo.SqlTplVo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
public class Tpl {


        // 正则表达式用于匹配类似 #{variableName} 的动态变量
        private static final Pattern VARIABLE_PATTERN = Pattern.compile("\\#\\{([^}]+)}");
        private static final Pattern TPL_PATTERN = Pattern.compile("\\?");
        public static String trimSql(String sql){
            return sql.trim();
        }
        /**
     *
     * @param tpl
     * @return
     */
    public static SqlTplVo parse(String tpl,boolean fillValue) {
        SqlTplVo vo=new SqlTplVo();
        Matcher matcher = VARIABLE_PATTERN.matcher(tpl);
        StringBuffer sb = new StringBuffer();
        List<String> varNames=new ArrayList<>();
        List<Object> varValues=new ArrayList<>();
        while (matcher.find()) {
            String variable = matcher.group(1); // 获取变量名
            varNames.add(variable);
            String value="";
             varValues.add(value);
            matcher.appendReplacement(sb, Matcher.quoteReplacement(fillValue?"'"+value+"'":"?"));
        }
        matcher.appendTail(sb);
        vo.setSqlTpl(sb.toString());
        vo.setVarValues(varValues);
        vo.setVarNames(varNames);
        return vo;
    }

    public static String setSqlValues(String sqlTpl,List<Object> values){
        Matcher matcher = TPL_PATTERN.matcher(sqlTpl);
        StringBuffer sb = new StringBuffer();
        int i=0;
        while (matcher.find()) {
            matcher.appendReplacement(sb, Matcher.quoteReplacement("'"+values.get(i)+"'"));
            i=i+1;
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
    public static SqlTplVo parse(String tpl, Map<String,Object> vars,boolean fillValue) {
        SqlTplVo vo=new SqlTplVo();
        Matcher matcher = VARIABLE_PATTERN.matcher(tpl);
        StringBuffer sb = new StringBuffer();
        List<String> varNames=new ArrayList<>();
        List<Object> varValues=new ArrayList<>();
        while (matcher.find()) {
            String variable = matcher.group(1); // 获取变量名
            varNames.add(variable);
            Object value = ExpTools.evaluate(variable,vars); // 获取变量对应的值
            varValues.add(value);
            matcher.appendReplacement(sb, Matcher.quoteReplacement(fillValue?"'"+value+"'":"?"));
        }
        matcher.appendTail(sb);
        vo.setSqlTpl(sb.toString());
        vo.setVarValues(varValues);
        vo.setVarNames(varNames);
        return vo;
    }


    /**
     *
     * @param tpl
     * @param params 接口变量池
     * @param userAndEnv 用户及环境变量池 {user:{userid:'',username:'',...},env:{date:Date,platformBranchId:''}}
     * @return
     */
    public static SqlTplVo parse(String tpl, Map<String,Object> params,Map<String,Object> userAndEnv,boolean fillValue) {
        SqlTplVo vo=new SqlTplVo();
        Matcher matcher = VARIABLE_PATTERN.matcher(tpl);
        StringBuffer sb = new StringBuffer();
        List<String> varNames=new ArrayList<>();
        List<Object> varValues=new ArrayList<>();
        while (matcher.find()) {
            String variable = matcher.group(1); // 获取变量名
            varNames.add(variable);
            Object value = null;
            if(variable.startsWith("user.")||variable.startsWith("env.")){
                value = ExpTools.evaluate(variable,userAndEnv);
                varValues.add(value);
            }else{
                value = ExpTools.evaluate(variable,params);
                varValues.add(value);
            }
            matcher.appendReplacement(sb, Matcher.quoteReplacement(fillValue?"'"+value+"'":"?"));
        }
        matcher.appendTail(sb);

        vo.setSqlTpl(sb.toString());
        vo.setVarValues(varValues);
        vo.setVarNames(varNames);
        return vo;
    }

        public static void main(String[] args) {
            String sql = "select * from sys_user where 1=1 and userid=#{user.userid}\n" +
                    "\n" +
                    "\n" +
                    "\n";
            Map<String, Object> params = new HashMap<>();
            Map<String,Object> user=new HashMap<>();
            user.put("userid","1");
            user.put("username","Alice");
            params.put("sex","男");
            params.put("user",user);
            sql=trimSql(sql);
            SqlTplVo parsedSQL = parse(sql, params,true);
            System.out.println(parsedSQL);
            // 输出: SELECT * FROM users WHERE id = 1 AND name = 'Alice'
        }
}
