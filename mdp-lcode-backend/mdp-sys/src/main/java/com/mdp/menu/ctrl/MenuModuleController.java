package com.mdp.menu.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.entity.Tips;
import com.mdp.core.err.BizException;
import com.mdp.core.query.QueryTools;
import com.mdp.menu.entity.MenuModule;
import com.mdp.menu.service.MenuModuleService;
import com.mdp.qx.HasRole;
import com.mdp.safe.client.cache.ModuleRedisCacheService;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;
import java.util.stream.Collectors;

import static com.mdp.core.utils.BaseUtils.fromMap;
import static com.mdp.core.utils.BaseUtils.toMap;

/**
 * url编制采用rest风格,如对menu_module 模块定义表-用于进行机构级别的权限控制，机构如果购买了模块，则能够进行访问的操作有增删改查,对应的url分别为:<br>
 *  新增: menu/menuModule/add <br>
 *  查询: menu/menuModule/list<br>
 *  模糊查询: menu/menuModule/listKey<br>
 *  修改: menu/menuModule/edit <br>
 *  删除: menu/menuModule/del<br>
 *  批量删除: menu/menuModule/batchDel<br>
 * 组织 com  顶级模块 mdp 大模块 menu 小模块 <br>
 * 实体 MenuModule 表 menu_module 当前主键(包括多主键): id; 
 ***/
@RestController("mdp.menu.menuModuleController")
@RequestMapping(value="/*/menu/menuModule")
@Api(tags={"模块定义表-用于进行机构级别的权限控制，机构如果购买了模块，则能够进行访问操作接口"})
public class MenuModuleController {
	
	static Logger logger =LoggerFactory.getLogger(MenuModuleController.class);
	
	@Autowired
	private MenuModuleService menuModuleService;

	@Autowired
	private ModuleRedisCacheService redisCacheService;

