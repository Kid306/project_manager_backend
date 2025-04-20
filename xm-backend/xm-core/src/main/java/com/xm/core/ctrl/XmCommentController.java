package com.xm.core.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.query.QueryTools;
import com.mdp.core.utils.RequestUtils;
import com.mdp.msg.client.PushNotifyMsgService;
import com.mdp.qx.HasRole;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.swagger.ApiEntityParams;
import com.xm.core.entity.XmComment;
import com.xm.core.entity.XmMenu;
import com.xm.core.service.XmCommentService;
import com.xm.core.service.XmMenuCalcService;
import com.xm.core.service.XmMenuService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.mdp.core.utils.BaseUtils.toMap;

/**
 * url编制采用rest风格,如对xm_comment 档案评论表的操作有增删改查,对应的url分别为:<br>
 * 组织 com  顶级模块 xm 大模块 core 小模块 <br>
 * 实体 XmComment 表 xm_comment 当前主键(包括多主键): id; 
 ***/
@RestController("xm.core.xmCommentController")
@RequestMapping(value="/xm/core/xmComment")
@Api(tags={"档案评论表操作接口"})
public class XmCommentController {
	
	static Logger logger =LoggerFactory.getLogger(XmCommentController.class);
	
	@Autowired
	private XmCommentService xmCommentService;
	
	@Autowired
	XmMenuService xmMenuService;

	@Autowired
	PushNotifyMsgService notifyMsgService;
	 

	Map<String,Object> fieldsMap = toMap(new XmComment());
 
	
	@ApiOperation( value = "查询档案评论表信息列表",notes=" ")
	@ApiEntityParams( XmComment.class )
	@ApiImplicitParams({
			@ApiImplicitParam(name="pageSize",value="每页大小，默认20条",required=false),
			@ApiImplicitParam(name="pageNum",value="当前页码,从1开始",required=false),
			@ApiImplicitParam(name="total",value="总记录数,服务器端收到0时，会自动计算总记录数，如果上传>0的不自动计算",required=false),
			@ApiImplicitParam(name="count",value="是否计算总记录条数，如果count=true,则计算计算总条数，如果count=false 则不计算",required=false),
			@ApiImplicitParam(name="orderBy",value="排序列 如性别、学生编号排序 orderBy = sex desc,student desc",required=false),
 	})
	@ApiResponses({
		@ApiResponse(code = 200,response=XmComment.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listXmComment(@ApiIgnore @RequestParam Map<String,Object> params){

		IPage page=QueryTools.initPage(params);
		String pid= (String) params.get("pid");
		if(!StringUtils.hasText(pid)){
			params.put("pidIsNull","1");
		}

		QueryWrapper<XmComment> qw = QueryTools.initQueryWrapper(XmComment.class , params);
		List<Map<String,Object>> datas = xmCommentService.selectListMapByWhere(page,qw,params);


		if(datas.size()>0) {
			//List<XmComment> children=xmCommentService.selectListByPids(datas.stream().map(k->(String)k.get("id")).collect(Collectors.toList()));

			//return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal()).put("children",children);	//列出XmComment列表
		}

		return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());	//列出XmComment列表
	}


	@ApiOperation( value = "新增一条档案评论表信息",notes="addXmComment,主键如果为空，后台自动生成")
	@ApiResponses({
			@ApiResponse(code = 200,response=XmComment.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@HasRole
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addXmComment(@RequestBody XmComment xmComment) {

			User user=LoginUtils.getCurrentUserInfo();

			xmComment.setId(xmCommentService.createKey("id"));
			xmComment.setBranchId(user.getBranchId());
			xmComment.setUserid(user.getUserid());
			xmComment.setUsername(user.getUsername());
			xmComment.setCdate(new Date());
			xmComment.setIp(RequestUtils.getIpAddr(RequestUtils.getRequest()));
			xmCommentService.insert(xmComment);
			if(StringUtils.hasText(xmComment.getPid())){
				xmCommentService.updateChildrenSum(xmComment.getPid(),Integer.valueOf(1));
			}
			return Result.ok().setData(xmComment);
		
	}


	@ApiOperation( value = "删除一条档案评论表信息",notes="delXmComment,仅需要上传主键字段")
	@ApiResponses({
			@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	})
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delXmComment(@RequestBody XmComment xmComment){

			XmComment commentDb=this.xmCommentService.selectOneById(xmComment.getId());
			if(commentDb==null){
				return Result.error("data-0","评论已不存在");
			}
			User user=LoginUtils.getCurrentUserInfo();
			if(!LoginUtils.isSuperAdmin()){
				if(!LoginUtils.isBranchAdmin(commentDb.getBranchId())){
					if(!user.getUserid().equals(commentDb.getUserid())){
						return Result.error("no-qx-0","无权限删除评论");
					}
				}
			}
			xmCommentService.deleteByPk(xmComment);
			if(StringUtils.hasText(commentDb.getPid())){
				xmCommentService.updateChildrenSum(commentDb.getPid(),Integer.valueOf(-1));
			} 
			return Result.ok();
		
	}


	@ApiOperation( value = "点赞评论",notes="praiseComment")
	@ApiResponses({
			@ApiResponse(code = 200,response=XmComment.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/praise",method=RequestMethod.POST)
	public Result praiseComment(@RequestBody XmComment xmComment) {

			xmCommentService.praiseComment(xmComment);
			return Result.ok();
		
	}

	@ApiOperation( value = "屏蔽评论",notes="unShowComment")
	@ApiResponses({
			@ApiResponse(code = 200,response=XmComment.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/unshow",method=RequestMethod.POST)
	public Result unShowComment(@RequestBody String[] ids) {

		User user=LoginUtils.getCurrentUserInfo();
		List<XmComment> comments=this.xmCommentService.selectListByIds(Arrays.asList(ids));
		if(comments==null || comments.size()==0){
			return Result.error("data-0","评论已不存在");
		}
		boolean isSuperAdmin=LoginUtils.isSuperAdmin();
		for (XmComment comment : comments) {
			if(!isSuperAdmin){
				if(!LoginUtils.isBranchAdmin(comment.getBranchId())){
					if(!user.getUserid().equals(comment.getUserid())){
						return Result.error("无权限修改","无权限屏蔽评论【"+comment.getContext()+"】");
					}
				}
			}
		}
		xmCommentService.unShowComment(ids);
		return Result.ok();
		
	}

	@ApiOperation( value = "打开评论",notes="showComment")
	@ApiResponses({
			@ApiResponse(code = 200,response=XmComment.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/show",method=RequestMethod.POST)
	public Result showComment(@RequestBody String[] ids) {

		User user= LoginUtils.getCurrentUserInfo();
		List<XmComment> comments=this.xmCommentService.selectListByIds(Arrays.asList(ids));
		if(comments==null || comments.size()==0){
			return Result.error("data-0","评论已不存在");
		}
		boolean isSuperAdmin=LoginUtils.isSuperAdmin();
		for (XmComment comment : comments) {
			if(!isSuperAdmin){
				if(!LoginUtils.isBranchAdmin(comment.getBranchId())){
					if(!user.getUserid().equals(comment.getUserid())){
						return Result.error("无权限修改","无权限打开此评论【"+comment.getContext()+"】");
					}
				}
			}
		}
		xmCommentService.showComment(ids);
		return Result.ok();
		
	}
	

}
