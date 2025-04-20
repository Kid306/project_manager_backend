package com.mdp.dm.base.service;


import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mdp.core.DsUtil;
import com.mdp.core.api.Sequence;
import com.mdp.core.entity.DmField;
import com.mdp.core.err.BizException;
import com.mdp.core.query.QueryTools;
import com.mdp.core.service.SequenceService;
import com.mdp.core.utils.BaseUtils;
import com.mdp.core.utils.DateUtils;
import com.mdp.core.utils.ObjectTools;
import com.mdp.dm.base.VersionUtil;
import com.mdp.dm.base.mapper.DefaultTableDataBaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
public class DefaultTableDataBaseService implements TableDataBaseService {

    @Autowired
    DefaultTableDataBaseMapper baseMapper;

    Sequence sequenceService=new SequenceService();

    @Autowired
    DataMetaBaseService dataMetaService;
  

    public Boolean existsData(String ds,String tableName){
        DynamicDataSourceContextHolder.push(ds);
        try {
            Map<String,Object> params=new HashMap();
            IPage page=QueryTools.initPage(BaseUtils.map("pageNum",1,"pageSize",1,"count",true));
            params.put("tableName",tableName);
            QueryWrapper qw=new QueryWrapper();
            List<Map<String,Object>> datas=baseMapper.selectListMapByWhere(page,qw,params);
            return page.getTotal()>0;
        }finally {
            DynamicDataSourceContextHolder.poll();
        }

    } 

    public boolean saveOrUpdate(String ds,String tableName,Map<String,Object> params,boolean ignoNull){
         List<DmField> tableFields=dataMetaService.getColumnsInfoFirstCache(ds,tableName);
        QueryWrapper qw=new QueryWrapper<>();
        List<String> pks= this.calcPks(tableFields);
        for (String key : pks) {
            Object val=params.get(ObjectTools.camelName(key));
            qw.eq(BaseUtils.underscoreName(key),val);
        }
        IPage page=QueryTools.initPage(BaseUtils.map("pageNum",1,"pageSize",1));
        params.put("tableName",tableName);
        List<Map<String, Object>> datas=new ArrayList<>();
        try {
            datas = baseMapper.selectListMapByWhere(page, qw, params);
        }finally {
           // DynamicDataSourceContextHolder.poll();
        }

        if (datas != null && datas.size() > 0) {//update
            return this.updateById(ds,tableName,tableFields,params,ignoNull);
        } else {//add
             return this.save(ds,tableName,tableFields,params,ignoNull);
        }
    }
    
    
    
    public List<String> calcPks(List<DmField> tableFields){
        List<String> pks=tableFields.stream().filter(k->k.isPk()).map(k->k.getColumnName()).collect(Collectors.toList());
        return pks;
    }
    
    public List<DmField> getDmFields(String ds,String tableName){
        return dataMetaService.getColumnsInfoFirstCache(ds,tableName);
    }

    public boolean updateById(String ds,String tableName,List<DmField> tableFields,Map<String,Object> params,boolean ignoNull){
         JdbcTemplate template=DsUtil.getJdbcTemplate(ds);
         List<String> pks=calcPks(tableFields);
        String sqlTpl = "update %s %s where %s";
         List<DmField> setFields = tableFields.stream().filter(k->!k.isPk()).collect(Collectors.toList());
        List<DmField> finalSetFields = new ArrayList<>();
        String whereSql = pks.stream().map(k -> ObjectTools.camelToUnderline(k) + "=?").collect(Collectors.joining(" and "));
        List<Object> vars = new ArrayList<>();
        for (DmField field : setFields) {
            String fieldIdCamel = ObjectTools.camelName(field.getColumnName());
            Object val = params.get(fieldIdCamel);
            if (ignoNull) {
                if (!ObjectTools.isEmpty(val)) {
                    finalSetFields.add(field);
                    vars.add(val);
                }
            } else {
                finalSetFields.add(field);
                vars.add(val);
            }
        }
        if (finalSetFields.size() == 0) {
            return true;
        }
        for (String pk : pks) {
            String pkName = ObjectTools.camelToUnderline(pk);
            vars.add(params.get(pkName));
        }
        String setSql = finalSetFields.stream().map(k -> "set " + ObjectTools.camelToUnderline(k.getColumnName()) + "=?").collect(Collectors.joining(","));
        String fullSql = String.format(sqlTpl, tableName, setSql, whereSql);
        int i=template.update(fullSql, vars.toArray()); 
        return i>0;  
    }

