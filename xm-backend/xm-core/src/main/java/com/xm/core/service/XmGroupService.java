package com.xm.core.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mdp.core.entity.Tips;
import com.mdp.core.service.BaseService;
import com.mdp.core.utils.ObjectTools;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.xm.core.entity.*;
import com.xm.core.mapper.XmGroupMapper;
import com.xm.core.service.cache.XmGroupCacheService;
import com.xm.core.service.client.SysClient;
import com.xm.core.service.push.XmPushMsgService;
import com.xm.core.vo.XmGroupVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br>
 * 组织 com.qqkj  顶级模块 oa 大模块 xm 小模块 <br>
 * 实体 XmProjectGroup 表 XM.xm_group 当前主键(包括多主键): id;
 ***/
@Service("xm.core.xmGroupService")
public class XmGroupService extends BaseService<XmGroupMapper,XmGroup> {


	@Autowired
	XmRecordService xmRecordService;


	@Autowired
	XmGroupUserService xmGroupUserService;

	@Autowired
	XmGroupCacheService groupCacheService;


	@Autowired
	XmProductProjectLinkService xmProductProjectLinkService;

	@Autowired
	private XmProjectService xmProjectService;


	@Autowired
	private XmProductService xmProductService;

	@Autowired
	XmPushMsgService pushMsgService;


	@Autowired
	SysClient sysClient;

	public void clearProjectGroup(String projectId){
		groupCacheService.clearProjectGroups(projectId);
	}
	public void clearProductGroup(String productId){
		groupCacheService.clearProductGroups(productId);
	}



	public Tips checkIsProductAdmOrTeamHeadOrAss(User user, String tardgetUserid, String productId){
		Tips tips = new Tips("成功");
		List<XmGroupVo> pgroups=new ArrayList<>();
		boolean isAdm=this.checkUserIsProductAdm(productId, user.getUserid());
		if(!isAdm){
			pgroups=this.getProductGroupVoList(productId);
			if(pgroups==null || pgroups.size()==0){
				return new Tips(false,"group-0","产品【"+productId+"】还未建立产品团队，请先进行团队成员维护");
			}
			boolean isHead=this.checkUserIsOtherUserTeamHeadOrAss(pgroups,user.getUserid(),tardgetUserid);
			if(!isHead){
				return new Tips(false,"not-head","您无权操作！产品经理、组长可以操作。");
			}
		}

		return tips;
	}


	public Tips checkIsProjectAdmOrTeamHeadOrAss(User user, String tardgetUserid, String projectId){
		Tips tips = new Tips("成功");
		List<XmGroupVo> pgroups=new ArrayList<>();
		boolean isAdm=this.checkUserIsProjectAdm(projectId, user.getUserid());
		if(!isAdm){
			pgroups=this.getProjectGroupVoList(projectId);
			if(pgroups==null || pgroups.size()==0){
				return new Tips(false,"group-0","项目【"+projectId+"】还未建立项目团队，请先进行团队成员维护");
			}
			boolean isHead=this.checkUserIsOtherUserTeamHeadOrAss(pgroups,user.getUserid(),tardgetUserid);
			if(!isHead){
				return new Tips(false,"not-head","您无权操作！项目经理、组长可以操作。");
			}
		}

		return tips;
	}


	public List<XmGroupVo> getProductGroupVoList(String productId) {
		List<XmGroupVo>	groupVoList=new ArrayList<>();
		List<XmGroupVo>	groupVoList2  = groupCacheService.getProductGroups(productId);
		if(groupVoList2==null||groupVoList2.size()==0) {

			XmGroup group = new XmGroup();
			group.setProductId(productId);
			List<XmGroup> groupList = this.selectListByWhere(group);
			if(groupList==null || groupList.size()==0) {
				groupCacheService.putProductGroups(productId, groupVoList);
				return groupVoList;
			}
			List<XmGroupUser> groupUserList=this.xmGroupUserService.selectGroupUserListByProductId(productId);
			if(groupUserList==null || groupUserList.size()==0) {
				//groupCacheService.putProjectGroups(productId, groupVoList);
				//return groupVoList;
			}
			groupList.forEach(g -> {
				XmGroupVo gvo = new XmGroupVo();
				BeanUtils.copyProperties(g,gvo);
				List<XmGroupUser> groupUsers=new ArrayList<>();
				groupUserList.forEach(gu -> {
					if(g.getId().equals(gu.getGroupId())) {
						groupUsers.add(gu);
					}
				});
				gvo.setGroupUsers(groupUsers );
				groupVoList.add(gvo);
			});
			groupCacheService.putProductGroups(productId, groupVoList);

			return groupVoList;
		}else {
			return groupVoList2;
		}

	}


