package com.xm.core.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.LangTips;
import com.mdp.core.entity.Result;
import com.mdp.core.query.QueryTools;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.swagger.ApiEntityParams;
import com.xm.core.entity.XmProjectEnvList;
import com.xm.core.service.XmProjectEnvListService;
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
 * @since 2023-10-3
 */
@RestController
@RequestMapping(value="/xm/core/xmProjectEnvList")
@Api(tags={"项目环境清单-操作接口"})
public class XmProjectEnvListController {
	
	static Logger logger =LoggerFactory.getLogger(XmProjectEnvListController.class);
	
	@Autowired
	private XmProjectEnvListService xmProjectEnvListService;

	@ApiOperation( value = "项目环境清单-查询列表",notes=" ")
	@ApiEntityParams(XmProjectEnvList.class)
	@ApiResponses({
		@ApiResponse(code = 200,response=XmProjectEnvList.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listXmProjectEnvList(@ApiIgnore @RequestParam Map<String,Object> params){
			User user=LoginUtils.getCurrentUserInfo();
			QueryWrapper<XmProjectEnvList> qw = QueryTools.initQueryWrapper(XmProjectEnvList.class , params);
			IPage page=QueryTools.initPage(params);
			List<Map<String,Object>> datas = xmProjectEnvListService.selectListMapByWhere(page,qw,params);
			return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());
	}
	

	@ApiOperation( value = "项目环境清单-新增",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmProjectEnvList.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addXmProjectEnvList(@RequestBody XmProjectEnvList xmProjectEnvList) {
		 xmProjectEnvListService.save(xmProjectEnvList);
         return Result.ok("add-ok","添加成功！");
	}

	@ApiOperation( value = "项目环境清单-删除",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delXmProjectEnvList(@RequestBody XmProjectEnvList xmProjectEnvList){
		xmProjectEnvListService.removeById(xmProjectEnvList);
        return Result.ok("del-ok","删除成功！");
	}

	@ApiOperation( value = "项目环境清单-修改",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmProjectEnvList.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editXmProjectEnvList(@RequestBody XmProjectEnvList xmProjectEnvList) {
		xmProjectEnvListService.updateById(xmProjectEnvList);
        return Result.ok("edit-ok","修改成功！");
	}

    @ApiOperation( value = "项目环境清单-批量修改某些字段",notes="")
    @ApiEntityParams( value = XmProjectEnvList.class, props={ }, remark = "项目环境清单", paramType = "body" )
	@ApiResponses({
			@ApiResponse(code = 200,response=XmProjectEnvList.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields( @ApiIgnore @RequestBody Map<String,Object> params) {
            User user= LoginUtils.getCurrentUserInfo();
            xmProjectEnvListService.editSomeFields(params);
            return Result.ok("edit-ok","更新成功");
	}

	@ApiOperation( value = "项目环境清单-批量删除",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelXmProjectEnvList(@RequestBody List<XmProjectEnvList> xmProjectEnvLists) {
	    User user= LoginUtils.getCurrentUserInfo();
        if(xmProjectEnvLists.size()<=0){
            return Result.error("batchDel-data-err-0","请上送待删除数据列表");
        }
         List<XmProjectEnvList> datasDb=xmProjectEnvListService.listByIds(xmProjectEnvLists.stream().map(i-> i.getId() ).collect(Collectors.toList()));

        List<XmProjectEnvList> can=new ArrayList<>();
        List<XmProjectEnvList> no=new ArrayList<>();
        for (XmProjectEnvList data : datasDb) {
            if(true){
                can.add(data);
            }else{
                no.add(data);
            }
        }
        List<String> msgs=new ArrayList<>();
        if(can.size()>0){
            xmProjectEnvListService.removeByIds(can);
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

	@ApiOperation( value = "项目环境清单-根据主键查询一条数据",notes=" ")
     @ApiResponses({
            @ApiResponse(code = 200,response=XmProjectEnvList.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
    })
    @RequestMapping(value="/queryById",method=RequestMethod.GET)
    public Result queryById(XmProjectEnvList xmProjectEnvList) {
        XmProjectEnvList data = (XmProjectEnvList) xmProjectEnvListService.getById(xmProjectEnvList);
        return Result.ok().setData(data);
    }

}
