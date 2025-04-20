package com.mdp.sys.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.query.QueryTools;
import com.mdp.swagger.ApiEntityParams;
import com.mdp.sys.entity.UserGradeRecord;
import com.mdp.sys.service.UserGradeRecordService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

import static com.mdp.core.utils.BaseUtils.toMap;

/**
 * url编制采用rest风格,如对sys_user_grade_record 用户信用得分记录表的操作有增删改查,对应的url分别为:<br>
 * 组织 com  顶级模块 mdp 大模块 sys 小模块 <br>
 * 实体 UserGradeRecord 表 sys_user_grade_record 当前主键(包括多主键): id; 
 ***/
@RestController("mdp.sys.userGradeRecordController")
@RequestMapping(value="/*/sys/userGradeRecord")
@Api(tags={"用户信用得分记录表操作接口"})
public class UserGradeRecordController {
	
	static Logger logger =LoggerFactory.getLogger(UserGradeRecordController.class);
	
	@Autowired
	private UserGradeRecordService userGradeRecordService;
	 

	Map<String,Object> fieldsMap = toMap(new UserGradeRecord());
 
	
	@ApiOperation( value = "查询用户信用得分记录表信息列表",notes=" ")
	@ApiEntityParams( UserGradeRecord.class )
	@ApiImplicitParams({
			@ApiImplicitParam(name="pageSize",value="每页大小，默认20条",required=false),
			@ApiImplicitParam(name="pageNum",value="当前页码,从1开始",required=false),
			@ApiImplicitParam(name="total",value="总记录数,服务器端收到0时，会自动计算总记录数，如果上传>0的不自动计算",required=false),
			@ApiImplicitParam(name="count",value="是否计算总记录条数，如果count=true,则计算计算总条数，如果count=false 则不计算",required=false),
			@ApiImplicitParam(name="orderBy",value="排序列 如性别、学生编号排序 orderBy = sex desc,student desc",required=false),
 	})
	@ApiResponses({
		@ApiResponse(code = 200,response=UserGradeRecord.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listUserGradeRecord(@ApiIgnore @RequestParam Map<String,Object> params){
		
		
		
		IPage page=QueryTools.initPage(params);
 		QueryWrapper<UserGradeRecord> qw= QueryTools.initQueryWrapper(UserGradeRecord.class,params);

		List<Map<String,Object>>	userGradeRecordList = userGradeRecordService.selectListMapByWhere(page,qw,params);
		
		return Result.ok().setData(userGradeRecordList).setTotal(page.getTotal());

	}
	
 
	
	/**
	@ApiOperation( value = "新增一条用户信用得分记录表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=UserGradeRecord.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addUserGradeRecord(@RequestBody UserGradeRecord userGradeRecord) {
		
		
		try{
		    boolean createPk=false;
			if(!StringUtils.hasText(userGradeRecord.getId())) {
			    createPk=true;
				userGradeRecord.setId(userGradeRecordService.createKey("id"));
			}
			if(createPk==false){
                 if(userGradeRecordService.selectOneObject(userGradeRecord) !=null ){
                    return Result.error("pk-exists","编号重复，请修改编号再提交");
                }
            }
			userGradeRecordService.insert(userGradeRecord);
			return Result.ok().setData(userGradeRecord);
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
	@ApiOperation( value = "删除一条用户信用得分记录表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delUserGradeRecord(@RequestBody UserGradeRecord userGradeRecord){
		
		
		try{
            if(!StringUtils.hasText(userGradeRecord.getId())) {
                 return Result.error("pk-not-exists","请上送主键参数id");
            }
            UserGradeRecord userGradeRecordDb = userGradeRecordService.selectOneObject(userGradeRecord);
            if( userGradeRecordDb == null ){
                return Result.error("data-not-exists","数据不存在，无法删除");
            }
			userGradeRecordService.deleteByPk(userGradeRecord);
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
	@ApiOperation( value = "根据主键修改一条用户信用得分记录表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=UserGradeRecord.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editUserGradeRecord(@RequestBody UserGradeRecord userGradeRecord) {
		
		
		try{
            if(!StringUtils.hasText(userGradeRecord.getId())) {
                 return Result.error("pk-not-exists","请上送主键参数id");
            }
            UserGradeRecord userGradeRecordDb = userGradeRecordService.selectOneObject(userGradeRecord);
            if( userGradeRecordDb == null ){
                return Result.error("data-not-exists","数据不存在，无法修改");
            }
			userGradeRecordService.updateSomeFieldByPk(userGradeRecord);
			return Result.ok().setData(userGradeRecord);
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
    @ApiEntityParams( value = UserGradeRecord.class, props={ }, remark = "用户信用得分记录表", paramType = "body" )
	@ApiResponses({
			@ApiResponse(code = 200,response=UserGradeRecord.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields( @ApiIgnore @RequestBody Map<String,Object> userGradeRecordMap) {
		
		
		try{
            List<String> ids= (List<String>) userGradeRecordMap.get("$pks");
			if(ids==null || ids.size()==0){
				return Result.error("ids-0","ids不能为空");
			}

			Set<String> fields=new HashSet<>();
            fields.add("id");
			for (String fieldName : userGradeRecordMap.keySet()) {
				if(fields.contains(fieldName)){
					return Result.error(fieldName+"-no-edit",fieldName+"不允许修改");
				}
			}
			Set<String> fieldKey=userGradeRecordMap.keySet().stream().filter(i-> fieldsMap.containsKey(i)).collect(Collectors.toSet());
			fieldKey=fieldKey.stream().filter(i->!StringUtils.isEmpty(userGradeRecordMap.get(i) )).collect(Collectors.toSet());

			if(fieldKey.size()<=0) {
				return Result.error("fieldKey-0","没有需要更新的字段");
 			}
			UserGradeRecord userGradeRecord = fromMap(userGradeRecordMap,UserGradeRecord.class);
			List<UserGradeRecord> userGradeRecordsDb=userGradeRecordService.selectListByIds(ids);
			if(userGradeRecordsDb==null ||userGradeRecordsDb.size()==0){
				return Result.error("data-0","记录已不存在");
			}
			List<UserGradeRecord> can=new ArrayList<>();
			List<UserGradeRecord> no=new ArrayList<>();
			User user = LoginUtils.getCurrentUserInfo();
			for (UserGradeRecord userGradeRecordDb : userGradeRecordsDb) {
				Tips tips2 = new Tips("检查通过"); 
				if(!tips2.isOk()){
				    no.add(userGradeRecordDb); 
				}else{
					can.add(userGradeRecordDb);
				}
			}
			if(can.size()>0){
                userGradeRecordMap.put("$pks",can.stream().map(i->i.getId()).collect(Collectors.toList()));
			    userGradeRecordService.editSomeFields(userGradeRecordMap); 
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
	@ApiOperation( value = "根据主键列表批量删除用户信用得分记录表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelUserGradeRecord(@RequestBody List<UserGradeRecord> userGradeRecords) {
		
         
        try{ 
            if(userGradeRecords.size()<=0){
                return Result.error("data-0","请上送待删除数据列表");
            }
             List<UserGradeRecord> datasDb=userGradeRecordService.selectListByIds(userGradeRecords.stream().map(i-> i.getId() ).collect(Collectors.toList()));

            List<UserGradeRecord> can=new ArrayList<>();
            List<UserGradeRecord> no=new ArrayList<>();
            for (UserGradeRecord data : datasDb) {
                if(true){
                    can.add(data);
                }else{
                    no.add(data);
                } 
            }
            List<String> msgs=new ArrayList<>();
            if(can.size()>0){
                userGradeRecordService.batchDelete(can);
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
