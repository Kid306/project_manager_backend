package com.xm.core.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mdp.core.entity.Tips;
import com.mdp.core.err.BizException;
import com.mdp.core.service.BaseService;
import com.mdp.core.utils.CollectionUtils;
import com.mdp.core.utils.NumberUtil;
import com.mdp.core.utils.ObjectTools;
import com.xm.calc.service.XmCalcServiceApi;
import com.xm.core.entity.XmMenu;
import com.xm.core.entity.XmTask;
import com.xm.core.entity.XmWorkload;
import com.xm.core.mapper.XmMenuMapper;
import com.xm.core.queue.XmMenuSumParentsPushService;
import com.xm.core.vo.XmIterationMenuVo;
import com.xm.core.vo.XmMenuVo;
import com.xm.core.vo.XmPhaseMenusVo;
import com.xm.rpt.XmRptQueryServiceApi;
import com.xm.tools.XmUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br> 
 * 组织 com.qqkj  顶级模块 oa 大模块 xm 小模块 <br>
 * 实体 XmMenu 表 XM.xm_menu 当前主键(包括多主键): menu_id; 
 ***/
@Service("xm.core.xmMenuService")
public class XmMenuService extends BaseService<XmMenuMapper,XmMenu> {

	@Autowired
	XmMenuStateService xmMenuStateService;

	@Autowired
	XmMenuSumParentsPushService pushService;
	@Autowired
	XmCalcServiceApi xmCalcServiceApi;

	@Autowired
	XmRptQueryServiceApi xmRptQueryServiceApi;
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
	 * 连同功能关联的项目需求计划数据一起带出
	 *
	 * @return
	 */
	public List<Map<String, Object>> selectListMapByWhereWithPlan(IPage page, QueryWrapper ew, Map<String,Object> ext) {
		return baseMapper.selectListMapByWhereWithPlan(page,ew,ext);
	}

	/**
	 * 连同功能关联的状态数据一起带出
	 * @return
	 */
	public List<Map<String, Object>> selectListMapByWhereWithState(IPage page, QueryWrapper ew, Map<String,Object> ext) {

		return baseMapper.selectListMapByWhereWithState(page,ew,ext);
	}


	public void updateMenuChildrenCntByMenuId(String menuId) {
		if(ObjectUtils.isEmpty(menuId)||"0".equals(menuId)){
			return;
		}
		baseMapper.updateMenuChildrenCntByMenuId( menuId);
		
	}

	public void updateChildrenCntByIds(List<String> ids) {
		baseMapper.updateChildrenCntByIds( ids);
	}

	@Transactional
	public void batchInsertOrUpdate(List<XmMenuVo> xmMenus) {
		List<XmMenuVo> addList = new ArrayList<>();
		List<XmMenuVo> editList = new ArrayList<>();
		List<XmMenu> xmMenusDb = this.selectListByIds(xmMenus.stream().map(i -> i.getMenuId()).collect(Collectors.toList()));
		editList = xmMenus.stream().filter(i -> xmMenusDb.stream().filter(k -> k.getMenuId().equals(i.getMenuId())).findAny().isPresent()).collect(Collectors.toList());
		addList = xmMenus.stream().filter(i -> !xmMenusDb.stream().filter(k -> k.getMenuId().equals(i.getMenuId())).findAny().isPresent()).collect(Collectors.toList());

		List<XmMenu> xmMenuList = new ArrayList<>();
		if (addList.size() > 0) {
			xmMenuList.addAll(addList.stream().map(i -> (XmMenu) i).collect(Collectors.toList()));
			this.saveBatch(xmMenuList);
			Set<String> ids=xmMenuList.stream().filter(k->ObjectTools.isNotEmpty(k.getPmenuId())&&!"0".equals(k.getPmenuId())).map(k->k.getPmenuId()).collect(Collectors.toSet());

			if(!ids.isEmpty()){
				this.baseMapper.updateChildrenCntByIds(CollectionUtils.convertList(ids,k->k));
			}
			this.xmMenuStateService.batchLoadXmMenuToState(xmMenus.get(0).getProductId());
		}
		if (editList.size() > 0) {
			List<XmMenu> updateList = editList.stream().map(i -> (XmMenu) i).collect(Collectors.toList());
			xmMenuList.addAll(updateList);
			this.updateBatchById(updateList);
		}
		if (xmMenuList.size() > 0) {
			this.batchSumParents(xmMenuList);
		}
	}

