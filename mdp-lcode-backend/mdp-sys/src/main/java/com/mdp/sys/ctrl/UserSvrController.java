package com.mdp.sys.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.err.BizException;
import com.mdp.core.query.QueryTools;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.swagger.ApiEntityParams;
import com.mdp.sys.entity.UserSvr;
import com.mdp.sys.service.UserService;
import com.mdp.sys.service.UserSvrService;
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
 * url编制采用rest风格,如对sys_branch_svr 个人服务列表的操作有增删改查,对应的url分别为:<br>
 * 组织 com  顶级模块 mdp 大模块 sys 小模块 <br>
 * 实体 UserSvr 表 sys_branch_svr 当前主键(包括多主键): id; 
 ***/
@RestController("mdp.sys.userSvrController")
@RequestMapping(value="/*/sys/userSvr")
@Api(tags={"个人服务列表操作接口"})
public class UserSvrController {

	static Logger logger =LoggerFactory.getLogger(UserSvrController.class);

	@Autowired
	private UserSvrService userSvrService;


	Map<String,Object> fieldsMap = toMap(new UserSvr());


	@ApiOperation( value = "查询个人服务列表信息列表",notes=" ")
	@ApiEntityParams( UserSvr.class )
	@ApiImplicitParams({
			@ApiImplicitParam(name="pageSize",value="每页大小，默认20条",required=false),
			@ApiImplicitParam(name="pageNum",value="当前页码,从1开始",required=false),
			@ApiImplicitParam(name="total",value="总记录数,服务器端收到0时，会自动计算总记录数，如果上传>0的不自动计算",required=false),
			@ApiImplicitParam(name="count",value="是否计算总记录条数，如果count=true,则计算计算总条数，如果count=false 则不计算",required=false),
			@ApiImplicitParam(name="orderBy",value="排序列 如性别、学生编号排序 orderBy = sex desc,student desc",required=false),
	})
	@ApiResponses({
			@ApiResponse(code = 200,response=UserSvr.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listUserSvr( @ApiIgnore @RequestParam Map<String,Object> params){
		IPage page=QueryTools.initPage(params);
		QueryWrapper<UserSvr> qw= QueryTools.initQueryWrapper(UserSvr.class,params);
		List<Map<String,Object>>	userSvrList = userSvrService.selectListMapByWhere(page,qw,params);
		return Result.ok().setData(userSvrList).setTotal(page.getTotal());

	}

	@ApiOperation( value = "查询个人服务列表信息列表",notes=" ")
	@ApiEntityParams( UserSvr.class )
	@ApiResponses({
			@ApiResponse(code = 200,response=UserSvr.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/mySvrs",method=RequestMethod.GET)
	public Result mySvrs( @ApiIgnore @RequestParam Map<String,Object> params){
		User user=LoginUtils.getCurrentUserInfo();
		params.put("userid",user.getUserid());
		IPage page=QueryTools.initPage(params);
		QueryWrapper<UserSvr> qw= QueryTools.initQueryWrapper(UserSvr.class,params);
		List<Map<String,Object>>	userSvrList = userSvrService.selectListMapByWhere(page,qw,params);
		return Result.ok().setData(userSvrList).setTotal(page.getTotal());
	}


	@ApiOperation( value = "查询服务明细，包含用户信息，服务信息一起返回",notes=" ")
	@ApiImplicitParams({
			@ApiImplicitParam(name="id",value="服务编号",required=false),
	})
	@ApiResponses({
			@ApiResponse(code = 200,response=UserSvr.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/detail",method=RequestMethod.GET)
	public Result detail( @ApiIgnore @RequestParam Map<String,Object> userSvr){
		
		
		if(!StringUtils.hasText((String) userSvr.get("id"))){
			return Result.error("id-0","服务编号必填");
		}
 		Map<String,Object> data= this.userSvrService.detail((String)userSvr.get("id"));
		if(data!=null){
			data.put("idCardNo",null);
			data.put("password",null);
			String phoneno= (String) data.get("phoneno");
			data.put("phoneno", UserService.phoneEncode(phoneno));
		}
		return Result.ok().setData(data);
	}


	@ApiOperation( value = "新增一条个人服务列表信息",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200,response=UserSvr.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addUserSvr(@RequestBody UserSvr userSvr) {
		
		
		try{
			boolean createPk=false;
			if(!StringUtils.hasText(userSvr.getId())) {
				createPk=true;
				userSvr.setId(userSvrService.createKey("id"));
			}
			if(createPk==false){
				if(userSvrService.selectOneObject(userSvr) !=null ){
					return Result.error("pk-exists","编号重复，请修改编号再提交");
				}
			}
			User user=LoginUtils.getCurrentUserInfo();
			userSvr.setUserid(user.getUserid());
			userSvrService.insert(userSvr);
			return Result.ok().setData(userSvr);
		}catch (BizException e) {
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}
	}

	@ApiOperation( value = "删除一条个人服务列表信息",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	})
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delUserSvr(@RequestBody UserSvr userSvr){
		
		
		try{
			if(!StringUtils.hasText(userSvr.getId())) {
				return Result.error("pk-not-exists","请上送主键参数id");
			}
			UserSvr userSvrDb = userSvrService.selectOneObject(userSvr);
			if( userSvrDb == null ){
				return Result.error("data-not-exists","数据不存在，无法删除");
			}
			User user=LoginUtils.getCurrentUserInfo();
			if(!user.getUserid().equals(userSvrDb.getUserid())){
				return Result.error("userid-0","无权删除");
			}


			userSvrService.deleteByPk(userSvr);
			return Result.ok();
		}catch (BizException e) {
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}
	}

	@ApiOperation( value = "根据主键修改一条个人服务列表信息",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200,response=UserSvr.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editUserSvr(@RequestBody UserSvr userSvr) {
		
		
		try{
			if(!StringUtils.hasText(userSvr.getId())) {
				return Result.error("pk-not-exists","请上送主键参数id");
			}

			User user = LoginUtils.getCurrentUserInfo();
			UserSvr userSvrDb = userSvrService.selectOneObject(userSvr);

			if( userSvrDb == null ){
				return Result.error("data-not-exists","数据不存在，无法修改");
			}
			if(!user.getUserid().equals(userSvrDb.getUserid())){
				return Result.error("userid-0","您无权修改该数据");
			}
			userSvrService.updateSomeFieldByPk(userSvr);
			return Result.ok().setData(userSvr);
		}catch (BizException e) {
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}
	}

	@ApiOperation( value = "批量修改某些字段",notes="")
	@ApiEntityParams( value = UserSvr.class, props={ }, remark = "个人服务列表", paramType = "body" )
	@ApiResponses({
			@ApiResponse(code = 200,response=UserSvr.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields( @ApiIgnore @RequestBody Map<String,Object> userSvrMap) {
		
		
		try{
			List<String> ids= (List<String>) userSvrMap.get("$pks");
			if(ids==null || ids.size()==0){
				return Result.error("ids-0","ids不能为空");
			}

			Set<String> fields=new HashSet<>();
			fields.add("id");
			for (String fieldName : userSvrMap.keySet()) {
				if(fields.contains(fieldName)){
					return Result.error(fieldName+"-no-edit",fieldName+"不允许修改");
				}
			}
			Set<String> fieldKey=userSvrMap.keySet().stream().filter(i-> fieldsMap.containsKey(i)).collect(Collectors.toSet());
			fieldKey=fieldKey.stream().filter(i->!StringUtils.isEmpty(userSvrMap.get(i) )).collect(Collectors.toSet());

			if(fieldKey.size()<=0) {
				return Result.error("fieldKey-0","没有需要更新的字段");
			}
			UserSvr userSvr = fromMap(userSvrMap,UserSvr.class);
			List<UserSvr> userSvrsDb=userSvrService.selectListByIds(ids);
			if(userSvrsDb==null ||userSvrsDb.size()==0){
				return Result.error("data-0","记录已不存在");
			}
			List<UserSvr> can=new ArrayList<>();
			List<UserSvr> no=new ArrayList<>();
			User user = LoginUtils.getCurrentUserInfo();
			for (UserSvr userSvrDb : userSvrsDb) {
				if(!user.getUserid().equals(userSvrDb.getUserid())){
					no.add(userSvrDb);
				}else{
					can.add(userSvrDb);
				}
			}
			if(can.size()>0){
				userSvrMap.put("$pks",can.stream().map(i->i.getId()).collect(Collectors.toList()));
				userSvrService.editSomeFields(userSvrMap);
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

	@ApiOperation( value = "根据主键列表批量删除个人服务列表信息",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	})
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelUserSvr(@RequestBody List<UserSvr> userSvrs) {
		
		
		try{
			if(userSvrs.size()<=0){
				return Result.error("data-0","请上送待删除数据列表");
			}
			List<UserSvr> datasDb=userSvrService.selectListByIds(userSvrs.stream().map(i-> i.getId() ).collect(Collectors.toList()));

			List<UserSvr> can=new ArrayList<>();
			List<UserSvr> no=new ArrayList<>();
			User user = LoginUtils.getCurrentUserInfo();
			for (UserSvr data : datasDb) {
				if(user.getUserid().equals(user.getUserid())){
					can.add(data);
				}else{
					no.add(data);
				}
			}
			List<String> msgs=new ArrayList<>();
			if(can.size()>0){
				userSvrService.batchDelete(can);
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
		}catch (BizException e) {
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}
	}

}
