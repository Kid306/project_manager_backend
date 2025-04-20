package com.mdp.dm.service;

import com.mdp.core.DsUtil;
import com.mdp.core.entity.DmField;
import com.mdp.core.entity.DmTable;
import com.mdp.core.err.BizException;
import com.mdp.core.utils.ObjectTools;
import com.mdp.dm.base.dao.DevJdbcDao;
import com.mdp.dm.base.service.DataMetaBaseService;
import com.mdp.dm.base.service.DataMetaCacheBaseService;
import com.mdp.dm.tools.Tpl;
import com.mdp.dm.vo.SqlTplVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * 
 * @author cyc 将entity的名字改成符合驼峰命名规范的名字 20150215
 * @see org.springframework.jdbc.support.JdbcUtils
 *
 */
 @Service
public class DmDataMetaService extends DevJdbcDao implements DataMetaBaseService {

	@Autowired
	public DataMetaCacheBaseService dataMetaCacheService;


	public List<DmField> getColumnsInfoFirstCache(String ds, String tableName) {
		List<DmField> fields=dataMetaCacheService.getFields(ds,tableName);
		if(fields==null){
			fields=super.getColumnsInfo(ds,tableName);
			if(fields==null){
				fields=new ArrayList<>();
			}else {
				try{
					List<String> pks=super.getPrimaryKeys(ds,tableName);
					for (DmField field : fields) {
						if (pks.stream().filter(k -> k.equalsIgnoreCase(field.columnName)).findAny().isPresent()) {
							field.setPk(true);
						}
					}
				}catch (Exception e){
					logger.error("表格没有主键",e.getMessage());
				}
			}
			dataMetaCacheService.putFields(ds,tableName,fields);
		}
		return fields;
	}

	public List<DmField> getColumnsInfoBySqlTplFirstCache(String ds, String sqlTpl,List<Object> varValues) {
 		List<DmField> fields=dataMetaCacheService.getFields(ds,sqlTpl);
		if(fields==null){
			fields=this.getColumnsInfoBySqlTpl(ds,sqlTpl,varValues);
			if(fields==null){
				fields=new ArrayList<>();
			}
			dataMetaCacheService.putFields(ds,sqlTpl,fields);
		}
		return fields;
	}