	/** 请在此类添加自定义函数 */
	public List<XmGroupVo> getProjectGroupVoList(String projectId) {
		List<XmGroupVo>	groupVoList=new ArrayList<>();
		List<XmGroupVo>	groupVoList2  = groupCacheService.getProjectGroups(projectId);
		if(groupVoList2==null||groupVoList2.size()==0) {

			XmGroup group = new XmGroup();
			group.setProjectId(projectId);
			List<XmGroup> groupList = this.selectListByWhere(group);
			if(groupList==null || groupList.size()==0) {
				groupCacheService.putProjectGroups(projectId, groupVoList);
				return groupVoList;
			}
			List<XmGroupUser> groupUserList=this.xmGroupUserService.selectGroupUserListByProjectId(projectId);
			if(groupUserList==null || groupUserList.size()==0) {
				//groupCacheService.putProjectGroups(projectId, groupVoList);
				//return groupVoList;
			}
			groupList.forEach(g -> {
				XmGroupVo gvo = new XmGroupVo();
				BeanUtils.copyProperties(g,gvo);
				List<XmGroupUser> groupUsers=new ArrayList<>();
				groupUserList.forEach(gu -> {
					if(g.getId().equals(gu.getGroupId())) {
						groupUsers.add(gu);
					}
				});
				gvo.setGroupUsers(groupUsers );
				groupVoList.add(gvo);
			});
			groupCacheService.putProjectGroups(projectId, groupVoList);

			return groupVoList;
		}else {
			return groupVoList2;
		}

	}

	public boolean checkUserIsProjectAdm(XmProject xmProject,String userid){
		if(xmProject==null){
			return false;
		}
		Map<String,String> map=this.getProjectAdmUsers(xmProject);
		if(map.containsKey(userid)){
			return true;
		}
		return false;
	}
	public boolean checkUserIsProjectAdm(String projectId,String userid){
		XmProject xmProject=xmProjectService.getProjectFromCache(projectId);
		if(xmProject==null){
			return false;
		}
		Map<String,String> map=this.getProjectAdmUsers(xmProject);
		if(map.containsKey(userid)){
			return true;
		}
		return false;
	}

	public boolean checkUserIsProductAdm(XmProduct xmProduct,String userid){
		if(xmProduct==null){
			return false;
		}
		Map<String,String> map=this.getProductAdmUsers(xmProduct);
		if(map.containsKey(userid)){
			return true;
		}
		return false;
	}
	public boolean checkUserIsProductAdm(String productId,String userid){
		XmProduct xmProduct=xmProductService.getProductFromCache(productId);
		if(xmProduct==null){
			return false;
		}
		Map<String,String> map=this.getProductAdmUsers(xmProduct);
		if(map.containsKey(userid)){
			return true;
		}
		return false;
	}

	/**
	 * 新增项目时，同时新增项目团队及小组组员等
	 * @param projectId
	 * @param xmGroupVoList
	 */
	@Transactional
	public void addGroups(String projectId,List<XmGroupVo> xmGroupVoList) {
		List<XmGroup> groups=new ArrayList<>();
		List<XmGroupUser> groupUsers=new ArrayList<>();
		for (XmGroupVo xmGroupVo : xmGroupVoList) {
			XmGroup group=new XmGroup();
			xmGroupVo.setId(this.createKey("id"));
			BeanUtils.copyProperties(xmGroupVo, group);
			groups.add(group);
			List<XmGroupUser> groupUsersTemp=xmGroupVo.getGroupUsers();
			if(groupUsersTemp==null || groupUsersTemp.size()==0) {
				continue;
			}else {
				for (XmGroupUser xmGroupUser : groupUsersTemp) {
					xmGroupUser.setGroupId(group.getId());
					xmGroupUser.setJoinTime(new Date());
					xmGroupUser.setStatus("0");
					groupUsers.add(xmGroupUser);
				}

			}
		}
		if(groups.size()>0) {
			this.batchInsert(groups);
			User u=LoginUtils.getCurrentUserInfo();

			for (XmGroupVo group : xmGroupVoList) {
				List<XmGroupUser> users=group.getGroupUsers();
				List<Map<String,Object>> umaps=new ArrayList<>();
				for (XmGroupUser xmGroupUser : users) {
					Map<String,Object> u2=new HashMap<>();
					u2.put("userid", xmGroupUser.getUserid());
					u2.put("username", xmGroupUser.getUsername());
					umaps.add(u2);

				}
				pushMsgService.pushChannelGroupCreateMsg(u.getBranchId(),group.getProjectId(),group.getId(), group.getId(), group.getGroupName(), u.getUserid(), u.getUsername(), umaps,"新增小组["+group.getGroupName()+"]及以下组员："+StringUtils.arrayToDelimitedString(group.getGroupUsers().toArray(), ","));
				if(group.getGroupUsers()!=null && group.getGroupUsers().size()>0) {
					xmRecordService.addXmGroupRecord(projectId, group.getId(),"项目-团队-新增小组", "新增小组["+group.getGroupName()+"]及以下组员："+StringUtils.arrayToDelimitedString(group.getGroupUsers().toArray(), ","),JSON.toJSONString(group),null);

				}else {
					xmRecordService.addXmGroupRecord(projectId, group.getId(),"项目-团队-新增小组", "新增小组["+group.getGroupName()+"]",JSON.toJSONString(group),null);

				}

			}
		}
		if(groupUsers.size()>0) {
			xmGroupUserService.batchInsert(groupUsers);
		}
		groupCacheService.putProjectGroups(projectId, null);
	}
	/*
	 * 更新项目团队
	 * 1.项目管理者、项目创建人可以添加项目小组
	 * 2.小组长可以添加本小组成员、清空本小组所有成员
	 * 3.项目管理者、创建人可以任意添加删除成员
	 * 4.项目管理者、创建人可以调整小组组长
	 * 5.如果更改了产品编号，同步更改小组用户归属产品
	 */
	@Transactional
	public Tips updateGroup(XmGroup group, XmGroup groupDb) {
		Tips tips=new Tips("成功");
		super.updateSomeFieldByPk(group);
		if(ObjectTools.isEmpty(group)){
			group.setProductId("");
		}
		if(ObjectTools.isEmpty(groupDb.getProductId())){
			groupDb.setProductId("");
		}
		if(!group.getProductId().equals(groupDb.getProductId())){
			this.xmGroupUserService.lambdaUpdate().set(XmGroupUser::getProductId,group.getProductId()).eq(XmGroupUser::getGroupId,group.getId()).update();
		}
		this.sumParents(group);
		return tips;
	}


