// package com.mdp.app.ctrl;
//
// import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
// import com.baomidou.mybatisplus.core.metadata.IPage;
// import com.mdp.app.entity.AppTpAuth;
// import com.mdp.app.service.AppTpAuthService;
// import com.mdp.app.service.WxpubClientService;
// import com.mdp.core.entity.Result;
// import com.mdp.core.err.BizException;
// import com.mdp.core.query.QueryTools;
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
// import java.util.List;
// import java.util.Map;
// /**
//  * url编制采用rest风格,如对ADMIN.app_tp_auth MDP平台应用与第三方应用关系的操作有增删改查,对应的url分别为:<br>
//  *  新增: app/appTpAuth/add <br>
//  *  查询: app/appTpAuth/list<br>
//  *  模糊查询: app/appTpAuth/listKey<br>
//  *  修改: app/appTpAuth/edit <br>
//  *  删除: app/appTpAuth/del<br>
//  *  批量删除: app/appTpAuth/batchDel<br>
//  * 组织 com.qqkj  顶级模块 mdp 大模块 app 小模块 <br>
//  * 实体 AppTpAuth 表 ADMIN.app_tp_auth 当前主键(包括多主键): auth_id;
//  ***/
// @RestController("mdp.app.appTpAuthController")
// @RequestMapping(value="/*/app/appTpAuth")
// @Api(tags={"MDP平台应用与第三方应用关系操作接口"})
// public class AppTpAuthController {
//
// 	static Log logger=LogFactory.getLog(AppTpAuthController.class);
//
// 	@Autowired
// 	private AppTpAuthService appTpAuthService;
// 	@Autowired
//     WxpubClientService wxpubClientService;
//
// 	@Autowired
// 	private com.mdp.tpa.client.service.AppTpAuthService cacheAppTpAuthService;
//
// 	@ApiOperation( value = "查询MDP平台应用与第三方应用关系信息列表",notes="listAppTpAuth,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
// 	@ApiResponses({
// 		@ApiResponse(code = 200,response= AppTpAuth.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},pageInfo:{total:总记录数},data:[数据对象1,数据对象2,...]}")
// 	})
// 	@RequestMapping(value="/list",method=RequestMethod.GET)
// 	public Result listAppTpAuth( @ApiIgnore @RequestParam Map<String,Object>  appTpAuth){
//
// 		IPage page=QueryTools.initPage(appTpAuth);
// 		QueryWrapper<AppTpAuth> qw= QueryTools.initQueryWrapper(AppTpAuth.class, appTpAuth);
// 		List<Map<String,Object>>	appTpAuthList = appTpAuthService.selectListMapByWhere(page,qw,appTpAuth);
// 		return Result.ok().setData(appTpAuthList);
// 	}
//
//
//
// 	/***/
// 	@ApiOperation( value = "新增一条MDP平台应用与第三方应用关系信息",notes="addAppTpAuth,主键如果为空，后台自动生成")
// 	@ApiResponses({
// 		@ApiResponse(code = 200,response=AppTpAuth.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
// 	})
// 	@HasRole(roles= {"superAdmin"})
// 	@RequestMapping(value="/add",method=RequestMethod.POST)
// 	public Result addAppTpAuth(@RequestBody AppTpAuth appTpAuth) {
//
//
// 		try{
// 			if(StringUtils.isEmpty(appTpAuth.getAuthId())) {
// 				appTpAuth.setAuthId(appTpAuthService.createKey("authId"));
// 			}else{
// 				 AppTpAuth appTpAuthQuery = new  AppTpAuth(appTpAuth.getAuthId());
// 				if(appTpAuthService.countByWhere(appTpAuthQuery)>0){
// 					return Result.error("id-exists","编号重复，请修改编号再提交");
// 				}
// 			}
// 			appTpAuthService.insert(appTpAuth);
// 			return Result.ok().setData(appTpAuth);
// 		}catch (BizException e) {
// 			logger.error("执行异常",e);
// 			return Result.error(e);
// 		}catch (Exception e) {
// 			logger.error("执行异常",e);
// 			return Result.error(e);
// 		}
// 	}
//
//
// 	/***/
// 	@ApiOperation( value = "删除一条MDP平台应用与第三方应用关系信息",notes="delAppTpAuth,仅需要上传主键字段")
// 	@ApiResponses({
// 		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
// 	})
// 	@HasRole(roles= {"superAdmin"})
// 	@RequestMapping(value="/del",method=RequestMethod.POST)
// 	public Result delAppTpAuth(@RequestBody AppTpAuth appTpAuth){
//
//
// 		try{
// 			appTpAuthService.deleteByPk(appTpAuth);
// 			cacheAppTpAuthService.clearOne(appTpAuth.getAuthId());
// 			wxpubClientService.clearAppTpAuth(appTpAuth.getAuthId());
// 			return Result.ok();
// 		}catch (BizException e) {
// 			logger.error("执行异常",e);
// 			return Result.error(e);
// 		}catch (Exception e) {
// 			logger.error("执行异常",e);
// 			return Result.error(e);
// 		}
// 	}
//
//
// 	/***/
// 	@ApiOperation( value = "根据主键修改一条MDP平台应用与第三方应用关系信息",notes="editAppTpAuth")
// 	@ApiResponses({
// 		@ApiResponse(code = 200,response=AppTpAuth.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
// 	})
// 	@HasRole(roles= {"superAdmin"})
// 	@RequestMapping(value="/edit",method=RequestMethod.POST)
// 	public Result editAppTpAuth(@RequestBody AppTpAuth appTpAuth) {
//
//
// 		try{
// 			appTpAuthService.updateByPk(appTpAuth);
// 			cacheAppTpAuthService.clearOne(appTpAuth.getAuthId());
// 			wxpubClientService.clearAppTpAuth(appTpAuth.getAuthId());
// 			return Result.ok().setData(appTpAuth);
// 		}catch (BizException e) {
// 			logger.error("执行异常",e);
// 			return Result.error(e);
// 		}catch (Exception e) {
// 			logger.error("执行异常",e);
// 			return Result.error(e);
// 		}
// 	}
//
//
//
//
// 	/***/
// 	@ApiOperation( value = "根据主键列表批量删除MDP平台应用与第三方应用关系信息",notes="batchDelAppTpAuth,仅需要上传主键字段")
// 	@ApiResponses({
// 		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
// 	})
// 	@HasRole(roles= {"superAdmin"})
// 	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
// 	public Result batchDelAppTpAuth(@RequestBody List<AppTpAuth> appTpAuths) {
//
//
// 		try{
// 			appTpAuthService.batchDelete(appTpAuths);
// 			for (AppTpAuth appTpAuth : appTpAuths) {
// 				cacheAppTpAuthService.clearOne(appTpAuth.getAuthId());
// 				wxpubClientService.clearAppTpAuth(appTpAuth.getAuthId());
// 			}
//
// 			return Result.ok();
// 		}catch (BizException e) {
// 			logger.error("执行异常",e);
// 			return Result.error(e);
// 		}catch (Exception e) {
// 			logger.error("执行异常",e);
// 			return Result.error(e);
// 		}
// 	}
//
// }
