package com.mdp.sys.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.query.QueryTools;
import com.mdp.core.utils.NumberUtil;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.swagger.ApiEntityParams;
import com.mdp.sys.entity.UserInterests;
import com.mdp.sys.service.UserInterestsService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static com.mdp.core.utils.BaseUtils.toMap;

/**
 * url编制采用rest风格,如对sys_user_interests 用户权益关联表的操作有增删改查,对应的url分别为:<br>
 * 组织 com  顶级模块 mdp 大模块 sys 小模块 <br>
 * 实体 UserInterests 表 sys_user_interests 当前主键(包括多主键): userid; 
 ***/
@RestController("mdp.sys.userInterestsController")
@RequestMapping(value="/*/sys/userInterests")
@Api(tags={"个人权益关联表操作接口"})
public class UserInterestsController {
	
	static Logger logger =LoggerFactory.getLogger(UserInterestsController.class);
	
	@Autowired
	private UserInterestsService userInterestsService;
	 

	Map<String,Object> fieldsMap = toMap(new UserInterests());
 
	
	@ApiOperation( value = "查询个人权益关联表信息列表",notes=" ")
	@ApiEntityParams( UserInterests.class )
	@ApiImplicitParams({
			@ApiImplicitParam(name="pageSize",value="每页大小，默认20条",required=false),
			@ApiImplicitParam(name="pageNum",value="当前页码,从1开始",required=false),
			@ApiImplicitParam(name="total",value="总记录数,服务器端收到0时，会自动计算总记录数，如果上传>0的不自动计算",required=false),
			@ApiImplicitParam(name="count",value="是否计算总记录条数，如果count=true,则计算计算总条数，如果count=false 则不计算",required=false),
			@ApiImplicitParam(name="orderBy",value="排序列 如性别、学生编号排序 orderBy = sex desc,student desc",required=false),
 	})
	@ApiResponses({
		@ApiResponse(code = 200,response=UserInterests.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listUserInterests( @ApiIgnore @RequestParam Map<String,Object> params){
		
		
		
		IPage page=QueryTools.initPage(params);
		QueryWrapper<UserInterests> qw= QueryTools.initQueryWrapper(UserInterests.class,params);
		List<Map<String,Object>>	userInterestsList = userInterestsService.selectListMapByWhere(page,qw,params);
		
		return Result.ok().setData(userInterestsList);

	}


	@ApiOperation( value = "查询个人权益明细",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200,response=UserInterests.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/detail",method=RequestMethod.GET)
	public Result listUserInterests(  ){
		
		
		User user=LoginUtils.getCurrentUserInfo();
		Map<String,Object> userInterestsMap=this.userInterestsService.detailMap(user.getUserid());
		if(userInterestsMap==null || userInterestsMap.isEmpty()){
			UserInterests userInterests=this.userInterestsService.getDefaultUserInterests(user.getUserid());
			this.userInterestsService.insert(userInterests);
			userInterestsMap=this.userInterestsService.detailMap(user.getBranchId());
		}
		userInterestsMap.remove("password");
		return Result.ok().setData(userInterestsMap);

	}
	/**
	 * 检查个人是否具有某种权益
	 * userid-用户编号，exp-需要新增的经验值，bids-需要新增的投标次数，at-需要增加的金额
	 * @param userInterests
	 * @return
	 */
	@ApiOperation( value = "检查会员是否具有权益",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200,response=UserInterests.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/checkUserInterests",method=RequestMethod.GET)
	public Result checkUserInterests( @ApiIgnore @RequestParam Map<String,Object> userInterests){
		
		
		String userid= (String) userInterests.get("userid");
		if(!StringUtils.hasText((CharSequence) userInterests.get("userid"))){
			return Result.error("userid-0","用户编号不能为空");
		}
		Integer bids=NumberUtil.getInteger(userInterests.get("bids"),0);
		BigDecimal exp=NumberUtil.getBigDecimal(userInterests.get("exp"),BigDecimal.ZERO);
		BigDecimal at=NumberUtil.getBigDecimal(userInterests.get("at"),BigDecimal.ZERO);
		UserInterests userInterestsDb=this.userInterestsService.selectOneById(userid);
		if(userInterestsDb==null){
			userInterestsDb=this.userInterestsService.getDefaultUserInterests(userid);
			this.userInterestsService.insert(userInterestsDb);
		}
		if(bids>0){
			if(userInterestsDb.getBids()!=null && userInterestsDb.getCmonthBids()+bids>userInterestsDb.getBids()){
				return Result.error("bids-not-enough","投标次数超限");
			}
		}
		if(exp.compareTo(BigDecimal.ZERO)>0){
			if(userInterestsDb.getSmaxExp()!=null && userInterestsDb.getSmaxExp().compareTo(exp)<0){
				return Result.error("smaxExp-not-enough","投标工作量超限");
			}
		}
		if(at.compareTo(BigDecimal.ZERO)>0){
			if(userInterestsDb.getSmaxAt()!=null && userInterestsDb.getSmaxAt().compareTo(at)<0){
				return Result.error("smaxAt-not-enough","投标金额超限");
			}
		}
		return Result.ok().setData(userInterestsDb);
	}
	
	/**
	@ApiOperation( value = "新增一条用户权益关联表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=UserInterests.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addUserInterests(@RequestBody UserInterests userInterests) {
		
		
		try{
		    boolean createPk=false;
			if(!StringUtils.hasText(userInterests.getUserid())) {
			    createPk=true;
				userInterests.setUserid(userInterestsService.createKey("userid"));
			}
			if(createPk==false){
                 if(userInterestsService.selectOneObject(userInterests) !=null ){
                    return Result.error("pk-exists","编号重复，请修改编号再提交");
                }
            }
			userInterestsService.insert(userInterests);
			return Result.ok().setData(userInterests);
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
	@ApiOperation( value = "删除一条用户权益关联表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delUserInterests(@RequestBody UserInterests userInterests){
		
		
		try{
            if(!StringUtils.hasText(userInterests.getUserid())) {
                 return Result.error("pk-not-exists","请上送主键参数userid");
            }
            UserInterests userInterestsDb = userInterestsService.selectOneObject(userInterests);
            if( userInterestsDb == null ){
                return Result.error("data-not-exists","数据不存在，无法删除");
            }
			userInterestsService.deleteByPk(userInterests);
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
	@ApiOperation( value = "根据主键修改一条用户权益关联表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=UserInterests.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editUserInterests(@RequestBody UserInterests userInterests) {
		
		
		try{
            if(!StringUtils.hasText(userInterests.getUserid())) {
                 return Result.error("pk-not-exists","请上送主键参数userid");
            }
            UserInterests userInterestsDb = userInterestsService.selectOneObject(userInterests);
            if( userInterestsDb == null ){
                return Result.error("data-not-exists","数据不存在，无法修改");
            }
			userInterestsService.updateSomeFieldByPk(userInterests);
			return Result.ok().setData(userInterests);
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
    @ApiEntityParams( value = UserInterests.class, props={ }, remark = "用户权益关联表", paramType = "body" )
	@ApiResponses({
			@ApiResponse(code = 200,response=UserInterests.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields( @ApiIgnore @RequestBody Map<String,Object> userInterestsMap) {
		
		
		try{
            List<String> userids= (List<String>) userInterestsMap.get("userids");
			if(userids==null || userids.size()==0){
				return Result.error("userids-0","userids不能为空");
			}

			Set<String> fields=new HashSet<>();
            fields.add("userid");
			for (String fieldName : userInterestsMap.keySet()) {
				if(fields.contains(fieldName)){
					return Result.error(fieldName+"-no-edit",fieldName+"不允许修改");
				}
			}
			Set<String> fieldKey=userInterestsMap.keySet().stream().filter(i-> fieldsMap.containsKey(i)).collect(Collectors.toSet());
			fieldKey=fieldKey.stream().filter(i->!StringUtils.isEmpty(userInterestsMap.get(i) )).collect(Collectors.toSet());

			if(fieldKey.size()<=0) {
				return Result.error("fieldKey-0","没有需要更新的字段");
 			}
			UserInterests userInterests = fromMap(userInterestsMap,UserInterests.class);
			List<UserInterests> userInterestssDb=userInterestsService.selectListByIds(userids);
			if(userInterestssDb==null ||userInterestssDb.size()==0){
				return Result.error("data-0","记录已不存在");
			}
			List<UserInterests> can=new ArrayList<>();
			List<UserInterests> no=new ArrayList<>();
			User user = LoginUtils.getCurrentUserInfo();
			for (UserInterests userInterestsDb : userInterestssDb) {
				Tips tips2 = new Tips("检查通过"); 
				if(!tips2.isOk()){
				    no.add(userInterestsDb); 
				}else{
					can.add(userInterestsDb);
				}
			}
			if(can.size()>0){
                userInterestsMap.put("userids",can.stream().map(i->i.getUserid()).collect(Collectors.toList()));
			    userInterestsService.editSomeFields(userInterestsMap); 
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
	@ApiOperation( value = "根据主键列表批量删除用户权益关联表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelUserInterests(@RequestBody List<UserInterests> userInterestss) {
		
         
        try{ 
            if(userInterestss.size()<=0){
                return Result.error("data-0","请上送待删除数据列表");
            }
             List<UserInterests> datasDb=userInterestsService.selectListByIds(userInterestss.stream().map(i-> i.getUserid() ).collect(Collectors.toList()));

            List<UserInterests> can=new ArrayList<>();
            List<UserInterests> no=new ArrayList<>();
            for (UserInterests data : datasDb) {
                if(true){
                    can.add(data);
                }else{
                    no.add(data);
                } 
            }
            List<String> msgs=new ArrayList<>();
            if(can.size()>0){
                userInterestsService.batchDelete(can);
                msgs.add(String.format("成功删除%s条数据.",can.size()));
            }
    
            if(no.size()>0){ 
                msgs.add(String.format("以下%s条数据不能删除.【%s】",no.size(),no.stream().map(i-> i.getUserid() ).collect(Collectors.joining(","))));
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
