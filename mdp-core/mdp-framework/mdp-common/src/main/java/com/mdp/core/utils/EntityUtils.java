package com.mdp.core.utils;


import cn.hutool.core.util.ObjectUtil;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class EntityUtils {

    public static DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public static Map<Class,Map<String,String>> nameCaches=new HashMap<>();

    public static  <T> Map<String,String> getNameMap(Class<T> clazz){

        Map<String,String> m= nameCaches.get(clazz);
        if(m==null){
            m=parseEntityFieldNames(clazz);
            if(m!=null){
                nameCaches.put(clazz,m);
                return m;
            }
        }
        return m;
    }

    public static String parseFieldName(String name){
        if(ObjectTools.isEmpty(name)){
            return "";
        }
        String regex = "[^\u4E00-\u9FA5]";
       Optional<String> o= Arrays.stream(name.split(regex)).filter(k->ObjectTools.isNotEmpty(k)).findFirst();
        if(!o.isPresent()){
            String n=name.replaceAll("[ \\t\\n\\x0B\\f\\r]\\S*", "");
            return n;
        }
        return o.get();
    }

    private static <T> Map<String, String> parseEntityFieldNames(Class<T> clazz) {
        Map<String,String> nameMap=new HashMap<>();
        for (Field field : clazz.getDeclaredFields()) {
            ApiModelProperty ano=field.getAnnotation(ApiModelProperty.class);
            if(ano!=null){
                String fieldId=field.getName();
                String notes=ano.notes();
                if(ObjectTools.isEmpty(notes)){
                    nameMap.put(fieldId,fieldId);
                    continue;
                }
                nameMap.put(fieldId,parseFieldName(notes));
            }else{
                Schema schema=field.getAnnotation(Schema.class);
                if(schema!=null){
                    String fieldId=field.getName();
                    String notes=schema.description();
                    if(ObjectTools.isEmpty(notes)){
                        nameMap.put(fieldId,fieldId);
                        continue;
                    }
                    nameMap.put(fieldId,parseFieldName(notes));
                }else{
                    nameMap.put(field.getName(), field.getName());
                }

            }
        }
        return nameMap;
    }


    public static <T> Set<String> getChangeLogs(Map<String,Object> newParams,T entity,FieldCb fieldCb,String... ignoreFieldIds){
        Map<String,String> nameMap=getNameMap(entity.getClass());
        Set<String> ignoreSet=new HashSet<>();
        if(ignoreFieldIds!=null){
            for (String ignoreFieldId : ignoreFieldIds) {
                ignoreSet.add(ignoreFieldId);
            }
        }
        ignoreSet.add("$pks");
        if(nameMap==null||nameMap.isEmpty()){
            return new HashSet<>();
        }else{
            Set<String> changeLog=new HashSet<>();
            Map<String,Object> oldValues=BaseUtils.toMap(entity);
            for (Map.Entry<String, Object> e : newParams.entrySet()) {
                if(ignoreSet.contains(e.getKey())){
                    continue;
                }
                if(ObjectUtil.equal(e.getValue(),oldValues.get(e.getKey()))){
                    continue;
                }
                String name=nameMap.get(e.getKey());
                String remark=String.format("字段【%s】,旧值【%s】,新值【%s】",name,oldValues.get(e.getKey()),formatValue(e.getValue()));

                if(fieldCb!=null){
                    String remarkCb=fieldCb.call(e.getKey(),name,e.getValue(),oldValues.get(e.getKey()));
                    if(ObjectTools.isNotEmpty(remarkCb)){
                        remark=remarkCb;
                    }
                }
                changeLog.add(remark);
            }
            return changeLog;
        }
    }
    public static Object formatValue(Object v){


        if(v==null){
            return null;
        }else if(v instanceof Date){
            return DateUtils.format((Date) v,"yyyy-MM-dd HH:mm:ss");
        }else if(v instanceof LocalDate){
            LocalDate localDate=(LocalDate) v;
             return localDate.format(formatter);
        }else{
            return v;
        }
    }

    public static <T> Set<String> getChangeLogs(Map<String,Object> newParams,T entity,String... ignoreFieldIds){
        return getChangeLogs(newParams,entity,null,ignoreFieldIds);
    }


    public static <T> String getChangeLogsString(Map<String,Object> newParams,T entity,String... ignoreFieldIds){
        return getChangeLogs(newParams,entity,null,ignoreFieldIds).stream().collect(Collectors.joining(";"));
    }

    public static void main(String[] args) {
        System.out.println(parseFieldName("123陈裕天"));
    }
}
