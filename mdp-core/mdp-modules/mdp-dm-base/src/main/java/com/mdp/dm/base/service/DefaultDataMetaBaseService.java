package com.mdp.dm.base.service;

import com.mdp.core.entity.DmField;
import com.mdp.core.entity.DmTable;
import com.mdp.dm.base.VersionUtil;
import com.mdp.dm.base.dao.DevJdbcDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * 
 * @author cyc 将entity的名字改成符合驼峰命名规范的名字 20150215
 * @see org.springframework.jdbc.support.JdbcUtils
 *
 */
public class DefaultDataMetaBaseService extends DevJdbcDao implements DataMetaBaseService {

	@Autowired
	public DataMetaCacheBaseService dataMetaCacheService;


	public List<DmField> getColumnsInfoFirstCache(String ds, String tableName) {
		List<DmField> fields=dataMetaCacheService.getFields(ds,tableName);
		if(fields==null){
			fields=super.getColumnsInfo(ds,tableName);
			if(fields==null){
				fields=new ArrayList<>();
			}else {
				try {
					List<String> pks=super.getPrimaryKeys(ds,tableName);
					for (DmField field : fields) {
						if(pks.stream().filter(k->k.equalsIgnoreCase(field.columnName)).findAny().isPresent()){
							field.setPk(true);
						}
					}
				}catch (Exception e){
					logger.error("没有主键"+e.getMessage());
				}

			}
			dataMetaCacheService.putFields(ds,tableName,fields);
		}
		return fields;
	}

	@Override
	public List<DmField> getColumnsInfoBySqlTplFirstCache(String ds, String sql, List<Object> varValues) {
		sql=sql.trim();
		String sqlTem=sql.trim();
		List<DmField> fields=dataMetaCacheService.getFields(ds,sqlTem);
		if(fields==null){
			fields=this.getColumnsInfoBySqlTpl(ds,sql,varValues);
			if(fields==null){
				fields=new ArrayList<>();
			}
			dataMetaCacheService.putFields(ds,sqlTem,fields);
		}
		return fields;
	}

	public List<DmField> getColumnsInfoBySqlFirstCache(String ds, String sql) {
		VersionUtil.supportVersion(false);
		return null;
	}

	/**
	 *	获取sql语句的列信息
	 */
	public  List<DmField> getColumnsInfoBySql(String ds,String dsSql){
		VersionUtil.supportVersion(false);
		return null;
	}

	@Override
	public List<DmField> getColumnsInfoBySqlTpl(String ds, String dsSql, List<Object> varValues) {
		VersionUtil.supportVersion(false);
		return Collections.emptyList();
	}


	public List<DmTable> getTableList(String ds, String tableName){
		VersionUtil.supportVersion(false);
		return null;
	}
}