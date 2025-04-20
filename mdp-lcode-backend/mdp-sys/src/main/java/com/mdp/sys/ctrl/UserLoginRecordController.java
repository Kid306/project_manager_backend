package com.mdp.sys.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.query.QueryTools;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.sys.entity.UserLoginRecord;
import com.mdp.sys.service.UserLoginRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

import static com.mdp.core.utils.BaseUtils.toMap;

/**
 * url编制采用rest风格,如对sys_user_login_record 用户登录信息登记的操作有增删改查,对应的url分别为:<br>
 *  新增: sys/userLoginRecord/add <br>
 *  查询: sys/userLoginRecord/list<br>
 *  模糊查询: sys/userLoginRecord/listKey<br>
 *  修改: sys/userLoginRecord/edit <br>
 *  删除: sys/userLoginRecord/del<br>
 *  批量删除: sys/userLoginRecord/batchDel<br>
 * 组织 com  顶级模块 mdp 大模块 sys 小模块 <br>
 * 实体 UserLoginRecord 表 sys_user_login_record 当前主键(包括多主键): id; 
 ***/
@RestController("mdp.sys.userLoginRecordController")
@RequestMapping(value="/*/sys/userLoginRecord")
@Api(tags={"用户登录信息登记操作接口"})
public class UserLoginRecordController {
	
	static Logger logger =LoggerFactory.getLogger(UserLoginRecordController.class);
	
	@Autowired
	private UserLoginRecordService userLoginRecordService;
	 

