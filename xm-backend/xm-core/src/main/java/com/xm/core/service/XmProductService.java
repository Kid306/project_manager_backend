package com.xm.core.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.err.BizException;
import com.mdp.core.service.BaseService;
import com.mdp.core.utils.ObjectTools;
import com.mdp.safe.client.entity.User;
import com.xm.core.entity.*;
import com.xm.core.mapper.XmProductMapper;
import com.xm.core.service.cache.XmProductCacheService;
import com.xm.core.vo.XmProductAddVo;
import com.xm.core.vo.XmProjectVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br> 
 * 组织 com.qqkj  顶级模块 oa 大模块 xm 小模块 <br>
 * 实体 XmProduct 表 XM.xm_product 当前主键(包括多主键): id; 
 ***/
@Service("xm.core.xmProductService")
public class XmProductService extends BaseService<XmProductMapper,XmProduct> {


	@Value("${mdp.platform-branch-id:platform-branch-001}")
	String platformBranchId="platform-branch-001";
	
	@Autowired
	XmProjectService xmProjectService;
	
	@Autowired
	XmMenuService xmMenuService;

	@Autowired
	XmProductCacheService xmProductCacheService;

	@Autowired
	XmProductProjectLinkService linkService;

	@Autowired
	XmProductStateService stateService;

	@Autowired
	private XmTaskService xmTaskService;
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

	/**
	 * 产品不直接根项目关联，此函数作废了
	 * @param productId
	 * @return
	 */
	@Deprecated
	public  long checkExistsProject(String productId) { 
		return 0;
	}
	public  long checkExistsMenu(String productId) {
		XmMenu p=new XmMenu();
		p.setProductId(productId);
		return xmMenuService.countByWhere(p);
	}
	public XmProduct getProductFromCache(String productId) {
		XmProduct productCahce=xmProductCacheService.getProduct(productId);
		if(productCahce==null) {
			productCahce = this.selectOneObject(new XmProduct(productId));
			xmProductCacheService.putProduct(productId, productCahce);
			return productCahce;
		}
		return productCahce;
	}
	public void clearCache(String productId){
		xmProductCacheService.clear(productId);
	}

	/**
	 * 连同产品关联的状态数据一起带出
	 * @return
	 */
	public List<Map<String, Object>> selectListMapByWhereWithState(IPage page, QueryWrapper ew, Map<String,Object> ext) {
		return baseMapper.selectListMapByWhereWithState( page,ew,ext);
	}

