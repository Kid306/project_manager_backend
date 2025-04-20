package com.xm.core.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mdp.core.entity.Result;
import com.mdp.core.entity.Tips;
import com.mdp.core.query.QueryTools;
import com.mdp.core.utils.RequestUtils;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.swagger.ApiEntityParams;
import com.xm.core.entity.XmProduct;
import com.xm.core.entity.XmTestPlan;
import com.xm.core.entity.XmTestPlanCase;
import com.xm.core.service.*;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;
import java.util.stream.Collectors;

import static com.mdp.core.utils.BaseUtils.fromMap;
import static com.mdp.core.utils.BaseUtils.toMap;

/**
 * url编制采用rest风格,如对xm_test_plan 测试计划的操作有增删改查,对应的url分别为:<br>
 * 组织 com  顶级模块 xm 大模块 core 小模块 <br>
 * 实体 XmTestPlan 表 xm_test_plan 当前主键(包括多主键): id; 
 ***/
@RestController("xm.core.xmTestPlanController")
@RequestMapping(value="/xm/core/xmTestPlan")
@Api(tags={"测试计划操作接口"})
public class XmTestPlanController {
	
	static Logger logger =LoggerFactory.getLogger(XmTestPlanController.class);
	
	@Autowired
	private XmTestPlanService xmTestPlanService;

	@Autowired
	XmGroupService groupService;

	@Autowired
	XmProductService productService;

	@Autowired
	XmProductQxService productQxService;

	@Autowired
	XmRecordService recordService;

	@Autowired
	XmTestPlanCaseService xmTestPlanCaseService;
	 

