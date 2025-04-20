package com.mdp.dm.base.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.DmField;

import java.util.List;
import java.util.Map;

public interface TableDataBaseService {

    public Boolean existsData(String ds,String tableName);

    public boolean saveOrUpdate(String ds,String tableName,Map<String,Object> params,boolean ignoNull);
    
    public List<String> calcPks(List<DmField> tableFields);
    
    public List<DmField> getDmFields(String ds,String tableName);

    public boolean updateById(String ds,String tableName,List<DmField> tableFields,Map<String,Object> params,boolean ignoNull);

    public boolean save(String ds,String tableName,List<DmField> tableFields,Map<String,Object> params,boolean ignoNull);

    public List<Map<String,Object>> list(String ds,String tableName,IPage page,QueryWrapper qw,Map<String,Object> params);

    public List<Map<String,Object>> listBySql(String ds,String dsSql,IPage page,QueryWrapper qw,Map<String,Object> params);

    public boolean removeById(String ds,String tableName,Object params);

    public boolean editSomeFields(String ds,String tableName,Map<String, Object> params);

    public boolean removeByIds(String ds,String tableName, List<Object> ids);

    public Map<String, Object>  getById(String ds,String tableName, Object id);

    public boolean updateByWhere(String ds,String tableName, Map<String,Object> set,Map<String, Object> where);

    public List<Map<String,Object>> listByIds(String ds,String tableName, List<?> formDataList);

    public boolean saveOrUpdateBatch(String ds,String tableName, List<Map<String, Object>> formDataList);

    public boolean updateBatch(String ds,String tableName, List<Map<String, Object>> formDataList);

    public boolean saveBatch(String ds,String tableName, List<Map<String, Object>> formDataList);

    public String createKey(String keyName);
}
