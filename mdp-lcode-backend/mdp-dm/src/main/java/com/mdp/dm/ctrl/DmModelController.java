package com.mdp.dm.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.LangTips;
import com.mdp.core.entity.Result;
import com.mdp.core.query.QueryTools;
import com.mdp.dm.entity.DmModel;
import com.mdp.dm.service.DmModelService;
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
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
/**
 * @author maimeng-mdp code-gen
 * @since 2024-5-4
 */
@RestController
@RequestMapping(value="/mdp/dm/model")
@Api(tags={"-操作接口"})
public class DmModelController {
	
	static Logger logger =LoggerFactory.getLogger(DmModelController.class);
	
	@Autowired
	private DmModelService modelService;

	@ApiOperation( value = "-查询列表",notes=" ")
	@ApiEntityParams(DmModel.class)
	@ApiResponses({
		@ApiResponse(code = 200,response= DmModel.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listModel(@ApiIgnore @RequestParam Map<String,Object> params){
			User user=LoginUtils.getCurrentUserInfo();
			QueryWrapper<DmModel> qw = QueryTools.initQueryWrapper(DmModel.class , params);
			qw.eq("branch_id",user.getBranchId());
			IPage page=QueryTools.initPage(params);
			List<Map<String,Object>> datas = modelService.selectListMapByWhere(page,qw,params);
			return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());
	}


	@ApiOperation( value = "-查询列表-缓存优先",notes=" ")
	@ApiEntityParams(DmModel.class)
	@ApiResponses({
			@ApiResponse(code = 200,response= DmModel.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list/cacheFirst",method=RequestMethod.GET)
	public Result listModelCacheFirst(@ApiIgnore @RequestParam Map<String,Object> params){
		User user=LoginUtils.getCurrentUserInfo();
		List<DmModel> datas=modelService.getModelsByBranchIdCacheFirst(user.getBranchId());
		return Result.ok("query-ok","查询成功").setData(datas).setTotal(datas.size());
	}
	@ApiOperation( value = "-新增",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response= DmModel.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addModel(@RequestBody DmModel model) {
		User user=LoginUtils.getCurrentUserInfo();
		model.setBranchId(user.getBranchId());
		model.setCuserid(user.getUserid());
		model.setCtime(new Date());
		 modelService.save(model);
		 modelService.putModelToCache(model.getBranchId(),model.getId(),model);
         return Result.ok("add-ok","添加成功！");
	}

	@ApiOperation( value = "-删除",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delModel(@RequestBody DmModel model){
		User user=LoginUtils.getCurrentUserInfo();
		DmModel modelDb=modelService.getById(model.getId());

		if(!user.getBranchId().equalsIgnoreCase(modelDb.getBranchId())){
			return Result.error("branchId-not-match","该模型不属于您的企业模型，您无权限修改");
		}
		modelService.removeById(modelDb.getId());
		modelService.removeModelFromCache(modelDb.getBranchId(),modelDb.getId());
        return Result.ok("del-ok","删除成功！");
	}

	@ApiOperation( value = "-修改",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response= DmModel.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editModel(@RequestBody DmModel model) {
		User user=LoginUtils.getCurrentUserInfo();
		DmModel modelDb=modelService.getById(model.getId());
		if(!user.getBranchId().equalsIgnoreCase(modelDb.getBranchId())){
			return Result.error("branchId-not-match","该模型不属于您的企业模型，您无权限修改");
		}
		modelService.updateById(model);
		modelService.putModelToCache(modelDb.getBranchId(),modelDb.getId(),model);
        return Result.ok("edit-ok","修改成功！");
	}

    @ApiOperation( value = "-批量修改某些字段",notes="")
    @ApiEntityParams( value = DmModel.class, props={ }, remark = "", paramType = "body" )
	@ApiResponses({
			@ApiResponse(code = 200,response= DmModel.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields( @ApiIgnore @RequestBody Map<String,Object> params) {
            User user= LoginUtils.getCurrentUserInfo();
			List<String> ids= (List<String>) params.get("$pks");
			List<DmModel> modelsDb=modelService.listByIds(ids);
		for (DmModel modelDb : modelsDb) {
			if(!user.getBranchId().equalsIgnoreCase(modelDb.getBranchId())){
				return Result.error("branchId-not-match","该模型不属于您的企业模型，您无权限修改");
			}
		}
            modelService.editSomeFields(params);
            return Result.ok("edit-ok","更新成功");
	}

	@ApiOperation( value = "-批量删除",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelModel(@RequestBody List<DmModel> models) {
	    User user= LoginUtils.getCurrentUserInfo();
        if(models.size()<=0){
            return Result.error("batchDel-data-err-0","请上送待删除数据列表");
        }
         List<DmModel> datasDb=modelService.listByIds(models.stream().map(i-> i.getId() ).collect(Collectors.toList()));

        List<DmModel> can=new ArrayList<>();
        List<DmModel> no=new ArrayList<>();
        for (DmModel data : datasDb) {
            if(user.getBranchId().equalsIgnoreCase(data.getBranchId())){
                can.add(data);
				modelService.removeModelFromCache(data.getBranchId(),data.getId());
            }else{
                no.add(data);
            }
        }
        List<String> msgs=new ArrayList<>();
        if(can.size()>0){
            modelService.removeByIds(can);
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

	@ApiOperation( value = "-根据主键查询一条数据",notes=" ")
     @ApiResponses({
            @ApiResponse(code = 200,response= DmModel.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
    })
    @RequestMapping(value="/queryById",method=RequestMethod.GET)
    public Result queryById(DmModel model) {
        DmModel data = (DmModel) modelService.getById(model);
        return Result.ok().setData(data);
    }

}
