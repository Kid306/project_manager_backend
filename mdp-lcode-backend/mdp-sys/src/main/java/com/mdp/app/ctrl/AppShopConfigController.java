// package com.mdp.app.ctrl;
//
// import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
// import com.baomidou.mybatisplus.core.metadata.IPage;
// import com.mdp.app.entity.AppShopConfig;
// import com.mdp.app.service.AppShopConfigService;
// import com.mdp.core.api.CacheHKVService;
// import com.mdp.core.entity.Result;
// import com.mdp.core.err.BizException;
// import com.mdp.core.query.QueryTools;
// import com.mdp.core.utils.DateUtils;
// import com.mdp.mallm.shop.entity.Shop;
// import com.mdp.mallm.shop.service.ShopService;
// import com.mdp.plat.entity.Platform;
// import com.mdp.plat.service.PlatformService;
// import com.mdp.qx.HasRole;
// import io.swagger.annotations.Api;
// import io.swagger.annotations.ApiOperation;
// import io.swagger.annotations.ApiResponse;
// import io.swagger.annotations.ApiResponses;
// import org.apache.commons.logging.Log;
// import org.apache.commons.logging.LogFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.util.StringUtils;
// import org.springframework.web.bind.annotation.*;
// import springfox.documentation.annotations.ApiIgnore;
//
// import java.math.BigDecimal;
// import java.util.Date;
// import java.util.List;
// import java.util.Map;
// /**
//  * url编制采用rest风格,如对ADM.app_shop_config app_shop_config的操作有增删改查,对应的url分别为:<br>
//  *  新增: app/appShopConfig/add <br>
//  *  查询: app/appShopConfig/list<br>
//  *  模糊查询: app/appShopConfig/listKey<br>
//  *  修改: app/appShopConfig/edit <br>
//  *  删除: app/appShopConfig/del<br>
//  *  批量删除: app/appShopConfig/batchDel<br>
//  * 组织 com.qqkj  顶级模块 mdp 大模块 app 小模块 <br>
//  * 实体 AppShopConfig 表 ADM.app_shop_config 当前主键(包括多主键): shop_id;
//  ***/
// @RestController("mdp.app.appShopConfigController")
// @RequestMapping(value="/*/app/appShopConfig")
// @Api(tags={"app_shop_config操作接口"})
// public class AppShopConfigController {
//
// 	static Log logger=LogFactory.getLog(AppShopConfigController.class);
//
// 	@Autowired
// 	private AppShopConfigService appShopConfigService;
//
// 	@Autowired
// 	CacheHKVService cacheHKVService;
//
//
//
// 	@Autowired
// 	PlatformService platformService;
//
// 	@Autowired
// 	private ShopService shopService;
//
// 	@ApiOperation( value = "查询app_shop_config信息列表",notes="listAppShopConfig,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
// 	@ApiResponses({
// 		@ApiResponse(code = 200,response=AppShopConfig.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},pageInfo:{total:总记录数},data:[数据对象1,数据对象2,...]}")
// 	})
// 	@RequestMapping(value="/list",method=RequestMethod.GET)
// 	public Result listAppShopConfig( @ApiIgnore @RequestParam Map<String,Object>  appShopConfig){
//
// 		IPage page=QueryTools.initPage(appShopConfig);
// 		QueryWrapper<AppShopConfig> qw= QueryTools.initQueryWrapper(AppShopConfig.class, appShopConfig);
// 		List<Map<String,Object>>	appShopConfigList = appShopConfigService.selectListMapByWhere(page,qw,appShopConfig);
//
// 		return Result.ok().setData(appShopConfigList).setTotal(page.getTotal());
//
// 	}
//
//
//
// 	/***/
// 	@ApiOperation( value = "新增一条app_shop_config信息",notes="addAppShopConfig,主键如果为空，后台自动生成")
// 	@ApiResponses({
// 		@ApiResponse(code = 200,response=AppShopConfig.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
// 	})
// 	@RequestMapping(value="/add",method=RequestMethod.POST)
//
// 	@HasRole(roles= {"superAdmin"})
// 	public Result addAppShopConfig(@RequestBody AppShopConfig appShopConfig) {
//
//
// 		try{
//
// 			if(!StringUtils.hasText(appShopConfig.getShopId())){
// 				return Result.error("shopId-is-null","商户编号不能为空");
// 			}
// 			Shop shopDb=this.shopService.selectOneObject(new Shop(appShopConfig.getShopId()));
// 			if(shopDb==null){
// 				return Result.error("shop-is-null","商户不存在");
// 			}
// 			AppShopConfig appShopConfigQuery = new  AppShopConfig(appShopConfig.getShopId());
// 			if(appShopConfigService.countByWhere(appShopConfigQuery)>0){
//  				return Result.error("id-exists","编号重复，请修改编号再提交");
// 			}
// 			appShopConfigQuery = new  AppShopConfig();
// 			appShopConfigQuery.setShopBranchId(appShopConfig.getShopBranchId());
// 			if(appShopConfigService.countByWhere(appShopConfigQuery)>0){
//  				return Result.error("org-had-another-partner","该机构已经存在一个商户，同一个机构不允许配置多个商户");
// 			}
//
//
// 			Platform platformQuery=new Platform();
// 			platformQuery.setStatus("1");
// 			List<Platform> platforms=platformService.selectListByWhere(platformQuery);
// 			if(platforms==null || platforms.size()==0){
// 				return Result.error("platform-is-not-found","未找到平台配置信息，请先配置平台信息");
// 			}
//
// 			if(!StringUtils.hasText(platforms.get(0).getShopId())){
// 				return Result.error("platform-not-set-shopId","平台未配置平台商户编号，无法创建商户");
// 			}
// 			if(!StringUtils.hasText(platforms.get(0).getBranchId())){
// 				return Result.error("platform-not-set-branchId","平台未配置机构编号，无法创建商户");
// 			}
// 			Platform platform=platforms.get(0);
// 			if(!platform.getShopId().equals(appShopConfig.getPlatShopId())){
// 				return Result.error("platShopId-not-right","平台商户不是有效的平台商户");
// 			}
// 			if(appShopConfig.getShopServiceCharges()==null){
// 				appShopConfig.setShopServiceCharges(BigDecimal.ZERO);
// 			}
// 			if(appShopConfig.getTpServiceCharges()==null){
// 				appShopConfig.setTpServiceCharges(BigDecimal.ZERO);
// 			}
//
// 			if(appShopConfig.getPlatServiceCharges()==null){
// 				appShopConfig.setPlatServiceCharges(BigDecimal.ZERO);
// 			}
// 			appShopConfig.setLastUpdateTime(DateUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
// 			appShopConfigService.insert(appShopConfig);
// 			Shop shop=new Shop();
// 			shop.setId(appShopConfig.getShopId());
// 			shop.setAcctPrjType(appShopConfig.getAcctPrjType());
// 			shop.setPlatBranchId(appShopConfig.getPlatBranchId());
// 			shop.setPlatShopId(appShopConfig.getPlatShopId());
// 			shop.setIsJoinPlat(appShopConfig.getIsJoinPlat());
// 			shopService.updateSomeFieldByPk(shop);
// 			return Result.ok().setData(appShopConfig);
// 		}catch (BizException e) {
// 			logger.error("",e);
// 			return Result.error(e);
// 		}catch (Exception e) {
// 			logger.error("",e);
// 			return Result.error(e);
// 		}
// 	}
//
//
// 	/**
// 	@ApiOperation( value = "删除一条app_shop_config信息",notes="delAppShopConfig,仅需要上传主键字段")
// 	@ApiResponses({
// 		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
// 	})
// 	@RequestMapping(value="/del",method=RequestMethod.POST)
// 	public Result delAppShopConfig(@RequestBody AppShopConfig appShopConfig){
//
//
// 		try{
// 			appShopConfigService.deleteByPk(appShopConfig);
// 		}catch (BizException e) {
// 			logger.error("",e);
// 			return Result.error(e);
// 		}catch (Exception e) {
// 			logger.error("",e);
// 			return Result.error(e);
// 		}
// 	}
// 	*/
//
// 	/***/
// 	@ApiOperation( value = "根据主键修改一条app_shop_config信息",notes="editAppShopConfig")
// 	@ApiResponses({
// 		@ApiResponse(code = 200,response=AppShopConfig.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
// 	})
// 	@RequestMapping(value="/edit",method=RequestMethod.POST)
// 	@HasRole(roles= {"superAdmin"})
// 	public Result editAppShopConfig(@RequestBody AppShopConfig appShopConfig) {
//
//
// 		try{
//
// 			if(!StringUtils.hasText(appShopConfig.getShopId())){
// 				return Result.error("shopId-is-null","商户编号不能为空");
// 			}
// 			Shop shopDb=this.shopService.selectOneObject(new Shop(appShopConfig.getShopId()));
// 			if(shopDb==null){
// 				return Result.error("shop-is-null","商户不存在");
// 			}
// 			Platform platformQuery=new Platform();
// 			platformQuery.setStatus("1");
// 			List<Platform> platforms=platformService.selectListByWhere(platformQuery);
// 			if(platforms==null || platforms.size()==0){
// 				return Result.error("platform-is-not-found","未找到平台配置信息，请先配置平台信息");
// 			}
//
// 			if(!StringUtils.hasText(platforms.get(0).getShopId())){
// 				return Result.error("platform-not-set-shopId","平台未配置平台商户编号，无法创建商户");
// 			}
// 			if(!StringUtils.hasText(platforms.get(0).getBranchId())){
// 				return Result.error("platform-not-set-branchId","平台未配置机构编号，无法创建商户");
// 			}
// 			Platform platform=platforms.get(0);
// 			if(StringUtils.hasText(appShopConfig.getPlatShopId()) && !platform.getShopId().equals(appShopConfig.getPlatShopId())){
// 				return Result.error("platShopId-not-right","归属平台商户编号不是有效的平台商户");
// 			}
// 			appShopConfig.setLastUpdateTime(DateUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
// 			appShopConfigService.updateByPk(appShopConfig);
// 			Shop shop=new Shop();
// 			shop.setId(appShopConfig.getShopId());
// 			shop.setAcctPrjType(appShopConfig.getAcctPrjType());
// 			shop.setPlatBranchId(appShopConfig.getPlatBranchId());
// 			shop.setPlatShopId(appShopConfig.getPlatShopId());
// 			shop.setIsJoinPlat(appShopConfig.getIsJoinPlat());
// 			shopService.updateSomeFieldByPk(shop);
//
// 			try {
// 				cacheHKVService.delete(AppShopConfig.class.getSimpleName()+"-"+appShopConfig.getShopId());
// 			} catch (Exception e) {
//
// 			}
// 			return Result.ok().setData(appShopConfig);
// 		}catch (BizException e) {
// 			logger.error("",e);
// 			return Result.error(e);
// 		}catch (Exception e) {
// 			logger.error("",e);
// 			return Result.error(e);
// 		}
// 	}
//
//
//
//
// 	/**
// 	@ApiOperation( value = "根据主键列表批量删除app_shop_config信息",notes="batchDelAppShopConfig,仅需要上传主键字段")
// 	@ApiResponses({
// 		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
// 	})
// 	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
// 	public Result batchDelAppShopConfig(@RequestBody List<AppShopConfig> appShopConfigs) {
//
//
// 		try{
// 			appShopConfigService.batchDelete(appShopConfigs);
// 		}catch (BizException e) {
// 			logger.error("",e);
// 			return Result.error(e);
// 		}catch (Exception e) {
// 			logger.error("",e);
// 			return Result.error(e);
// 		}
// 	}
// 	*/
// }
