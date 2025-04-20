package com.mdp.core.dao;

import com.mdp.core.entity.No;
import com.mdp.core.utils.SequenceFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * 序列号机制用来生成系统中的序列号<br>
 * 序列号以一个递增的整数为基础，构造出序列字符串。<br>
 * 序列号的定义由以下各部分组成<br>
 * a. noid 序列号的唯一标识<br>
 * b. noName 序列号的名称 <br>
 * c. noLength 序列号中整数部分的长度<br>
 * d. noType 序列号的类型，即 复位设置： 1: 不复位 2: 按年复位 3: 按月复位 4: 按日复位<br>
 * e. prefix 前缀字符串<br>
 * f. postfix 后缀字符串<br>
 * 如某序列号的prefix="CN",postfix="_files",noLength=5，<br>
 * 则生成的序列号为 CN00001_files,CN00002_files,CN00003_files等<br>
 * 
 * 前缀字符串与后缀字符串中可以使用{date:yyyy}(四位年份),{date:yy}(两位年份),{date:MM}(月份),{date:dd}(日期)，{date:HH}(小时),{date:mm}(分钟),{date:ss}(秒),{date:SSS}(毫秒),{time}(时间戳),{rand:2}(2位随机数),等符号,<br>
 * 分别表示当前年份，月份与日期<br>
 * 复位标志表示序列号何时重新从头开始可选为1: 不复位 2: 按年复位 3: 按月复位 4: 按日复位<br>
 * 
 * @author chenyc
 * 
 */
public class SequenceDao extends JdbcDaoSupport  {
	
	public static final Long DEFAULT_START_ID = new Long(1);

	public static final int DEFAULT_LENGTH = 10;

	public boolean autoCreate=true;

	public void setAutoCreate(boolean autoCreate) {
		this.autoCreate = autoCreate;
	}

	static Logger log= LoggerFactory.getLogger(SequenceDao.class);
	
	
	/**
	 * 序列号机制用来生成系统中的序列号<br>
	 * 序列号以一个递增的整数为基础，构造出序列字符串。<br>
	 * 序列号的定义由以下各部分组成<br>
	 * @param noid 序列号的唯一标识<br>
	 * @param noName 序列号的名称 <br>
	 * @param noLength 序列号中整数部分的长度 ,0表示保持原样不在数字前面补0<br>
	 * @param noType  复位设置： 1: 不复位 2: 按年复位 3: 按月复位 4: 按日复位<br>
	 * @param prefix 前缀字符串<br>
	 * @param postfix 后缀字符串<br>
	 * 如某序列号的prefix="CN",postfix="_files",noLength=5，<br>
	 * 则生成的序列号为 CN00001_files,CN00002_files,CN00003_files等<br>
	 * 
	 * 前缀字符串与后缀字符串中可以使用{yyyy}(四位年份),{yy}(两位年份),{mm}(月份),{dd}(日期)等符号,<br>
	 * 分别表示当前年份，月份与日期<br>
	 * 复位标志表示序列号何时重新从头开始可选为1: 不复位 2: 按年复位 3: 按月复位 4: 按日复位<br>
	 * 
	 * @author cyc
	 * 
	 */
	public String generate(String noid,String noName,int noLength,String noType,String prefix,String postfix,Date date) {
		try {
			List l = batchGenerate(noid,noName,noLength,noType,prefix,postfix,1,date);
			return (String) l.get(0);
		} catch (Exception e) { 
			log.error("创建序列号失败"+noid,e);
			throw e;
		}
	}
	/**
	 * 序列号机制用来生成系统中的序列号<br>
	 * 序列号以一个递增的整数为基础，构造出序列字符串。<br>
	 * 序列号的定义由以下各部分组成<br>
	 * @param noid 序列号的唯一标识<br>
	 * @param noName 序列号的名称 <br>
	 * @param noLength 序列号中整数部分的长度 ,0表示保持原样不在数字前面补0<br>
	 * @param noType  复位设置： 1: 不复位 2: 按年复位 3: 按月复位 4: 按日复位<br>
	 * @param prefix 前缀字符串<br>
	 * @param postfix 后缀字符串<br>
	 * @param count 批量生成几个序列号
	 * 如某序列号的prefix="CN",postfix="_files",noLength=5，<br>
	 * 则生成的序列号为 CN00001_files,CN00002_files,CN00003_files等<br>
	 * 
	 * 前缀字符串与后缀字符串中可以使用{yyyy}(四位年份),{yy}(两位年份),{mm}(月份),{dd}(日期)等符号,<br>
	 * 分别表示当前年份，月份与日期<br>
	 * 复位标志表示序列号何时重新从头开始可选为1: 不复位 2: 按年复位 3: 按月复位 4: 按日复位<br>
	 * 
	 * @author cyc
	 * 
	 */
	public List batchGenerate(String noid,String noName,int noLength,String noType,String prefix,String postfix,int count,Date date) {
		Calendar cale=Calendar.getInstance();
		cale.setTime(date);
		No no = loadNO(noid,noName, noLength,noType,prefix,postfix,count,cale);
		long id = no.getNextid(); 
		List l = new ArrayList();
		for (int i = 0; i < count; i++) {
			String value = makeSequence(id, no.getNoLength(), no.getPrefix(),
					no.getPostfix(), cale);
			id++;
			l.add(value);

		}

		return l;
	}

