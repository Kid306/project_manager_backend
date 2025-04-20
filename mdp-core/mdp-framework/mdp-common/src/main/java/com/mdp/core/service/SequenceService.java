package com.mdp.core.service;

import com.mdp.core.api.Sequence;
import com.mdp.core.utils.ObjectTools;
import com.mdp.core.utils.SequenceFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.*;

/**
 * 序列号机制用来生成系统中的序列号<br>
 * 序列号以一个递增的整数为基础，构造出序列字符串。<br>
 * 序列号的定义由以下各部分组成<br>
 * a. noid 序列号的唯一标识<br>
 * b. noname 序列号的名称 <br>
 * c. nolength 序列号中整数部分的长度<br>
 * d. notype 序列号的类型，即 复位设置： 1: 不复位 2: 按年复位 3: 按月复位 4: 按日复位<br>
 * e. prefix 前缀字符串<br>
 * f. postfix 后缀字符串<br>
 * 如某序列号的prefix="CN",postfix="_files",nolength=5，<br>
 * 则生成的序列号为 CN00001_files,CN00002_files,CN00003_files等<br>
 * 
 * 前缀字符串与后缀字符串中可以使用{date:yyyy}(四位年份),{date:yy}(两位年份),{date:MM}(月份),{date:dd}(日期)，{date:HH}(小时),{date:mm}(分钟),{date:ss}(秒),{date:SSS}(毫秒),{time}(时间戳),{rand:2}(2位随机数),等符号,<br>
 *  
 * 复位标志表示序列号何时重新从头开始可选为1: 不复位 2: 按年复位 3: 按月复位 4: 按日复位<br>
 * 
 * prefix及postfix举例： {date:yyyyMMddHHmmssSSS}{rand:2}",
 * {rand:2} 会替换成随机数,后面的数字是随机数的位数 
 * {time} 会替换成时间戳 
 * {date:yyyy} 会替换成四位年份 
 * {date:yy} 会替换成两位年份 
 * {date:MM} 会替换成两位月份 
 * {date:dd} 会替换成两位日期 
 * {date:HH} 会替换成两位小时 
 * {date:mm} 会替换成两位分钟 
 * {date:ss} 会替换成两位秒 
 * {date:SSS}会替换成三位毫秒 
 * @author cyc
 * @since 1.0 
 ****/
public class SequenceService implements Sequence {


	int init=0;
	Logger log= LoggerFactory.getLogger(SequenceService.class);
	Set<String> set=new HashSet<String>(1000);
	Set<String> accountSet=new HashSet<String>(1000);
	Set<String> useridSet=new HashSet<String>(1000);

	Map<String,Object> smsCodeMap=new HashMap<String,Object>();
	Set<String> commonNoSet=new HashSet<String>(1000);

	int userSeq=0;
	int accountSeq=0;
	Map<String,Integer> setSeq=new HashMap<>();
	Map<String, Integer> commonNoSeq=new HashMap();

	
	/***
	 * 序列号格式化工具，如给定 {time}{date:yyyyMMddHHmmssS}{rand:2}  结果为：14768373247162016101908352471601<br>
	 * {time}                  					--> 1476837324716<br>
	 * {date:yyyyMMddHHmmssS}  					--> 20161019083524716<br>
	 * {date62:yyyyMMddHHmmssS}                 --> aldkfdjfkgjla 将日期按62进制表示{DATE62:yyyyMMddHHmmssSS} <br>
	 * {rand:2}               					--> 01<br>
	 * {time}{date:yyyyMMddHHmmssS}{rand:2}		--> 14768373247162016101908352471601<br>
	 * 
	 **/
	public String getCommonNo(String format) {
		String s= SequenceFormat.parse(format);
		s=s.toUpperCase(Locale.ROOT);
		String key=format+"_"+s;
		while(commonNoSet.contains(key)){
			 s= SequenceFormat.parse(format);
			s=s.toUpperCase(Locale.ROOT);
			 key=format+"_"+s;
		}
		commonNoSet.add(key);
		return s; 
		
	}
	 
	@Override
	public String getAccountId(){
		if(accountSeq>=9){
			accountSeq=0;
		}
		accountSeq=accountSeq+1;
		String date=SequenceFormat.parse("{DATE62}");
		String s= getSeqStr("A",date,accountSeq+"");
		while(accountSet.contains(s)){
			s= getSeqStr("A",date,accountSeq+"");
		}
		accountSet.add(s);
		return s;
	}

	public static String getSeqStr(String prefix,String dateStr,String seq){
		String rands=SequenceFormat.parse("{rand:2}");
		String s= prefix+dateStr+seq+rands;
		s=s.toUpperCase(Locale.ROOT);
		return s;
	}

