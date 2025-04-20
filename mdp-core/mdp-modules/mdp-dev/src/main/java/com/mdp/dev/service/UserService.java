package com.mdp.dev.service;

import com.mdp.dev.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**<p>
 *1 注意:生成的源代码一般存放于 src/main/java ;<br>
 *3 也可以通过配置 sourceDirectory,testDirectory两个参数设置代码生成存放的目录.<br>
 *4 源代码文件列表:  	<br>
 * 		   假设：${largeModuleDir} = ${companyPackage}/${topModuleName}/${largeModuleName}<br>
 *         源代码:<br>
 *         控制层代码：${largeModuleDir}/ctrl/${smallModuleName}/${tableName}Controller.java<br>
 *         服务层代码：${largeModuleDir}/service/${smallModuleName}/${tableName}Service.java<br>
 *         数据访问码：${largeModuleDir}/dao/${smallModuleName}/${tableName}Mapper.xml<br>
 *         实体类代码：${largeModuleDir}/dao/${smallModuleName}/entity/${tableName}.java<br>
 *         <br>
 *5 测试案例代码文件列表: 	<br>
 * 		   假设：${testLargeModuleDir} = ${companyPackage}/${topModuleName}/${largeModuleName}<br>    
 *         单元测试代码:<br>
 *         控制层代码：${testLargeModuleDir}/ctrl/${smallModuleName}/Test${tableName}Controller.java<br>
 *         服务层代码：${testLargeModuleDir}/service/${smallModuleName}/Test${tableName}Service.java<br>
 *         数据访问层：${testLargeModuleDir}/dao/${smallModuleName}/Test${tableName}Dao.java<br>
 *6 页面文件列表：<br>
 *         假设：${pagesLargeModuleDir} = classpath:templates/pages/${companyPackage}/${topModuleName}/${largeModuleName}<br> 
 *         页面代码：<br> 
 *         crud：${pagesLargeModuleDir}/${smallModuleName}/${tableName}/${tableName}Mng.vue<br>
 *         
 *  @author cyc 20150820
 *  
 ****/
@Service
public class UserService {
	@Autowired
    UserDao userDao;
	
	public void initSuperAdminPassword(){
		userDao.initSuperAdminPassword();
	}
	
}