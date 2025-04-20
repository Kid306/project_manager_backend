package com.mdp.dev.service;

import com.mdp.core.entity.DmField;
import com.mdp.core.entity.DmTable;
import com.mdp.core.entity.LangTips;
import com.mdp.core.err.BizException;
import com.mdp.core.utils.EntityUtils;
import com.mdp.dev.dao.CodeDevJdbcDao;
import com.mdp.dev.dao.GenDataDao;
import com.mdp.dev.entity.CodeGenField;
import com.mdp.dev.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.*;

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
public class CodeGenService {


	/**生成代码*/
	@Autowired
	CodeDevJdbcDao devJdbcDao;


	/**生成数据*/
	@Autowired
	GenDataDao genDataDao;
	
	
	@Autowired
	TempleteService templateService;
	
	String rootDirectory =System.getProperty("user.dir");
	
	Map<String,Object> rootMap=new HashMap<String,Object>();
	List<String> createdFiles=new  ArrayList<String>();
	
	public List<String> getCreatedFiles() {
		 return createdFiles;
	}
	/**初始化各文件路径*/
	String largeModulePackageDirectory=null;
	/**源代码 pub dao*/
	/**源代码 large module*/
	String fullSubDaoFileName=null;
	String fullMyBatisMapperJavaFileName=null;
	String fullMyBatisMapperFileName=null;
	String fullSubEntityFileName=null;
	String fullCrudServiceFileName=null;
	String fullTradeServiceFileName=null;
	String fullCrudControllerFileName=null;
	String fullMenuSqlFileName=null;
	/**测试案例*/
	String fullTestSubDaoFileName=null;
	String fullTestCrudServiceFileName=null;
	String fullTestCrudControllerFileName=null;
	String testLageModulePackageDirectory=null;


	/**vue页面**/
	String fullEntityVueMngPage=null;
	String fullEntityVueAddPage;
	String fullEntityVueEditPage;
	String apiPath="";
	/**客户端访问服务端的api**/
	String fullViewApiFileName=null;
	/**客户端模拟数据**/
	String fullMockTestDataFileName=null;
	String fullMockFileName=null;
	boolean printTableField=false;
	
	public Logger logger = LoggerFactory.getLogger(CodeGenService.class);


	public void setDevJdbcDao(CodeDevJdbcDao devJdbcDao) {
		this.devJdbcDao = devJdbcDao;
	}
	public GenDataDao getGenDataDao() {
		return genDataDao;
	}

	public void setGenDataDao(GenDataDao genDataDao) {
		this.genDataDao = genDataDao;
	}

	public TempleteService getTemplateService() {
		return templateService;
	}

	public void setTemplateService(TempleteService templateService) {
		this.templateService = templateService;
	}
	
