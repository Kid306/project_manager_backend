package com.mdp.core;

import com.mdp.core.entity.No;
import com.mdp.core.utils.ObjectTools;
import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlExpression;
import org.apache.commons.jexl3.MapContext;
import org.apache.commons.jexl3.introspection.JexlSandbox;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ExpTools {
    private static JexlEngine jexl=null;

    static {
        initJexl();
    }
    public static void main(String[] args) {



        Map<String, Object> vars = new HashMap<>();
        vars.put("myBean", new MyBean("1", "2"));
        Object result = evaluate("1*5",vars);
        System.out.println(result); // 输出 "value1_value2"
        result = evaluate("'xxxx'+((myBean.property1*5)-1).valueOf('1')",vars);
        System.out.println(result); // 输出 "value1_value2"

        result=evaluate("myBean.userid",vars);
        System.out.println("空变量池测试："+result);

        Map<String,Object> p1=new HashMap<>();
        No no=new No();
        no.setId("no_id");
        no.setYear(10);
        no.setDay(20);
        p1.put("node",no);

        Map<String,Object> p2=new HashMap<>();
        Map<String,Object> user=new HashMap<>();
        user.put("userid","chenyc_001");
        user.put("username","财");
        user.put("deptid","dept001");
        user.put("branchId","branch001");
        p2.put("user",user);

        Map<String,Object> p3=new HashMap<>();
        Map<String,Object> env=new HashMap<>();
        env.put("platformBranchId","platformBranchId001");
        env.put("date",new Date());
        p3.put("env",env);

        long start=System.currentTimeMillis();
        System.out.println(System.currentTimeMillis());
        for (int i = 0; i < 100000; i++) {
            Object result1=getValue("user.userid",vars);
            //System.out.println(result1);
        }
        long end=System.currentTimeMillis();
        System.out.println("花费时间："+(end-start)/1000+"秒"+(end-start)/1000/60+"分");
        start=System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            Object result1=user.get("userid");
            //System.out.println(result1);
        }
        end=System.currentTimeMillis();
        System.out.println("花费时间："+(end-start)/1000+"秒"+(end-start)/1000/60+"分");
    }

    public static void initJexl(){
        JexlSandbox sandbox = new JexlSandbox();
        jexl = new JexlBuilder().sandbox(sandbox).create();
    }
    /**
     * @param expStr 任意表达式
     * @param vars 变量池
     * @return
     */
    public static  Object evaluate(String expStr,Map<String,Object> vars){
        JexlExpression expression = jexl.createExpression(expStr);
        MapContext context = new MapContext(vars);
        Object result = expression.evaluate(context);
        return result;
    }

    /**
     * 近限于xxx.xx.xx之类的表达式，其它请用
     * @param expStr user.userid、user.username、user.deptid、user.branchId、env.date、env.platformBranchId、
     * @param objects [params,user,env]等
     * @return
     */
    public static  Object getValue(String expStr,Object ...objects){

        if(objects==null){
            return expStr;
        }
        for (int i = 0; i < objects.length; i++) {
            Map<String,Object> vars=new HashMap<>();
            String key="_"+i;
            vars.put(key,objects[i]);
            String expStrTemp=key+"."+expStr;
            JexlExpression expression = jexl.createExpression(expStrTemp);
            MapContext context = new MapContext(vars);
            Object result = expression.evaluate(context);
            if(!ObjectTools.isEmpty(result)){
                return result;
            }
        }

        return expStr;
    }
 
    public static class MyBean {
        private String property1;
        private String property2;
 
        public MyBean(String property1, String property2) {
            this.property1 = property1;
            this.property2 = property2;
        }
 
        public String getProperty1() {
            return property1;
        }
 
        public String getProperty2() {
            return property2;
        }
    }
}