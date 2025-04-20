package com.xm.core.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.entity.Tips;
import com.mdp.core.query.QueryTools;
import com.mdp.core.utils.NumberUtil;
import com.mdp.core.utils.RequestUtils;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.swagger.ApiEntityParams;
import com.xm.core.entity.XmProduct;
import com.xm.core.entity.XmTestCase;
import com.xm.core.service.*;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static com.mdp.core.utils.BaseUtils.fromMap;
import static com.mdp.core.utils.BaseUtils.toMap;

/**
 * url编制采用rest风格,如对xm_test_case 测试用例的操作有增删改查,对应的url分别为:<br>
 * 组织 com  顶级模块 xm 大模块 core 小模块 <br>
 * 实体 XmTestCase 表 xm_test_case 当前主键(包括多主键): id; 
 ***/
@RestController("xm.core.xmTestCaseController")
@RequestMapping(value="/xm/core/xmTestCase")
@Api(tags={"测试用例操作接口"})
public class XmTestCaseController {
	
	static Logger logger =LoggerFactory.getLogger(XmTestCaseController.class);
	
	@Autowired
	private XmTestCaseService xmTestCaseService;

	@Autowired
	XmGroupService groupService;

	@Autowired
	XmProductService productService;

	@Autowired
	XmProductQxService productQxService;

	@Autowired
	XmRecordService recordService;
	 

