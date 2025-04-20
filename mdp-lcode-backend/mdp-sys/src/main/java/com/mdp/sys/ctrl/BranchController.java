package com.mdp.sys.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.entity.Tips;
import com.mdp.core.err.BizException;
import com.mdp.core.query.QueryTools;
import com.mdp.qx.HasQx;
import com.mdp.qx.HasRole;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.sys.entity.Branch;
import com.mdp.sys.entity.Dept;
import com.mdp.sys.entity.Role;
import com.mdp.sys.entity.UserTpaInvite;
import com.mdp.sys.pub.service.InviteCacheService;
import com.mdp.sys.service.BranchService;
import com.mdp.sys.service.DeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;
import java.util.stream.Collectors;

import static com.mdp.core.utils.BaseUtils.fromMap;
import static com.mdp.core.utils.BaseUtils.toMap;


/**
 * url编制采用rest风格,如对ADMIN.sys_branch 管理端机构表（机构下面若干部门）的操作有增删改查,对应的url分别为:<br>
 *  新增: sys/branch/add <br>
 *  查询: sys/branch/list<br>
 *  模糊查询: sys/branch/listKey<br>
 *  修改: sys/branch/edit <br>
 *  删除: sys/branch/del<br>
 *  批量删除: sys/branch/batchDel<br>
 * 组织 com.qqkj  顶级模块 mdp 大模块 sys 小模块 <br>
 * 实体 Branch 表 ADMIN.sys_branch 当前主键(包括多主键): id; 
 ***/
@RestController("mdp.sys.branchController")
@RequestMapping(value="/*/sys/branch")
@Api(tags={"管理端机构表（机构下面若干部门）操作接口"})
public class BranchController {
	
	static Log logger=LogFactory.getLog(BranchController.class);
	
	@Autowired
	private BranchService branchService;
	@Autowired
	private DeptService deptService;

	@Autowired
	InviteCacheService inviteCacheService;


	Map<String,Object> fieldsMap = toMap(new Branch());
	
