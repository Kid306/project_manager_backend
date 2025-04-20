package com.mdp.core.dao;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.handlers.AnnotationHandler;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.ClassUtils;
import com.baomidou.mybatisplus.core.toolkit.GlobalConfigUtils;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.mdp.core.dao.annotation.TableIds;
import org.apache.ibatis.reflection.Reflector;
import org.apache.ibatis.session.Configuration;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class TableUtil {



    static Map<Class,List<TableFieldInfo>> TABLE_ALL_FIELDS_CACHE =new HashMap<>();
    static Map<Class,List<TableFieldInfo>> TABLE_PK_FIELDS_CACHE =new HashMap<>();

    static Map<Class,String> TABLE_NAME_ALIAS_CACHE =new HashMap<>();
    static Map<Class,String> TABLE_NAME_CACHE =new HashMap<>();

    public static List<TableFieldInfo> getPkFields(Class<?> clazz) {

        List<TableFieldInfo> fieldInfos=new ArrayList<>();
        if (clazz != null && !ReflectionKit.isPrimitiveOrWrapper(clazz) && clazz != String.class && !clazz.isInterface()) {
            Class<?> targetClass = ClassUtils.getUserClass(clazz);
             fieldInfos=TABLE_PK_FIELDS_CACHE.get(targetClass);
             if(fieldInfos==null){
                 TableInfo tableInfo=TableInfoHelper.getTableInfo(clazz);
                 if(tableInfo==null){
                     return fieldInfos;
                 }else{
                     Configuration configuration=tableInfo.getConfiguration();
                     GlobalConfig globalConfig=GlobalConfigUtils.getGlobalConfig(configuration);
                     initTableAllFields(clazz,globalConfig,tableInfo);
                     return  TABLE_PK_FIELDS_CACHE.get(targetClass);
                 }

             }else{
                 return fieldInfos;
             }
        }else {
            return new ArrayList<>();
        }
    }


    private static void initTableAllFields(Class<?> clazz, GlobalConfig globalConfig, TableInfo tableInfo) {
        AnnotationHandler annotationHandler = globalConfig.getAnnotationHandler();
         Reflector reflector = tableInfo.getReflector();
        List<Field> list = TableInfoHelper.getAllFields(clazz, annotationHandler);
        boolean existTableLogic = TableInfoHelper.isExistTableLogic(list, annotationHandler);
        List<TableFieldInfo> pklist = new ArrayList();
        Iterator var13 = list.iterator();

        while(var13.hasNext()) {
            Field field = (Field)var13.next();
                TableId tableId = (TableId)annotationHandler.getAnnotation(field, TableId.class);

                if (tableId!=null) {
                    TableFieldInfo fieldInfo=new TableFieldInfo(globalConfig, tableInfo, field, reflector, existTableLogic, false);
                    pklist.add(fieldInfo);
                } else {
                    TableIds tableIds= field.getAnnotation(TableIds.class);
                    if(tableIds==null){
                        continue;
                    }
                    TableFieldInfo fieldInfo=new TableFieldInfo(globalConfig, tableInfo, field, reflector, existTableLogic, false);
                    pklist.add(fieldInfo);
                }
            }
        if(pklist.size()>0){
            TABLE_PK_FIELDS_CACHE.put(clazz,pklist);
            List<TableFieldInfo> tableFieldInfos=tableInfo.getFieldList();
            Map<String,TableFieldInfo> map=new HashMap<>();
            for (TableFieldInfo tableFieldInfo : tableFieldInfos) {
                map.put(tableFieldInfo.getColumn(),tableFieldInfo);
            }
            for (TableFieldInfo tableFieldInfo : pklist) {
                map.put(tableFieldInfo.getColumn(),tableFieldInfo);
            }
            TABLE_ALL_FIELDS_CACHE.put(clazz,map.values().stream().collect(Collectors.toList()));
        }else {

        }

    }

    public static List<TableFieldInfo> getAllFields(Class clazz){
        if (clazz != null && !ReflectionKit.isPrimitiveOrWrapper(clazz) && clazz != String.class && !clazz.isInterface()) {
            Class<?> targetClass = ClassUtils.getUserClass(clazz);
            List<TableFieldInfo> fieldInfos = TABLE_ALL_FIELDS_CACHE.get(targetClass);
            if(fieldInfos==null ){
                TableInfo tableInfo=TableInfoHelper.getTableInfo(clazz);
                if(tableInfo==null){
                    return fieldInfos;
                }else{
                    Configuration configuration=tableInfo.getConfiguration();
                    GlobalConfig globalConfig=GlobalConfigUtils.getGlobalConfig(configuration);
                    initTableAllFields(clazz,globalConfig,tableInfo);
                    return  TABLE_ALL_FIELDS_CACHE.get(targetClass);
                }
            }else{
                return fieldInfos;
            }
        } else {
            return new ArrayList<>();
        }
    }


    public static String getTableAlias(Class<?> clazz){
        return TABLE_NAME_ALIAS_CACHE.get(clazz);
    }


    public static String getTableName(Class<?> clazz){
        TableInfo tableInfo= TableInfoHelper.getTableInfo(clazz);
        if(tableInfo==null){
            return null;
        }
        return tableInfo.getTableName();
    }
}