	public void pushMsg(User user, XmGroup g, List<Map<String,Object>> users){
		pushMsgService.pushChannelGroupCreateMsg(user.getBranchId(),g.getProjectId(),g.getId(), g.getId(), g.getGroupName(), user.getUserid(), user.getUsername(), users,"新增小组["+g.getGroupName()+"]及以下组员："+ users.stream().map(i->(String)i.get("username")).collect(Collectors.joining(",")));

	}

	/**
	 * 获取用户在某个项目中的组
	 * @param projectId
	 * @param userid
	 * @return
	 */
	public List<XmGroupVo> getUserGroupsByProjectId(String projectId, String userid){
		List<XmGroupVo> xmGroupVoList=this.getProjectGroupVoList(projectId);
		return this.getUserGroups(xmGroupVoList, userid);
	}
	/**
	 * 获取用户在某个产品中的组
	 * @param productId
	 * @param userid
	 * @return
	 */
	public List<XmGroupVo> getUserGroupsByProductId(String productId, String userid){
		List<XmGroupVo> xmGroupVoList=this.getProductGroupVoList(productId);
		return this.getUserGroups(xmGroupVoList, userid);
	}

	/**
	 * 检查用户是否在一些组中任意个组当组长
	 * @param xmGroupVoList
	 * @param teamHeadUserid
	 * @return
	 */
	public boolean  checkUserIsHeadInGroups(List<XmGroupVo> xmGroupVoList, String teamHeadUserid){
		if(xmGroupVoList==null || xmGroupVoList.size()==0)return false;
		if(!StringUtils.hasText(teamHeadUserid)){
			return false;
		}
		for (XmGroupVo xmGroupVo : xmGroupVoList) {
			if(teamHeadUserid.equals(xmGroupVo.getLeaderUserid())){
				return true;
			}

		}
		return false;
	}

