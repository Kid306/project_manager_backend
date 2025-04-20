package com.xm.core.ctrl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.entity.Tips;
import com.mdp.core.query.QueryTools;
import com.mdp.core.utils.BaseUtils;
import com.mdp.core.utils.RequestUtils;
import com.mdp.msg.client.PushNotifyMsgService;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.sensitive.SensitiveWordService;
import com.mdp.swagger.ApiEntityParams;
import com.xm.core.entity.XmIteration;
import com.xm.core.entity.XmProduct;
import com.xm.core.service.*;
import com.xm.core.vo.XmIterationVo;
import io.swagger.annotations.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;
import java.util.stream.Collectors;

import static com.mdp.core.utils.BaseUtils.fromMap;

/**
 * url编制采用rest风格,如对XM.xm_iteration 迭代定义的操作有增删改查,对应的url分别为:<br>
 *  新增: xm/xmIteration/add <br>
 *  查询: xm/xmIteration/list<br>
 *  模糊查询: xm/xmIteration/listKey<br>
 *  修改: xm/xmIteration/edit <br>
 *  删除: xm/xmIteration/del<br>
 *  批量删除: xm/xmIteration/batchDel<br>
 * 组织 com.qqkj  顶级模块 oa 大模块 xm 小模块 <br>
 * 实体 XmIteration 表 XM.xm_iteration 当前主键(包括多主键): id; 
 ***/
@RestController("xm.core.xmIterationController")
@RequestMapping(value="/xm/core/xmIteration")
@Api(tags={"迭代定义操作接口"})
public class XmIterationController {
	
	static Log logger=LogFactory.getLog(XmIterationController.class);
	
	@Autowired
	private XmIterationService xmIterationService;



	@Autowired
	private XmProductService xmProductService;


	@Autowired
	private XmRecordService  xmRecordService;


	@Autowired
	private XmGroupService  groupService;

	@Autowired
	XmIterationStateService xmIterationStateService;

	@Autowired
	XmProductQxService productQxService;


	@Autowired
	PushNotifyMsgService notifyMsgService;
	@Autowired
	SensitiveWordService sensitiveWordService;

	Map<String,Object> fieldsMap = BaseUtils.toMap(new XmIteration());


