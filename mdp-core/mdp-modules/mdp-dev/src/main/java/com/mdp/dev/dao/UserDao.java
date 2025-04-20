package com.mdp.dev.dao;

import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.mdp.core.entity.LangTips;
import com.mdp.core.err.BizException;
import com.mdp.core.utils.ObjectTools;
import com.mdp.dev.utils.SafePasswordEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.DigestUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * 
 * @author cyc 将entity的名字改成符合驼峰命名规范的名字 20150215
 * @see org.springframework.jdbc.support.JdbcUtils
 *
 */
@Repository
public class UserDao {
	
	SafePasswordEncoder passwordEncoder=new SafePasswordEncoder();


	DynamicRoutingDataSource dataSourceService;


	public Logger logger = LoggerFactory.getLogger(UserDao.class);

	public Connection getConnection() {
		try {
			if(dataSourceService==null){
				dataSourceService= SpringUtil.getBean(DynamicRoutingDataSource.class);
			}
			return dataSourceService.getConnection();
		} catch (SQLException e) {
			logger.error("db-connection-error",e);
			throw new RuntimeException("db-connection-error",e);
		}
	}
	public Connection getConnection(String ds) {
		try {
			if(ObjectTools.isEmpty(ds)){
				throw new BizException(LangTips.errMsg("ds-required","数据源%s不能为空","ds"));
			}
			if(dataSourceService==null){
				dataSourceService=SpringUtil.getBean(DynamicRoutingDataSource.class);
			}
			DataSource dataSource=dataSourceService.getDataSource(ds);
			if(dataSource==null){
				throw new BizException(LangTips.errMsg("ds-not-exists","%s数据源不存在",ds));
			}
			return dataSourceService.getDataSource(ds).getConnection();
		} catch (SQLException e) {
			logger.error("db-connection-error",e);
			throw new RuntimeException("db-connection-error",e);
		}
	}
	
	public String getNewMd5Password(String password){
		return passwordEncoder.encode(password);
	}
   
   /**Authorized filter invocation [POST /oauth2/token?grant_type=password&auth_type=password_display_userid&userloginid=superAdmin&password=21218cca77804d2ba1922c33e0151105] with attributes [authenticated]
    *  添加超级管理员
    *  superAdmin
    *  21218cca77804d2ba1922c33e0151105
	*  21218cca77804d2ba1922c33e0151105
    */
   public String initSuperAdminPassword(){ 
	   String displayUserid="superAdmin";
	   String passwordJsMd5=DigestUtils.md5DigestAsHex(("888888").getBytes());
	   System.out.printf("前端MD5后的密码：", passwordJsMd5);//0c85ff2f8718ab9b151e105248b893b9
	   String passwordJavaMd5=getNewMd5Password(passwordJsMd5);
	   System.out.printf("后端加密后密码 ：", passwordJavaMd5);//$2a$10$IRNwiubbXKIahfwhZr3UuuBE0bkncMTmmYgmo.eyiFvpBzq15qs7a
	     String sql="update sys_user u set u.password='"+passwordJavaMd5+"' where u.display_userid='"+displayUserid+"'";
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