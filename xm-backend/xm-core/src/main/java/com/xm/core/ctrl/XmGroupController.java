package com.xm.core.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.entity.Tips;
import com.mdp.core.query.QueryTools;
import com.mdp.core.utils.ObjectTools;
import com.mdp.core.utils.RequestUtils;
import com.mdp.msg.client.PushNotifyMsgService;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.swagger.ApiEntityParams;
import com.xm.core.entity.XmGroup;
import com.xm.core.entity.XmProduct;
import com.xm.core.entity.XmProject;
import com.xm.core.service.*;
import com.xm.core.service.cache.XmGroupCacheService;
import com.xm.core.service.push.XmPushMsgService;
import com.xm.core.vo.XmGroupVo;
import io.swagger.annotations.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * url编制采用rest风格,如对XM.xm_group xm_group的操作有增删改查,对应的url分别为:<br>
 *  新增: xm/xmGroup/add <br>
 *  查询: xm/xmGroup/list<br>
 *  模糊查询: xm/xmGroup/listKey<br>
 *  修改: xm/xmGroup/edit <br>
 *  删除: xm/xmGroup/del<br>
 *  批量删除: xm/xmGroup/batchDel<br>
 * 组织 com.qqkj  顶级模块 oa 大模块 xm 小模块 <br>
 * 实体 XmProjectGroup 表 XM.xm_group 当前主键(包括多主键): id; 
 ***/
@RestController("xm.core.xmGroupController")
@RequestMapping(value="/xm/core/xmGroup")
@Api(tags={"xm_group操作接口"})
public class XmGroupController {
	
	static Log logger=LogFactory.getLog(XmGroupController.class);
	
	@Autowired
	private XmGroupService xmGroupService;


	@Autowired
	private XmGroupCacheService xmGroupCacheService;
	
	@Autowired
	private XmProjectService xmProjectService;


	@Autowired
	private XmProductService xmProductService;

	@Autowired
	private XmProjectQxService projectQxService;


	@Autowired
	private XmProductQxService productQxService;
	
	@Autowired
    XmPushMsgService pushMsgService;


	@Autowired
	PushNotifyMsgService notifyMsgService;

	@Autowired
    XmRecordService xmRecordService;

