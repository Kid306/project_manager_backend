package com.xm.core.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mdp.audit.log.client.annotation.AuditLog;
import com.mdp.audit.log.client.annotation.OperType;
import com.mdp.core.entity.Result;
import com.mdp.core.entity.Tips;
import com.mdp.core.err.BizException;
import com.mdp.core.query.QueryTools;
import com.mdp.core.utils.BaseUtils;
import com.mdp.core.utils.EntityUtils;
import com.mdp.core.utils.NumberUtil;
import com.mdp.core.utils.RequestUtils;
import com.mdp.msg.client.PushNotifyMsgService;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.sensitive.SensitiveWordService;
import com.mdp.swagger.ApiEntityParams;
import com.xm.core.entity.*;
import com.xm.core.service.*;
import com.xm.core.service.push.XmPushMsgService;
import com.xm.core.vo.XmQuestionVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * url编制采用rest风格,如对XM.xm_question xm_question的操作有增删改查,对应的url分别为:<br>
 *  新增: xm/xmQuestion/add <br>
 *  查询: xm/xmQuestion/list<br>
 *  模糊查询: xm/xmQuestion/listKey<br>
 *  修改: xm/xmQuestion/edit <br>
 *  删除: xm/xmQuestion/del<br>
 *  批量删除: xm/xmQuestion/batchDel<br>
 * 组织 com.qqkj  顶级模块 oa 大模块 xm 小模块 <br>
 * 实体 XmQuestion 表 XM.xm_question 当前主键(包括多主键): id; 
 ***/
@RestController("xm.core.xmQuestionController")
@RequestMapping(value="/xm/core/xmQuestion")
@Api(tags={"xm_question操作接口"})
public class XmQuestionController {
	
	static Log logger=LogFactory.getLog(XmQuestionController.class);
	
	@Autowired
	private XmQuestionService xmQuestionService;
	 
	@Autowired
	private XmPushMsgService xmPushMsgService;

	@Autowired
	private XmRecordService xmRecordService;

	@Autowired
	XmQuestionHandleService xmQuestionHandleService;

	@Autowired
	SensitiveWordService sensitiveWordService;

	@Autowired
	XmProductService productService;

	@Autowired
	XmProjectService projectService;

	@Autowired
	XmGroupService groupService;


	@Autowired
	XmProductQxService productQxService;


	@Autowired
	XmProjectQxService projectQxService;


	@Autowired
	PushNotifyMsgService notifyMsgService;

	Map<String,Object> fieldsMap = BaseUtils.toMap(new XmQuestion());
	