	@Transactional
    public XmProduct copyTo(User user, XmProductCopyVo xmProduct) {
		XmProduct pq=new XmProduct();
		pq.setId(xmProduct.getId());
		XmProduct xmProductDb=this.selectOneObject(pq);
		if(xmProductDb==null){
			throw new BizException("产品不存在");
		}

		if(!"1".equals(xmProductDb.getIsTpl())){
			if(!user.getBranchId().equals(xmProductDb.getBranchId())){
				throw new BizException("您无权复制其它组织的产品。");
			}
		}else{
			if(!user.getBranchId().equals(xmProductDb.getBranchId())){
				if(!"9".equals(xmProductDb.getShowOut())){
					throw new BizException("您无权复制其它组织的产品");
				}
			}
		}
		String isTpl=xmProduct.getIsTpl();
		XmProduct xmProductTo=new XmProduct();
		BeanUtils.copyProperties(xmProductDb,xmProductTo);
		xmProductTo.setProductName(xmProduct.getProductName());
		xmProductTo.setCode(xmProduct.getCode());
		if(!StringUtils.hasText(xmProduct.getCode())){
			xmProductTo.setCode(createProductCode(user.getBranchId()));
		}
		xmProductTo.setCreateUserid(user.getUserid());
		xmProductTo.setCreateUsername(user.getUsername());
		xmProductTo.setId(this.createProductId(xmProductTo.getCode()));
		xmProductTo.setBranchId(user.getBranchId());
		xmProductTo.setDeptid(user.getDeptid());
		xmProductTo.setDeptName(user.getDeptName());
		xmProductTo.setAdmUserid(user.getUserid());
		xmProductTo.setAdmUsername(user.getUsername());
		xmProductTo.setPmUserid(user.getUserid());
		xmProductTo.setPmUsername(user.getUsername());
		xmProductTo.setCtime(new Date());
		xmProductTo.setPstatus("0");
		xmProductTo.setIsTpl(isTpl);
		xmProductTo.setAssUserid(user.getUserid());
		xmProductTo.setAssUsername(user.getUsername());
		xmProductTo.setBizProcInstId(null);
		xmProductTo.setBizFlowState("0");
		xmProductTo.setLtime(new Date());
		xmProductTo.setDel("0");
		xmProductTo.setLocked("0");
		xmProductTo.setTplType(xmProduct.getTplType());
		if(xmProduct.getProductName().equals(xmProductDb.getProductName())){
			xmProductTo.setProductName(xmProduct.getProductName()+"(复制)");
		}

		xmProductTo.setShowOut("1".equals(xmProduct.getTplType())?"9":"6");
		this.insert(xmProductTo);
		Map<String,String>	newMenuIdMap=new HashMap<>();
		if("1".equals(xmProduct.getCopyMenu())){
			XmMenu mq=new XmMenu();
			mq.setProductId(xmProduct.getId());
			List<XmMenu> xmMenus=this.xmMenuService.selectListByWhere(mq);

			if(xmMenus!=null && xmMenus.size()>0){
				for (XmMenu node : xmMenus) {
					newMenuIdMap.put(node.getMenuId(),this.xmMenuService.createKey("id"));
				}
				for (XmMenu node : xmMenus) {
					String oldId=node.getMenuId();
					String newId=newMenuIdMap.get(oldId);
					node.setMenuId(newId);
					node.setProductId(xmProductTo.getId());
					if(ObjectTools.isEmpty(node.getPmenuId())||"0".equals(node.getPmenuId())){
						node.setPmenuId("0");
					}else{
						node.setPmenuId(newMenuIdMap.get(node.getPmenuId()));
					}

					node.setCtime(new Date());
					node.setMmUserid(user.getUserid());
					node.setMmUsername(user.getUsername());
					node.setIterationId(null);
				}
				this.xmMenuService.parentIdPathsCalcBeforeSave(xmMenus);
				this.xmMenuService.doBatchInsert(xmMenus);
			}
		}

		if("1".equals(xmProduct.getCopyPhase())){
			XmTask taskQ=new XmTask();
			taskQ.setProductId(xmProduct.getId());
			List<XmTask> xmTasks=this.xmTaskService.selectListByWhere(taskQ);
			// 这里要分析出所有的上级计划一并导入新的项目
			Set<String> taskIds=xmTasks.stream().map(k->k.getId()).collect(Collectors.toSet());
			Set<String> planIds=new HashSet<>();
			for (XmTask xmTask : xmTasks) {
				String pidPaths=xmTask.getPidPaths();
				String[] pids=pidPaths.split(",");
				for (String pid : pids) {
					if(!taskIds.contains(pid)){
						planIds.add(pid);
					}
				}

			}
			List<XmTask> plans=this.xmTaskService.listByIds(planIds);
			xmTasks.addAll(plans);

			//创建新项目
			XmProjectVo xmProjectVo=new XmProjectVo();
			BeanUtils.copyProperties(xmProductTo,xmProjectVo);
			xmProjectVo.setIsTpl(isTpl);
			xmProjectVo.setId(null);
			xmProjectVo.setCode(null);
			xmProjectVo.setName(xmProductTo.getProductName());
			XmProductProjectLink link=new XmProductProjectLink();
			link.setProductId(xmProductTo.getId());
			xmProjectVo.setLinks(Arrays.asList(link));
			XmProject xmProjectTo=this.xmProjectService.saveProject(xmProjectVo);

			Map<String,String> newTaskIdMap=new HashMap<>();
			if(xmTasks!=null && xmTasks.size()>0){
				for (XmTask node : xmTasks) {
					newTaskIdMap.put(node.getId(),this.xmTaskService.createKey("id"));
				}
				for (XmTask node : xmTasks) {
					String oldId=node.getId();
					String newId=newTaskIdMap.get(oldId);
					node.setProductId(xmProductTo.getId());
					node.setId(newId);
					if(ObjectTools.isEmpty(node.getParentTaskid())||"0".equals(node.getParentTaskid())){
						node.setParentTaskid("0");
					}else{
						node.setParentTaskid(newTaskIdMap.get(node.getParentTaskid()));
					}

					node.setCbranchId(user.getBranchId());
					node.setCdeptid(user.getDeptid());
					node.setCreateUsername(user.getUsername());
					node.setCreateUserid(user.getUserid());
					node.setCreateTime(new Date());
					node.setPreTaskid(newTaskIdMap.get(node.getPreTaskid()));
					node.setIsTpl(isTpl);
					node.setMenuId(newMenuIdMap.get(node.getMenuId()));
					node.setExeUsernames(null);
					node.setExeUserids(null);
					node.setRate(0);
					node.setActEndTime(null);
					node.setActStartTime(null);
					node.setExecutorUserid(null);
					node.setExecutorUsername(null);
					node.setProjectId(xmProjectTo.getId());
					node.setProjectName(xmProjectTo.getName());
				}
				this.xmTaskService.parentIdPathsCalcBeforeSave(xmTasks);
				this.xmTaskService.batchImportFromTemplate(xmTasks);
			}
		}
		return xmProductTo;
    }

