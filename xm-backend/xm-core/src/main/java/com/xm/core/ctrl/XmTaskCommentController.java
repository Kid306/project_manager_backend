package com.xm.core.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.LangTips;
import com.mdp.core.entity.Result;
import com.mdp.core.query.QueryTools;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.swagger.ApiEntityParams;
import com.xm.core.entity.XmTaskComment;
import com.xm.core.service.XmTaskCommentService;
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
@RequestMapping(value="/xm/core/xmTaskComment")
@Api(tags={"档案评论表-操作接口"})
public class XmTaskCommentController {
	
	static Logger logger =LoggerFactory.getLogger(XmTaskCommentController.class);
	
	@Autowired
	private XmTaskCommentService xmTaskCommentService;

	@ApiOperation( value = "档案评论表-查询列表",notes=" ")
	@ApiEntityParams(XmTaskComment.class)
	@ApiResponses({
		@ApiResponse(code = 200,response=XmTaskComment.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listXmTaskComment(@ApiIgnore @RequestParam Map<String,Object> params){
			User user=LoginUtils.getCurrentUserInfo();
			QueryWrapper<XmTaskComment> qw = QueryTools.initQueryWrapper(XmTaskComment.class , params);
			IPage page=QueryTools.initPage(params);
			List<Map<String,Object>> datas = xmTaskCommentService.selectListMapByWhere(page,qw,params);
			return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());
	}
	

	@ApiOperation( value = "档案评论表-新增",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmTaskComment.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addXmTaskComment(@RequestBody XmTaskComment xmTaskComment) {
		 xmTaskCommentService.save(xmTaskComment);
         return Result.ok("add-ok","添加成功！");
	}

	@ApiOperation( value = "档案评论表-删除",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delXmTaskComment(@RequestBody XmTaskComment xmTaskComment){
		xmTaskCommentService.removeById(xmTaskComment);
        return Result.ok("del-ok","删除成功！");
	}

	@ApiOperation( value = "档案评论表-修改",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmTaskComment.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editXmTaskComment(@RequestBody XmTaskComment xmTaskComment) {
		xmTaskCommentService.updateById(xmTaskComment);
        return Result.ok("edit-ok","修改成功！");
	}

    @ApiOperation( value = "档案评论表-批量修改某些字段",notes="")
    @ApiEntityParams( value = XmTaskComment.class, props={ }, remark = "档案评论表", paramType = "body" )
	@ApiResponses({
			@ApiResponse(code = 200,response=XmTaskComment.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields( @ApiIgnore @RequestBody Map<String,Object> params) {
            User user= LoginUtils.getCurrentUserInfo();
            xmTaskCommentService.editSomeFields(params);
            return Result.ok("edit-ok","更新成功");
	}

	@ApiOperation( value = "档案评论表-批量删除",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelXmTaskComment(@RequestBody List<XmTaskComment> xmTaskComments) {
	    User user= LoginUtils.getCurrentUserInfo();
        if(xmTaskComments.size()<=0){
            return Result.error("batchDel-data-err-0","请上送待删除数据列表");
        }
         List<XmTaskComment> datasDb=xmTaskCommentService.listByIds(xmTaskComments.stream().map(i-> i.getId() ).collect(Collectors.toList()));

        List<XmTaskComment> can=new ArrayList<>();
        List<XmTaskComment> no=new ArrayList<>();
        for (XmTaskComment data : datasDb) {
            if(true){
                can.add(data);
            }else{
                no.add(data);
            }
        }
        List<String> msgs=new ArrayList<>();
        if(can.size()>0){
            xmTaskCommentService.removeByIds(can);
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

	@ApiOperation( value = "档案评论表-根据主键查询一条数据",notes=" ")
     @ApiResponses({
            @ApiResponse(code = 200,response=XmTaskComment.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
    })
    @RequestMapping(value="/queryById",method=RequestMethod.GET)
    public Result queryById(XmTaskComment xmTaskComment) {
        XmTaskComment data = (XmTaskComment) xmTaskCommentService.getById(xmTaskComment);
        return Result.ok().setData(data);
    }

}
