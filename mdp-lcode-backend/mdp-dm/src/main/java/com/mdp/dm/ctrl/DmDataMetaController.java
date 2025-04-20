package com.mdp.dm.ctrl;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.mdp.core.SpringUtils;
import com.mdp.core.api.ContextEnvService;
import com.mdp.core.entity.DmField;
import com.mdp.core.entity.DmTable;
import com.mdp.core.entity.Result;
import com.mdp.core.err.BizException;
import com.mdp.core.utils.BaseUtils;
import com.mdp.core.utils.ObjectTools;
import com.mdp.dm.service.DmDataMetaService;
import com.mdp.dm.service.DmDataSetService;
import com.mdp.dm.tools.DLType;
import com.mdp.dm.tools.DmQxUtil;
import com.mdp.dm.tools.SQLParse;
import com.mdp.dm.tools.Tpl;
import com.mdp.dm.vo.SqlTplVo;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author maimeng-mdp code-gen
 * @since 2023-9-22
 */
@RestController
@RequestMapping(value="/*/dm/meta")
@Api(tags={"查询数据库的表信息"})
public class DmDataMetaController {
	
	static Logger logger =LoggerFactory.getLogger(DmDataMetaController.class);

	String demoUserid="demo-branch-01";


	DynamicRoutingDataSource dataSourceService;

	@Autowired
	ContextEnvService contextEnvService;

	/**生成代码*/
	@Autowired
	DmDataMetaService dataMetaService;

	@Autowired
	DmDataSetService dataSetService;

	@ApiOperation( value = "查询表的列信息",notes=" ")
 	@ApiResponses({
		@ApiResponse(code = 200,response= DmField.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/table/column/list",method=RequestMethod.GET)
	public Result tableColumnInfo(@ApiIgnore @RequestParam Map<String,Object> params){

			String dataSource= (String) params.get("dataSource");
			String tableName= (String) params.get("tableName");
			if(ObjectTools.isEmpty(dataSource)){
				return Result.error("dataSource-required","数据库用户名、或者模式名、数据源名称%s不能为空","dataSource");
			}
			if(ObjectTools.isEmpty(dataSource)){
				return Result.error("tableName-required","表名不能为空");
			}
		DmQxUtil.checkTableQx(tableName,DLType.values());
			List<DmField> datas=dataMetaService.getColumnsInfo(dataSource,tableName);
			return Result.ok().setData(datas).setTotal(datas!=null?datas.size():0);
	}

	@ApiOperation( value = "查询sql类型数据集的列信息",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200,response=DmField.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/sql/column/list",method=RequestMethod.GET)
	public Result sqlColumnInfo(@ApiIgnore @RequestParam Map<String,Object> params){

		String dataSource= (String) params.get("dataSource");
		String dsSql = (String) params.get("dsSql");
		if(ObjectTools.isEmpty(dataSource)){
			return Result.error("dataSource-required","数据库用户名、或者模式名、数据源名称%s不能为空","dataSource");
		}
		if(ObjectTools.isEmpty(dsSql)){
			return Result.error("dsSql-required","sql不能为空");
		}

		SqlTplVo vo= Tpl.parse(dsSql,params,contextEnvService.getAll(),false);
		List<String> tableNames=new ArrayList<>();
		try {

			Statement statement = CCJSqlParserUtil.parse(vo.getSqlTpl());
			tableNames = SQLParse.getTableNames(statement);
 			DmQxUtil.checkDmRoles();
			for (String tableName : tableNames) {//对每个表进行权限检测
				DmQxUtil.checkTableQx(tableName, SQLParse.getDLType(statement));
			}
			List<DmField> datas = dataMetaService.getColumnsInfoBySqlTplFirstCache(dataSource, vo.getSqlTpl(),vo.getVarValues());
			return Result.ok().setData(datas).setTotal(datas != null ? datas.size() : 0);
		} catch (JSQLParserException e) {
			logger.error("sql解析错误",e);
			return  Result.error("sql-err","sql不正确。【%s】",e.getMessage());
		} catch (BizException e){
			throw e;
		}catch (Exception e){
			logger.error("sql执行错误",e);
			return Result.error("sql-err-1",e.getMessage());
		}
	}


	@ApiOperation( value = "查询表格列表",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200,response=DmField.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/table/list",method=RequestMethod.GET)
	public Result tableInfo(@ApiIgnore @RequestParam Map<String,Object> params){
		User user= LoginUtils.getCurrentUserInfo();
		String dataSource= (String) params.get("dataSource");
		String tableName= (String) params.get("tableName");
		if(ObjectTools.isEmpty(dataSource)){
			return Result.error("dataSource-required","数据库用户名、或者模式名、数据源名称%s不能为空","dataSource");
		}
		List<DmTable> datas=new ArrayList<>();
		List<DmTable> datasDb=dataMetaService.getTableList(dataSource,tableName);
		if(datasDb!=null && datasDb.size()>0){
			 datas=DmQxUtil.getHasQxTableList(datasDb,DLType.values());
		}
		return Result.ok().setTotal(datas.size()).setData(datas);
	}

	@ApiOperation( value = "查询数据源列表",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200,response=DmField.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/dataSource/list",method=RequestMethod.GET)
	public Result dsList(@ApiIgnore @RequestParam Map<String,Object> params){
		if(dataSourceService==null){
			dataSourceService= SpringUtils.getBean(DynamicRoutingDataSource.class);
		}
		Map<String, DataSource> maps=dataSourceService.getDataSources();
		return Result.ok().setData(maps.keySet().stream().map(k-> BaseUtils.map("id",k,"name",k)).collect(Collectors.toList()));
	}


}
