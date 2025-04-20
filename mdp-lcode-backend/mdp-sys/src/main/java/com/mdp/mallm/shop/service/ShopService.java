package com.mdp.mallm.shop.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.err.BizException;
import com.mdp.core.service.BaseService;
import com.mdp.core.utils.DateUtils;
import com.mdp.mallm.shop.entity.Shop;
import com.mdp.mallm.shop.entity.ShopLocation;
import com.mdp.mallm.shop.mapper.ShopMapper;
import com.mdp.plat.entity.Platform;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.sys.entity.Branch;
import com.mdp.sys.entity.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br>
 * 组织 com.qqkj 顶级模块 mallm 大模块 shop 小模块 <br>
 * 实体 Shop 表 shop 当前主键(包括多主键): id;
 ***/

@Service("mdp.mallm.shop.shopService")
@DS("mall-ds")
public class ShopService extends BaseService<ShopMapper,Shop> {


	@Autowired
	private ShopLocationService locationService;
	/**
	 * 创建商户
	 * 
	 * @cdate 2019-7-30 16:41
	 * @author lyk
	 */
	@Transactional
	public void createShop(Map<String, Object> params, Branch branch, Dept dept, Platform platform) {
 		String shopBrand = (String) params.get("shopBrand");
		String shopRemark = (String) params.get("shopRemark");
		String shopLogo = (String) params.get("shopLogo");
		String shopId=(String)params.get("shopId");
		Shop shopQuery=new Shop();
		shopQuery.setBranchId(branch.getId());
		shopQuery.setDeptid(dept.getDeptid());
		List<Shop> shopList=this.selectListByWhere(shopQuery);
		if(!CollectionUtils.isEmpty(shopList)) {
			throw new BizException("这个机构下的这个部门已经创建过商户了,不能再创建");
		}
		Shop shop=new Shop();
		shop.setId(shopId);
		Date date=DateUtils.getNowDate();
		User user=LoginUtils.getCurrentUserInfo();
		if(StringUtils.hasText(dept.getManager())){
			shop.setAdmin(dept.getManager());
			shop.setMngUserid(dept.getManager());
			shop.setMngUsername(dept.getManagerName());
		}else {
			shop.setAdmin(branch.getAdmUserid());
			shop.setMngUserid(branch.getAdmUserid());
			shop.setMngUsername(branch.getAdmUsername());
		}

		shop.setIsEnable("1");
		shop.setCreateTime(date);
		shop.setShopBrand(shopBrand);
		shop.setShopLogo(shopLogo);
		shop.setDeptid(dept.getDeptid());
		shop.setBranchId(branch.getId());
		shop.setCuser(user.getUsername());
		shop.setLdate(date);
		shop.setPlatShopId(platform.getShopId());
		shop.setPlatBranchId(platform.getBranchId());
		shop.setIsJoinPlat("1");
		if(!StringUtils.isEmpty(shopRemark)) {
			shop.setShopRemark(shopRemark);
		}
		this.insert(shop);
 		//插入shop_location表的数据
		ShopLocation location=new ShopLocation();
		location.setId(shop.getId()+"-0");//创建总部店铺
		location.setShopId(shop.getId());
		location.setAdmin(shop.getAdmin());
		location.setBranchId(shop.getBranchId());
		location.setDeptid(shop.getDeptid());
		location.setBusinessName(dept.getDeptName());
		location.setCreateTime(new Date());
		location.setBranchName(user.getBranchName());
		location.setStatus("0");
		location.setDataStatus("0");
		location.setLocationType("1");
		location.setShopStatus("3");
		location.setCuserid(user.getUserid());
		location.setCreateTime(date);
		location.setLopuserid(user.getUserid());
		location.setLopcreate(date);
		location.setMngUserid(shop.getMngUserid());
		location.setMngUsername(shop.getMngUsername());
		locationService.insert(location);
	}

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

}
