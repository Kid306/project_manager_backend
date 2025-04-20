package com.mdp.dm.service;


import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mdp.core.DsUtil;
import com.mdp.core.api.Sequence;
import com.mdp.core.entity.DmField;
import com.mdp.core.entity.LangTips;
import com.mdp.core.err.BizException;
import com.mdp.core.query.QueryTools;
import com.mdp.core.service.SequenceService;
import com.mdp.core.utils.BaseUtils;
import com.mdp.core.utils.DateUtils;
import com.mdp.core.utils.ObjectTools;
import com.mdp.dm.base.service.TableDataBaseService;
import com.mdp.dm.mapper.DmTableDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
@Service
public class DmTableDataService implements TableDataBaseService  {

    @Autowired
    DmTableDataMapper baseMapper;

    Sequence sequenceService=new SequenceService();

    @Autowired
    DmDataMetaService dataMetaService;
  

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

    public JdbcTemplate getJdbcTemplate(){

        JdbcTemplate template=new JdbcTemplate();
        template.setDataSource(DsUtil.getDataSource());
        return template;
    }
    public JdbcTemplate getJdbcTemplate(String ds){
        JdbcTemplate template=new JdbcTemplate();
        template.setDataSource( DsUtil.getDataSource(ds) );
        return template;
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
    
    
    
    public List<String>  calcPks(List<DmField> tableFields){
        List<String> pks=tableFields.stream().filter(k->k.isPk()).map(k->k.getColumnName()).collect(Collectors.toList());
        return pks;
    }
    
    public List<DmField> getDmFields(String ds,String tableName){
        return dataMetaService.getColumnsInfoFirstCache(ds,tableName);
    }

    public boolean updateById(String ds,String tableName,List<DmField> tableFields,Map<String,Object> params,boolean ignoNull){
         JdbcTemplate template=getJdbcTemplate(ds);
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
        List<String> pks= calcPks(tableFields);
        JdbcTemplate template=getJdbcTemplate(ds); 
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

    public List<Map<String,Object>> listBySql(String ds,String dsSql,IPage page,QueryWrapper qw,Map<String,Object> params) {
        params.put("dsSql",dsSql);
        if(DynamicDataSourceContextHolder.peek()==null || !DynamicDataSourceContextHolder.peek().equalsIgnoreCase(ds)){
            DynamicDataSourceContextHolder.push(ds);
            try {
                List<Map<String,Object>> datas=baseMapper.listBySql(page,qw,params);
                return datas;
            }finally {
                DynamicDataSourceContextHolder.poll();
            }

        }else{
            List<Map<String,Object>> datas=baseMapper.listBySql(page,qw,params);
            return datas;
        }
    }
    public List<Map<String,Object>> list(String ds,String tableName,IPage page,QueryWrapper qw,Map<String,Object> params) {
        params.put("tableName",tableName);
        if(DynamicDataSourceContextHolder.peek()==null || !DynamicDataSourceContextHolder.peek().equalsIgnoreCase(ds)){
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
        if(params==null){
            return false;
        }
        JdbcTemplate template=getJdbcTemplate(ds);
        try {

            StringBuffer sql=new StringBuffer(" delete %s where ");

            List<String> pks= calcPks(dataMetaService.getColumnsInfoFirstCache(ds,tableName));
            if(pks==null || pks.size()==0){
                throw new BizException(LangTips.errMsg("pks-required","表格%s未设置主键",tableName));
            }
            List<Object> vars=new ArrayList<>();
            if(pks.size()>1){
                if(params instanceof Map) {
                    Map<String,Object> pmap= (Map<String, Object>) params;
                    String whereSql=pks.stream().map(k->k+"=?").collect(Collectors.joining(" and "));
                    sql.append(whereSql);
                    for (String pk : pks) {
                        vars.add(pmap.get(pk));
                    }
                }else {
                    throw new BizException("多主键表，请用map传递主键参数");
                }
            }else {
                sql.append(pks.get(0)).append("=?");
                if(params instanceof Map) {
                    Map<String,Object> pmap= (Map<String, Object>) params;
                    vars.add(pmap.get(pks.get(0)));
                }else {
                    vars.add(params);
                }
            }
            String fullSql=sql.toString();
            fullSql=String.format(fullSql,tableName);
            template.update(fullSql,vars.toArray());
        }finally {
            DynamicDataSourceContextHolder.poll();
        }
        return true;
    }

    public boolean editSomeFields(String ds,String tableName,Map<String, Object> params) {
        return true;
    }

    public boolean  removeByIds(String ds,String tableName, List ids) {

        if(ids==null){
            return false;
        }
        if(ids.size()==1){
            return removeById(ds,tableName,ids.get(0));
        }
        JdbcTemplate template=getJdbcTemplate(ds);
        try {

            StringBuffer sql=new StringBuffer(" delete %s where ");
            List<String> pks= calcPks(getDmFields(ds,tableName));
            List<Object> vars=new ArrayList<>();
            Object params=ids.get(0);
            sql.append("(").append(pks.stream().collect(Collectors.joining(","))).append(") in ");
            if(pks.size()>1){
                if(params instanceof Map) {
                    String whereSql= (String) ids.stream().map(k->"(?,?)").collect(Collectors.joining(","));
                    sql.append(whereSql);
                    for (Object id : ids) {
                        Map<String,Object> pmap= (Map<String, Object>) id;
                        for (String pk : pks) {
                            vars.add(pmap.get(pk));
                        }
                    }
                }else {
                    throw new BizException("mult-pks-need-map-type","多主键表，请用map传递主键参数");
                }
            }else {
                String whereSql= (String) ids.stream().map(k->"(?)").collect(Collectors.joining(","));
                sql.append(whereSql);
                if(params instanceof Map) {
                    for (Object id : ids) {
                        Map<String,Object> pmap= (Map<String, Object>) id;
                        vars.add(pmap.get(pks.get(0)));
                    }
                }else {
                    vars=ids;
                }
            }
            String fullSql=sql.toString();
            fullSql=String.format(fullSql,tableName);
            template.update(fullSql,vars.toArray());
        }finally {
            DynamicDataSourceContextHolder.poll();
        }
        return true;
    }

    public Map<String, Object>  getById(String ds,String tableName, Object id) {
        if(id==null){
            return null;
        }
            Map<String,Object> params=BaseUtils.map();

            List<String> pks= calcPks(getDmFields(ds,tableName));
            if(pks.size()>1){
                if(id instanceof Map){
                    Map<String,Object> map= (Map<String, Object>) id;
                    for (String pk : pks) {
                        String pkName=ObjectTools.camelName(pk);
                        Object v=map.get(pk);
                        if(ObjectTools.isEmpty(v)){
                            throw new BizException(LangTips.errMsg("pk-v-required","主键[%s]必输",pkName));
                        }
                        params.put(pkName,v);
                    }
                }else{
                    throw new BizException("mult-pks-need-map-type","多主键表，请用map传递主键参数");
                }
            }else{
                if(id instanceof Map){
                   Map<String,Object> map= (Map<String, Object>) id;
                    for (String pk : pks) {
                        String pkName=ObjectTools.camelName(pk);
                        Object v=map.get(pk);
                        if(ObjectTools.isEmpty(v)){
                            throw new BizException(LangTips.errMsg("pk-v-required","主键[%s]必输",pkName));
                        }
                        params.put(pkName,v);
                    }
                }else{
                    for (String pk : pks) {
                        String pkName=ObjectTools.camelName(pk);
                        params.put(pkName,id);
                    }
                }

            }
            if(params.isEmpty()){
                throw new BizException(LangTips.errMsg("pk-value-required","主键【%s】参数不能为空",pks.toString()));
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

        if(where==null){
            return false;
        }
        JdbcTemplate template=getJdbcTemplate(ds);

            String sql=" update %s %s where %s";
            List<Object> vars=new ArrayList<>();
            List<DmField> fields=dataMetaService.getColumnsInfoFirstCache(ds,tableName);
            String whereSql="";
            String setSql="";
            Map<String,Object> whereCon=BaseUtils.map();
            Map<String,Object> setCon=BaseUtils.map();
            for (DmField field : fields) {
                if(where.containsKey(field.getColumnName())){
                    whereCon.put(field.getColumnName(),where.get(field.getColumnName()));
                }
                if(set.containsKey(field.getColumnName())){
                    setCon.put(field.getColumnName(),set.get(field.getColumnName()));
                }
            }
            if(setCon.isEmpty()){
                throw new BizException("set-required","至少必须更新一个字段");
            }else{
                setSql=setCon.entrySet().stream().map(k->"set "+k.getKey()+"=?").collect(Collectors.joining(" and "));
                vars.addAll(setCon.entrySet().stream().map(k->k.getValue()).collect(Collectors.toList()));
            }
            if(whereCon.isEmpty()){
                whereSql="1=1";
            }else{
                whereSql=whereCon.entrySet().stream().map(k->k.getKey()+"=?").collect(Collectors.joining(" and "));
                vars.addAll(whereCon.entrySet().stream().map(k->k.getValue()).collect(Collectors.toList()));
            }

            String fullSql= String.format(sql,tableName,setSql,whereSql);
            int i=template.update(fullSql,vars.toArray());
            return i>0;
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
        if(formDataList==null || formDataList.size()==0){
           return false;
        }else if(formDataList.size()==1){
            return saveOrUpdate(ds,tableName,formDataList.get(0),false);
        }else{
            List<Map<String,Object>> dataDb=listByIds(ds,tableName,formDataList);
            List<Map<String,Object>> needUpdateList=new ArrayList<>();
            List<Map<String,Object>> needInsertList=new ArrayList<>();
            if(dataDb==null || dataDb.size()==0){//全部新增
                needInsertList=formDataList;
            }else{
                List<DmField> fields=parseDmFields(ds,tableName);
                List<String> pks= calcPks(fields);
                 boolean isMpk=pks.size()>1;
                for (Map<String, Object> pmap : formDataList) {
                    boolean isInsert=false;
                    for (String pk : pks) {
                        if(isMpk){
                            if(ObjectTools.isEmpty(pmap.get(pk))){
                                throw new BizException(LangTips.errMsg("pks-data-0","多主键表，主键字段%s不能为空",pk));
                            }
                        }else{
                            if(ObjectTools.isEmpty(pmap.get(pk))){//如果没有主键，自动创建
                                String id=createKey(pk);
                                pmap.put(pk,id);
                                isInsert=true;
                            }else{
                                isInsert=false;
                            }
                        }
                    }
                    if(isInsert){
                        needInsertList.add(pmap);
                    }else{
                        needUpdateList.add(pmap);
                    }
                }
            }
            boolean update=true;
            boolean insert=true;
            if(needUpdateList.size()>0){
               update= updateBatch(ds,tableName,needUpdateList);
            }
            if(needInsertList.size()>0){
                insert=saveBatch(ds,tableName,needInsertList);
            }
            return update||insert;
        }
    }

    public boolean updateBatch(String ds,String tableName, List<Map<String, Object>> formDataList) {
        List<DmField> fields=parseDmFields(ds,tableName);
        List<String> pks=calcPks(fields);
        if(pks==null || pks.size()==0){
            throw new BizException("pks-required","表未设置主键，不能进行数据维护");
        }
        List<DmField> tableFields=parseDmFields(ds,tableName);
        JdbcTemplate template=getJdbcTemplate(ds);
                List<Object[]> varList = new ArrayList<>();
                String sql = " update %s %s where %s ";
                //进行主键处理
                String setSql = fields.stream().map(k -> " set " + ObjectTools.camelToUnderline(k.getColumnName()) + "=?").collect(Collectors.joining(","));
                String whereSql = pks.stream().map(k -> ObjectTools.camelToUnderline(k) + "=?").collect(Collectors.joining(" and "));
                for (Map<String, Object> pmap : formDataList) {
                    List<Object> pkParams = new ArrayList<>();
                    for (String pk : pks) {
                        String pkName = ObjectTools.camelName(pk);
                        if (ObjectTools.isEmpty(pmap.get(pkName))) {
                            throw new BizException(LangTips.errMsg("pks-data-required", "多主键下主键参数%s不能为空", pkName));
                        }
                        pkParams.add(pmap.get(pkName));
                    }
                    List<Object> row = new ArrayList<>();
                    for (DmField field : fields) {
                        if (pks.stream().filter(k -> ObjectTools.camelName(k).equalsIgnoreCase(field.getColumnName())).findAny().isPresent()) {
                            continue;
                        }
                        row.add(pmap.get(field.getColumnName()));
                    }
                    row.addAll(pkParams);
                    varList.add(row.toArray());
                }
                String fullSql = String.format(sql, tableName, setSql, whereSql);
                int[] i=template.batchUpdate(fullSql, varList);
                return i.length>0;
    }

    public boolean saveBatch(String ds,String tableName, List<Map<String, Object>> formDataList) {
        List<DmField> fields=parseDmFields(ds,tableName);
        List<String> pks=calcPks(fields);
        if(pks==null || pks.size()==0){
            throw new BizException("pks-required","表未设置主键，不能进行数据维护");
        }
        List<DmField> tableFields=parseDmFields(ds,tableName);
        JdbcTemplate template=getJdbcTemplate(ds);

                List<Object[]> varList = new ArrayList<>();
                String sql = " insert %s (%s) values (%s) ";
                //进行主键处理
                boolean isMulPk = pks.size() > 1;
                String fieldsSql = fields.stream().map(k -> k.getColumnName()).collect(Collectors.joining(","));
                String valuesSql = fields.stream().map(k -> "?").collect(Collectors.joining(","));
                for (Map<String, Object> pmap : formDataList) {
                    for (String pk : pks) {
                        String pkName = ObjectTools.camelName(pk);
                        if (isMulPk) {
                            if (ObjectTools.isEmpty(pmap.get(pkName))) {
                                throw new BizException(LangTips.errMsg("pks-data-0", "多主键下主键参数%s不能为空", pkName));
                            }
                        } else {
                            if (ObjectTools.isEmpty(pmap.get(pkName))) {
                                pmap.put(pkName, createKey(pkName));
                            }
                        }
                    }
                    List<Object> row = new ArrayList<>();
                    for (DmField field : fields) {
                        row.add(pmap.get(field.getColumnName()));
                    }
                    varList.add(row.toArray());
                }
                String fullSql = String.format(sql, tableName, fieldsSql, valuesSql);
                int[] i=template.batchUpdate(fullSql, varList);
                return i.length>0;
    }

    private List<DmField> parseDmFields(String ds, String tableName) {
        return dataMetaService.getColumnsInfoFirstCache(ds,tableName);
    }


    public String createKey(String keyName) {
        return sequenceService.getTablePK(this.getClass().getName(), keyName);
    }
}