	/**
	 * 检查用户是否在指定的小组中做组长
	 * @param xmGroupVoList
	 * @param groupId
	 * @param teamHeadUserid
	 * @return
	 */
	public boolean  checkUserIsHeadInGroup(List<XmGroupVo> xmGroupVoList, String groupId, String teamHeadUserid){
		if(xmGroupVoList==null || xmGroupVoList.size()==0)return false;
		if(!StringUtils.hasText(teamHeadUserid)){
			return false;
		}
		if(!StringUtils.hasText(groupId)){
			return false;
		}

		for (XmGroupVo xmGroupVo : xmGroupVoList) {
			if(groupId.equals(xmGroupVo.getId())){
				if(teamHeadUserid.equals(xmGroupVo.getLeaderUserid())){
					return true;
				}
			}
		}
		return false;
	}
	public List<XmGroupVo> getUserGroups(List<XmGroupVo> xmGroupVoList, String userid){
		List<XmGroupVo> userGroups=new ArrayList<>();
		if(xmGroupVoList==null) {
			return userGroups;
		}
		for (XmGroupVo g : xmGroupVoList) {
			if(userid.equals(g.getLeaderUserid())||userid.equals(g.getAssUserid())){
				userGroups.add(g);
			}else{
				List<XmGroupUser> gus=g.getGroupUsers();
				boolean exists=false;
				if(gus==null) {
					continue;
				}
				for (XmGroupUser gu : gus) {
					if(userid.equals(gu.getUserid())) {
						exists=true;
						break;
					}
				}
				if(exists) {
					userGroups.add(g);

				}
			}
		}
		return userGroups;
	}
	public boolean checkUserExistsProjectGroup(String projectId, String userid){
		List<XmGroupVo> userGroups= getUserGroupsByProjectId(projectId,userid);
		return userGroups!=null && userGroups.size()>0;

	}
	public boolean checkUserExistsProductGroup(String productId, String userid){
		List<XmGroupVo> userGroups= getUserGroupsByProductId(productId,userid);
		return userGroups!=null && userGroups.size()>0;

	}
	public boolean checkUserExistsGroup(List<XmGroupVo> userGroups, String userid){
		List<XmGroupVo> userGroups2= this.getUserGroups(userGroups, userid);
		return userGroups2!=null && userGroups2.size()>0;
	}
	/**
	 * 检查某个人是否为指定的小组的组长
	 * @param xmGroupVo
	 * @param headUserid
	 * @return
	 */
	public boolean checkUserIsTeamHead(XmGroupVo xmGroupVo, String headUserid){
		if(xmGroupVo==null){
			return false;
		}
		if(headUserid.equals(xmGroupVo.getLeaderUserid())){
			return true;
		}
		return false;

	}
	/**
	 * 检查某个人是否为指定的小组的组长\副组长\助理
	 * @param xmGroupVo
	 * @param headUserid
	 * @return
	 */
	public boolean checkUserIsTeamHeadOrAss(XmGroupVo xmGroupVo, String headUserid){
		if(xmGroupVo==null){
			return false;
		}
		if(headUserid.equals(xmGroupVo.getLeaderUserid())||headUserid.equals(xmGroupVo.getAssUserid())){
			return true;
		}
		return false;

	}
	/**
	 * 检查某个人是否另外一个人的组长
	 * @param xmGroupVoList
	 * @param headUserid
	 * @param memUserid
	 * @return
	 */
	public boolean checkUserIsOtherUserTeamHead(List<XmGroupVo> xmGroupVoList, String headUserid, String memUserid){
		if(xmGroupVoList==null || xmGroupVoList.size()==0) {
			return false;
		}

		List<XmGroupVo> userGroups=this.getUserGroups(xmGroupVoList, memUserid);
		if(userGroups==null || userGroups.size()==0) {
			return false;
		}
		for (XmGroupVo ug : userGroups) {
			if(headUserid.equals(ug.getLeaderUserid())){
				return true;
			}else{
				Optional<XmGroupVo> optional=xmGroupVoList.stream().filter(i->i.getId().equals(ug.getPgroupId())).findAny();
				while (optional!=null  && optional.isPresent()){
					XmGroupVo g=optional.get();
					if(headUserid.equals(g.getLeaderUserid())){
						return true;
					}else{
						optional=xmGroupVoList.stream().filter(i->i.getId().equals(g.getPgroupId())).findAny();
					}

				}
			}
		}
		return false;
	}

	/**
	 * 检查某个人是否另外一个人的组长
	 * @param xmGroupVoList
	 * @param headUserid 检查headUserid是不是memUserid的组长
	 * @param memUserid
	 * @return
	 */
	public boolean checkUserIsOtherUserTeamHeadOrAss(List<XmGroupVo> xmGroupVoList, String headUserid, String memUserid){
		if(xmGroupVoList==null || xmGroupVoList.size()==0) {
			return false;
		}

		List<XmGroupVo> userGroups=this.getUserGroups(xmGroupVoList, memUserid);
		if(userGroups==null || userGroups.size()==0) {
			return false;
		}
		for (XmGroupVo ug : userGroups) {
			if(headUserid.equals(ug.getLeaderUserid())||headUserid.equals(ug.getAssUserid())){
				return true;
			}else{
				Optional<XmGroupVo> optional=xmGroupVoList.stream().filter(i->i.getId().equals(ug.getPgroupId())).findAny();
				while (optional!=null  && optional.isPresent()){
					XmGroupVo g=optional.get();
					if(headUserid.equals(g.getLeaderUserid())||headUserid.equals(g.getAssUserid())){
						return true;
					}else{
						optional=xmGroupVoList.stream().filter(i->i.getId().equals(g.getPgroupId())).findAny();
					}

				}
			}
		}
		return false;
	}
	public  List<XmGroupUser> getProjectManagers(List<XmGroupVo> xmGroupVoList){
		for (XmGroupVo g : xmGroupVoList) {
			if("nbxmjl".equals(g.getPgTypeId())) {
				return g.getGroupUsers();
			}

		}
		return null;

	}



