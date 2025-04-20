package com.mdp.core.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mdp.core.entity.Tips;
import com.mdp.core.err.BizException;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 平台级公共类，没有业务逻辑，仅提供方便操作的一些公共函数，辅助开发
 * @author cyc
 * @author cyc 20151102 增加静态函数 p(Object...keyAndValues),提供快速构造一个map的方法,根据传入的键值对，动态放置到Map中，并返回Map，减少不必要的put和 get操作
 * @since 1.0
 */
public class BaseUtils {
	private final static String DATE_TIME_FORMATTER = "yyyy-MM-dd HH:mm:ss";
	public static ObjectMapper objectMapper=new ObjectMapper();

	static {
		initInnerObjectMapper();
	}

	public static void initObjectMapper(ObjectMapper objectMapper){
		BaseUtils.objectMapper=objectMapper;
		initInnerObjectMapper();
	}
	private static void initInnerObjectMapper(){
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
		//指定遇到date按照这种格式转换
		SimpleDateFormat fmt = new SimpleDateFormat(DATE_TIME_FORMATTER);
		objectMapper.setDateFormat(fmt);

		// 日期序列化配置

		JavaTimeModule javaTimeModule = new JavaTimeModule();

		// Date序列化和反序列化
		javaTimeModule.addSerializer(Date.class, new JsonSerializer<Date>() {
			@Override
			public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
					throws IOException {
				SimpleDateFormat formatter = new SimpleDateFormat(DATE_TIME_FORMATTER);
				String formattedDate = formatter.format(date);
				jsonGenerator.writeString(formattedDate);
			}
		});
		javaTimeModule.addDeserializer(Date.class, new MdpDateDeserializer() );

		/**
		 * 将Long,BigInteger序列化的时候,转化为String,某些类里面的Long类型数据超长造成精度缺失可以在属性上使用@JsonSerialize注解
		 */
		objectMapper.registerModule(javaTimeModule);
	}
	static class InstantCustomDeserializer extends JsonDeserializer<Instant> {

		@Override
		public Instant deserialize(JsonParser p, DeserializationContext ctxt)
				throws IOException, JsonProcessingException {
			String dateString = p.getText().trim();
			if(StringUtils.hasText(dateString)){
				Date pareDate;
				pareDate = DateUtils.parse(dateString);
				if(null != pareDate){
					return pareDate.toInstant();
				}
			}
			return null;
		}

	}
	static class InstantCustomSerializer extends JsonSerializer<Instant> {
		private DateTimeFormatter format;

		private InstantCustomSerializer(DateTimeFormatter formatter) {
			this.format = formatter;
		}

		@Override
		public void serialize(Instant instant, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
			if (instant == null) {
				return;
			}
			String jsonValue = format.format(instant.atZone(ZoneId.systemDefault()));
			jsonGenerator.writeString(jsonValue);
		}
	}
	/**
	 * 快速构造一个map，根据传入的键值对，动态放置到Map中，并返回Map，减少不必要的put和 get操作<br>
	 * 用法如下：<br>
	 * map("account_id","admin","account_name","管理员","account_status",1);<br>
	 * 
	 * 内部处理成如下：<br>
	 * Map<String,Object> p=new HashMap<String,Object>();<br>
	 * 	p.put("account_id","admin");<br>
	 * 	p.put("account_name","管理员");<br>
	 * 	p.put("account_status",1);<br>
	 * return p;<br>
	 * @param keyAndValues 可变参数的键值对 
	 *  如 "account_id",admin <br>
	 *  或者  "account_id",admin,"account_name","管理员" <br>
	 *  或者  "account_id","admin","account_name","管理员","account_status",1等<br>
	 * @return Map
	 */
	public static  Map<String,Object> map(Object...keyAndValues){
		if(keyAndValues.length%2!=0){
			throw new BizException("E0002", "传入的参数必须成对出现,如[account_id,admin,account_name,管理员]");
		}
		int l=keyAndValues.length/2;
		Map<String,Object> p=new HashMap<String,Object>();
		for (int i = 0; i < l; i++) {
			p.put((String) keyAndValues[2*i], keyAndValues[2*i+1]);
		}
		return p;
	};
	
	
	public static  Map<String,Object> map(Object p){
		 if(p instanceof Map){
			return (Map<String,Object>)p;
		}else{
			return null;
		}
	}
	