    public boolean save(String ds,String tableName,List<DmField> tableFields,Map<String,Object> params,boolean ignoNull){
         JdbcTemplate template=DsUtil.getJdbcTemplate(ds);
                String sqlTpl = "insert %s (%s) value (%s)";
                 List<DmField> setFields = tableFields.stream().filter(k->k.isPk()).collect(Collectors.toList());
                List<DmField> finalSetFields = new ArrayList<>();
                List<Object> vars = new ArrayList<>();
                Map<String, Object> dataMap = new HashMap<>();
                for (DmField field : setFields) {
                    String fieldIdCamel = ObjectTools.camelName(field.getColumnName());
                    Object val = params.get(fieldIdCamel);
                    if (ignoNull) {
                        if (!ObjectTools.isEmpty(val)) {
                            finalSetFields.add(field);
                            dataMap.put(fieldIdCamel, val);
                            vars.add(val);
                        }
                    } else {
                        finalSetFields.add(field);
                        dataMap.put(fieldIdCamel, val);
                        vars.add(val);
                    }
                }
                if (ignoNull) {
                    if (dataMap.isEmpty()) {
                        return true;
                    }
                }
                String fieldsSql = finalSetFields.stream().map(k -> ObjectTools.camelToUnderline(k.getColumnName())).collect(Collectors.joining(","));
                String valusSql = finalSetFields.stream().map(k -> "?").collect(Collectors.joining(","));

                String fullSql = String.format(sqlTpl, tableName, fieldsSql, valusSql);
               int i= template.update(fullSql, vars.toArray());
               return i>0;

    }

    public List<Map<String,Object>> list(String ds,String tableName,IPage page,QueryWrapper qw,Map<String,Object> params) {
        params.put("tableName",tableName);
        if(!DynamicDataSourceContextHolder.peek().equalsIgnoreCase(ds)){
            DynamicDataSourceContextHolder.push(ds);
            try {
                List<Map<String,Object>> datas=baseMapper.selectListMapByWhere(page,qw,params);
                return datas;
            }finally {
                DynamicDataSourceContextHolder.poll();
            }
            
        }else{
            List<Map<String,Object>> datas=baseMapper.selectListMapByWhere(page,qw,params);
            return datas;
        } 
    }

    @Override
    public List<Map<String, Object>> listBySql(String ds, String dsSql, IPage page, QueryWrapper qw, Map<String, Object> params) {
        params.put("dsSql",dsSql);
        if(!DynamicDataSourceContextHolder.peek().equalsIgnoreCase(ds)){
            DynamicDataSourceContextHolder.push(ds);
            try {
                List<Map<String,Object>> datas=baseMapper.selectListMapByWhere(page,qw,params);
                return datas;
            }finally {
                DynamicDataSourceContextHolder.poll();
            }

        }else{
            List<Map<String,Object>> datas=baseMapper.selectListMapByWhere(page,qw,params);
            return datas;
        }
    }


    public String getStr(Object val){
        if(val==null){
            return "";
        }else if(val instanceof Integer){
            return Integer.toString((Integer) val);
        }else if(val instanceof Long){
            return Long.toString((Long) val);
        }else if(val instanceof Short){
            return Short.toString((Short) val);
        }else if(val instanceof Float){
            return Float.toString((Float) val);
        }else if(val instanceof Double){
            return Double.toString((Double) val);
        }else if(val instanceof BigDecimal){
             return ((BigDecimal)(val)).toString();
        }else if(val instanceof Date){
            return DateUtils.format((Date)val,DateUtils.datetimePattern);
        }else if(val instanceof String){
            return (String)val;
        }else{
            return val.toString();
        }
    }