	Map<String,Object> fieldsMap = toMap(new MenuModule());
 
	
	@ApiOperation( value = "查询模块定义表-用于进行机构级别的权限控制，机构如果购买了模块，则能够进行访问信息列表",notes=" ") 
	@ApiResponses({
		@ApiResponse(code = 200,response=MenuModule.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listMenuModule( @ApiIgnore @RequestParam Map<String,Object>  menuModule){

		IPage page=QueryTools.initPage(menuModule);

		QueryWrapper<MenuModule> qw= QueryTools.initQueryWrapper(MenuModule.class,menuModule);
		List<Map<String,Object>>	menuModuleList = menuModuleService.selectListMapByWhere(page,qw,menuModule);
		
		return Result.ok().setData(menuModuleList).setTotal(page.getTotal());

	}
	
 

	@ApiOperation( value = "新增一条模块定义表-用于进行机构级别的权限控制，机构如果购买了模块，则能够进行访问信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=MenuModule.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@HasRole(roles = {"superAdmin"})
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addMenuModule(@RequestBody MenuModule menuModule) {
		
		
		try{
		    boolean createPk=false;
			if(!StringUtils.hasText(menuModule.getId())) {
			    createPk=true;
				menuModule.setId(menuModuleService.createKey("id"));
			}
			if(createPk==false){
                 if(menuModuleService.selectOneObject(menuModule) !=null ){
                    return Result.error("pk-exists","编号重复，请修改编号再提交");
                }
            }
			menuModuleService.insert(menuModule);
			return Result.ok().setData(menuModule);
		}catch (BizException e) { 
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}  
	}

	@ApiOperation( value = "删除一条模块定义表-用于进行机构级别的权限控制，机构如果购买了模块，则能够进行访问信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	})
	@HasRole(roles = {"superAdmin"})
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delMenuModule(@RequestBody MenuModule menuModule){
		
		
		try{
            if(!StringUtils.hasText(menuModule.getId())) {
                 return Result.error("pk-not-exists","请上送主键参数id");
            }
            MenuModule menuModuleDb = menuModuleService.selectOneObject(menuModule);
            if( menuModuleDb == null ){
                return Result.error("data-not-exists","数据不存在，无法删除");
            }
			menuModuleService.deleteByPk(menuModule);
			redisCacheService.clear(menuModuleDb.getId());
			return Result.ok();
		}catch (BizException e) { 
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}  
	}

	@ApiOperation( value = "根据主键修改一条模块定义表-用于进行机构级别的权限控制，机构如果购买了模块，则能够进行访问信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=MenuModule.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@HasRole(roles = {"superAdmin"})
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editMenuModule(@RequestBody MenuModule menuModule) {
		
		
		try{
            if(!StringUtils.hasText(menuModule.getId())) {
                 return Result.error("pk-not-exists","请上送主键参数id");
            }
            MenuModule menuModuleDb = menuModuleService.selectOneObject(menuModule);
            if( menuModuleDb == null ){
                return Result.error("data-not-exists","数据不存在，无法修改");
            }
			menuModuleService.updateSomeFieldByPk(menuModule);
			redisCacheService.clear(menuModuleDb.getId());
			return Result.ok().setData(menuModule);
		}catch (BizException e) { 
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}  
	}

    @ApiOperation( value = "批量修改某些字段",notes="")
	@ApiResponses({
			@ApiResponse(code = 200,response=MenuModule.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@HasRole(roles = {"superAdmin"})
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields(@RequestBody Map<String,Object> menuModuleMap) {
		
		
		try{
            List<String> ids= (List<String>) menuModuleMap.get("$pks");
			if(ids==null || ids.size()==0){
				return Result.error("ids-0","ids不能为空");
			}

			Set<String> fields=new HashSet<>();
            fields.add("id");
			for (String fieldName : menuModuleMap.keySet()) {
				if(fields.contains(fieldName)){
					return Result.error(fieldName+"-no-edit",fieldName+"不允许修改");
				}
			}
			Set<String> fieldKey=menuModuleMap.keySet().stream().filter(i-> fieldsMap.containsKey(i)).collect(Collectors.toSet());
			fieldKey=fieldKey.stream().filter(i->!StringUtils.isEmpty(menuModuleMap.get(i) )).collect(Collectors.toSet());

			if(fieldKey.size()<=0) {
				return Result.error("fieldKey-0","没有需要更新的字段");
 			}
			MenuModule menuModule = fromMap(menuModuleMap,MenuModule.class);
			List<MenuModule> menuModulesDb=menuModuleService.selectListByIds(ids);
			if(menuModulesDb==null ||menuModulesDb.size()==0){
				return Result.error("data-0","记录已不存在");
			}
			List<MenuModule> can=new ArrayList<>();
			List<MenuModule> no=new ArrayList<>();
			User user = LoginUtils.getCurrentUserInfo();
			for (MenuModule menuModuleDb : menuModulesDb) {
				Tips tips2 = new Tips("检查通过"); 
				if(!tips2.isOk()){
				    no.add(menuModuleDb); 
				}else{
					can.add(menuModuleDb);
				}
			}
			if(can.size()>0){
                menuModuleMap.put("$pks",can.stream().map(i->i.getId()).collect(Collectors.toList()));
			    menuModuleService.editSomeFields(menuModuleMap);
				for (MenuModule module : can) {
					redisCacheService.clear(module.getId());
				}
			}
			List<String> msgs=new ArrayList<>();
			if(can.size()>0){
				msgs.add(String.format("成功更新以下%s条数据",can.size()));
			}
			if(no.size()>0){
				msgs.add(String.format("以下%s个数据无权限更新",no.size()));
			}
			if(can.size()>0){
				return Result.ok(msgs.stream().collect(Collectors.joining()));
			}else {
				return Result.error(msgs.stream().collect(Collectors.joining()));
			}
			//return Result.ok().setData(xmMenu);
		}catch (BizException e) {
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}
	}

	@ApiOperation( value = "根据主键列表批量删除模块定义表-用于进行机构级别的权限控制，机构如果购买了模块，则能够进行访问信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	})
	@HasRole(roles = {"superAdmin"})
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelMenuModule(@RequestBody List<MenuModule> menuModules) {
		
         
        try{ 
            if(menuModules.size()<=0){
                return Result.error("data-0","请上送待删除数据列表");
            }
             List<MenuModule> datasDb=menuModuleService.selectListByIds(menuModules.stream().map(i-> i.getId() ).collect(Collectors.toList()));

            List<MenuModule> can=new ArrayList<>();
            List<MenuModule> no=new ArrayList<>();
            for (MenuModule data : datasDb) {
                if(true){
                    can.add(data);
                }else{
                    no.add(data);
                } 
            }
            List<String> msgs=new ArrayList<>();
            if(can.size()>0){
                menuModuleService.batchDelete(menuModules);
                msgs.add(String.format("成功删除%s条数据.",can.size()));
				for (MenuModule module : can) {
					redisCacheService.clear(module.getId());
				}
            }
    
            if(no.size()>0){ 
                msgs.add(String.format("以下%s条数据不能删除.【%s】",no.size(),no.stream().map(i-> i.getId() ).collect(Collectors.joining(","))));
            }
            if(can.size()>0){
                 return Result.ok(msgs.stream().collect(Collectors.joining()));
            }else {
                return Result.error(msgs.stream().collect(Collectors.joining()));
            }
        }catch (BizException e) { 
            logger.error("",e);
            return Result.error(e);
        }catch (Exception e) {
            logger.error("",e);
            return Result.error(e);
        }
	} 

}
