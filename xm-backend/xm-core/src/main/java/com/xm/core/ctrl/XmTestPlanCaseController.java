package com.xm.core.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.entity.Tips;
import com.mdp.core.query.QueryTools;
import com.mdp.core.utils.DateUtils;
import com.mdp.core.utils.NumberUtil;
import com.mdp.core.utils.RequestUtils;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.swagger.ApiEntityParams;
import com.xm.core.entity.*;
import com.xm.core.service.*;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static com.mdp.core.utils.BaseUtils.*;

/**
 * url编制采用rest风格,如对xm_test_plan_case 测试计划与用例关系表的操作有增删改查,对应的url分别为:<br>
 * 组织 com  顶级模块 xm 大模块 core 小模块 <br>
 * 实体 XmTestPlanCase 表 xm_test_plan_case 当前主键(包括多主键): case_id,plan_id; 
 ***/
@RestController("xm.core.xmTestPlanCaseController")
@RequestMapping(value="/xm/core/xmTestPlanCase")
@Api(tags={"测试计划与用例关系表操作接口"})
public class XmTestPlanCaseController {
	
	static Logger logger =LoggerFactory.getLogger(XmTestPlanCaseController.class);
	
	@Autowired
	private XmTestPlanCaseService xmTestPlanCaseService;


	@Autowired
	private XmTestPlanService xmTestPlanService;

	@Autowired
	XmTestCaseService xmTestCaseService;

	@Autowired
	XmGroupService groupService;

	@Autowired
	XmProductService productService;

	@Autowired
	XmProductQxService productQxService;


	@Autowired
	XmRecordService recordService;
	 