	@ApiOperation( value = "查询管理端机构表（机构下面若干部门）信息列表",notes="listBranch,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
	@ApiResponses({
		@ApiResponse(code = 200,response= Branch.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},pageInfo:{total:总记录数},data:[数据对象1,数据对象2,...]}")
	}) 
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listBranch( @ApiIgnore @RequestParam Map<String,Object> params){
		 
		
		IPage page=QueryTools.initPage(params);
		QueryWrapper<Branch> qw= QueryTools.initQueryWrapper(Branch.class,params);
 		List<Map<String,Object>>	branchList = branchService.selectListMapByWhere(page,qw,params);
		return Result.ok().setData(branchList).setTotal(page.getTotal());
		
	}
	@ApiResponses({
			@ApiResponse(code = 200,response= Role.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},pageInfo:{total:总记录数},data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/getBranch",method=RequestMethod.GET)
	public Result getBranch( @RequestParam String branchId){
		return Result.ok("","成功").setData(this.branchService.selectOneById(branchId));
	}
	/**
	 * 查询机构 根据关键字 免权限免userToken
	 * @cdate 2020/2/13 17:12
	 * @author LinYuKun
	 * @return
	 */
	@RequestMapping(value="/listBranchNoAuth",method=RequestMethod.GET)
	public Result listBranchNoAuth(@ApiIgnore @RequestParam Map<String,Object> params){
		 
		
		IPage page=QueryTools.initPage(params);
		QueryWrapper<Branch> qw= QueryTools.initQueryWrapper(Branch.class,params);
		try {
			List<Map<String,Object>> branchList =branchService.selectListMapByWhere(page,qw,params);
			return Result.ok().setData(branchList).setTotal(page.getTotal());
		}catch(Exception e) {
			logger.error("",e);
            return Result.error(e);
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
	@RequestMapping(value="/processApprova",method=RequestMethod.POST)
	public Result processApprova( @RequestBody Map<String,Object> flowVars){
		
		
		  
		try{ 
			
			branchService.processApprova(flowVars);
			logger.debug("procInstId====="+flowVars.get("procInstId"));
			return Result.ok();
		}catch (BizException e) { 
			logger.error("执行异常",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}  
	}
	
	/***/
	@ApiOperation( value = "新增一条管理端机构表（机构下面若干部门）信息",notes="addBranch,主键如果为空，后台自动生成")
	@ApiResponses({
		@ApiResponse(code = 200,response=Branch.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@HasRole
 	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addBranch(@RequestBody Branch branch) {
		
		
		  
		try{
			if(StringUtils.isEmpty(branch.getId())) {
				branch.setId(branchService.createKey("id"));
			} else {  
				Branch branchQuery=new Branch(branch.getId());
				if(branchService.countByWhere(branchQuery)>0) { 
					return Result.error("id-exists","机构编码已存在");
				};
				branchQuery=new Branch();
				branchQuery.setBranchName(branch.getBranchName());
				if(branchService.countByWhere(branchQuery)>0) { 
					return Result.error("branch-name-exists","机构名称已存在");

				};
			}
			branchService.insert(branch);
			return Result.ok().setData(branch);
		}catch (BizException e) { 
			logger.error("执行异常",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}  
	}

	@ApiOperation( value = "批量修改某些字段",notes="")
	@ApiResponses({
			@ApiResponse(code = 200,response=Branch.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@HasRole
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields(@RequestBody Map<String,Object> branchMap) {
		
		
		try{

			List<String> ids= (List<String>) branchMap.get("$pks");
			if(ids==null || ids.size()==0){
				return Result.error("ids-0","ids不能为空");
			}
			Set<String> fields=new HashSet<>();
			fields.add("id");
			for (String fieldName : branchMap.keySet()) {
				if(fields.contains(fieldName)){
					return Result.error(fieldName+"-no-edit",fieldName+"不允许修改");
				}
			}
			Set<String> fieldKey=branchMap.keySet().stream().filter(i-> fieldsMap.containsKey(i)).collect(Collectors.toSet());
			fieldKey=fieldKey.stream().filter(i->!StringUtils.isEmpty(branchMap.get(i) )).collect(Collectors.toSet());

			if(fieldKey.size()<=0) {
				return Result.error("fieldKey-0","没有需要更新的字段");
			}
			Branch branch = fromMap(branchMap,Branch.class);
			List<Branch> branchsDb=branchService.selectListByIds(ids);
			if(branchsDb==null ||branchsDb.size()==0){
				return Result.error("data-0","记录已不存在");
			}
			List<Branch> can=new ArrayList<>();
			List<Branch> no=new ArrayList<>();
			User user = LoginUtils.getCurrentUserInfo();
			if(branchMap.containsKey("branchName")){
				String nbranchName= (String) branchMap.get("branchName");
				if(ids.size()>1){
					return Result.error("branchName-only-one","机构名称只能逐条修改");
				}
				if(!branchsDb.get(0).getBranchName().equals(nbranchName)){
					Branch branchC=new Branch();
					branchC.setBranchName(nbranchName);
					int c= (int) this.branchService.countByWhere(branchC);
					if(c>0){
						return Result.error("branchName-exists","机构名称已存在，请换名字");
					}
				}

			}
			for (Branch branchDb : branchsDb) {
				Tips tips2 = new Tips("检查通过");
				if(!branchDb.getId().equals(user.getBranchId())){
					tips2.setErrMsg("no-qx-edit","无权限修改");
				}
				if(!tips2.isOk()){
					no.add(branchDb);
				}else{
					can.add(branchDb);
				}
			}
			if(can.size()>0){
				branchMap.put("$pks",can.stream().map(i->i.getId()).collect(Collectors.toList()));
				branchService.editSomeFields(branchMap);
			}
			List<String> msgs=new ArrayList<>();
			if(can.size()>0){
				msgs.add(String.format("成功更新以下%s条数据",can.size()));
			}
			if(no.size()>0){
				msgs.add(String.format("以下%s个数据无权限更新",no.size()));
			}
			if(can.size()>0){
				return Result.ok(msgs.stream().collect(Collectors.joining()));
			}else {
				return Result.error(msgs.stream().collect(Collectors.joining()));
			}
			//return Result.ok().setData(xmMenu);
		}catch (BizException e) {
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}
	}
	
	/***/
	@ApiOperation( value = "删除一条管理端机构表（机构下面若干部门）信息",notes="delBranch,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	})
	@HasRole
	@HasQx(value = "sys_branch_del",name = "删除机构",moduleId = "mdp-sys",moduleName = "组织管理")
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delBranch(@RequestBody Branch branch){
		
		
		if(!LoginUtils.isSuperAdmin() && !LoginUtils.isBranchAdmin(branch.getId())) { 
			return Result.error("not-org-adm-not-allow-oper","不是机构管理员，不允许操作");
		}
		try{
			Dept dept=new Dept();
			dept.setBranchId(branch.getId());
			if(this.deptService.countByWhere(dept)>0){
				return Result.error("has-dept","该机构具有部门，不允许删除");
			}
			branchService.deleteByPk(branch);
			return Result.ok();
		}catch (BizException e) { 
			logger.error("执行异常",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}  
	}
	 
	
	/***/
	@ApiOperation( value = "根据主键修改一条管理端机构表（机构下面若干部门）信息",notes="editBranch")
	@ApiResponses({
		@ApiResponse(code = 200,response=Branch.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 

	@HasQx(value = "sys_branch_edit",name = "修改机构",moduleId = "mdp-sys",moduleName = "组织管理")
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editBranch(@RequestBody Branch branch) {
		
		
		try{
			if(!LoginUtils.isSuperAdmin() && !LoginUtils.isBranchAdmin(branch.getId())) {
				return Result.error("not-org-adm-not-allow-oper","不是机构管理员，不允许操作");
			}
			branchService.updateByPk(branch);
			return Result.ok().setData(branch);
		}catch (BizException e) { 
			logger.error("执行异常",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}  
	}

	/***/
	@ApiOperation( value = "企业申领-申请作为该企业的主管理员",notes="claimBranch")
	@ApiResponses({
			@ApiResponse(code = 200,response=Branch.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})

	@HasQx(value = "sys_branch_claim",name = "企业申领",moduleId = "mdp-sys",moduleName = "组织管理")
	@RequestMapping(value="/claim",method=RequestMethod.POST)
	public Result claimBranch(@RequestBody Branch branch) {


		try{
			User user=LoginUtils.getCurrentUserInfo();
			 Branch branchDb=this.branchService.getById(branch.getId());
			 if(branchDb==null){
			 	return Result.error("branch-id-not-exists","企业不存在，请创建企业即可");
			 }
			 if("1".equals(branchDb.getClaimStatus())){
			 	return Result.error("branch-claim-1","企业已被申领，申领人为%s",branchDb.getAdmUsername());
			 }
			 Branch branchUpdate=new Branch();
			 String inviteId=inviteCacheService.createInviteId("claim");
			UserTpaInvite invite=new UserTpaInvite();
			invite.setInviteId(inviteId);
			invite.setSendBranchId(branchDb.getId());
			invite.setSendBranchName(branchDb.getBranchName());
			invite.setSendUserid(branchDb.getId());
			invite.setSendUsername(branchDb.getBranchName());
			invite.setJoinUserid(user.getUserid());
			invite.setJoinUsername(user.getUsername());
			invite.setSendTime(new Date());
			 inviteCacheService.setInvite(inviteId,invite);
			return Result.ok().setData(invite);
		}catch (BizException e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}
	}


	@ApiOperation( value = "根据主键列表批量删除管理端机构表（机构下面若干部门）信息",notes="batchDelBranch,仅需要上传主键字段")
	@ApiResponses({
			@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	})

	@HasQx(value = "sys_branch_batchDel",name = "批量删除机构",moduleId = "mdp-sys",moduleName = "组织管理")
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelBranch(@RequestBody List<Branch> branchs) {
		
		
		try{
			if(!LoginUtils.isSuperAdmin()  ) {
				for (Branch branch : branchs) {
					if(!LoginUtils.isBranchAdmin(branch.getId())){
						return Result.error("not-org-adm-not-allow-oper","不是机构管理员，不允许操作");
					}
				}
			}
			if(branchs.size()<=0){
				return Result.error("data-0","请上送待删除数据列表");
			}
			List<Branch> datasDb=branchService.selectListByIds(branchs.stream().map(i-> i.getId() ).collect(Collectors.toList()));

			List<Branch> can=new ArrayList<>();
			List<Branch> no=new ArrayList<>();
			for (Branch data : datasDb) {
				if(true){
					can.add(data);
				}else{
					no.add(data);
				}
			}
			List<String> msgs=new ArrayList<>();
			if(can.size()>0){
				branchService.batchDelete(branchs);
				msgs.add(String.format("成功删除%s条数据.",can.size()));
			}

			if(no.size()>0){
				msgs.add(String.format("以下%s条数据不能删除.【%s】",no.size(),no.stream().map(i-> i.getId() ).collect(Collectors.joining(","))));
			}
			if(can.size()>0){
				return Result.ok(msgs.stream().collect(Collectors.joining()));
			}else {
				return Result.error(msgs.stream().collect(Collectors.joining()));
			}
		}catch (BizException e) {
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}
	}

}
