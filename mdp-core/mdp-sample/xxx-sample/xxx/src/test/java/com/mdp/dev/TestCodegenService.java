package com.mdp.dev;

import com.mdp.dev.main.CodeBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**<p>
 *1 注意:生成的源代码一般存放于 src/main/java ;<br>
 *2 生成的页面代码在 src/下面
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
 *         curd页面：src/views/${largeModuleDir}/${smallModuleName}/${tableName}/${tableName}Mng.vue<br>
 *         api文件：src/api/${largeModuleDir}/${smallModuleName}/${tableName}/edit${tableName}.js<br>
 *         mock：src/mock/${largeModuleDir}/${smallModuleName}/${tableName}/list${tableName}.js<br>
 *         mock：src/mock/${largeModuleDir}/${smallModuleName}/${tableName}/list${tableName}Mock.js<br>
     
 *  @author cyc 20150820
 *  
 ****/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestCodegenService extends CodeBase {

	/**
	 * 例子
	 */
	@Test
	public void createAll_demo(){
		// 数据库拥有者
		String dbOwner="master";

		// 后端代码存放工程
		String serviceProjectPath="D:/IdeaProjects/mdp-sys-backend/mdp-sysxxx";

		// 前端代码存放工程
		String viewProjectPath="D:/IdeaProjects/mdp-sys-ui-webxxx";

		// 生成的java代码文件存放的java包目录
		String javaPackage="com.mdp.mo";

		String pathFilter="com.";

		// 生成代码文件名需要过滤掉的前缀 比如不想mo_出现在文件名，可以填ignoePrefixs="mo_"
		String ignoePrefixs="";

		// 如果文件名已存在，是否覆盖
		boolean isForceOveride=true;

		// 是否在实体属性上打印 @TableField注解，当字段命名不规范时，设为true
		boolean printTableField=false;


		// 表名列表，可以填入任意多个表
		String[] tableNames={"sys_branch_interests"};
		//super.createAll(dbOwner, serviceProjectPath, viewProjectPath, javaPackage,pathFilter, ignoePrefixs, isForceOveride,printTableField, tableNames);
	}
}