	public List<Map<String, Object>> queryTaskUsersByMenuId(String menuId) {
		return baseMapper.queryTaskUsersByMenuId( menuId);
	}

	public List<XmMenu> selectExistIterationMenus(List<String> menuIds) {

		return baseMapper.selectExistIterationMenus( map("menuIds", menuIds));
	}

	@Transactional
	@Override
	public boolean removeById(XmMenu xmMenu) {
		super.removeById(xmMenu);
		this.updateMenuChildrenCntByMenuId(xmMenu.getPmenuId());
		this.sumParents(xmMenu,xmMenu.getMenuId());
		return true;
	}

	public List<XmMenu> parentIdPathsCalcBeforeSave(List<XmMenu> nodes) {
		List<XmMenu> noExistsList = nodes.stream().filter(i -> !nodes.stream().filter(k -> k.getMenuId().equals(i.getPmenuId())).findAny().isPresent()).collect(Collectors.toList());
		noExistsList = noExistsList.stream().filter(i -> StringUtils.hasText(i.getPmenuId()) && !"0".equals(i.getPmenuId())).collect(Collectors.toList());
		Map<String, String> hadCalcMap = new HashMap<>();
		for (XmMenu node : noExistsList) {
			if (hadCalcMap.containsKey(node.getPmenuId())) {
				String idPaths = hadCalcMap.get(node.getPmenuId());
				node.setPidPaths(idPaths + node.getMenuId() + ",");
			} else {
				this.parentIdPathsCalcBeforeSave(node);
				String idPaths = node.getPidPaths();
				idPaths = idPaths.substring(0, idPaths.length() - node.getMenuId().length() - 1);
				hadCalcMap.put(node.getPmenuId(), idPaths);
			}
		}
		for (XmMenu node : nodes) {
			if (!StringUtils.hasText(node.getPmenuId()) ||"0".equals(node.getPmenuId())) {
				node.setPidPaths("0," + node.getMenuId() + ",");
				node.setPmenuId("0");
			}
			if (hadCalcMap.containsKey(node.getPmenuId())) {
				String idPaths = hadCalcMap.get(node.getPmenuId());
				node.setPidPaths(idPaths + node.getMenuId() + ",");
			}
		}
		for (XmMenu node : nodes) {
			if (!StringUtils.hasText(node.getPmenuId()) ||"0".equals(node.getPmenuId())) {
				continue;
			}
			if (!hadCalcMap.containsKey(node.getPmenuId())) {
				List<XmMenu> pnodeList = this.getParentList(node, nodes);
				if (pnodeList == null || pnodeList.size() == 0) {//理论上不应该存在这种情况
					node.setPidPaths("0," + node.getMenuId() + ",");
					node.setPmenuId("0");
					continue;
				}
				XmMenu topParent = pnodeList.get(pnodeList.size() - 1);
				String idPath = "0,";
				if (hadCalcMap.containsKey(topParent.getPmenuId())) {
					idPath = hadCalcMap.get(topParent.getPmenuId());
				}
				for (int i = pnodeList.size() - 1; i >= 0; i--) {
					idPath = idPath + pnodeList.get(i).getMenuId() + ",";
				}
				node.setPidPaths(idPath + node.getMenuId() + ",");
			}
		}
		for (XmMenu node : nodes) {
			String idPaths = node.getPidPaths();
			String[] idpss = idPaths.split(",");
			node.setLvl(idpss.length - 1);
		}
		return nodes;
	}

	public Tips parentIdPathsCalcBeforeSave(XmMenu currNode) {
		Tips tips = new Tips("成功");
		if (!StringUtils.hasText(currNode.getPmenuId()) || "0".equals(currNode.getPmenuId())) {
			currNode.setPidPaths("0," + currNode.getMenuId() + ",");
			currNode.setPmenuId("0");
			currNode.setLvl(1);
			return tips;
		} else {
			List<XmMenu> parentList = this.getParentList(currNode);
			if (parentList == null || parentList.size() == 0) {
				currNode.setPidPaths("0," + currNode.getMenuId() + ",");
				currNode.setPmenuId("0");
				currNode.setLvl(1);
				return tips;
			}
			String idPath = "0,";
			for (int i = parentList.size() - 1; i >= 0; i--) {
				idPath = idPath + parentList.get(i).getMenuId() + ",";
			}
			currNode.setPidPaths(idPath + currNode.getMenuId() + ",");

			String idPaths = currNode.getPidPaths();
			String[] idpss = idPaths.split(",");
			currNode.setLvl(idpss.length - 1);
		}
		return tips;
	}

