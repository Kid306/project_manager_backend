package com.mdp.dev.ctrl;

import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.mdp.core.entity.Result;
import com.mdp.core.err.BizException;
import com.mdp.core.utils.BaseUtils;
import com.mdp.core.utils.ObjectTools;
import com.mdp.dev.dao.CodeDevJdbcDao;
import com.mdp.dev.dao.GenDataDao;
import com.mdp.dev.entity.CodeGenVo;
import com.mdp.dev.main.CodeBase;
import com.mdp.dev.service.CodeGenService;
import com.mdp.dev.service.DevFileService;
import com.mdp.dev.service.TempleteService;
import com.mdp.dev.utils.ArcTools;
import com.mdp.dev.utils.ZipUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author maimeng-mdp code-gen
 * @since 2023-9-22
 */
@RestController
@RequestMapping(value="/*/dev/code")
@Tag(name="代码生成器-在线生成器",description = "代码生成器-在线生成器")
public class CodeGenController {
	
	static Logger logger =LoggerFactory.getLogger(CodeGenController.class);

	DynamicRoutingDataSource dataSourceService;

	/**生成代码*/
	@Autowired
	CodeDevJdbcDao codeGenDao;

	/**生成数据*/
	@Autowired
	GenDataDao genDataDao;


	@Autowired
	TempleteService templateService;

	@Autowired
	ResourceLoader resourceLoader;

	@Autowired
	DevFileService devFileService;


	@Operation( summary =  "生成代码")
 	 
	@RequestMapping(value="/gen",method=RequestMethod.GET)
	public ResponseEntity gen(CodeGenVo params,HttpServletRequest request, HttpServletResponse response) throws IOException {
		if(ObjectTools.isEmpty(params.getJavaPackage())){
			throw new BizException("javaPackage-required","javaPackage不能为空，该参数决定代码文件最终存放位置");
		}
		if(ObjectTools.isEmpty(params.getTableNames())){
			throw new BizException("tableNames-required","表名不能为空，该参数决定代码文件最终存放位置");
		}

		params.setForceOveride(true);
		String baseId=devFileService.createKey();
		String basePath=ArcTools.pathJoin(true,devFileService.getArcUploadRootPath(),baseId);
		params.setServiceProjectPath(ArcTools.pathJoin(true,basePath,"server_prj"));
		params.setViewProjectPath(ArcTools.pathJoin(true,basePath,"ui_prj"));
		CodeGenService codeGenService=new CodeGenService();
		codeGenService.setDevJdbcDao(codeGenDao);
 		codeGenService.setGenDataDao(genDataDao);
		codeGenService.setTemplateService(templateService);
		CodeBase codeBase=new CodeBase();
		codeBase.setCodeGenService(codeGenService);
		String[] tableNames=params.getTableNames().split(",");
		codeBase.createAll(params.getDbOwner(),params.getServiceProjectPath(),params.getViewProjectPath(),params.getJavaPackage(), params.getPathFilter(), params.getIgnoePrefixs(),params.getForceOveride(),params.getPrintTableField(),tableNames);
		List<String> fileList=codeBase.getCreatedFiles();
		String zipFile=basePath+".zip";
		try {
			ZipUtils.toZip(basePath,zipFile,true);
		} catch (IOException e) {
			throw e;
		}
		String fileName=params.getTableNames();
		fileName=fileName.replaceAll(",","_")+"-"+baseId+".zip";
		return download(request,response,zipFile,fileName,false);
 	}

	public ResponseEntity download(HttpServletRequest request, HttpServletResponse response, String fullPath,String fileName,Boolean preview) {

		//下载文件路径
		String servletPath=request.getServletPath();
 		if(StringUtils.isEmpty(fileName)){
			fileName=servletPath.substring(servletPath.lastIndexOf("/")+1);

		}
		Resource resource=resourceLoader.getResource("file:"+fullPath);
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException e) {
			logger.error("无法获取文件类型", e);
		}
		if (contentType == null) {
			contentType = "application/octet-stream";
		}
		if( preview){
			return ResponseEntity.ok()
					.contentType(MediaType.parseMediaType(contentType))
					.body(resource);
		}else {
			return ResponseEntity.ok()
					.contentType(MediaType.parseMediaType(contentType))
					.header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileName + "\"")
					.body(resource);
		}

	}

	@Operation( summary =  "查询数据源列表")
	 
	@RequestMapping(value="/dataSource/list",method=RequestMethod.GET)
	public Result dsList(  @RequestParam Map<String,Object> params){
		if(dataSourceService==null){
			dataSourceService= SpringUtil.getBean(DynamicRoutingDataSource.class);
		}
		Map<String, DataSource> maps=dataSourceService.getDataSources();
		return Result.ok().setData(maps.keySet().stream().map(k-> BaseUtils.map("id",k,"name",k)).collect(Collectors.toList()));
	}

}
