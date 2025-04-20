package com.mdp.core.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.mdp.core.api.Sequence;
import com.mdp.core.dao.TableUtil;
import com.mdp.core.entity.LangTips;
import com.mdp.core.err.BizException;
import com.mdp.core.query.QueryTools;
import com.mdp.core.utils.BaseUtils;
import com.mdp.core.utils.ObjectTools;
import org.apache.ibatis.binding.MapperMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;

/**
 * 服务基类,本服务类对增删改查进行了抽象.
 * @author cyc  
 * @author chenyc 20150801 增加通讯服务类 CommService
 * @author cyc  20150802 增加 静态方法map(Object...keyAndValues)函数，快速构造一个Map
 * @author cyc  20150803 增加批量更新接口函数 batchUpdate
 * @author cyc 20150816 注入通讯模块,可以在服务内部发起对外系统的通讯请求
 * @author cyc 20150826 将服务类扩展成模板,从仅仅支持map到支持所有的数据类型
 * @author cyc 20160120 增加生成序列号的函数 createKey(String keyName);
 * @author cyc 20230718 改由mybats plus 服务类提供服务
 * @since 1.0
 */ 

public    class BaseService<M extends BaseMapper<T>, T> extends ServiceImpl<M,T> {

	protected Logger log= LoggerFactory.getLogger(getClass());

	Sequence sequenceService=new SequenceService();


	/**
	 * 快速构造一个map，根据传入的键值对，动态放置到Map中，并返回Map，减少不必要的put和 get操作<br>
	 * 用法如下：<br>
	 * map("account_id","admin","account_name","管理员","account_status",1);<br>
	 * 
	 * @return Map
	 */
	public static Map<String,Object> map(Object...keyAndValues){
		return BaseUtils.map(keyAndValues);
	}

	/**
	 * 批量更新一批数据，支持联合主键，每个字段均更新
	 * @param entityList
	 * @return
	 */
	@Override
	public boolean updateBatchById(Collection<T> entityList) {

		return this.updateBatchById(entityList,1000);
	}

	public int insert(T parameter) {
		return this.save(parameter)?0:1;
	}

	@Override
	public boolean save(T entity) {
		List<TableFieldInfo> pks=TableUtil.getPkFields(entity.getClass());
		if(pks==null||pks.size()==0){
			return super.save(entity);
		}else if(pks.size()==1){
			return super.save(entity);
		}else{
			for (TableFieldInfo pk : pks) { 
				Object val= null;
				try {
					val = pk.getField().get(entity);
					if(ObjectTools.isEmpty(val)){
						pk.getField().set(entity,sequenceService.getTablePK(this.entityClass.getSimpleName(),pk.getColumn()));
					}
				} catch (IllegalAccessException e) {
					log.error("",e);
					throw new BizException(e.getMessage());
				}
			}
			return super.save(entity);
		}
	}

	/**
	 * 根据主键更新一条数据的某些非空值字段，支持联合主键
	 * @param entity
	 * @return
	 */
	@Override
	public boolean updateById(T entity) {
		 return updateById(entity,true);

	}