	Map<String,Object> fieldsMap = toMap(new XmTestPlanCase());
 
	
	@ApiOperation( value = "查询测试计划与用例关系表信息列表",notes=" ")
	@ApiEntityParams( XmTestPlanCase.class )
	@ApiImplicitParams({
			@ApiImplicitParam(name="pageSize",value="每页大小，默认20条",required=false),
			@ApiImplicitParam(name="pageNum",value="当前页码,从1开始",required=false),
			@ApiImplicitParam(name="total",value="总记录数,服务器端收到0时，会自动计算总记录数，如果上传>0的不自动计算",required=false),
			@ApiImplicitParam(name="count",value="是否计算总记录条数，如果count=true,则计算计算总条数，如果count=false 则不计算",required=false),
			@ApiImplicitParam(name="orderBy",value="排序列 如性别、学生编号排序 orderBy = sex desc,student desc",required=false),
 	})
	@ApiResponses({
		@ApiResponse(code = 200,response=XmTestPlanCase.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listXmTestPlanCase(@ApiIgnore @RequestParam Map<String,Object> params){
		
		
		RequestUtils.transformArray(params, "$pks");		
		IPage page=QueryTools.initPage(params);

		User user= LoginUtils.getCurrentUserInfo();
		paramsInit(params);
		QueryWrapper<XmTestPlanCase> qw = QueryTools.initQueryWrapper(XmTestPlanCase.class , params,"caseName","menuId");
		List<Map<String,Object>> datas = xmTestPlanCaseService.selectListMapByWhere(page,qw,params);
			return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());	//列出XmTestPlanCase列表

	}

	public void paramsInit(Map<String,Object> xmTestPlanCase){

		QueryTools.alias(xmTestPlanCase,"productId res.productId");
		User user=LoginUtils.getCurrentUserInfo();
		xmTestPlanCase.put("pbranchId",user.getBranchId());
	}


	@ApiOperation( value = "查询执行结果分布",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200,response=XmTestPlanCase.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/getXmTestPlanCaseExecStatusDist",method=RequestMethod.GET)
	public Result getXmTestPlanCaseExecStatusDist(@ApiIgnore @RequestParam Map<String,Object> params){
		
				
		IPage page=QueryTools.initPage(params);
		User user= LoginUtils.getCurrentUserInfo();
		paramsInit(params);
		List<Map<String,Object>>	datas = xmTestPlanCaseService.getXmTestPlanCaseExecStatusDist(params);	//列出XmTestPlanCase列表
		return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());	//列出XmTestPlanCase列表

	}


	@ApiOperation( value = "查询成员执行结果分布",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200,response=XmTestPlanCase.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/getXmTestPlanCaseUserDist",method=RequestMethod.GET)
	public Result getXmTestPlanCaseUserDist(@ApiIgnore @RequestParam Map<String,Object> params){
		
				
		IPage page=QueryTools.initPage(params);
		paramsInit(params);
		List<Map<String,Object>>	datas = xmTestPlanCaseService.getXmTestPlanCaseUserDist(params);	//列出XmTestPlanCase列表
		return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());	//列出XmTestPlanCase列表

	}


	@ApiOperation( value = "查询测试执行次数按日统计",notes=" ")
 	@ApiResponses({
			@ApiResponse(code = 200,response=XmTestPlanCase.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/getXmTestDayTimesList",method=RequestMethod.GET)
	public Result getXmTestDayTimesList(@ApiIgnore @RequestParam Map<String,Object> params){
		
				
		IPage page=QueryTools.initPage(params);
		User user= LoginUtils.getCurrentUserInfo();
		paramsInit(params);
		List<Map<String,Object>>	datas = xmTestPlanCaseService.getXmTestDayTimesList(params);	//列出XmTestPlanCase列表
		return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());	//列出XmTestPlanCase列表


	}
	@ApiOperation( value = "查询测试用例规划到测试计划的数目",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200,response=XmTestPlanCase.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/getXmTestCaseToPlanCalcList",method=RequestMethod.GET)
	public Result getXmTestCaseToPlanCalcList(@ApiIgnore @RequestParam Map<String,Object> params){
		
				
		IPage page=QueryTools.initPage(params);
		User user= LoginUtils.getCurrentUserInfo();
		paramsInit(params);
		List<Map<String,Object>>	datas = xmTestPlanCaseService.getXmTestCaseToPlanCalcList(params);	//列出XmTestPlanCase列表
		return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());	//列出XmTestPlanCase列表

	}




	@ApiOperation( value = "从用例库导入用例列表到测试计划中",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200,response=XmTestPlanCase.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/importFromTestCase",method=RequestMethod.POST)
	public Result importFromTestCase(@RequestBody ImportFromTestCaseVo importFromTestCaseVo) {

			if(importFromTestCaseVo.getCaseIds()==null || importFromTestCaseVo.getCaseIds().size()==0){
				return Result.error("caseIds-0","测试用例编号不能为空");
			}
			if(!StringUtils.hasText(importFromTestCaseVo.getPlanId())){
				return Result.error("planId-0","测试计划编号不能为空");
			}
			List<XmTestCase> casesDb=xmTestCaseService.selectListByIds(importFromTestCaseVo.getCaseIds());
			if(casesDb==null || casesDb.size()<=0){
				return Result.error("cases-0","测试用例不存在");
			}

			XmTestPlan xmTestPlanDb=this.xmTestPlanService.getById(importFromTestCaseVo.getPlanId());
			if(xmTestPlanDb==null){
				return Result.error("testPlan-0","测试计划不存在");
			}
			if("2".equals(xmTestPlanDb.getStatus())){
				return Result.error("status-2","测试计划已结束");
			}
			User user=LoginUtils.getCurrentUserInfo();
			XmProduct xmProductDb=productService.getProductFromCache(xmTestPlanDb.getProductId());
			if(!groupService.checkUserIsProductAdm(xmProductDb,user.getUserid()) && !groupService.checkUserExistsProductGroup(xmProductDb.getId(),user.getUserid())){
				return Result.error("no-in-pteam","您不是产品团队成员，不能操作。");
			};
			//过滤掉已存在的测试用例 同一个用例不能重复添加到同一个计划中
			List<XmTestPlanCase> planCasesDb=this.xmTestPlanCaseService.selectListByCaseIdsAndPlanId(importFromTestCaseVo.getPlanId(),importFromTestCaseVo.getCaseIds()	);
			if(planCasesDb!=null && planCasesDb.size()>0){
				casesDb=casesDb.stream().filter(k->!planCasesDb.stream().filter(i->i.getCaseId().equals(k.getId())).findAny().isPresent()).collect(Collectors.toList());
			}
			if(casesDb.size()>0){
 				List<XmTestPlanCase> planCases=new ArrayList<>();
				for (XmTestCase xmTestCase : casesDb) {
					XmTestPlanCase xmTestPlanCase=new XmTestPlanCase();
					BeanUtils.copyProperties(xmTestCase,xmTestPlanCase);
					xmTestPlanCase.setPlanId(xmTestPlanDb.getId());
					xmTestPlanCase.setBugs(0);
					xmTestPlanCase.setExecStatus("0");
					xmTestPlanCase.setCaseId(xmTestCase.getId());
					xmTestPlanCase.setCtime(new Date());
					xmTestPlanCase.setLtime(new Date());
					xmTestPlanCase.setExecUserid(user.getUserid());
					xmTestPlanCase.setExecUsername(user.getUsername());
					xmTestPlanCase.setPriority(xmTestCase.getCpriority());
					xmTestPlanCase.setProjectId(xmTestPlanDb.getProjectId());
					xmTestPlanCase.setProductId(xmProductDb.getId());
					planCases.add(xmTestPlanCase);
					recordService.addXmCaseExeRecord(xmTestPlanCase.getProductId(),xmTestPlanCase.getProjectId(),xmTestPlanCase.getCaseId(),xmTestPlanCase.getPlanId(),"执行性用例-批量新增",String.format("【%s】新增执行性用例【%s】",user.getUsername(),xmTestCase.getCaseName()));
				}
				this.xmTestPlanCaseService.batchInsert(planCases);
			}

		return Result.ok();
		
	}
	@ApiOperation( value = "删除一条测试计划与用例关系表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delXmTestPlanCase(@RequestBody XmTestPlanCase xmTestPlanCase){

            if(!StringUtils.hasText(xmTestPlanCase.getCaseId())) {
                 return Result.error("pk-not-exists","请上送主键参数caseId");
            }
            if(!StringUtils.hasText(xmTestPlanCase.getPlanId())) {
                 return Result.error("pk-not-exists","请上送主键参数planId");
            }
            XmTestPlanCase xmTestPlanCaseDb = xmTestPlanCaseService.selectOneObject(xmTestPlanCase);
            if( xmTestPlanCaseDb == null ){
                return Result.error("data-not-exists","数据不存在，无法删除");
            }
            XmProduct xmProductDb=productService.getProductFromCache(xmTestPlanCaseDb.getProductId());
            if(xmProductDb==null){
				return Result.error("product-not-exists","产品已不存在");
			}
            User user=LoginUtils.getCurrentUserInfo();
            Tips tips=productQxService.checkProductQx(xmProductDb,1,user);
            Result.assertIsFalse(tips);
			xmTestPlanCaseService.deleteByPk(xmTestPlanCase);
		recordService.addXmCaseExeRecord(xmTestPlanCaseDb.getProductId(),xmTestPlanCaseDb.getProjectId(),xmTestPlanCaseDb.getCaseId(),xmTestPlanCaseDb.getPlanId(),"执行性用例-删除",String.format("【%s】删除执行性用例【%s】",user.getUsername(),xmTestPlanCaseDb.getCaseId()));

		return Result.ok();
	}

	@ApiOperation( value = "根据主键修改一条测试计划与用例关系表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmTestPlanCase.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editXmTestPlanCase(@RequestBody XmTestPlanCase xmTestPlanCase) {

            if(!StringUtils.hasText(xmTestPlanCase.getCaseId())) {
                 return Result.error("pk-not-exists","请上送主键参数caseId");
            }
            if(!StringUtils.hasText(xmTestPlanCase.getPlanId())) {
                 return Result.error("pk-not-exists","请上送主键参数planId");
            }
            XmTestPlanCase xmTestPlanCaseDb = xmTestPlanCaseService.selectOneObject(xmTestPlanCase);
            if( xmTestPlanCaseDb == null ){
                return Result.error("data-not-exists","数据不存在，无法修改");
            }
			XmProduct xmProductDb=productService.getProductFromCache(xmTestPlanCaseDb.getProductId());
			if(xmProductDb==null){
				return Result.error("product-not-exists","产品已不存在");
			}
			User user=LoginUtils.getCurrentUserInfo();
			Tips tips=productQxService.checkProductQx(xmProductDb,1,user);
			Result.assertIsFalse(tips);
			xmTestPlanCaseService.updateSomeFieldByPk(xmTestPlanCase);
			return Result.ok();
		
	}

    @ApiOperation( value = "批量修改某些字段",notes="")
    @ApiEntityParams( value = XmTestPlanCase.class, props={ }, remark = "测试计划与用例关系表", paramType = "body" )
	@ApiResponses({
			@ApiResponse(code = 200,response=XmTestPlanCase.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields( @ApiIgnore @RequestBody Map<String,Object> xmTestPlanCaseMap) {

			List<Map<String,Object>> $pks= (List<Map<String,Object>>) xmTestPlanCaseMap.get("$pks");
			if($pks==null || $pks.size()==0){
				return Result.error("$pks-0","$pks不能为空");
			}

			Set<String> fields=new HashSet<>();
            fields.add("caseId");
            fields.add("planId");
			fields.add("execDate");
			for (String fieldName : xmTestPlanCaseMap.keySet()) {
				if(fields.contains(fieldName)){
					return Result.error(fieldName+"-no-edit",fieldName+"不允许修改");
				}
			}
			Set<String> fieldKey=xmTestPlanCaseMap.keySet().stream().filter(i-> fieldsMap.containsKey(i)).collect(Collectors.toSet());
			fieldKey=fieldKey.stream().filter(i->xmTestPlanCaseMap.get(i)!=null).collect(Collectors.toSet());

			if(fieldKey.size()<=0) {
				return Result.error("fieldKey-0","没有需要更新的字段");
 			}
			XmTestPlanCase xmTestPlanCase = fromMap(xmTestPlanCaseMap,XmTestPlanCase.class);
			List<XmTestPlanCase> xmTestPlanCasesDb=xmTestPlanCaseService.selectListByIds($pks);
			if(xmTestPlanCasesDb==null ||xmTestPlanCasesDb.size()==0){
				return Result.error("data-0","记录已不存在");
			}
			if(StringUtils.hasText(xmTestPlanCase.getExecStatus()) && !"0".equals(xmTestPlanCase.getExecStatus())){
				xmTestPlanCaseMap.put("execDate", DateUtils.getDate("yyyy-MM-dd"));
			}
			List<XmTestPlanCase> can=new ArrayList<>();
			List<XmTestPlanCase> no=new ArrayList<>();
			Set<String> noTipsSet=new HashSet<>();
			XmTestPlanCase xmTestPlanCaseDb=xmTestPlanCasesDb.get(0);
			if(xmTestPlanCasesDb.stream().filter(k->!k.getPlanId().equals(xmTestPlanCaseDb.getPlanId())).findAny().isPresent()){
				return Result.error("planId-0","批量操作只能操作同一个测试计划的用例");
			}
			User user = LoginUtils.getCurrentUserInfo();

			XmProduct xmProductDb=productService.getProductFromCache(xmTestPlanCaseDb.getProductId());
			if(xmProductDb==null){
				return Result.error("product-not-exists","产品已不存在");
			}
			if(StringUtils.hasText(xmTestPlanCase.getExecUserid())){
 				Tips tips =productQxService.checkProductQx(xmProductDb,1,user,xmTestPlanCase.getExecUserid(),xmTestPlanCase.getExecUsername(),null);
				Result.assertIsFalse(tips);
			}else {
 				Tips tips =productQxService.checkProductQx(xmProductDb,1,user);
				Result.assertIsFalse(tips);
			}

			boolean isPm=groupService.checkUserIsProductAdm(xmProductDb,user.getUserid());
			if(isPm){
				can=xmTestPlanCasesDb;
			}else {
				for (XmTestPlanCase pcDb : xmTestPlanCasesDb) {
	 				Tips tips2 = productQxService.checkProductQx(xmProductDb, 1, user, pcDb.getExecUserid(), pcDb.getExecUsername(), null);
					if (!tips2.isOk()) {
						no.add(xmTestPlanCaseDb);
						noTipsSet.add(tips2.getMsg());
					} else {
						can.add(xmTestPlanCaseDb);
					}
				}
			}
			if(can.size()>0){
				List<Map<String,Object>> pks=can.stream().map(i->map( "caseId",i.getCaseId(),  "planId",i.getPlanId())).collect(Collectors.toList());
                xmTestPlanCaseMap.put("$pks",pks);
			    xmTestPlanCaseService.editSomeFields(xmTestPlanCaseMap);


				if(xmTestPlanCaseMap.containsKey("budgetWorkload")){
					xmTestPlanCaseService.batchUpdateBudgetWorkloadAndRate(pks, NumberUtil.getBigDecimal(xmTestPlanCaseMap.get("budgetWorkload"), BigDecimal.ZERO));
				}
				for (XmTestPlanCase row: can) {
					recordService.addXmCaseExeRecord(row.getProductId(),row.getProjectId(),row.getCaseId(),row.getPlanId(),"执行性用例-批量修改",String.format("【%s】批量修改执行性用例: %s",user.getUsername(),ChangeLogService.getChangeLogsString(xmTestPlanCaseMap,row)));

				}
			}
			List<String> msgs=new ArrayList<>();
			if(can.size()>0){
				msgs.add(String.format("成功更新以下%s条数据",can.size()));
			}
			if(no.size()>0){
				msgs.add(String.format("以下%s个数据无权限更新,原因【%s】",no.size(),noTipsSet.stream().collect(Collectors.joining(";"))));
			}
			if(can.size()>0){
				return Result.ok(msgs.stream().collect(Collectors.joining()));
			}else {
				return Result.error(msgs.stream().collect(Collectors.joining()));
			} 
		
	}

	@ApiOperation( value = "根据主键列表批量删除测试计划与用例关系表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelXmTestPlanCase(@RequestBody List<XmTestPlanCase> xmTestPlanCases) {
		
        
        
            if(xmTestPlanCases.size()<=0){
                return Result.error("data-0","请上送待删除数据列表");
            }
             List<XmTestPlanCase> datasDb=xmTestPlanCaseService.selectListByIds(xmTestPlanCases.stream().map(i->map( "caseId",i.getCaseId() ,  "planId",i.getPlanId() )).collect(Collectors.toList()));

            List<XmTestPlanCase> can=new ArrayList<>();
            List<XmTestPlanCase> no=new ArrayList<>();
			Set<String> noTipsSet=new HashSet<>();
			XmTestPlanCase xmTestPlanCaseDb=datasDb.get(0);
			if(datasDb.stream().filter(k->!k.getPlanId().equals(xmTestPlanCaseDb.getPlanId())).findAny().isPresent()){
				return Result.error("planId-0","批量操作只能操作同一个测试计划的用例");
			}
			User user = LoginUtils.getCurrentUserInfo();

			XmProduct xmProductDb=productService.getProductFromCache(xmTestPlanCaseDb.getProductId());
			if(xmProductDb==null){
				return Result.error("product-not-exists","产品已不存在");
			}
			boolean isPm=groupService.checkUserIsProductAdm(xmProductDb,user.getUserid());
			if(isPm){
				can=datasDb;
			}else {
				for (XmTestPlanCase pcDb : datasDb) {
	 				Tips tips1=productQxService.checkProductQx(xmProductDb,1,user,pcDb.getExecUserid(),pcDb.getExecUsername(),null);
					if(tips1.isOk()){
						can.add(pcDb);
					}else{
						no.add(pcDb);
						noTipsSet.add(tips1.getMsg());
					}
				}
			}

            List<String> msgs=new ArrayList<>();
            if(can.size()>0){
                xmTestPlanCaseService.batchDelete(can);
                msgs.add(String.format("成功删除%s条数据.",can.size()));
				for (XmTestPlanCase row: can) {
					recordService.addXmCaseExeRecord(row.getProductId(),row.getProjectId(),row.getCaseId(),row.getPlanId(),"执行性用例-批量删除",String.format("【%s】批量删除执行性用例【%s】",user.getUsername(),row.getCaseId()));

				}
            }
    
            if(no.size()>0){ 
                msgs.add(String.format("以下%s条数据不能删除.原因【%s】",no.size(),noTipsSet.stream().collect(Collectors.joining(";"))));
            }
            if(can.size()>0){
                 return Result.ok(msgs.stream().collect(Collectors.joining()));
            }else {
                return Result.error(msgs.stream().collect(Collectors.joining()));
            } 
        
	} 

}
