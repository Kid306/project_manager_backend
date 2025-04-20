package com.mdp.dm.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.api.ContextEnvService;
import com.mdp.core.entity.DmField;
import com.mdp.core.entity.LangTips;
import com.mdp.core.entity.Result;
import com.mdp.core.query.QueryTools;
import com.mdp.core.utils.ObjectTools;
import com.mdp.core.valid.ValidUtil;
import com.mdp.dm.entity.DmDataSet;
import com.mdp.dm.service.DmDataMetaService;
import com.mdp.dm.service.DmDataSetService;
import com.mdp.dm.service.DmTableDataService;
import com.mdp.dm.service.DmWhereService;
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
import net.sf.jsqlparser.statement.select.PlainSelect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author maimeng-mdp code-gen
 * @since 2024-4-25
 */
@RestController
@RequestMapping(value="/*/dm/data")
@Api(tags={"数据集-操作接口"})
public class DmTableDataController {
	
	static Logger logger =LoggerFactory.getLogger(DmTableDataController.class);
	
	@Autowired
	private DmTableDataService tableDataService;
	
	@Autowired
	DmDataMetaService dataMetaService;


	@Autowired
	DmDataSetService dataSetService;

	@Autowired
	ContextEnvService contextEnvService;



	public void baseCheck(String dataSource,String tableName){
		ValidUtil.isRequired("dataSource","数据源",dataSource);
		ValidUtil.isRequired("tableName","表名",tableName);
		ValidUtil.isLetterNumberLine("tableName","表名",tableName);;
	}


	@ApiOperation( value = "执行数据集数据查询，数据集编号",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200,response=Result.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/listByDataSetId",method=RequestMethod.GET)
	public Result listByDataSetId(@ApiIgnore @RequestParam Map<String,Object> params){
		User user=LoginUtils.getCurrentUserInfo();
		String id= (String) params.get("dataSetId");
		ValidUtil.isRequired("dataSetId","数据集编号",id);
		DmDataSet dataSet=dataSetService.getDataSetFromCacheFirst(id);
		if(dataSet==null){
			return Result.error("data-0","数据不存在");
		}
		SqlTplVo vo= Tpl.parse(dataSet.getDsSql(),params,contextEnvService.getAll(),false);
		List<String> tableNames=new ArrayList<>();
		try {

			Statement statement = CCJSqlParserUtil.parse(vo.getSqlTpl());
			tableNames=SQLParse.getTableNames(statement);
			PlainSelect plainSelect=(PlainSelect) SQLParse.getSelect(statement).getSelectBody();
 			DmQxUtil.checkDmRoles();
			for (String tableName : tableNames) {//对每个表进行权限检测
				DmQxUtil.checkTableQx(tableName,SQLParse.getDLType(statement));
			}

			SQLParse.injectWhere(plainSelect,new DmWhereService(dataMetaService,dataSet.getDataSource(), user));

		List<DmField> dmFields=dataMetaService.getColumnsInfoBySqlTplFirstCache(dataSet.getDataSource(),vo.getSqlTpl(),vo.getVarValues());
		QueryWrapper qw = QueryTools.initDmQw(dmFields,params);
		IPage page=QueryTools.initPage(params);
		String sql=plainSelect.toString();
		sql=Tpl.setSqlValues(sql,vo.getVarValues());
		List<Map<String,Object>> datas = tableDataService.listBySql(dataSet.getDataSource(),sql,page,qw,params);
		return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());
		} catch (JSQLParserException e) {
			logger.error("sql解析错误",e);
			return  Result.error("sql-err","sql不正确。【%s】",e.getMessage());
		} catch (Exception e){
			logger.error("sql执行错误",e);
			return Result.error("sql-err-1",e.getMessage());
		}
	}