	private List<XmMenu> getParentList(XmMenu currNode) {
		List<XmMenu> parentList = new ArrayList<>();
		XmMenu current = currNode;
		while (true) {
			if (!StringUtils.hasText(current.getPmenuId()) || "0".equals(current.getPmenuId()) || current.getMenuId().equals(current.getPmenuId())) {
				return parentList;
			}
			XmMenu query = new XmMenu();
			query.setMenuId(current.getPmenuId());
			current = this.selectOneObject(query);
			if (current == null) {
				return parentList;
			}
			parentList.add(current);
		}
	}

	private List<XmMenu> getParentList(XmMenu currNode, List<XmMenu> nodes) {
		List<XmMenu> parentList = new ArrayList<>();
		XmMenu current = currNode;
		while (true) {
			if (!StringUtils.hasText(current.getPmenuId()) || "0".equals(current.getPmenuId()) || current.getMenuId().equals(current.getPmenuId())) {
				return parentList;
			}
			XmMenu query = new XmMenu();
			query.setMenuId(current.getPmenuId());
			Optional<XmMenu> optional = nodes.stream().filter(i -> i.getMenuId().equals(query.getMenuId())).findFirst();
			if (optional.isPresent()) {
				current = optional.get();
				parentList.add(current);
			} else {
				return parentList;
			}
		}
	}

	public void setNull(XmMenu xmMenu){
		if(ObjectUtils.isEmpty(xmMenu.getPmenuId())){
			xmMenu.setPmenuId("0");
		}
		if(ObjectUtils.isEmpty(xmMenu.getProductId())){
			xmMenu.setProductId(null);
		}
		if(ObjectUtils.isEmpty(xmMenu.getIterationId())){
			xmMenu.setIterationId(null);
		}
		if(ObjectUtils.isEmpty(xmMenu.getIterationName())){
			xmMenu.setIterationName(null);
		}

		if(ObjectUtils.isEmpty(xmMenu.getFuncId())){
			xmMenu.setFuncId(null);
		}
		if(ObjectUtils.isEmpty(xmMenu.getFuncName())){
			xmMenu.setFuncName(null);
		}
	}

	@Transactional
	public int insert(XmMenu xmMenu) {
		this.setNull(xmMenu);
		int i = super.insert(xmMenu);
		if(!"0".equals(xmMenu.getPmenuId()) && !ObjectTools.isEmpty(xmMenu.getPmenuId())){
			this.baseMapper.updateMenuChildrenCntByMenuId(xmMenu.getPmenuId());
		}
		xmMenuStateService.batchLoadXmMenuToState(xmMenu.getProductId());
		this.sumParents(xmMenu,xmMenu.getMenuId());
		return i;
	}

	@Transactional
	public int updateByPk(XmMenu xmMenu) {
		int i = super.updateByPk(xmMenu);
		return i;
	}

	@Transactional
	public void doBatchInsert(List<XmMenu> xmMenus) {
		super.batchInsert(xmMenus);
		Set<String> ids=xmMenus.stream().filter(k->ObjectTools.isNotEmpty(k.getPmenuId())&&!"0".equals(k.getPmenuId())).map(k->k.getPmenuId()).collect(Collectors.toSet());

		if(!ids.isEmpty()){
			this.baseMapper.updateChildrenCntByIds(CollectionUtils.convertList(ids,k->k));
		}
		this.xmMenuStateService.batchLoadXmMenuToState(xmMenus.get(0).getProductId());
		this.batchSumParents(xmMenus);

	}