	/**
	 * 根据主键批量删除一批数据
	 * @param pks 主键参数列表，可以是如下几种格式 List<Map<String,Object>>,List<T>,List<String>,List<Integer>等
	 *            单主键时 pks=[?,?,?],多主键时 pks=[{主键1:?,主键2:?},{主键1:?,主键2:?},{主键1:?,主键2:?}]
	 * @return
	 */
	@Override
	public boolean removeByIds(Collection pks){
			if(pks==null || pks.size()==0){
				return false;
			}
			Object id=pks.stream().findAny().get();
			if(pks.size()==1){
				return this.removeByPk(id);
			}
			if(id instanceof Map){
				QueryWrapper<T> qw=new QueryWrapper();
				List<TableFieldInfo> pkFields=TableUtil.getPkFields(this.entityClass);
				List<Object> vars=new ArrayList<>();
				for (Object pk0 : pks) {
					Map<String,Object> pk= (Map<String, Object>)pk0;
					for (int i1 = 0; i1 < pkFields.size(); i1++) {
						vars.add(pk.get(pkFields.get(i1).getProperty()));
					}
				}

				qw.apply(calcInSqlByPkFields(pkFields,pks.size()),vars.toArray(new Object[vars.size()]));
				return super.remove(qw);
			}else if(id.getClass().getName().equals(this.entityClass.getName())){

				List<TableFieldInfo> pkFields=TableUtil.getPkFields(this.entityClass);
				if(pkFields==null || pkFields.size()==0){
					throw new BizException("no-id-find","在对象%s中未找到主键，请确保有id或者标注了 tableId或者tableIds注解",this.entityClass.getName());
				}

				QueryWrapper<T> qw = new QueryWrapper();

				int pkSize = pks.size();

				List<Object> vars = new ArrayList<>();
				for (Object pk0 : pks) {
					T pk = (T) pk0;
					for (int i1 = 0; i1 < pkFields.size(); i1++) {
						try {
							TableFieldInfo field=pkFields.get(i1);
							field.getField().setAccessible(true);
							vars.add(field.getField().get(pk));
						} catch (IllegalAccessException e) {
							log.error("",e);
							throw new BizException(e.getMessage());
						}
					}
				}
				qw.apply(calcInSqlByPkFields(pkFields,pkSize),vars.toArray(new Object[vars.size()]));
				return super.remove(qw);

		}else if(id instanceof Serializable){
			return super.removeByIds((Collection<? extends Serializable>) pks);
		}else {
			return false;
		}
	}

	/**
	 * 删除函数
	 * 所有条件为=,空值忽略，有可能造成全表删除。
	 * @param parameter
	 * @return
	 */
	public int deleteByWhere(T parameter) {
		Map<String,Object> map=BaseUtils.toMap(parameter,false);
		List<TableFieldInfo> allFields=TableUtil.getAllFields(this.entityClass);
		Map<String,Object> rmap=new HashMap<>();
		for (Map.Entry<String, Object> kv : map.entrySet()) {
			Object val=kv.getValue();
			String key=kv.getKey();
			if(ObjectTools.isEmpty(val)){
				continue;
			}
			Optional<TableFieldInfo> fieldInfo=allFields.stream().filter(k->k.getProperty().equalsIgnoreCase(key)).findAny();
			if(fieldInfo.isPresent()){
				rmap.put(fieldInfo.get().getColumn(),val);
			} 
		}
		return baseMapper.deleteByMap(rmap);
	}
	/**
	 * 根据主键删除一个数据，支持联合主键
	 * @param id 可以实字符串(单主键)、map(多主键)、T 等类型
	 * @return
	 */
	@Override
	public boolean removeById(Serializable id) {
		return this.removeByPk((Object) id);
	}
	/**
	 * 根据主键删除一个数据，支持联合主键
	 * @param id 可以实字符串(单主键)、map(多主键)、T 等类型
	 * @return
	 */
	@Override
	public boolean removeById(T id) {
		return this.removeByPk(id);
	}
	/**
	 * 根据主键删除一个数据，支持联合主键
	 * @param id 可以实字符串(单主键)、map(多主键)、T 等类型
	 * @return
	 */
	public boolean removeById(Map<String,Object> id) {
		return this.removeByPk(id);
	}

	/**
	 * 根据主键删除一个数据，支持联合主键
	 * @param id 可以实字符串(单主键)、map(多主键)、T 等类型
	 * @return
	 */
	public boolean removeById(String id) {
		return this.removeByPk(id);
	}
	/**
	 * 根据主键删除一个数据，支持联合主键
	 * @param id 可以实字符串(单主键)、map(多主键)、T 等类型
	 * @return
	 */
	protected boolean removeByPk(Object id) {
		if(ObjectTools.isEmpty(id)){
			return false;
		}
		if(id instanceof Map){
			List<TableFieldInfo> pkFields=TableUtil.getPkFields(this.entityClass);
			Map<String,Object> pk= (Map<String, Object>) id;
			QueryWrapper<T> rw=new QueryWrapper<>();
			for (TableFieldInfo pkField : pkFields) {
				pkField.getField().setAccessible(true);
				rw.eq(pkField.getColumn(),pk.get(pkField.getProperty()));
			}
			return super.remove(rw);
		}else if(id.getClass().getName().equals(this.entityClass.getName())){
			List<TableFieldInfo> pkFields=TableUtil.getPkFields(this.entityClass);
			T pk= (T) id;
			QueryWrapper<T> rw=new QueryWrapper<>();
			for (TableFieldInfo pkField : pkFields) {
				try {
					pkField.getField().setAccessible(true);
					rw.eq(pkField.getColumn(),pkField.getField().get(pk));
				} catch (IllegalAccessException e) {
					log.error("",e);
					throw new BizException(e.getMessage());
				}
			}
			return super.remove(rw);
		}else if (id instanceof Serializable){
			return super.removeById((Serializable) id);
		}else {
			return false;
		}
	}