	/**
	 * 检测某个用户是否属于项目组的内部管理团队成员，内部管理组成员
	 * @param xmGroupVoList
	 * @param pmUserid
	 * @return
	 */
	public boolean checkUserIsProjectManager(List<XmGroupVo> xmGroupVoList , String pmUserid) {
		if(xmGroupVoList==null || xmGroupVoList.size()==0) {
			return false;
		}
		List<XmGroupUser> getProjectManagers=this.getProjectManagers(xmGroupVoList);
		if(getProjectManagers==null || getProjectManagers.size()==0) {
			return false;
		}
		for (XmGroupUser user : getProjectManagers) {
			if( user.getUserid().equals(pmUserid)) {
				return true;
			}

		}
		return false;
	}


	public List<XmGroupVo> getProjectGroupVoListByIterationId(String iterationId) {

		List<XmProductProjectLink> list=this.xmProductProjectLinkService.selectListByIterationId(iterationId);
		List<XmGroupVo> datas=new ArrayList<>();
		if(list!=null && list.size()>0){
			for (XmProductProjectLink productProjectLink : list) {
				List<XmGroupVo> data0=this.getProjectGroupVoList(productProjectLink.getProjectId());
				if(data0!=null && data0.size()>0){
					datas.addAll(data0);
				}
			}
		}
		return datas;
	}



	public List<XmGroup> parentIdPathsCalcBeforeSave(List<XmGroup> nodes) {
		List<XmGroup> noExistsList=nodes.stream().filter(i->!nodes.stream().filter(k->k.getId().equals(i.getPgroupId())).findAny().isPresent()).collect(Collectors.toList());
		noExistsList=noExistsList.stream().filter(i->StringUtils.hasText(i.getPgroupId())).collect(Collectors.toList());
		Map<String,String> hadCalcMap=new HashMap<>();
		for (XmGroup node : noExistsList) {
			if(hadCalcMap.containsKey(node.getPgroupId())){
				String idPaths=hadCalcMap.get(node.getPgroupId());
				node.setPidPaths(idPaths+node.getId()+",");
			}else{
				this.parentIdPathsCalcBeforeSave(node);
				String idPaths=node.getPidPaths();
				idPaths=idPaths.substring(0,idPaths.length()-node.getId().length()-1);
				hadCalcMap.put(node.getPgroupId(),idPaths);
			}
		}
		for (XmGroup node : nodes) {
			if(!StringUtils.hasText(node.getPgroupId())){
				node.setPidPaths("0,"+node.getId()+",");
				continue;
			}
			if(hadCalcMap.containsKey(node.getPgroupId())){
				String idPaths=hadCalcMap.get(node.getPgroupId());
				node.setPidPaths(idPaths+node.getId()+",");
			}else{
				List<XmGroup> pnodeList=this.getParentList(node,nodes);
				if(pnodeList==null ||pnodeList.size()==0){
					node.setPidPaths("0,"+node.getPgroupId()+","+node.getId()+",");
					continue;
				}
				XmGroup topParent=pnodeList.get(pnodeList.size()-1);
				String idPath="0,";
				if(hadCalcMap.containsKey(topParent.getPgroupId())){
					idPath=hadCalcMap.get(topParent.getPgroupId());
				}
				for (int i = pnodeList.size() - 1; i >= 0; i--) {
					idPath=idPath+pnodeList.get(i).getId()+",";
				}
				node.setPidPaths(idPath+node.getId()+",");
			}
		}
		for (XmGroup node : nodes) {
			String idPaths=node.getPidPaths();
			String[] idpss=idPaths.split(",");
			node.setLvl(idpss.length-1);
		}
		return nodes;
	}

	public static void main(String[] args) {
		String idpaths="0,1,2,3,";
		String[] idpss=idpaths.split(",");
		int lvl=idpss.length;

	}

	public Tips checkProjectStatus(XmProject xmProject){
		Tips tips=new Tips("成功");
		if(xmProject==null){
			tips.setErrMsg("prj-0","项目已不存在");
		} else if("4".equals(xmProject.getStatus())){
			tips.setErrMsg("prj-status-4","项目暂停中，不能操作");
		} else if("9".equals(xmProject.getStatus())){
			tips.setErrMsg("prj-status-9","项目已关闭，不能操作");
		}
		return tips;
	}