	@ApiOperation( value = "数据集数据预览，上送数据源，查询sql",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200,response=Result.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/previewSqlData",method=RequestMethod.GET)
	public Result previewSqlData(@ApiIgnore @RequestParam Map<String,Object> params){
		User user=LoginUtils.getCurrentUserInfo();
		String dataSource= (String) params.get("dataSource");
		String dsSql= (String) params.get("dsSql");
		if(ObjectTools.isEmpty(dsSql)){
			return Result.error("dsSql-0","sql不能为空");
		}
		params.remove("tableName");
		dsSql=Tpl.trimSql(dsSql);
		SqlTplVo vo= Tpl.parse(dsSql,params,contextEnvService.getAll(),false);
		List<String> tableNames=new ArrayList<>();
        try {

            Statement statement = CCJSqlParserUtil.parse(vo.getSqlTpl());
			tableNames=SQLParse.getTableNames(statement);
			PlainSelect plainSelect=(PlainSelect) SQLParse.getSelect(statement).getSelectBody();
			DmQxUtil.checkDmRoles();
			for (String tableName : tableNames) {//对每个表进行权限检测
				DmQxUtil.checkTableQx(tableName,SQLParse.getDLType(statement));
			}
			SQLParse.injectWhere(plainSelect,new DmWhereService(dataMetaService,dataSource, user));
        List<DmField> dmFields=dataMetaService.getColumnsInfoBySqlTpl(dataSource,vo.getSqlTpl(),vo.getVarValues());
			QueryWrapper qw = QueryTools.initDmQw(dmFields,params);
			IPage page=QueryTools.initPage(params);
			String sql=plainSelect.toString();
			sql=Tpl.setSqlValues(sql,vo.getVarValues());
 		List<Map<String,Object>> datas = tableDataService.listBySql(dataSource,sql,page,qw,params);
		return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());
		} catch (JSQLParserException e) {
			logger.error("sql解析错误",e);
			return  Result.error("sql-err","sql不正确。【%s】",e.getMessage());
		} catch (Exception e){
			logger.error("sql执行错误",e);
			return Result.error("sql-err-1",e.getMessage());
		}
	}