	Map<String,Object> fieldsMap = toMap(new UserLoginRecord());
 
	
	@ApiOperation( value = "查询用户登录信息登记信息列表",notes=" ") 
	@ApiResponses({
		@ApiResponse(code = 200,response=UserLoginRecord.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listUserLoginRecord( @ApiIgnore @RequestParam Map<String,Object> params){
		
		
		
		IPage page=QueryTools.initPage(params);
		QueryWrapper<UserLoginRecord> qw= QueryTools.initQueryWrapper(UserLoginRecord.class,params);
		User user=LoginUtils.getCurrentUserInfo();
		if(LoginUtils.isSuperAdmin() ){
 			qw.eq("branch_id",user.getBranchId());
		}else if(LoginUtils.isBranchAdmin(user.getBranchId())){
			qw.eq("branch_id",user.getBranchId());
		}else{
			qw.eq("branch_id",user.getBranchId());
			qw.eq("userid",user.getUserid());
		}
		List<Map<String,Object>>	userLoginRecordList = userLoginRecordService.selectListMapByWhere(page,qw,params);
		for (Map<String, Object> map : userLoginRecordList) {
			String phoneno= (String) map.get("phoneno");
			if(StringUtils.hasText(phoneno) && phoneno.length()>10){
				phoneno=phoneno.substring(0,3)+"******"+phoneno.substring(9);
			}else if(StringUtils.hasText(phoneno) && phoneno.length()>6){
				phoneno=phoneno.substring(0,3)+"***"+phoneno.substring(6);
			}
			map.put("phoneno",phoneno);
		}
		
		return Result.ok().setData(userLoginRecordList).setTotal(page.getTotal());

	}
	
 
	
	/**
	@ApiOperation( value = "新增一条用户登录信息登记信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=UserLoginRecord.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addUserLoginRecord(@RequestBody UserLoginRecord userLoginRecord) {
		
		
		try{
		    boolean createPk=false;
			if(!StringUtils.hasText(userLoginRecord.getId())) {
			    createPk=true;
				userLoginRecord.setId(userLoginRecordService.createKey("id"));
			}
			if(createPk==false){
                 if(userLoginRecordService.selectOneObject(userLoginRecord) !=null ){
                    return Result.error("pk-exists","编号重复，请修改编号再提交");
                }
            }
			userLoginRecordService.insert(userLoginRecord);
			return Result.ok().setData(userLoginRecord);
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
	@ApiOperation( value = "删除一条用户登录信息登记信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delUserLoginRecord(@RequestBody UserLoginRecord userLoginRecord){
		
		
		try{
            if(!StringUtils.hasText(userLoginRecord.getId())) {
                 return Result.error("pk-not-exists","请上送主键参数id");
            }
            UserLoginRecord userLoginRecordDb = userLoginRecordService.selectOneObject(userLoginRecord);
            if( userLoginRecordDb == null ){
                return Result.error("data-not-exists","数据不存在，无法删除");
            }
			userLoginRecordService.deleteByPk(userLoginRecord);
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
	@ApiOperation( value = "根据主键修改一条用户登录信息登记信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=UserLoginRecord.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editUserLoginRecord(@RequestBody UserLoginRecord userLoginRecord) {
		
		
		try{
            if(!StringUtils.hasText(userLoginRecord.getId())) {
                 return Result.error("pk-not-exists","请上送主键参数id");
            }
            UserLoginRecord userLoginRecordDb = userLoginRecordService.selectOneObject(userLoginRecord);
            if( userLoginRecordDb == null ){
                return Result.error("data-not-exists","数据不存在，无法修改");
            }
			userLoginRecordService.updateSomeFieldByPk(userLoginRecord);
			return Result.ok().setData(userLoginRecord);
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
	@ApiResponses({
			@ApiResponse(code = 200,response=UserLoginRecord.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields(@RequestBody Map<String,Object> userLoginRecordMap) {
		
		
		try{
            List<String> ids= (List<String>) userLoginRecordMap.get("$pks");
			if(ids==null || ids.size()==0){
				return Result.error("ids-0","ids不能为空");
			}

			Set<String> fields=new HashSet<>();
            fields.add("id");
			for (String fieldName : userLoginRecordMap.keySet()) {
				if(fields.contains(fieldName)){
					return Result.error(fieldName+"-no-edit",fieldName+"不允许修改");
				}
			}
			Set<String> fieldKey=userLoginRecordMap.keySet().stream().filter(i-> fieldsMap.containsKey(i)).collect(Collectors.toSet());
			fieldKey=fieldKey.stream().filter(i->!StringUtils.isEmpty(userLoginRecordMap.get(i) )).collect(Collectors.toSet());

			if(fieldKey.size()<=0) {
				return Result.error("fieldKey-0","没有需要更新的字段");
 			}
			UserLoginRecord userLoginRecord = fromMap(userLoginRecordMap,UserLoginRecord.class);
			List<UserLoginRecord> userLoginRecordsDb=userLoginRecordService.selectListByIds(ids);
			if(userLoginRecordsDb==null ||userLoginRecordsDb.size()==0){
				return Result.error("data-0","记录已不存在");
			}
			List<UserLoginRecord> can=new ArrayList<>();
			List<UserLoginRecord> no=new ArrayList<>();
			User user = LoginUtils.getCurrentUserInfo();
			for (UserLoginRecord userLoginRecordDb : userLoginRecordsDb) {
				Tips tips2 = new Tips("检查通过"); 
				if(!tips2.isOk()){
				    no.add(userLoginRecordDb); 
				}else{
					can.add(userLoginRecordDb);
				}
			}
			if(can.size()>0){
                userLoginRecordMap.put("$pks",can.stream().map(i->i.getId()).collect(Collectors.toList()));
			    userLoginRecordService.editSomeFields(userLoginRecordMap); 
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
	@ApiOperation( value = "根据主键列表批量删除用户登录信息登记信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelUserLoginRecord(@RequestBody List<UserLoginRecord> userLoginRecords) {
		
         
        try{ 
            if(userLoginRecords.size()<=0){
                return Result.error("data-0","请上送待删除数据列表");
            }
             List<UserLoginRecord> datasDb=userLoginRecordService.selectListByIds(userLoginRecords.stream().map(i-> i.getId() ).collect(Collectors.toList()));

            List<UserLoginRecord> can=new ArrayList<>();
            List<UserLoginRecord> no=new ArrayList<>();
            for (UserLoginRecord data : datasDb) {
                if(true){
                    can.add(data);
                }else{
                    no.add(data);
                } 
            }
            List<String> msgs=new ArrayList<>();
            if(can.size()>0){
                userLoginRecordService.batchDelete(userLoginRecords);
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
        
        return Result.ok();
	} 
	*/
}