	/**
	 * 初始化参数，所有参数不区分大小写，会统一置换为小写
	 * @param serviceRootPath
	 * @param viewRootPath
	 * @param javaPackage 顶级模块包名,如mdp,一般根据公司目前的所属顶级产品进行命名 顶级模块下一般包含若干个大模块，如果没有顶级产品，可以为空
	 * @param pagePathFilter
	 * @param owner 表所属用户,此参数用于mybatis中表名带前缀,用于过滤重复的表等.
	 * @param tableName 表名
	 * @param ignorePrefix 在表名映射成实体名时,需要去掉的表前缀.如   sys_user去掉sys_就是user
	 * @param sourceDirectory gen或者src/main/java
	 * @param resourceDirectory src/main/resources
	 * @param sourceDirectory gen或者src/test/java 
	 * 
	 * @author cyc
	 * */
	public void initMetaData(String serviceRootPath,String viewRootPath,String javaPackage,String pagePathFilter,String owner,String tableName,String sourceDirectory,String resourceDirectory,String testDirectory,String ignorePrefix,boolean printTableField) {
		this.printTableField=printTableField;
		serviceRootPath=StringUtils.isEmpty(serviceRootPath)?rootDirectory:serviceRootPath;
		viewRootPath=StringUtils.isEmpty(viewRootPath)?rootDirectory:viewRootPath; 
		String viewPath=javaPackage.replaceAll(pagePathFilter,"").replaceAll("[.]","/");
		tableName=tableName.toLowerCase();//如 sys_user
		
		/**组装几个关键路径 1.基础包路径 一般为 com.公司.顶级模块 ; 2 公共包路径 一般为 基础包路径.pub; 3 模块包路径 一般为 基础包路径.子模块 */
		
		String tempTableName=tableName;
		//去掉前缀如sys_user去掉 sys_后变成 user
		if(StringUtils.hasText(ignorePrefix)  ){
			String[] igs=ignorePrefix.split(",");
			for (int i = 0; i < igs.length; i++) {
				if(tableName.startsWith(igs[i].toLowerCase(Locale.ROOT))){
					tempTableName=tempTableName.substring(igs[i].length());
				}
			}
		}
		//首字母小写的驼峰命名，如 user_role --> userRole
		String smallEntityName=JdbcUtils.convertUnderscoreNameToPropertyName(tempTableName);
		String h5SmallEntityName=tempTableName.toLowerCase().replaceAll("_", "-");
		//首字母大写的驼峰命名，如 user_role --> UserRole
		String entityName=smallEntityName.substring(0, 1).toUpperCase()+smallEntityName.substring(1);
		
		/**获取表的元数据信息*/
		List<String> primaryKeysListString = devJdbcDao.getPrimaryKeys(owner,tableName);//主键名称列表，多主键
		DmTable tableInfos= devJdbcDao.getTable(owner,tableName);
		if(tableInfos==null){
			throw new BizException(LangTips.errMsg("tableName-not-exists","表格%s不存在",tableName));
		}
		List<DmField> fields = devJdbcDao.getColumnsInfo(owner,tableName);//表对应的所有列信息
		DmTable tableInfo=tableInfos;
		String tableRemarks=EntityUtils.parseFieldName(tableInfo.getTableRemarks());
		List<CodeGenField> columnList=new ArrayList<>();
		for (DmField fieldInfo : fields) {
			CodeGenField cgf=CodeGenField.fromTableField(fieldInfo);
			cgf.setTableRemarks(tableRemarks);
			columnList.add(cgf);
		}
		List<CodeGenField> primaryKeysList=new ArrayList<CodeGenField>();//主键对应的列信息
		List<CodeGenField> columnExcludePkList=new ArrayList<CodeGenField>();//表对应的所有列信息,不保护主键列
		getTableColumnsExcludePkAndNewPk(columnList,primaryKeysListString,primaryKeysList,columnExcludePkList);
		
		/**初始化各文件路径*/
		this.createdFiles.clear();
		 String largeModulePackage=javaPackage;
 		 String largeModulePath=largeModulePackage.replaceAll("[.]", "/");
		 largeModulePackageDirectory=serviceRootPath+"/"+sourceDirectory+"/"+largeModulePath;
		 testLageModulePackageDirectory=serviceRootPath+"/"+testDirectory+"/"+largeModulePath;

		String pkAlias="pkList"; 

		/**源代码 large module*/
		 fullMyBatisMapperJavaFileName=largeModulePackageDirectory+"/mapper/"+entityName+"Mapper.java";
		 fullMyBatisMapperJavaFileName=fullMyBatisMapperJavaFileName.replaceAll("//", "/");
		 fullMyBatisMapperFileName=largeModulePackageDirectory+"/mapper/"+entityName+"Mapper.xml";
		 fullMyBatisMapperFileName=fullMyBatisMapperFileName.replaceAll("//", "/");
		 fullSubEntityFileName=largeModulePackageDirectory+"/entity/"+entityName+".java";
		 fullSubEntityFileName=fullSubEntityFileName.replaceAll("//", "/");
		 fullCrudServiceFileName=largeModulePackageDirectory+"/service/"+entityName+"Service.java";
		 fullCrudServiceFileName=fullCrudServiceFileName.replaceAll("//", "/");
		 fullCrudControllerFileName=largeModulePackageDirectory+"/ctrl/"+entityName+"Controller.java";
		 fullCrudControllerFileName=fullCrudControllerFileName.replaceAll("//", "/");
		/**测试案例*/
		fullTestCrudServiceFileName=testLageModulePackageDirectory+"/service/"+"Test"+entityName+"Service.java";
		fullTestCrudServiceFileName=fullTestCrudServiceFileName.replaceAll("//", "/");
		fullTestCrudControllerFileName=testLageModulePackageDirectory+"/ctrl/"+"Test"+entityName+"Controller.java";
		fullTestCrudControllerFileName=fullTestCrudControllerFileName.replaceAll("//", "/");
		/**jsp页面目录**/
		
		  String vuePathTemp="views/"+viewPath+"/"+smallEntityName;
		  vuePathTemp=vuePathTemp.replaceAll("//", "/");
		 fullEntityVueMngPage=viewRootPath+"/src/"+vuePathTemp+"/Index.vue";
		 fullEntityVueAddPage=viewRootPath+"/src/"+vuePathTemp+"/Add.vue";
		 fullEntityVueEditPage=viewRootPath+"/src/"+vuePathTemp+"/Form.vue";
		 fullEntityVueMngPage=fullEntityVueMngPage.replaceAll("//", "/");
		 fullEntityVueAddPage=fullEntityVueAddPage.replaceAll("//", "/");
		 fullEntityVueEditPage=fullEntityVueEditPage.replaceAll("//", "/");
		 fullViewApiFileName=viewRootPath+"/src/api/"+viewPath+"/"+smallEntityName+".js";
		 fullViewApiFileName=fullViewApiFileName.replaceAll("//", "/");
		 
		 fullMockTestDataFileName=viewRootPath+"/src/mock/"+viewPath+"/"+smallEntityName+".js";
		 fullMockTestDataFileName=fullMockTestDataFileName.replaceAll("//", "/"); 
		 fullMockFileName=viewRootPath+"/src/mock/"+viewPath+"/"+smallEntityName+"Mock.js";
		 fullMockFileName=fullMockFileName.replaceAll("//", "/");
		 apiPath=viewPath;

		apiPath=apiPath.replaceAll("//", "/");
		 /**创建目录*/
		createDir(largeModulePackageDirectory);
		
		/**测试数据生成*/
		List<List<CodeGenField>> tableValue=genDataDao.createTableWithTestValue(7, columnList);
		String javaPackageTrimComOrOrg=javaPackage.replaceAll("^(com[.])|^(org[.])","");
		String[] pkgs=javaPackageTrimComOrOrg.split("[.]");
		String contextName=pkgs[0];

		/**把数据放入容器中 供ftl 模板使用*/
		String baseRequestUri="";
		rootMap.put("pkAlias",pkAlias);
		rootMap.put("owner", owner);
		rootMap.put("bigContextName", contextName.substring(0,1).toUpperCase(Locale.ROOT)+contextName.substring(1));
 		rootMap.put("baseRequestUri", baseRequestUri.replaceAll("[.]","/"));
		if(owner==null){
			owner="";
		}
		rootMap.put("largeModulePackage", largeModulePackage);
		rootMap.put("tableName", tableName);
		rootMap.put("tableRemarks", tableRemarks);
		rootMap.put("entityName",entityName);
		rootMap.put("smallEntityName",smallEntityName);
		rootMap.put("h5SmallEntityName",h5SmallEntityName);
		rootMap.put("apiPath",apiPath);

		rootMap.put("columnList", columnList);
		rootMap.put("primaryKeysList", primaryKeysList);
		rootMap.put("columnExcludePkList", columnExcludePkList);
		rootMap.put("importClassList", getImportClass(columnList));
 		rootMap.put("tableValue", tableValue);
		rootMap.put("printTableField", printTableField);
	}