	@ApiOperation( value = "数据集-查询列表",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200,response=Result.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/queryById",method=RequestMethod.GET)
	public Result queryById(@ApiIgnore @RequestParam Map<String,Object> params){
		User user=LoginUtils.getCurrentUserInfo();
		String dataSource= (String) params.get("dataSource");
		String tableName= (String) params.get("tableName");
		params.remove("dsSql");
		baseCheck(dataSource,tableName);
		DmQxUtil.checkTableQx(tableName, DLType.SELECT);
		List<DmField> dmFields=dataMetaService.getColumnsInfoFirstCache(dataSource,tableName);
		List<DmField> pkFields=dmFields.stream().filter(k->k.isPk).collect(Collectors.toList());
		if(pkFields.size()>1){
			for (DmField pkField : pkFields) {
				String pkName=ObjectTools.camelName(pkField.getColumnName());
				Object pkValue=params.get(pkName);
				if(ObjectTools.isEmpty(pkValue)){
					return Result.error("pks-required","主键【%s】的值不能为空",pkFields.stream().map(k->ObjectTools.camelName(k.getColumnName())).collect(Collectors.joining(",")));
				}
			}
		}else if(pkFields.size()==1){
			DmField pkField=pkFields.get(0);
			String pkName=ObjectTools.camelName(pkField.getColumnName());
			Object pkValue=params.get(pkName);
			if(ObjectTools.isEmpty(pkValue)){
				return Result.error("pk-required","主键【%s】的值不能为空",pkName);
			}
		}else {
			return Result.error("pk-field-required","表格【%s】未设置主键，无法精确查询数据。",tableName);
		}
		DmQxUtil.checkDmRoles();
 		Map<String,Object> data = tableDataService.getById(dataSource,tableName,params);
		return Result.ok("query-ok","查询成功").setData(data).setTotal(data==null||data.isEmpty()?0:1);
	}
	@ApiOperation( value = "数据集-查询列表",notes=" ") 
	@ApiResponses({
		@ApiResponse(code = 200,response=Result.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listTableData(@ApiIgnore @RequestParam Map<String,Object> params){
			User user=LoginUtils.getCurrentUserInfo();
			String dataSource= (String) params.get("dataSource");
			String tableName= (String) params.get("tableName");

		params.remove("dsSql");
		baseCheck(dataSource,tableName);
		DmQxUtil.checkTableQx(tableName, DLType.SELECT);
			List<DmField> dmFields=dataMetaService.getColumnsInfoFirstCache(dataSource,tableName);
		DmField bfield=DmQxUtil.getBranchField(dmFields);
		if(bfield!=null){
			params.put(ObjectTools.camelName(bfield.getColumnName()),user.getBranchId());
		}else {
			 DmQxUtil.checkDmRoles();
			//return Result.error("table-no-branch-field","表【%s】没有明确的企业编号字段,企业编号字段匹配规则为【%s】，不允许前端添加数据",tableName,DmQxUtil.branchIdRegex);
		}
			QueryWrapper qw = QueryTools.initDmQw(dmFields,params);
			DmQxUtil.addBranchQx(qw,dmFields);
			IPage page=QueryTools.initPage(params);
			List<Map<String,Object>> datas = tableDataService.list(dataSource,tableName,page,qw,params);
			return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());
	}
	

	@ApiOperation( value = "数据集-新增",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=Result.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addTableData(@RequestBody Map<String,Object> params) {
		User user=LoginUtils.getCurrentUserInfo();
		String dataSource= (String) params.get("dataSource");
		String tableName= (String) params.get("tableName");
		baseCheck(dataSource,tableName);

		DmQxUtil.checkTableQx(tableName, DLType.INSERT);
		List<DmField> dmFields=dataMetaService.getColumnsInfoFirstCache(dataSource,tableName);
		DmField bfield=DmQxUtil.getBranchField(dmFields);
		if(bfield!=null){
			params.put(ObjectTools.camelName(bfield.getColumnName()),user.getBranchId());
		}else {
			return Result.error("table-no-branch-field","表【%s】没有明确的企业编号字段,企业编号字段匹配规则为【%s】，不允许前端添加数据",tableName,DmQxUtil.branchIdRegex);
		}
		Map<String,Object> dataDb=tableDataService.getById(dataSource,tableName,params);
		if(dataDb!=null && !dataDb.isEmpty()){
			return Result.error("data-exists","数据已存在，请不要重复录入");
		}
		tableDataService.save(dataSource,tableName,dmFields,params,true);
         return Result.ok("add-ok","添加成功！");
	}

	@ApiOperation( value = "数据集-删除",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delTableData(@RequestBody Map<String,Object> params){
		User user=LoginUtils.getCurrentUserInfo();
		String dataSource= (String) params.get("dataSource");
		String tableName= (String) params.get("tableName");
		baseCheck(dataSource,tableName);
		DmQxUtil.checkTableQx(tableName, DLType.DELETE);
		List<DmField> dmFields=dataMetaService.getColumnsInfoFirstCache(dataSource,tableName);
		Map<String,Object> rowDb=tableDataService.getById(dataSource,tableName,params);
		if(rowDb==null || rowDb.isEmpty()){
			return Result.error("data-0","数据不存在");
		}
		Object branchV=DmQxUtil.getBranchValue(dmFields,params);
		if(branchV==null || !user.getBranchId().equalsIgnoreCase((String) branchV)){
			return Result.error("data-qx-branch-err-del","该数据不属于您的企业数据，不允许删除");
		}
		tableDataService.removeById(dataSource,tableName,params);
        return Result.ok("del-ok","删除成功！");
	}

	@ApiOperation( value = "数据集-修改",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=Result.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editTableData(@RequestBody Map<String,Object> params) {
		User user=LoginUtils.getCurrentUserInfo();
		String dataSource= (String) params.get("dataSource");
		String tableName= (String) params.get("tableName");
		baseCheck(dataSource,tableName);
		DmQxUtil.checkTableQx(tableName, DLType.UPDATE);
		List<DmField> dmFields=dataMetaService.getColumnsInfoFirstCache(dataSource,tableName);

 		Map<String,Object> rowDb=tableDataService.getById(dataSource,tableName,params);
		if(rowDb==null || rowDb.isEmpty()){
			return Result.error("data-0","数据不存在");
		}
		Object branchV=DmQxUtil.getBranchValue(dmFields,params);
		if(branchV==null || !user.getBranchId().equalsIgnoreCase((String) branchV)){
			return Result.error("data-qx-branch-err-edit","该数据不属于您的企业数据，不允许修改");
		}
		tableDataService.updateById(dataSource,tableName,dmFields,params,true);
        return Result.ok("edit-ok","修改成功！");
	}

    @ApiOperation( value = "数据集-批量修改某些字段",notes="")
 	@ApiResponses({
			@ApiResponse(code = 200,response=Result.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields( @ApiIgnore @RequestBody Map<String,Object> params) {
            User user= LoginUtils.getCurrentUserInfo();
		String dataSource= (String) params.get("dataSource");
		String tableName= (String) params.get("tableName");
		baseCheck(dataSource,tableName);
		DmQxUtil.checkTableQx(tableName, DLType.UPDATE);
		List<DmField> dmFields=dataMetaService.getColumnsInfoFirstCache(dataSource,tableName);
		List<Object> pks= (List<Object>) params.get("$pks");
		List<Map<String,Object>> datas=tableDataService.listByIds(dataSource,tableName,pks);
		if(datas==null ||datas.size()==0){
			return Result.error("data-0","数据不存在");
		}
		for (Map<String, Object> data : datas) {
			Object branchV=DmQxUtil.getBranchValue(dmFields,data);
			if(branchV==null || !user.getBranchId().equalsIgnoreCase((String) branchV)){
				return Result.error("data-qx-branch-err-edit","该数据不属于您的企业数据，不允许修改");
			}
		}
		tableDataService.editSomeFields(dataSource,tableName,params);
            return Result.ok("edit-ok","更新成功");
	}

	@ApiOperation( value = "数据集-批量删除",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelTableData(@RequestBody List<Map<String,Object>> tableDatas) {
	    User user= LoginUtils.getCurrentUserInfo();
		Map<String,Object> params=tableDatas.get(0);
		String dataSource= (String) params.get("dataSource");
		String tableName= (String) params.get("tableName");
		baseCheck(dataSource,tableName);

		DmQxUtil.checkTableQx(tableName, DLType.DELETE);
		List<DmField> dmFields=dataMetaService.getColumnsInfoFirstCache(dataSource,tableName);

		if(tableDatas.size()<=0){
            return Result.error("batchDel-data-err-0","请上送待删除数据列表");
        }
         List<Map<String,Object>> datasDb=tableDataService.listByIds(dataSource,tableName,tableDatas);

        List<Map<String,Object>> can=new ArrayList<>();
        List<Map<String,Object>> no=new ArrayList<>();
        for (Map<String,Object> data : datasDb) {
			Object branchV=DmQxUtil.getBranchValue(dmFields,data);
			if(branchV==null || !user.getBranchId().equalsIgnoreCase((String) branchV)){
				return Result.error("data-qx-branch-err-del","该数据不属于您的企业数据，不允许删除");
			}
            if(true){
                can.add(data);
            }else{
                no.add(data);
            }
        }
        List<String> msgs=new ArrayList<>();
        if(can.size()>0){
            tableDataService.removeByIds(dataSource,tableName,can);
            msgs.add(LangTips.transMsg("del-ok-num","成功删除%s条数据.",can.size()));
        }

        if(no.size()>0){
            msgs.add(LangTips.transMsg("not-allow-del-num","有%s条数据不能删除:【%s】",no.size()));
        }
        if(can.size()>0){
             return Result.ok(msgs.stream().collect(Collectors.joining()));
        }else {
            return Result.error(msgs.stream().collect(Collectors.joining()));
        }
	} 
 

}
