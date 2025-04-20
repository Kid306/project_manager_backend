package com.mdp.core.query;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mdp.core.JdbcTypeMapping;
import com.mdp.core.SpringUtils;
import com.mdp.core.TplParse;
import com.mdp.core.api.ContextEnvService;
import com.mdp.core.dao.TableUtil;
import com.mdp.core.entity.DmField;
import com.mdp.core.err.BizException;
import com.mdp.core.service.DefaultQueryEnvService;
import com.mdp.core.utils.BaseUtils;
import com.mdp.core.utils.Const;
import com.mdp.core.utils.NumberUtil;
import com.mdp.core.utils.ObjectTools;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
public class QueryTools {

	private static ContextEnvService envService =null;

	public static final String ALIAS_KEY="$alias_rules";
	public static final String BEGIN = "$begin";
	public static final String END = "$end";
	
	public static final String PKS = "$pks";
	/**
	 * 数字类型字段，拼接此后缀 接受多值参数
	 */
	public static final String IN = "$in";
	public static final String NIN = "$nin";
	public static final String BETWEEN = "$between";

	public static final String STAR = "*";
	public static final String COMMA = ",";

	public static final String NOT_EQUAL = "!";
	/**页面带有规则值查询，空格作为分隔符*/
	public static final String QUERY_SEPARATE_KEYWORD = " ";
	/**高级查询前端传来的参数名*/
	public static final String HI_QUERY_PARAMS = "hiQueryParams";
	/** 单引号 */
	public static final String SQL_SQ = "'";

	/**排序列 orderBy=field1 asc,field2 desc*/
	// add by cyc 20230717
	public static final String ORDER_BY = "$orderBy";

	/**mysql 模糊查询之特殊字符下划线 （_、\）*/
	public static final String LIKE_MYSQL_SPECIAL_STRS = "_,%";

	public static  AntPathMatcher antPathMatcher=new AntPathMatcher();

	public static ObjectMapper o=new ObjectMapper();

	public static Logger logger=LoggerFactory.getLogger(QueryTools.class);

	/**时间格式化 */
	public static final ThreadLocal<SimpleDateFormat> local = new ThreadLocal<SimpleDateFormat>();
	public static SimpleDateFormat getTime(){
		SimpleDateFormat time = local.get();
		if(time == null){
			time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			local.set(time);
		}
		return time;
	}

	public static ContextEnvService getEnvService() {
		ContextEnvService envBean=null;
		try {
			envBean=SpringUtils.getBean(ContextEnvService.class);
		}catch (Exception e){

		}

		if(envBean==null){
			envBean=new DefaultQueryEnvService();
		}
		envService=envBean;
		return envBean;
	}

	/**
	 * 将前端上传的数组转换成数组
	 * 如 branch[]=1,2,3,4,5 ==> branch=[1,2,3,4,5]
	 * @param params
	 */
	public static void transToList(Map<String,Object> params){
		if(params==null || params.isEmpty()){
			return;
		}
		Set<String> keys=params.keySet().stream().collect(Collectors.toSet());
		for (String key : keys) {
			if(!key.endsWith("[]")){
				continue;
			}
			String newKey=key.substring(0,key.length()-2);
			Object value=params.get(key);
			if(ObjectTools.isEmpty(value)) {
				continue;
			}
			if(value instanceof String){
				String val=(String)value;
				if(val.indexOf("[")==0) {
					try {
						ArrayList[] valus=o.readValue(val, ArrayList[].class);
						params.put(newKey, valus);
						params.remove(key);
					} catch (JsonParseException e) {
						logger.error("",e);
					} catch (JsonMappingException e) {
						logger.error("",e);
					} catch (IOException e) {
						logger.error("",e);
					}

				}else {
					params.put(newKey, Arrays.asList(val.split(",")));
					params.remove(key);
				}
			}else{
				params.put(newKey, value);
				params.remove(key);
			}
		}
	}

	public static IPage initPage(){
		IPage page=new Page(1,15000,0L,false);
		return page;
	}
	public static IPage initPage(Map<String, Object> parameterMap){
		if(parameterMap!=null && parameterMap.containsKey("pageNum")){
			Integer pageNum=NumberUtil.getInteger(getValFromMap(parameterMap,"pageNum"),1);
			Integer pageSize=NumberUtil.getInteger(getValFromMap(parameterMap,"pageSize"),1000);
			Long total=NumberUtil.getLong(getValFromMap(parameterMap,"total"),0L);
			Boolean count=getBooleanVal(getValFromMap(parameterMap,"count"));
			if(count==true){
				total=0L;
			}

			IPage page=new Page(pageNum,pageSize,total,total<=0L);
			return page;
		}else{
			IPage page=new Page(1,1500,0,false);
			return page;
		}
	}

	public static Boolean getBooleanVal(Object val){
		if(val instanceof String){
			if("1".equals(val)){
				return true;
			}else if("true".equals(val)){
				return true;
			}
		}else if(val instanceof Integer) {
			if(1==(Integer)val){
				return true;
			}
		}else if(val instanceof Boolean){
			return (Boolean) val;
		}
		return false;
	}
	public static IPage initPage(Integer pageNum,Integer pageSize,Long total,boolean searchCount){
		if(pageNum!=null && pageNum>=1){
			IPage page=new Page(pageNum,pageSize==null?10:pageSize,total,searchCount);
			return page;
		}else{
			IPage page=new Page(1,15000,0L,false);
			return page;
		}
	}
	/**
	 * @return QueryWrapper实例
	 */
	public static <T> QueryWrapper<T> initQueryWrapper(Class<T> clazz){
		QueryWrapper<T> queryWrapper=new QueryWrapper<T>();
		queryWrapper.setEntityClass(clazz);
		 return queryWrapper;
	}
	/**
	 * @return Wrapper实例
	 */
	public static <T> UpdateWrapper<T> initUpdateWrapper(Class<T> clazz){
		UpdateWrapper<T> queryWrapper=new UpdateWrapper<T>();
		queryWrapper.setEntityClass(clazz);
		return queryWrapper;
	}
	/**
	 * 获取查询条件构造器QueryWrapper实例 通用查询条件已被封装完成
	 * @param clazz 实体类的类型，用于过滤不属于该类的属性
	 * @param unIgnoreColumnName 符合规则的将不会被排除出where语句中，ant表达式，如 xxxx*表示以xxxx开头的，如 res.*代表以res.开头,*end代表以end结尾
 	 * @param parameterMap request.getParameterMap()
	 * @return QueryWrapper实例
	 */
	public static <T> QueryWrapper<T> initQueryWrapper(Class<T> clazz,Map<String, Object> parameterMap,String ...unIgnoreColumnName){
		try {
			T t= clazz==null?null:clazz.newInstance();
			transToList((Map<String, Object>) parameterMap);
			return initQueryWrapper(t,parameterMap,unIgnoreColumnName);
		} catch (InstantiationException e) {
			log.error("",e);
			throw new BizException(e.getMessage());
		} catch (IllegalAccessException e) {
			log.error("",e);
			throw new BizException(e.getMessage());
		}

	}
	/**
	 * 获取查询条件构造器QueryWrapper实例 通用查询条件已被封装完成
	 * @param entity 查询实体
	 * @param unIgnoreColumnName 符合规则的将不会被排除出where语句中，ant表达式，如 xxxx*表示以xxxx开头的，如 res.*代表以res.开头,*end代表以end结尾
	 * @param parameterMap request.getParameterMap()
	 * @return QueryWrapper实例
	 */
	public static <T> QueryWrapper<T> initQueryWrapper(T entity,Map<String, Object> parameterMap,String ...unIgnoreColumnName){
		long start = System.currentTimeMillis();
		QueryWrapper<T> queryWrapper = new QueryWrapper<T>();
		initQwByParams(queryWrapper, entity, parameterMap,unIgnoreColumnName);
		log.debug("---查询条件构造器初始化完成,耗时:"+(System.currentTimeMillis()-start)+"毫秒----");
		return queryWrapper;
	}

