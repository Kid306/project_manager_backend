package com.mdp.menu.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.entity.Tips;
import com.mdp.core.err.BizException;
import com.mdp.core.query.QueryTools;
import com.mdp.menu.entity.MenuModule;
import com.mdp.menu.entity.MenuModuleBranch;
import com.mdp.menu.service.MenuModuleBranchService;
import com.mdp.menu.service.MenuModuleService;
import com.mdp.qx.HasRole;
import com.mdp.safe.client.cache.ModuleBranchRedisCacheService;
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

import static com.mdp.core.utils.BaseUtils.*;


/**
 * url编制采用rest风格,如对menu_module_branch 机构开通模块对应关系表的操作有增删改查,对应的url分别为:<br>
 *  新增: menu/menuModuleBranch/add <br>
 *  查询: menu/menuModuleBranch/list<br>
 *  模糊查询: menu/menuModuleBranch/listKey<br>
 *  修改: menu/menuModuleBranch/edit <br>
 *  删除: menu/menuModuleBranch/del<br>
 *  批量删除: menu/menuModuleBranch/batchDel<br>
 * 组织 com  顶级模块 mdp 大模块 menu 小模块 <br>
 * 实体 MenuModuleBranch 表 menu_module_branch 当前主键(包括多主键): branch_id,module_id; 
 ***/
@RestController("mdp.menu.menuModuleBranchController")
@RequestMapping(value="/*/menu/menuModuleBranch")
@Api(tags={"机构开通模块对应关系表操作接口"})
public class MenuModuleBranchController {
	
	static Logger logger =LoggerFactory.getLogger(MenuModuleBranchController.class);
	
	@Autowired
	private MenuModuleBranchService menuModuleBranchService;

	@Autowired
	MenuModuleService moduleService;

	@Autowired
	ModuleBranchRedisCacheService  redisCacheService;
	 