	@Transactional
	public void doBatchDelete(List<XmMenu> canDelList) {
		super.batchDelete(canDelList);
		Set<String> ids=canDelList.stream().filter(k->ObjectTools.isNotEmpty(k.getPmenuId())&&!"0".equals(k.getPmenuId())).map(k->k.getPmenuId()).collect(Collectors.toSet());

		if(!ids.isEmpty()){
			this.baseMapper.updateChildrenCntByIds(CollectionUtils.convertList(ids,k->k));
		}
		String[] ignorIds= canDelList.stream().map(k->k.getMenuId()).toArray(String[]::new);
		List<Set<String>> list= XmUtils.parsePidPaths(canDelList.stream().map(k->k.getPidPaths()).collect(Collectors.toSet()),1,ignorIds);
		for (Set<String> set : list) {
			xmCalcServiceApi.xmMenuBatchSumParents( set.stream().collect(Collectors.toList()));
		}
	}

	@Transactional
	public void doBatchDeleteByProductIds(List<String> productIds) {
		super.baseMapper.doBatchDeleteByProductIds( productIds);
	}


	/**
	 * 检查是否能删除干净所有儿子孙子节点。
	 *
	 * @param delNode  当前删除节点
	 * @param delNodes 本批量需要删除的全部节点
	 * @return
	 */
	public boolean checkCanDelAllChild(XmMenu delNode, List<XmMenu> delNodes) {
		if (delNode == null) {
			return true;
		}
		if (delNode.getChildrenCnt() == null || delNode.getChildrenCnt() <= 0) {
			return true;
		}
		List<XmMenu> childList = delNodes.stream().filter(i -> delNode.getMenuId().equals(i.getPmenuId())).collect(Collectors.toList());
		if (childList == null || childList.size() < delNode.getChildrenCnt()) {
			return false;
		}
		for (XmMenu n : childList) {
			if (!this.checkCanDelAllChild(n, delNodes)) {
				return false;
			}
		}
		return true;

	}

	public void batchUnIteration(XmIterationMenuVo xmIterationMenus) {
		baseMapper.batchUnIteration( xmIterationMenus);
	}

	public void batchIteration(XmIterationMenuVo xmIterationMenus) {
		baseMapper.batchIteration( xmIterationMenus);
	}


	public void batchUnProductPhase(XmPhaseMenusVo xmPhaseMenusVo) {
		baseMapper.batchUnProductPhase( xmPhaseMenusVo);
	}

	public void batchProductPhase(XmPhaseMenusVo xmPhaseMenusVo) {
		baseMapper.batchProductPhase( xmPhaseMenusVo);
	}

	public List<XmMenu> listTenMenuByProductIdAndIterationId(String productId, String iterationId) {

		return baseMapper.listTenMenuByProductIdAndIterationId( map("productId", productId, "iterationId", iterationId));
	}



	@Transactional
	public void batchChangeParent(List<XmMenu> xmMenus,XmMenu parentMenu) {
		if(parentMenu!=null){
			baseMapper.batchChangeParent(map("menuIds",xmMenus.stream().map(i->i.getMenuId()).collect(Collectors.toList()),"pmenuId",parentMenu.getMenuId(),"parentPidPaths",parentMenu.getPidPaths()));
		}else {
			baseMapper.batchChangeParent(map("menuIds",xmMenus.stream().map(i->i.getMenuId()).collect(Collectors.toList()),"pmenuId","0","parentPidPaths","0,"));

		}
		List<String> oldPids=xmMenus.stream().map(k->k.getPmenuId()).collect(Collectors.toSet()).stream().collect(Collectors.toList());
		if(parentMenu!=null){
			oldPids.add(parentMenu.getMenuId());
		}
		oldPids.remove("0");
		if(oldPids.size()>0){
			this.updateChildrenCntByIds(oldPids);
		}
	}


    public List<XmMenu> selectListByIdsWithsChildrenCnt(List<String> ids) {
		return baseMapper.selectListByIdsWithsChildrenCnt(ids);
    }



	@Transactional
	public void sumParents(XmMenu node,String ignorId){
		xmCalcServiceApi.xmMenuSumParents( XmUtils.parsePidPaths(node.getPidPaths(),1,ignorId));

	}
	@Transactional
	public void batchSumParents(List<XmMenu> xmMenus) {
		if(xmMenus==null||xmMenus.size()<=0){
			return;
		}
		List<Set<String>> list= XmUtils.parsePidPaths(xmMenus.stream().map(k->k.getPidPaths()).collect(Collectors.toSet()),1);
		for (Set<String> set : list) {
			xmCalcServiceApi.xmMenuBatchSumParents( set.stream().collect(Collectors.toList()));
		}
	}

