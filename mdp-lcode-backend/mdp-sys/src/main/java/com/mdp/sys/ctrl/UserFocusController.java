package com.mdp.sys.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.err.BizException;
import com.mdp.core.query.QueryTools;
import com.mdp.qx.HasRole;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.swagger.ApiEntityParams;
import com.mdp.sys.entity.UserFocus;
import com.mdp.sys.service.UserFocusService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

import static com.mdp.core.utils.BaseUtils.toMap;


/**
 * url编制采用rest风格,如对sys_user_focus 我关注的项目或者任务的操作有增删改查,对应的url分别为:<br>
 * 组织 com  顶级模块 mdp 大模块 sys 小模块 <br>
 * 实体 UserFocus 表 sys_user_focus 当前主键(包括多主键): userid,biz_id,pbiz_id; 
 ***/
@RestController("mdp.sys.userFocusController")
@RequestMapping(value="/*/sys/userFocus")
@Api(tags={"我关注的项目或者任务操作接口"})
public class UserFocusController {
	
	static Logger logger =LoggerFactory.getLogger(UserFocusController.class);
	
	@Autowired
	private UserFocusService userFocusService;
	 

	Map<String,Object> fieldsMap = toMap(new UserFocus());
 
	
	@ApiOperation( value = "查询我关注的项目或者任务信息列表",notes=" ")
	@ApiEntityParams( UserFocus.class )
	@ApiImplicitParams({
			@ApiImplicitParam(name="pageSize",value="每页大小，默认20条",required=false),
			@ApiImplicitParam(name="pageNum",value="当前页码,从1开始",required=false),
			@ApiImplicitParam(name="total",value="总记录数,服务器端收到0时，会自动计算总记录数，如果上传>0的不自动计算",required=false),
			@ApiImplicitParam(name="count",value="是否计算总记录条数，如果count=true,则计算计算总条数，如果count=false 则不计算",required=false),
			@ApiImplicitParam(name="orderBy",value="排序列 如性别、学生编号排序 orderBy = sex desc,student desc",required=false),
 	})
	@ApiResponses({
		@ApiResponse(code = 200,response=UserFocus.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listUserFocus( @ApiIgnore @RequestParam Map<String,Object>  params){
		
		
		
		IPage page=QueryTools.initPage(params);
 		QueryWrapper<UserFocus> qw= QueryTools.initQueryWrapper(UserFocus.class,params);

		List<Map<String,Object>>	userFocusList = userFocusService.selectListMapByWhere(page,qw,params);
		
		return Result.ok().setData(userFocusList).setTotal(page.getTotal());

	}


	@ApiOperation( value = "查询我关注的项目或者任务信息列表",notes=" ")
	@ApiEntityParams( UserFocus.class )
	@ApiImplicitParams({
			@ApiImplicitParam(name="pageSize",value="每页大小，默认20条",required=false),
			@ApiImplicitParam(name="pageNum",value="当前页码,从1开始",required=false),
			@ApiImplicitParam(name="total",value="总记录数,服务器端收到0时，会自动计算总记录数，如果上传>0的不自动计算",required=false),
			@ApiImplicitParam(name="count",value="是否计算总记录条数，如果count=true,则计算计算总条数，如果count=false 则不计算",required=false),
			@ApiImplicitParam(name="orderBy",value="排序列 如性别、学生编号排序 orderBy = sex desc,student desc",required=false),
	})
	@ApiResponses({
			@ApiResponse(code = 200,response=UserFocus.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@HasRole
	@RequestMapping(value="/myFocusForIndex",method=RequestMethod.GET)
	public Result myFocusForIndex( ){
		
		
		List<Map<String,Object>>	datas = userFocusService.myFocusForIndex(LoginUtils.getCurrentUserInfo().getUserid());
		return Result.ok().setData(datas).setTotal(datas!=null?datas.size():0);
	}

	@ApiOperation( value = "新增一条我关注的项目或者任务信息",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200,response=UserFocus.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addUserFocus(@RequestBody UserFocus userFocus) {
		
		
		try{
			User user = LoginUtils.getCurrentUserInfo();
			if(!StringUtils.hasText(userFocus.getBizId())) {
				return Result.error("bizId","业务编号不能为空");
			}
			if(!StringUtils.hasText(userFocus.getPbizId())) {
				return Result.error("pbizId","上级编号不能为空");
			}
			if(!StringUtils.hasText(userFocus.getFocusType())) {
				return Result.error("focusType","关注类型不能为空");
			}
			userFocus.setUserid(user.getUserid());
			userFocus.setUsername(user.getUsername());
			userFocus.setUbranchId(user.getBranchId());
			if(userFocusService.selectOneObject(userFocus) !=null ){
				return Result.error("pk-exists","已关注");
			}
			userFocusService.focus(userFocus);
			return Result.ok().setData(userFocus);
		}catch (BizException e) {
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}
	}

	@ApiOperation( value = "删除一条我关注的项目或者任务信息",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	})
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delUserFocus(@RequestBody UserFocus userFocus){
		
		
		try{
			User user = LoginUtils.getCurrentUserInfo();
			if(!StringUtils.hasText(userFocus.getBizId())) {
				return Result.error("pk-not-exists","请上送主键参数bizId");
			}
			if(!StringUtils.hasText(userFocus.getPbizId())) {
				return Result.error("pk-not-exists","请上送主键参数pbizId");
			}
			userFocus.setUserid(user.getUserid());
			UserFocus userFocusDb = userFocusService.selectOneObject(userFocus);
			if( userFocusDb == null ){
				return Result.error("data-not-exists","数据不存在，无法删除");
			}
			userFocusService.unfocus(userFocusDb);
			return Result.ok();
		}catch (BizException e) {
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}
	}
	
	/**
	@ApiOperation( value = "根据主键修改一条我关注的项目或者任务信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=UserFocus.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editUserFocus(@RequestBody UserFocus userFocus) {
		
		
		try{
            if(!StringUtils.hasText(userFocus.getUserid())) {
                 return Result.error("pk-not-exists","请上送主键参数userid");
            }
            if(!StringUtils.hasText(userFocus.getBizId())) {
                 return Result.error("pk-not-exists","请上送主键参数bizId");
            }
            if(!StringUtils.hasText(userFocus.getPbizId())) {
                 return Result.error("pk-not-exists","请上送主键参数pbizId");
            }
            UserFocus userFocusDb = userFocusService.selectOneObject(userFocus);
            if( userFocusDb == null ){
                return Result.error("data-not-exists","数据不存在，无法修改");
            }
			userFocusService.updateSomeFieldByPk(userFocus);
			return Result.ok().setData(userFocus);
		}catch (BizException e) { 
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}  
	}
	*/

	/**
    @ApiOperation( value = "批量修改某些字段",notes="")
    @ApiEntityParams( value = UserFocus.class, props={ }, remark = "我关注的项目或者任务", paramType = "body" )
	@ApiResponses({
			@ApiResponse(code = 200,response=UserFocus.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields( @ApiIgnore @RequestBody Map<String,Object> userFocusMap) {
		
		
		try{
			List<Map<String,Object>> pkList= (List<Map<String,Object>>) userFocusMap.get("$pks");
			if(pkList==null || pkList.size()==0){
				return Result.error("$pks-0","$pks不能为空");
			}

			Set<String> fields=new HashSet<>();
            fields.add("userid");
            fields.add("bizId");
            fields.add("pbizId");
			for (String fieldName : userFocusMap.keySet()) {
				if(fields.contains(fieldName)){
					return Result.error(fieldName+"-no-edit",fieldName+"不允许修改");
				}
			}
			Set<String> fieldKey=userFocusMap.keySet().stream().filter(i-> fieldsMap.containsKey(i)).collect(Collectors.toSet());
			fieldKey=fieldKey.stream().filter(i->!StringUtils.isEmpty(userFocusMap.get(i) )).collect(Collectors.toSet());

			if(fieldKey.size()<=0) {
				return Result.error("fieldKey-0","没有需要更新的字段");
 			}
			UserFocus userFocus = fromMap(userFocusMap,UserFocus.class);
			List<UserFocus> userFocussDb=userFocusService.selectListByIds(pkList);
			if(userFocussDb==null ||userFocussDb.size()==0){
				return Result.error("data-0","记录已不存在");
			}
			List<UserFocus> can=new ArrayList<>();
			List<UserFocus> no=new ArrayList<>();
			User user = LoginUtils.getCurrentUserInfo();
			for (UserFocus userFocusDb : userFocussDb) {
				Tips tips2 = new Tips("检查通过"); 
				if(!tips2.isOk()){
				    no.add(userFocusDb); 
				}else{
					can.add(userFocusDb);
				}
			}
			if(can.size()>0){
                userFocusMap.put("$pks",can.stream().map(i->map( "userid",i.getUserid(),  "bizId",i.getBizId(),  "pbizId",i.getPbizId())).collect(Collectors.toList()));
			    userFocusService.editSomeFields(userFocusMap); 
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
	*/

	/**
	@ApiOperation( value = "根据主键列表批量删除我关注的项目或者任务信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelUserFocus(@RequestBody List<UserFocus> userFocuss) {
		
         
        try{ 
            if(userFocuss.size()<=0){
                return Result.error("data-0","请上送待删除数据列表");
            }
             List<UserFocus> datasDb=userFocusService.selectListByIds(userFocuss.stream().map(i->map( "userid",i.getUserid() ,  "bizId",i.getBizId() ,  "pbizId",i.getPbizId() )).collect(Collectors.toList()));

            List<UserFocus> can=new ArrayList<>();
            List<UserFocus> no=new ArrayList<>();
            for (UserFocus data : datasDb) {
                if(true){
                    can.add(data);
                }else{
                    no.add(data);
                } 
            }
            List<String> msgs=new ArrayList<>();
            if(can.size()>0){
                userFocusService.batchDelete(can);
                msgs.add(String.format("成功删除%s条数据.",can.size()));
            }
    
            if(no.size()>0){ 
                msgs.add(String.format("以下%s条数据不能删除.【%s】",no.size(),no.stream().map(i-> i.getUserid() +" "+ i.getBizId() +" "+ i.getPbizId() ).collect(Collectors.joining(","))));
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
        
        return Result.ok();
	} 
	*/
}
