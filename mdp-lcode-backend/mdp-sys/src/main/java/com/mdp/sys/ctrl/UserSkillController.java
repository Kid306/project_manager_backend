package com.mdp.sys.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.err.BizException;
import com.mdp.core.query.QueryTools;
import com.mdp.swagger.ApiEntityParams;
import com.mdp.sys.entity.UserSkill;
import com.mdp.sys.entity.UserSkillsVo;
import com.mdp.sys.service.UserSkillService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

import static com.mdp.core.utils.BaseUtils.toMap;

/**
 * url编制采用rest风格,如对sys_user_skill 该表属于oa.hr_user_skill的冗余表，避免跨库查询的操作有增删改查,对应的url分别为:<br>
 * 组织 com  顶级模块 mdp 大模块 sys 小模块 <br>
 * 实体 UserSkill 表 sys_user_skill 当前主键(包括多主键): userid,skill_id; 
 ***/
@RestController("mdp.sys.userSkillController")
@RequestMapping(value="/*/sys/userSkill")
@Api(tags={"该表属于oa.hr_user_skill的冗余表，避免跨库查询操作接口"})
public class UserSkillController {
	
	static Logger logger =LoggerFactory.getLogger(UserSkillController.class);
	
	@Autowired
	private UserSkillService userSkillService;
	 

