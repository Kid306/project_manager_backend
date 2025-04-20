package com.mdp.app.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.app.entity.AppMdpDef;
import com.mdp.app.service.AppMdpDefService;
import com.mdp.core.entity.LangTips;
import com.mdp.core.entity.Result;
import com.mdp.core.err.BizException;
import com.mdp.core.query.QueryTools;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.mdp.core.utils.BaseUtils.toMap;

@RestController
@RequestMapping(value="/*/app/appMdpDef")
@Api(tags={"MDP平台应用定义表-操作接口"})
public class AppMdpDefController {
	
	static Logger logger =LoggerFactory.getLogger(AppMdpDefController.class);
	
	@Autowired
	private AppMdpDefService appMdpDefService;
	 

	Map<String,Object> fieldsMap = toMap(new AppMdpDef());
 
	
	@ApiOperation( value = "MDP平台应用定义表-查询列表",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=AppMdpDef.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listAppMdpDef(@ApiIgnore @RequestParam Map<String,Object>  appMdpDef){
		try {
			User user=LoginUtils.getCurrentUserInfo();
			QueryWrapper<AppMdpDef> qw = QueryTools.initQueryWrapper(AppMdpDef.class , appMdpDef);
			IPage page = QueryTools.initPage(appMdpDef);
			QueryTools.initPage(appMdpDef);
			Map<String,Object> ext=new HashMap<>();
			List<Map<String,Object>> datas = appMdpDefService.getBaseMapper().selectListMapByWhere(page,qw,ext);
			return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());
		}catch (BizException e) {
			return Result.error(e);
		}catch (Exception e) {
			return Result.error(e);
		}
	}
	

	@ApiOperation( value = "MDP平台应用定义表-新增",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=AppMdpDef.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addAppMdpDef(@RequestBody AppMdpDef appMdpDef) {
		 appMdpDefService.save(appMdpDef);
         return Result.ok("add-ok","添加成功！");
	}

	@ApiOperation( value = "MDP平台应用定义表-删除",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delAppMdpDef(@RequestBody AppMdpDef appMdpDef){
		appMdpDefService.removeById(appMdpDef);
        return Result.ok("del-ok","删除成功！");
	}

	@ApiOperation( value = "MDP平台应用定义表-修改",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=AppMdpDef.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editAppMdpDef(@RequestBody AppMdpDef appMdpDef) {
		appMdpDefService.updateById(appMdpDef);
        return Result.ok("edit-ok","修改成功！");
	}

    @ApiOperation( value = "MDP平台应用定义表-批量修改某些字段",notes="")
    @ApiEntityParams( value = AppMdpDef.class, props={ }, remark = "MDP平台应用定义表", paramType = "body" )
	@ApiResponses({
			@ApiResponse(code = 200,response=AppMdpDef.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields( @ApiIgnore @RequestBody Map<String,Object> appMdpDefMap) {
        try{
            User user= LoginUtils.getCurrentUserInfo();
            appMdpDefService.editSomeFields(appMdpDefMap);
            return Result.ok("edit-ok","更新成功");
        }catch (BizException e) {
            logger.error("",e);
            return Result.error(e);
        }catch (Exception e) {
            logger.error("",e);
            return Result.error(e);
        }
	}

	@ApiOperation( value = "MDP平台应用定义表-批量删除",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelAppMdpDef(@RequestBody List<AppMdpDef> appMdpDefs) {
	    User user= LoginUtils.getCurrentUserInfo();
        try{ 
            if(appMdpDefs.size()<=0){
                return Result.error("appMdpDef-batchDel-data-err-0","请上送待删除数据列表");
            }
             List<AppMdpDef> datasDb=appMdpDefService.listByIds(appMdpDefs.stream().map(i-> i.getMdpAppid() ).collect(Collectors.toList()));

            List<AppMdpDef> can=new ArrayList<>();
            List<AppMdpDef> no=new ArrayList<>();
            for (AppMdpDef data : datasDb) {
                if(true){
                    can.add(data);
                }else{
                    no.add(data);
                } 
            }
            List<String> msgs=new ArrayList<>();
            if(can.size()>0){
                appMdpDefService.removeByIds(can);
                msgs.add(LangTips.transMsg("del-ok-num","成功删除%s条数据.",can.size()));
            }
    
            if(no.size()>0){ 
                msgs.add(LangTips.transMsg("not-allow-del-num","以下%s条数据不能删除:【%s】",no.size(),no.stream().map(i-> i.getMdpAppid() ).collect(Collectors.joining(","))));
            }
            if(can.size()>0){
                 return Result.ok(msgs.stream().collect(Collectors.joining()));
            }else {
                return Result.error(msgs.stream().collect(Collectors.joining()));
            } 
        }catch (BizException e) { 
           return Result.error(e);
        }catch (Exception e) {
            return Result.error(e);
        }


	} 

	@ApiOperation( value = "MDP平台应用定义表-根据主键查询一条数据",notes=" ")
     @ApiResponses({
            @ApiResponse(code = 200,response=AppMdpDef.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
    })
    @RequestMapping(value="/queryById",method=RequestMethod.GET)
    public Result queryById(AppMdpDef appMdpDef) {
        AppMdpDef data = (AppMdpDef) appMdpDefService.getById(appMdpDef);
        return Result.ok().setData(data);
    }

}