	/**
	 * 根据主键更新某条数据
	 * @param entity
	 * @param ignoreNull 空值的属性是否忽略，不进行更新 true忽略，false为不忽略，更新
	 * @return
	 */
	public boolean updateById(T entity ,boolean ignoreNull){
		List<TableFieldInfo> pkFields=TableUtil.getPkFields(this.entityClass);
		if(pkFields==null || pkFields.size()==0){
			throw new BizException("no-id-find","在对象%s中未找到主键，请确保有id或者标注了 tableId或者tableIds注解",this.entityClass.getName());
		}

		if(pkFields.size()==1 && ignoreNull){
			return SqlHelper.retBool(baseMapper.updateById(entity));
		}else if(pkFields.size()==1 && ignoreNull==false){
			UpdateWrapper<T> uw=new UpdateWrapper<>();
			for (TableFieldInfo pkField : pkFields) {
				try {
					pkField.getField().setAccessible(true);
					uw.eq(pkField.getColumn(),pkField.getField().get(entity));
				} catch (IllegalAccessException e) {
					log.error("",e);
					throw new BizException(e.getMessage());
				}
			}
			List<TableFieldInfo> allFields=TableUtil.getAllFields(this.entityClass);
			for (TableFieldInfo field : allFields) {
				try {
					if(field.getColumn().equalsIgnoreCase(pkFields.get(0).getColumn())){
						continue;
					}
					field.getField().setAccessible(true);
					Object val=field.getField().get(entity);
					if(ObjectTools.isEmpty(val)){
						uw.set(field.getColumn(),val);
					}
				} catch (IllegalAccessException e) {
					log.error("",e);
					throw new BizException(e.getMessage());
				}
			}
			return SqlHelper.retBool(baseMapper.update(entity,uw));
		}else{
			UpdateWrapper<T> uw=new UpdateWrapper<>();
			for (TableFieldInfo pkField : pkFields) {
				try {
					pkField.getField().setAccessible(true);
					uw.eq(pkField.getColumn(),pkField.getField().get(entity));
				} catch (IllegalAccessException e) {
					log.error("",e);
					throw new BizException(e.getMessage());
				}
			}
			if(ignoreNull==false){
				List<TableFieldInfo> allFields=TableUtil.getAllFields(this.entityClass);
				for (TableFieldInfo field : allFields) {
					try {
						if(pkFields.stream().filter(k->k.getProperty().equalsIgnoreCase(field.getProperty())).findAny().isPresent()){
							continue;
						}
						field.getField().setAccessible(true);
						Object val=field.getField().get(entity);
						if(ObjectTools.isEmpty(val)){
							uw.set(field.getColumn(),val);
						}
					} catch (IllegalAccessException e) {
						log.error("",e);
						throw new BizException(e.getMessage());
					}
				}
			}

			return SqlHelper.retBool(baseMapper.update(entity,uw));
		}

	}

	/**
	 * 根据主键删除一条数据
	 * 改由 removeById 提供服务
	 * @param parameter
	 * @return
	 */
	@Deprecated
	public int deleteByPk(T parameter) {
		List<TableFieldInfo> pkFields=TableUtil.getPkFields(this.entityClass);
		if(pkFields==null || pkFields.size()==0){
			return 0;
		}else if(pkFields.size()>=1){

			QueryWrapper<T> rw=new QueryWrapper<>();
			for (TableFieldInfo pkField : pkFields) {
				try {
					pkField.getField().setAccessible(true);
					rw.eq(pkField.getColumn(),pkField.getField().get(parameter));
				} catch (IllegalAccessException e) {
					log.error("",e);
					throw new BizException(e.getMessage());
				}
			}
			return baseMapper.delete(rw);
		}else {
			return 0;
		}
	}