	public XmProduct copyTo(User user, List<XmTask> xmTasks,XmProduct xmProductTo) {

		Set<String> menuIds=xmTasks.stream().filter(k->ObjectTools.isNotEmpty(k.getMenuId())).map(k->k.getMenuId()).collect(Collectors.toSet());
		if(menuIds==null || menuIds.isEmpty()){
			return xmProductTo;
		}
		List<XmMenu> xmMenus=this.xmMenuService.listByIds(menuIds);
		if(xmMenus==null || xmMenus.size()==0){
			return xmProductTo;
		}
		xmProductTo.setCode(this.createProductCode(user.getBranchId()));
		xmProductTo.setId(this.createProductId(xmProductTo.getCode()));
		Set<String> pids=new HashSet<>();
		for (XmMenu xmMenu : xmMenus) {
			String pidPaths=xmMenu.getPidPaths();
			String[] pidss=pidPaths.split(",");
			for (String pid : pidss) {
				if(!menuIds.contains(pid)){
					pids.add(pid);
				}
			}
		}
		List<XmMenu> pmenus=this.xmMenuService.listByIds(pids);
		xmMenus.addAll(pmenus);

		Map<String,String>	newMenuIdMap=new HashMap<>();
		Map<String,XmMenu> menuMap=new HashMap<>();
		for (XmMenu node : xmMenus) {
			newMenuIdMap.put(node.getMenuId(),this.xmMenuService.createKey("id"));
			menuMap.put(node.getMenuId(),node);
		}
		for (XmMenu node : xmMenus) {
			String oldId=node.getMenuId();
			String newId=newMenuIdMap.get(oldId);
			node.setMenuId(newId);
			node.setProductId(xmProductTo.getId());
			if(ObjectTools.isEmpty(node.getPmenuId())||"0".equals(node.getPmenuId())){
				node.setPmenuId("0");
			}else{
				node.setPmenuId(newMenuIdMap.get(node.getPmenuId()));
			}
			node.setIsTpl(xmProductTo.getIsTpl());
			node.setCtime(new Date());
			node.setMmUserid(user.getUserid());
			node.setMmUsername(user.getUsername());
			node.setIterationId(null);
		}
		this.xmMenuService.parentIdPathsCalcBeforeSave(xmMenus);
		this.xmMenuService.doBatchInsert(xmMenus);
		this.save(xmProductTo);
		stateService.loadTasksToXmProductState(xmProductTo.getId());
		for (XmTask xmTask : xmTasks) {
			if(ObjectTools.isNotEmpty(xmTask.getMenuId())){
				XmMenu xmMenu=menuMap.get(xmTask.getMenuId());
				if(xmMenu!=null){
					xmTask.setMenuName(xmMenu.getMenuName());
					xmTask.setMenuId(xmMenu.getMenuId());
					xmTask.setProductId(xmProductTo.getId());
					xmTask.setIsTpl(xmProductTo.getIsTpl());
				}else{
					xmTask.setMenuId(null);
					xmTask.setMenuName(null);
					xmTask.setProductId(xmProductTo.getId());
					xmTask.setIsTpl(xmProductTo.getIsTpl());
				}
			}
		}
		return xmProductTo;
	}
	public String createProductCode(String branchId){
		XmProduct product=new XmProduct();
		product.setBranchId(branchId);
		long count=this.countByWhere(product);
		String seq=(count%10000+1)+"";
		int preLength=4-seq.length();

		if(preLength>0){
			for (int i = 0; i < preLength; i++) {
				seq="0"+seq;
			}
		}
		String code=getSequenceService().getCommonNo("PD{date:yyyy}-"+seq+"-{rands:2}");
		return code;

	}
	public String createProductId(String code){
		String id=getSequenceService().getCommonNo(code+"-{rands:4}");
		XmProduct xmProduct=new XmProduct(id);
		long idcount=this.countByWhere(xmProduct);
		while (idcount>0){
			id=getSequenceService().getCommonNo(code+"-{rands:4}");
			xmProduct=new XmProduct(id);
			idcount=this.countByWhere(xmProduct);
		}
		return id;

	}
	@Transactional
	public void doDeleteByPk(XmProduct xmProduct) {
		XmMenu xmMenu=new XmMenu();
		xmMenu.setProductId(xmProduct.getId());
		this.xmMenuService.deleteByWhere(xmMenu);
		super.deleteByPk(xmProduct);
	}

	public void doBatchDelete(List<XmProduct> canDelList) {
		this.xmMenuService.doBatchDeleteByProductIds(canDelList.stream().map(i->i.getId()).collect(Collectors.toList()));
		super.batchDelete(canDelList);
	}

	@Transactional
    public void addProduct(XmProductAddVo xmProduct) {
		super.insert(xmProduct);
		if(xmProduct.getLinks()!=null && xmProduct.getLinks().size()>0){
			linkService.batchInsert(xmProduct.getLinks());
		}
    }
}