	/**获取表的除了主键外的所有字段信息**/
	public void getTableColumnsExcludePkAndNewPk(List<CodeGenField> allColumnsList, List<String> pkList, List<CodeGenField> newPk, List<CodeGenField> columnsExcludePk){
		for (int i = 0; i < allColumnsList.size(); i++) {
			CodeGenField tn=allColumnsList.get(i);
			if(!pkList.contains(tn.getColumnName())){
				columnsExcludePk.add(tn);

			}else{
				tn.setPk(true);
				newPk.add(tn);
			}
		}

	}

	/**获取字段中需要在生成的java文件中引入的类名集合**/
	public List<CodeGenField> getImportClass(List<CodeGenField> allColumnsList){
		ArrayList<CodeGenField> importClassList=new ArrayList<CodeGenField>();
		Map<String, CodeGenField> m=new HashMap<String, CodeGenField>();
		for (int i = 0; i < allColumnsList.size(); i++) {
			CodeGenField tn=allColumnsList.get(i);
			if(tn.getFullJavaClassName().contains(".") && !tn.getFullJavaClassName().contains("java.lang") && !m.containsKey(tn.getFullJavaClassName())){
				importClassList.add(tn);
				m.put(tn.getFullJavaClassName(), tn);
			}
		}
		return importClassList;

	}

