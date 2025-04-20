package com.mdp.sys.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.entity.Tips;
import com.mdp.core.err.BizException;
import com.mdp.core.query.QueryTools;
import com.mdp.core.utils.BaseUtils;
import com.mdp.msg.client.PushNotifyMsgService;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.swagger.ApiEntityParams;
import com.mdp.sys.entity.Branch;
import com.mdp.sys.entity.User;
import com.mdp.sys.entity.UserValidInfo;
import com.mdp.sys.entity.UserValidInfoApprovaVo;
import com.mdp.sys.service.BranchService;
import com.mdp.sys.service.UserCreditRecordService;
import com.mdp.sys.service.UserService;
import com.mdp.sys.service.UserValidInfoService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;
import java.util.stream.Collectors;

import static com.mdp.core.utils.BaseUtils.map;
import static com.mdp.core.utils.BaseUtils.toMap;


/**
 * url编制采用rest风格,如对sys_user_valid_info 用户实名验证资料库的操作有增删改查,对应的url分别为:<br>
 * 组织 com  顶级模块 mdp 大模块 sys 小模块 <br>
 * 实体 UserValidInfo 表 sys_user_valid_info 当前主键(包括多主键): userid; 
 ***/
@RestController("mdp.sys.userValidInfoController")
@RequestMapping(value="/*/sys/userValidInfo")
@Api(tags={"用户实名验证资料库操作接口"})
public class UserValidInfoController {

	@Value(value = "${mdp.platform-branch-id:platform-branch-001}")
	String platformBranchId;
	
	static Logger logger =LoggerFactory.getLogger(UserValidInfoController.class);
	
	@Autowired
	private UserValidInfoService userValidInfoService;


	@Autowired
	private UserService userService;


	@Autowired
	private BranchService branchService;

	@Autowired
	UserCreditRecordService userCreditRecordService;

	@Autowired
	PushNotifyMsgService pushNotifyMsgService;
	 

