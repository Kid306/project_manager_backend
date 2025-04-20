package com.mdp.dm.base.dao;

import com.mdp.core.DsUtil;
import com.mdp.core.entity.DmField;
import com.mdp.core.entity.DmTable;
import com.mdp.core.entity.LangTips;
import com.mdp.core.err.BizException;
import com.mdp.core.utils.DateUtils;
import com.mdp.core.utils.JdbcUtils;
import com.mdp.core.utils.ObjectTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Date;
import java.util.*;


/**
 * 
 * @author cyc 将entity的名字改成符合驼峰命名规范的名字 20150215
 * @see org.springframework.jdbc.support.JdbcUtils
 *
 */
public class DevJdbcDao{


	
	public Logger logger = LoggerFactory.getLogger(DevJdbcDao.class);



	public Connection getConnection() {
        try {
            return DsUtil.getDataSource().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
	public Connection getConnection(String ds) {
		try {
			if(ObjectTools.isEmpty(ds)){
				throw new BizException(LangTips.errMsg("ds-required","数据源%s不能为空","ds"));
			}
			DataSource dataSource= DsUtil.getDataSource(ds);
			if(dataSource==null){
				throw new BizException(LangTips.errMsg("ds-not-exists","%s数据源不存在",ds));
			}
			return dataSource.getConnection();
		} catch (SQLException e) {
			logger.error("db-connection-error",e);
			throw new RuntimeException("db-connection-error",e);
		}
	}



	/**获取表的主键名称**/
	public List<String> getPrimaryKeys(String ds,String tableName) {
		if(ObjectTools.isEmpty(tableName)){
			throw new BizException("tableName-required","表名必填");
		}
		ArrayList pklist = new ArrayList();
		Connection conn = getConnection(ds);
		String catalog="";
		String schema="";
		ResultSet rs = null;
		Map<String,Object> map=new HashMap<String,Object>();
		try {
			catalog=conn.getCatalog();
			schema=conn.getSchema();

			if(!DsUtil.containsDataSource(ds)){
				catalog=ds;
				schema=ds;
			};
			DatabaseMetaData dmd = conn.getMetaData();
			rs = dmd.getPrimaryKeys(catalog, schema, tableName.toUpperCase());
			while (rs.next()) {
				String primaryKey = rs.getString("COLUMN_NAME").toLowerCase();
				if(!map.containsKey(primaryKey)){
					pklist.add(primaryKey);
					map.put(primaryKey, primaryKey);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
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
		if(pklist.size()<=0){
			throw new RuntimeException("没有读取到表对应的主键,请检查表中是否存在主键,如已存在,请检查当前登录用户是否有权限读取.表名:"+tableName+" ,表所属用户:"+schema);
		}
		return pklist;
	}



    
   /**
	*	获取列信息
    */
   public  List<DmField> getColumnsInfo(String ds, String tableName){
	   if(ObjectTools.isEmpty(tableName)){
		   throw new BizException("tableName-required","表名必填");
	   }else {
		   tableName=tableName.toUpperCase();
	   }
		Connection conn = getConnection(ds);
		ResultSet rs = null;
	   String catalog="";
	   String schema="";
	   try{
		 /**
		  * 设置连接属性,使得可获取到列的REMARK(备注)

		   if(conn instanceof OracleConnection){
			   ((OracleConnection)conn).setRemarksReporting(true);
		   }
		   */
		   catalog=conn.getCatalog();
		   schema=conn.getSchema();

		   if(!DsUtil.containsDataSource(ds)){
			   catalog=ds;
			   schema=ds;
		   };
		  DatabaseMetaData dbmd = conn.getMetaData();
		 /**
		  * 获取可在指定类别中使用的表列的描述。
		  * 方法原型:ResultSet getColumns(String catalog,String schemaPattern,String tableNamePattern,String columnNamePattern)
		  * catalog - 表所在的类别名称;""表示获取没有类别的列,null表示获取所有类别的列。
		  * schema - 表所在的模式名称(oracle中对应于Tablespace);""表示获取没有模式的列,null标识获取所有模式的列; 可包含单字符通配符("_"),或多字符通配符("%");
		  * tableNamePattern - 表名称;可包含单字符通配符("_"),或多字符通配符("%");
		  * columnNamePattern - 列名称; ""表示获取列名为""的列(当然获取不到);null表示获取所有的列;可包含单字符通配符("_"),或多字符通配符("%");
		  */
		 catalog=ObjectTools.isEmpty(catalog)?null:catalog.toUpperCase();
		 schema=ObjectTools.isEmpty(schema)?null:schema.toUpperCase();
		 rs =dbmd.getColumns(catalog, schema, tableName.toUpperCase(), null);
		 Map<String,Object> map=new HashMap<String,Object>();
		 return resultSetToFieldInfo(rs,map);
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


	public DmTable getTable(String ds, String tableName){
		if(ObjectTools.isEmpty(tableName)){
			throw new BizException("tableName-required","表名不能为空");
		}else {
			tableName=tableName.toUpperCase();
		}

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
			DmTable tableInfo=new DmTable()	;
			while (rs.next()) {

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
			}

			return tableInfo;
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

   public List<DmField> resultSetToFieldInfo(ResultSet rs , Map<String,Object> fieldMap) throws SQLException {

	   ArrayList<DmField> columnsList = new ArrayList<DmField>();
	   while(rs.next()){
		   DmField fieldInfo = new DmField();
		   String tableCat = rs.getString("TABLE_CAT"); // 表类别（可能为空）,mysql中为数据库名称
		   //fieldInfo.setTableSchemaName(rs.getString("TABLE_SCHEM")); // 表模式（可能为空）,在oracle中获取的是命名空间,其它数据库未知
		   fieldInfo.setTableName(rs.getString("TABLE_NAME").toLowerCase()); // 表名
		   String columnName=rs.getString("COLUMN_NAME").toLowerCase();
		   if(fieldMap.containsKey(columnName)){
			   continue;
		   }else{
			   fieldMap.put(columnName,columnName);
		   }
		   fieldInfo.setColumnName(columnName); // 列名
		   fieldInfo.setDataType(rs.getInt("DATA_TYPE")); // 对应的java.sql.Types的SQL类型(列类型ID)
		   fieldInfo.setDataTypeName(rs.getString("TYPE_NAME")); // java.sql.Types类型名称(列类型名称)

		   fieldInfo.setColumnSize(rs.getInt("COLUMN_SIZE")); // 列大小
		   fieldInfo.setDecimalDigits(rs.getInt("DECIMAL_DIGITS")); // 小数位数
		   fieldInfo.setNumPrecRadix(rs.getInt("NUM_PREC_RADIX")); // 基数（通常是10或2）
		   String dataTypeName=rs.getString("TYPE_NAME");
		   String fjcn= JdbcUtils.convertColumnTypeToJavaType(dataTypeName,fieldInfo.getDecimalDigits());
		   //fieldInfo.setFullJavaClassName(fjcn);
		   // --未知
		   /**
			* 0 (columnNoNulls) - 该列不允许为空 1 (columnNullable) - 该列允许为空 2
			* (columnNullableUnknown) - 不确定该列是否为空
			*/
		   fieldInfo.setNullAble(rs.getInt("NULLABLE")); // 是否允许为null
		   String remarks=rs.getString("REMARKS");
		   if(!StringUtils.hasText(remarks)){
			   remarks=columnName;
		   }
		   fieldInfo.setRemarks(remarks); // 列描述
		   //fieldInfo.setChinaName(remarks); // 列描述作为中文名字
		   fieldInfo.setColumnDef(rs.getString("COLUMN_DEF")); // 默认值
		   fieldInfo.setCharOctetLength(rs.getInt("CHAR_OCTET_LENGTH")); // 对于 char
		   // 类型，该长度是列中的最大字节数
		   fieldInfo.setOrdinalPosition(rs.getInt("ORDINAL_POSITION")); // 表中列的索引（从1开始）
		   /**
			* ISO规则用来确定某一列的是否可为空(等同于NULLABLE的值:[ 0:'YES'; 1:'NO'; 2:''; ])
			* YES -- 该列可以有空值; NO -- 该列不能为空; 空字符串--- 不知道该列是否可为空
			*/
		   fieldInfo.setIsNullAble(rs.getString("IS_NULLABLE"));
		   /**
			* 不是标准，报错，不能使用
			*/
		   //fieldInfo.setPk(rs.getBoolean("IS_PRIMARY_KEY"));

		   /**
			* 指示此列是否是自动递增 YES -- 该列是自动递增的 NO -- 该列不是自动递增 空字串---
			* 不能确定该列是否自动递增
			*/
		   // String isAutoincrement = rs.getString("IS_AUTOINCREMENT");
		   // //该参数测试报错
		   //fieldInfo=genDataDao.createTestData(fieldInfo);
		   columnsList.add(fieldInfo);
	   }
	   return columnsList;
   }

   /**
    * 
    * @param menuName 菜单中文名字
    * @param url 菜单入口url 如 system/menu/listMenu
    * @param parentid 父菜单菜单编号 生成代码时,一般挂到80 示例代码下
    */
   public String createMenu(String menuid,String menuName,String url,String parentid){
	   Date d=new Date();
	   //insert("insert into cli_menu (menutype,menuid,name,url,parentid,loperate_person,loperate_date) values (?,?,?,?,?,?,?)","A", menuid,menuName,url,parentid,"",d);
	   String sql="insert into cli_menu (menutype,menuid,name,url,parentid,loperate_person,loperate_date) values ('A','"+menuid+"','"+menuName+"','"+url+"','"+parentid+"','"+ DateUtils.format(d, "yyyyMMddHHmmss")+"')";
	   insert(sql);
	   return sql;
   }
   
   public void insert(String sql){
	   Connection conn = getConnection();
	Statement st = null;
	
	try{
		 
		//得到运行环境
		st = conn.createStatement();

		//执行SQL
		 st.executeUpdate(sql);
 
	}catch(Exception ex){
		 logger.error("",ex);
	}finally{
		//释放资源
		if(st != null){
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				st = null;//--> 让他迅速成为java gc的对象
			}
		}
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				conn = null;//--> 让他迅速成为java gc的对象
			}
		}
	}
   }
}