	// 生成entity层代码
	public void createSubEntity(boolean isForceOveride) {
		createDir(fullSubEntityFileName);
		toFile(fullSubEntityFileName, "dev/subEntity.ftl",isForceOveride);

	}

	// 生成dao层代码,dao和entity放在同一个包下
	public void createSubDao(boolean isForceOveride) {
		createDir(fullSubDaoFileName);
		toFile(fullSubDaoFileName, "dev/subDao.ftl",isForceOveride);
	}
	// 生成MyBatisMapper.xml文件
	public void createMyBatisMapperFile(boolean isForceOveride) {
		createDir(fullMyBatisMapperJavaFileName);
		toFile(fullMyBatisMapperJavaFileName, "dev/myBatisMapperJava.ftl",isForceOveride);
		createDir(fullMyBatisMapperFileName);
		toFile(fullMyBatisMapperFileName, "dev/myBatisMapper.ftl",isForceOveride);
	}
	
	// 生成菜单sql文件
	public void createMenuSqlFile(boolean isForceOveride) {
		createDir(fullMenuSqlFileName);
		toFile(fullMenuSqlFileName, "dev/menuSql.ftl",isForceOveride);
	}
	
	// 生成service层代码
	public void createTradeService(boolean isForceOveride) {
		createDir(fullTradeServiceFileName);
		toFile(fullTradeServiceFileName, "dev/tradeService.ftl",isForceOveride);
	}

	// 生成crudService层代码
	public void createCrudService(boolean isForceOveride) {
		createDir(fullCrudServiceFileName);
		toFile(fullCrudServiceFileName, "dev/crudService.ftl",isForceOveride);
	}

	// 生成control层
	public void createCrudController(boolean isForceOveride) {
		createDir(fullCrudControllerFileName);
		toFile(fullCrudControllerFileName,"dev/crudController.ftl",isForceOveride);
		
	}

	// 生成CrudService层的单元测试代码
	public void createTestCrudService(boolean isForceOveride) {
		createDir(fullTestCrudServiceFileName);
		toFile(fullTestCrudServiceFileName,"dev/testCrudService.ftl",isForceOveride);
	}

	
	// 生成SubDao层的单元测试代码
	public void createTestSubDao(boolean isForceOveride) {
		createDir(fullTestSubDaoFileName);
		toFile(fullTestSubDaoFileName, "dev/testSubDao.ftl",isForceOveride);
	}
	// 生成ctrl层的单元测试代码
	public void createTestCrudController(boolean isForceOveride) {
		
		createDir(fullTestCrudControllerFileName);
		toFile(fullTestCrudControllerFileName, "dev/testCrudController.ftl",isForceOveride);
		
	}
	 
	 
	/**创建 EntityMng.vue**/
	public void createEntityListPage(boolean isForceOveride) {
		
		createDir(fullEntityVueMngPage);
		toFile(fullEntityVueMngPage, "dev/vueMng.ftl",isForceOveride);
		//toFile(fullEntityVueAddPage, "dev/vueAdd.ftl",isForceOveride);
		toFile(fullEntityVueEditPage, "dev/vueForm.ftl",isForceOveride);
		
	}
	
	/**创建 api/${entityName}.js**/
	public void createViewApi(boolean isForceOveride) {
		
		createDir(fullViewApiFileName);
		toFile(fullViewApiFileName, "dev/viewApi.ftl",isForceOveride);
		
	} 
	/**创建 mock/data/${entityName}.js**/
	public void createMockData(boolean isForceOveride) {
		
		

		try{
			//createDir(fullMockTestDataFileName);
			//toFile(fullMockTestDataFileName, "dev/mockData.ftl",isForceOveride);
			//createDir(fullMockFileName);
			//toFile(fullMockFileName, "dev/mock.ftl",isForceOveride);
			
		}catch (Exception e) {
			logger.error("",e);
		}
		
	} 
	
