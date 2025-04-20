package com.xm.core.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.audit.log.client.annotation.AuditLog;
import com.mdp.audit.log.client.annotation.OperType;
import com.mdp.core.entity.Result;
import com.mdp.core.entity.Tips;
import com.mdp.core.query.QueryTools;
import com.mdp.core.utils.RequestUtils;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.swagger.ApiEntityParams;
import com.xm.core.entity.XmEnvList;
import com.xm.core.service.XmEnvListService;
import com.xm.core.service.XmGroupService;
import io.swagger.annotations.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

/**
 * url编制采用rest风格,如对XM.xm_env_list xm_env_list的操作有增删改查,对应的url分别为:<br>
 *  新增: xm/xmEnvList/add <br>
 *  查询: xm/xmEnvList/list<br>
 *  模糊查询: xm/xmEnvList/listKey<br>
 *  修改: xm/xmEnvList/edit <br>
 *  删除: xm/xmEnvList/del<br>
 *  批量删除: xm/xmEnvList/batchDel<br>
 * 组织 com.qqkj  顶级模块 oa 大模块 xm 小模块 <br>
 * 实体 XmEnvList 表 XM.xm_env_list 当前主键(包括多主键): id; 
 ***/
@RestController("xm.core.xmEnvListController")
@RequestMapping(value="/xm/core/xmEnvList")
@Api(tags={"xm_env_list操作接口"})
public class XmEnvListController {
	
	static Log logger=LogFactory.getLog(XmEnvListController.class);
	
	@Autowired
	private XmEnvListService xmEnvListService;