	/**
	 * 更新某条数据的某些非空值字段
	 * 改由 updateById 提供服务
	 * @param parameter
	 * @return
	 */
	@Deprecated
	public int updateSomeFieldByPk(T parameter) {
		return this.updateById(parameter,true)?1:0;
	}

	/**
	 * 根据主键更新一条数据的所有字段
	 * 改由 updateById 提供服务
	 * @param parameter
	 * @return
	 */
	@Deprecated
	public int updateByPk(T parameter) {
		return this.updateById(parameter,false)?1:0;
	}


	/**
	 * 新增或者更新
	 * 改由 saveOrUpdate 提供服务
	 * @param parameter
	 * @return
	 */
	@Deprecated
	public int insertOrUpdate(T parameter) {
		if (ObjectTools.isEmpty(this.selectOneById(parameter))) {
			return this.insert(parameter);
		} else {
			return this.updateByPk(parameter);
		}
	}


	/**
	 * 新增或者保存一条数据
	 * @param entity
	 * @return
	 */
	@Override
	public boolean saveOrUpdate(T entity) {
		if (ObjectTools.isEmpty(selectOneById(entity))) {
			return this.save(entity);
		} else {
			return this.updateById(entity);
		}
	}

	/**
	 * 自定义更新函数
	 * @param updateWrapper
	 * @return
	 */
	@Override
	public boolean update(Wrapper<T> updateWrapper) {
		return super.update(updateWrapper);
	}

	/**
	 * 批量更新一批数据，支持联合主键，每个字段均更新
	 * @param entityList
	 * @param batchSize 每个批次处理数据条数
	 * @return
	 */
	@Override
	public boolean updateBatchById(Collection<T> entityList, int batchSize) {
		List<TableFieldInfo> pkFields=TableUtil.getPkFields(this.entityClass);
		List<TableFieldInfo> allFields=TableUtil.getAllFields(this.entityClass);
		if(pkFields.size()>1){
			String sqlStatement = this.getSqlStatement(SqlMethod.UPDATE);
			return this.executeBatch(entityList, batchSize, (sqlSession, entity) -> {
				MapperMethod.ParamMap param = new MapperMethod.ParamMap();
				UpdateWrapper<T> ew=new UpdateWrapper<>();
				for (int i = 0; i < allFields.size(); i++) {
					TableFieldInfo fieldInfo=allFields.get(i);
					if(pkFields.stream().filter(k->k.getColumn().equalsIgnoreCase(fieldInfo.getColumn())).findAny().isPresent()){
						continue;
					}
					Field field=fieldInfo.getField();
					field.setAccessible(true);
					try {
						ew.set(fieldInfo.getColumn(),field.get(entity));
					} catch (IllegalAccessException e) {
						log.error("",e);
						throw new BizException(e.getMessage());
					}
				}
				for (TableFieldInfo pkField : pkFields) {
					pkField.getField().setAccessible(true);
					try {
						ew.eq(pkField.getColumn(),pkField.getField().get(entity));
					} catch (IllegalAccessException e) {
						log.error("",e);
						throw new BizException(e.getMessage());
					}
				}
				param.put("ew", ew);
				param.put("et",entity);
				sqlSession.update(sqlStatement, param);
			});
		}else{
			return super.updateBatchById(entityList,batchSize);
		}

	}

	/**
	 * 批量更新
	 * 改由 updateBatchById提供服务
	 * @param batchValues
	 * @return
	 */
	@Deprecated
	public int[] batchUpdate(List<T> batchValues) {
		boolean isOk=this.updateBatchById(batchValues);
		int[] ints=new int[batchValues.size()];
		for (int i = 0; i < batchValues.size(); i++) {
			ints[i]=isOk?1:0;
		}
		return ints;
	}

	/**
	 * 批量新增 支持联合主键
	 * @param entityList
	 * @return
	 */
	@Override
	public boolean saveBatch(Collection<T> entityList) {
		return this.saveBatch(entityList,1000);
	}