    public boolean removeById(String ds,String tableName,Object params) {
         return VersionUtil.supportVersion(false);
    }

    public boolean editSomeFields(String ds,String tableName,Map<String, Object> params) {
        return VersionUtil.supportVersion(false);
    }

    public boolean removeByIds(String ds,String tableName, List<Object> ids) {
        return VersionUtil.supportVersion(false);
    }

    public Map<String, Object>  getById(String ds,String tableName, Object id) {
        if(id==null){
            return null;
        }
            Map<String,Object> params=BaseUtils.map();

            List<String> pks= calcPks(getDmFields(ds,tableName));
            if(pks.size()>1){
                if(id instanceof Map){
                    params= (Map<String, Object>) id;
                }else{
                    throw new BizException("mult-pks-need-map-type","多主键表，请用map传递主键参数");
                }
            }else{
                for (String pk : pks) {
                    params.put(pk,id);
                }
            }
            params.put("tableName",tableName);
            QueryWrapper qw= QueryTools.initDmQw(parseDmFields(ds,tableName),params);
            IPage page=new Page(1,1,0L,false);
            List<Map<String,Object>> datas=this.list(ds,tableName,page,qw,params);
            if(datas.size()>0){
                return datas.get(0);
            }else{
                return null;
            }
    }

    public boolean updateByWhere(String ds,String tableName, Map<String,Object> set,Map<String, Object> where) {
        return VersionUtil.supportVersion(false);
    }

    public List<Map<String,Object>> listByIds(String ds,String tableName, List<?> formDataList){
        List<DmField> fields=getDmFields(ds,tableName);
       List<String> pks= calcPks(fields);
       if(formDataList==null){
           return new ArrayList<>();
       }else if(formDataList.size()==1){
           Map<String,Object> data=getById(ds,tableName,formDataList.get(0));
           if(data==null || data.isEmpty()){
               return new ArrayList<>();
           }else{
               return Collections.singletonList(getById(ds,tableName, formDataList.get(0)));
           }
       }
       String sql=" %s %s ";
       QueryWrapper qw=QueryTools.initDmQw(fields,null);
       List<Object> vars=new ArrayList<>();
       String fieldsSql=pks.stream().map(k->k).collect(Collectors.joining(","));
       String whereSql= pks.stream().map(k->"?").collect(Collectors.joining(","));
       String fullWhereSql=formDataList.stream().map(k->"("+whereSql+")").collect(Collectors.joining(","));
       String fullSql=String.format(sql,tableName,"("+fieldsSql+") in ",fullWhereSql);
       for (Object p : formDataList) {
           Map<String,Object> pmap= (Map<String, Object>) p;
           for (String pk : pks) {
               vars.add(pmap.get(pk));
           }
       }
       qw.apply(true,fullSql,vars.toArray());
       return list(ds,tableName,QueryTools.initPage(),qw,BaseUtils.map());
    }

    public boolean saveOrUpdateBatch(String ds,String tableName, List<Map<String, Object>> formDataList) {
        return VersionUtil.supportVersion(false);
    }

    public boolean updateBatch(String ds,String tableName, List<Map<String, Object>> formDataList) {
        return VersionUtil.supportVersion(false);
    }

    public boolean saveBatch(String ds,String tableName, List<Map<String, Object>> formDataList) {
        return VersionUtil.supportVersion(false);
    }

    private List<DmField> parseDmFields(String ds, String tableName) {
        return dataMetaService.getColumnsInfoFirstCache(ds,tableName);
    }


    public String createKey(String keyName) {
        return sequenceService.getTablePK(this.getClass().getName(), keyName);
    }
}