	/**
	 * 获取查询条件构造器QueryWrapper实例 通用查询条件已被封装完成
	 * @param tableFields 表格字段信息
	 * @param unIgnoreColumnName 符合规则的将不会被排除出where语句中，ant表达式，如 xxxx*表示以xxxx开头的，如 res.*代表以res.开头,*end代表以end结尾
	 * @param parameterMap request.getParameterMap()
	 * @return QueryWrapper实例
	 */
	public static <T> QueryWrapper<T> initDmQw(List<DmField> tableFields, Map<String, Object> parameterMap, String ...unIgnoreColumnName){
		long start = System.currentTimeMillis();
		QueryWrapper<T> queryWrapper = new QueryWrapper<T>();
		initDmQwByParams(queryWrapper, tableFields, parameterMap,unIgnoreColumnName);
		log.debug("---查询条件构造器初始化完成,耗时:"+(System.currentTimeMillis()-start)+"毫秒----");
		return queryWrapper;
	}


	/**
	 *
	 * @param columns 如 branchId res.branchId 以空格隔开
	 */
	public static void alias(Map<String,Object> params,String ...columns){

		if(columns==null){
		 	return;
		 }
		 if(params==null){
		 	return;
		 }
		Map<String,String> rules= (Map<String,String>) params.get(ALIAS_KEY);
		if(rules==null){
			rules=new HashMap<>();
			params.put(ALIAS_KEY,rules);
		}
		for (String column : columns) {
			if(StringUtils.isEmpty(column)){
				continue;
			}
			String colname=column.replaceAll("  "," ");
			String[] colStr=colname.split(" ");
			if(colStr.length==2){
				 rules.put(ObjectTools.camelName(colStr[0]),colStr[1]);
			}
		}


	}


