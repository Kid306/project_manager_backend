package com.mdp.mallm.shop.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mdp.core.entity.Result;
import com.mdp.core.err.BizException;
import com.mdp.core.query.QueryTools;
import com.mdp.core.utils.NumberUtil;
import com.mdp.mallm.shop.entity.Shop;
import com.mdp.mallm.shop.service.ShopService;
import com.mdp.plat.entity.Platform;
import com.mdp.plat.service.PlatformService;
import com.mdp.qx.HasRole;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.sys.ctrl.BranchController;
import com.mdp.sys.entity.Branch;
import com.mdp.sys.entity.Dept;
import com.mdp.sys.service.BranchService;
import com.mdp.sys.service.DeptService;
import io.swagger.annotations.Api;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController("mdp.mallm.shop.shopController")
@RequestMapping(value="/*/mallm/shop/shop")
@Api(tags={"商户信息操作接口"})
public class ShopController {
	static Log logger=LogFactory.getLog(BranchController.class);

	@Autowired
	private ShopService shopService;

	@Autowired
	BranchService branchService;

	@Autowired
	DeptService deptService;

	@Autowired
	PlatformService platformService;
	
	/**
	 * 创建商户
	 * @cdate 2019-7-30 16:41
	 * @author lyk
	 * */
	@RequestMapping(value="/createShop",method=RequestMethod.POST) 
	@HasRole(roles= {"superAdmin"})
	public Result addCategory(@RequestBody Map<String,Object> params) {
		
		
		try {

			String shopId= (String) params.get("shopId");
			if(!StringUtils.hasText(shopId)){
				return Result.error("shopId-is-null","商户编号不能为空");
			}
			if(this.shopService.countByWhere(new Shop(shopId))>0){
				return Result.error("shopId-is-exsts","商户编号已存在，无法创建");
			};
			String branchId = (String) params.get("branchId");
			String deptid=(String)params.get("deptid");
			if(StringUtils.isEmpty(branchId)) {
				throw new BizException("请传递机构编号");
			}
			Branch customerQueryBranchObject=new Branch();
			customerQueryBranchObject.setId(branchId);
			customerQueryBranchObject.setEnabled("1");
			List<Branch> bracnList=branchService.selectListByWhere(customerQueryBranchObject);
			if(CollectionUtils.isEmpty(bracnList)) {
				throw new BizException("查找不到机构信息");
			}
			Branch branchResult=bracnList.get(0);
			Dept dept=new Dept();
			dept.setBranchId(branchResult.getId());
			dept.setState("A");
			dept.setDeptid(deptid);
			List<Dept> deptList=deptService.selectListByWhere(dept);
			if(CollectionUtils.isEmpty(deptList)) {
				throw new BizException("查找不到部门信息");
			}
			Shop shopDb=new Shop();
			shopDb.setBranchId(branchId);
			if(this.shopService.countByWhere(shopDb)>0){
				throw new BizException("该机构已存在商户，不能再建立商户");
			};

			Platform platformQuery=new Platform();
			platformQuery.setStatus("1");
			List<Platform> platforms=platformService.selectListByWhere(platformQuery);
			if(platforms==null || platforms.size()==0){
				return Result.error("platform-is-not-found","未找到平台配置信息，请先配置平台信息");
			}

			if(!StringUtils.hasText(platforms.get(0).getShopId())){
				return Result.error("platform-not-set-shopId","平台未配置平台商户编号，无法创建商户");
			}
			if(!StringUtils.hasText(platforms.get(0).getBranchId())){
				return Result.error("platform-not-set-branchId","平台未配置机构编号，无法创建商户");
			}
			Platform platform=platforms.get(0);
			shopService.createShop(params,branchResult, deptList.get(0),platform);
			return Result.ok();
		}catch(Exception e) {
			logger.error("",e);
            return Result.error(e);
		}
	}
	/**
	 * 创建商户
	 * @cdate 2019-7-30 16:41
	 * @author lyk
	 * */
	//@HasRole(roles = {"superAdmin"})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listShop(@RequestParam Map<String,Object> params) {
		
		
		try {
			User user=LoginUtils.getCurrentUserInfo();
			if(!LoginUtils.isSuperAdmin()){
				params.put("branchId",user.getBranchId());
			}
			QueryWrapper<Shop> qw= QueryTools.initQueryWrapper(Shop.class,params);
			IPage page=new Page(NumberUtil.getInteger("pageNum",1),NumberUtil.getInteger("pageSize",1000));
			List<Map<String,Object>> data=shopService.selectListMapByWhere(page,qw,params);
			return Result.ok().setData( data);
		}catch(Exception e) {
			logger.error("",e);
            return Result.error(e);
		}
	}
	
}