	Map<String,Object> fieldsMap = toMap(new UserValidInfo());
 
	
	@ApiOperation( value = "查询用户实名验证资料库信息列表",notes=" ")
	@ApiEntityParams( UserValidInfo.class )
	@ApiImplicitParams({
			@ApiImplicitParam(name="pageSize",value="每页大小，默认20条",required=false),
			@ApiImplicitParam(name="pageNum",value="当前页码,从1开始",required=false),
			@ApiImplicitParam(name="total",value="总记录数,服务器端收到0时，会自动计算总记录数，如果上传>0的不自动计算",required=false),
			@ApiImplicitParam(name="count",value="是否计算总记录条数，如果count=true,则计算计算总条数，如果count=false 则不计算",required=false),
			@ApiImplicitParam(name="orderBy",value="排序列 如性别、学生编号排序 orderBy = sex desc,student desc",required=false),
 	})
	@ApiResponses({
		@ApiResponse(code = 200,response=UserValidInfo.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listUserValidInfo( @ApiIgnore @RequestParam Map<String,Object> params){
		
		
		
		if(LoginUtils.hasAnyRoles("superAdmin","platformAdmin")){

		}
		IPage page=QueryTools.initPage(params);
		QueryWrapper<UserValidInfo> qw= QueryTools.initQueryWrapper(UserValidInfo.class,params);
		List<Map<String,Object>>	userValidInfoList = userValidInfoService.selectListMapByWhere(page,qw,params);
		
		return Result.ok().setData(userValidInfoList).setTotal(page.getTotal());

	}


	/**
	@ApiOperation( value = "新增一条用户实名验证资料库信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=UserValidInfo.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addUserValidInfo(@RequestBody UserValidInfo userValidInfo) {
		
		
		try{
		    boolean createPk=false;
			if(!StringUtils.hasText(userValidInfo.getUserid())) {
			    createPk=true;
				userValidInfo.setUserid(userValidInfoService.createKey("userid"));
			}
			if(createPk==false){
                 if(userValidInfoService.selectOneObject(userValidInfo) !=null ){
                    return Result.error("pk-exists","编号重复，请修改编号再提交");
                }
            }
			userValidInfoService.insert(userValidInfo);
			return Result.ok().setData(userValidInfo);
		}catch (BizException e) { 
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}  
	}
	 */
	@ApiOperation( value = "删除一条用户实名验证资料库信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delUserValidInfo(@RequestBody UserValidInfo userValidInfo){
		
		
		try{
            if(!StringUtils.hasText(userValidInfo.getUserid())) {
                 return Result.error("pk-not-exists","请上送主键参数userid");
            }
            UserValidInfo userValidInfoDb = userValidInfoService.selectOneObject(userValidInfo);
            if( userValidInfoDb == null ){
                return Result.error("data-not-exists","数据不存在，无法删除");
            }
			userValidInfoService.deleteByPk(userValidInfo);
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
	@ApiOperation( value = "根据主键修改一条用户实名验证资料库信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=UserValidInfo.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editUserValidInfo(@RequestBody UserValidInfo userValidInfo) {
		
		
		try{
            if(!StringUtils.hasText(userValidInfo.getUserid())) {
                 return Result.error("pk-not-exists","请上送主键参数userid");
            }
            UserValidInfo userValidInfoDb = userValidInfoService.selectOneObject(userValidInfo);
            if( userValidInfoDb == null ){
                return Result.error("data-not-exists","数据不存在，无法修改");
            }
			userValidInfoService.updateSomeFieldByPk(userValidInfo);
			return Result.ok().setData(userValidInfo);
		}catch (BizException e) { 
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}  
	}
	*/


	@ApiOperation( value = "实名认证申请",notes="")
	@ApiEntityParams( value = UserValidInfo.class, props={ }, remark = "用户实名验证资料库", paramType = "body" )
	@ApiResponses({
			@ApiResponse(code = 200,response=UserValidInfo.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result add(  @RequestBody UserValidInfo userValidInfo) {
		
		
		try{

			String userid= userValidInfo.getUserid();
			if(!StringUtils.hasText(userid)){
				return Result.error("userid-0","userids不能为空");
			}
			if(StringUtils.isEmpty(userValidInfo.getIsOrg())){
				return Result.error("isOrg-0","isOrg不能为空，1为企业认证，0为个人认证");
			}

			com.mdp.safe.client.entity.User user=LoginUtils.getCurrentUserInfo();
			if("1".equals(userValidInfo.getIsOrg())){ //企业认证
				if(!userValidInfo.getUserid().equals(user.getBranchId())){
					return Result.error("userid-err1","企业认证用户编号必须为企业管理员");
				}
			}else{
				if(!userValidInfo.getUserid().equals(user.getUserid())){
					return Result.error("userid-err0","个人实名认证必须自己提交");
				}
			}

			User userdb=this.userService.selectOneById(userid);
			if(userdb==null){
				return Result.error("user-0","账户已不存在");
			}
			if("1".equals(userdb.getMemType()) && userdb.getUserid().equals(userdb.getBranchId())){
				if(!"1".equals(userValidInfo.getIsOrg())){
					return Result.error("memType-1","您是企业管理员，只能申请企业认证");
				}
			}
			if("1".equals(userValidInfo.getIsOrg())) {

				//先检查机构名字是否已存在
				boolean nameExists = this.branchService.checkBranchNameExistsAndValid(userValidInfo.getActBname(), userdb.getBranchId());
				if (nameExists) {
					return Result.error("actBname-exists", "机构名称已存在");
				}
				boolean bcodeExists = this.branchService.checkBcodeExistsAndValid(userValidInfo.getUscc(), userdb.getBranchId());
				if (bcodeExists) {
					return Result.error("bcode-exists", "统一社会信用代码已存在");
				}
			}else{
				//如果是个人实名认证，需要查身份证是否已存在
				boolean idNoExists=this.userService.checkIdCardNoExistsAndValid(userValidInfo.getIdNo(),userdb.getUserid());
				if (idNoExists) {
					return Result.error("idCardNo-exists", "身份号码已存在");
				}
			}
			userValidInfo.setFlowState("1");

			UserValidInfo userValidInfoDb=userValidInfoService.selectOneById(userid);
			if(userValidInfoDb==null ){
				userValidInfo.setCtime(new Date());
				userValidInfo.setLtime(new Date());
				userValidInfo.setBranchId(userdb.getBranchId());
				userValidInfo.setFlowState("1");
				pushNotifyMsgService.pushMsg(platformBranchId,"superAdmin","平台管理员",userdb.getUserid(),userdb.getUsername(),userdb.getUsername()+"申请身份实名验证，请及时处理",null);

				this.userValidInfoService.insert(userValidInfo);
			}else{
				if( "0".equals(userValidInfo.getIsOrg() )){
					if("1".equals(userValidInfoDb.getFlowState())){
						return Result.error("flowState-1","已有申请，正在审核中，无须重复申请");
					}
					if("2".equals(userValidInfoDb.getFlowState())){
						return Result.error("flowState-2","实名认证已审核通过，无须重复申请");
					}
				}else{
					if("1".equals(userValidInfoDb.getIsOrg())){
						if("1".equals(userValidInfoDb.getFlowState())){
							return Result.error("flowState-1","已有申请，正在审核中，无须重复申请");
						}
						if("2".equals(userValidInfoDb.getFlowState())){
							return Result.error("flowState-2","实名认证已审核通过，无须重复申请");
						}
					}
				}

				userValidInfo.setCtime(new Date());
				userValidInfo.setLtime(new Date());
				userValidInfo.setBranchId(userdb.getBranchId());
				userValidInfo.setFlowState("1");
				pushNotifyMsgService.pushMsg(platformBranchId,"superAdmin","平台管理员",userdb.getUserid(),userdb.getUsername(),userdb.getUsername()+"申请身份实名验证，请及时处理",null);
				this.userValidInfoService.updateSomeFieldByPk(userValidInfo);

			}
			return Result.ok();
		}catch (BizException e) {
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}
	}

    @ApiOperation( value = "批量修改某些字段,如果没有数据，将自动新增数据",notes="")
    @ApiEntityParams( value = UserValidInfo.class, props={ }, remark = "用户实名验证资料库", paramType = "body" )
	@ApiResponses({
			@ApiResponse(code = 200,response=UserValidInfo.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields( @ApiIgnore @RequestBody Map<String,Object> userValidInfoMap) {
		
		
		try{

            List<String> userids= (List<String>) userValidInfoMap.get("userids");
			if(userids==null || userids.size()==0){
				return Result.error("userids-0","userids不能为空");
			}
			if(userids.size()>1){
				return Result.error("userids-2","一次只能提交一个认证");
			}
			if(!userValidInfoMap.containsKey("isOrg")){
				return Result.error("isOrg-0","isOrg不能为空，1为企业认证，0为个人认证");
			}
			UserValidInfo userValidInfo= BaseUtils.fromMap(userValidInfoMap,UserValidInfo.class);
			if("1".equals(userValidInfo.getIsOrg())) {
				//先检查机构名字是否已存在
				boolean nameExists = this.branchService.checkBranchNameExistsAndValid(userValidInfo.getActBname(), userValidInfo.getBranchId());
				if (nameExists) {
					return Result.error("actBname-exists", "机构名称已存在");
				}
				boolean bcodeExists = this.branchService.checkBcodeExistsAndValid(userValidInfo.getUscc(), userValidInfo.getBranchId());
				if (bcodeExists) {
					return Result.error("bcode-exists", "统一社会信用代码已存在");
				}
			}else{
				//如果是个人实名认证，需要查身份证是否已存在
				boolean idNoExists=this.userService.checkIdCardNoExistsAndValid(userValidInfo.getIdNo(),userValidInfo.getUserid());
				if (idNoExists) {
					return Result.error("idCardNo-exists", "身份号码已存在");
				}
			}
			userValidInfoMap.put("flowState","1");
			Set<String> fields=new HashSet<>();
            fields.add("userid");
			fields.add("validLvls");
			for (String fieldName : userValidInfoMap.keySet()) {
				if(fields.contains(fieldName)){
					return Result.error(fieldName+"-no-edit",fieldName+"不允许修改");
				}
			}
			Set<String> fieldKey=userValidInfoMap.keySet().stream().filter(i-> fieldsMap.containsKey(i)).collect(Collectors.toSet());
			fieldKey=fieldKey.stream().filter(i->!StringUtils.isEmpty(userValidInfoMap.get(i) )).collect(Collectors.toSet());
			com.mdp.safe.client.entity.User user=LoginUtils.getCurrentUserInfo();
			if(fieldKey.size()<=0) {
				return Result.error("fieldKey-0","没有需要更新的字段");
 			}
			List<UserValidInfo> userValidInfosDb=userValidInfoService.selectListByIds(userids);
			if(userValidInfosDb==null ||userValidInfosDb.size()==0){
				userValidInfosDb=new ArrayList<>();
				List<com.mdp.sys.entity.User> usersdb=this.userService.selectListByIds(userids);
				for (User u : usersdb) {
					if(StringUtils.hasText(u.getValidLvls())){
						if(u.getValidLvls().startsWith("1")){
							return Result.error("validLvls","已实名认证，不能重复验证");
						}
					}
					UserValidInfo info=new UserValidInfo();
					info.setUserid("1".equals(userValidInfo.getIsOrg())?u.getBranchId():u.getUserid());
					info.setIsOrg( userValidInfo.getIsOrg() );
					info.setCtime(new Date());
					info.setLtime(new Date());
					info.setBranchId(u.getBranchId());
					info.setFlowState("0");
					userValidInfosDb.add(info);
					pushNotifyMsgService.pushMsg(user,"superAdmin","平台管理员",u.getUsername()+"申请身份实名验证，请及时处理",null);
				}
				this.userValidInfoService.batchInsert(userValidInfosDb);
			}
			List<UserValidInfo> can=new ArrayList<>();
			List<UserValidInfo> no=new ArrayList<>();
			for (UserValidInfo userValidInfoDb : userValidInfosDb) {
				Tips tips2 = new Tips("检查通过"); 
				if(!tips2.isOk()){
				    no.add(userValidInfoDb); 
				}else{
					can.add(userValidInfoDb);
				}
			}
			if(can.size()>0){
                userValidInfoMap.put("userids",can.stream().map(i->i.getUserid()).collect(Collectors.toList()));
			    userValidInfoService.editSomeFields(userValidInfoMap);
			    List<String> branchIds=new ArrayList<>();
				for (UserValidInfo validInfo : can) {
					if("1".equals(validInfo.getIsOrg())){
						branchIds.add(validInfo.getUserid());
					}
				}

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


	@ApiOperation( value = "实名认证审核",notes="")
 	@ApiResponses({
			@ApiResponse(code = 200,response=UserValidInfo.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/approva",method=RequestMethod.POST)
	public Result approva(   @RequestBody UserValidInfoApprovaVo userValidInfoApprovaVo) {
		
		
		try{
			if(!LoginUtils.hasAnyRoles("platformAdmin","superAdmin")){
				return Result.error("role-0","无权限审核，只有平台管理员或者超级管理员有权限审核");
			}
			if(!StringUtils.hasText(userValidInfoApprovaVo.getUserid())){
				return Result.error("userid-0","userid不能为空");
			}

			if(!StringUtils.hasText(userValidInfoApprovaVo.getFlowState())){
				return Result.error("flowState-0","flowState不能为空");
			}
			if(!"2".equals(userValidInfoApprovaVo.getFlowState()) && !StringUtils.hasText(userValidInfoApprovaVo.getValidRemarks())){
				return Result.error("validRemarks-0","validRemarks不能为空");
			}
			UserValidInfo userValidInfoDb=this.userValidInfoService.selectOneById(userValidInfoApprovaVo.getUserid());
			if(userValidInfoDb==null){
				return Result.error("data-0","没有待审核记录");
			}
			if("2".equals(userValidInfoDb.getFlowState())){
				return Result.error("flowState-0","该记录已审核通过，不允许重复审核");
			}
			List<String> branchIds=new ArrayList<>();
			UserValidInfo userValidInfoUpdate=new UserValidInfo();
			String validLvls="";
			if("1".equals(userValidInfoDb.getIsOrg())) {
				//先检查机构名字是否已存在
				boolean nameExists = this.branchService.checkBranchNameExistsAndValid(userValidInfoDb.getActBname(), userValidInfoDb.getBranchId());
				if (nameExists) {
					return Result.error("actBname-exists", "机构名称已存在");
				}
				boolean bcodeExists = this.branchService.checkBcodeExistsAndValid(userValidInfoDb.getUscc(), userValidInfoDb.getBranchId());
				if (bcodeExists) {
					return Result.error("bcode-exists", "统一社会信用代码已存在");
				}
			}else{
				//如果是个人实名认证，需要查身份证是否已存在
				boolean idNoExists=this.userService.checkIdCardNoExistsAndValid(userValidInfoDb.getIdNo(),userValidInfoDb.getUserid());
				if (idNoExists) {
					return Result.error("idCardNo-exists", "身份证号码已存在");
				}
			}
			if("2".equals(userValidInfoApprovaVo.getFlowState())){
				String userid="1".equals(userValidInfoDb.getIsOrg())? userValidInfoDb.getBranchId(): userValidInfoDb.getUserid();
				User userdb=this.userService.selectOneById(userid);
				User userUpdate=new User();
				userUpdate.setUserid(userdb.getUserid());
				validLvls=userdb.getValidLvls();
				if("1".equals(userValidInfoDb.getIsOrg())){
					if(StringUtils.hasText(validLvls)&&validLvls.length()>=8){
						String[] lvlsArr=validLvls.split(",");
						lvlsArr[0]="1";
						lvlsArr[3]="1";
						lvlsArr[4]="1";
						validLvls= Arrays.stream(lvlsArr).collect(Collectors.joining(","));
					}else{
						validLvls="1,0,0,1,1";
					}
					userUpdate.setUsername(userValidInfoDb.getActBname());
				}else{
					if(StringUtils.hasText(validLvls)&&validLvls.length()>=8){
						String[] lvlsArr=validLvls.split(",");
						lvlsArr[0]="1";
						validLvls= Arrays.stream(lvlsArr).collect(Collectors.joining(","));
					}else{
						validLvls="1,0,0,0,0";
					}
					userUpdate.setUsername(userValidInfoDb.getActUname());
				}

				userUpdate.setValidLvls(validLvls);

				if("1".equals(userValidInfoDb.getIsOrg())){
					branchIds.add(userValidInfoDb.getBranchId());
					Branch branchDb=this.branchService.selectOneById(userValidInfoDb.getBranchId());
					if(branchDb==null){
						branchDb=new Branch();
						branchDb.setId(userValidInfoDb.getBranchId());
						branchDb.setBranchName(userValidInfoDb.getActBname());
						branchDb.setBlicense(userValidInfoDb.getBizLicense());
						branchDb.setBcode(userValidInfoDb.getUscc());
						branchDb.setCuserid(userdb.getUserid());
						branchDb.setCusername(userdb.getUsername());
						branchDb.setCdate(new Date());
						branchDb.setAdmUserid(userValidInfoDb.getBranchId());
						branchDb.setAdmUsername(userValidInfoDb.getActBname());
						this.branchService.insert(branchDb);
					}
					validLvls=branchDb.getValidLvls();
					if(StringUtils.hasText(validLvls)&&validLvls.length()>=8){
						String[] lvlsArr=validLvls.split(",");
						lvlsArr[0]="1";
						lvlsArr[3]="1";
						lvlsArr[4]="1";
						validLvls= Arrays.stream(lvlsArr).collect(Collectors.joining(","));
					}else{
						validLvls="1,0,0,1,1";
					}

					this.userService.updateSomeFieldByPk(userUpdate);
					branchService.editSomeFields(map("$pks",branchIds,"validLvls",validLvls,"branchName",userValidInfoDb.getActBname(),"bcode",userValidInfoDb.getUscc()));
					userCreditRecordService.addCreditScore(userUpdate.getUserid(),"2",branchDb.getId(),"企业实名认证");
				}else{
					this.userService.updateSomeFieldByPk(userUpdate);
					userCreditRecordService.addCreditScore(userUpdate.getUserid(),"1",userid,"个人实名认证");
				}

			}


			userValidInfoUpdate.setUserid(userValidInfoDb.getUserid());
			userValidInfoUpdate.setLtime(new Date());
			userValidInfoUpdate.setFlowState(userValidInfoApprovaVo.getFlowState());
			userValidInfoUpdate.setValidRemarks(userValidInfoApprovaVo.getValidRemarks());
			this.userValidInfoService.updateSomeFieldByPk(userValidInfoUpdate);
			com.mdp.safe.client.entity.User user=LoginUtils.getCurrentUserInfo();
			if("3".equals(userValidInfoApprovaVo.getFlowState())){
				pushNotifyMsgService.pushMsg(user,userValidInfoDb.getUserid(),userValidInfoDb.getUserid(), "您申请身份实名验证已被拒绝，请修改资料后重新提交",null);
			}else if("2".equals(userValidInfoApprovaVo.getFlowState())) {
				pushNotifyMsgService.pushMsg(user,userValidInfoDb.getUserid(),userValidInfoDb.getUserid(), "您申请身份实名验证已审核通过",null);
			}

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
	@ApiOperation( value = "根据主键列表批量删除用户实名验证资料库信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelUserValidInfo(@RequestBody List<UserValidInfo> userValidInfos) {
		
         
        try{ 
            if(userValidInfos.size()<=0){
                return Result.error("data-0","请上送待删除数据列表");
            }
             List<UserValidInfo> datasDb=userValidInfoService.selectListByIds(userValidInfos.stream().map(i-> i.getUserid() ).collect(Collectors.toList()));

            List<UserValidInfo> can=new ArrayList<>();
            List<UserValidInfo> no=new ArrayList<>();
            for (UserValidInfo data : datasDb) {
                if(true){
                    can.add(data);
                }else{
                    no.add(data);
                } 
            }
            List<String> msgs=new ArrayList<>();
            if(can.size()>0){
                userValidInfoService.batchDelete(can);
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
	**/
}
