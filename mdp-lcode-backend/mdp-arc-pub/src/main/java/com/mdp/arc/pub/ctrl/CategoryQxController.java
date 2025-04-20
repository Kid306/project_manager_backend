package com.mdp.arc.pub.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.arc.pub.entity.CategoryQx;
import com.mdp.arc.pub.service.CategoryQxService;
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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
/**
 * @author maimeng-mdp code-gen
 * @since 2023-9-15
 */
@RestController
@RequestMapping(value="/mdp/arc/pub/categoryQx")
@Api(tags={"表单权限-操作接口"})
public class CategoryQxController {
	
	static Logger logger =LoggerFactory.getLogger(CategoryQxController.class);
	
	@Autowired
	private CategoryQxService categoryQxService;

	@ApiOperation( value = "表单权限-查询列表",notes=" ")
	@ApiEntityParams(CategoryQx.class)
	@ApiResponses({
		@ApiResponse(code = 200,response=CategoryQx.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listCategoryQx(@ApiIgnore @RequestParam Map<String,Object> params){
		try {
			User user=LoginUtils.getCurrentUserInfo();
			QueryWrapper<CategoryQx> qw = QueryTools.initQueryWrapper(CategoryQx.class , params);
			IPage page=QueryTools.initPage(params);
			List<Map<String,Object>> datas = categoryQxService.selectListMapByWhere(page,qw,params);
			return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());
		}catch (BizException e) {
			return Result.error(e);
		}catch (Exception e) {
			return Result.error(e);
		}
	}
	

	@ApiOperation( value = "表单权限-新增",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=CategoryQx.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addCategoryQx(@RequestBody CategoryQx categoryQx) {
		 categoryQxService.save(categoryQx);
         return Result.ok("add-ok","添加成功！");
	}

	@ApiOperation( value = "表单权限-删除",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delCategoryQx(@RequestBody CategoryQx categoryQx){
		categoryQxService.removeById(categoryQx);
        return Result.ok("del-ok","删除成功！");
	}

	@ApiOperation( value = "表单权限-修改",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=CategoryQx.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editCategoryQx(@RequestBody CategoryQx categoryQx) {
		categoryQxService.updateById(categoryQx);
        return Result.ok("edit-ok","修改成功！");
	}

    @ApiOperation( value = "表单权限-批量修改某些字段",notes="")
    @ApiEntityParams( value = CategoryQx.class, props={ }, remark = "表单权限", paramType = "body" )
	@ApiResponses({
			@ApiResponse(code = 200,response=CategoryQx.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields( @ApiIgnore @RequestBody Map<String,Object> params) {
        try{
            User user= LoginUtils.getCurrentUserInfo();
            categoryQxService.editSomeFields(params);
            return Result.ok("edit-ok","更新成功");
        }catch (BizException e) {
            logger.error("",e);
            return Result.error(e);
        }catch (Exception e) {
            logger.error("",e);
            return Result.error(e);
        }
	}

	@ApiOperation( value = "表单权限-批量删除",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelCategoryQx(@RequestBody List<CategoryQx> categoryQxs) {
	    User user= LoginUtils.getCurrentUserInfo();
        try{ 
            if(categoryQxs.size()<=0){
                return Result.error("categoryQx-batchDel-data-err-0","请上送待删除数据列表");
            }
             List<CategoryQx> datasDb=categoryQxService.listByIds(categoryQxs.stream().map(i-> i.getCateId() ).collect(Collectors.toList()));

            List<CategoryQx> can=new ArrayList<>();
            List<CategoryQx> no=new ArrayList<>();
            for (CategoryQx data : datasDb) {
                if(true){
                    can.add(data);
                }else{
                    no.add(data);
                } 
            }
            List<String> msgs=new ArrayList<>();
            if(can.size()>0){
                categoryQxService.removeByIds(can);
                msgs.add(LangTips.transMsg("del-ok-num","成功删除%s条数据.",can.size()));
            }
    
            if(no.size()>0){ 
                msgs.add(LangTips.transMsg("not-allow-del-num","以下%s条数据不能删除:【%s】",no.size(),no.stream().map(i-> i.getCateId() ).collect(Collectors.joining(","))));
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

	@ApiOperation( value = "表单权限-根据主键查询一条数据",notes=" ")
     @ApiResponses({
            @ApiResponse(code = 200,response=CategoryQx.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
    })
    @RequestMapping(value="/queryById",method=RequestMethod.GET)
    public Result queryById(CategoryQx categoryQx) {
        CategoryQx data = (CategoryQx) categoryQxService.getById(categoryQx);
        return Result.ok().setData(data);
    }

}
