package com.mdp.dev.main;

import com.mdp.core.err.BizException;
import com.mdp.core.utils.ObjectTools;
import com.mdp.dev.service.CodeGenService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

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
 *         假设：${jspLargeModuleDir} = webapp/WEB-INF/pages/${companyPackage}/${topModuleName}/${largeModuleName}<br> 
 *         jsp页面代码：<br>
 *         新增：${jspLargeModuleDir}/${smallModuleName}/${tableName}/add${tableName}.jsp<br>
 *         修改：${jspLargeModuleDir}/${smallModuleName}/${tableName}/edit${tableName}.jsp<br>
 *         查询：${jspLargeModuleDir}/${smallModuleName}/${tableName}/list${tableName}.jsp<br>
 *         
 *7 大模块的spring配置文件<br>
 *         创建 spring/ctx/${topModuleName}_${largeModuleName}.xml<br>
 *         创建 spring/mvc/${topModuleName}_${largeModuleName}.xml<br>
 *        
 *  @author cyc 20150820
 *  
 ****/
public  class CodeBase {


	@Autowired
	public CodeGenService codeGenService=null;



	public List<String> createdFiles=new ArrayList<>();

	public CodeBase() {
		this.init();
	}
	public   void init(){
		  
	}

	/**
	 * 初始化参数，所有参数不区分大小写，会统一置换为小写
	 * @param serviceProjectPath 服务层代码存放位置
	 * @param viewProjectPath 页面相关的页面存放项目的根目录 如C:/Users/Administrator/workspace/cfm-web
	 * @param javaPackage java src 下的包结构 如javaPackage=com.mdp.sys ,将会在该包下创建子包${tableName},驼峰命名,代码存放在 com.mdp.sys.${tableName}的ctrl/entity/mapper/service四个目录
	 * @param pagePathFilter 根据pagePathFilter的正则表达式，去掉部分javaPackage的前缀作为页面存放目录
	 * @param owner 表所属用户,此参数用于mybatis中表名带前缀,用于过滤重复的表等.
	 * @param tableName 表名
	 * @param ignoePrefixs 在表名映射成实体名时,需要去掉的表前缀.如   sys_user去掉sys_就是user
	 * @param printTableField 是否打印@TableField 到实体的字体上，当字段最后有下横线这种不规范的命名时，设为true可解决问题
	 * @author cyc
	 * */
	public  void initMetaData(String serviceProjectPath,String viewProjectPath,String javaPackage ,String pagePathFilter,String owner, String tableName,String ignoePrefixs,boolean printTableField){
		String sourceDirectory="src/main/java";
		String resourceDirectory="src/main/resources";
		String testDirectory="src/test/java";
		codeGenService.initMetaData(serviceProjectPath,viewProjectPath,javaPackage, pagePathFilter,owner, tableName, sourceDirectory,resourceDirectory, testDirectory,ignoePrefixs,printTableField);

	}
	public  void printFileName() {
		codeGenService.printFileName();
	}

	/**
	 * 创建代码
	 * @param dbOwner 数据库owner
	 * @param serviceProjectPath 服务项目所在目录
	 * @param viewProjectPath 页面项目所在目录
	 * @param javaPackage java src 下的包结构 如javaPackage=com.mdp.sys ,将会在该包下创建子包${tableName},驼峰命名,代码存放在 com.mdp.sys.${tableName}的ctrl/entity/mapper/service四个目录
	 * @param pagePathFilter 根据pagePathFilter的正则表达式，去掉部分javaPackage的前缀作为页面存放目录
	 * @param ignoePrefixs,生成代码时，命名文件时需要忽略的前缀，多个以逗号分隔
	 * @param isForceOveride 生成文件时，如果目录下已有同名文件是否覆盖
	 * @param printTableField  是否打印@TableField到实体类中
	 * @param tableNames 依赖的表，多个以逗号分隔
	 */
	public  void createAll(String dbOwner,String serviceProjectPath,String viewProjectPath,String javaPackage,String pagePathFilter,String ignoePrefixs,boolean isForceOveride,boolean printTableField,String... tableNames){
		if(ObjectTools.isEmpty(javaPackage)){
			throw new BizException("javaPackage不能为空，该参数决定代码文件最终存放位置");
		}
		this.createdFiles=new ArrayList<>();
		for (int i = 0; i < tableNames.length; i++) {
			initMetaData(serviceProjectPath,viewProjectPath,javaPackage,pagePathFilter,dbOwner, tableNames[i],ignoePrefixs,printTableField);
			codeGenService.createAll(isForceOveride);
			printFileName();
			this.createdFiles.addAll(codeGenService.getCreatedFiles());
		}
	}

	public static void main(String[] args) {
		String javaPackage="com.org.xxx.ffff";
		String javaPackageTrimComOrOrg=javaPackage.replaceAll("^(com[.])|^(org[.])","");
		System.out.println(javaPackageTrimComOrOrg);

	}

	public CodeGenService getCodeGenService() {
		return codeGenService;
	}

	public void setCodeGenService(CodeGenService codeGenService) {
		this.codeGenService = codeGenService;
	}
	public List<String> getCreatedFiles() {
		return createdFiles;
	}

	public void setCreatedFiles(List<String> createdFiles) {
		this.createdFiles = createdFiles;
	}
}