	/**
	 * 批量新增 支持联合主键
	 * @param entityList
	 * @param batchSize 每批次数量条数
	 * @return
	 */
	@Override
	public boolean saveBatch(Collection<T> entityList, int batchSize) {
		List<TableFieldInfo> pkFields=TableUtil.getPkFields(this.entityClass);
		if(pkFields==null || pkFields.size()<=1){
			return super.saveBatch(entityList,batchSize);
		}else{
			for (T t : entityList) {
				for (TableFieldInfo pkField : pkFields) {
					pkField.getField().setAccessible(true);
					try {
						Object val=pkField.getField().get(t);
						if(ObjectTools.isEmpty(val)){
							pkField.getField().set(t,sequenceService.getTablePK(this.entityClass.getSimpleName(),pkField.getColumn()));
						}
					} catch (IllegalAccessException e) {
						log.error("",e);
						throw new BizException(e.getMessage());
					}
				}
			}
			return super.saveBatch(entityList,batchSize);
		}
	}

	/**
	 * 批量插入
	 * 参考saveBatch
	 */
	@Deprecated
	public int[] batchInsert(List<T> batchValues) {
		boolean isOk=this.saveBatch(batchValues);
		int[] ints=new int[batchValues.size()];
		for (int i = 0; i < batchValues.size(); i++) {
			ints[i]=isOk?1:0;
		}
		return ints;
	}


	/**
	 * 批量删除
	 * 参考 removeByIds
	 * @param batchValues
	 * @return
	 */
	@Deprecated
	public int[] batchDelete(List<T> batchValues) {

 		boolean isOk=this.removeByIds(batchValues);
		int[] ints=new int[batchValues.size()];
		for (int i = 0; i < batchValues.size(); i++) {
			ints[i]=isOk?1:0;
		}
		return ints;
	}


	/**
	 * 根据条件自动组装查询条件进行统计
	 * 所有条件均为 = ,空值忽略
	 * @param parameter
	 * @return
	 */
	public long countByWhere(T parameter) {
		QueryWrapper<T> wrapper= QueryTools.initQueryWrapper(parameter,null);
		return baseMapper.selectCount(wrapper);
	}


	/**
	 * 查询一个对象
	 * 参考 selectOneById
	 * @param parameter
	 * @return
	 */
	@Deprecated
	public T selectOneObject(T parameter) {
		return this.getById((Serializable) parameter);
	}
	/**
	 * 根据主键查询一个对象，支持多主健查询
	 * @param pk 主键，可以是String,T,Map等多种格式
	 *           单主键时必须是String,Integer等类型 pk=?,多主键时必须是T或者Map类型 pk={主键1:?,主键2:?}
	 * @return
	 */
	@Deprecated
	public T selectOneById(Object pk){
		return this.getById((Serializable) pk);
	}

	/**
	 * 根据主键查询一个对象，支持多主健查询
	 * @param pk 主键，可以是String,T,Map等多种格式
	 *           单主键时必须是String,Integer等类型 pk=?,多主键时必须是T或者Map类型 pk={主键1:?,主键2:?}
	 * @return
	 */
	public T getById(T pk)  {
		return this.getById((Serializable)pk);
	}

	/**
	 * 根据主键查询一个对象，支持多主健查询
	 * @param pk 主键，可以是String,T,Map等多种格式
	 *           单主键时必须是String,Integer等类型 pk=?,多主键时必须是T或者Map类型 pk={主键1:?,主键2:?}
	 * @return
	 */
	public T getById(Map<String,Object> pk)  {
		return this.getById((Serializable)pk);
	}

	/**
	 * 根据主键查询一个对象，支持多主健查询
	 * @param pk 主键，可以是String,T,Map等多种格式
	 *           单主键时必须是String,Integer等类型 pk=?,多主键时必须是T或者Map类型 pk={主键1:?,主键2:?}
	 * @return
	 */
	public T getById(String pk)  {
		return this.getById((Serializable)pk);
	}