	@ApiOperation( value = "删除旧团队，新增新团队",notes="")
	@ApiResponses({
			@ApiResponse(code = 200,response= XmGroup.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	//@HasQx(value = "xm_core_xmGroup_updateGroup",name = "批量更新修改项目团队信息",moduleId = "xm-project",moduleName = "管理端-项目管理系统")
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result updateGroup(@RequestBody XmGroup group) {

		
		
		if(group==null){
			return Result.error("小组信息不能为空");
		}
		if(!StringUtils.hasText(group.getId())){
			return Result.error("id-0","小组编号不能为空");
		}
		User user=LoginUtils.getCurrentUserInfo();
		XmGroup groupDb=this.xmGroupService.getById( group.getId());
		if(groupDb==null){
			return Result.error("data-0","小组已不存在。");
		}
		if(ObjectTools.isEmpty(group.getProductId())){
			group.setProductId("");
		}
		if(ObjectTools.isEmpty(groupDb.getProductId())){
			groupDb.setProductId("");
		}
			XmProject project=xmProjectService.getProjectFromCache(groupDb.getProjectId());
			boolean isPm=xmGroupService.checkUserIsProjectAdm(project, user.getUserid());
			if(!isPm){
 				Tips tips = projectQxService.checkProjectQx(project,0,user);
				Result.assertIsFalse(tips);
			}
			if(StringUtils.hasText(group.getLeaderUserid()) && !group.getLeaderUserid().equals(groupDb.getLeaderUserid())){
 				Tips tips = projectQxService.checkProjectQx(project,0,user, groupDb.getLeaderUserid(),groupDb.getLeaderUsername(),null);
 				Result.assertIsFalse(tips);
 				tips =projectQxService.checkProjectScopeQx(project,0,group.getLeaderUserid(),group.getLeaderUsername(),null);
				Result.assertIsFalse(tips);
			}
			if(StringUtils.hasText(group.getAssUserid()) && !group.getAssUserid().equals(groupDb.getAssUserid())){
 				Tips tips = projectQxService.checkProjectQx(project,0,user, groupDb.getAssUserid(),groupDb.getAssUsername(),null);
				Result.assertIsFalse(tips);
 				tips =projectQxService.checkProjectScopeQx(project,0,group.getAssUserid(),group.getAssUsername(),null);
				Result.assertIsFalse(tips);
			}
			//项目管理人员或者小组管理人员才有权限更新小组归属产品

		boolean isChangeProduct=!group.getProductId().equals(groupDb.getProductId());
			if(isChangeProduct){
				 if(!isPm){
					 if(!user.getUserid().equals(groupDb.getLeaderUserid()) && user.getUserid().equals(groupDb.getAssUserid())){
						 return Result.error("need-pm-to-change-group-product-0","项目管理人员或者小组管理人员才有权限更新小组归属产品");
					 }
				 }
			}


		xmGroupService.parentIdPathsCalcBeforeSave(group);
		Tips tips= xmGroupService.updateGroup(group,groupDb);	//列出XmProjectGroup列表
		Result.assertIsFalse(tips);
		xmGroupCacheService.clearProjectGroup(groupDb.getProjectId());
		xmRecordService.addXmGroupRecord(groupDb.getProjectId(),groupDb.getId(),"团队-小组-修改小组","修改小组信息【"+groupDb.getGroupName()+"】");

		if(isChangeProduct){
			if(!ObjectTools.isEmpty(groupDb.getProductId())){
				xmGroupCacheService.clearProductGroup(groupDb.getProductId());
				xmRecordService.addXmProductRecord(groupDb.getProductId(),"团队-小组-移出小组","将小组【"+groupDb.getGroupName()+"】移出当前产品");

			}
			if(!ObjectTools.isEmpty(group.getProductId())){
				xmGroupCacheService.clearProductGroup(group.getProductId());
				xmRecordService.addXmProductRecord(group.getProductId(),"团队-小组-移入小组","将小组【"+group.getGroupName()+"】移入当前产品");
			}
			xmRecordService.addXmGroupRecord(groupDb.getProjectId(),groupDb.getId(),"团队-小组-修改小组","修改小组信息【"+groupDb.getGroupName()+"】,将小组归属产品由【"+groupDb.getProductId()+"】变更为【"+group.getProductId()+"】");

		}

		return Result.ok();
	}

	@ApiOperation( value = "根据项目Id拿到团队",notes="")
	@ApiEntityParams(XmGroup.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name="pageSize",value="每页记录数",required=false),
			@ApiImplicitParam(name="pageNum",value="当前页码,从1开始",required=false),
			@ApiImplicitParam(name="total",value="总记录数,服务器端收到0时，会自动计算总记录数，如果上传>0的不自动计算",required=false),
			@ApiImplicitParam(name="orderBy",value="排序列 如性别、学生编号排序 orderBy = sex desc,student_id desc",required=false),
			@ApiImplicitParam(name="count",value="是否进行总条数计算,count=true|false",required=false)
	})
	@ApiResponses({
			@ApiResponse(code = 200,response= XmGroup.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	//@HasQx(value = "xm_core_xmGroup_getGroups",name = "查找项目团队信息",moduleId = "xm-project",moduleName = "管理端-项目管理系统")
	@RequestMapping(value="/getGroups",method=RequestMethod.GET)
	public Result getGroup(@ApiIgnore @RequestParam Map<String,Object> params) {
		
		RequestUtils.transformArray(params, "ids");		
		List<XmGroupVo>	datas=new ArrayList<>();
		String iterationId= (String) params.get("iterationId");
		String projectId= (String) params.get("projectId");
		String productId= (String) params.get("productId");
		if(StringUtils.hasText(productId)){
			datas = xmGroupService.getProductGroupVoList(productId);	//产品团队
		}else if(StringUtils.hasText(projectId)){
			datas = xmGroupService.getProjectGroupVoList(projectId);	//列出XmProjectGroup列表
		}else if(StringUtils.hasText(iterationId)){
			datas = xmGroupService.getProjectGroupVoListByIterationId(iterationId );	//列出XmProjectGroup列表
		}

		return Result.ok().setData(datas);
	}


		
 
	
	@ApiOperation( value = "查询xm_group信息列表",notes="listXmProjectGroup,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
	@ApiEntityParams(XmGroup.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name="pageSize",value="每页记录数",required=false),
		@ApiImplicitParam(name="pageNum",value="当前页码,从1开始",required=false),
		@ApiImplicitParam(name="total",value="总记录数,服务器端收到0时，会自动计算总记录数，如果上传>0的不自动计算",required=false),
		@ApiImplicitParam(name="orderBy",value="排序列 如性别、学生编号排序 orderBy = sex desc,student_id desc",required=false),
		@ApiImplicitParam(name="count",value="是否进行总条数计算,count=true|false",required=false) 
	})
	@ApiResponses({
		@ApiResponse(code = 200,response= XmGroup.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listXmProjectGroup(@ApiIgnore @RequestParam Map<String,Object> params){
		 
		RequestUtils.transformArray(params, "ids");		
		IPage page=QueryTools.initPage(params);
		User user=LoginUtils.getCurrentUserInfo();
		String projectId= (String) params.get("projectId");
		String productId= (String) params.get("productId");
		String iterationId= (String) params.get("iterationId");
		if(!StringUtils.hasText(projectId) && !StringUtils.hasText(productId) && !StringUtils.hasText(iterationId)){
		params.put("branchId",user.getBranchId());
			params.put("orCrowBranchId",user.getBranchId());
		}
		QueryWrapper<XmGroup> qw = QueryTools.initQueryWrapper(XmGroup.class , params);
		List<Map<String,Object>> datas = xmGroupService.selectListMapByWhere(page,qw,params);
			return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());	//列出XmProjectGroup列表
		
	}

	@ApiOperation( value = "新增一条xm_group信息",notes="addXmProjectGroup,主键如果为空，后台自动生成")
	@ApiResponses({
		@ApiResponse(code = 200,response= XmGroup.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	//@HasQx(value = "xm_core_xmGroup_add",name = "新增项目团队信息",moduleId = "xm-project",moduleName = "管理端-项目管理系统")
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addXmProjectGroup(@RequestBody XmGroup xmGroup) {

			User u = LoginUtils.getCurrentUserInfo();

				xmGroup.setBranchId(u.getBranchId());
					if(!StringUtils.hasText(xmGroup.getProjectId())){
						return Result.error("projectId-0","项目编号不能为空");
					}
					XmProject project = xmProjectService.getProjectFromCache(xmGroup.getProjectId());
					if(project==null){
						return Result.error("project-0","项目已不存在");
					}
	 				Tips tips =this.xmGroupService.checkProjectStatus(project);
					Result.assertIsFalse(tips);
	 				Tips tips2 =checkProjectGroupQxForAdd(project,u,xmGroup);
					Result.assertIsFalse(tips2);
					xmGroup.setBranchId(project.getBranchId());
				if(!ObjectTools.isEmpty(xmGroup.getProductId())){
					XmProduct xmProduct = xmProductService.getProductFromCache(xmGroup.getProductId());
					if(xmProduct==null){
						return Result.error("product-0","产品已不存在");
					}
	 				tips =this.xmGroupService.checkProductStatus(xmProduct);
					Result.assertIsFalse(tips);
	 				tips =checkProductGroupQxForAdd(xmProduct,u,xmGroup);
					Result.assertIsFalse(tips);
				}


			if (StringUtils.isEmpty(xmGroup.getId())) {
				xmGroup.setId(xmGroupService.createKey("id"));
			} else {
				XmGroup xmGroupQuery = new XmGroup(xmGroup.getId());
				if (xmGroupService.countByWhere(xmGroupQuery) > 0) {
					return Result.error("编号重复，请修改编号再提交");
					
				}
			}
			if(!StringUtils.hasText(xmGroup.getBranchId())){
				xmGroup.setBranchId(u.getBranchId());
			}
			this.xmGroupService.parentIdPathsCalcBeforeSave(xmGroup);
			if(ObjectTools.isEmpty(xmGroup.getPgroupId())){
				xmGroup.setPgroupId("0");
			}
			xmGroupService.insert(xmGroup);
				xmGroupCacheService.clearProjectGroup(xmGroup.getProjectId());
				xmRecordService.addXmGroupRecord(xmGroup.getProjectId(),xmGroup.getId(),"团队-小组-新增小组","新增小组【"+xmGroup.getGroupName()+"】");
		if(!ObjectTools.isEmpty(xmGroup.getProductId())) {

			xmGroupCacheService.clearProductGroup(xmGroup.getProductId());
			xmRecordService.addXmProductRecord(xmGroup.getProductId(), "团队-小组-新增小组", "新增小组【" + xmGroup.getGroupName() + "】");

		}
  			
		return Result.ok().setData(xmGroup);
		
	}


	public Tips checkProductGroupQxForAdd(XmProduct xmProduct,User u,XmGroup xmGroup){
		
		
		Tips tips=productQxService.checkProductQx(xmProduct,0,u);
		if(!tips.isOk()){
			return tips;
		}
		if(StringUtils.hasText(xmGroup.getLeaderUserid()) && !xmGroup.getLeaderUserid().equals(u.getUserid())){
			tips=productQxService.checkProductScopeQx(xmProduct,0,xmGroup.getLeaderUserid(),xmGroup.getLeaderUsername(),null);
		}
		if(!tips.isOk()){
			return tips;
		}
		if(StringUtils.hasText(xmGroup.getAssUserid()) && !xmGroup.getAssUserid().equals(u.getUserid())){
			tips=productQxService.checkProductScopeQx(xmProduct,0,xmGroup.getAssUserid(),xmGroup.getAssUsername(),null);
		}
		return tips;
	}


	public Tips checkProjectGroupQxForAdd(XmProject project,User u,XmGroup xmGroup){
		
		
		Tips tips=projectQxService.checkProjectQx(project,0,u);
		if(!tips.isOk()){
			return tips;
		}
		if(StringUtils.hasText(xmGroup.getLeaderUserid()) && !xmGroup.getLeaderUserid().equals(u.getUserid())){
			tips=projectQxService.checkProjectScopeQx(project,0,xmGroup.getLeaderUserid(),xmGroup.getLeaderUsername(),null);
		}
		if(!tips.isOk()){
			return tips;
		}
		if(StringUtils.hasText(xmGroup.getAssUserid()) && !xmGroup.getAssUserid().equals(u.getUserid())){
			tips=projectQxService.checkProjectScopeQx(project,0,xmGroup.getAssUserid(),xmGroup.getAssUsername(),null);
		}
		return tips;
	}

	@ApiOperation( value = "删除一条xm_group信息",notes="delXmProjectGroup,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delXmProjectGroup(@RequestBody XmGroup xmGroup){

			User u = LoginUtils.getCurrentUserInfo();
			if(!StringUtils.hasText(xmGroup.getId())){
				return Result.error("id-0","请上送小组编号");
			}
			XmGroup groupDb=this.xmGroupService.selectOneObject(xmGroup);
			if(groupDb==null){
				return Result.error("data-0","小组已不存在");
			}
				XmProject project = xmProjectService.getProjectFromCache(groupDb.getProjectId());
				if(project==null){
					return Result.error("project-0","项目已不存在");
				}
				boolean isPm=xmGroupService.checkUserIsProjectAdm(project,u.getUserid());
				if(!isPm) {
	 				Tips tips =projectQxService.checkProjectQx(project,0,u,groupDb.getLeaderUserid(),groupDb.getLeaderUsername(), null);
					Result.assertIsFalse(tips);
 				}
			XmGroup childrenGroupQuery=new XmGroup();
			childrenGroupQuery.setPgroupId(xmGroup.getId());
			long childrenCnt=this.xmGroupService.countByWhere(childrenGroupQuery);
			if(childrenCnt>0){
				return Result.error("childrenCnt-no-0","该小组有下级小组，不能删除。请先删除下级小组。");
			}
			xmGroupService.doDeleteByPk(xmGroup,groupDb);
				xmGroupCacheService.clearProjectGroup(groupDb.getProjectId());
				xmRecordService.addXmGroupRecord(groupDb.getProjectId(),groupDb.getId(),"团队-小组-删除小组","删除小组【"+groupDb.getGroupName()+"】");
 			if(ObjectTools.isNotEmpty(groupDb.getProductId())){
				xmGroupCacheService.clearProductGroup(groupDb.getProductId());
				xmRecordService.addXmProductRecord(groupDb.getProductId(),"团队-小组-删除小组","删除小组【"+groupDb.getGroupName()+"】");

			}



		return Result.ok("delete-ok","删除成功");
		
	}


	/**
	 *


	@ApiOperation( value = "根据主键列表批量删除xm_group信息",notes="batchDelXmProjectGroup,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelXmProjectGroup(@RequestBody List<XmGroup> xmGroups) {

			List<XmGroup> groupsDb=this.xmGroupService.selectListByIds(xmGroups.stream().map(i->i.getId()).collect(Collectors.toList()));
			if(groupsDb==null || groupsDb.size()==0){
				return Result.error("data-0","要删除的小组已不存在");
			}
			User user=LoginUtils.getCurrentUserInfo();
			XmGroup groupDb=groupsDb.get(0);
 			String id=groupDb.getProductId();
			List<XmGroup> hasChildNodes=new ArrayList<>();
			List<XmGroup> noQxs=new ArrayList<>();
			List<XmGroup> canDelNodes=new ArrayList<>();
			id=groupDb.getProjectId();
			XmProject prject=this.xmProjectService.getProjectFromCache(id);
			Map<String,String> projectAdmMap=xmGroupService.getProjectAdmUsers(prject);
			if (!projectAdmMap.containsKey(user.getUserid())) {
				return Result.error("not-project-adm","您不是项目管理人员，不能删除小组。项目级助理以上人员可以删除小组。");
			}

			if(canDelNodes.size()>0){
				for (XmGroup canDelNode : canDelNodes) {
					if(!xmGroupService.checkCanDelAllChild(canDelNode,canDelNodes)){
						hasChildNodes.add(canDelNode);
					}else{
						canDelNodes.add(canDelNode);
					}
				}
			}
			if(canDelNodes.size()>0){
				String groupNames=canDelNodes.stream().map(i->i.getGroupName()).collect(Collectors.joining(","));

				xmGroupService.doBatchDeleteProjectGroups(canDelNodes);
				xmGroupCacheService.clearProjectGroups(groupDb.getProjectId());
				xmRecordService.addXmGroupRecord(groupDb.getProjectId(),groupDb.getId(),"团队-小组-批量删除小组","删除"+canDelNodes.size()+"个小组【"+groupNames+"】");

			}
		return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());
		
	}

	 */
}