	public static String encodeBase64(String value){
		/**
		StringBuilder sb = new StringBuilder(new String(Base64.encode(value.getBytes())));
	    while (sb.charAt(sb.length() - 1) == '=') {
	        sb.deleteCharAt(sb.length() - 1);
	    }
	    return sb.toString();
	    **/
		return null;
	}
	
	public static Map<String, Object> toMap(Object o) {

		return objectMapper.convertValue(o,Map.class);
	}
 
	
	public static Map<String, Object> toMap(Object o,Set<String> ignoreFieldSet) {
		 return objectMapper.convertValue(o,Map.class);
	}
	
	/**
	 * 将当前对象转换成map.<br>
	 * 所有具有set方法的属性都会写到map中,并且属性会被一式两份放到map中.<br>
	 * 如对于setAccountId()函数,会被识别为具有accountId属性,同时会用反驼峰命名法再将accountId转成account_id,作为map中的key值.<br>
	 * 即转换出来的map中,存在两个属性,一个是原生的accountId属性,一个是反驼峰命名的account_id属性<br>
	 */
	public static <T> T fromMap(Map<String,Object> sourceMap,Class<T> clazz){
		 return objectMapper.convertValue(sourceMap,clazz);
		
	}

	/**
	 * 将一个对象转换成 map ，可选择是否自动将驼峰命名字段转换成 下横线分割的字段名字
	 * @param o
	 * @param toUnderscoreName 是否将map的key转换成下横线 如 branchNo->branch_no, branchName -> branch_name
	 * @return
	 */
	public static Map<String, Object> toMap(Object o,boolean toUnderscoreName) {
 		Map<String,Object> data= objectMapper.convertValue(o,Map.class);
 		Map<String,Object> result=new HashMap<>();
 		if(toUnderscoreName){
			for (Map.Entry<String, Object> kv : data.entrySet()) {
				result.put(underscoreName(kv.getKey()),kv.getValue());
			}
			return result;
		}
 		return data;
	}
	/**
	 * 将使用驼峰命名法命名的字段转换成下横线分隔的字段.
	 * 如 accountName 转换成 account_name
	 * @param name 字段名 如accountName
	 * @return 转换后的字段名 如 account_name
	 */
	public static String underscoreName(String name) {
		if (!StringUtils.hasLength(name)) {
			return "";
		}
//		name.replaceAll("_", "");
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
	
	public static Tips mapToTips(Map<String,Object> m){
		if(m!=null){
			if(m.containsKey("tips")){
				Map<String,Object> tipsMap=(Map<String, Object>) m.get("tips");

				Tips tips=new Tips();
				tips.putAll(tipsMap);
				if( "true".equals(tipsMap.get("isOk")) || true== (boolean)tipsMap.get("isOk")){
					tips.put("isOk", true);
				}else{
					tips.put("isOk", false);
				}
				return tips;
			}else {
				return new Tips("成功");
			}
		}
		return null;
		
	}
	public static Object getFieldValueByAnnotation(Object object, Class clazz) {
		Field[] fields = object.getClass().getDeclaredFields();
		for (Field field : fields) {
			boolean bool = field.isAnnotationPresent(clazz);
			if (bool) {
				try {
					field.setAccessible(true);
					return field.get(object);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}

			}
		}
		return null;
	}
	public static Field getFieldByAnnotation(Object object, Class clazz) {
		Field[] fields = object.getClass().getDeclaredFields();
		for (Field field : fields) {
			boolean bool = field.isAnnotationPresent(clazz);
			if(bool){
				return field;
			}
		}
		return null;
	}

	public static void main(String[] args) {
		Map<String,Object> p=new HashMap<>();
		p.put("date2","2020-10-01T19:23:46");
		//DateTest dt=BaseUtils.fromMap(p,DateTest.class);
		//System.out.println(BaseUtils.to);
	}
}

