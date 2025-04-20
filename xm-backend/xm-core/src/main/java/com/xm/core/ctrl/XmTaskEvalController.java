package com.xm.core.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.entity.Tips;
import com.mdp.core.query.QueryTools;
import com.mdp.core.utils.RequestUtils;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.sensitive.SensitiveWordService;
import com.mdp.swagger.ApiEntityParams;
import com.xm.core.entity.MyTotalEval;
import com.xm.core.entity.XmTaskEval;
import com.xm.core.service.XmTaskEvalService;
import com.xm.core.service.client.SysClient;
import io.swagger.annotations.*;
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
 * url编制采用rest风格,如对xm_task_eval xm_task_eval的操作有增删改查,对应的url分别为:<br>
 * 组织 com  顶级模块 xm 大模块 core 小模块 <br>
 * 实体 XmTaskEval 表 xm_task_eval 当前主键(包括多主键): id; 
 ***/
@RestController("xm.core.xmTaskEvalController")
@RequestMapping(value="/xm/core/xmTaskEval")
@Api(tags={"xm_task_eval操作接口"})
public class XmTaskEvalController {
	
	static Logger logger =LoggerFactory.getLogger(XmTaskEvalController.class);
	
	@Autowired
	private XmTaskEvalService xmTaskEvalService;

	@Autowired
	SysClient sysClient;

	@Autowired
	SensitiveWordService sensitiveWordService;
	 