	public Tips checkProductStatus(XmProduct xmProduct){
		Tips tips=new Tips("成功");
		if(xmProduct==null){
			tips.setErrMsg("product-0","产品已不存在");
		} else if("3".equals(xmProduct.getPstatus())){
			tips.setErrMsg("pstatus-3","产品已关闭，不能操作");
		}
		return tips;
	}
	public Tips checkHasProjectEditQx(User user,XmProject xmProject){
		Tips tips=new Tips("成功");
		Map<String,String> pmUserMap=new HashMap<>();
		pmUserMap.put(xmProject.getCreateUserid(),xmProject.getCreateUsername());
		pmUserMap.put(xmProject.getPmUserid(),xmProject.getPmUsername());
		pmUserMap.put(xmProject.getAdmUserid(),xmProject.getAdmUsername());
		pmUserMap.put(xmProject.getAssUserid(),xmProject.getAssUsername());
		if(!pmUserMap.containsKey(user.getUserid())){
			tips.setErrMsg("no-project-edit-qx","您不是项目【"+xmProject.getName()+"】的管理人员，无权修改项目信息。");
		}
		return tips;
	}


	public Map<String,String> getProductAdmUsers(XmProduct xmProductDb){

		Map<String,String> proUsersMap=new HashMap<>();
		proUsersMap.put(xmProductDb.getPmUserid(),xmProductDb.getPmUsername());
		proUsersMap.put(xmProductDb.getAssUserid(),xmProductDb.getAssUsername());
		proUsersMap.put(xmProductDb.getAdmUserid(),xmProductDb.getAdmUsername());
		return proUsersMap;
	}
	public Tips checkHasProductEditQx(User user,XmProduct xmProductDb) {
		Tips tips=new Tips("成功");
		Map<String,String> proUsersMap=this.getProductAdmUsers(xmProductDb);
		if(!proUsersMap.containsKey(user.getUserid())){
			tips.setErrMsg("no-product-edit-qx","您不是产品【"+xmProductDb.getProductName()+"】的管理人员，无权修改产品信息。");
		}
		return tips;
	}

	public Tips checkHasEditProdcutGroupQx(User user, XmGroup group, XmGroup groupDb, XmProduct xmProductDb){
		Tips tips = new Tips("成功");
		if(groupDb==null){
			tips.setErrMsg("data-0","该小组已不存在");
			return tips;
		}
		Map<String,String> proUsersMap=this.getProductAdmUsers(xmProductDb);

		if(StringUtils.hasText(group.getAssUserid()) && !group.getAssUserid().equals(groupDb.getAssUserid())){
			if(!proUsersMap.containsKey(user.getUserid()) && !user.getUserid().equals(groupDb.getLeaderUserid())){
				tips.setErrMsg("no-qx-change-pro-g-assUserid","您无权限修改小组助理.组长及以上人员可以修改。");
			}
		}
		if(StringUtils.hasText(group.getLeaderUserid()) && !group.getLeaderUserid().equals(groupDb.getLeaderUserid())){
			if(!proUsersMap.containsKey(user.getUserid())){
				tips.setErrMsg("no-qx-change-pro-g-leaderUserid","您无权限修改小组组长.产品级助理及以上人员可以修改。");
			}
		}
		if(StringUtils.hasText(group.getPgroupId()) && !group.getPgroupId().equals(groupDb.getPgroupId())){
			if(!proUsersMap.containsKey(user.getUserid())){
				tips.setErrMsg("no-qx-change-g-pgroupId","您无权限修改小组归属上级单位.产品级助理及以上人员可以修改。");
			}
		}
		return tips;
	}

	public Map<String,String> getProjectAdmUsers(XmProject xmProject){
		Map<String,String> pmUserMap=new HashMap<>();
		pmUserMap.put(xmProject.getCreateUserid(),xmProject.getCreateUsername());
		pmUserMap.put(xmProject.getPmUserid(),xmProject.getPmUsername());
		pmUserMap.put(xmProject.getAdmUserid(),xmProject.getAdmUsername());
		pmUserMap.put(xmProject.getAssUserid(),xmProject.getAssUsername());
		return pmUserMap;
	}
	public Tips checkHasEditProjectGroupQx(User user, XmGroup group, XmGroup groupDb, XmProject xmProject){
		Tips tips = new Tips("成功");
		if(groupDb==null){
			tips.setErrMsg("data-0","该小组已不存在");
			return tips;
		}

		Map<String,String> pmUserMap=this.getProjectAdmUsers(xmProject);
		if(!pmUserMap.containsKey(user.getUserid())){
			if(!user.getUserid().equals(groupDb.getLeaderUserid()) && !user.getUserid().equals(groupDb.getAssUserid())){
				tips.setErrMsg("not-prj-group-adm-user","您无权修改小组信息。 组长助理及以上人员有权限修改。");
				return tips;
			}
		}
		if(StringUtils.hasText(group.getAssUserid()) && !group.getAssUserid().equals(groupDb.getAssUserid())){
			if(!pmUserMap.containsKey(user.getUserid()) && !user.getUserid().equals(groupDb.getLeaderUserid())){
				tips.setErrMsg("no-qx-change-prj-g-assUserid","您无权限修改小组助理.组长及以上人员可以修改。");
				return tips;
			}
		}
		if(StringUtils.hasText(group.getLeaderUserid()) && !group.getLeaderUserid().equals(groupDb.getLeaderUserid())){
			if(!pmUserMap.containsKey(user.getUserid())){
				tips.setErrMsg("no-qx-change-prj-g-leaderUserid","您无权限修改小组组长.项目级助理及以上人员可以修改。");
				return tips;
			}
		}
		if(StringUtils.hasText(group.getPgroupId()) && !group.getPgroupId().equals(groupDb.getPgroupId())){
			if(!pmUserMap.containsKey(user.getUserid())){
				tips.setErrMsg("no-qx-change-prj-g-pgroupId","您无权限修改小组归属上级单位.项目级助理及以上人员可以修改。");
				return tips;
			}
		}

		return tips;
	}