	@Override
	public String getUserid(){
		if(userSeq>=9){
			userSeq=0;
		}
		userSeq=userSeq+1;
		String date=SequenceFormat.parse("{DATE62}");
		String s= getSeqStr("U",date,userSeq+"");
		while(useridSet.contains(s)){
			s = getSeqStr("U",date,userSeq+"");
		}
		useridSet.add(s);
		return s;
	}
	
	
	/**
	 * 交易流水号 －本系统内部使用
	 * @return
	 */
	@Override
	public String getTxFlowNo() {
		return getTablePK("req","reqFlowNo");
	}
	/***
	 * 全局事务跟踪号，用于在各个系统中流转，保持交易的连贯性。
	 * @return
	 */
	@Override
	public String getGlobalFlowNo() {
		String glfn=UUID.randomUUID().toString();
		return glfn;
	}
	@Override
	public String getSmsCode(String phoneNo,int length){
		String s= SequenceFormat.parse("{rand:"+length+"}");
		s=s.toUpperCase(Locale.ROOT);
		while(smsCodeMap.containsKey(s) && smsCodeMap.containsValue(phoneNo)){
			s= SequenceFormat.parse("{rand:"+length+"}");
			s=s.toUpperCase(Locale.ROOT);
		}
		smsCodeMap.put(s, phoneNo);
		return s;
	}
	@Override
	public boolean validateSmsCode(String phoneNo,String code){
		 
		if(smsCodeMap.containsKey(code) && smsCodeMap.containsValue(phoneNo)){
			return true;
		} 
		return false;
	}
 
	
	/**
	 * 生成某个表对应某个字段的序号
	 * @param tableName
	 * @param colunm
	 * @return
	 */
	@Override
	public String getTablePK(String tableName, String colunm) {
		String realTableName=getRealTableName(tableName);
		String prefix=getPrefixByRealTableName(realTableName);
		String date=SequenceFormat.parse("{DATE:yyyyMMddHHmmssSSS}");
		String seqKey=prefix+realTableName+date;
		Integer seq=setSeq.get(seqKey);
		if(seq==null){
			seq=0;
			setSeq.put(seqKey,seq);
		}else{
			if(seq>=9){
				seq=0;
			}else{
				seq=seq+1;
			}

			setSeq.put(seqKey,seq);
		}
		String s= getSeqStr(prefix,date,seq+"");
		int count=0;
		int size=set.size();
		while(set.contains(s)){
			s = getSeqStr(prefix,date,seq+"");
			count=count+1;
			if(count>size){
				s=this.getTablePK(tableName,colunm);
				return s;
			}
		}
		set.add(s);
		return s;
	}

	@Override
	public String getBranchId() {
		return getCommonNo("B{date62}{rand:2}");
	}

	@Override
	public String getSessionKey() {
		
		return null;
	}
	@Override
	public  String getReqFlowNo() { 
		return getTablePK("req","reqFlowNo");
	}
	public static String genReqFlowNo(){
		return null;
	} 
	
	@Override
	public List<String> batchGenerate(String tableName, String colunm,int count) {

		List ids=new ArrayList<String>();
		for (int i = 0; i < count; i++) {
			ids.add(getTablePK(tableName,colunm));
		}
		return ids;
	}

	public static String getRealTableName(String tableName){
		String realTableName=tableName;
		if(ObjectTools.isNotEmpty(tableName)){
			String[] tabls=tableName.split("\\.");
			String table=tabls[tabls.length-1];
			table=table.toUpperCase(Locale.ROOT);
			if(table.endsWith("IMPL")){
				table=table.substring(0, table.length()-4);
			}

			if(table.endsWith("SERVICE")){
				table=table.substring(0,table.length()-7);
			}
			realTableName=table;
		}else{
			realTableName="PUB";
		}
 		return realTableName;
	}
	public static String getPrefixByRealTableName(String tableName){
		if(tableName.length()<=2){
			return tableName;
		}else {
			String prefix=tableName.substring(0,2);
			return prefix;
		}
	}
	public static String getPrefixByTableName(String tableName){
		String realTableName=getRealTableName(tableName);
		return getPrefixByRealTableName(realTableName);
	}

	public static void main(String[] args) {
		String prefix=getPrefixByTableName("com.aaa.bb.c");
		SequenceService sequenceService=new SequenceService();
		String s1=sequenceService.getTablePK("com.aaa.bb.c","id");
		String s2=sequenceService.getTablePK("com.aaa.bb.cxxxx","id");
		String s3=sequenceService.getTablePK("com.aaa.bb.cagServiceImpl","id");
		String s4=sequenceService.getTablePK("com.aaa.bb.cbgService","id");


		String ss1=sequenceService.getTablePK("com","id");
		String ss2=sequenceService.getTablePK("comServiceImpl","id");
		String ss3=sequenceService.getTablePK("serviceImpl","id");
		String ss4=sequenceService.getTablePK("service","id");

		String ss5=sequenceService.getTablePK("","id");

		String ss6=sequenceService.getTablePK(null,"id");
		int i=100000;
		while (i>0){
			i--;
			String ss7=sequenceService.getTablePK("com.aaa.bb.cxxxx","id");
			System.out.println(i+"--------->"+ss7);
		}

		String ss7=sequenceService.getCommonNo("{time}");
		String ss8=sequenceService.getCommonNo("{time}");
		String ss9=sequenceService.getCommonNo("{time}");
		String ss10=sequenceService.getCommonNo("{time}");
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);
		System.out.println(s4);

		System.out.println(ss1);
		System.out.println(ss2);
		System.out.println(ss3);
		System.out.println(ss4);
		System.out.println(ss5);
		System.out.println(ss6);

		System.out.println(sequenceService.getUserid());
		System.out.println(sequenceService.getAccountId());

		int i2=10000;
		while (i2>0){
			i2--;
			System.out.println(sequenceService.getBranchId());
		}



	}
	@Scheduled(cron = "0 5 * * * ?")
	public void clear(){

		this.set.clear();
		smsCodeMap.clear();
		this.useridSet.clear();
		this.accountSet.clear();
		this.commonNoSet.clear();

		userSeq=0;
		accountSeq=0;
		setSeq.clear();
		commonNoSeq.clear();
		init=1;
	}
	@Scheduled(cron = "0 59 23 * * ?")
	public void clearInitParams(){
		init=0;
	}
}
