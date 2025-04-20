package com.xm.core.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.query.QueryTools;
import com.mdp.core.utils.ObjectTools;
import com.mdp.core.utils.RequestUtils;
import com.mdp.msg.client.PushNotifyMsgService;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.swagger.ApiEntityParams;
import com.xm.core.entity.XmGroupUser;
import com.xm.core.entity.XmProduct;
import com.xm.core.entity.XmProject;
import com.xm.core.service.*;
import com.xm.core.service.push.XmPushMsgService;
import com.xm.core.vo.XmGroupVo;
import io.swagger.annotations.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;
import java.util.stream.Collectors;

import static com.mdp.core.utils.BaseUtils.map;

/**
 * url编制采用rest风格,如对XM.xm_group_user xm_group_user的操作有增删改查,对应的url分别为:<br>
 *  新增: core/xmGroupUser/add <br>
 *  查询: core/xmGroupUser/list<br>
 *  模糊查询: core/xmGroupUser/listKey<br>
 *  修改: core/xmGroupUser/edit <br>
 *  删除: core/xmGroupUser/del<br>
 *  批量删除: core/xmGroupUser/batchDel<br>
 * 组织 com.qqkj  顶级模块 xm 大模块 core 小模块 <br>
 * 实体 XmProjectGroupUser 表 XM.xm_group_user 当前主键(包括多主键): id; 
 ***/
@RestController("xm.core.xmGroupUserController")
@RequestMapping(value="/xm/core/xmGroupUser")
@Api(tags={"xm_group_user操作接口"})
public class XmGroupUserController {
	
	static Log logger=LogFactory.getLog(XmGroupUserController.class);
	
	@Autowired
	private XmGroupUserService xmGroupUserService;


	@Autowired
	private XmProjectService xmProjectService;


	@Autowired
	private XmProductService xmProductService;

	@Autowired
	XmGroupService xmGroupService;


	@Autowired
	XmRecordService xmRecordService;

	@Autowired
	XmPushMsgService pushMsgService;



