package com.mdp.arc.pub.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.arc.pub.entity.Category;
import com.mdp.arc.pub.entity.CategoryQx;
import com.mdp.arc.pub.mapper.CategoryMapper;
import com.mdp.core.entity.LangTips;
import com.mdp.core.entity.Tips;
import com.mdp.core.query.QueryTools;
import com.mdp.core.service.BaseService;
import com.mdp.core.utils.ObjectTools;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br> 
 * 组织 com.mdp  顶级模块 arc 大模块 pub 小模块 <br>
 * 实体 Category 表 arc_category 当前主键(包括多主键): id; 
 ***/
@Service("mdp.arc.pub.service.CategoryService")
@DS("arc-ds")
public class CategoryService extends BaseService<CategoryMapper,Category> {
	static Logger logger =LoggerFactory.getLogger(CategoryService.class);

	@Autowired
	CategoryQxService qxService;

	/**
	 * 自定义查询，支持多表关联
	 * @param page 分页条件
	 * @param ew 一定要，并且必须加@Param("ew")注解
	 * @param ext 如果xml中需要根据某些值进行特殊处理，可以通过这个进行传递，非必须，注解也可以不加
	 * @return
	 */
	public List<Map<String,Object>> selectListMapByWhere(IPage page, QueryWrapper ew, Map<String,Object> ext){
		return baseMapper.selectListMapByWhere(page,ew,ext);
	}


	/** 请在此类添加自定义函数 */

	public  int insert(Category category) {
		category.setPaths(calcPidString(category));
		int i= super.insert(category);
		return i;

	}

	public Tips parentIdPathsCalcBeforeSave(Category currNode) {
		Tips tips = new Tips("成功");
		if (!StringUtils.hasText(currNode.getPid()) || "0".equals(currNode.getPid())) {
			currNode.setPaths("0," + currNode.getId() + ",");
			currNode.setPid("0");
			return tips;
		} else {
			List<Category> parentList=this.getParentList(currNode);
			if(parentList==null ||parentList.size()==0){
				currNode.setPaths("0,"+currNode.getId()+",");
				currNode.setPid("0");
				return tips;
			}
			String idPath="0,";
			for (int i = parentList.size() - 1; i >= 0; i--) {
				idPath=idPath+parentList.get(i).getId()+",";
			}
			currNode.setPaths(idPath+currNode.getId()+",");

			String idPaths=currNode.getPaths();
			String[] idpss=idPaths.split(",");
		}
		return tips;
	}

	public List<Category> parentIdPathsCalcBeforeSave(List<Category> nodes) {
		List<Category> noExistsList=nodes.stream().filter(i->!nodes.stream().filter(k->k.getId().equals(i.getPid())).findAny().isPresent()).collect(Collectors.toList());
		noExistsList=noExistsList.stream().filter(i-> StringUtils.hasText(i.getPid()) && !"0".equals(i.getPid())).collect(Collectors.toList());
		Map<String,String> hadCalcMap=new HashMap<>();
		for (Category node : noExistsList) {
			if(hadCalcMap.containsKey(node.getPid())){
				String idPaths=hadCalcMap.get(node.getPid());
				node.setPaths(idPaths+node.getId()+",");
			}else{
				this.parentIdPathsCalcBeforeSave(node);
				String idPaths=node.getPaths();
				idPaths=idPaths.substring(0,idPaths.length()-node.getId().length()-1);
				hadCalcMap.put(node.getPid(),idPaths);
			}
		}
		for (Category node : nodes) {
			if(!StringUtils.hasText(node.getPid())||"0".equals(node.getPid())){
				node.setPaths("0,"+node.getId()+",");
				node.setPid("0");
				continue;
			}
			if(hadCalcMap.containsKey(node.getPid())){
				String idPaths=hadCalcMap.get(node.getPid());
				node.setPaths(idPaths+node.getId()+",");
			}
		}

		for (Category node : nodes) {
			if(!StringUtils.hasText(node.getPid())||"0".equals(node.getPid())){
				continue;
			}
			if(!hadCalcMap.containsKey(node.getPid())){
				List<Category> pnodeList=this.getParentList(node,nodes);
				if(pnodeList==null ||pnodeList.size()==0){//理论上不应该存在这种情况
					node.setPaths("0,"+node.getId()+",");
					node.setPid("0");
					continue;
				}
				Category topParent=pnodeList.get(pnodeList.size()-1);
				String idPath="0,";
				if(hadCalcMap.containsKey(topParent.getPid())){
					idPath=hadCalcMap.get(topParent.getPid());
				}
				for (int i = pnodeList.size() - 1; i >= 0; i--) {
					idPath=idPath+pnodeList.get(i).getId()+",";
				}
				node.setPaths(idPath+node.getId()+",");
			}
		}
		for (Category node : nodes) {
			String idPaths=node.getPaths();
			String[] idpss=idPaths.split(",");
		}
		return nodes;
	}


	private List<Category> getParentList(Category currNode){
		List<Category> parentList=new ArrayList<>();
		Category current=currNode;
		while (true){
			if(!StringUtils.hasText(current.getPid()) || "0".equals(current.getPid())){
				return parentList;
			}
			current=this.getById(current.getPid());
			if(current==null){
				return parentList;
			}
			parentList.add(current);
		}
	}

	private List<Category> getParentList(Category currNode,List<Category> nodes){
		List<Category> parentList=new ArrayList<>();
		Category current=currNode;
		while (true){
			if(!StringUtils.hasText(current.getPid()) || "0".equals(current.getPid())){
				return parentList;
			}
			Category query=new Category();
			query.setId(current.getPid());
			Optional<Category> optional=nodes.stream().filter(i->i.getId().equals(query.getId())).findFirst();
			if(optional.isPresent()){
				current=optional.get();
				parentList.add(current);
			}else{
				return parentList;
			}

		}
	}

