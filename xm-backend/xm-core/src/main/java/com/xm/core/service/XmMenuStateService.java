package com.xm.core.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Tips;
import com.mdp.core.service.BaseService;
import com.mdp.core.utils.DateUtils;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.xm.calc.service.XmCalcServiceApi;
import com.xm.core.entity.XmMenu;
import com.xm.core.entity.XmMenuState;
import com.xm.core.mapper.XmMenuStateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br> 
 * 组织 com.qqkj  顶级模块 oa 大模块 xm 小模块 <br>
 * 实体 XmMenuState 表 XM.xm_menu_state 当前主键(包括多主键): id; 
 ***/
@Service("xm.core.xmMenuStateService")
public class XmMenuStateService extends BaseService<XmMenuStateMapper,XmMenuState> {

	@Autowired
	XmCalcServiceApi xmCalcServiceApi;
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
	 * 根据前端手动选择的功能列表及项目，批量插入到功能计划表中，如果有重复则忽略
	 * @param productId
	 * @param productName
	 * @param menus
	 * @return
	 */
	public Tips batchAddStateByProductIdAndMenuList(String productId,String productName,List<XmMenu> menus){
		
		Tips tips =new Tips("批量新增成功");
		if(menus==null || menus.size()==0) {
			tips.setErrMsg("功能列表不能为空");
			return tips;
		}
		if(StringUtils.isEmpty(productId)) {
			tips.setErrMsg("项目编号不能为空");
			return tips;
		}
		User user=LoginUtils.getCurrentUserInfo();
		XmMenuState queryState=new XmMenuState();
		queryState.setProductId(productId);
		Map<String,XmMenu> menusMap=new HashMap<>();
		
		
		Map<String,XmMenuState> statesMap=new HashMap<>();
		List<XmMenuState> dbMenuStates=this.selectListByWhere(queryState);
		List<XmMenuState> addStates=new ArrayList<>();
		for (XmMenuState xmMenuState : dbMenuStates) {
			statesMap.put(xmMenuState.getMenuId(), xmMenuState);
		}
		
		for (XmMenu xmMenu : menus) {
			menusMap.put(xmMenu.getMenuId(), xmMenu);
			XmMenuState state=statesMap.get(xmMenu.getMenuId());
			if(state==null) {
				state=new XmMenuState();
				state.setProductId(productId);
				state.setMenuId(xmMenu.getMenuId());
				state.setMenuName(xmMenu.getMenuName());
				state.setCtime(new Date());
				state.setBizDate(DateUtils.getDate("yyyy-MM-dd"));
				addStates.add(state);
			}
		}
		this.batchInsert(addStates);
		return tips;
		
	}


	public long batchLoadXmMenuToState(String productId){
		long i=super.baseMapper.batchLoadXmMenuToState(map("bizDate",DateUtils.getDate("yyyy-MM-dd"),"productId",productId));
		return i;
	}

	@Transactional
	public void sumParents(XmMenu node){
		String id=node.getMenuId();
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
		if(pidPathsList.size()>0){
			xmCalcServiceApi.xmMenuSumParents(pidPathsList	);
		}

	}
	@Transactional
	public void batchSumParents(List<XmMenu> xmMenus) {
		List<Set<String>> list=new ArrayList<>();
		for (XmMenu node : xmMenus) {
			String id=node.getMenuId();
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
						xmCalcServiceApi.xmMenuBatchSumParents( ids);
					}

				}

			}


		}

	}


	/**
	 * 计算bug、task、测试案例、等数据
	 * @param productId
	 * @return
	 */
	public int loadTasksToXmMenuState(String productId) {
		xmCalcServiceApi.loadTasksToXmMenuState(productId);
		return 1;
	}
}

