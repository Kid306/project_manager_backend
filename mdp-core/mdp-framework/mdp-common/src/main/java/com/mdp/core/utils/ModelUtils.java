package com.mdp.core.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class ModelUtils {

	private static final long serialVersionUID = 1L;

	
	/**
	 * Initialize the mapping metadata for the given class.
	 * @param mappedClass the mapped class.
	 */
	private static HashMap<String, PropertyDescriptor> initialize(Class<?> classType) {
		HashMap<String, PropertyDescriptor> mappedFields = new HashMap<String, PropertyDescriptor>();
		PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(classType);
		for (PropertyDescriptor pd : pds) {
			if (pd.getWriteMethod() != null) {
				mappedFields.put(pd.getName().toLowerCase(), pd);
				String underscoredName = underscoreName(pd.getName());
				if (!pd.getName().toLowerCase().equals(underscoredName)) {
					mappedFields.put(underscoredName, pd);
				}
			}
		}
		return mappedFields;
	}
	
		/**
	 * Convert a name in camelCase to an underscored name in lower case.
	 * Any upper case letters are converted to lower case with a preceding underscore.
	 * @param name the string containing original name
	 * @return the converted name
	 */
	public static String underscoreName(String name) {
		if (!StringUtils.hasLength(name)) {
			return "";
		}
		StringBuilder result = new StringBuilder();
		result.append(name.substring(0, 1).toLowerCase());
		for (int i = 1; i < name.length(); i++) {
			String s = name.substring(i, i + 1);
			String slc = s.toLowerCase();
			if (!s.equals(slc)) {
				result.append("_").append(slc);
			}
			else {
				result.append(s);
			}
		}
		return result.toString();
	}
	
	public static Map<String, Object> objectToMap(Object object) {
		return objectToMap(null,object);
	}

	/**
	 * 
	 * 通过命名空间将javabean转成map，如果一个map存放多个表的数据，多个字段同名的情况下，添加命名空间进行区分 。
	 * 比如:
	 * 	SysAccount account=new SysAccount();
	 *  Map<String,Object> map=account.toMap("ac");
	 *  
	 *  则map中每个key会加上ac。作为前缀，数据如下：
	 *  map。put("ac。account_id","1");
	 *  map。put("ac.account_name","2");
	 *  map。put("ac.account_status","1");
	 *  
	 *  通过map初始化javabean的时候：
	 *  SysAccount account=new SysAccount();
	 *  account.fromMap("ac",map);//优先从 map中取aa.account_id的字段，如果没找到，再找没有命名空间的字段如account_id，
	 *  
	 * @param nameSpace 命名空间
	 */
	public static Map<String, Object> objectToMap(String nameSpace,Object object) {
		
		HashMap<String, PropertyDescriptor> mappedFields =initialize(object.getClass());
		if(nameSpace!=null&&!"".equals(nameSpace)){
			nameSpace=nameSpace+".";
		}else{
			nameSpace="";
		}
		Map<String, Object> result=new HashMap<String,Object>();
		BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(object);
		Iterator<Entry<String, PropertyDescriptor>> ite=mappedFields.entrySet().iterator();
		while(ite.hasNext()){
			Entry<String, PropertyDescriptor> entry=(Entry<String, PropertyDescriptor>) ite.next();
			result.put(nameSpace+ entry.getKey(), bw.getPropertyValue(entry.getValue().getName()));
		}
		return result;
	}


	
	/**
	 * 通过命名空间，将map中的数据，转化成javabean对象的数据
	 * 比如map中数据如下
	 *  map。put("account_id","admin");
	 *  map。put("bb.account_id","admin_bb");
	 *  map。put("aa.account_id","admin_aa");
	 *  
	 *  通过map初始化javabean的时候：
	 *  SysAccount account=new SysAccount();
	 *  account.fromMap("aa",map);//优先从 map中取aa.account_id的字段，如果没找到，再找没有命名空间的字段如account_id，
	 * @param nameSpace 命名空间，如果一个map存放多个表的数据，多个字段同名的情况下，添加命名空间进行区分 。	 *  
	 * @param sourceMap
	 * @return 
	 * @return 
	 */
//	public void fromMap(Map<String, Object> sourceMap) {
//		fromMap("",sourceMap);
//	}
//	
	public static <T>  T mapToObject(Map<String,Object> sourceMap,Class<T> classType){

		HashMap<String, PropertyDescriptor> mappedFields =initialize(classType);
		T mappedObject = BeanUtils.instantiate(classType);
		BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(mappedObject);
		Iterator<Entry<String, Object>> ite=sourceMap.entrySet().iterator();
		while(ite.hasNext()){
			Entry<String, Object> entry=ite.next();
			PropertyDescriptor pd = mappedFields.get(entry.getKey().toLowerCase());
			if (pd != null) {
				bw.setPropertyValue(pd.getName(), entry.getValue());
			}
			
		}
		return mappedObject;
		
	}
	
	
	
	
	
}