	Map<String,Object> fieldsMap = toMap(new UserSkill());
 
	
	@ApiOperation( value = "查询该表属于oa.hr_user_skill的冗余表，避免跨库查询信息列表",notes=" ")
	@ApiEntityParams( UserSkill.class )
	@ApiResponses({
		@ApiResponse(code = 200,response=UserSkill.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listUserSkill( @ApiIgnore @RequestParam Map<String,Object> params){
		
		
		
		IPage page=QueryTools.initPage(params);
		QueryWrapper<UserSkill> qw= QueryTools.initQueryWrapper(UserSkill.class,params);
		List<Map<String,Object>>	userSkillList = userSkillService.selectListMapByWhere(page,qw,params);
		
		return Result.ok().setData(userSkillList).setTotal(page.getTotal());

	}
	
 
	
	/**
	@ApiOperation( value = "新增一条该表属于oa.hr_user_skill的冗余表，避免跨库查询信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=UserSkill.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addUserSkill(@RequestBody UserSkill userSkill) {
		
		
		try{
		    boolean createPk=false;
			if(!StringUtils.hasText(userSkill.getUserid())) {
			    createPk=true;
				userSkill.setUserid(userSkillService.createKey("userid"));
			}
			if(!StringUtils.hasText(userSkill.getSkillId())) {
			    createPk=true;
				userSkill.setSkillId(userSkillService.createKey("skillId"));
			}
			if(createPk==false){
                 if(userSkillService.selectOneObject(userSkill) !=null ){
                    return Result.error("pk-exists","编号重复，请修改编号再提交");
                }
            }
			userSkillService.insert(userSkill);
			return Result.ok().setData(userSkill);
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
	@ApiOperation( value = "删除一条该表属于oa.hr_user_skill的冗余表，避免跨库查询信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delUserSkill(@RequestBody UserSkill userSkill){
		
		
		try{
            if(!StringUtils.hasText(userSkill.getUserid())) {
                 return Result.error("pk-not-exists","请上送主键参数userid");
            }
            if(!StringUtils.hasText(userSkill.getSkillId())) {
                 return Result.error("pk-not-exists","请上送主键参数skillId");
            }
            UserSkill userSkillDb = userSkillService.selectOneObject(userSkill);
            if( userSkillDb == null ){
                return Result.error("data-not-exists","数据不存在，无法删除");
            }
			userSkillService.deleteByPk(userSkill);
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
	@ApiOperation( value = "根据主键修改一条该表属于oa.hr_user_skill的冗余表，避免跨库查询信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=UserSkill.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editUserSkill(@RequestBody UserSkill userSkill) {
		
		
		try{
            if(!StringUtils.hasText(userSkill.getUserid())) {
                 return Result.error("pk-not-exists","请上送主键参数userid");
            }
            if(!StringUtils.hasText(userSkill.getSkillId())) {
                 return Result.error("pk-not-exists","请上送主键参数skillId");
            }
            UserSkill userSkillDb = userSkillService.selectOneObject(userSkill);
            if( userSkillDb == null ){
                return Result.error("data-not-exists","数据不存在，无法修改");
            }
			userSkillService.updateSomeFieldByPk(userSkill);
			return Result.ok().setData(userSkill);
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
    @ApiEntityParams( value = UserSkill.class, props={ }, remark = "该表属于oa.hr_user_skill的冗余表，避免跨库查询", paramType = "body" )
	@ApiResponses({
			@ApiResponse(code = 200,response=UserSkill.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields( @ApiIgnore @RequestBody Map<String,Object> userSkillMap) {
		
		
		try{
			List<Map<String,Object>> pkList= (List<Map<String,Object>>) userSkillMap.get("$pks");
			if(pkList==null || pkList.size()==0){
				return Result.error("$pks-0","$pks不能为空");
			}

			Set<String> fields=new HashSet<>();
            fields.add("userid");
            fields.add("skillId");
			for (String fieldName : userSkillMap.keySet()) {
				if(fields.contains(fieldName)){
					return Result.error(fieldName+"-no-edit",fieldName+"不允许修改");
				}
			}
			Set<String> fieldKey=userSkillMap.keySet().stream().filter(i-> fieldsMap.containsKey(i)).collect(Collectors.toSet());
			fieldKey=fieldKey.stream().filter(i->!StringUtils.isEmpty(userSkillMap.get(i) )).collect(Collectors.toSet());

			if(fieldKey.size()<=0) {
				return Result.error("fieldKey-0","没有需要更新的字段");
 			}
			UserSkill userSkill = fromMap(userSkillMap,UserSkill.class);
			List<UserSkill> userSkillsDb=userSkillService.selectListByIds(pkList);
			if(userSkillsDb==null ||userSkillsDb.size()==0){
				return Result.error("data-0","记录已不存在");
			}
			List<UserSkill> can=new ArrayList<>();
			List<UserSkill> no=new ArrayList<>();
			User user = LoginUtils.getCurrentUserInfo();
			for (UserSkill userSkillDb : userSkillsDb) {
				Tips tips2 = new Tips("检查通过"); 
				if(!tips2.isOk()){
				    no.add(userSkillDb); 
				}else{
					can.add(userSkillDb);
				}
			}
			if(can.size()>0){
                userSkillMap.put("$pks",can.stream().map(i->map( "userid",i.getUserid(),  "skillId",i.getSkillId())).collect(Collectors.toList()));
			    userSkillService.editSomeFields(userSkillMap); 
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
	@ApiOperation( value = "根据主键列表批量删除该表属于oa.hr_user_skill的冗余表，避免跨库查询信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelUserSkill(@RequestBody List<UserSkill> userSkills) {
		
         
        try{ 
            if(userSkills.size()<=0){
                return Result.error("data-0","请上送待删除数据列表");
            }
             List<UserSkill> datasDb=userSkillService.selectListByIds(userSkills.stream().map(i->map( "userid",i.getUserid() ,  "skillId",i.getSkillId() )).collect(Collectors.toList()));

            List<UserSkill> can=new ArrayList<>();
            List<UserSkill> no=new ArrayList<>();
            for (UserSkill data : datasDb) {
                if(true){
                    can.add(data);
                }else{
                    no.add(data);
                } 
            }
            List<String> msgs=new ArrayList<>();
            if(can.size()>0){
                userSkillService.batchDelete(can);
                msgs.add(String.format("成功删除%s条数据.",can.size()));
            }
    
            if(no.size()>0){ 
                msgs.add(String.format("以下%s条数据不能删除.【%s】",no.size(),no.stream().map(i-> i.getUserid() +" "+ i.getSkillId() ).collect(Collectors.joining(","))));
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


	@ApiOperation( value = "根据主键列表批量删除sys_user_skill信息",notes="batchEdit,仅需要上传主键字段")
	@ApiEntityParams(UserSkillsVo.class)
	@ApiResponses({
			@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	})
	@RequestMapping(value="/batchEdit",method=RequestMethod.POST)
	public Result batchEdit(@RequestBody UserSkillsVo userSkillsVo) {
		
		
		try{
			userSkillService.batchEdit(userSkillsVo);
			return Result.ok();
		}catch (BizException e) {
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}
	}
}
