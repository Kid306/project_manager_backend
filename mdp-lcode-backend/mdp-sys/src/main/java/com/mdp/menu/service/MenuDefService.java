package com.mdp.menu.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.LangTips;
import com.mdp.core.entity.Tips;
import com.mdp.core.err.BizException;
import com.mdp.core.service.BaseService;
import com.mdp.menu.entity.MenuDef;
import com.mdp.menu.mapper.MenuDefMapper;
import com.mdp.safe.client.cache.RoleMenusRedisCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br> 
 * 组织 com.qqkj  顶级模块 mdp 大模块 menu 小模块 <br>
 * 实体 MenuDef 表 ADMIN.menu_def 当前主键(包括多主键): id; 
 ***/
@Service("mdp.menu.menuDefService")
public class MenuDefService extends BaseService<MenuDefMapper,MenuDef> {

	@Autowired
	RoleMenusRedisCacheService roleMenusRedisCacheService;

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
	
	public List<MenuDef> selectMenusByRoleids(String...roleids) {
		List<MenuDef> allMenus=new ArrayList<>();
		if(roleids==null || roleids.length<=0) {
			return allMenus;
		}
		QueryWrapper<MenuDef> qw=new QueryWrapper<>();
		qw.in("roleid",roleids);
		return  list(qw);
	}
	
	
	public Long countChildrenByIds( List<MenuDef> menuDefs ) { 
		return baseMapper.countChildrenByIds(menuDefs);
		
	} 
	public  List<MenuDef> getNotExistsMenuDefByRidAndModuleId(List<MenuDef> menuDefs) {
		List<MenuDef> existsList= getBaseMapper().selectMenusInDbByRidAndModuleId(menuDefs);
		List<MenuDef> notExistsList=new ArrayList<>();
		for (MenuDef menuDef : menuDefs) {
			boolean exists=false;
			for (MenuDef def : existsList) {
				
				if(def.getRpath().equals(menuDef.getRpath())) {
					exists=true; 
				}
			}
			if(exists==true) {
				continue;
			}else {
				notExistsList.add(menuDef);
			}  
		}
		return notExistsList;
		 
	}
	
	/**
	 * 批量保存按钮到菜单表
	 * @param buttons
	 * @return
	 */
	public Tips batchSaveButton(List<MenuDef> buttons) {
		
		if(buttons==null) {
			return LangTips.errMsg("buttons-required","按钮为空，不允许保存");
		}
		for (MenuDef btn : buttons) {
			if(StringUtils.isEmpty(btn.getPid())) {
				return LangTips.errMsg("pid-not-empty", "pid", "按钮没有指定菜单，不允许保存");
			}
			if(StringUtils.isEmpty(btn.getMenuType())) {
				btn.setMenuType("2");
			}
			if(StringUtils.isEmpty(btn.getId())) {
				btn.setId(super.createKey("btnId"));
			}
		}
		List<MenuDef> buttonsDb=selectExistsButonsByIds(buttons);
		List<MenuDef> insertButtons=new ArrayList<>();
		List<MenuDef> updateButtons=new ArrayList<>();
		for (MenuDef menuDef : buttons) {
			boolean exists=false;
			for (MenuDef menuDefdb : buttonsDb) {
				if(menuDefdb.getId().equals(menuDef.getId()) && menuDefdb.getPid().equals(menuDef.getPid())) {
					updateButtons.add(menuDef);
					exists=true;
					break;
				}else {
					if(menuDefdb.getId().equals(menuDef.getId()) && !menuDefdb.getPid().equals(menuDef.getPid())) { 
						throw new BizException("id-exists", "id","按钮"+ menuDef.getMname()+"的编号【"+menuDef.getId()+"】已存在，请重新编号");
					}
				}
			}
			if(exists==false) {
				insertButtons.add(menuDef);
			}
		}
		if(insertButtons.size()>0) {
			super.batchInsert(insertButtons);
		}
		if(updateButtons.size()>0) {
			super.batchUpdate(updateButtons);
		}
		
		return LangTips.okMsg("ok","成功");
		
	}

	private List<MenuDef> selectExistsButonsByIds(List<MenuDef> buttons) {
		return getBaseMapper().selectExistsButonsByIds(buttons);
	}
	/** 请在此类添加自定义函数 */

}