	@Autowired
	XmGroupService xmGroupService;
 
	
	@ApiOperation( value = "查询xm_env_list信息列表",notes="listXmEnvList,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
	@ApiEntityParams(XmEnvList.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name="pageSize",value="每页记录数",required=false),
		@ApiImplicitParam(name="pageNum",value="当前页码,从1开始",required=false),
		@ApiImplicitParam(name="total",value="总记录数,服务器端收到0时，会自动计算总记录数，如果上传>0的不自动计算",required=false),
		@ApiImplicitParam(name="orderBy",value="排序列 如性别、学生编号排序 orderBy = sex desc,student_id desc",required=false),
		@ApiImplicitParam(name="count",value="是否进行总条数计算,count=true|false",required=false) 
	})
	@ApiResponses({
		@ApiResponse(code = 200,response= XmEnvList.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listXmEnvList(@ApiIgnore @RequestParam Map<String,Object> params){
		 
		RequestUtils.transformArray(params, "ids");		
		IPage page=QueryTools.initPage(params);
		User user=LoginUtils.getCurrentUserInfo();
		params.put("userid",user.getUserid());
		params.put("branchId",user.getBranchId());
		QueryWrapper<XmEnvList> qw = QueryTools.initQueryWrapper(XmEnvList.class , params);
		List<Map<String,Object>> datas = xmEnvListService.selectListMapByWhere(page,qw,params);
		return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());	//列出XmEnvList列表 
	}
	
 
	
	@ApiOperation( value = "新增一条xm_env_list信息",notes="addXmEnvList,主键如果为空，后台自动生成")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmEnvList.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	////@HasQx(value = "xm_core_xmEnvList_add",name = "新建环境清单",moduleId = "xm-project",moduleName = "管理端-项目管理系统")
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addXmEnvList(@RequestBody XmEnvList xmEnvList) {

			if(!StringUtils.hasText(xmEnvList.getProjectId())){
				return Result.error("projectId-0","项目编号不能为空");
			}
			if(!StringUtils.hasText(xmEnvList.getName())){
				return Result.error("name-0","名称不能为空");
			}
			User user= LoginUtils.getCurrentUserInfo();
			if(StringUtils.hasText(xmEnvList.getProjectId())){
				boolean inProjectGroup=xmGroupService.checkUserExistsProjectGroup(xmEnvList.getProjectId(),user.getUserid());
				if(!inProjectGroup){
					return Result.error("no-in-project","您不在项目中【"+xmEnvList.getProjectId()+"】，不能添加环境清单");
				}
			}

			if(!StringUtils.hasText(xmEnvList.getReadQx())){
				return Result.error("readQx-0","请选中读权限");
			}
			if(!StringUtils.hasText(xmEnvList.getWriteQx())){
				return Result.error("writeQx-0","请选中写权限");
			}
			xmEnvListService.addEnv(xmEnvList);
		return Result.ok();
	}

	@ApiOperation( value = "删除一条xm_env_list信息",notes="delXmEnvList,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	})
	////@HasQx(value = "xm_core_xmEnvList_del",name = "删除环境清单",moduleId = "xm-project",moduleName = "管理端-项目管理系统")
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delXmEnvList(@RequestBody XmEnvList xmEnvList){

			XmEnvList xmEnvListDb=this.xmEnvListService.selectOneById(xmEnvList.getId());
			if(xmEnvListDb==null){
				return Result.error("data-0","数据已不存在");
			}
			User user=LoginUtils.getCurrentUserInfo();
			if(StringUtils.hasText(xmEnvListDb.getWriteQx()) && !"0".equals(xmEnvListDb.getWriteQx()) && user.getUserid().equals(xmEnvListDb.getCreateUserid())){
				String writeQx=xmEnvListDb.getWriteQx();
				if("1".equals(writeQx)){//同一机构可写
					if(!user.getBranchId().equals(xmEnvListDb.getBranchId())){
						return Result.error("writeQx-err-1","您无权更改");
					}
				}else if("2".equals(writeQx)){//同一机构同一项目可写
					boolean inProject=this.xmGroupService.checkUserExistsProjectGroup(xmEnvListDb.getProjectId(), user.getUserid());
					if(!inProject){
						return Result.error("writeQx-err-2","您不在项目【"+xmEnvListDb.getProjectId()+"】,无权更改");
					}
				}else if("3".equals(writeQx)){//同一机构同一项目可写
	 				Tips isHeader=this.xmGroupService.checkIsProjectAdmOrTeamHeadOrAss(user,xmEnvListDb.getCreateUserid(),xmEnvListDb.getProjectId());
 					if(!isHeader.isOk()){
						return Result.error("writeQx-err-3","您不是【"+xmEnvListDb.getCreateUsername()+"】的上级,无权更改");
					}
				}
			}
			xmEnvListService.deleteByPk(xmEnvList);
		return Result.ok();
		
	}

	@ApiOperation( value = "根据主键修改一条xm_env_list信息",notes="editXmEnvList")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmEnvList.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	////@HasQx(value = "xm_core_xmEnvList_edit",name = "修改环境清单",moduleId = "xm-project",moduleName = "管理端-项目管理系统")
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editXmEnvList(@RequestBody XmEnvList xmEnvList) {

			XmEnvList xmEnvListDb=this.xmEnvListService.selectOneById(xmEnvList.getId());
			if(xmEnvListDb==null){
				return Result.error("data-0","数据已不存在");
			}
			User user=LoginUtils.getCurrentUserInfo();
			if(StringUtils.hasText(xmEnvListDb.getWriteQx()) && !"0".equals(xmEnvListDb.getWriteQx()) && user.getUserid().equals(xmEnvListDb.getCreateUserid())){
				String writeQx=xmEnvListDb.getWriteQx();
				if("1".equals(writeQx)){//同一机构可写
					if(!user.getBranchId().equals(xmEnvListDb.getBranchId())){
						return Result.error("writeQx-err-1","您无权更改");
					}
				}else if("2".equals(writeQx)){//同一机构同一项目可写
					boolean inProject=this.xmGroupService.checkUserExistsProjectGroup(xmEnvListDb.getProjectId(), user.getUserid());
					if(!inProject){
						return Result.error("writeQx-err-2","您不在项目【"+xmEnvListDb.getProjectId()+"】,无权更改");
					}
				}else if("3".equals(writeQx)){//同一机构同一项目可写
	 				Tips isHeader=this.xmGroupService.checkIsProjectAdmOrTeamHeadOrAss(user,xmEnvListDb.getCreateUserid(),xmEnvListDb.getProjectId());
					if(!isHeader.isOk()){
						return Result.error("writeQx-err-3","您不是【"+xmEnvListDb.getCreateUsername()+"】的上级,无权更改");
					}
				}
			}
			xmEnvListService.updateByPk(xmEnvList);

		return Result.ok();
	}


	/**
	@ApiOperation( value = "根据主键列表批量删除xm_env_list信息",notes="batchDelXmEnvList,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	})
	//@HasQx(value = "xm_core_xmEnvList_batchDel",name = "批量删除环境清单",moduleId = "xm-project",moduleName = "管理端-项目管理系统")
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelXmEnvList(@RequestBody List<XmEnvList> xmEnvLists) {
		
		
		
			xmEnvListService.batchDelete(xmEnvLists);
		return Result.ok();
		
	}
	**/


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
	@AuditLog(firstMenu="办公平台",secondMenu="项目惯例",func="processApprova",funcDesc="项目环境清单审批",operType=OperType.UPDATE)
	@RequestMapping(value="/processApprova",method=RequestMethod.POST)
	public Result processApprova( @RequestBody Map<String,Object> flowVars){
		this.xmEnvListService.processApprova(flowVars);
		return Result.ok();
		
	}
}