	/**
	 * 根据主键查询一个对象，支持多主健查询
	 * @param pk 主键，可以是String,T,Map等多种格式
	 *           单主键时必须是String,Integer等类型 pk=?,多主键时必须是T或者Map类型 pk={主键1:?,主键2:?}
	 * @return
	 */
	@Override
	public T getById(Serializable pk)  {
		if(pk instanceof Map){
			Map<String,Object> pkMap= (Map<String, Object>) pk;
			QueryWrapper<T> qw=new QueryWrapper();
			List<TableFieldInfo> fieldInfos=TableUtil.getPkFields(this.entityClass);
			for (int i = 0; i < fieldInfos.size(); i++) {
				String col=fieldInfos.get(i).getColumn();
				qw.eq(col,pkMap.get(fieldInfos.get(i).getProperty()));
			}
			return super.getOne(qw);
		}else if(pk.getClass().getName().equals( this.entityClass.getName())){
			T pk0=(T)pk;
			QueryWrapper<T> qw=new QueryWrapper();
			List<TableFieldInfo> fieldInfos=TableUtil.getPkFields(this.entityClass);
			for (int i = 0; i < fieldInfos.size(); i++) {
				TableFieldInfo field=fieldInfos.get(i);
				field.getField().setAccessible(true);
				String col=fieldInfos.get(i).getColumn();
				try {
					Object val=field.getField().get(pk0);
					qw.eq(col,val);
				} catch (IllegalAccessException e) {
					log.error("",e);
					throw new BizException(e.getMessage());
				}
			}
			return super.getOne(qw);
		}else if(pk instanceof Serializable){
			return super.getById((Serializable) pk);
		}else{
			throw new BizException("no-support-dao-parameter-type","不支持的参数类型%s",pk.getClass().getName());
		}
	}

	/**
	 * 自定义查询，所有条件均为 = ,有值才作为条件，无值忽略
	 * @param parameter
	 * @return
	 */
	public List<T> selectListByWhere(T parameter) {
		return baseMapper.selectList(QueryTools.initQueryWrapper(parameter,null));
	}

	/**
	 * 根据主键查询一批数据
	 * 支持单、多主键查询
	 * 改由 listByIds 提供服务
	 * @param pks 主键参数列表，可以是如下几种格式 List<Map<String,Object>>,List<T>,List<String>,List<Integer>等
	 *            单主键时 pks=[?,?,?],多主键时 pks=[{主键1:?,主键2:?},{主键1:?,主键2:?},{主键1:?,主键2:?}]
	 * @return
	 */
	@Deprecated
	public List<T> selectListByIds(List<?> pks) {
		return this.listByIds(pks);
	}

	@Override
	public List<T> listByIds(Collection pks) {
		if(pks==null || pks.size()==0){
			return new ArrayList<>();
		}
		Object id=pks.stream().findFirst().get();
		if(pks.size()==1){
			T data=this.getById((Serializable) id);
			return data==null?Arrays.asList():Arrays.asList(data);
		}
		if(id instanceof String){
			return super.listByIds((Collection<? extends Serializable>) pks);
		}else if(id instanceof Map){
			QueryWrapper<T> qw=new QueryWrapper();
			List<TableFieldInfo> pkFields=TableUtil.getPkFields(this.entityClass);

			List<Object> vars=new ArrayList<>();
			for (Object pk0 : pks) {
				Map<String,Object> pk= (Map<String, Object>) pk0;
				for (int i1 = 0; i1 < pkFields.size(); i1++) {
					vars.add(pk.get(pkFields.get(i1).getProperty()));
				}
			}
			qw.apply(calcInSqlByPkFields(pkFields,pks.size()),vars.toArray(new Object[vars.size()]));
			return super.list(qw);
		}else if(id.getClass().getName().equals(this.entityClass.getName())){

			List<TableFieldInfo> pkFields=TableUtil.getPkFields(this.entityClass);
			if(pkFields==null || pkFields.size()==0){
				throw new BizException("no-id-find","在对象%s中未找到主键，请确保有id或者标注了 tableId或者tableIds注解",this.entityClass.getName());
			}
			if(pkFields.size()>1){
				QueryWrapper<T> qw=new QueryWrapper();
				List<Object> vars=new ArrayList<>();
				for (Object pk0 : pks) {
					T pk= (T) pk0;
					for (int i1 = 0; i1 < pkFields.size(); i1++) {
						try {
							vars.add(pkFields.get(i1).getField().get(pk));
						} catch (IllegalAccessException e) {
							log.error("",e);
							throw new BizException(e.getMessage());
						}
					}
				}
				qw.apply(calcInSqlByPkFields(pkFields,pks.size()),vars.toArray(new Object[vars.size()]));
				return super.list(qw);
			}else {
				List<String> vars=new ArrayList<>();
				TableFieldInfo field=pkFields.get(0);
				field.getField().setAccessible(true);
				for (Object pk0 : pks) {
					T pk= (T) pk0;
					try {
						Object val=field.getField().get(pk);
						vars.add(val==null?null:val.toString());
					} catch (IllegalAccessException e) {
						log.error("",e);
						throw new BizException(e.getMessage());
					}
				}
				return super.listByIds(vars);
			}

		}else {
			throw new BizException("no-support-dao-parameter-type","不支持的参数类型%s",id.getClass().getName());
		}
	}