	Map<String,Object> fieldsMap = toMap(new XmTestPlan());
 
	
	@ApiOperation( value = "查询测试计划信息列表",notes=" ")
	@ApiEntityParams( XmTestPlan.class )
	@ApiImplicitParams({
			@ApiImplicitParam(name="pageSize",value="每页大小，默认20条",required=false),
			@ApiImplicitParam(name="pageNum",value="当前页码,从1开始",required=false),
			@ApiImplicitParam(name="total",value="总记录数,服务器端收到0时，会自动计算总记录数，如果上传>0的不自动计算",required=false),
			@ApiImplicitParam(name="count",value="是否计算总记录条数，如果count=true,则计算计算总条数，如果count=false 则不计算",required=false),
			@ApiImplicitParam(name="orderBy",value="排序列 如性别、学生编号排序 orderBy = sex desc,student desc",required=false),
 	})
	@ApiResponses({
		@ApiResponse(code = 200,response=XmTestPlan.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listXmTestPlan(@ApiIgnore @RequestParam Map<String,Object> params){
		
		
		RequestUtils.transformArray(params, "ids");
		User user=LoginUtils.getCurrentUserInfo();
		params.put("cbranchId",user.getBranchId());		
		IPage page=QueryTools.initPage(params);
		QueryWrapper<XmTestPlan> qw = QueryTools.initQueryWrapper(XmTestPlan.class , params);
		List<Map<String,Object>> datas = xmTestPlanService.selectListMapByWhere(page,qw,params);
			return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());	//列出XmTestPlan列表

	}


	@ApiOperation( value = "统计测试计划数据",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200,response=XmTestPlan.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/calc",method=RequestMethod.POST)
	public Result calcXmTestPlan(@RequestBody XmTestPlan xmTestPlan) {

			boolean createPk=false;
			if(!StringUtils.hasText(xmTestPlan.getId())) {
				return Result.error("id-0","测试计划编号不能为空");
			}
			this.xmTestPlanService.calcXmTestPlan(xmTestPlan.getId());

		return Result.ok();
		
	}

	@ApiOperation( value = "新增一条测试计划信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmTestPlan.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addXmTestPlan(@RequestBody XmTestPlan xmTestPlan) {

		    boolean createPk=false;
			if(!StringUtils.hasText(xmTestPlan.getId())) {
			    createPk=true;
				xmTestPlan.setId(xmTestPlanService.createKey("id"));
			}
			if(createPk==false){
                 if(xmTestPlanService.selectOneObject(xmTestPlan) !=null ){
                    return Result.error("pk-exists","编号重复，请修改编号再提交");
                }
            }
			if(!StringUtils.hasText(xmTestPlan.getProjectId())){
				return Result.error("projectId-0","项目编号不能为空");
			}

			if(!StringUtils.hasText(xmTestPlan.getProductId())){
				return Result.error("productId-0","产品编号不能为空");
			}

			if(!StringUtils.hasText(xmTestPlan.getCasedbId())){
				return Result.error("casedbId-0","测试用例库编号不能为空");
			}
			User user=LoginUtils.getCurrentUserInfo();
			XmProduct xmProductDb=productService.getProductFromCache(xmTestPlan.getProductId());
 			boolean isPm=groupService.checkUserIsProductAdm(xmProductDb,user.getUserid());
 			if(!isPm){
 				Tips tips =productQxService.checkProductQx(xmProductDb,1,user,xmTestPlan.getCuserid(),xmTestPlan.getCusername(),xmTestPlan.getCbranchId());
				Result.assertIsFalse(tips);
 			}
			xmTestPlan.setPbranchId(xmProductDb.getBranchId());
			xmTestPlan.setCuserid(user.getUserid());
			xmTestPlan.setCusername(user.getUsername());
			xmTestPlan.setCtime(new Date());
			xmTestPlan.setCbranchId(user.getBranchId());
			xmTestPlanService.insert(xmTestPlan);
			recordService.addXmTestPlanRecord(xmTestPlan.getProductId(),xmTestPlan.getProjectId(),xmTestPlan.getId(),xmTestPlan.getCasedbId(),"测试计划-新增",String.format("【%s】新增测试计划【%s】",user.getUsername(),xmTestPlan.getName()));
		return Result.ok().setData(xmTestPlan);
	}

	@ApiOperation( value = "删除一条测试计划信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delXmTestPlan(@RequestBody XmTestPlan xmTestPlan){

            if(!StringUtils.hasText(xmTestPlan.getId())) {
                 return Result.error("pk-not-exists","请上送主键参数id");
            }
            XmTestPlan xmTestPlanDb = xmTestPlanService.selectOneObject(xmTestPlan);
            if( xmTestPlanDb == null ){
                return Result.error("data-not-exists","数据不存在，无法删除");
            }

			User user=LoginUtils.getCurrentUserInfo();
			XmProduct xmProductDb=productService.getProductFromCache(xmTestPlanDb.getProductId());
			boolean isPm=groupService.checkUserIsProductAdm(xmProductDb,user.getUserid());
			if(!isPm){
 				Tips tips =productQxService.checkProductQx(xmProductDb,1,user,xmTestPlanDb.getCuserid(),xmTestPlanDb.getCusername(),xmTestPlanDb.getCbranchId());
				Result.assertIsFalse(tips);
			}
		long count=xmTestPlanCaseService.count(Wrappers.lambdaQuery(XmTestPlanCase.class).eq(XmTestPlanCase::getPlanId,xmTestPlan.getId()));
			if(count>0){
				return Result.error("notallow-del-plan-had-case","测试计划存在【%s】个执行用例，不允许删除，请先删除执行用例。",count);
			}
			xmTestPlanService.deleteByPk(xmTestPlan);
			xmTestPlan=xmTestPlanDb;
		recordService.addXmTestPlanRecord(xmTestPlan.getProductId(),xmTestPlan.getProjectId(),xmTestPlan.getId(),xmTestPlan.getCasedbId(),"测试计划-删除",String.format("【%s】删除测试计划【%s】",user.getUsername(),xmTestPlan.getName()));

		return Result.ok();
		
	}

	@ApiOperation( value = "根据主键修改一条测试计划信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmTestPlan.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editXmTestPlan(@RequestBody XmTestPlan xmTestPlan) {

            if(!StringUtils.hasText(xmTestPlan.getId())) {
                 return Result.error("pk-not-exists","请上送主键参数id");
            }
            XmTestPlan xmTestPlanDb = xmTestPlanService.selectOneObject(xmTestPlan);
            if( xmTestPlanDb == null ){
                return Result.error("data-not-exists","数据不存在，无法修改");
            }


			User user=LoginUtils.getCurrentUserInfo();
			XmProduct xmProductDb=productService.getProductFromCache(xmTestPlanDb.getProductId());
			boolean isPm=groupService.checkUserIsProductAdm(xmProductDb,user.getUserid());
			if(!isPm){
 				Tips tips =productQxService.checkProductQx(xmProductDb,1,user,xmTestPlanDb.getCuserid(),xmTestPlanDb.getCusername(),xmTestPlanDb.getCbranchId());
				Result.assertIsFalse(tips);
			}
			xmTestPlanService.updateSomeFieldByPk(xmTestPlan);
		return Result.ok();
	}

    @ApiOperation( value = "批量修改某些字段",notes="")
    @ApiEntityParams( value = XmTestPlan.class, props={ }, remark = "测试计划", paramType = "body" )
	@ApiResponses({
			@ApiResponse(code = 200,response=XmTestPlan.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields( @ApiIgnore @RequestBody Map<String,Object> xmTestPlanMap) {

            List<String> ids= (List<String>) xmTestPlanMap.get("$pks");
			if(ids==null || ids.size()==0){
				return Result.error("$pks-0","ids不能为空");
			}

			Set<String> fields=new HashSet<>();
            fields.add("id");
			for (String fieldName : xmTestPlanMap.keySet()) {
				if(fields.contains(fieldName)){
					return Result.error(fieldName+"-no-edit",fieldName+"不允许修改");
				}
			}
			Set<String> fieldKey=xmTestPlanMap.keySet().stream().filter(i-> fieldsMap.containsKey(i)).collect(Collectors.toSet());
			fieldKey=fieldKey.stream().filter(i->xmTestPlanMap.get(i)!=null).collect(Collectors.toSet());

			if(fieldKey.size()<=0) {
				return Result.error("fieldKey-0","没有需要更新的字段");
 			}
			XmTestPlan xmTestPlan = fromMap(xmTestPlanMap,XmTestPlan.class);
			List<XmTestPlan> xmTestPlansDb=xmTestPlanService.selectListByIds(ids);
			if(xmTestPlansDb==null ||xmTestPlansDb.size()==0){
				return Result.error("data-0","记录已不存在");
			}
			XmTestPlan xmTestPlanDb2=xmTestPlansDb.get(0);
			if(xmTestPlansDb.stream().filter(k->!k.getProductId().equals(xmTestPlanDb2.getProductId())).findAny().isPresent()){
				return Result.error("product-no-same","批量操作只能操作同一个产品下的测试计划。");
			}
			User user = LoginUtils.getCurrentUserInfo();
			XmProduct xmProductDb=productService.getProductFromCache(xmTestPlanDb2.getProductId());
			if(StringUtils.hasText(xmTestPlan.getCuserid())){
 				Tips tips =this.productQxService.checkProductQx(xmProductDb,1,user,xmTestPlan.getCuserid(),xmTestPlan.getCusername(),xmTestPlan.getCbranchId());
				Result.assertIsFalse(tips);
			}
			boolean isPm=groupService.checkUserIsProductAdm(xmProductDb,user.getUserid());
			List<XmTestPlan> can=new ArrayList<>();
			List<XmTestPlan> no=new ArrayList<>();
			Set<String> noTips=new HashSet<>();
			if(isPm){
				can=xmTestPlansDb;
			}else {
				for (XmTestPlan xmTestPlanDb : xmTestPlansDb) {
	 				Tips tips2 =productQxService.checkProductQx(xmProductDb,1,user,xmTestPlanDb.getCuserid(),xmTestPlanDb.getCusername(),xmTestPlanDb.getCbranchId());
					if(!tips2.isOk()){
						no.add(xmTestPlanDb);
						noTips.add(tips2.getMsg());
					}else{
						can.add(xmTestPlanDb);
					}
				}
			}
			if(can.size()>0){
                xmTestPlanMap.put("$pks",can.stream().map(i->i.getId()).collect(Collectors.toList()));
			    xmTestPlanService.editSomeFields(xmTestPlanMap);
				for (XmTestPlan r : can) {
					recordService.addXmTestPlanRecord(r.getProductId(),r.getProjectId(),r.getId(),r.getCasedbId(),"测试计划-批量修改",String.format("【%s】修改测试计划: %s",user.getUsername(),ChangeLogService.getChangeLogsString(xmTestPlanMap,r)));
				}
			}
			List<String> msgs=new ArrayList<>();
			if(can.size()>0){
				msgs.add(String.format("成功更新以下%s条数据",can.size()));
			}
			if(no.size()>0){
				msgs.add(String.format("以下%s个数据无权限更新,原因【%s】",no.size(),noTips.stream().collect(Collectors.joining(";"))));
			}
			if(can.size()>0){
				return Result.ok(msgs.stream().collect(Collectors.joining()));
			}else {
				return Result.error(msgs.stream().collect(Collectors.joining()));
			} 
		
	}

	@ApiOperation( value = "根据主键列表批量删除测试计划信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelXmTestPlan(@RequestBody List<XmTestPlan> xmTestPlans) {
		
        
        
            if(xmTestPlans.size()<=0){
                return Result.error("data-0","请上送待删除数据列表");
            }
             List<XmTestPlan> datasDb=xmTestPlanService.selectListByIds(xmTestPlans.stream().map(i-> i.getId() ).collect(Collectors.toList()));
			XmTestPlan xmTestPlanDb2=datasDb.get(0);
			if(datasDb.stream().filter(k->!k.getProductId().equals(xmTestPlanDb2.getProductId())).findAny().isPresent()){
				return Result.error("product-no-same","批量操作只能操作同一个产品下的测试计划。");
			}
			User user = LoginUtils.getCurrentUserInfo();
			XmProduct xmProductDb=productService.getProductFromCache(xmTestPlanDb2.getProductId());
 			boolean isPm=groupService.checkUserIsProductAdm(xmProductDb,user.getUserid());
			List<XmTestPlan> can=new ArrayList<>();
			List<XmTestPlan> no=new ArrayList<>();
			Set<String> noTips=new HashSet<>();
			if(isPm){
				can=datasDb;
			}else {
				for (XmTestPlan xmTestPlanDb : datasDb) {
	 				Tips tips2  =productQxService.checkProductQx(xmProductDb,1,user,xmTestPlanDb.getCuserid(),xmTestPlanDb.getCusername(),xmTestPlanDb.getCbranchId());
					if(!tips2.isOk()){
						no.add(xmTestPlanDb);
						noTips.add(tips2.getMsg());
					}else{
						can.add(xmTestPlanDb);
					}

				}
			}

            List<String> msgs=new ArrayList<>();
		List<XmTestPlan> existsCases = Collections.emptyList();
			if(can.size()>0){
				existsCases=xmTestPlanService.lambdaQuery().in(XmTestPlan::getId,can.stream().map(k->k.getId()).collect(Collectors.toList())).exists("select 1 from xm_test_plan_case r where r.plan_id=xm_test_plan.id").list();
				List<XmTestPlan> finalExistsCases = existsCases;
				can=can.stream().filter(k->!finalExistsCases.stream().filter(d->d.getId().equals(k.getId())).findAny().isPresent()).collect(Collectors.toList());
				xmTestPlanService.batchDelete(can);
                msgs.add(String.format("成功删除%s条数据.",can.size()));
				for (XmTestPlan r : can) {
					recordService.addXmTestPlanRecord(r.getProductId(),r.getProjectId(),r.getId(),r.getCasedbId(),"测试计划-批量删除",String.format("【%s】删除测试计划【%s】",user.getUsername(),r.getName()));
				}
            }

        if(no.size()>0){
                msgs.add(String.format("有%s条数据无权限删除.原因【%s】",no.size(), noTips.stream().collect(Collectors.joining(";"))));
            }
		if(existsCases!=null && existsCases.size()>0){
			msgs.add(String.format("以下计划存在执行用例，不能删除，请先将执行用例删除.【%s】",existsCases.stream().map(k->k.getName()).collect(Collectors.joining(","))));
		}
            if(can.size()>0){
                 return Result.ok(msgs.stream().collect(Collectors.joining()));
            }else {
                return Result.error(msgs.stream().collect(Collectors.joining()));
            } 
        
	} 

}