	Map<String,Object> fieldsMap = toMap(new XmTaskEval());
 
	
	@ApiOperation( value = "查询xm_task_eval信息列表",notes=" ")
	@ApiEntityParams( XmTaskEval.class )
	@ApiImplicitParams({
			@ApiImplicitParam(name="pageSize",value="每页大小，默认20条",required=false),
			@ApiImplicitParam(name="pageNum",value="当前页码,从1开始",required=false),
			@ApiImplicitParam(name="total",value="总记录数,服务器端收到0时，会自动计算总记录数，如果上传>0的不自动计算",required=false),
			@ApiImplicitParam(name="count",value="是否计算总记录条数，如果count=true,则计算计算总条数，如果count=false 则不计算",required=false),
			@ApiImplicitParam(name="orderBy",value="排序列 如性别、学生编号排序 orderBy = sex desc,student desc",required=false),
 	})
	@ApiResponses({
		@ApiResponse(code = 200,response=XmTaskEval.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listXmTaskEval(@ApiIgnore @RequestParam Map<String,Object> params){
		
		
		RequestUtils.transformArray(params, "ids");		
		IPage page=QueryTools.initPage(params);
		QueryWrapper<XmTaskEval> qw = QueryTools.initQueryWrapper(XmTaskEval.class , params);
		List<Map<String,Object>> datas = xmTaskEvalService.selectListMapByWhere(page,qw,params);
			return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());	//列出XmTaskEval列表

	}


	@ApiOperation( value = "服务商中心交易评价汇总信息",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200,response= MyTotalEval.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/getServiceProviderEval",method=RequestMethod.GET)
	public Result getMyEval(){
		
		
		User user = LoginUtils.getCurrentUserInfo();
		MyTotalEval myTotalEval = xmTaskEvalService.getServiceProviderEval(user);	//列出XmTaskEval列表
		return Result.ok().setData(myTotalEval);
		
	}

	@ApiOperation( value = "个人中心交易评价汇总信息",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200,response= MyTotalEval.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/getPersonEval",method=RequestMethod.GET)
	public Result getPersonEval(){
		
		
		User user = LoginUtils.getCurrentUserInfo();
		MyTotalEval myTotalEval = xmTaskEvalService.getPersonEval(user);	//列出XmTaskEval列表
		return Result.ok().setData(myTotalEval);


	}
	@ApiOperation( value = "新增一条xm_task_eval信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmTaskEval.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addXmTaskEval(@RequestBody XmTaskEval xmTaskEval) {

		    boolean createPk=false;

			if(!StringUtils.hasText(xmTaskEval.getId())) {
			    createPk=true;
				xmTaskEval.setId(xmTaskEvalService.createKey("id"));
			}
			if(createPk==false){
                 if(xmTaskEvalService.selectOneObject(xmTaskEval) !=null ){
                    return Result.error("pk-exists","编号重复，请修改编号再提交");
                }
            }
			if(!StringUtils.hasText(xmTaskEval.getTaskId())){
				return Result.error("taskId-0","任务编号不能为空");
			}

			if(!StringUtils.hasText(xmTaskEval.getToUserid())){
				return Result.error("toUserid-0","被评价人编号不能为空");
			}
			Set<String> words=sensitiveWordService.getSensitiveWord(xmTaskEval.getRemark());
			if(words!=null && words.size()>0){
				return Result.error("remark-sensitive-word","评论存在敏感词"+words+"，请修改再提交");
			}
			User user = LoginUtils.getCurrentUserInfo();
			User toUser=sysClient.getUserByUserid(xmTaskEval.getToUserid());
			if(toUser==null){
				return Result.error("toUser-0","被评价人不存在");
			}
			xmTaskEval.setEvalTime(new Date());
			xmTaskEval.setEvalUserid(user.getUserid());
			xmTaskEval.setEvalUsername(user.getUsername());
			xmTaskEval.setEvalBranchId(user.getBranchId());
			xmTaskEval.setToUsername(toUser.getUsername());
			xmTaskEval.setToBranchId(toUser.getBranchId());
			xmTaskEvalService.insert(xmTaskEval);
		return Result.ok().setData(xmTaskEval);
	}

	@ApiOperation( value = "删除一条xm_task_eval信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delXmTaskEval(@RequestBody XmTaskEval xmTaskEval){

            if(!StringUtils.hasText(xmTaskEval.getId())) {
                 return Result.error("pk-not-exists","请上送主键参数id");
            }
            XmTaskEval xmTaskEvalDb = xmTaskEvalService.selectOneObject(xmTaskEval);
            if( xmTaskEvalDb == null ){
                return Result.error("data-not-exists","数据不存在，无法删除");
            }
			xmTaskEvalService.deleteByPk(xmTaskEval);
		return Result.ok();
		
	}

	@ApiOperation( value = "根据主键修改一条xm_task_eval信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmTaskEval.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editXmTaskEval(@RequestBody XmTaskEval xmTaskEval) {

            if(!StringUtils.hasText(xmTaskEval.getId())) {
                 return Result.error("pk-not-exists","请上送主键参数id");
            }
            XmTaskEval xmTaskEvalDb = xmTaskEvalService.selectOneObject(xmTaskEval);
            if( xmTaskEvalDb == null ){
                return Result.error("data-not-exists","数据不存在，无法修改");
            }
			xmTaskEvalService.updateSomeFieldByPk(xmTaskEval);
		return Result.ok();
	}

    @ApiOperation( value = "批量修改某些字段",notes="")
    @ApiEntityParams( value = XmTaskEval.class, props={ }, remark = "xm_task_eval", paramType = "body" )
	@ApiResponses({
			@ApiResponse(code = 200,response=XmTaskEval.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields( @ApiIgnore @RequestBody Map<String,Object> xmTaskEvalMap) {

            List<String> ids= (List<String>) xmTaskEvalMap.get("ids");
			if(ids==null || ids.size()==0){
				return Result.error("ids-0","ids不能为空");
			}

			Set<String> fields=new HashSet<>();
            fields.add("id");
			for (String fieldName : xmTaskEvalMap.keySet()) {
				if(fields.contains(fieldName)){
					return Result.error(fieldName+"-no-edit",fieldName+"不允许修改");
				}
			}
			Set<String> fieldKey=xmTaskEvalMap.keySet().stream().filter(i-> fieldsMap.containsKey(i)).collect(Collectors.toSet());
			fieldKey=fieldKey.stream().filter(i->xmTaskEvalMap.get(i)!=null).collect(Collectors.toSet());

			if(fieldKey.size()<=0) {
				return Result.error("fieldKey-0","没有需要更新的字段");
 			}
			XmTaskEval xmTaskEval = fromMap(xmTaskEvalMap,XmTaskEval.class);
			List<XmTaskEval> xmTaskEvalsDb=xmTaskEvalService.selectListByIds(ids);
			if(xmTaskEvalsDb==null ||xmTaskEvalsDb.size()==0){
				return Result.error("data-0","记录已不存在");
			}
			List<XmTaskEval> can=new ArrayList<>();
			List<XmTaskEval> no=new ArrayList<>();
			User user = LoginUtils.getCurrentUserInfo();
			for (XmTaskEval xmTaskEvalDb : xmTaskEvalsDb) {
 				Tips tips2 = new Tips("检查通过"); 
				if(!tips2.isOk()){
				    no.add(xmTaskEvalDb); 
				}else{
					can.add(xmTaskEvalDb);
				}
			}
			if(can.size()>0){
                xmTaskEvalMap.put("ids",can.stream().map(i->i.getId()).collect(Collectors.toList()));
			    xmTaskEvalService.editSomeFields(xmTaskEvalMap); 
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
		
	}

	@ApiOperation( value = "根据主键列表批量删除xm_task_eval信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelXmTaskEval(@RequestBody List<XmTaskEval> xmTaskEvals) {
		
        
        
            if(xmTaskEvals.size()<=0){
                return Result.error("data-0","请上送待删除数据列表");
            }
             List<XmTaskEval> datasDb=xmTaskEvalService.selectListByIds(xmTaskEvals.stream().map(i-> i.getId() ).collect(Collectors.toList()));

            List<XmTaskEval> can=new ArrayList<>();
            List<XmTaskEval> no=new ArrayList<>();
            for (XmTaskEval data : datasDb) {
                if(true){
                    can.add(data);
                }else{
                    no.add(data);
                } 
            }
            List<String> msgs=new ArrayList<>();
            if(can.size()>0){
                xmTaskEvalService.batchDelete(can);
                msgs.add(String.format("成功删除%s条数据.",can.size()));
            }
    
            if(no.size()>0){ 
                msgs.add(String.format("以下%s条数据不能删除.【%s】",no.size(),no.stream().map(i-> i.getId() ).collect(Collectors.joining(","))));
            }
            if(can.size()>0){
                 return Result.ok(msgs.stream().collect(Collectors.joining()));
            }else {
                return Result.error(msgs.stream().collect(Collectors.joining()));
            } 
        
	} 

}