	/**
	 * 根据主键同时更新一批数据的某些字段
	 * 类似：
	 * 	单主键 update xxx=?,bbb=? where id in (?,?)
	 * 	多主键 update xxx=?,bbb=? where (id1,id2) in (({0},{0}),({0},{0}))
	 * @param pks 单主键时 $pks=[?,?,?],多主键时 $pks=[{主键1:?,主键2:?},{主键1:?,主键2:?},{主键1:?,主键2:?}]
	 * @param parameter
	 * @return
	 */
	public int editSomeFields(List<?> pks,T parameter){
		List<TableFieldInfo> pkFields=TableUtil.getPkFields(this.entityClass);
		int pkSize=pkFields.size();
		if(pkFields==null || pkSize==0){
			throw new BizException("no-id-find","在对象%s中未找到主键，请确保有id或者标注了 tableId或者tableIds注解",this.entityClass.getName());
		}else if(pkSize==1){//单主键
			UpdateWrapper<T> uw=new UpdateWrapper<>();
			uw.in(pkFields.get(0).getColumn(),pks);
			uw.setEntity(parameter);
			return baseMapper.update(parameter,uw);
		}else {//多主键
			Object pk3=pks.get(0);
			if(! (pk3 instanceof Map)){
				throw new BizException("pks-error","主键列表格式错误,多主键时，假设 branchId,userid为联合主键 上送的参数map={field1:xx,field2:xx,$pks=[{branchId:1,userid:1x},{branchId:2,userid:2x}]}");
			}
			UpdateWrapper<T> uw=new UpdateWrapper<>();
			List<Object> vars=new ArrayList<>();
			for (int i = 0; i < pks.size(); i++) {
				Map<String,Object> pk= (Map<String, Object>) pks.get(i);
				for (int i1 = 0; i1 < pkSize; i1++) {
					vars.add(pk.get(pkFields.get(i1).getColumn()));
				}
			}
			uw.apply(calcInSqlByPkFields(pkFields,pks.size()),vars.toArray(new Object[vars.size()]));
			return super.baseMapper.update(parameter,uw);
		}

	};
	/**
	 * 根据主键同时更新一批数据的某些字段
	 * 类似：
	 * 	单主键 update xxx=?,bbb=? where id in (?,?)
	 * 	多主键 update xxx=?,bbb=? where (id1,id2) in (({0},{0}),({0},{0}))
	 * @param parameter Map类型，其中必须有一个key值为主键列表，如果单主键，则 key=主键+s.如果是多主键，统一为 key=$pks
	 *                  单主键: 假设 userid为主键 map={filed1:xx,field2:xx,userid:[1,2,3,4]}
	 *                  多主键：假设 branchId,userid为联合主键 map={field1:xx,field2:xx,$pks=[{branchId:1,userid:1x},{branchId:2,userid:2x}]}
	 * @return
	 */
	public int editSomeFields(Map<String,Object> parameter) {

		List<TableFieldInfo> pkFields=TableUtil.getPkFields(this.entityClass);
		List<TableFieldInfo> allFiels=TableUtil.getAllFields(this.entityClass);

		if(pkFields.size()==0){
			return 0;
		}else if(pkFields.size()==1){
			TableFieldInfo field=pkFields.get(0);
			List<String> ids=null;
			if(parameter.containsKey(QueryTools.PKS)){
				ids= (List<String>) parameter.get(QueryTools.PKS);
			}else if(parameter.containsKey("ids")){
				//为了兼容就版本传递ids作为主键列表
				ids= (List<String>) parameter.get("ids");
			}else {
				throw new BizException(LangTips.errMsg("ids-required","主键列表 %s 或者 %s 不存在",QueryTools.PKS,"ids"));
			}

			UpdateWrapper<T> uw=new UpdateWrapper<>();
			for (String key0 : parameter.keySet()) {
				String key=BaseUtils.underscoreName(key0);
				if(key0.equalsIgnoreCase(field.getProperty())||key0.equalsIgnoreCase(field.getProperty()+"s")){
					continue;
				}
				Optional<TableFieldInfo> fieldInfo=allFiels.stream().filter(k->k.getProperty().equalsIgnoreCase(key0)).findAny();
				if(!fieldInfo.isPresent()){
					continue;
				}
				uw.set(fieldInfo.get().getColumn(),parameter.get(key0));
			}
			uw.in( field.getColumn(),ids);
			return super.baseMapper.update(null, uw);
		}else if(pkFields.size()>1){
			List<Map> pks=null;

			if(parameter.containsKey(QueryTools.PKS)){
				pks= (List<Map>) parameter.get(QueryTools.PKS);
			}else if(parameter.containsKey("ids")){
				//为了兼容就版本传递ids作为主键列表
				pks= (List<Map>) parameter.get("ids");
			}else {
				throw new BizException(LangTips.errMsg("ids-required","主键列表 %s 或者 %s 不存在",QueryTools.PKS,"ids"));
			}
			Object pk3=pks.get(0);
			if(! (pk3 instanceof Map)){
				throw new BizException("pks-error","主键列表格式错误,多主键时，假设 branchId,userid为联合主键 上送的参数map={field1:xx,field2:xx,$pks=[{branchId:1,userid:1x},{branchId:2,userid:2x}]}");
			}
			UpdateWrapper<T> uw=new UpdateWrapper<>();
			for (String key0 : parameter.keySet()) {
				if(QueryTools.PKS.equals(key0) || "ids".equals(key0)){
					continue;
				}
				Optional<TableFieldInfo> optional=allFiels.stream().filter(k->k.getProperty().equalsIgnoreCase(key0)).findAny();
				if(!optional.isPresent()){
					continue;
				}
				uw.set(optional.get().getColumn(),parameter.get(key0));
			}

			List<Object> vars=new ArrayList<>();
			for (int i = 0; i < pks.size(); i++) {
				Map<String,Object> pk= (Map<String, Object>) pks.get(i);
				for (int i1 = 0; i1 < pkFields.size(); i1++) {
					vars.add(pk.get(pkFields.get(i1).getProperty()));
				}
			}
			uw.apply(calcInSqlByPkFields(pkFields,pks.size()),vars.toArray(new Object[vars.size()]));
			return super.baseMapper.update(null,uw);
		}else {
			return 0;
		}
	}