	@ApiOperation( value = "查询xm_question信息列表",notes="listXmQuestion,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
 	@ApiEntityParams(XmQuestion.class)
	@ApiResponses({
		@ApiResponse(code = 200,response= XmQuestion.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listXmQuestion(@ApiIgnore @RequestParam Map<String,Object> params){
		 
		RequestUtils.transformArray(params, "ids");
		RequestUtils.transformArray(params, "menuIds");
		RequestUtils.transformArray(params, "tagIdList");		
		IPage page=QueryTools.initPage(params);
		User user = LoginUtils.getCurrentUserInfo();
		if(LoginUtils.isBranchAdmin()){
			params.put("pbranchId",user.getBranchId());
		}else {
			String id= (String) params.get("id");
			String menuId= (String) params.get("menuId");
			Object ids=  params.get("ids");
			Object menuIds=  params.get("menuIds");
			String productId= (String) params.get("productId");
			String myUserid= (String) params.get("myUserid");
			String projectId= (String) params.get("projectId");
			String linkIterationId= (String) params.get("linkIterationId");
			String casedbId= (String) params.get("casedbId");
			String planId= (String) params.get("planId");
			String funcId= (String) params.get("funcId");
			String hisHandlerUserid= (String) params.get("hisHandlerUserid");


			if(   !( StringUtils.hasText(myUserid) ||StringUtils.hasText(id) || StringUtils.hasText(menuId) || StringUtils.hasText(productId)|| StringUtils.hasText(projectId)||menuIds!=null||ids!=null|| StringUtils.hasText(casedbId)|| StringUtils.hasText(linkIterationId)|| StringUtils.hasText(planId)|| StringUtils.hasText(funcId) || StringUtils.hasText(hisHandlerUserid)) ){
				params.put("compete",user.getUserid());
			}
		}

		QueryWrapper<XmQuestion> qw=QueryTools.initQueryWrapper(XmQuestion.class,params);
		List<Map<String,Object>>	datas = xmQuestionService.selectListMapByWhere(page,qw,params);	//列出XmQuestion列表
		return Result.ok().setData(datas).setTotal(page.getTotal());
		
	}


	@RequestMapping(value="/getXmQuestionAttDist",method=RequestMethod.GET)
	public Result getXmQuestionAttDist(@ApiIgnore @RequestParam Map<String,Object> params){
		User user=LoginUtils.getCurrentUserInfo();
		params.put("pbranchId",user.getBranchId());
		IPage page=QueryTools.initPage(params);
		List<Map<String,Object>> datas= this.xmQuestionService.getXmQuestionAttDist(params);
		return Result.ok("ok","成功").setData(datas).setTotal(page.getTotal());
	}

	@RequestMapping(value="/getXmQuestionAgeDist",method=RequestMethod.GET)
	public Result getXmQuestionAgeDist(@ApiIgnore @RequestParam Map<String,Object> params){
		User user=LoginUtils.getCurrentUserInfo();
		params.put("pbranchId",user.getBranchId());
		IPage page=QueryTools.initPage(params);
		QueryWrapper<XmQuestion> qw=QueryTools.initQueryWrapper(XmQuestion.class,params);
		List<Map<String,Object>> datas= this.xmQuestionService.getXmQuestionAgeDist(page,qw,params);
		return Result.ok("ok","成功").setData(datas).setTotal(datas.size());
	}
	@RequestMapping(value="/getXmQuestionRetestDist",method=RequestMethod.GET)
	public Result getXmQuestionRetestDist(@ApiIgnore @RequestParam Map<String,Object> params){
		User user=LoginUtils.getCurrentUserInfo();
		params.put("pbranchId",user.getBranchId());
		IPage page=QueryTools.initPage(params);
		QueryWrapper<XmQuestion> qw=QueryTools.initQueryWrapper(XmQuestion.class,params);
		List<Map<String,Object>> datas= this.xmQuestionService.getXmQuestionRetestDist(page,qw,params);
		return Result.ok("ok","成功").setData(datas).setTotal(page.getTotal());
	}
	@RequestMapping(value="/getXmQuestionSort",method=RequestMethod.GET)
	public Result getXmQuestionSort(@ApiIgnore @RequestParam Map<String,Object> params){
		User user=LoginUtils.getCurrentUserInfo();
		params.put("pbranchId",user.getBranchId());
		IPage page=QueryTools.initPage(params);
		QueryWrapper<XmQuestion> qw=QueryTools.initQueryWrapper(XmQuestion.class,params);
		List<Map<String,Object>> datas= this.xmQuestionService.getXmQuestionSort(page,qw,params);
		return Result.ok().setData(datas).setTotal(page.getTotal());
	}

	@ApiOperation( value = "新增一条xm_question信息",notes="addXmQuestion,主键如果为空，后台自动生成")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmQuestion.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	//@HasQx(value = "xm_core_xmQuestion_add",name = "新增bug",moduleId = "xm-project",moduleName = "管理端-项目管理系统")
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addXmQuestion(@RequestBody XmQuestionVo xmQuestionVo) {

			if(!StringUtils.hasText(xmQuestionVo.getProjectId())){
				return Result.error("项目编号projectId必传");
				
			}
			User user=LoginUtils.getCurrentUserInfo();
			if(!StringUtils.hasText(xmQuestionVo.getQtype())){
				xmQuestionVo.setQtype("1");
			}
			xmQuestionVo.setCreateTime(new Date());
			xmQuestionVo.setCreateUserid(user.getUserid());
			xmQuestionVo.setCreateUsername(user.getUsername());
			xmQuestionVo.setLtime(new Date());
			if(xmQuestionVo.getEndTime()==null){
				xmQuestionVo.setEndTime(DateUtils.addDays(xmQuestionVo.getCreateTime(),7));
			}
			if(!StringUtils.hasText(xmQuestionVo.getHandlerUserid())){
				xmQuestionVo.setHandlerUserid(user.getUserid());
				xmQuestionVo.setHandlerUsername(user.getUsername());
			}
			Set<String> words=sensitiveWordService.getSensitiveWord(xmQuestionVo.getName());
			if(words!=null && words.size()>0){
				return Result.error("name-sensitive-word","名字有敏感词"+words+",请修改后再提交");
			}
			words=sensitiveWordService.getSensitiveWord(xmQuestionVo.getRemarks());
			if(words!=null && words.size()>0){
				return Result.error("remark-sensitive-word","备注中有敏感词"+words+",请修改后再提交");
			}
 			Tips tips=checkOneQx(xmQuestionVo.getProjectId(),xmQuestionVo.getProductId());
			Result.assertIsFalse(tips);
			if(StringUtils.hasText(xmQuestionVo.getProjectId())){
				XmProject xmProject=projectService.getProjectFromCache(xmQuestionVo.getProjectId() );
				xmQuestionVo.setPbranchId(xmProject.getBranchId());
			}else if(StringUtils.hasText(xmQuestionVo.getProductId())){
				XmProduct xmProduct=productService.getProductFromCache(xmQuestionVo.getProductId() );
				xmQuestionVo.setPbranchId(xmProduct.getBranchId());
			}
			if(StringUtils.isEmpty(xmQuestionVo.getId())) {
				xmQuestionVo.setId(xmQuestionService.createKey("id"));
			}else{
				if(this.xmQuestionService.count(Wrappers.lambdaQuery(XmQuestion.class).eq(XmQuestion::getId,xmQuestionVo.getId()))>0){
					return Result.error("id-exists","编号【%s】重复",xmQuestionVo.getId());
				}
			}
			xmQuestionService.addQuestion(xmQuestionVo);
			xmRecordService.addXmBugRecord(xmQuestionVo.getProductId(),xmQuestionVo.getProjectId(),xmQuestionVo.getId(), xmQuestionVo.getMenuId(),"缺陷-新增",String.format("【%s】创建缺陷【%s】",user.getUsername(),xmQuestionVo.getName()));
			if(!StringUtils.isEmpty(xmQuestionVo.getHandlerUserid())) {
				notifyMsgService.pushMsg(user,xmQuestionVo.getHandlerUserid(),xmQuestionVo.getHandlerUsername(),"您有新的bug【"+xmQuestionVo.getName()+"】需要处理，请尽快修复！",null);
				xmPushMsgService.pushPrichatMsgToIm(user.getBranchId(), user.getUserid(), user.getUsername(), xmQuestionVo.getHandlerUserid(),xmQuestionVo.getHandlerUsername(), "【"+user.getUsername()+"】创建bug【"+xmQuestionVo.getName()+"】并指派给"+xmQuestionVo.getHandlerUsername());
			}
		return Result.ok().setData(xmQuestionVo);
	}
	 
	
	@ApiOperation( value = "根据主键修改一条xm_question信息",notes="editXmQuestion")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmQuestion.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	//@HasQx(value = "xm_core_xmQuestion_edit",name = "修改bug",moduleId = "xm-project",moduleName = "管理端-项目管理系统")
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editXmQuestion(@RequestBody XmQuestionVo xmQuestionVo) {



		if(!StringUtils.hasText(xmQuestionVo.getId())){
			return Result.error("id-0","编号不能为空");
		}
		XmQuestion xmQuestionDb=this.xmQuestionService.selectOneById(xmQuestionVo.getId());
		Tips tips=checkOneQx(xmQuestionDb.getProjectId(),xmQuestionDb.getProductId());
		Result.assertIsFalse(tips);
		User user=LoginUtils.getCurrentUserInfo();
		xmQuestionService.updateQuestion(xmQuestionVo);
		if(!StringUtils.isEmpty(xmQuestionVo.getHandlerUserid())) {
			xmPushMsgService.pushPrichatMsgToIm(user.getBranchId(), user.getUserid(), user.getUsername(), xmQuestionVo.getHandlerUserid(),xmQuestionVo.getHandlerUsername(), user.getUsername()+"修改bug【"+xmQuestionVo.getName()+"】");
		}
		return Result.ok().setData(xmQuestionVo);
	}
	
	@ApiOperation( value = "根据主键修改一条xm_question信息",notes="editXmQuestion")
	@ApiResponses({
			@ApiResponse(code = 200,response=XmQuestion.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	//@HasQx(value = "xm_core_xmQuestion_editStatus",name = "修改bug状态",moduleId = "xm-project",moduleName = "管理端-项目管理系统")
	@RequestMapping(value="/editStatus",method=RequestMethod.POST)
	public Result editStatus(@RequestBody XmQuestion xmQuestion) {


			if(!StringUtils.hasText(xmQuestion.getId())){
				return Result.error("id-0","编号不能为空");
			}

			XmQuestion xmQuestionDb=this.xmQuestionService.selectOneById(xmQuestion.getId());
			Tips  tips=checkOneQx(xmQuestionDb.getProjectId(),xmQuestionDb.getProductId());
			Result.assertIsFalse(tips);
			User user=LoginUtils.getCurrentUserInfo();
			xmQuestionService.updateSomeFieldByPk(xmQuestion);
 			if(!StringUtils.isEmpty(xmQuestion.getHandlerUserid())) {
				xmPushMsgService.pushPrichatMsgToIm(user.getBranchId(), user.getUserid(), user.getUsername(), xmQuestion.getHandlerUserid(),xmQuestion.getHandlerUsername(), user.getUsername()+"修改bug【"+xmQuestion.getName()+"】状态");
			}
			
		return Result.ok();
		
	}


	/***/
	@ApiOperation( value = "根据主键修改一条项目菜单表信息",notes="editSomeFields")
	@ApiResponses({
			@ApiResponse(code = 200,response= XmMenu.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	//@HasQx(value = "xm_core_xmQuestion_editSomeFields",name = "修改bug的某些字段",moduleId = "xm-project",moduleName = "管理端-项目管理系统")
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields(@RequestBody Map<String,Object> xmQuestionMap) {

			User user=LoginUtils.getCurrentUserInfo();
			List<String> ids= (List<String>) xmQuestionMap.get("$pks");

			if(ids==null || ids.size()==0){
				Result.error("ids-0","ids不能为空");
			}
 			List<XmQuestion> xmQuestionsDb=xmQuestionService.selectListByIds(ids);
			if(xmQuestionsDb==null ||xmQuestionsDb.size()==0){
				Result.error("bugs-0","该bug已不存在");
			}

			List<XmQuestion> canOper=new ArrayList<>();

			List<XmQuestion> noOper=new ArrayList<>();

			Map<String,Tips> noOperTips=new HashMap<>();

			if(xmQuestionMap.containsKey("handlerUserid")){
				this.checkQx(xmQuestionsDb,canOper,noOper,noOperTips,2);
			}else{
				this.checkQx(xmQuestionsDb,canOper,noOper,noOperTips,1);
			}
			if(canOper.size()>0){
				List<String> pks=canOper.stream().map(k->k.getId()).collect(Collectors.toList());
				xmQuestionMap.put("$pks",pks);

				Set<String> fieldKey=xmQuestionMap.keySet().stream().filter(i->fieldsMap.containsKey(i)).collect(Collectors.toSet());
				fieldKey=fieldKey.stream().filter(i->xmQuestionMap.get(i)!=null).collect(Collectors.toSet());
				if(fieldKey.size()>0){
					Set<String> fields=new HashSet<>();
					for (String fieldName : xmQuestionMap.keySet()) {
						if(fields.contains(fieldName)){
							return Result.error(fieldName+"-no-edit",fieldName+"不允许修改");
						}
					}
					if(StringUtils.hasText(xmQuestionsDb.get(0).getProductId()) && xmQuestionMap.containsKey("handlerUserid")){
						String handlerUserid= (String) xmQuestionMap.get("handlerUserid");
						String handlerUsername= (String) xmQuestionMap.get("handlerUsername");
						XmQuestion xmQuedb=canOper.get(0);
		 				Tips tips1=productQxService.checkProductScopeQx(productService.getProductFromCache(xmQuedb.getProductId()),1,user,handlerUserid,handlerUsername,null);
						if(!tips1.isOk()){
							if(StringUtils.hasText(xmQuedb.getProjectId())){
				 				tips1=projectQxService.checkProjectScopeQx(projectService.getProjectFromCache(xmQuedb.getProjectId()),1,user,handlerUserid,handlerUsername,null);
							}
							Result.assertIsFalse(tips1);
						}
					}

					String bugStatus= (String) xmQuestionMap.get("bugStatus");
					if(bugStatus!=null){
						if("8".equals(bugStatus)){//关闭缺陷就把结束时间定死
							xmQuestionMap.put("endTime",new Date());
						}
					}
					xmQuestionService.editSomeFields(xmQuestionMap);
					if(xmQuestionMap.containsKey("budgetWorkload")){
						xmQuestionService.batchUpdateBudgetWorkloadAndRate(pks, NumberUtil.getBigDecimal(xmQuestionMap.get("budgetWorkload"), BigDecimal.ZERO));
					}
					String remarks= (String) xmQuestionMap.get("remarks");
					String description= (String) xmQuestionMap.get("description");
					String handlerUsername= (String) xmQuestionMap.get("handlerUsername");

					List<XmQuestionHandle> handles=new ArrayList<>();
					Map<String,Object> map=new HashMap<>();
					map.putAll(xmQuestionMap);
					map.remove("$pks");
					for (XmQuestion qdb : canOper) {
						XmQuestion xmQuestionVo=new XmQuestion();
						Map<String,Object> m2=BaseUtils.toMap(qdb);
						m2.putAll(map);
						xmQuestionVo=BaseUtils.fromMap(m2,XmQuestion.class);
						XmQuestionHandle handle=new XmQuestionHandle();
						if(StringUtils.hasText(remarks)){
							handle.setReceiptMessage("【"+user.getUsername()+"】修改缺陷处理意见为【"+xmQuestionVo.getRemarks()+"】,旧值【"+qdb.getRemarks()+"】");
						}else if(StringUtils.hasText(handlerUsername)){
							handle.setReceiptMessage("【"+user.getUsername()+"】将缺陷重新指派，负责人由【"+qdb.getHandlerUsername()+"】 变更为 【"+handlerUsername+"】");
							xmRecordService.addXmBugRecord(qdb.getProductId(),qdb.getProjectId(),qdb.getId(),qdb.getMenuId(),"缺陷-指派",String.format("【%s】将缺陷指派给【%s】",user.getUsername(),handlerUsername));
							notifyMsgService.pushMsg(user,xmQuestionVo.getHandlerUserid(),xmQuestionVo.getHandlerUsername(),user.getUsername()+"将bug【"+xmQuestionVo.getName()+"】指派给您，请及时跟进。",null);

						}else{
							handle.setReceiptMessage("【"+user.getUsername()+"】修改缺陷信息："+ChangeLogService.getChangeLogsString(xmQuestionMap,qdb));
						}

						handle.setHandleStatus(xmQuestionVo.getBugStatus());
						handle.setCreateTime(new Date());
						handle.setReceiptTime(new Date());
						handle.setHandlerUserid(qdb.getHandlerUserid());
						handle.setHandlerUsername(qdb.getHandlerUsername());
						handle.setLastUpdateTime(new Date());
						handle.setHandleSolution(xmQuestionVo.getSolution());
						handle.setQuestionId(qdb.getId());
						handle.setTargetUserid(xmQuestionVo.getHandlerUserid());
						handle.setTargetUsername(xmQuestionVo.getHandlerUsername());
						handle.setOperUserid(user.getUserid());
						handle.setOperUsername(user.getUsername());
						handle.setOldStatus(qdb.getBugStatus());
						handle.setId(this.xmQuestionHandleService.createKey("id"));
						handles.add(handle);
					}

					xmQuestionHandleService.batchAddAsync(handles);

				}
			}


			List<String> msgs=new ArrayList<>();
			if(canOper.size()>0){
				for (XmQuestion xmQuestion : canOper) {
					noOperTips.remove(xmQuestion.getId());
				}
				msgs.add(String.format("修改了%s个缺陷。",canOper.size()));
			}
			if(noOper.size()>0){
				Set<String> noDelTips2=new HashSet<>();
				for (XmQuestion xmQuestion : noOper) {
	 				Tips tips1=noOperTips.get(xmQuestion.getId());
					noDelTips2.add(tips1.getMsg());
				}
				msgs.add(String.format("其中%s个缺陷，无权限修改。原因【%s】",noOper.size(),noDelTips2.stream().collect(Collectors.joining(";"))));
			}
			if(canOper.size()>0){
				return Result.ok(msgs.stream().collect(Collectors.joining()));
			}else{
				return Result.error(msgs.stream().collect(Collectors.joining()));
			} 
		
	}

	@ApiOperation( value = "根据主键列表批量删除xm_question信息",notes="batchDelXmQuestion,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelXmQuestion(@RequestBody List<XmQuestion> xmQuestions) {

			User user=LoginUtils.getCurrentUserInfo();
			if(xmQuestions==null || xmQuestions.size()==0){
				return Result.error("p-0","参数不能为空");
			}
			List<XmQuestion> xmQuestionsDb=xmQuestionService.selectListByIds(xmQuestions.stream().map(i->i.getId()).collect(Collectors.toList()));
			if(xmQuestionsDb==null || xmQuestionsDb.size()==0){
				return Result.error("data-0","所有数据已不存在");
			}
			List<XmQuestion> canOper=new ArrayList<>();

			List<XmQuestion> noOper=new ArrayList<>();

			Map<String,Tips> noOperTips=new HashMap<>();

			this.checkQx(xmQuestionsDb,canOper,noOper,noOperTips,0);

			if(canOper.size()>0){
				xmQuestionService.batchDelete(canOper);
			}

			List<String> msgs=new ArrayList<>();
			if(canOper.size()>0){
				for (XmQuestion xmQuestion : canOper) {
					noOperTips.remove(xmQuestion.getId());
				}
				msgs.add(String.format("删除了%s个缺陷。",canOper.size()));
				for (XmQuestion r : canOper) {
					xmRecordService.addXmBugRecord(r.getProductId(),r.getProjectId(),r.getId(),r.getMenuId(),"缺陷-删除",String.format("【%s】删除缺陷【%s】",user.getUsername(),r.getName()));
				}
			}
			if(noOper.size()>0){
				Set<String> noDelTips2=new HashSet<>();
				for (XmQuestion xmQuestion : noOper) {
	 				Tips tips1=noOperTips.get(xmQuestion.getId());
					noDelTips2.add(tips1.getMsg());
				}
				msgs.add(String.format("其中%s个缺陷，无权限删除。原因【%s】",noOper.size(),noDelTips2.stream().collect(Collectors.joining(";"))));
			}
			if(canOper.size()>0){
				return Result.ok(msgs.stream().collect(Collectors.joining()));
			}else{
				return Result.error(msgs.stream().collect(Collectors.joining()));
			} 
		
	}


	public Tips checkOneQx(String projectId,String productId){
		Tips tips1=new Tips("成功");
		User user=LoginUtils.getCurrentUserInfo();
		if(StringUtils.hasText(projectId)){
			XmProject xmProject=projectService.getProjectFromCache(projectId );
			tips1 = this.projectQxService.checkProjectQx(xmProject,1,user);
		}
		if((StringUtils.hasText(projectId) && !tips1.isOk()) || !StringUtils.hasText(projectId)){
			if(StringUtils.hasText(productId)){
				XmProduct xmProduct=productService.getProductFromCache(productId);
 				tips1=this.productQxService.checkProductQx(xmProduct,1,user);
			}
		}
		return tips1;
	}

	/**
	 *
	 * @param xmQuestionsDb
	 * @param canOper
	 * @param noOper
	 * @param noOperTips
	 * @param opType  0-删除，1修改其它信息，2指派新负责人
	 */
	public void checkQx(List<XmQuestion> xmQuestionsDb, List<XmQuestion> canOper, List<XmQuestion> noOper, Map<String,Tips> noOperTips,int opType/**0-删除，1修改其它信息，2指派新负责人**/){
		User user=LoginUtils.getCurrentUserInfo();
		/**
		 * 如果有测试计划，有产品编号，走产品团队判断权限
		 * 如果未通过，继续走项目权限判断
		 */
		Map<String,List<XmQuestion>> productsMap=new HashMap<>();//有产品的数据
		Map<String,List<XmQuestion>> projectsMap=new HashMap<>();//没有产品、但有项目的数据
		for (XmQuestion xmQuestion : xmQuestionsDb) {
			if(StringUtils.hasText(xmQuestion.getProductId())){
				List<XmQuestion> datas=productsMap.get(xmQuestion.getProductId());
				if(datas==null){
					datas=new ArrayList<>();
					datas.add(xmQuestion);
					productsMap.put(xmQuestion.getProductId(),datas);
				}else {
					datas.add(xmQuestion);
				}
			}else if(StringUtils.hasText(xmQuestion.getProjectId())) {
				List<XmQuestion> datas=projectsMap.get(xmQuestion.getProjectId());
				if(datas==null){
					datas=new ArrayList<>();
					datas.add(xmQuestion);
					projectsMap.put(xmQuestion.getProjectId(),datas);
				}else {
					datas.add(xmQuestion);
				}
			}
		}

		List<XmQuestion> productNoDel=new ArrayList<>();
		
		if(productsMap.size()>0){
			for (String productId : productsMap.keySet()) {
				XmProduct xmProduct=productService.getProductFromCache(productId);
 				Tips tips1=productQxService.checkProductQx(xmProduct,1,user);
				if(!tips1.isOk()){
					productNoDel.addAll(productsMap.get(productId));
					for (XmQuestion xmQuestion : productsMap.get(productId)) {
						noOperTips.put(xmQuestion.getId(),tips1);
					}
					productsMap.remove(productId);
				}else{
					List<XmQuestion> questions=productsMap.get(productId);
					if(groupService.checkUserIsProductAdm(xmProduct,user.getUserid())){
						if(opType==0){//删除，产品经理有百分百权限
							canOper.addAll(questions);
						}else if(opType==1){//修改其它信息，产品经理有百分百权限
							canOper.addAll(questions);
						}else if(opType==2){//重新指派，要检查被指派人是否在项目组
							for (XmQuestion question : questions) {
				 				tips1=productQxService.checkProductScopeQx(xmProduct,1,question.getHandlerUserid(),question.getHandlerUsername(),null);
								if(!tips1.isOk()){
									productNoDel.add(question);
									noOperTips.put(question.getId(),tips1);
								}else {
									canOper.add(question);
								}
							}
						}

					}else{
						for (XmQuestion question : questions) {
							if(opType==0){
				 				tips1=productQxService.checkProductQx(xmProduct,1,user,question.getCreateUserid(),question.getCreateUsername(),null);
							}else if(opType==1){
				 				tips1=productQxService.checkProductQx(xmProduct,1,user,question.getHandlerUserid(),question.getHandlerUsername(),null);
							}else if(opType==2){
				 				tips1=productQxService.checkProductQx(xmProduct,1,user,question.getHandlerUserid(),question.getHandlerUsername(),null);

							}
							if(!tips1.isOk()){
								productNoDel.add(question);
								noOperTips.put(question.getId(),tips1);
							}else {
								canOper.add(question);
							}
						}
					}
				}
			}
		}
		for (XmQuestion xmQuestion : productNoDel) {
			if(StringUtils.hasText(xmQuestion.getProjectId())){
				List<XmQuestion> ques=projectsMap.get(xmQuestion.getProjectId());
				if(ques!=null){
					if(!ques.stream().filter(k->k.getId().equals(xmQuestion.getId())).findAny().isPresent()){
						ques.add(xmQuestion);
					}
				}else{
					ques=new ArrayList<>();
					ques.add(xmQuestion);
					projectsMap.put(xmQuestion.getProjectId(),ques);
				}

			}else{
				noOper.add(xmQuestion);
			}
		}
		if(projectsMap.size()>0){
			for (String projectId : projectsMap.keySet()) {
				XmProject xmProject=projectService.getProjectFromCache(projectId);
 				Tips tips1=projectQxService.checkProjectQx(xmProject,1,user);
				if(!tips1.isOk()){
					noOper.addAll(projectsMap.get(projectId));
					for (XmQuestion xmQuestion : projectsMap.get(projectId)) {
						noOperTips.put(xmQuestion.getId(),tips1);
					}
					projectsMap.remove(projectId);
				}else{
					List<XmQuestion> questions=projectsMap.get(projectId);
					if(groupService.checkUserIsProjectAdm(xmProject,user.getUserid())){
						if(opType==0){//删除，产品经理有百分百权限
							canOper.addAll(questions);
						}else if(opType==1){//修改其它信息，产品经理有百分百权限
							canOper.addAll(questions);
						}else if(opType==2){//重新指派，要检查被指派人是否在项目组
							for (XmQuestion question : questions) {
				 				tips1=projectQxService.checkProjectScopeQx(xmProject,1,question.getHandlerUserid(),question.getHandlerUsername(),null);
								if(!tips1.isOk()){
									noOper.add(question);
 									noOperTips.put(question.getId(),tips1);
								}else {
									canOper.add(question);
								}
							}
						}

					}else {
						for (XmQuestion question : questions) {
							if (opType == 0) {
				 				tips1 = projectQxService.checkProjectQx( xmProject, 1, user, question.getCreateUserid(), question.getCreateUsername(), null);
							} else if (opType == 1) {
				 				tips1 = projectQxService.checkProjectQx( xmProject, 1, user, question.getHandlerUserid(), question.getHandlerUsername(), null);
							} else if (opType == 2) {
				 				tips1 = projectQxService.checkProjectQx( xmProject, 1, user, question.getHandlerUserid(), question.getHandlerUsername(), null);

							}
							if (!tips1.isOk()) {
								noOper.add(question);
								noOperTips.put(question.getId(), tips1);
							} else {
								canOper.add(question);
							}
						}
					}
				}
			}
		}
	}

	/**
	 * 流程审批过程中回调该接口，更新业务数据
	 * 如果发起流程时上送了restUrl，则无论流程中是否配置了监听器都会在流程发生以下事件时推送数据过来
	 * eventName: PROCESS_STARTED 流程启动完成 全局
	 *            PROCESS_COMPLETED 流程正常结束 全局
	 *            PROCESS_CANCELLED 流程删除 全局
	 *            create 人工任务启动
	 *            complete 人工任务完成  
	 *            assignment 人工任务分配给了具体的人
	 *            delete 人工任务被删除
	 *            
	 * 其中 create/complete/assignment/delete事件是需要在模型中人工节点上配置了委托代理表达式 ${taskBizCallListener}才会推送过来。
	 * 在人工任务节点上配置 任务监听器  建议事件为 complete,其它assignment/create/complete/delete也可以，一般建议在complete,委托代理表达式 ${taskBizCallListener}
	 * 
	 * @param flowVars {flowBranchId,agree,procInstId,assignee,actId,taskName,mainTitle,branchId,bizKey,commentMsg,eventName,modelKey} 等 
	 * @return 如果tips.isOk==false，将影响流程提交
	 **/
	@AuditLog(firstMenu="办公平台",secondMenu="项目问题管理",func="processApprova",funcDesc="项目问题审核",operType= OperType.UPDATE)
	@RequestMapping(value="/processApprova",method=RequestMethod.POST)
	public Result processApprova( @RequestBody Map<String,Object> flowVars){
			this.xmQuestionService.processApprova(flowVars);
			return Result.ok();
	}


}