	/**
	 *
	 * @param clomn
	 * @param unIgnoreCloumns 符合规则的将不会被排除出where语句中，ant表达式，如 xxxx*表示以xxxx开头的，如 res.*代表以res.开头,*end代表以end结尾
	 * @return
	 */
	public static boolean isUnIgnoreColumnName(String clomn,List<String> unIgnoreCloumns){
		if(unIgnoreCloumns==null){
			return false;
		}
		for (String unIgnoreCloumn : unIgnoreCloumns) {
			if(antPathMatcher.match(unIgnoreCloumn,clomn)){
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		String val="*ddd*";
		if(val.indexOf("*")>=0){
			if(val.startsWith("*")){
				val=val.substring(1);
			}
			if(val.endsWith("*")){
				val=val.substring(0,val.length()-1);
			}
		}

		List<String> un=new ArrayList<>();
		un.add("s.branch_id");
		un.add("ext(.*)");

		boolean is=isUnIgnoreColumnName("s.bRanch_id",un);
		System.out.println("xxxxx  "+is);


		is=isUnIgnoreColumnName("extColumn1",un);
		System.out.println("extColumn1  "+is);

		String name="res.resxxx$end...begin";
		String name2=name.replaceAll("[$]((begin)|(end)|(in))","");
		System.out.println("name2  "+name2);

		String name3=name.replaceAll("[.]{2,}",".");
		name3=name3.replaceAll("^([^.]*[.])","");
		System.out.println("name3  "+name3);


		AntPathMatcher antPathMatcher=new AntPathMatcher();
		System.out.printf(" extColumn mathc ext* -->"+String.valueOf(antPathMatcher.match("ext*","extColumn")));

		System.out.printf(" res.extColumn mathc res.* -->"+String.valueOf(antPathMatcher.match("res.*","res.extColumn")));;

		System.out.printf(" res.res.extColumn mathc res.* -->"+String.valueOf(antPathMatcher.match("res.*","res.res.extColumn")));;

		System.out.printf(" aaaa$end mathc *$end -->"+String.valueOf(antPathMatcher.match("*$end","aaaa$end")));;

		String name333="res.xxx.ddd";
		int idx=name333.lastIndexOf(".");
		String name44=name333.substring(idx+1);
		String name555=name333.substring(0,idx+1);
		System.out.printf("name555"+name555);



	}

	public static TableFieldInfo getTableFieldInfo(String name,List<TableFieldInfo> fieldInfos){
		return getTableFieldInfo(name,fieldInfos,null);
	}

	public static DmField getDmField(String name,List<DmField> fieldInfos ){
		if(fieldInfos==null){
			return null;
		}
		if(name==null){
			return null;
		}
		String n1=name.toLowerCase(Locale.ENGLISH);

		n1=n1.replaceAll("[.]{2,}",".");
		String name2=n1.replaceAll("^([^.]*[.])","");
		String finalName = name2.replaceAll("_","");
		boolean hasPrefix=name.indexOf(".")>0;
		for (DmField fieldInfo : fieldInfos) {
			String columnOrign=fieldInfo.getColumnName();
			String property=ObjectTools.camelName(columnOrign);
			if(name.equalsIgnoreCase(property) || name.equalsIgnoreCase(columnOrign)){
				return fieldInfo;
			}else if(hasPrefix){
				if(finalName.equalsIgnoreCase(property) || finalName.equalsIgnoreCase(columnOrign.replaceAll("_",""))){
					return fieldInfo;
				}
			}
		}
		return null;

	}
	public static TableFieldInfo getTableFieldInfo(String name,List<TableFieldInfo> fieldInfos,String tableName ){
		if(fieldInfos==null){
			return null;
		}
		if(name==null){
			return null;
		}
		String n1=name.toLowerCase(Locale.ENGLISH);

		n1=n1.replaceAll("[.]{2,}",".");
		String name2=n1.replaceAll("^([^.]*[.])","");
		String finalName = name2.replaceAll("_","");
		boolean hasPrefix=name.indexOf(".")>0;
		for (TableFieldInfo fieldInfo : fieldInfos) {
			String columnOrign=fieldInfo.getColumn();
			String property=fieldInfo.getProperty();
			if(name.equalsIgnoreCase(property) || name.equalsIgnoreCase(columnOrign)){
				return fieldInfo;
			}else if(hasPrefix){
				if(finalName.equalsIgnoreCase(property) || finalName.equalsIgnoreCase(columnOrign.replaceAll("_",""))){
					return fieldInfo;
				}
			}
		}
		return null;

	}

	public static TableFieldInfo getTableFieldInfoByCondition(String colPrefix,String property,List<TableFieldInfo> fieldInfos,String tableName){
		 return getTableFieldInfo(ObjectTools.isNotEmpty(colPrefix)?colPrefix+"."+property:property,fieldInfos,tableName);
	}
	public static DmField getTableFieldInfoByCondition(String colPrefix,String property,List<DmField> fieldInfos){
		return getDmField(ObjectTools.isNotEmpty(colPrefix)?colPrefix+"."+property:property,fieldInfos);
	}

	/**
	 * 组装Mybatis Plus 查询条件
	 */
	protected static void initDmQwByParams(QueryWrapper<?> queryWrapper, List<DmField> tableFields, Map<String, Object> parameterMap, String ...unIgnoreColumnName) {

		List<String> unIgnoreColumnNameList=ObjectTools.isEmpty(unIgnoreColumnName)? Arrays.asList() : Arrays.asList(unIgnoreColumnName);

		if(parameterMap!=null){
			List<DmField> allFields= tableFields;
			String name, type, column;
			for (Map.Entry<String, ?> kv : parameterMap.entrySet()) {
				{
					DmField fieldInfo=getDmField(kv.getKey(),allFields);
					DmField field=null;
					if(fieldInfo!=null){
						field=fieldInfo;
					}

					name = kv.getKey();
					type = field!=null? JdbcTypeMapping.jdbcType2javaType(field.getDataTypeName()).getSimpleName() :"String";
					boolean isUnIgo=isUnIgnoreColumnName(name,unIgnoreColumnNameList);
					try {
						if(  fieldInfo==null && !isUnIgo){
							continue;
						}
						if ( !isUnIgo && (isInnerKeyName(name))) {
							continue;
						}
						Object value=getValFromMap(parameterMap,name);
						if(fieldInfo==null){
							column=propertyNameToClolumnName(name);
						}else{
							column=fieldInfo.getColumnName();
							int idx=name.lastIndexOf(".");
							if(idx>0){
								column=name.substring(0,idx+1)+column;
							}
						}
						if(ObjectTools.isEmpty(column)){
							continue;
						}
						QueryCondition curr = convert2QueryConditionByValue(type,name,column,value);
						if(curr==null|| ObjectTools.isEmpty(curr.getSqlOper())){
							continue;
						}
						initQwByCondition(queryWrapper,curr, parameterMap);

					} catch (Exception e) {
						log.error(e.getMessage(), e);
						throw new BizException(e.getMessage());
					}
				}
			}
		}else{
			return;
		}

		// 排序逻辑 处理
		doMultiFieldsOrder(queryWrapper, parameterMap);

		//高级查询
		doHiQuery(queryWrapper, parameterMap,null,tableFields,unIgnoreColumnName);

	}

	/**
	 * 组装Mybatis Plus 查询条件
	 */
	protected static void initQwByParams(QueryWrapper<?> queryWrapper, Object searchObj, Map<String, Object> parameterMap, String ...unIgnoreColumnName) {
		List<String> unIgnoreColumnNameList=ObjectTools.isEmpty(unIgnoreColumnName)? Arrays.asList() : Arrays.asList(unIgnoreColumnName);

		if(parameterMap!=null){
 			List<TableFieldInfo> allFields= TableUtil.getAllFields(searchObj.getClass());
			String tableName=TableUtil.getTableName(searchObj.getClass());
			Map<String,String> aliasRules= (Map<String, String>) parameterMap.get(ALIAS_KEY);
			boolean hasAlias=aliasRules!=null&& !aliasRules.isEmpty();
			boolean hasAllAlias=false;
			String allAlias="";
			if(hasAlias){
				allAlias=aliasRules.get("*");
				hasAllAlias=ObjectTools.isNotEmpty(allAlias);
 				if(!hasAllAlias){
					allAlias="";
				}else{
					if( !allAlias.endsWith(".")){
						allAlias=allAlias+".";
					}
				}
			}
			String name, type, column;
			for (Map.Entry<String, ?> kv : parameterMap.entrySet()) {
				{
					TableFieldInfo fieldInfo=getTableFieldInfo(kv.getKey(),allFields,tableName);
					Field field=null;
					if(fieldInfo!=null){
						field=fieldInfo.getField();
						field.setAccessible(true);
					}

					name = kv.getKey();
					if(name==null){
						continue;
					}
					type = field!=null?field.getType().toString() :"String";
					boolean isUnIgo=isUnIgnoreColumnName(name,unIgnoreColumnNameList);
					try {
						if( searchObj!=null && fieldInfo==null && !isUnIgo){
							continue;
						}
						if ( !isUnIgo && (isInnerKeyName(name))) {
							continue;
						}
						Object value=null;
						if(parameterMap!=null){
							value=getValFromMap(parameterMap,name);
						}
						if(fieldInfo==null){
							column=propertyNameToClolumnName(name);
							int idx=name.lastIndexOf(".");
							if(idx>0){
								//
							}else{
								if(hasAlias){
									String alias=aliasRules.get(name);
									if(ObjectTools.isNotEmpty(alias)){
										int idx2=alias.lastIndexOf(".");
										if(idx2>0){
											column=alias.substring(0,idx2+1)+BaseUtils.underscoreName(alias.substring(idx2+1));
										}else{
											column=BaseUtils.underscoreName(alias);
										}
									}else if(hasAllAlias){
										column=allAlias+column;
									}
								}
							}
						}else{
							column=fieldInfo.getColumn();
							int idx=name.lastIndexOf(".");
							if(idx>0){
								column=name.substring(0,idx+1)+column;
							}else{
								if(hasAlias){
									String alias=aliasRules.get(name);
									if(ObjectTools.isNotEmpty(alias)){
										int idx2=alias.lastIndexOf(".");
										if(idx2>0){
											column=alias.substring(0,idx2+1)+BaseUtils.underscoreName(alias.substring(idx2+1));
										}else{
											column=BaseUtils.underscoreName(alias);
										}
									}else if(hasAllAlias){
										column=allAlias+column;
									}
								}
							}
						}
 						if(ObjectTools.isEmpty(column)){
							continue;
						}
							QueryCondition curr = convert2QueryConditionByValue(type,name,column,value);
							if(curr==null|| ObjectTools.isEmpty(curr.getSqlOper())){
								continue;
							}
							initQwByCondition(queryWrapper,curr, parameterMap);

					} catch (Exception e) {
						log.error(e.getMessage(), e);
						throw new BizException(e.getMessage());
					}
				}
			}
		}else if(searchObj!=null){
			List<TableFieldInfo> allFields=TableUtil.getAllFields(searchObj.getClass());
			String name, type, column;
			for (TableFieldInfo fieldInfo : allFields) {
				Field field=fieldInfo.getField();
				field.setAccessible(true);
				name = field.getName();
				type = field.getType().getTypeName();
				try {
					if (isInnerKeyName(name)|| !PropertyUtils.isReadable(searchObj, name)) {
						continue;
					}
					Object value=field.get(searchObj);
					column = fieldInfo.getColumn();
					if(ObjectTools.isEmpty(column)){
						//column为null只有一种情况 那就是 添加了注解@TableField(exist = false) 后续都不用处理了
						continue;
					}
					QueryCondition curr = convert2QueryConditionByValue(type,name,column,value);
					if(curr==null || ObjectTools.isEmpty(curr.getSqlOper())){
						continue;
					}
					initQwByCondition(queryWrapper,curr, parameterMap);
				} catch (Exception e) {
					log.error(e.getMessage(), e);
					throw new BizException(e.getMessage());
				}
			}
		}

		// 排序逻辑 处理 
		doMultiFieldsOrder(queryWrapper, parameterMap);
				
		//高级查询
		doHiQuery(queryWrapper, parameterMap,searchObj.getClass(),null,unIgnoreColumnName);

	}

	protected static QueryCondition convert2QueryConditionByValue(String type, String name, String column, Object value) {
		if(ObjectTools.isEmpty(value)){
			return null;
		}
		QueryCondition curr=new QueryCondition();
		int idx=column.lastIndexOf(".");
		if(idx>0){
			curr.setProperty(column.substring(idx+1));
			curr.setColPrefix(column.substring(0,idx+1));
		}else{
			curr.setProperty(column);
		}
		curr.setColType(type);
		SqlOper oper=convert2OperByValue(value);
		if(oper==null){
			return null;
		}
		curr.setSqlOper(oper.getValue());
		Object value2=trimOper(value);
		if(SqlOper.BETWEEN.eq(oper)|| SqlOper.IN.eq(oper)|| SqlOper.NOT_IN.eq(oper)){
			Object[] arr=transObjectToArray(value2);
			if(arr==null || arr.length==0){
				return null;
			}
			if(SqlOper.BETWEEN.eq(oper)){
				curr.setSqlVal(arr.length>0?arr[0]:"");
				curr.setEndVal(arr.length>1?arr[1]:"");
			}else if(SqlOper.IN.eq(oper)|| SqlOper.NOT_IN.eq(oper)){
				curr.setSqlVal(arr);
			}

		}else{
			curr.setSqlVal(value2);
		}
		return curr;
	}

	public static String propertyNameToClolumnName(String propertyName){

		String name2=propertyName.replace("[]","");
		return BaseUtils.underscoreName(name2);
	}



	protected static Object getValFromMap(Map<String, Object> parameterMap, String name) {
		if(parameterMap==null){
			return null;
		}
		if(name==null){
			return null;
		}
		Object vs=parameterMap.get(name);;
		if(vs==null){
			return null;
		}else{
			if(vs instanceof String){
				String value= TplParse.parse((String)vs,parameterMap,getEnvService().getAll());
				return value;
			}
		}
		return vs;
	}


	/**
	 * 多字段排序 change by cyc 20230717
	 * 前端上送 orderBy=field1,field2 desc|asc
	 */
	protected static void doMultiFieldsOrder(QueryWrapper<?> queryWrapper,Map<String, Object> parameterMap) {
		String  orderBy=null;
		if(parameterMap!=null&& parameterMap.containsKey(ORDER_BY)) {
			orderBy = (String) getValFromMap(parameterMap,ORDER_BY);
		}
		if(orderBy==null){
			return;
		}

		orderBy=orderBy.replaceAll("  "," ");
			String[] obs=orderBy.split(",");

			for (String ob : obs) {
				String[] ob2=ob.split(" ");
				String column=propertyNameToClolumnName(ob2[0]);
				if(ob2.length==1){
					queryWrapper.orderByAsc(column);
				}else  if(ob2.length>1){
					if(ob2[1].toUpperCase().contains("ASC")){
						queryWrapper.orderByAsc(column);
					}else {
						queryWrapper.orderByDesc(column);
					}
				}
			}
	}
	
	/**
	 * 高级查询
	 * @param queryWrapper 查询对象
	 * @param parameterMap 参数对象
	 */
	protected static void  doHiQuery(QueryWrapper<?> queryWrapper, Map<String, Object> parameterMap, Class<?> clazz,List<DmField> tableFields,String ...unIgnoreColumnName) {
		if(parameterMap!=null&& parameterMap.containsKey(HI_QUERY_PARAMS)){
			Object hiQueryParamsObj =getValFromMap(parameterMap, HI_QUERY_PARAMS);
 			QueryCondition condition=null;
			if(hiQueryParamsObj instanceof String){
				String hiQueryParams=(String)hiQueryParamsObj;
				try {
					hiQueryParams = URLDecoder.decode(hiQueryParams, "UTF-8");
					condition = JSON.parseObject(hiQueryParams, QueryCondition.class);
				} catch (UnsupportedEncodingException e) {
					log.error("--高级查询参数转码失败：" + hiQueryParams, e);
					throw new BizException(e.getMessage());
				}
			}else if(hiQueryParamsObj instanceof QueryCondition){
				condition=(QueryCondition)hiQueryParamsObj;
			}else if(hiQueryParamsObj instanceof Map){
				condition=BaseUtils.fromMap((Map) hiQueryParamsObj, QueryCondition.class);
			}else if(hiQueryParamsObj instanceof QueryCondition[]){
				condition=new QueryCondition();
				condition.setSqlLink(SqlLink.AND.getValue());
				condition.setChildren((QueryCondition[])hiQueryParamsObj);
			}else if(hiQueryParamsObj instanceof Collection){
				Collection his=(Collection)hiQueryParamsObj;
				condition=new QueryCondition();
				condition.setSqlLink(SqlLink.AND.getValue());
				QueryCondition[] conditions=new QueryCondition[his.size()];
				int i=0;
				for (Object hi : his) {
					if(hi instanceof QueryCondition){
						conditions[i]=((QueryCondition)hi);
					}else if(hi instanceof Map){
						conditions[i]=(BaseUtils.fromMap((Map) hi, QueryCondition.class));
					}
					i=i+1;
				}
				condition.setChildren(conditions);
			}else if(hiQueryParamsObj instanceof Map[]){
				Map<String,Object>[] maps=(Map<String, Object>[])hiQueryParamsObj;
				condition=new QueryCondition();
				condition.setSqlLink(SqlLink.AND.getValue());
				QueryCondition[] conditions=new QueryCondition[maps.length];
				int i=0;
				for (Map<String, Object> map : maps) {
					conditions[i]=(BaseUtils.fromMap(map, QueryCondition.class));
					i=i+1;
				}
 			}
            try {

            	if (condition == null) {
                    return;
                }
                 condition=filterUnRightCondition(condition);
				if (condition == null) {
					return;
				}
                if(clazz!=null){
					initQwByConditions(queryWrapper,parameterMap,SqlLink.get(condition.getSqlLink()),condition.getChildren(),clazz,unIgnoreColumnName);
				}else if(tableFields!=null && tableFields.size()>0){
					initQwByConditions(queryWrapper,parameterMap,SqlLink.get(condition.getSqlLink()),condition.getChildren(),tableFields,unIgnoreColumnName);
				}
			} catch (Exception e) {
                log.error("--高级查询拼接失败：" + e.getMessage());
                e.printStackTrace();
            }
		}
	}

	public static Object[] transObjectToArray(Object value){
		if(ObjectTools.isEmpty(value)){
			return null;
		}
		if(value instanceof String){
			return ((String) value).split(",");
		}else if(value instanceof String[]){
			return (String[]) value;
		}else if(value instanceof int[]){
			return Arrays.stream(((int[]) value)).boxed().toArray(Integer[]::new);
		}else if(value instanceof double[]){
			return Arrays.stream(((double[]) value)).boxed().toArray(Double[]::new);
		}else if(value instanceof long[]){
			return Arrays.stream(((long[]) value)).boxed().toArray(Long[]::new);
		}else if(value instanceof float[]){
			float[] arr=((float[]) value);

			Float[] fLoats=new Float[arr.length];
			for (int i = 0; i < arr.length; i++) {
				fLoats[i]=Float.valueOf(arr[i]);
			}
			return fLoats;
		}else if(value instanceof BigDecimal[]){
			return (BigDecimal[])value;
		}else if(value instanceof Collection){
			return ((Collection) value).toArray();
		}else if(value.getClass().isArray()){
			return (Object[]) value;
		}else{
			return Arrays.asList(value).toArray();
		}
	}
	
	public static void initQwByCondition(QueryWrapper<?> qw, QueryCondition curr, Map<String, Object> parameterMap){
		if(curr.getChildren()!=null &&  curr.getChildren().length>0){
			return;
		}else{

			if(ObjectTools.isEmpty(curr.getColPrefix())){
				curr.setColPrefix("");
			}
			String columnName=propertyNameToClolumnName(curr.getColPrefix()+curr.getProperty());
			Object value=curr.getSqlVal();
			if(value!=null && value instanceof String){
				if(parameterMap==null){
					value = TplParse.parse((String)value,getEnvService().getAll());
				}else{
					value = TplParse.parse((String)value,parameterMap,getEnvService().getAll());
				}

			}
			if(SqlOper.IN.eq(curr.getSqlOper()) || SqlOper.NOT_IN.eq(curr.getSqlOper())){
				qw.in(columnName,transObjectToArray(value));
			}else if(SqlOper.BETWEEN.eq(curr.getSqlOper())){
				qw.between(columnName,curr.getSqlVal(),curr.getEndVal());
			}else if(SqlOper.GT.eq(curr.getSqlOper())){
				qw.gt(columnName,curr.getSqlVal());
			}else if(SqlOper.GE.eq(curr.getSqlOper())){
				qw.ge(columnName,curr.getSqlVal());
			}else if(SqlOper.LT.eq(curr.getSqlOper())){
				qw.lt(columnName,curr.getSqlVal());
			}else if(SqlOper.LE.eq(curr.getSqlOper())){
				qw.le(columnName,curr.getSqlVal());
			}else if(SqlOper.NE.eq(curr.getSqlOper())){
				qw.ne(columnName,curr.getSqlVal());
			}else if(SqlOper.EQ.eq(curr.getSqlOper())){
				qw.eq(columnName,curr.getSqlVal());
			}else if(SqlOper.LIKE.eq(curr.getSqlOper())){
				qw.like(columnName,curr.getSqlVal());
			}else if(SqlOper.LIKE_LEFT.eq(curr.getSqlOper())){
				qw.likeLeft(columnName,curr.getSqlVal());
			}else if(SqlOper.LIKE_RIGHT.eq(curr.getSqlOper())){
				qw.likeRight(columnName,curr.getSqlVal());
			}else if(SqlOper.IS_NULL.eq(curr.getSqlOper())){
				qw.isNull(columnName);
			}else if(SqlOper.IS_NOT_NULL.eq(curr.getSqlOper())){
				qw.isNotNull(columnName);
			}else if(SqlOper.SQL.eq(curr.getSqlOper())){
				qw.apply((String) curr.getSqlVal(),transObjectToArray(curr.getEndVal()));
			}
		}
	}

	public static QueryCondition filterUnRightCondition(QueryCondition hi){
		QueryCondition condition=new QueryCondition();
		if( hi==null ){//自身为空
			return null;
		}else if(SqlLink.get(hi.getSqlLink())==null && SqlOper.get(hi.getSqlOper())==null){//link + oper 不能全为空
			throw new BizException("sql-link-or-sql-oper-required","操作符、连接符不能同时为空");
		}else if(ObjectTools.isNotEmpty(hi.getSqlLink())){ //sqlLink 只能是 空、AND、OR 三种
			if(SqlLink.get(hi.getSqlLink())==null){
				throw new BizException("not-support-sql-link","不支持的连接符%s",hi.getSqlLink());
			}
			if(hi.getChildren()==null||hi.getChildren().length==0){
				return null;
			}
		}else if(ObjectTools.isNotEmpty(hi.getSqlOper())){
			if(SqlOper.get(hi.getSqlOper())==null){
				throw new BizException("not-support-sql-oper","不支持的操作符%s",hi.getSqlOper());
			}else if(SqlOper.BETWEEN.eq(hi.getSqlOper())){//如果 oper=$BETWEEN ,不能是sqlVal或者endVal为空
				if(ObjectTools.isEmpty(hi.getSqlVal()) && ObjectTools.isEmpty(hi.getEndVal())){
					return null;
				}else if(ObjectTools.isEmpty(hi.getSqlVal())||ObjectTools.isEmpty(hi.getEndVal())){
					throw new BizException("between-sql-val-end-val-required","区间查询必须填入开始值及结束值");
				}
			}else if(!SqlOper.IS_NULL.eq(hi.getSqlOper()) && !SqlOper.IS_NOT_NULL.eq(hi.getSqlOper())){
				if(ObjectTools.isEmpty(hi.getSqlVal())){
					return null;
				}
			}
		}
		condition.setProperty(hi.getProperty());
		condition.setColPrefix(hi.getColPrefix());
		condition.setSqlOper(hi.getSqlOper());
		condition.setSqlVal(hi.getSqlVal());
		condition.setEndVal(hi.getEndVal());
		condition.setCid(hi.getCid());
		condition.setSqlLink(hi.getSqlLink());
		condition.setColType(hi.getColType());
		condition.setChildren(null);
		if(SqlLink.get(hi.getSqlLink())!=null){
			if(hi.getChildren()!=null && hi.getChildren().length>0){
				List<QueryCondition> childs=new ArrayList<>();
				for (QueryCondition child : hi.getChildren()) {
					QueryCondition c=filterUnRightCondition(child);
					if(c!=null){
						childs.add(c);
					}
				}
				if(childs.size()>0){
					condition.setChildren(childs.toArray(new QueryCondition[childs.size()]));
					return condition;
				}else{
					return null;
				}
			}else{
				return null;
			}
		}else if(SqlOper.get(hi.getSqlOper())!=null){
			return condition;
		}
		return null;
	}

	public static QueryWrapper<?> initQwByConditions(QueryWrapper<?> qw,Map<String,Object> parameterMap, SqlLink link, QueryCondition[] children, Class<?> clazz, String ...unIgnoreColumnName){
		if(children==null || children.length==0){
			return qw;
		}
		List<String> unIgnoreColumnNameList=ObjectTools.isEmpty(unIgnoreColumnName)? Arrays.asList() : Arrays.asList(unIgnoreColumnName);
		String tableName=clazz==null?null:TableUtil.getTableName(clazz);
		List<TableFieldInfo> allFields=clazz==null?null:TableUtil.getAllFields(clazz);
		if(SqlLink.AND.eq(link)){
			qw.and(andQw->{
				for (int i = 0; i < children.length; i++) {
					QueryCondition curr=children[i];
					if(curr.getChildren()==null||curr.getChildren().length==0){
						if(allFields!=null && allFields.size()>0){
							TableFieldInfo fieldInfo=getTableFieldInfoByCondition(curr.getColPrefix(),curr.getProperty(),allFields,tableName);
							if(fieldInfo==null){
								if(!isUnIgnoreColumnName((StringUtils.hasText(curr.getColPrefix())?curr.getColPrefix():"")+curr.getProperty(),unIgnoreColumnNameList)){
									continue;
								}
							}else{
								curr.setProperty(fieldInfo.getColumn());
							}
						}
						initQwByCondition(andQw,curr, parameterMap);
					}else{
						initQwByConditions(andQw, parameterMap,SqlLink.get(curr.getSqlLink()),curr.getChildren(),clazz,unIgnoreColumnName);
					} 
					
				}
			});
		}else if(SqlLink.OR.eq(link)){
			qw.or(orQw->{
				for (int i = 0; i < children.length; i++) {
					QueryCondition curr=children[i];
					if(curr.getChildren()==null||curr.getChildren().length==0){
						if(allFields!=null && allFields.size()>0){
							TableFieldInfo fieldInfo=getTableFieldInfoByCondition(curr.getColPrefix(),curr.getProperty(),allFields,tableName);
							if(fieldInfo==null){
								if(!isUnIgnoreColumnName(curr.getColPrefix()+curr.getProperty(),unIgnoreColumnNameList)){
									continue;
								}
							}else{
								curr.setProperty(fieldInfo.getColumn());
							}
						}
						initQwByCondition(orQw,curr, parameterMap);
					}else{
						initQwByConditions(orQw,parameterMap, SqlLink.get(curr.getSqlLink()),curr.getChildren(),clazz,unIgnoreColumnName);
					}
				}
			});
		}
		return qw;
	}

	public static QueryWrapper<?> initQwByConditions(QueryWrapper<?> qw,Map<String,Object> parameterMap, SqlLink link, QueryCondition[] children,List<DmField> tableFields, String ...unIgnoreColumnName){
		if(children==null || children.length==0){
			return qw;
		}
		List<String> unIgnoreColumnNameList=ObjectTools.isEmpty(unIgnoreColumnName)? Arrays.asList() : Arrays.asList(unIgnoreColumnName);
 		List<DmField> allFields=tableFields;
		if(SqlLink.AND.eq(link)){
			qw.and(andQw->{
				for (int i = 0; i < children.length; i++) {
					QueryCondition curr=children[i];
					if(curr.getChildren()==null||curr.getChildren().length==0){
						if(allFields!=null && allFields.size()>0){
							DmField fieldInfo=getTableFieldInfoByCondition(curr.getColPrefix(),curr.getProperty(),allFields);
							if(fieldInfo==null){
								if(!isUnIgnoreColumnName((StringUtils.hasText(curr.getColPrefix())?curr.getColPrefix():"")+curr.getProperty(),unIgnoreColumnNameList)){
									continue;
								}
							}else{
								curr.setProperty(fieldInfo.getColumnName());
							}
						}
						initQwByCondition(andQw,curr,parameterMap);
					}else{
						initQwByConditions(andQw,parameterMap, SqlLink.get(curr.getSqlLink()),curr.getChildren(),tableFields,unIgnoreColumnName);
					}

				}
			});
		}else if(SqlLink.OR.eq(link)){
			qw.or(orQw->{
				for (int i = 0; i < children.length; i++) {
					QueryCondition curr=children[i];
					if(curr.getChildren()==null||curr.getChildren().length==0){
						if(allFields!=null && allFields.size()>0){
							DmField fieldInfo=getTableFieldInfoByCondition(curr.getColPrefix(),curr.getProperty(),allFields);
							if(fieldInfo==null){
								if(!isUnIgnoreColumnName(curr.getColPrefix()+curr.getProperty(),unIgnoreColumnNameList)){
									continue;
								}
							}else{
								curr.setProperty(fieldInfo.getColumnName());
							}
						}
						initQwByCondition(orQw,curr, parameterMap);
					}else{
						initQwByConditions(orQw,parameterMap, SqlLink.get(curr.getSqlLink()),curr.getChildren(),tableFields,unIgnoreColumnName);
					}
				}
			});
		}
		return qw;
	}
	/**
	 * 根据值判断运算操作符
	 * 支持 QueryOper中定义的所有操作符
	 * @param value
	 * @return
	 */
	public static SqlOper convert2OperByValue(Object value) {
		if (value == null) {
			return SqlOper.EQ;
		}
		// 如果是数组，进行in查询 add by cyc 20230717
		if(value instanceof String[]){
			 return SqlOper.IN;
		}else if(value instanceof Collection){
			return SqlOper.IN;
		}else if(value.getClass().isArray()){
			return SqlOper.IN;
		}else if(value instanceof Date){
			return SqlOper.EQ;
		}else if(value instanceof Integer){
			return SqlOper.EQ;
		}else if(value instanceof Long){
			return SqlOper.EQ;
		}else if(value instanceof Double){
			return SqlOper.EQ;
		}else if(value instanceof BigDecimal){
			return SqlOper.EQ;
		}else if(value instanceof Float){
			return SqlOper.EQ;
		}else if(value instanceof Float){
			return SqlOper.EQ;
		}
		String val = (value + "").trim();
		if (val.length() == 0) {
			return SqlOper.EQ;
		}
		SqlOper op= SqlOper.get(val);
		if(op==null){
			return SqlOper.EQ;
		}else {
			return op;
		}
	}
	
	/**
	 * 替换掉运算符
	 * @param value
	 * @return
	 */
	protected static Object trimOper(Object value) {

		if (! (value instanceof String)){
			return value;
		}
		String val = (value + "").trim();
		String valU=val.toUpperCase(Locale.ROOT);
		if(val.indexOf("*")>=0){
			if(val.startsWith("*")){
				val=val.substring(1);
			}
			if(val.endsWith("*")){
				val=val.substring(0,val.length()-1);
			}
			return val;
		}
		for (SqlOper oper : SqlOper.values()) {
			if(valU.startsWith(oper.getValue())){
				val=val.substring(oper.getValue().length());


				return val;

			}
		}

		return val;
	}

	
	/**
	 * 获取日期类型的值
	 * @param value
	 * @param rule
	 * @return
	 * @throws ParseException
	 */
	protected static Date getDateQueryByRule(String value, SqlOper rule) throws ParseException {
		Date date = null;
		if(value.length()==10) {
			if(rule== SqlOper.GE) {
				//比较大于
				date = getTime().parse(value + " 00:00:00");
			}else if(rule== SqlOper.LE) {
				//比较小于
				date = getTime().parse(value + " 23:59:59");
			}
			//TODO 日期类型比较特殊 可能oracle下不一定好使
		}
		if(date==null) {
			date = getTime().parse(value);
		}
		return date;
	}
	 
	/**
	 * 
	 * @param name
	 * @return
	 */
	protected static boolean isInnerKeyName(String name) {
		return "$orderBy".equals(name) ||"pageNum".equalsIgnoreCase(name) || "pageSize".equalsIgnoreCase(name) || PKS.equalsIgnoreCase(name) || ALIAS_KEY.equalsIgnoreCase(name) ;
	}



	/**
	* @author: scott
	* @Description: 去掉值前后单引号
	* @date: 2020/3/19 21:26
	* @param ruleValue: 
	* @Return: java.lang.String
	*/
	public static String trimSingleQuote(String ruleValue) {
		if (ObjectTools.isEmpty(ruleValue)) {
			return "";
		}
		if (ruleValue.startsWith(QueryTools.SQL_SQ)) {
			ruleValue = ruleValue.substring(1);
		}
		if (ruleValue.endsWith(QueryTools.SQL_SQ)) {
			ruleValue = ruleValue.substring(0, ruleValue.length() - 1);
		}
		return ruleValue;
	}
	public static String converRuleValue(String ruleValue) {

		return ruleValue;
	}
	public static String getSqlRuleValue(String sqlRule){
		try {
			Set<String> varParams = getSqlRuleParams(sqlRule);
			for(String var:varParams){
				String tempValue = converRuleValue(var);
				sqlRule = sqlRule.replace("#{"+var+"}",tempValue);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return sqlRule;
	}
	
	/**
	 * 获取sql中的#{key} 这个key组成的set
	 */
	public static Set<String> getSqlRuleParams(String sql) {
		if(ObjectTools.isEmpty(sql)){
			return null;
		}
		Set<String> varParams = new HashSet<String>();
		String regex = "\\#\\{\\w+\\}";
		
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(sql);
		while(m.find()){
			String var = m.group();
			varParams.add(var.substring(var.indexOf("{")+1,var.indexOf("}")));
		}
		return varParams;
	}
	
	/**
	 * 获取查询条件 
	 * @param field
	 * @param alias
	 * @param value
	 * @param isString
	 * @return
	 */
	public static String getSingleQueryConditionSql(String field,String alias,Object value,boolean isString) {
		return getSingleQueryConditionSql(field, alias, value, isString,null);
	}

	/**
	 * 报表获取查询条件 支持多数据源
	 * @param field
	 * @param alias
	 * @param value
	 * @param isString
	 * @param dataBaseType
	 * @return
	 */
	public static String getSingleQueryConditionSql(String field,String alias,Object value,boolean isString, String dataBaseType) {
		if (value == null) {
			return "";
		}
		field =  alias+ ObjectTools.camelToUnderline(field);
		SqlOper rule = QueryTools.convert2OperByValue(value);
		return getSingleSqlByRule(rule, field, value, isString, dataBaseType);
	}

	/**
	 * 获取单个查询条件的值
	 * @param rule
	 * @param field
	 * @param value
	 * @param isString
	 * @param dataBaseType
	 * @return
	 */
	protected static String getSingleSqlByRule(SqlOper rule, String field, Object value, boolean isString, String dataBaseType) {
		String res = "";
		switch (rule) {
		case GT:
			res =field+rule.getValue()+getFieldConditionValue(value, isString, dataBaseType);
			break;
		case GE:
			res = field+rule.getValue()+getFieldConditionValue(value, isString, dataBaseType);
			break;
		case LT:
			res = field+rule.getValue()+getFieldConditionValue(value, isString, dataBaseType);
			break;
		case LE:
			res = field+rule.getValue()+getFieldConditionValue(value, isString, dataBaseType);
			break;
		case EQ:
			res = field+rule.getValue()+getFieldConditionValue(value, isString, dataBaseType);
			break;
		case NE:
			res = field+" <> "+getFieldConditionValue(value, isString, dataBaseType);
			break;
		case IN:
			res = field + " in "+getInConditionValue(value, isString);
			break;
		case LIKE:
			res = field + " like "+getLikeConditionValue(value);
			break;
		case LIKE_LEFT:
			res = field + " like "+getLikeConditionValue(value);
			break;
		case LIKE_RIGHT:
			res = field + " like "+getLikeConditionValue(value);
			break;
		default:
			res = field+" = "+getFieldConditionValue(value, isString, dataBaseType);
			break;
		}
		return res;
	}


	/**
	 * 获取单个查询条件的值
	 * @param rule
	 * @param field
	 * @param value
	 * @param isString
	 * @return
	 */
	protected static String getSingleSqlByRule(SqlOper rule, String field, Object value, boolean isString) {
		return getSingleSqlByRule(rule, field, value, isString, null);
	}

	/**
	 * 获取查询条件的值
	 * @param value
	 * @param isString
	 * @param dataBaseType
	 * @return
	 */
	protected static String getFieldConditionValue(Object value,boolean isString, String dataBaseType) {
		String str = value.toString().trim();
		if(str.startsWith("!")) {
			str = str.substring(1);
		}else if(str.startsWith(">=")) {
			str = str.substring(2);
		}else if(str.startsWith("<=")) {
			str = str.substring(2);
		}else if(str.startsWith(">")) {
			str = str.substring(1);
		}else if(str.startsWith("<")) {
			str = str.substring(1);
		}
		if(dataBaseType==null){
			dataBaseType = getDbType();
		}
		if(isString) {
			if(Const.DB_TYPE_SQLSERVER.equals(dataBaseType)){
				return " N'"+str+"' ";
			}else{
				return " '"+str+"' ";
			}
		}else {
			// 如果不是字符串 有一种特殊情况 popup调用都走这个逻辑 参数传递的可能是“‘admin’”这种格式的
			if(Const.DB_TYPE_SQLSERVER.equals(dataBaseType) && str.endsWith("'") && str.startsWith("'")){
				return " N"+str;
			}
			return value.toString();
		}
	}

	protected static String getInConditionValue(Object value,boolean isString) {
		//update-begin-author:taoyan date:20210628 for: 查询条件如果输入,导致sql报错
		String[] temp = value.toString().split(",");
		if(temp.length==0){
			return "('')";
		}
		if(isString) {
			List<String> res = new ArrayList<>();
			for (String string : temp) {
				if(Const.DB_TYPE_SQLSERVER.equals(getDbType())){
					res.add("N'"+string+"'");
				}else{
					res.add("'"+string+"'");
				}
			}
			return "("+String.join("," ,res)+")";
		}else {
			return "("+value.toString()+")";
		}
		//update-end-author:taoyan date:20210628 for: 查询条件如果输入,导致sql报错
	}
	
	protected static String getLikeConditionValue(Object value) {
		String str = value.toString().trim();
		if(str.startsWith("*") && str.endsWith("*")) {
			if(Const.DB_TYPE_SQLSERVER.equals(getDbType())){
				return "N'%"+str.substring(1,str.length()-1)+"%'";
			}else{
				return "'%"+str.substring(1,str.length()-1)+"%'";
			}
		}else if(str.startsWith("*")) {
			if(Const.DB_TYPE_SQLSERVER.equals(getDbType())){
				return "N'%"+str.substring(1)+"'";
			}else{
				return "'%"+str.substring(1)+"'";
			}
		}else if(str.endsWith("*")) {
			if(Const.DB_TYPE_SQLSERVER.equals(getDbType())){
				return "N'"+str.substring(0,str.length()-1)+"%'";
			}else{
				return "'"+str.substring(0,str.length()-1)+"%'";
			}
		}else {
			if(str.indexOf("%")>=0) {
				if(Const.DB_TYPE_SQLSERVER.equals(getDbType())){
					if(str.startsWith("'") && str.endsWith("'")){
						return "N"+str;
					}else{
						return "N"+"'"+str+"'";
					}
				}else{
					if(str.startsWith("'") && str.endsWith("'")){
						return str;
					}else{
						return "'"+str+"'";
					}
				}
			}else {
				if(Const.DB_TYPE_SQLSERVER.equals(getDbType())){
					return "N'%"+str+"%'";
				}else{
					return "'%"+str+"%'";
				}
			}
		}
	}



	/**
	 * 转换sql中的系统变量
	 * @param sql
	 * @return
	 */
	public static String convertSystemVariables(String sql){
		return getSqlRuleValue(sql);
	}




	static String DB_TYPE;
	/**
	 * 获取系统数据库类型
	 */
	protected static String getDbType() {
		if(ObjectTools.isNotEmpty(DB_TYPE)){
			return DB_TYPE;
		}
		DataSource dataSource = SpringUtils.getApplicationContext().getBean(DataSource.class);
		try {
			return getDatabaseTypeByDataSource(dataSource);
		} catch (SQLException e) {
			log.error("数据库类型读取异常",e);
			throw new RuntimeException("数据库类型读取异常");
		}

	}


	/**
	 * 获取数据库类型
	 * @param dataSource
	 * @return
	 * @throws SQLException
	 */
	protected static String getDatabaseTypeByDataSource(DataSource dataSource) throws SQLException {
		if("".equals(DB_TYPE)) {
			Connection connection = dataSource.getConnection();
			try {
				DatabaseMetaData md = connection.getMetaData();
				String dbType = md.getDatabaseProductName().toLowerCase();
				if(dbType.indexOf("mysql")>=0) {
					DB_TYPE = Const.DB_TYPE_MYSQL;
				}else if(dbType.indexOf("oracle")>=0 ||dbType.indexOf("dm")>=0) {
					DB_TYPE = Const.DB_TYPE_ORACLE;
				}else if(dbType.indexOf("sqlserver")>=0||dbType.indexOf("sql server")>=0) {
					DB_TYPE = Const.DB_TYPE_SQLSERVER;
				}else if(dbType.indexOf("postgresql")>=0) {
					DB_TYPE = Const.DB_TYPE_POSTGRESQL;
				}else if(dbType.indexOf("mariadb")>=0) {
					DB_TYPE = Const.DB_TYPE_MARIADB;
				}else {
					log.error("数据库类型:[" + dbType + "]不识别!");
					//throw new JeecgBootException("数据库类型:["+dbType+"]不识别!");
				}
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}finally {
				connection.close();
			}
		}
		return DB_TYPE;

	}


	/**
	 * 获取class的 包括父类的
	 * @param clazz
	 * @return
	 */
	protected static List<Field> getClassFields(Class<?> clazz) {
		List<Field> list = new ArrayList<Field>();
		Field[] fields;
		do{
			fields = clazz.getDeclaredFields();
			for(int i = 0;i<fields.length;i++){
				list.add(fields[i]);
			}
			clazz = clazz.getSuperclass();
		}while(clazz!= Object.class&&clazz!=null);
		return list;
	}

	/**
	 * 获取表字段名
	 * @param clazz
	 * @param name
	 * @return
	 */
	protected static String getTableFieldName(Class<?> clazz, String name) {
		try {
			//如果字段加注解了@TableField(exist = false),不走DB查询
			Field field = null;
			try {
				field = clazz.getDeclaredField(name);
			} catch (NoSuchFieldException e) {
				//e.printStackTrace();
			}

			//如果为空，则去父类查找字段
			if (field == null) {
				List<Field> allFields = getClassFields(clazz);
				List<Field> searchFields = allFields.stream().filter(a -> a.getName().equals(name)).collect(Collectors.toList());
				if(searchFields!=null && searchFields.size()>0){
					field = searchFields.get(0);
				}
			}

			if (field != null) {
				TableField tableField = field.getAnnotation(TableField.class);
				if (tableField != null){
					if(tableField.exist() == false){
						//如果设置了TableField false 这个字段不需要处理
						return null;
					}else{
						String column = tableField.value();
						//如果设置了TableField value 这个字段是实体字段
						if(!"".equals(column)){
							return column;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}


	/**
	 * mysql 模糊查询之特殊字符下划线 （_、\）
	 *
	 * @param value:
	 * @Return: java.lang.String
	 */
	protected static String specialStrConvert(String value) {
		if (Const.DB_TYPE_MYSQL.equals(getDbType()) || Const.DB_TYPE_MARIADB.equals(getDbType())) {
			String[] special_str = QueryTools.LIKE_MYSQL_SPECIAL_STRS.split(",");
			for (String str : special_str) {
				if (value.indexOf(str) !=-1) {
					value = value.replace(str, "\\" + str);
				}
			}
		}
		return value;
	}
}