	@ApiOperation( value = "查询迭代定义信息列表",notes="listXmIteration,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
	@ApiEntityParams(XmIteration.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name="pageSize",value="每页记录数",required=false),
		@ApiImplicitParam(name="pageNum",value="当前页码,从1开始",required=false),
		@ApiImplicitParam(name="total",value="总记录数,服务器端收到0时，会自动计算总记录数，如果上传>0的不自动计算",required=false),
		@ApiImplicitParam(name="orderBy",value="排序列 如性别、学生编号排序 orderBy = sex desc,student_id desc",required=false),
		@ApiImplicitParam(name="count",value="是否进行总条数计算,count=true|false",required=false) 
	})
	@ApiResponses({
		@ApiResponse(code = 200,response= XmIteration.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listXmIteration(@ApiIgnore @RequestParam Map<String,Object> params){
		
		
		RequestUtils.transformArray(params, "ids");		
		IPage page=QueryTools.initPage(params);
		String id= (String) params.get("id");
		Object ids=  params.get("ids");
		String productId= (String) params.get("productId");
		String adminUserid= (String) params.get("adminUserid");
		String menuId= (String) params.get("menuId");
		String queryScope=(String) params.get("queryScope");
		String branchId=(String) params.get("branchId");
		String linkProjectId=(String) params.get("linkProjectId");
		User user = LoginUtils.getCurrentUserInfo();
		params.put("userid",user.getUserid());
		params.remove("branchId");

		QueryTools.alias(params,"iterationName res.iterationName");
		QueryWrapper<XmIteration> qw=QueryTools.initQueryWrapper(XmIteration.class,params);
		if(  !( StringUtils.hasText(branchId)|| StringUtils.hasText(id) || StringUtils.hasText(productId)|| StringUtils.hasText(menuId)||ids!=null
				|| StringUtils.hasText(adminUserid) ) ){
			if(LoginUtils.isBranchAdmin()){
				qw.eq("res.branch_id",user.getBranchId());
			}else{
				if(!StringUtils.hasText(productId) && !StringUtils.hasText(linkProjectId)){
					params.put("compete",user.getUserid());
				}else{
					qw.eq("res.branch_id",user.getBranchId());
				}
			}
		}
		if("branchId".equals(queryScope)){
			qw.eq("res.branch_id",user.getBranchId());
		}
 		List<Map<String,Object>> datas = xmIterationService.selectListMapByWhere(page,qw,params);
			return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());	//列出XmIteration列表

	}
	
	@ApiOperation( value = "查询迭代数据，联通状态数据一起带出",notes="") 
	@ApiResponses({
		@ApiResponse(code = 200,response=XmIteration.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/listWithState",method=RequestMethod.GET)
	public Result listWithState(@ApiIgnore @RequestParam Map<String,Object> params){
		 
		RequestUtils.transformArray(params, "ids");		
		IPage page=QueryTools.initPage(params);

		String id= (String) params.get("id");
		Object ids=  params.get("ids");
		String productId= (String) params.get("productId");
		String adminUserid= (String) params.get("adminUserid");
		String menuId= (String) params.get("menuId");
		String queryScope=(String) params.get("queryScope");
		String branchId=(String) params.get("branchId");
		String linkProjectId=(String) params.get("linkProjectId");
		User user = LoginUtils.getCurrentUserInfo();
		params.put("userid",user.getUserid());
		params.remove("branchId");
		QueryTools.alias(params,"iterationName res.iterationName");
		QueryWrapper<XmIteration> qw=QueryTools.initQueryWrapper(XmIteration.class,params);
		if(  !(StringUtils.hasText(branchId)|| StringUtils.hasText(id) || StringUtils.hasText(productId)|| StringUtils.hasText(menuId)||ids!=null
				|| StringUtils.hasText(adminUserid) ) ){
			if(LoginUtils.isBranchAdmin()){
				qw.eq("res.branch_id",user.getBranchId());
			}else{
				if(!StringUtils.hasText(productId) && !StringUtils.hasText(linkProjectId)){
					params.put("compete",user.getUserid());
				}else{
					qw.eq("res.branch_id",user.getBranchId());
 				}
			}
		}
		if("branchId".equals(queryScope)){
			qw.eq("res.branch_id",user.getBranchId());
		}
		List<Map<String,Object>>	datas = xmIterationService.selectListMapByWhereWithState(page,qw,params);	//列出XmIteration列表
		
		return Result.ok().setData(datas).setTotal(page.getTotal());
		
		
	}
	
	/***/
	@ApiOperation( value = "新增一条迭代定义信息",notes="addXmIteration,主键如果为空，后台自动生成")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmIteration.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	//@HasQx(value = "xm_core_xmIteration_add",name = "新增迭代计划",moduleId = "xm-project",moduleName = "管理端-项目管理系统")
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addXmIteration(@RequestBody XmIterationVo xmIteration) {

		XmIteration q=new XmIteration();
		User user= LoginUtils.getCurrentUserInfo();
	
		Set<String> words=sensitiveWordService.getSensitiveWord(xmIteration.getIterationName());
		if(words!=null && words.size()>0){
			return Result.error("name-sensitive-word","名字有敏感词"+words+",请修改后再提交");
		}
		words=sensitiveWordService.getSensitiveWord(xmIteration.getRemark());
		if(words!=null && words.size()>0){
			return Result.error("remark-sensitive-word","备注中有敏感词"+words+",请修改后再提交");
		}
		q.setBranchId(user.getBranchId());
		Long count=this.xmIterationService.countByWhere(q);
		xmIteration.setId(this.xmIterationService.createIterationId(count));
		xmIteration.setSeqNo(Long.toString(count+1));
		xmIteration.setCtime(new Date());
		xmIteration.setCuserid(user.getUserid());
		xmIteration.setCusername(user.getUsername());
		xmIteration.setIstatus("0");
		xmIteration.setIphase("0");
		XmProduct xmProductDb=xmProductService.getProductFromCache(xmIteration.getProductId());
		Tips tips=productQxService.checkProductQx(xmProductDb,3,user);
		Result.assertIsFalse(tips); 
		xmIteration.setBranchId(xmProductDb.getBranchId());
	
		if(!StringUtils.hasText(xmIteration.getAdminUserid())){
			xmIteration.setAdminUserid(user.getUserid());
			xmIteration.setAdminUsername(user.getUsername());
		}else if(!xmIteration.getAdminUserid().equals(user.getUserid())){
			boolean isPm=groupService.checkUserIsProductAdm(xmProductDb,user.getUserid());
			if( !isPm ){
 				tips = productQxService.checkProductQx(xmProductDb,3,user,xmIteration.getAdminUserid(),xmIteration.getAdminUsername(),null);
				Result.assertIsFalse(tips);
			}
		}
	
	
	
		notifyMsgService.pushMsg(user,xmIteration.getAdminUserid(),xmIteration.getAdminUsername(),"您成为迭代【"+xmIteration.getIterationName()+"】管理员，请及时跟进。",null);
		xmIterationService.addIteration(xmIteration);
		xmIterationStateService.loadTasksToXmIterationState(xmIteration.getId());
		xmRecordService.addXmIterationRecord(xmIteration.getProductId(),xmIteration.getId(),"迭代-新增","新增迭代"+xmIteration.getIterationName());
		return Result.ok().setData(xmIteration);
	}
	
	
	/***/
	@ApiOperation( value = "删除一条迭代定义信息",notes="delXmIteration,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	})
	//@HasQx(value = "xm_core_xmIteration_del",name = "删除迭代计划",moduleId = "xm-project",moduleName = "管理端-项目管理系统")
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delXmIteration(@RequestBody XmIteration xmIteration){

			if(!StringUtils.hasText(xmIteration.getId())){
				return Result.error("id-0","请上送迭代编号");
			}
			XmIteration iterationDb=this.xmIterationService.selectOneById(xmIteration.getId());
			if(iterationDb==null){
				return Result.error("data-0","迭代不存在");
			}
			User user=LoginUtils.getCurrentUserInfo();
			XmProduct xmProductDb=xmProductService.getProductFromCache(iterationDb.getProductId());
			boolean isPm=groupService.checkUserIsProductAdm(xmProductDb,user.getUserid());
 			if( !isPm ){
  				Tips tips  = productQxService.checkProductQx(xmProductDb,3,user,iterationDb.getAdminUserid(),iterationDb.getAdminUsername(),null);
 				Result.assertIsFalse(tips);
			}


			xmIterationService.deleteByPk(xmIteration);
			xmRecordService.addXmIterationRecord(xmIteration.getProductId(),xmIteration.getId(),"迭代-删除","删除迭代"+iterationDb.getIterationName());

		return Result.ok();
		
	}
	 
	
	/***/
	@ApiOperation( value = "根据主键修改一条迭代定义信息",notes="editXmIteration")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmIteration.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	//@HasQx(value = "xm_core_xmIteration_edit",name = "修改迭代计划",moduleId = "xm-project",moduleName = "管理端-项目管理系统")
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editXmIteration(@RequestBody XmIteration xmIteration) {

		if(!StringUtils.hasText(xmIteration.getId())){
			return Result.error("id-0","请上送迭代编号");
		}
		XmIteration iterationDb=this.xmIterationService.selectOneById(xmIteration.getId());
		if(iterationDb==null){
			return Result.error("data-0","迭代不存在");
		}
		User user=LoginUtils.getCurrentUserInfo();
		XmProduct xmProductDb=xmProductService.getProductFromCache(iterationDb.getProductId());
		boolean isPm=groupService.checkUserIsProductAdm(xmProductDb,user.getUserid());
		if( !isPm ){
			Tips tips = productQxService.checkProductQx(xmProductDb,3,user,iterationDb.getAdminUserid(),iterationDb.getAdminUsername(),null);
			Result.assertIsFalse(tips);
		}
		xmIteration.setAdminUserid(null);//不允许更改负责人
		xmIteration.setAdminUsername(null);
		xmIterationService.updateByPk(xmIteration);
		xmRecordService.addXmIterationRecord(xmIteration.getProductId(),xmIteration.getId(),"迭代-修改","修改迭代"+iterationDb.getIterationName());
		return Result.ok();
	}

	@ApiOperation( value = "批量修改某些字段",notes="")
	@ApiEntityParams( value = XmIteration.class, props={ }, remark = "迭代定义", paramType = "body" )
	@ApiResponses({
			@ApiResponse(code = 200,response=XmIteration.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields( @ApiIgnore @RequestBody Map<String,Object> xmIterationMap) {

			List<String> ids= (List<String>) xmIterationMap.get("$pks");
			if(ids==null || ids.size()==0){
				return Result.error("$pks-0","ids不能为空");
			}

			Set<String> fields=new HashSet<>();
			fields.add("id");
			for (String fieldName : xmIterationMap.keySet()) {
				if(fields.contains(fieldName)){
					return Result.error(fieldName+"-no-edit",fieldName+"不允许修改");
				}
			}
			Set<String> fieldKey=xmIterationMap.keySet().stream().filter(i-> fieldsMap.containsKey(i)).collect(Collectors.toSet());
			fieldKey=fieldKey.stream().filter(i->xmIterationMap.get(i)!=null).collect(Collectors.toSet());

			if(fieldKey.size()<=0) {
				return Result.error("fieldKey-0","没有需要更新的字段");
			}
			XmIteration xmIteration = fromMap(xmIterationMap,XmIteration.class);
			List<XmIteration> xmIterationsDb=xmIterationService.selectListByIds(ids);
			if(xmIterationsDb==null ||xmIterationsDb.size()==0){
				return Result.error("data-0","记录已不存在");
			}


			List<XmIteration> can=new ArrayList<>();
			List<XmIteration> no=new ArrayList<>();
			Map<String,Tips> noTipsMap=new HashMap<>();
			User user = LoginUtils.getCurrentUserInfo();
			XmIteration iterationDb=xmIterationsDb.get(0);
			if(xmIterationsDb.stream().filter(k->!k.getProductId().equals(iterationDb.getProductId())).findAny().isPresent()){
				return Result.error("data-0","批量修改只能修改同一个产品下的迭代记录");
			}
			XmProduct xmProductDb=xmProductService.getProductFromCache(iterationDb.getProductId());


			if(xmIterationMap.containsKey("adminUserid")){
				String adminUserid= (String) xmIterationMap.get("adminUserid");
				String adminUsername= (String) xmIterationMap.get("adminUsername");
				if(StringUtils.hasText(adminUserid)){
	 				Tips tips =productQxService.checkProductQx(xmProductDb,3,user,adminUserid,adminUsername,null);
					Result.assertIsFalse(tips);
				}
			}

			boolean isPm=groupService.checkUserIsProductAdm(xmProductDb,user.getUserid());
			for (XmIteration iterationDb2 : xmIterationsDb) {
				if( !isPm ){
	 				Tips tips =productQxService.checkProductQx(xmProductDb,3,user,iterationDb2.getAdminUserid(),iterationDb2.getAdminUsername(),null);
					if(!tips.isOk()){
						no.add(iterationDb2);
						noTipsMap.put(tips.getMsg(),tips);
					}else{
						can.add(iterationDb2);
					}
				}else{
					can.add(iterationDb2);
				}
			}
			if(can.size()>0){
				xmIterationMap.put("$pks",can.stream().map(i->i.getId()).collect(Collectors.toList()));
				xmIterationService.editSomeFields(xmIterationMap);
				if(xmIterationMap.containsKey("adminUserid")){
					String adminUserid= (String) xmIterationMap.get("adminUserid");
					String adminUsername= (String) xmIterationMap.get("adminUsername");
					if(StringUtils.hasText(adminUserid)){
						for (XmIteration iteration : can) {
							notifyMsgService.pushMsg(user,adminUserid,adminUsername,"您成为迭代【"+iteration.getIterationName()+"】管理员，请及时跟进。",null);
						}
					}

				}
				for (XmIteration canDb : can) {
					String remarks= ChangeLogService.getChangeLogsString(xmIterationMap,canDb);
					xmRecordService.addXmIterationRecord(canDb.getProductId(), canDb.getId(),"迭代-修改", "修改迭代: " + remarks);
				}
			}
			List<String> msgs=new ArrayList<>();
			if(can.size()>0){
				msgs.add(String.format("成功更新以下%s条数据",can.size()));
			}
			if(no.size()>0){
				msgs.add(String.format("以下%s个数据无权限更新，原因【%s】",no.size(),noTipsMap.keySet().stream().collect(Collectors.joining(";"))));
			}
			if(can.size()>0){
				return Result.ok(msgs.stream().collect(Collectors.joining()));
			}else {
				return Result.error(msgs.stream().collect(Collectors.joining()));
			} 
		
	}

	//@HasQx(value = "xm_core_xmIteration_loadTasksToXmIterationState",name = "计算迭代的bug、工作量、人员投入、进度等",moduleId = "xm-project",moduleName = "管理端-项目管理系统")
	@RequestMapping(value="/loadTasksToXmIterationState",method=RequestMethod.POST)
	public Result loadTasksToXmIterationState(@RequestBody XmIteration xmIteration) {

			if(!StringUtils.hasText(xmIteration.getId())){
				return Result.error("id-0","请上送迭代编号");
			}
			if(xmIteration==null || StringUtils.isEmpty(xmIteration.getId())) {
				return Result.error("请输入迭代编号id");
			}else {
				XmIteration iterationDb=this.xmIterationService.selectOneObject(xmIteration);
				if(iterationDb==null){
					return Result.error("data-0","迭代不存在");
				}
				xmIterationService.loadTasksToXmIterationState(xmIteration.getId());
				xmRecordService.addXmIterationRecord(xmIteration.getProductId(),xmIteration.getId(),"迭代-汇总","汇总计算迭代数据"+iterationDb.getIterationName());

			}
		return Result.ok();
		
	} 
}