	Map<String,Object> fieldsMap = toMap(new XmTestCase());
 
	
	@ApiOperation( value = "查询测试用例信息列表",notes=" ")
	@ApiEntityParams( XmTestCase.class )
	@ApiImplicitParams({
			@ApiImplicitParam(name="pageSize",value="每页大小，默认20条",required=false),
			@ApiImplicitParam(name="pageNum",value="当前页码,从1开始",required=false),
			@ApiImplicitParam(name="total",value="总记录数,服务器端收到0时，会自动计算总记录数，如果上传>0的不自动计算",required=false),
			@ApiImplicitParam(name="count",value="是否计算总记录条数，如果count=true,则计算计算总条数，如果count=false 则不计算",required=false),
			@ApiImplicitParam(name="orderBy",value="排序列 如性别、学生编号排序 orderBy = sex desc,student desc",required=false),
 	})
	@ApiResponses({
		@ApiResponse(code = 200,response=XmTestCase.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listXmTestCase(@ApiIgnore @RequestParam Map<String,Object> params){
		
		
		RequestUtils.transformArray(params, "ids");
		RequestUtils.transformArray(params, "menuIds");		
		IPage page=QueryTools.initPage(params);
		paramsInit(params);
		QueryWrapper<XmTestCase> qw = QueryTools.initQueryWrapper(XmTestCase.class , params);
		List<Map<String,Object>> datas = xmTestCaseService.selectListMapByWhere(page,qw,params);
			return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());	//列出XmTestCase列表

	}

	@ApiOperation( value = "测试用例排行榜",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200,response=XmTestCase.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/getXmTestCaseSort",method=RequestMethod.GET)
	public Result getXmTestCaseSort(@ApiIgnore @RequestParam Map<String,Object> params){
		
		
 		paramsInit(params);
 		String groupBy= (String) params.get("groupBy");
 		if("func_id".equals(groupBy) || "menu_id".equals(groupBy) || "cuserid".equals(groupBy)){

		}else {
 			return Result.error("groupBy-0","分组参数错误");
		}
 		IPage page=QueryTools.initPage(params);
 		QueryWrapper<XmTestCase> qw=QueryTools.initQueryWrapper(XmTestCase.class,params);
		List<Map<String,Object>>	datas = xmTestCaseService.getXmTestCaseSort(page,qw,params);	//列出XmTestCase列表
		return Result.ok().setData(datas).setTotal(page.getTotal());
	}


	public void paramsInit(Map<String,Object> xmTestCase){
		Object ids=xmTestCase.get("ids");
		Object menuIds=xmTestCase.get("menuIds");
		Object caseIds=xmTestCase.get("caseIds");
		String id= (String) xmTestCase.get("id");
		String cuserid = (String) xmTestCase.get("cuserid");
		String menuId= (String) xmTestCase.get("menuId");
		String funcId= (String) xmTestCase.get("funcId");
		String iteration= (String) xmTestCase.get("iteration");
		String casedbId= (String) xmTestCase.get("casedbId");
		String productId= (String) xmTestCase.get("productId");
		String projectId= (String) xmTestCase.get("projectId");
		String planId= (String) xmTestCase.get("planId");
		String caseId= (String) xmTestCase.get("caseId");
		if(!(StringUtils.hasText(id)||StringUtils.hasText(menuId)||StringUtils.hasText(funcId)||StringUtils.hasText(iteration)||StringUtils.hasText(casedbId)||StringUtils.hasText(productId)||StringUtils.hasText(projectId)
				||StringUtils.hasText(planId)||StringUtils.hasText(caseId)||StringUtils.hasText(cuserid)||ids!=null||caseIds!=null||menuIds!=null)){
			User user=LoginUtils.getCurrentUserInfo();
			xmTestCase.put("pbranchId",user.getBranchId());
		}
	}

	@ApiOperation( value = "新增一条测试用例信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmTestCase.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addXmTestCase(@RequestBody XmTestCase xmTestCase) {


			xmTestCase.setId(xmTestCaseService.createKey("id"));
			if(StringUtils.isEmpty(xmTestCase.getProductId())){
				return Result.error("productId-0","产品编号不能为空");
			}
			User user=LoginUtils.getCurrentUserInfo();
			XmProduct xmProductDb=productService.getProductFromCache(xmTestCase.getProductId());
			Tips tips=productQxService.checkProductQx(xmProductDb,1,user,xmTestCase.getCuserid(),xmTestCase.getCusername(),xmTestCase.getCbranchId());
			Result.assertIsFalse(tips);
			xmTestCase.setCuserid(user.getUserid());
			xmTestCase.setCbranchId(user.getBranchId());
			xmTestCase.setCusername(user.getUsername());
			xmTestCase.setPbranchId(xmProductDb.getBranchId());
			xmTestCase.setLuserid(user.getUserid());
			xmTestCase.setLusername(user.getUsername());
			xmTestCase.setCaseStatus("0");
			xmTestCase.setCtime(new Date());
			xmTestCase.setLtime(new Date());
			xmTestCaseService.insert(xmTestCase);
			recordService.addXmCaseRecord(xmTestCase.getProductId(),xmTestCase.getId(),xmTestCase.getFuncId(),"用例-新增",String.format("【%s】新增测试用例【%s】",user.getUsername(),xmTestCase.getCaseName()));
		return Result.ok().setData(xmTestCase);
	}

	@ApiOperation( value = "删除一条测试用例信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delXmTestCase(@RequestBody XmTestCase xmTestCase){

            if(!StringUtils.hasText(xmTestCase.getId())) {
                 return Result.error("pk-not-exists","请上送主键参数id");
            }
            XmTestCase xmTestCaseDb = xmTestCaseService.selectOneObject(xmTestCase);
            if( xmTestCaseDb == null ){
                return Result.error("data-not-exists","数据不存在，无法删除");
            }
            User user=LoginUtils.getCurrentUserInfo();
			XmProduct xmProductDb=productService.getProductFromCache(xmTestCaseDb.getProductId());
			boolean isPm=groupService.checkUserIsProductAdm(xmProductDb,user.getUserid());
			if(!isPm){
 				Tips tips =productQxService.checkProductQx(xmProductDb,1,user,xmTestCaseDb.getCuserid(),xmTestCaseDb.getCusername(),xmTestCaseDb.getCbranchId());
				 Result.assertIsFalse(tips);
			}
			xmTestCaseService.deleteByPk(xmTestCase);
		recordService.addXmCaseRecord(xmTestCaseDb.getProductId(),xmTestCaseDb.getId(),xmTestCaseDb.getFuncId(),"用例-删除",String.format("【%s】新增测试用例【%s】",user.getUsername(),xmTestCaseDb.getCaseName()));

		return Result.ok();
 		
	}

	@ApiOperation( value = "根据主键修改一条测试用例信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmTestCase.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editXmTestCase(@RequestBody XmTestCase xmTestCase) {

            if(!StringUtils.hasText(xmTestCase.getId())) {
                 return Result.error("pk-not-exists","请上送主键参数id");
            }
            XmTestCase xmTestCaseDb = xmTestCaseService.selectOneById(xmTestCase);
            if( xmTestCaseDb == null ){
                return Result.error("data-not-exists","数据不存在，无法修改");
            }
			User user=LoginUtils.getCurrentUserInfo();
			XmProduct xmProductDb=productService.getProductFromCache(xmTestCaseDb.getProductId());
			boolean isPm=groupService.checkUserIsProductAdm(xmProductDb,user.getUserid());
			if(!isPm){
 				Tips tips =productQxService.checkProductQx(xmProductDb,1,user,xmTestCaseDb.getCuserid(),xmTestCaseDb.getCusername(),null);
				Result.assertIsFalse(tips);
			}

			xmTestCase.setLuserid(user.getUserid());
			xmTestCase.setLusername(user.getUsername());
			xmTestCase.setCuserid(null);//关键数据不能更新
			xmTestCase.setCusername(null);
			xmTestCase.setCbranchId(null);
			xmTestCase.setLtime(new Date());
			xmTestCaseService.updateSomeFieldByPk(xmTestCase);
		return Result.ok();
	}

    @ApiOperation( value = "批量修改某些字段",notes="")
    @ApiEntityParams( value = XmTestCase.class, props={ }, remark = "测试用例", paramType = "body" )
	@ApiResponses({
			@ApiResponse(code = 200,response=XmTestCase.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields( @ApiIgnore @RequestBody Map<String,Object> xmTestCaseMap) {

            List<String> ids= (List<String>) xmTestCaseMap.get("$pks");
			if(ids==null || ids.size()==0){
				return Result.error("$pks-0","$pks不能为空");
			}

			Set<String> fields=new HashSet<>();
            fields.add("id");
			for (String fieldName : xmTestCaseMap.keySet()) {
				if(fields.contains(fieldName)){
					return Result.error(fieldName+"-no-edit",fieldName+"不允许修改");
				}
			}
			Set<String> fieldKey=xmTestCaseMap.keySet().stream().filter(i-> fieldsMap.containsKey(i)).collect(Collectors.toSet());
			fieldKey=fieldKey.stream().filter(i->xmTestCaseMap.get(i)!=null).collect(Collectors.toSet());

			if(fieldKey.size()<=0) {
				return Result.error("fieldKey-0","没有需要更新的字段");
 			}
			XmTestCase xmTestCase = fromMap(xmTestCaseMap,XmTestCase.class);
			List<XmTestCase> xmTestCasesDb=xmTestCaseService.selectListByIds(ids);
			if(xmTestCasesDb==null ||xmTestCasesDb.size()==0){
				return Result.error("data-0","记录已不存在");
			}
			List<XmTestCase> can=new ArrayList<>();
			List<XmTestCase> no=new ArrayList<>();
			Map<String,Tips> noTipsMap=new HashMap<>();
			User user = LoginUtils.getCurrentUserInfo();
			XmTestCase xmTestCaseDb2=xmTestCasesDb.get(0);
			if(xmTestCasesDb.stream().filter(k->!k.getProductId().equals(xmTestCaseDb2.getProductId())).findAny().isPresent()){
				return Result.error("product-no-same","批量操作所有测试用例必须都在同一个产品下。");
			}
			XmProduct xmProductDb=productService.getProductFromCache(xmTestCaseDb2.getProductId());
			if( StringUtils.hasText(xmTestCase.getCuserid()) ){
 				Tips tips =this.productQxService.checkProductQx(xmProductDb,1,user,xmTestCase.getCuserid(),xmTestCase.getCusername(),null);
				Result.assertIsFalse(tips);
			}
			boolean isPm=groupService.checkUserIsProductAdm(xmProductDb,user.getUserid());
			if(isPm){
				 can=xmTestCasesDb;
			}else{
				for (XmTestCase xmTestCaseDb : xmTestCasesDb) {
	 				Tips tips2 = productQxService.checkProductQx(xmProductDb,1,user,xmTestCaseDb.getCuserid(),xmTestCaseDb.getCusername(),null);
					if(!tips2.isOk()){
						no.add(xmTestCaseDb);
						noTipsMap.put(tips2.getMsg(),tips2);
					}else{
						can.add(xmTestCaseDb);
					}
				}
			}

			if(can.size()>0){
				List<String> pks=can.stream().map(i->i.getId()).collect(Collectors.toList());
                xmTestCaseMap.put("$pks",pks);
			    xmTestCaseService.editSomeFields(xmTestCaseMap);
				for (XmTestCase row : can) {
					recordService.addXmCaseRecord(row.getProductId(),row.getId(),row.getFuncId(),"用例-批量修改",String.format("【%s】修改测试用例:%s",user.getUsername(),ChangeLogService.getChangeLogsString(xmTestCaseMap,row)));

				}

				if(xmTestCaseMap.containsKey("budgetWorkload")){
					xmTestCaseService.batchUpdateBudgetWorkloadAndRate(pks, NumberUtil.getBigDecimal(xmTestCaseMap.get("budgetWorkload"), BigDecimal.ZERO));
				}
			}
			List<String> msgs=new ArrayList<>();
			if(can.size()>0){
				msgs.add(String.format("成功更新以下%s条数据",can.size()));
			}
			if(no.size()>0){
				msgs.add(String.format("以下%s个数据无权限更新,原因【%s】",no.size(),noTipsMap.keySet().stream().collect(Collectors.joining(";"))));
			}
			if(can.size()>0){
				return Result.ok(msgs.stream().collect(Collectors.joining()));
			}else {
				return Result.error(msgs.stream().collect(Collectors.joining()));
			} 
		
	}

	@ApiOperation( value = "根据主键列表批量删除测试用例信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelXmTestCase(@RequestBody List<XmTestCase> xmTestCases) {
		
        
        
            if(xmTestCases.size()<=0){
                return Result.error("data-0","请上送待删除数据列表");
            }
             List<XmTestCase> datasDb=xmTestCaseService.selectListByIds(xmTestCases.stream().map(i-> i.getId() ).collect(Collectors.toList()));
			User user = LoginUtils.getCurrentUserInfo();
			XmTestCase xmTestCaseDb2=datasDb.get(0);
			if(datasDb.stream().filter(k->!k.getProductId().equals(xmTestCaseDb2.getProductId())).findAny().isPresent()){
				return Result.error("product-no-same","批量操作所有测试用例必须都在同一个产品下。");
			}
			XmProduct xmProductDb=productService.getProductFromCache(xmTestCaseDb2.getProductId());
			boolean isPm=groupService.checkUserIsProductAdm(xmProductDb,user.getUserid());

            List<XmTestCase> can=new ArrayList<>();
            List<XmTestCase> no=new ArrayList<>();
            Map<String, Tips> noTipsMap=new HashMap<>();
            if(isPm){
				can=datasDb;
			}else {
				for (XmTestCase data : datasDb) {
	 				Tips tips =productQxService.checkProductQx(xmProductDb,1,user,data.getCuserid(),data.getCusername(),data.getCbranchId());
					if(tips.isOk()){
						can.add(data);
					}else {
						noTipsMap.put(tips.getMsg(),tips);
						no.add(data);
					}
				}
			}

            List<String> msgs=new ArrayList<>();
            if(can.size()>0){
                xmTestCaseService.batchDelete(can);
                msgs.add(String.format("成功删除%s条数据.",can.size()));
				for (XmTestCase row : can) {
					recordService.addXmCaseRecord(row.getProductId(),row.getId(),row.getFuncId(),"用例-批量删除",String.format("【%s】删除测试用例:%s",user.getUsername(),row.getCaseName()));

				}
            }
    
            if(no.size()>0){ 
                msgs.add(String.format("以下%s条数据无权限删除.原因【%s】",no.size(), noTipsMap.keySet().stream().collect(Collectors.joining(";"))));
            }
            if(can.size()>0){
                 return Result.ok(msgs.stream().collect(Collectors.joining()));
            }else {
                return Result.error(msgs.stream().collect(Collectors.joining()));
            } 
        
	}
}
