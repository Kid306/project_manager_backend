package com.mdp.menu.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.LangTips;
import com.mdp.core.entity.Result;
import com.mdp.core.query.QueryTools;
import com.mdp.menu.entity.MenuFavorite;
import com.mdp.menu.service.MenuFavoriteService;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.swagger.ApiEntityParams;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
/**
 * @author maimeng-mdp code-gen
 * @since 2024-5-15
 */
@RestController
@RequestMapping(value="/mdp/menu/menuFavorite")
@Api(tags={"菜单收藏夹-操作接口"})
public class MenuFavoriteController {
	
	static Logger logger =LoggerFactory.getLogger(MenuFavoriteController.class);
	
	@Autowired
	private MenuFavoriteService menuFavoriteService;

	@ApiOperation( value = "菜单收藏夹-查询列表",notes=" ")
	@ApiEntityParams(MenuFavorite.class)
	@ApiResponses({
		@ApiResponse(code = 200,response=MenuFavorite.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listMenuFavorite(@ApiIgnore @RequestParam Map<String,Object> params){
			User user=LoginUtils.getCurrentUserInfo();
			QueryWrapper<MenuFavorite> qw = QueryTools.initQueryWrapper(MenuFavorite.class , params);
			IPage page=QueryTools.initPage(params);
			List<Map<String,Object>> datas = menuFavoriteService.selectListMapByWhere(page,qw,params);
			return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());
	}
	

	@ApiOperation( value = "菜单收藏夹-新增",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=MenuFavorite.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addMenuFavorite(@RequestBody MenuFavorite menuFavorite) {
		 menuFavoriteService.save(menuFavorite);
         return Result.ok("add-ok","添加成功！");
	}

	@ApiOperation( value = "菜单收藏夹-删除",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delMenuFavorite(@RequestBody MenuFavorite menuFavorite){
		menuFavoriteService.removeById(menuFavorite);
        return Result.ok("del-ok","删除成功！");
	}

	@ApiOperation( value = "菜单收藏夹-修改",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=MenuFavorite.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editMenuFavorite(@RequestBody MenuFavorite menuFavorite) {
		menuFavoriteService.updateById(menuFavorite);
        return Result.ok("edit-ok","修改成功！");
	}

    @ApiOperation( value = "菜单收藏夹-批量修改某些字段",notes="")
    @ApiEntityParams( value = MenuFavorite.class, props={ }, remark = "菜单收藏夹", paramType = "body" )
	@ApiResponses({
			@ApiResponse(code = 200,response=MenuFavorite.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields( @ApiIgnore @RequestBody Map<String,Object> params) {
            User user= LoginUtils.getCurrentUserInfo();
            menuFavoriteService.editSomeFields(params);
            return Result.ok("edit-ok","更新成功");
	}

	@ApiOperation( value = "菜单收藏夹-批量删除",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelMenuFavorite(@RequestBody List<MenuFavorite> menuFavorites) {
	    User user= LoginUtils.getCurrentUserInfo();
        if(menuFavorites.size()<=0){
            return Result.error("batchDel-data-err-0","请上送待删除数据列表");
        }
         List<MenuFavorite> datasDb=menuFavoriteService.listByIds(menuFavorites.stream().map(i-> i.getId() ).collect(Collectors.toList()));

        List<MenuFavorite> can=new ArrayList<>();
        List<MenuFavorite> no=new ArrayList<>();
        for (MenuFavorite data : datasDb) {
            if(true){
                can.add(data);
            }else{
                no.add(data);
            }
        }
        List<String> msgs=new ArrayList<>();
        if(can.size()>0){
            menuFavoriteService.removeByIds(can);
            msgs.add(LangTips.transMsg("del-ok-num","成功删除%s条数据.",can.size()));
        }

        if(no.size()>0){
            msgs.add(LangTips.transMsg("not-allow-del-num","以下%s条数据不能删除:【%s】",no.size(),no.stream().map(i-> i.getId() ).collect(Collectors.joining(","))));
        }
        if(can.size()>0){
             return Result.ok(msgs.stream().collect(Collectors.joining()));
        }else {
            return Result.error(msgs.stream().collect(Collectors.joining()));
        }
	} 

	@ApiOperation( value = "菜单收藏夹-根据主键查询一条数据",notes=" ")
     @ApiResponses({
            @ApiResponse(code = 200,response=MenuFavorite.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
    })
    @RequestMapping(value="/queryById",method=RequestMethod.GET)
    public Result queryById(MenuFavorite menuFavorite) {
        MenuFavorite data = (MenuFavorite) menuFavoriteService.getById(menuFavorite);
        return Result.ok().setData(data);
    }

}