	public String calcPidString(Category category){
		parentIdPathsCalcBeforeSave(category);
		return category.getPaths();
	}

	public   int updateByPk(Category category) {
		category.setPaths(calcPidString(category));
		int i= super.updateByPk(category);
		return i;
	}

	public   int[] batchDeleteByMyBranchId(List<Category> categorys,String branchId) {


		if(categorys==null || categorys.size()<=0) {
			return null;
		}

		int[] is= super.batchDelete(categorys);
		return is;
	}

	public   int deleteByPk(Category category) {

		int i= super.deleteByPk(category);
		return i;
	}
	@Transactional
	public void batchChangeParent(List<Category> categories,Category parent) {
		if(parent!=null){
			baseMapper.batchChangeParent(map("ids",categories.stream().map(i->i.getId()).collect(Collectors.toList()),"pid",parent.getId(),"paths",parent.getPaths()));
		}else {
			baseMapper.batchChangeParent(map("ids",categories.stream().map(i->i.getId()).collect(Collectors.toList()),"pid","0","paths","0,"));

		}
	}
	@Transactional
	public void batchSave(List<Category> collect) {
		if(collect!=null && collect.size()>0){
			this.parentIdPathsCalcBeforeSave(collect);
			this.batchInsert(collect);
		}
	}

	/**
	 *
	 * @param pcate
	 * @param checkType qry,edit,del
	 * @return
	 */
	public LangTips checkQx(Category pcate,String checkType){
		User user=LoginUtils.getCurrentUserInfo();
		String[] pids=pcate.getPaths().split(",");
		CategoryQx categoryQx= qxService.getOne(QueryTools.initQueryWrapper(CategoryQx.class).in("cate_id",pids));
		String op="qry".equals(checkType)?categoryQx.getOthQuery():("edit".equals(checkType)?categoryQx.getOthEdit():categoryQx.getOthDel());
		String roleids="qry".equals(checkType)?categoryQx.getQryRoleids():("edit".equals(checkType)?categoryQx.getEditRoleids():categoryQx.getDelRoleids());;
		String deptids="qry".equals(checkType)?categoryQx.getQryDeptids():("edit".equals(checkType)?categoryQx.getEditDeptids():categoryQx.getDelDeptids());;
		String userids="qry".equals(checkType)?categoryQx.getQryUserids():("edit".equals(checkType)?categoryQx.getEditUserids():categoryQx.getDelUserids());;


		String nroleids="qry".equals(checkType)?categoryQx.getNqRoleids():("edit".equals(checkType)?categoryQx.getNeRoleids():categoryQx.getNdRoleids());;
		String ndeptids="qry".equals(checkType)?categoryQx.getNqDeptids():("edit".equals(checkType)?categoryQx.getNeDeptids():categoryQx.getNdDeptids());;
		String nuserids="qry".equals(checkType)?categoryQx.getNqUserids():("edit".equals(checkType)?categoryQx.getNeUserids():categoryQx.getNdUserids());;
		if(categoryQx!=null){
			if("1".equals(op)){
				boolean roleCheckOk=false;
				boolean useridCheckOk=false;
				boolean deptidCheckOk=false;
				if(ObjectTools.isNotEmpty(roleids)){
					if(LoginUtils.hasAnyRoles(roleids.split(","))){
						roleCheckOk=true;
					}
				}
				if(ObjectTools.isNotEmpty(userids)){
					if(Arrays.stream(userids.split(",")).filter(k->user.getUserid().equals(k)).findAny().isPresent()){
						useridCheckOk=true;
					}
				}

				if(ObjectTools.isNotEmpty(deptids)){
					if(Arrays.stream(deptids.split(",")).filter(k->user.getDeptid().equals(k)).findAny().isPresent()){
						deptidCheckOk=true;
					}
				}
				boolean allowCheckOk=roleCheckOk||useridCheckOk||deptidCheckOk;
				boolean notAllow=false;
				if(ObjectTools.isNotEmpty(nroleids)){
					if(LoginUtils.hasAnyRoles(nroleids.split(","))){
						notAllow=true;
					}
				}
				if(ObjectTools.isNotEmpty(nuserids)){
					if(Arrays.stream(nuserids.split(",")).filter(k->user.getUserid().equals(k)).findAny().isPresent()){
						notAllow=true;
					}
				}

				if(ObjectTools.isNotEmpty(ndeptids)){
					if(Arrays.stream(ndeptids.split(",")).filter(k->user.getDeptid().equals(k)).findAny().isPresent()){
						notAllow=true;
					}
				}
				//禁止条款优先级最高
				//如果是被禁止的任一条款满足条件，则全部禁止
				if( notAllow ){
					return LangTips.errMsg("no-qx-edit","无权限在分类【%s】进行新增子分类、修改等操作",pcate.getName());
				}else if(!allowCheckOk){//如果被允许的任意条款满足，则允许
					if(ObjectTools.isNotEmpty(roleids)||ObjectTools.isNotEmpty(deptids)||ObjectTools.isNotEmpty(userids)){
						return LangTips.errMsg("no-qx-query","无权限查询");
					}
				}

			} else{
				return LangTips.errMsg("no-qx-edit","无权限在分类【%s】进行新增子分类、修改等操作",pcate.getName());

			}

		}
		return LangTips.okMsg();
	}

	public void updatecategoryTypeByPid(Category category) {
	}

	@Override
	public String createKey(String keyName) {
		return getSequenceService().getCommonNo("C{date62}{rands:4}");
	}
}