	public Tips parentIdPathsCalcBeforeSave(XmGroup currNode) {
		Tips tips = new Tips("成功");
		if (!StringUtils.hasText(currNode.getPgroupId()) || "0".equals(currNode.getPgroupId())) {
			currNode.setPidPaths("0," + currNode.getId() + ",");
			currNode.setLvl(1);
			return tips;
		} else {
			List<XmGroup> parentList=this.getParentList(currNode);
			if(parentList==null ||parentList.size()==0){
				currNode.setPidPaths("0,"+currNode.getPgroupId()+","+currNode.getId()+",");
				currNode.setLvl(2);
				return tips;
			}
			String idPath="0,";
			for (int i = parentList.size() - 1; i >= 0; i--) {
				idPath=idPath+parentList.get(i).getId()+",";
			}
			currNode.setPidPaths(idPath+currNode.getId()+",");

			String idPaths=currNode.getPidPaths();
			String[] idpss=idPaths.split(",");
			currNode.setLvl(idpss.length-1);
		}
		return tips;
	}

	private List<XmGroup> getParentList(XmGroup currNode){
		List<XmGroup> parentList=new ArrayList<>();
		XmGroup current=currNode;
		while (true){
			if(!StringUtils.hasText(current.getPgroupId()) || "0".equals(current.getPgroupId())){
				return parentList;
			}
			XmGroup query=new XmGroup();
			query.setId(current.getPgroupId());
			current=this.selectOneObject(query);
			if(current==null){
				return parentList;
			}
			parentList.add(current);
		}
	}

	private List<XmGroup> getParentList(XmGroup currNode, List<XmGroup> nodes){
		List<XmGroup> parentList=new ArrayList<>();
		XmGroup current=currNode;
		while (true){
			if(!StringUtils.hasText(current.getPgroupId()) || "0".equals(current.getPgroupId())){
				return parentList;
			}
			XmGroup query=new XmGroup();
			query.setId(current.getPgroupId());
			Optional<XmGroup> optional=nodes.stream().filter(i->i.getId().equals(query.getId())).findFirst();
			if(optional.isPresent()){
				current=optional.get();
				parentList.add(current);
			}else{
				return parentList;
			}

		}
	}


	@Transactional
	public void sumParents(XmGroup node){
		String id=node.getId();
		String pidPaths=node.getPidPaths();
		if(!StringUtils.hasText(pidPaths)){
			return;
		}
		if(!pidPaths.startsWith("0,")){
			return;
		}
		if("0".equals(node.getNtype())&&pidPaths.endsWith(id+",")){
			pidPaths=pidPaths.substring(2,pidPaths.indexOf(id));
		}else{
			pidPaths=pidPaths.substring(2);
		}

		if(!StringUtils.hasText(pidPaths)){
			return;
		}
		String[] pidPathss=pidPaths.split(",");
		List<String> pidPathsList=new ArrayList<>();
		for (int i = pidPathss.length-1; i >=0; i--) {
			pidPathsList.add(pidPathss[i]);
		}
		if(pidPathsList.size()>0){//暂时不处理，改由前端手段更新
			//super.update("sumParents",pidPathsList	);
		}

	}
	@Transactional
	public void batchSumParents(List<XmGroup> xmTasks) {
		List<Set<String>> list=new ArrayList<>();
		for (XmGroup node : xmTasks) {
			String id=node.getId();
			String pidPaths=node.getPidPaths();
			if(!StringUtils.hasText(pidPaths)){
				continue;
			}
			if(!pidPaths.startsWith("0,")){
				continue;
			}
			if("0".equals(node.getNtype())){
				pidPaths=pidPaths.substring(2,pidPaths.indexOf(id));
			}else{
				pidPaths=pidPaths.substring(2);
			}

			if(!StringUtils.hasText(pidPaths)){
				continue;
			}
			String[] pidPathss=pidPaths.split(",");
			for (int i = 0; i <pidPathss.length; i++) {
				if(list.size()<=i){
					list.add(new HashSet<>());
				}
				Set<String> set=list.get(i);
				set.add(pidPathss[i]);
			}
			if(list.size()<=0){
				return;
			}
			Set<String> allSet=new HashSet<>();
			for (int i = list.size() - 1; i >= 0; i--) {
				Set<String> set=list.get(i);
				if(set.size()>0){
					List<String> ids=set.stream().filter(k->!allSet.contains(k)).collect(Collectors.toList());
					if(ids.size()>0){
						allSet.addAll(ids.stream().collect(Collectors.toSet()));
						//super.update("batchSumParents", ids);////暂时不处理，改由前端手段更新
					}

				}

			}


		}

	}