	public String createKey(String keyName) {
		return sequenceService.getTablePK(this.getClass().getName(), keyName);
	}

	public String getNameSpace() {
		return this.mapperClass.getName();
	}

	public String statement(String sqlid){
		if(sqlid.contains(".")){
			return sqlid;
		}
		return getNameSpace()+"."+sqlid;
	}
	
	@PostConstruct
	protected void init(){

		if(this.sequenceService==null){
			this.sequenceService=new SequenceService();
		}
	}




	public Sequence getSequenceService() {
		return sequenceService;
	}

	public void setSequenceService(Sequence sequenceService) {
		this.sequenceService = sequenceService;
	}

	public Throwable getCause(Throwable cause ){ 
		if(cause!=null && cause instanceof BizException){
			return cause; 
		}else if(cause==null){
			return null; 
		}else{
			return this.getCause(cause.getCause());
		}
	}

	public static String calcInSqlByPkFields(List<TableFieldInfo> pkFields,int pkSize){
		StringBuffer sql=new StringBuffer();
		int pkFieldSize=pkFields.size();
		sql.append("(");
		for (int i = 0; i < pkFieldSize; i++) {
			if(i>0){
				sql.append(",");
			}
			sql.append(pkFields.get(i).getColumn());
		}
		sql.append(") in ( ");

		for (int i = 0; i < pkSize; i++) {
			if(i>0){
				sql.append(",");
			}
			sql.append("(");
			for (int i1 = 0; i1 < pkFieldSize; i1++) {
				if(i1>0){
					sql.append(",");
				}
				sql.append("{"+(pkFieldSize*i+i1)+"}");
			}
			sql.append(")");
		}
		sql.append(")");
		return sql.toString();
	}
}