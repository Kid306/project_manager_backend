package com.mdp.form.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.err.BizException;
import com.mdp.core.query.QueryTools;
import com.mdp.dm.base.service.TableDataBaseService;
import com.mdp.form.entity.FormDef;
import com.mdp.form.service.FormDefService;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/*/form/formDef")
@Api(tags={"表单定义-操作接口"})
public class FormDefController {
	
	static Logger logger =LoggerFactory.getLogger(FormDefController.class);
	
	@Autowired
	private FormDefService formDefService;

	@Autowired
	TableDataBaseService tableDataService;

	@ApiOperation( value = "表单定义-查询列表",notes=" ")
	@ApiEntityParams(FormDef.class)
	@ApiResponses({
		@ApiResponse(code = 200,response=FormDef.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listFormDef(@ApiIgnore @RequestParam Map<String,Object> params){
		try {
			User user=LoginUtils.getCurrentUserInfo();
			QueryWrapper<FormDef> qw = QueryTools.initQueryWrapper(FormDef.class , params);
			qw.eq("branch_id",user.getBranchId());
			IPage page=QueryTools.initPage(params);
			List<Map<String,Object>> datas = formDefService.selectListMapByWhere(page,qw,params);
			return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());
		}catch (BizException e) {
			return Result.error(e);
		}catch (Exception e) {
			return Result.error(e);
		}
	}
	

	@ApiOperation( value = "表单定义-新增",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=FormDef.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addFormDef(@RequestBody FormDef formDef) {
		 formDefService.addFormDefAndFields(formDef);
         return Result.ok("add-ok","添加成功！").setData(formDef);
	}

	@ApiOperation( value = "表单定义-删除",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delFormDef(@RequestBody FormDef formDef){
		Map<String,Object> p=new HashMap<>();
		p.put("formId", formDef.getId());
		FormDef formDefDb=formDefService.getFormCacheFirst(formDef.getId());
		if(formDefDb==null){
			return Result.error("data-0","表单不存在");
		}
		formDefService.checkFormDefQx(formDef.getId(),LoginUtils.getCurrentUserInfo());
		formDefService.removeById(formDef.getId());
        return Result.ok("del-ok","删除成功！");
	}

	@ApiOperation( value = "表单定义-修改",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=FormDef.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editFormDef(@RequestBody FormDef formDef) {
		formDefService.checkFormDefQx(formDef.getId(),LoginUtils.getCurrentUserInfo());
		formDefService.editFormDefAndFields(formDef);
        return Result.ok("edit-ok","修改成功！");
	}

    @ApiOperation( value = "表单定义-批量修改某些字段",notes="")
    @ApiEntityParams( value = FormDef.class, props={ }, remark = "表单定义", paramType = "body" )
	@ApiResponses({
			@ApiResponse(code = 200,response=FormDef.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields( @ApiIgnore @RequestBody Map<String,Object> params) {
        try{
            User user= LoginUtils.getCurrentUserInfo();
			formDefService.checkFormDefQx((String) params.get("id"),LoginUtils.getCurrentUserInfo());
            formDefService.editSomeFields(params);
            return Result.ok("edit-ok","更新成功");
        }catch (BizException e) {
            logger.error("",e);
            return Result.error(e);
        }catch (Exception e) {
            logger.error("",e);
            return Result.error(e);
        }
	}


	@ApiOperation( value = "表单定义-根据主键查询一条数据",notes=" ")
     @ApiResponses({
            @ApiResponse(code = 200,response=FormDef.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
    })
    @RequestMapping(value="/queryById",method=RequestMethod.GET)
    public Result queryById(FormDef formDef) {
        FormDef data = (FormDef) formDefService.getById(formDef);
        return Result.ok().setData(data);
    }

}