	private String makeSequence(long id, int noLength, String prefix,
			String postfix, Calendar cale) {
		String seq = integerToString(id, noLength);
		if (noLength>0 && seq.length() > noLength) {
			throw new IllegalArgumentException("序列号位数溢出！");
		}

		if ((prefix == null) && (postfix == null)) {
			return seq;
		}
 
		String prefixStr=prefix==null?"": SequenceFormat.format(prefix);
		String postfixStr=postfix==null?"":SequenceFormat.format(postfix);
		String ret =prefixStr + seq + postfixStr;
		return ret;
	}

	private String integerToString(long value, int num) {
		StringBuffer ret = new StringBuffer(Long.toString(value));
		if (ret.length() < num) {
			for (int i = ret.length(); i < num; i++) {
				ret.insert(0, "0");
			}
		}
		return ret.toString();
	}


	/**
	 * 序列号机制用来生成系统中的序列号<br>
	 * 序列号以一个递增的整数为基础，构造出序列字符串。<br>
	 * 序列号的定义由以下各部分组成<br>
	 * @param noid 序列号的唯一标识<br>
	 * @param noName 序列号的名称 <br>
	 * @param noLength 序列号中整数部分的长度 ,0表示保持原样不在数字前面补0<br>
	 * @param noType  复位设置： 1: 不复位 2: 按年复位 3: 按月复位 4: 按日复位<br>
	 * @param prefix 前缀字符串<br>
	 * @param postfix 后缀字符串<br>
	 * @param number 批量生成几个序列号
	 * 如某序列号的prefix="CN",postfix="_files",noLength=5，<br>
	 * 则生成的序列号为 CN00001_files,CN00002_files,CN00003_files等<br>
	 * 
	 * 前缀字符串与后缀字符串中可以使用{yyyy}(四位年份),{yy}(两位年份),{mm}(月份),{dd}(日期)等符号,<br>
	 * 分别表示当前年份，月份与日期<br>
	 * 复位标志表示序列号何时重新从头开始可选为1: 不复位 2: 按年复位 3: 按月复位 4: 按日复位<br>
	 * 
	 * @author cyc
	 * 
	 */
	public No loadNO(String noid, String noName, int noLength, String noType, String prefix, String postfix, int number, Calendar cale) {

		No no = null;
		Connection con = null;
		PreparedStatement state=null;
		ResultSet rs=null;
		try {
			int year=cale.get(Calendar.YEAR);
			int month=cale.get(Calendar.MONTH)+1;
			int day=cale.get(Calendar.DAY_OF_MONTH);
			String sql="select id,noid, no_name, no_length, no_type, prefix, postfix,nextid,year,month,day from adm.sys_no where noid = ?";
			if("2".equals(noType)){
				sql+=" and year=?";
			}else if("3".equals(noType)){
				sql+=" and year=? and month=?";
			}else if("4".equals(noType)){
				sql+=" and year=? and month=? and day=?";
			}
			sql+=" for update";
			con = getConnection();
			state = con
					.prepareStatement(
							sql,
							ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);
			state.setString(1, noid);
			if("2".equals(noType)){
				state.setInt(2, year);
			}else if("3".equals(noType)){
				state.setInt(2, year);
				state.setInt(3, month);
			}else if("4".equals(noType)){
				state.setInt(2, year);
				state.setInt(3, month);
				state.setInt(4, day);
			}
			rs = state.executeQuery();

			if (rs.next()) {
				no = new No();
				no.setId(rs.getString("id"));
				no.setNoid(rs.getString("noid"));
				no.setNoLength(rs.getInt("no_length"));
				no.setNoName(rs.getString("no_name"));
				no.setNoType(rs.getString("no_type"));
				no.setNextid(rs.getLong("nextid"));
				no.setPostfix(rs.getString("postfix"));
				no.setPrefix(rs.getString("prefix"));
				no.setYear(rs.getInt("year"));
				no.setMonth(rs.getInt("month"));
				no.setDay(rs.getInt("day"));
				rs.updateLong("nextid", no.getNextid()+number);
				rs.updateRow();
			} else {
				if (autoCreate) {
					rs.moveToInsertRow();
					// 设置默认值
					rs.updateString("id", SequenceFormat.parse("{date62:yyyyMMddHHmmssSSS}{rands:4}"));
					rs.updateString("noid", noid);
					rs.updateString("no_name", noName);
					rs.updateString("no_type", noType);
					rs.updateInt("no_length", noLength);
					if("1".equals(noType)){
						rs.updateInt("year", 0);
						rs.updateInt("month", 0);
						rs.updateInt("day", 0); 
					}else if("2".equals(noType)){
						rs.updateInt("year", year);
						rs.updateInt("month", 0);
						rs.updateInt("day", 0); 
					}else if("3".equals(noType)){
						rs.updateInt("year", year);
						rs.updateInt("month", month);
						rs.updateInt("day", 0); 
					}else if("4".equals(noType)){
						rs.updateInt("year", year);
						rs.updateInt("month", month);
						rs.updateInt("day", day); 
					}
					rs.updateString("prefix", prefix);
					rs.updateString("postfix", postfix);
					rs.updateLong("nextid", 1+number);
					rs.insertRow();

					no = new No();
					no.setNoid(noid);
					no.setNoName(noName);
					no.setNoType(noType);
					no.setNoLength(noLength);
					no.setPostfix(postfix);
					if("1".equals(noType)){
						no.setYear(0);
						no.setMonth(0);
						no.setDay(0);
					}else if("2".equals(noType)){
						no.setYear(year);
						no.setMonth(0);
						no.setDay(0);
					}else if("3".equals(noType)){
						no.setYear(year);
						no.setMonth(month);
						no.setDay(0);
					}else if("4".equals(noType)){
						no.setYear(year);
						no.setMonth(month);
						no.setDay(day);
					}
					no.setPrefix(prefix);
					no.setNextid(1);
				}
			}
		} catch (SQLException ex) {
			log.error("创建序列号失败",ex);
			throw new RuntimeException(ex);
		} finally {
			try {
				if(rs!=null){
					rs.close();
				}
				if(state!=null){

					state.close();
				}
				if(con==null) {
					return no;
				}
				if(!con.isClosed() && !con.getAutoCommit()) {
					con.commit();
				}
				if(con.isClosed()){
					con=null;
				}else{
					con.close();
					con=null;
				}
			} catch (SQLException e) {
				log.error("创建序列号失败",e);
			} 
		}

		return no;

	}

 
	/**
	 * 自动生成一个永远不复位的序号<br>
	 * 序列号的定义由以下各部分组成<br>
	 * @param noid 序列号的唯一标识<br>
	 * 如某序列号的prefix="CN",postfix="_files",noLength=5，<br>
	 * 则生成的序列号为 CN00001_files,CN00002_files,CN00003_files等<br>
	 * 
	 * 前缀字符串与后缀字符串中可以使用{yyyy}(四位年份),{yy}(两位年份),{mm}(月份),{dd}(日期)等符号,<br>
	 * 分别表示当前年份，月份与日期<br>
	 * 复位标志表示序列号何时重新从头开始可选为1: 不复位 2: 按年复位 3: 按月复位 4: 按日复位<br>
	 * 
	 * @author cyc
	 * 
	 */
	 
	public String generate(String noid,String name) {
		Calendar cale=Calendar.getInstance();
		List l = batchGenerate(noid,name, 1,cale);
		return (String) l.get(0);
	}
	/**
	 * 批量生成一批永远不复位的序列号
	 * 
	 * @author cyc
	 * 
	 */
	public List batchGenerate(String noid,String name, int count,Calendar cale) {
		No no = loadNO(noid,name,DEFAULT_LENGTH,"1","","",count,cale); 
		long id=no.getNextid(); 
		List l = new ArrayList();
		for (int i = 0; i < count; i++) {
			String value = makeSequence(id, no.getNoLength(), no.getPrefix(),
					no.getPostfix(), cale);
			id++;
			l.add(value);

		}

		return l;
	}

}