	public List<DmField> getColumnsInfoBySqlFirstCache(String ds, String sql) {
		String sqlTem=sql.trim();
		SqlTplVo tplVo= Tpl.parse(sqlTem,false);
		List<DmField> fields=dataMetaCacheService.getFields(ds,tplVo.getSqlTpl());
		if(fields==null){
			fields=this.getColumnsInfoBySqlTpl(ds,tplVo.getSqlTpl(),tplVo.getVarValues());
			if(fields==null){
				fields=new ArrayList<>();
			}
			dataMetaCacheService.putFields(ds,tplVo.getSqlTpl(),fields);
		}
		return fields;
	}
	/**
	 *	获取sql语句的列信息
	 */
	@Override
	public  List<DmField> getColumnsInfoBySql(String ds,String dsSql){
		String sqlTem=dsSql;
		if(ObjectTools.isEmpty(dsSql)){
			throw new BizException("dsSql-required","sql必填");
		}else {
			sqlTem=sqlTem.trim();
		}
		SqlTplVo tplVo= Tpl.parse(sqlTem,false);
		Connection conn = getConnection(ds);
		ResultSet rs = null;
		try{
			PreparedStatement st = conn.prepareStatement(tplVo.getSqlTpl());
			ArrayList<DmField> columnsList = new ArrayList<DmField>();
			List<Object> varValues=tplVo.getVarValues();
			if(varValues!=null && varValues.size()>0){
				for (int i = 1; i <= varValues.size(); i++) {
					st.setObject(i,varValues.get(i-1));
				}
			}
			rs = st.executeQuery();
 			ResultSetMetaData rsmd = rs.getMetaData();

			for(int i=1; i<=rsmd.getColumnCount(); i++ )

			{
				DmField fieldInfo = new DmField();
				fieldInfo.setColumnName(rsmd.getColumnName(i));
 				fieldInfo.setDataType(rsmd.getColumnType(i));
				fieldInfo.setDataTypeName(rsmd.getColumnTypeName(i));
				fieldInfo.setColumnSize(rsmd.getColumnDisplaySize(i));
				fieldInfo.setDecimalDigits(rsmd.getScale(i));
				columnsList.add(fieldInfo);
			}
			return columnsList;
		}catch(SQLException ex){
			logger.error("",ex);
			throw new BizException(ex.getMessage());
		}finally {
			try {
				if(rs!=null && !rs.isClosed()){
					rs.close();
				}
				if(conn!=null && !conn.isClosed()){
					conn.close();
				}
			} catch (SQLException e) {
			}
		}
	}
	/**
	 *	获取sql语句的列信息
	 */
	@Override
	public  List<DmField> getColumnsInfoBySqlTpl(String ds,String dsSql,List<Object> varValues){
		String sqlTem=dsSql;
		if(ObjectTools.isEmpty(dsSql)){
			throw new BizException("dsSql-required","sql必填");
		}else {
			sqlTem=dsSql.trim();
		}

		Connection conn = getConnection(ds);
		ResultSet rs = null;
		try{
			PreparedStatement st = conn.prepareStatement(sqlTem);
			ArrayList<DmField> columnsList = new ArrayList<DmField>();
			if(varValues!=null && varValues.size()>0){
				for (int i = 1; i <= varValues.size(); i++) {
					st.setObject(i,varValues.get(i-1));
				}
			}
			rs = st.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();

			for(int i=1; i<=rsmd.getColumnCount(); i++ )

			{
				DmField fieldInfo = new DmField();
				fieldInfo.setColumnName(rsmd.getColumnName(i));
				fieldInfo.setDataType(rsmd.getColumnType(i));
				fieldInfo.setDataTypeName(rsmd.getColumnTypeName(i));
				fieldInfo.setColumnSize(rsmd.getColumnDisplaySize(i));
				fieldInfo.setDecimalDigits(rsmd.getScale(i));
				columnsList.add(fieldInfo);
			}
			return columnsList;
		}catch(SQLException ex){
			logger.error("",ex);
			throw new BizException(ex.getMessage());
		}finally {
			try {
				if(rs!=null && !rs.isClosed()){
					rs.close();
				}
				if(conn!=null && !conn.isClosed()){
					conn.close();
				}
			} catch (SQLException e) {
			}
		}
	}

	@Override
	public List<DmTable> getTableList(String ds, String tableName){
		if(ObjectTools.isEmpty(tableName)){
			tableName=null;
		}else {
			tableName=tableName.toUpperCase();
		}

		List<DmTable> tableInfos = new ArrayList<DmTable>();
		ResultSet rs = null;
		String catalog="";
		String schema="";
		Connection conn = getConnection(ds);
		try {

			/**
			 * 设置连接属性,使得可获取到列的REMARK(备注)

			 if(conn instanceof OracleConnection){
			 ((OracleConnection)conn).setRemarksReporting(true);
			 }
			 */
			catalog = conn.getCatalog();
			schema = conn.getSchema();
			if (!DsUtil.containsDataSource(ds)) {
				catalog = ds;
				schema = ds;
			}
			;
			DatabaseMetaData dbmd = conn.getMetaData();
			rs = dbmd.getTables(catalog, schema, tableName, null);
			while (rs.next()) {
				DmTable tableInfo=new DmTable()	;
				String tableRemarks=rs.getString("REMARKS");
				if(!ObjectTools.isEmpty(tableRemarks)){
					tableRemarks = tableRemarks.replaceAll("[ \\t\\n\\x0B\\f\\r]\\S*", "");

				}
				tableInfo.setTableName(rs.getString("TABLE_NAME"));
				tableInfo.setTableRemarks(tableRemarks);
				tableInfo.setTableOwner(rs.getString("TABLE_SCHEM"));
//				if(ObjectTools.isEmpty(tableInfo.getTableOwner())){
//					tableInfo.setTableOwner(rs.getString("SCHEMA"));
//				}
				tableInfos.add(tableInfo);
			}

			return tableInfos;
		}catch (Exception e){
			throw new BizException(e.getMessage());
		}finally {
			try {
				if(rs!=null && !rs.isClosed()){
					rs.close();
				}
				if(conn!=null && !conn.isClosed()){
					conn.close();
				}
			} catch (SQLException e) {
			}
		}
	}

}