	/**
	 * 检查是否能删除干净所有儿子孙子节点。
	 * @param delNode 当前删除节点
	 * @param delNodes 本批量需要删除的全部节点
	 * @return
	 */
	public boolean checkCanDelAllChild(XmGroup delNode, List<XmGroup> delNodes) {
		if(delNode==null){
			return true;
		}
		if(delNode.getChildrenCnt()==null||delNode.getChildrenCnt()<=0){
			return true;
		}
		List<XmGroup> childList=delNodes.stream().filter(i->delNode.getId().equals(i.getPgroupId())).collect(Collectors.toList());
		if(childList==null||childList.size()<delNode.getChildrenCnt()){
			return false;
		}
		for (XmGroup n : childList) {
			if (!this.checkCanDelAllChild(n, delNodes)) {
				return false;
			}
		}
		return true;

	}

	@Transactional
	public void doDeleteByPk(XmGroup xmGroup, XmGroup groupDb) {
		super.deleteByPk(xmGroup);
		this.sumParents(groupDb);
	}

	@Transactional
	public void doBatchDeleteProductGroups(List<XmGroup> canDelNodes) {
		super.batchDelete(canDelNodes);
		batchSumParents(canDelNodes);
	}

	@Transactional
	public void doBatchDeleteProjectGroups(List<XmGroup> canDelNodes) {
		super.batchDelete(canDelNodes);
		batchSumParents(canDelNodes);
	}

	public XmGroupVo getProductGroupFromCache(String productId, String groupId) {
		XmGroupVo groupVo=groupCacheService.getProductGroup(productId,groupId);
		if(groupVo==null){
			XmGroup group=this.selectOneObject(new XmGroup(groupId));
			if(group==null){
				return null;
			}else{
				XmGroupUser xmGroupUser=new XmGroupUser();
				xmGroupUser.setGroupId(groupId);
				List<XmGroupUser> users=this.xmGroupUserService.selectListByWhere(xmGroupUser);
				XmGroupVo xmGroupVo=new XmGroupVo();
				BeanUtils.copyProperties(group,xmGroupVo);
				xmGroupVo.setGroupUsers(users);
				this.groupCacheService.putProductGroup(xmGroupVo);
				return xmGroupVo;
			}
		}else {
			return groupVo;
		}
	}
	public XmGroupVo getProjectGroupFromCache(String projectId, String groupId) {
		XmGroupVo groupVo=groupCacheService.getProjectGroup(projectId,groupId);
		if(groupVo==null){
			XmGroup group=this.selectOneObject(new XmGroup(groupId));
			if(group==null){
				return null;
			}else{
				XmGroupUser xmGroupUser=new XmGroupUser();
				xmGroupUser.setGroupId(groupId);
				List<XmGroupUser> users=this.xmGroupUserService.selectListByWhere(xmGroupUser);
				XmGroupVo xmGroupVo=new XmGroupVo();
				BeanUtils.copyProperties(group,xmGroupVo);
				xmGroupVo.setGroupUsers(users);
				this.groupCacheService.putProjectGroup(xmGroupVo);
				return xmGroupVo;
			}
		}else {
			return groupVo;
		}
	}

	public List<Map<String, Object>> selectListMapByWhere(IPage page, QueryWrapper<XmGroup> qw, Map<String, Object> ext) {
		return baseMapper.selectListMapByWhere(page,qw,ext);
	}

	public boolean checkIsSameProject(String projectId,String ...userids) {
		if(ObjectTools.isEmpty(projectId)){
			return false;
		}
		List<XmGroupVo> xmGroupVoList=this.getProjectGroupVoList(projectId);
		for (String userid : userids) {
			boolean exists=this.checkUserExistsGroup(xmGroupVoList,userid);
			if(!exists){
				return false;
			}
		}
		return true;
	}
}