	public void createDir(String dir){
		File f=new File(dir);
		if(f.isDirectory()){
			if(!f.exists()){
				f.mkdirs();
			}
		}else{
			File dir2=new File(f.getParent());
			if(!dir2.exists()){
				dir2.mkdirs();
			}
		}
		
	}
	/**
	 * 是否应该执行生成代码判断,如果已经存在代码文件并且强制覆盖,则生成;如果不存在代码文件,则生成;否则不生成
	 * @param fileName 目标文件存放位置,绝对路径
	 * @param ftlFile 模板文件 不带路径
	 * @param isForceOveride
	 * @return
	 */
	public void toFile(String fileName,String ftlFile,boolean isForceOveride){
		File f=new File(fileName);
		if(!f.exists()||isForceOveride){
			templateService.toFile(fileName, ftlFile,rootMap);
			this.createdFiles.add(fileName);
		}
		
	}
	/**
	 * 创建功能入口菜单
	 */
	public void createMenu(){
		String largeModule=(String)rootMap.get("largeModule");
		String menuid=(String)rootMap.get("smallEntityName")+DateUtils.getDate("yyyyMMddHHmmss");
		String subModulePathExt=(String) rootMap.get("subModulePathExt");
		String entityName=(String)rootMap.get("entityName");
		String url=largeModule+subModulePathExt+"/"+(String)rootMap.get("smallEntityName")+"/list"+entityName;
		String menuName=(String)rootMap.get("tableRemarks");
		String sql= devJdbcDao.createMenu(menuid,menuName, url, "80");
		rootMap.put("menuSql", sql);
	}
	
	public  void printFileName() {
		List<String> files=this.getCreatedFiles();
		System.out.println("start 打印生成的文件-------------------------------");
		for (int i = 0; i < files.size(); i++) {
			System.out.println(files.get(i));
		}
		System.out.println("end   打印生成的文件-------------------------------");
		
	}
	
	/**
	 * 创建所有文件并打印
	 * @param isForceOveride 是否在本地存在文件情况下覆盖
	 * 创建 ${Entity}Mng.vue文件 
	 * 创建 ${Entity}Controller.java文件
	 * 创建 Test${Entity}Controller.java文件
	 * 创建 ${Entity}Service.java文件
	 * 创建 Test${Entity}Service.java文件
	 * 创建 ${Entity}.java文件
	 * 创建 ${Entity}Mapper.xml文件
	 * 创建 Test${Entity}Dao.java文件
	 * 创建 ${Entity}_datetime.sql文件
	 */
	public void createAll(boolean isForceOveride){
			this.createByLevel(isForceOveride, true, true, true, true);
		}
	
	
	
	/**
	 * 创建所有文件并打印
	 * @param isForceOveride 是否在本地存在文件情况下覆盖
	 * 创建 list${Entity}.html文件
	 * 创建 edit${Entity}.html文件
	 * 创建 add${Entity}.html文件
	 * 创建 ${Entity}Controller.java文件
	 * 创建 Test${Entity}Controller.java文件
	 * 创建 ${Entity}Service.java文件
	 * 创建 Test${Entity}Service.java文件
	 * 创建 ${Entity}.java文件
	 * 创建 ${Entity}Mapper.xml文件
	 * 创建 Test${Entity}Dao.java文件
	 * 创建 ${Entity}_datetime.sql文件
	 * 创建 spring/ctx/${topModuleName}_${largeModuleName}.xml
	 * 创建 spring/mvc/${topModuleName}_${largeModuleName}.xml
	 */
	public void createByLevel(boolean isForceOveride,boolean isPage,boolean isController,boolean isService,boolean isDao){
			if(isPage){
				this.createEntityListPage(isForceOveride);//查询页面 
				this.createViewApi(isForceOveride);
				this.createMockData(isForceOveride);
				//this.createMenu();
				//this.createMenuSqlFile(isForceOveride);
			}
			if(isController){
				this.createCrudController(isForceOveride);//控制层类
				//this.createTestCrudController(false);	//测试案例(服务)
			}
			if(isService){
				this.createCrudService(isForceOveride);//服务类
				this.createTestCrudService(isForceOveride);	//测试案例(服务)
			}
			if(isDao){
				this.createSubEntity(true);//私有域
				this.createMyBatisMapperFile(isForceOveride);//myBatisMapper.xml文件
				//this.createTestSubDao(false);//测试案例(私有dao)
			}
			/**
			if(isSpringConfig){
				this.createSpringModule(false);
			}
			**/
			
		}
	
	
	
	
	

}