	Map<String,Object> fieldsMap = toMap(new MenuModuleBranch());
 
	
	@ApiOperation( value = "查询机构开通模块对应关系表信息列表",notes=" ") 
	@ApiResponses({
		@ApiResponse(code = 200,response=MenuModuleBranch.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listMenuModuleBranch( @ApiIgnore @RequestParam Map<String,Object>  menuModuleBranch){
		
		
		
		IPage page=QueryTools.initPage(menuModuleBranch);
		User user=LoginUtils.getCurrentUserInfo();
		if(!LoginUtils.isSuperAdmin()){ 
			menuModuleBranch.put("branchId",user.getBranchId());
		}
		QueryWrapper<MenuModuleBranch> qw=QueryTools.initQueryWrapper(MenuModuleBranch.class,menuModuleBranch);
		List<Map<String,Object>>	menuModuleBranchList = menuModuleBranchService.selectListMapByWhere(page,qw,menuModuleBranch);
		
		return Result.ok().setData(menuModuleBranchList).setTotal(page.getTotal());

	}
	@ApiOperation( value = "统计企业开通产品情况",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200,response=MenuModuleBranch.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/calcBranchModule",method=RequestMethod.GET)
	public Result calcBranchModule( ){
		
		
		User user=LoginUtils.getCurrentUserInfo();
		Map<String,Object> data=this.menuModuleBranchService.calcBranchModule(user.getBranchId());
		return Result.ok().setData(data);
	}

	@ApiOperation( value = "新增一条机构开通模块对应关系表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=MenuModuleBranch.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@HasRole(roles = {"superAdmin"})
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addMenuModuleBranch(@RequestBody MenuModuleBranch menuModuleBranch) {
		
		
		try{
			if(!StringUtils.hasText(menuModuleBranch.getBranchId())) {
				return Result.error("pk-0","请上送机构编号");
			}
			if(!StringUtils.hasText(menuModuleBranch.getModuleId())) {
				return Result.error("pk-0","请上送模块编号");
			}
			MenuModule moduleDb=this.moduleService.selectOneById(menuModuleBranch.getModuleId());
			if(moduleDb==null){
				return Result.error("module-0","模块已不存在");
			}
			if(menuModuleBranchService.selectOneObject(menuModuleBranch) !=null ){
				return Result.error("pk-exists","已经开通，无须再开");
			}
			User user=LoginUtils.getCurrentUserInfo();
			menuModuleBranch.setModuleName(moduleDb.getName());
			menuModuleBranch.setCtime(new Date());
			menuModuleBranch.setCuserid(user.getUserid());
			menuModuleBranch.setCusername(user.getUsername());
			menuModuleBranch.setStatus("0");
			menuModuleBranch.setLtime(new Date());
			menuModuleBranch.setLuserid(user.getUserid());
			menuModuleBranch.setLusername(user.getUsername());
			menuModuleBranchService.insert(menuModuleBranch);
			return Result.ok().setData(menuModuleBranch);
		}catch (BizException e) { 
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}  
	}

	@ApiOperation( value = "删除一条机构开通模块对应关系表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	})
	@HasRole(roles = {"superAdmin"})
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delMenuModuleBranch(@RequestBody MenuModuleBranch menuModuleBranch){
		
		
		try{
            if(!StringUtils.hasText(menuModuleBranch.getBranchId())) {
                 return Result.error("pk-not-exists","请上送主键参数branchId");
            }
            if(!StringUtils.hasText(menuModuleBranch.getModuleId())) {
                 return Result.error("pk-not-exists","请上送主键参数moduleId");
            }
            MenuModuleBranch menuModuleBranchDb = menuModuleBranchService.selectOneObject(menuModuleBranch);
            if( menuModuleBranchDb == null ){
                return Result.error("data-not-exists","数据不存在，无法删除");
            }
			menuModuleBranchService.deleteByPk(menuModuleBranch);
			redisCacheService.clear(menuModuleBranchDb.getBranchId(),menuModuleBranchDb.getModuleId());
			return Result.ok();
		}catch (BizException e) {
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}  
	}

	@ApiOperation( value = "根据主键修改一条机构开通模块对应关系表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=MenuModuleBranch.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@HasRole(roles = {"superAdmin"})
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editMenuModuleBranch(@RequestBody MenuModuleBranch menuModuleBranch) {
		
		
		try{
            if(!StringUtils.hasText(menuModuleBranch.getBranchId())) {
                 return Result.error("pk-not-exists","请上送主键参数branchId");
            }
            if(!StringUtils.hasText(menuModuleBranch.getModuleId())) {
                 return Result.error("pk-not-exists","请上送主键参数moduleId");
            }
            MenuModuleBranch menuModuleBranchDb = menuModuleBranchService.selectOneObject(menuModuleBranch);
            if( menuModuleBranchDb == null ){
                return Result.error("data-not-exists","数据不存在，无法修改");
            }
			menuModuleBranchService.updateSomeFieldByPk(menuModuleBranch);
			redisCacheService.clear(menuModuleBranchDb.getBranchId(),menuModuleBranchDb.getModuleId());
			return Result.ok().setData(menuModuleBranch);
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
			@ApiResponse(code = 200,response=MenuModuleBranch.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@HasRole(roles = {"superAdmin"})
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields(@RequestBody Map<String,Object> menuModuleBranchMap) {
		
		
		try{
			List<Map<String,Object>> pkList= (List<Map<String,Object>>) menuModuleBranchMap.get("$pks");
			if(pkList==null || pkList.size()==0){
				return Result.error("$pks-0","$pks不能为空");
			}

			Set<String> fields=new HashSet<>();
            fields.add("branchId");
            fields.add("moduleId");
			for (String fieldName : menuModuleBranchMap.keySet()) {
				if(fields.contains(fieldName)){
					return Result.error(fieldName+"-no-edit",fieldName+"不允许修改");
				}
			}
			Set<String> fieldKey=menuModuleBranchMap.keySet().stream().filter(i-> fieldsMap.containsKey(i)).collect(Collectors.toSet());
			fieldKey=fieldKey.stream().filter(i->!StringUtils.isEmpty(menuModuleBranchMap.get(i) )).collect(Collectors.toSet());

			if(fieldKey.size()<=0) {
				return Result.error("fieldKey-0","没有需要更新的字段");
 			}
			MenuModuleBranch menuModuleBranch = fromMap(menuModuleBranchMap,MenuModuleBranch.class);
			List<MenuModuleBranch> menuModuleBranchsDb=menuModuleBranchService.selectListByIds(pkList);
			if(menuModuleBranchsDb==null ||menuModuleBranchsDb.size()==0){
				return Result.error("data-0","记录已不存在");
			}
			List<MenuModuleBranch> can=new ArrayList<>();
			List<MenuModuleBranch> no=new ArrayList<>();
			User user = LoginUtils.getCurrentUserInfo();
			for (MenuModuleBranch menuModuleBranchDb : menuModuleBranchsDb) {
				Tips tips2 = new Tips("检查通过"); 
				if(!tips2.isOk()){
				    no.add(menuModuleBranchDb); 
				}else{
					can.add(menuModuleBranchDb);
				}
			}
			if(can.size()>0){
                menuModuleBranchMap.put("$pks",can.stream().map(i->map( "branchId",i.getBranchId(),  "moduleId",i.getModuleId())).collect(Collectors.toList()));
			    menuModuleBranchService.editSomeFields(menuModuleBranchMap);
				for (MenuModuleBranch moduleBranch : can) {
					redisCacheService.clear(moduleBranch.getBranchId(),moduleBranch.getModuleId());
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

	@ApiOperation( value = "根据主键列表批量删除机构开通模块对应关系表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	})
	@HasRole(roles = {"superAdmin"})
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelMenuModuleBranch(@RequestBody List<MenuModuleBranch> menuModuleBranchs) {
		
         
        try{ 
            if(menuModuleBranchs.size()<=0){
                return Result.error("data-0","请上送待删除数据列表");
            }
             List<MenuModuleBranch> datasDb=menuModuleBranchService.selectListByIds(menuModuleBranchs.stream().map(i->map( "branchId",i.getBranchId() ,  "moduleId",i.getModuleId() )).collect(Collectors.toList()));

            List<MenuModuleBranch> can=new ArrayList<>();
            List<MenuModuleBranch> no=new ArrayList<>();
            for (MenuModuleBranch data : datasDb) {
                if(true){
                    can.add(data);
                }else{
                    no.add(data);
                } 
            }
            List<String> msgs=new ArrayList<>();
            if(can.size()>0){
                menuModuleBranchService.batchDelete(can);
                msgs.add(String.format("成功删除%s条数据.",can.size()));
				for (MenuModuleBranch moduleBranch : can) {
					redisCacheService.clear(moduleBranch.getBranchId(),moduleBranch.getModuleId());
				}
            }
    
            if(no.size()>0){ 
                msgs.add(String.format("以下%s条数据不能删除.【%s】",no.size(),no.stream().map(i-> i.getBranchId() +" "+ i.getModuleId() ).collect(Collectors.joining(","))));
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
