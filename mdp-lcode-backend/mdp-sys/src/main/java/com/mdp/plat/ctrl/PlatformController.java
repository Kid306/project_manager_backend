package com.mdp.plat.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.err.BizException;
import com.mdp.core.query.QueryTools;
import com.mdp.plat.entity.Platform;
import com.mdp.plat.service.PlatformService;
import com.mdp.qx.HasRole;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

/**
 * url编制采用rest风格,如对ADM.plat_platform plat_platform的操作有增删改查,对应的url分别为:<br>
 *  新增: plat/platform/add <br>
 *  查询: plat/platform/list<br>
 *  模糊查询: plat/platform/listKey<br>
 *  修改: plat/platform/edit <br>
 *  删除: plat/platform/del<br>
 *  批量删除: plat/platform/batchDel<br>
 * 组织 com  顶级模块 mdp 大模块 plat 小模块 <br>
 * 实体 Platform 表 ADM.plat_platform 当前主键(包括多主键): id; 
 ***/
@RestController("mdp.plat.platformController")
@RequestMapping(value="/*/plat/platform")
@Api(tags={"plat_platform操作接口"})
public class PlatformController {
	
	static Logger logger =LoggerFactory.getLogger(PlatformController.class);

	@Value("${mdp.platform-id:platform-001}")
	String platformId;
	
	@Autowired
	private PlatformService platformService;
	 
		
 
	
	@ApiOperation( value = "查询plat_platform信息列表",notes=" ") 
	@ApiResponses({
		@ApiResponse(code = 200,response=Platform.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listPlatform( @ApiIgnore @RequestParam Map<String,Object>  platform){
		 
		
		IPage page=QueryTools.initPage(platform);
		QueryWrapper<Platform> qw= QueryTools.initQueryWrapper(Platform.class,platform);

		List<Map<String,Object>>	platformList = platformService.selectListMapByWhere(page,qw,platform);
		
		return Result.ok().setData(platformList);
		
	}
	@ApiOperation( value = "查询plat_platform信息列表",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200,response=Platform.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/detail",method=RequestMethod.GET)
	public Result getDetail( String id ){
		
		if(!StringUtils.hasText(id)){
			id=platformId;
		}
		Platform platform=this.platformService.selectOneObject(new Platform(id));
 		return Result.ok().setData(platform);
		
	}
 
	
	/**
	@ApiOperation( value = "新增一条plat_platform信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=Platform.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addPlatform(@RequestBody Platform platform) {
		
		
		try{
		    boolean createPk=false;
			if(StringUtils.isEmpty(platform.getId())) {
			    createPk=true;
				platform.setId(platformService.createKey("id"));
			}
			if(createPk==false){
                 if(platformService.selectOneObject(platform) !=null ){
                    tips.setFailureMsg("编号重复，请修改编号再提交");
                    
                    return Result.ok();
                }
            }
			platformService.insert(platform);
			return Result.ok().setData(platform);
		}catch (BizException e) { 
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}  
	}
	*/
	
	/**
	@ApiOperation( value = "删除一条plat_platform信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delPlatform(@RequestBody Platform platform){
		
		
		try{
			platformService.deleteByPk(platform);
		}catch (BizException e) { 
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}  
	}
	 */
	

	@ApiOperation( value = "根据主键修改一条plat_platform信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=Platform.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@HasRole(roles = "superAdmin")
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editPlatform(@RequestBody Platform platform) {
		
		
		try{
			if(platform.getDiscountPct()==null){
				platform.setDiscountPct(100);
			}
			platformService.editPlatform(platform);
			return Result.ok().setData(platform);
		}catch (BizException e) { 
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}  
	}
	

	
	/**
	@ApiOperation( value = "根据主键列表批量删除plat_platform信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelPlatform(@RequestBody List<Platform> platforms) {
		
		 
		try{ 
			platformService.batchDelete(platforms);
		}catch (BizException e) { 
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}  
	} 
	*/
}
