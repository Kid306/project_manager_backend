package com.xm.core.service;

import com.xm.core.entity.XmMenu;
import com.xm.core.entity.XmProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

import static com.mdp.core.utils.BaseUtils.map;

/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br> 
 * 组织 com.qqkj  顶级模块 oa 大模块 xm 小模块 <br>
 * 实体 XmMenu 表 XM.xm_menu 当前主键(包括多主键): menu_id; 
 ***/
@Service("xm.core.xmProductOperQxService")
public class XmMenuOperQxService {
	@Autowired
	XmMenuService xmMenuService;

	/**
	 * 检查某个人是否为产品级管理人员
	 * @param productDb
	 * @param userid
	 * @return
	 */
	public boolean checkIsProductAdmOrAss(XmProduct productDb,String userid){
		if(!StringUtils.hasText(userid)){
			return false;
		}else {
			if(userid.equals(productDb.getPmUserid())||userid.equals(productDb.getAdmUserid())||userid.equals(productDb.getAssUserid())){
				return true;
			}
		}
		return false;
	}

	/**
	 * 检查某个人是否故事的具有管理权限的人
	 * 1.如果是需求负责人、创建人，可以
	 * 2.如果是需求的上级(多级)负责人，可以
	 * @param menuId 需求、故事编号
	 * @param mmUserid
	 * @return
	 */
	public XmMenu getUserCanOpMenuById(String menuId, String mmUserid,Boolean childrenCnt) {
		if(!StringUtils.hasText(menuId) || !StringUtils.hasText(mmUserid)){
			return null;
		}
		List<XmMenu> xmMenus=this.getUserCanOpMenusByIds(Arrays.asList(menuId),mmUserid,childrenCnt);
		return xmMenus!=null && xmMenus.size()>0?xmMenus.get(0):null;
	}

	/**
	 * 检查某个人是否故事的具有管理权限的人
	 * 1.如果是需求负责人、创建人，可以
	 * 2.如果是需求的上级(多级)负责人，可以
	 * @param menuIds 需求、故事编号列表
	 * @param mmUserid
	 * @return
	 */
	public List<XmMenu> getUserCanOpMenusByIds(List<String> menuIds, String mmUserid,Boolean childrenCnt) {
		if(menuIds==null || menuIds.size()==0){
			return null;
		}
		List<XmMenu> xmMenus=xmMenuService.getUserCanOpMenusByIds(map("mmUserid",mmUserid,"menuIds",menuIds,"childrenCnt",childrenCnt));
		return xmMenus;
	}
}