	@Autowired
	PushNotifyMsgService notifyMsgService;
 
	
	@ApiOperation( value = "查询xm_group_user信息列表",notes="listXmProjectGroupUser,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
	@ApiEntityParams(XmGroupUser.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name="pageSize",value="每页记录数",required=false),
		@ApiImplicitParam(name="pageNum",value="当前页码,从1开始",required=false),
		@ApiImplicitParam(name="total",value="总记录数,服务器端收到0时，会自动计算总记录数，如果上传>0的不自动计算",required=false),
		@ApiImplicitParam(name="orderBy",value="排序列 如性别、学生编号排序 orderBy = sex desc,student_id desc",required=false),
		@ApiImplicitParam(name="count",value="是否进行总条数计算,count=true|false",required=false) 
	})
	@ApiResponses({
		@ApiResponse(code = 200,response= XmGroupUser.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listXmProjectGroupUser(@ApiIgnore @RequestParam Map<String,Object> params){
		 
		RequestUtils.transformArray(params, "ids");		
		IPage page= QueryTools.initPage(params);
		User user=LoginUtils.getCurrentUserInfo();
		QueryTools.alias(params,"assUsername g.assUsername","groupName g.groupName","leaderUsername g.leaderUsername","leaderUserid g.leaderUserid","assUserid g.assUserid");
		QueryWrapper<XmGroupUser> qw = QueryTools.initQueryWrapper(XmGroupUser.class , params,"assUsername","groupName","leaderUsername","assUserid","leaderUserid");
		qw.eq("g.branch_id",user.getBranchId());
		List<Map<String,Object>> datas = xmGroupUserService.selectListMapByWhere(page,qw,params);
			return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());	//列出XmProjectGroupUser列表
		
	}
	
 

	@ApiOperation( value = "新增一条xm_group_user信息",notes="addXmProjectGroupUser,主键如果为空，后台自动生成")
	@ApiResponses({
		@ApiResponse(code = 200,response= XmGroupUser.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addXmProjectGroupUser(@RequestBody XmGroupUser gu) {


			if(!StringUtils.hasText(gu.getGroupId())||!StringUtils.hasText(gu.getUserid())){
				return Result.error("pk-0","请上送小组编号，用户编号groupId,userid");
			}
			if(!StringUtils.hasText(gu.getPgClass())){
				return Result.error("pgClass-0","请上送小组类型pgClass");
			}
			String pgClass=gu.getPgClass();
			User user=LoginUtils.getCurrentUserInfo();
			String name="";
				if(!StringUtils.hasText(gu.getProjectId())){
					return Result.error("projectId-0","请上送小组归属项目编号");
				}

				XmProject xmProject=this.xmProjectService.getProjectFromCache(gu.getProjectId());
				if(xmProject==null){
					return Result.error("project-0","项目已不存在");
				}
				name=xmProject.getName();
		XmGroupVo xmGroupVo=this.xmGroupService.getProjectGroupFromCache(xmProject.getId(),gu.getGroupId());
		if(xmGroupVo==null){
			return Result.error("group-0","小组已不存在");
		}
		if(!xmGroupService.checkUserIsProjectAdm(xmProject, user.getUserid())){

			boolean isHead=xmGroupService.checkUserIsTeamHeadOrAss(xmGroupVo,user.getUserid());
			if(isHead==false){
				return Result.error("not-leader-ass","组长、副组长、组长助理以上人员可以添加小组成员。");
			}
		}
		if(ObjectTools.isNotEmpty(xmGroupVo.getProductId())){
			XmProduct product=this.xmProductService.getProductFromCache(xmGroupVo.getProductId());
			if(product==null){
				return Result.error("product-not-exists","产品【%s】已不存在",xmGroupVo.getProductId());
			}
			gu.setProductId(xmGroupVo.getProductId());
		}


			XmGroupUser guCount=new XmGroupUser();
		guCount.setGroupId(gu.getGroupId());
		guCount.setUserid(gu.getUserid());
			if(xmGroupUserService.countByWhere(guCount)>0){
				return Result.error("该用户已在小组中");
				
			}
			xmGroupUserService.insert(gu);
			Map<String,Object> usermap=new HashMap<>();
			usermap.put("userid", gu.getUserid());
			usermap.put("username", gu.getUsername());
			List<Map<String,Object>> users=new ArrayList<>();
			users.add(usermap);
			pushMsgService.pushJoinChannelGroupMsg(user.getBranchId(), gu.getGroupId(), users);
			notifyMsgService.pushMsg(user.getBranchId(),user.getUserid(),user.getUsername(),gu.getUserid(),gu.getUsername(),"恭喜您加入"+("0".equals(pgClass)?"项目":"产品")+"【"+name+"】",null);
			if(ObjectTools.isNotEmpty(xmGroupVo.getProductId())) {
				xmGroupService.clearProductGroup(xmGroupVo.getProductId());
				xmRecordService.addXmGroupRecord(xmGroupVo.getProductId(), gu.getGroupId(), "产品-团队-新增小组成员", "增加组员[" + gu.getUsername() + "]", gu.getUserid(), null);
			}
				xmGroupService.clearProjectGroup(gu.getProjectId());
				xmRecordService.addXmGroupRecord(gu.getProjectId(),gu.getGroupId(), "项目-团队-新增小组成员", "增加组员["+gu.getUsername()+"]",gu.getUserid(),null);


			
		return Result.ok();  
		
	}

	@ApiOperation( value = "删除一条xm_group_user信息",notes="delXmProjectGroupUser,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delXmProjectGroupUser(@RequestBody XmGroupUser gu){

			if(!StringUtils.hasText(gu.getGroupId())||!StringUtils.hasText(gu.getUserid())){
				return Result.error("pk-0","请上送小组编号，用户编号groupId,userid");
			}
			gu=this.xmGroupUserService.getById(gu);
			if(gu==null){
				return Result.error("data-0","小组组员已不存在");
			}
			User user=LoginUtils.getCurrentUserInfo();

				XmProject xmProject=this.xmProjectService.getProjectFromCache(gu.getProjectId());
				if(xmProject==null){
					return Result.error("project-0","项目已不存在");
				}
		XmGroupVo xmGroupVo=this.xmGroupService.getProjectGroupFromCache(xmProject.getId(),gu.getGroupId());
		if(xmGroupVo==null){
			return Result.error("group-0","小组已不存在");
		}
 				if(!xmGroupService.checkUserIsProjectAdm(xmProject, user.getUserid())){

					boolean isHead=xmGroupService.checkUserIsTeamHeadOrAss(xmGroupVo,user.getUserid());
					if(isHead==false){
						return Result.error("not-leader-ass","组长、副组长、组长助理以上人员可以删除小组成员。");
					}
				}


			xmGroupUserService.deleteByPk(gu);
			Map<String,Object> usermap=new HashMap<>();
			usermap.put("userid", gu.getUserid());
			usermap.put("username", gu.getUsername());
			List<Map<String,Object>> users=new ArrayList<>();
			users.add(usermap);

			notifyMsgService.pushMsg(user.getBranchId(),user.getUserid(),user.getUsername(),gu.getUserid(),gu.getUsername(),String.format("您离开项目【%s】中的小组【%s】",xmProject.getName(),xmGroupVo.getGroupName()),null);

			pushMsgService.pushLeaveChannelGroupMsg(user.getBranchId(), gu.getGroupId(), users);

			if(ObjectTools.isNotEmpty(xmGroupVo.getProductId())){
				xmGroupService.clearProductGroup(xmGroupVo.getProductId());
				xmRecordService.addXmProductRecord(xmGroupVo.getProductId(), "产品-团队-删除小组成员", "删除组员["+gu.getUsername()+"]");

			}

			xmGroupService.clearProjectGroup(xmGroupVo.getProjectId());
			xmRecordService.addXmGroupRecord(xmGroupVo.getProjectId(),gu.getGroupId(), "项目-团队-删除小组成员", "删除组员["+gu.getUsername()+"]",gu.getUserid(),null);



		return Result.ok("delete-ok","删除成功");
		
	}

	@ApiOperation( value = "根据主键修改一条xm_group_user信息",notes="editXmProjectGroupUser")
	@ApiResponses({
		@ApiResponse(code = 200,response= XmGroupUser.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editXmProjectGroupUser(@RequestBody XmGroupUser gu0) {

			if(!StringUtils.hasText(gu0.getGroupId())||!StringUtils.hasText(gu0.getUserid())){
				return Result.error("pk-0","请上送小组编号，用户编号groupId,userid");
			}
			XmGroupUser gu=this.xmGroupUserService.getById(gu0);
			if(gu==null){
				return Result.error("data-0","小组已不存在");
			}
			User user=LoginUtils.getCurrentUserInfo();
				if(!StringUtils.hasText(gu.getProjectId())){
					return Result.error("projectId-0","请上送小组归属项目编号");
				}

				XmProject xmProject=this.xmProjectService.getProjectFromCache(gu.getProjectId());
				if(xmProject==null){
					return Result.error("product-0","产品已不存在");
				}
				if(!xmGroupService.checkUserIsProjectAdm(xmProject, user.getUserid())){
					XmGroupVo xmGroupVo=this.xmGroupService.getProductGroupFromCache(xmProject.getId(),gu.getGroupId());
					if(xmGroupVo==null){
						return Result.error("group-0","小组已不存在");
					}
					boolean isHead=xmGroupService.checkUserIsTeamHeadOrAss(xmGroupVo,user.getUserid());
					if(isHead==false){
						return Result.error("not-leader-ass","组长、副组长、组长助理以上人员可以修改小组成员。");
					}
				}
			xmGroupUserService.updateSomeFieldByPk(gu0);

			if(ObjectTools.isNotEmpty(gu.getProductId())){
				xmGroupService.clearProductGroup(gu.getProductId());
				xmRecordService.addXmGroupRecord(gu.getProductId(), gu.getGroupId(),"产品-团队-修改小组成员信息", "变更["+gu.getUsername()+"]");

			}
		xmGroupService.clearProjectGroup(gu.getProjectId());
		xmRecordService.addXmGroupRecord(gu.getProjectId(), gu.getGroupId(),"项目-团队-修改小组成员信息", "变更["+gu.getUsername()+"]");


		return Result.ok();
		
	}


	@ApiOperation( value = "根据主键列表批量新增xm_group_user信息",notes="batchAddXmProjectGroupUser,仅需要上传主键字段")
	@ApiResponses({
			@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	})
	@RequestMapping(value="/batchAdd",method=RequestMethod.POST)
	public Result batchAddXmProjectGroupUser(@RequestBody List<XmGroupUser> gus) {
		
		if(gus==null || gus.size()==0){
			return Result.error("data-0","请上送要删除的小组成员");
		}
		
				
			if(gus.stream().filter(i->!StringUtils.hasText(i.getUserid())||!StringUtils.hasText(i.getGroupId())).findAny().isPresent()){
				return Result.error("userid-or-groupId-0","请上送用户编号及小组编号");
			}else{
				for (XmGroupUser gu : gus) {
					if ( !StringUtils.hasText(gu.getProjectId())) {
						return Result.error("projectId-0", "项目编号不能为空");
					}
					if (!StringUtils.hasText(gu.getObranchId())) {
						return Result.error("obranchId-0", "用户归属机构号不能为空");
					}
					if (!StringUtils.hasText(gu.getUserid())) {
						return Result.error("userid-0", "用户编号不能为空");
					}
					if (!StringUtils.hasText(gu.getUsername())) {
						return Result.error("username-0", "用户名称不能为空");
					}
					if (!StringUtils.hasText(gu.getGroupId())) {
						return Result.error("groupId-0", "要加入的组编号不能为空");
					}
				}
			}
			List<XmGroupUser> gusDb=this.xmGroupUserService.selectListByIds(gus);
			//过滤掉已经存在的
			List<XmGroupUser> gusNoExists=gus.stream().filter(i->!(gusDb.stream().filter(k->k.getGroupId().equals(i.getGroupId())&&k.getUserid().equals(i.getUserid()))).findAny().isPresent()).collect(Collectors.toList());
			if(gusNoExists.size()==0){
				return Result.error("user-had-exists","成功添加0个组员。以下用户已在小组中，不用再添加。【"+gusDb.stream().map(i->i.getUsername()).collect(Collectors.joining(","))+"】");
			}
			User user=LoginUtils.getCurrentUserInfo();
			XmGroupUser gu=gusNoExists.get(0);
			String projectId=gu.getProjectId();
			List<XmGroupUser> gus2=new ArrayList<>();
			XmProject xmProject=this.xmProjectService.getProjectFromCache(gu.getProjectId());
				if(xmProject==null){
					return Result.error("project-0","项目已不存在");
				}
				gus2=gusNoExists.stream().filter(i->projectId.equals(i.getProjectId())).collect(Collectors.toList());
				if(gus2.size()<gusNoExists.size()){
					return Result.error("data-0","批量新增只能新增同一个项目的成员。");
				}

			Set<String> groupIds=gusNoExists.stream().map(i->i.getGroupId()).collect(Collectors.toSet());
			List<XmGroupUser> canAddUsers=new ArrayList<>();
			Map<String,List<XmGroupUser>> groupUsersMap=new HashMap<>();
			List<XmGroupUser> noQx=new ArrayList<>();
			boolean isPm=xmGroupService.checkUserIsProjectAdm(xmProject,user.getUserid());
			Map<String,XmGroupVo> groupVoList=new HashMap<>();
			for (String groupId : groupIds) {
				XmGroupVo xmGroupVo=this.xmGroupService.getProjectGroupFromCache(xmProject.getId(),groupId);
				if(xmGroupVo==null){
					continue;
				}

					if(!isPm){
						boolean isHead=xmGroupService.checkUserIsTeamHeadOrAss(xmGroupVo,user.getUserid());
						if(isHead==false){
							continue;
						}
					}
				List<XmGroupUser> cdus=gus2.stream().filter(i->groupId.equals(i.getGroupId())).collect(Collectors.toList());
				canAddUsers.addAll(cdus);
				groupUsersMap.put(groupId,cdus);
				groupVoList.put(groupId,xmGroupVo);
			}
			noQx=gus.stream().filter(i->!canAddUsers.stream().filter(k->k.getUserid().equals(i.getUserid()) && k.getGroupId().equals(i.getGroupId())).findAny().isPresent()).collect(Collectors.toList());
			List<String> msg=new ArrayList<>();
			msg.add("成功新增"+canAddUsers.size()+"个小组用户.");
			if(canAddUsers.size()>0){
				xmGroupUserService.batchInsert(canAddUsers);
			}
			if(noQx.size()>0){
				msg.add("不是项目经理、小组长，无权限操作以下"+noQx.size()+"个用户，【"+noQx.stream().map(i->i.getUsername()).collect(Collectors.joining(","))+"】");
			}
 			if(gusDb.size()>0){
				msg.add("以下"+gusDb.size()+"个小组用户已在组里，无需再添加。【"+gusDb.stream().map(i->i.getUsername()).collect(Collectors.joining(","))+"】");
			}
			groupUsersMap.forEach((groupId,groupUsers)->{

				List<Map<String,Object>> users=groupUsers.stream().map(i->map("userid",i.getUserid(),"username",i.getUsername())).collect(Collectors.toList());
				pushMsgService.pushJoinChannelGroupMsg(user.getBranchId(),groupId, users);

					xmRecordService.addXmGroupRecord(projectId,groupId, "项目-团队-新增小组成员", "新增组员["+groupUsers.stream().map(i->i.getUsername()).collect(Collectors.joining(","))+"]",user.getUserid(),null);
				canAddUsers.stream().map(k->k.getProductId()).collect(Collectors.toSet());
				XmGroupVo groupVo=groupVoList.get(groupId);
 					if(ObjectTools.isNotEmpty(groupVo.getProductId())){
						xmRecordService.addXmGroupRecord(groupVo.getProductId(),groupId, "产品-团队-新增小组成员", "新增组员["+groupUsers.stream().map(i->i.getUsername()).collect(Collectors.joining(","))+"]",user.getUserid(),null);
					}


			});
			 Set<String> productIds=canAddUsers.stream().map(k->k.getProductId()).collect(Collectors.toSet());
			for (String productId : productIds) {
				this.xmGroupService.clearProductGroup(productId);
			}
			this.xmGroupService.clearProjectGroup(projectId);
			if(canAddUsers.size()!=0){
				return Result.ok(msg.stream().collect(Collectors.joining(" ")));
			}else{
				return Result.error(msg.stream().collect(Collectors.joining(" ")));
			}
		
	}


	@ApiOperation( value = "根据主键列表批量删除xm_group_user信息",notes="batchDelXmProjectGroupUser,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelXmProjectGroupUser(@RequestBody List<XmGroupUser> gus) {
		
		if(gus==null || gus.size()==0){
			return Result.error("data-0","请上送要删除的小组成员");
		}
		
				
			List<XmGroupUser> gusDb=this.xmGroupUserService.selectListByIds(gus);
			if(gusDb.size()==0){
				return Result.error("data-0","要删除的数据已不存在。");
			}
			User user=LoginUtils.getCurrentUserInfo();
			XmGroupUser gu=gusDb.get(0);
			String projectId=gu.getProjectId();
			List<XmGroupUser> gus2=new ArrayList<>();
			XmProject xmProject=null;
				xmProject=this.xmProjectService.getProjectFromCache(gu.getProjectId());
				if(xmProject==null){
					return Result.error("project-0","项目已不存在");
				}
				gus2=gusDb.stream().filter(i->projectId.equals(i.getProjectId())).collect(Collectors.toList());
				if(gus2.size()<gusDb.size()){
					return Result.error("data-0","批量删除只能删除同一个项目的成员。");
				}

			Set<String> groupIds=gusDb.stream().map(i->i.getGroupId()).collect(Collectors.toSet());
			List<XmGroupUser> canDelUsers=new ArrayList<>();
			Map<String,List<XmGroupUser>> groupUsersMap=new HashMap<>();
			boolean isPm=xmGroupService.checkUserIsProjectAdm(xmProject,user.getUserid());
			Map<String,XmGroupVo> groupVoMap=new HashMap<>();
			for (String groupId : groupIds) {
				XmGroupVo xmGroupVo=this.xmGroupService.getProjectGroupFromCache(xmProject.getId(),groupId);

				if(xmGroupVo==null){
					continue;
				}

					if(!isPm){

						boolean isHead=xmGroupService.checkUserIsTeamHeadOrAss(xmGroupVo,user.getUserid());
						if(isHead==false){
							continue;
						}
					}
				List<XmGroupUser> cdus=gus2.stream().filter(i->groupId.equals(i.getGroupId())).collect(Collectors.toList());
				canDelUsers.addAll(cdus);
				groupUsersMap.put(groupId,cdus);
				groupVoMap.put(xmGroupVo.getId(),xmGroupVo);
			}
			List<String> msg=new ArrayList<>();
			msg.add("成功删除"+canDelUsers.size()+"个小组用户.");
			if(canDelUsers.size()>0){
				xmGroupUserService.doBatchDelete(canDelUsers);
				this.xmGroupService.clearProjectGroup(projectId);
				Set<String> productIds=groupVoMap.values().stream().map(k->k.getProductId()).collect(Collectors.toSet());
				for (String productId : productIds) {
					this.xmGroupService.clearProductGroup(productId);
				}

			}
			List<String> noDelUsers=new ArrayList<>();
			if(canDelUsers.size()<gus.size()){

				for (XmGroupUser gu0 : gus) {
					if(!canDelUsers.stream().filter(i->i.getUserid().equals(gu0.getUserid())&&i.getGroupId().equals(gu0.getGroupId())).findAny().isPresent()){
						noDelUsers.add(gu0.getUsername());
					}
				}
				msg.add("以下"+noDelUsers.size()+"个小组用户无权限删除。【"+noDelUsers.stream().collect(Collectors.toSet()).stream().collect(Collectors.joining(","))+"】");
 			}

			groupUsersMap.forEach((groupId,groupUsers)->{

				List<Map<String,Object>> users=groupUsers.stream().map(i->map("userid",i.getUserid(),"username",i.getUsername())).collect(Collectors.toList());
				pushMsgService.pushLeaveChannelGroupMsg(user.getBranchId(),groupId, users);

						xmRecordService.addXmGroupRecord(groupVoMap.get(groupId).getProductId(),groupId, "产品-团队-删除小组成员", "删除组员["+groupUsers.stream().map(i->i.getUsername()).collect(Collectors.joining(","))+"]",user.getUserid(),null);


					xmRecordService.addXmGroupRecord(projectId,groupId, "项目-团队-删除小组成员", "删除组员["+groupUsers.stream().map(i->i.getUsername()).collect(Collectors.joining(","))+"]",user.getUserid(),null);


			});
			if(canDelUsers.size()!=0){
				xmGroupService.clearProjectGroup(projectId);
				Set<String> productIds=canDelUsers.stream().map(k->k.getProductId()).collect(Collectors.toSet());
				for (String productId : productIds) {
					xmGroupService.clearProductGroup(productId);
				}
				return Result.ok(msg.stream().collect(Collectors.joining(" ")));
			}else{
				return Result.error(msg.stream().collect(Collectors.joining(" ")));
			}
		
	}
}