    public List<Map<String,Object>> getXmMenuAttDist(IPage page, QueryWrapper qw, Map<String,Object> ext) {
		return xmRptQueryServiceApi.getXmMenuAttDist(page,qw,ext);
    }

	public List<Map<String, Object>> getXmMenuAgeDist(IPage page, QueryWrapper qw, Map<String,Object> ext) {
		return xmRptQueryServiceApi.getXmMenuAgeDist(qw,ext);
	}

    public List<Map<String, Object>> getXmMenuSort(IPage page, QueryWrapper qw, Map<String,Object> ext) {
		return xmRptQueryServiceApi.getXmMenuSort(page,qw,ext);
    }

	@Override
	public String createKey(String keyName) {
		return "M"+getSequenceService().getCommonNo("{date62}{rands:2}");
 	}

	public void updateUps(List<String> menuIds) {
		baseMapper.updateUps(menuIds);
	}

	public void updateComments(List<String> menuIds) {
		baseMapper.updateComments(menuIds);
	}
	public void upReads(String menuId,Integer reads) {
		baseMapper.upReads(map("menuId",menuId,"reads",reads));
	}

	public List<XmMenu> getUserCanOpMenusByIds(Map<String, Object> map) {
		return baseMapper.getUserCanOpMenusByIds(map);
	}


	/**
	 * 向上查找某个任务的所有依赖
	 * @param xmMenu
	 * @param m
	 */
	public void deriveUpList(XmMenu xmMenu, Map<String,XmMenu> m) {
		if(xmMenu==null){
			return;
		}
		if(ObjectTools.isNotEmpty(xmMenu.getPhaseId()) && !xmMenu.getPhaseId().equals("0")){
			List<String> ids=Arrays.asList(xmMenu.getPhaseId().split(","));
			ids=ids.stream().filter(k->!m.containsKey(k)&&!"0".equals(k)).collect(Collectors.toList());
			if(ids.size()==0){
				return;
			}
			List<XmMenu> ds=this.listByIds(ids);
			if(ds!=null && ds.size()>0){
				for (XmMenu d : ds) {
					if(m.containsKey(d.getMenuId())){
						continue;
					}
					m.put(d.getMenuId(),d);
					this.deriveUpList(d,m);
				}
			}
		}
	}

	/**
	 * 查询某个任务的所有的上依赖及下依赖
	 * @param id
	 * @return
	 */
	public List<XmMenu> deriveList(String id){
		XmMenu xmMenu=this.getById(id);
		Map<String,XmMenu> m=new HashMap<>();
		m.put(xmMenu.getMenuId(),xmMenu);
		this.deriveUpList(xmMenu,m);
		this.deriveDownList(xmMenu,m);
		return m.values().stream().collect(Collectors.toList());
	}

	/**
	 * 向下查找某个任务的所有依赖
	 * @param xmMenu
	 * @param m
	 */
	public void deriveDownList(XmMenu xmMenu,Map<String,XmMenu> m) {
		if(xmMenu==null){
			return;
		}
		List<XmMenu> ds=list(Wrappers.lambdaQuery(XmMenu.class).apply(true,"INSTR(phase_id, {0})>0",xmMenu.getMenuId()));
		if(ds!=null && ds.size()>0){
			for (XmMenu d : ds) {
				if(m.containsKey(d.getMenuId())){
					continue;
				}
				m.put(d.getMenuId(),d);
				this.deriveDownList(d,m);
			}
		}
	}



	@Transactional
	public int editSomeFieldsExt(Map<String, Object> parameter,List<XmMenu> menus) {
		int i=0;
		if(parameter.containsKey("initWorkload")){
			baseMapper.batchUpdateInitWorkloadAndRate(parameter);
		}else if(parameter.containsKey("calcType")){
			baseMapper.batchUpdateInitWorkloadAndRateByCalcType(parameter);
		}else if(parameter.containsKey("status")){
			baseMapper.batchUpdateFinishState(parameter);
		}else{
			i=super.editSomeFields(parameter);
		}

		if(parameter.containsKey("calcType") ||parameter.containsKey("status")||parameter.containsKey("initWorkload")){
			//this.batchSumParents(tasks);
			this.pushService.pushXmMenus(menus);
		}
		return i;
	}
}

