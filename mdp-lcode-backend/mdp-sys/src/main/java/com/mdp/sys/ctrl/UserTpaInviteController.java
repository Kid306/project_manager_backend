package com.mdp.sys.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.LangTips;
import com.mdp.core.entity.Result;
import com.mdp.core.err.BizException;
import com.mdp.core.query.QueryTools;
import com.mdp.core.utils.ObjectTools;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.swagger.ApiEntityParams;
import com.mdp.sys.entity.Branch;
import com.mdp.sys.entity.UserTpaInvite;
import com.mdp.sys.pub.service.InviteCacheService;
import com.mdp.sys.service.BranchService;
import com.mdp.sys.service.UserTpaInviteService;
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
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
/**
 * @author maimeng-mdp code-gen
 * @since 2023-9-22
 */
@RestController
@RequestMapping(value="/mdp/sys/userTpaInvite")
@Api(tags={"第三方邀请加入流水表，send_branch_id+join_userid唯一索引-操作接口"})
public class UserTpaInviteController {
	
	static Logger logger =LoggerFactory.getLogger(UserTpaInviteController.class);
	
	@Autowired
	private UserTpaInviteService userTpaInviteService;
	@Autowired
	InviteCacheService inviteCacheService;

	@Autowired
	BranchService branchService;

	@ApiOperation( value = "第三方邀请加入流水表，send_branch_id+join_userid唯一索引-查询列表",notes=" ")
	@ApiEntityParams(UserTpaInvite.class)
	@ApiResponses({
		@ApiResponse(code = 200,response=UserTpaInvite.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listUserTpaInvite(@ApiIgnore @RequestParam Map<String,Object> params){
		try {
			User user=LoginUtils.getCurrentUserInfo();
			QueryWrapper<UserTpaInvite> qw = QueryTools.initQueryWrapper(UserTpaInvite.class , params);
			qw.eq("send_branch_id",user.getBranchId());
			IPage page=QueryTools.initPage(params);
			List<Map<String,Object>> datas = userTpaInviteService.selectListMapByWhere(page,qw,params);
			return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());
		}catch (BizException e) {
			return Result.error(e);
		}catch (Exception e) {
			return Result.error(e);
		}
	}
	

	@ApiOperation( value = "第三方邀请加入流水表，send_branch_id+join_userid唯一索引-新增",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=UserTpaInvite.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addUserTpaInvite(@RequestBody UserTpaInvite userTpaInvite) {
		 userTpaInviteService.save(userTpaInvite);
         return Result.ok("add-ok","添加成功！");
	}

	@ApiOperation( value = "第三方邀请加入流水表，send_branch_id+join_userid唯一索引-删除",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delUserTpaInvite(@RequestBody UserTpaInvite userTpaInvite){
		userTpaInviteService.removeById(userTpaInvite);
        return Result.ok("del-ok","删除成功！");
	}

	@ApiOperation( value = "第三方邀请加入流水表，send_branch_id+join_userid唯一索引-修改",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=UserTpaInvite.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editUserTpaInvite(@RequestBody UserTpaInvite userTpaInvite) {
		userTpaInviteService.updateById(userTpaInvite);
        return Result.ok("edit-ok","修改成功！");
	}

    @ApiOperation( value = "第三方邀请加入流水表，send_branch_id+join_userid唯一索引-批量修改某些字段",notes="")
    @ApiEntityParams( value = UserTpaInvite.class, props={ }, remark = "第三方邀请加入流水表，send_branch_id+join_userid唯一索引", paramType = "body" )
	@ApiResponses({
			@ApiResponse(code = 200,response=UserTpaInvite.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields( @ApiIgnore @RequestBody Map<String,Object> params) {
        try{
            User user= LoginUtils.getCurrentUserInfo();
            userTpaInviteService.editSomeFields(params);
            return Result.ok("edit-ok","更新成功");
        }catch (BizException e) {
            logger.error("",e);
            return Result.error(e);
        }catch (Exception e) {
            logger.error("",e);
            return Result.error(e);
        }
	}

	@ApiOperation( value = "第三方邀请加入流水表，send_branch_id+join_userid唯一索引-批量删除",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelUserTpaInvite(@RequestBody List<UserTpaInvite> userTpaInvites) {
	    User user= LoginUtils.getCurrentUserInfo();
        try{ 
            if(userTpaInvites.size()<=0){
                return Result.error("userTpaInvite-batchDel-data-err-0","请上送待删除数据列表");
            }
             List<UserTpaInvite> datasDb=userTpaInviteService.listByIds(userTpaInvites.stream().map(i-> i.getInviteId() ).collect(Collectors.toList()));

            List<UserTpaInvite> can=new ArrayList<>();
            List<UserTpaInvite> no=new ArrayList<>();
            for (UserTpaInvite data : datasDb) {
                if(true){
                    can.add(data);
                }else{
                    no.add(data);
                } 
            }
            List<String> msgs=new ArrayList<>();
            if(can.size()>0){
                userTpaInviteService.removeByIds(can);
                msgs.add(LangTips.transMsg("del-ok-num","成功删除%s条数据.",can.size()));
            }
    
            if(no.size()>0){ 
                msgs.add(LangTips.transMsg("not-allow-del-num","以下%s条数据不能删除:【%s】",no.size(),no.stream().map(i-> i.getInviteId() ).collect(Collectors.joining(","))));
            }
            if(can.size()>0){
                 return Result.ok(msgs.stream().collect(Collectors.joining()));
            }else {
                return Result.error(msgs.stream().collect(Collectors.joining()));
            } 
        }catch (BizException e) { 
           return Result.error(e);
        }catch (Exception e) {
            return Result.error(e);
        }


	} 

	@ApiOperation( value = "第三方邀请加入流水表，send_branch_id+join_userid唯一索引-根据主键查询一条数据",notes=" ")
     @ApiResponses({
            @ApiResponse(code = 200,response=UserTpaInvite.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
    })
    @RequestMapping(value="/queryById",method=RequestMethod.GET)
    public Result queryById(UserTpaInvite userTpaInvite) {
        UserTpaInvite data = (UserTpaInvite) userTpaInviteService.getById(userTpaInvite);
        return Result.ok().setData(data);
    }
	/**
	 * 检查checkInviteId是否存在
	 * @return
	 */
	@RequestMapping(value = "/checkInviteId",method=RequestMethod.GET)
	public Result checkInviteId(UserTpaInvite userTpaInvite){
		if(ObjectTools.isNotEmpty(userTpaInvite.getInviteId())){
			UserTpaInvite data=inviteCacheService.getInvite(userTpaInvite.getInviteId());
			if(data==null){
				data =  userTpaInviteService.getById(userTpaInvite);
				inviteCacheService.setInvite(userTpaInvite.getInviteId(), data);
			}

			if(data==null){
				return Result.error("inviteId-not-exists","邀请码已无效");
			}else{
				return Result.ok().setData(data);
			}
		}else{
			return Result.error("inviteId-required","请上送邀请码");
		}
	}
	/**
	 * 1.企业发送指定的账户二维码给用户，让其扫码绑定账户
	 * 2.
	 * @return
	 */
	@PostMapping(value = "/createInviteId")
	@ResponseBody  public Result createInviteId(@RequestBody UserTpaInvite invite){
		if(ObjectTools.isEmpty(invite.getInviteType())){
			return Result.error("invite-type-required","邀请类型必填");
		}
		if(ObjectTools.isEmpty(invite.getInviteScene())){
			return Result.error("invite-scene-required","邀请场景必填");
		}
		if("2".equals(invite.getInviteScene()) && ObjectTools.isEmpty(invite.getJoinUserid())){
			return Result.error("joinUserid-required","请指定绑定的账户");
		}
		User user=LoginUtils.getCurrentUserInfo();
		QueryWrapper<UserTpaInvite> qw=QueryTools.initQueryWrapper(UserTpaInvite.class);
		UserTpaInvite inviteDb=null;
		String inviteId=null;
		Branch branchDb=null;
		if("3".equals(invite.getInviteScene())){//invite all
			qw.eq("send_branch_id",user.getBranchId()).eq("send_userid",user.getUserid());
			qw.eq("invite_type",invite.getInviteType());
			qw.eq("invite_scene","3");
			inviteDb=userTpaInviteService.getOne(qw);
			inviteId=inviteCacheService.createInviteId("invite");
		}else if("2".equals(invite.getInviteScene())){//bind joinUserid
			qw.eq("send_branch_id",user.getBranchId()).eq("send_userid",user.getUserid());
			qw.eq("invite_type",invite.getInviteType());
			qw.eq("invite_scene","2");
			qw.eq("join_userid",invite.getJoinUserid());
			inviteDb=userTpaInviteService.getOne(qw);
			inviteId=inviteCacheService.createInviteId("bind");
		}else if("4".equals(invite.getInviteScene())){//claim 企业申领
			branchDb=branchService.getById(invite.getSendBranchId());
			if(branchDb==null){
				return Result.error("branch-id-not-exists","企业不存在");
			}
			if(ObjectTools.isNotEmpty(branchDb.getClaimStatus()) && !"0".equals(branchDb.getClaimStatus())){
				return Result.error("branch-had-claim","企业已申领");
			}
			qw.eq("send_branch_id",branchDb.getId()).eq("send_userid",branchDb.getId());

			qw.eq("invite_scene","4");
			qw.eq("join_userid",user.getUserid());
			inviteDb=userTpaInviteService.getOne(qw);
			inviteId=inviteCacheService.createInviteId("claim");
		}
		if(inviteDb==null) {
			if("4".equals(invite.getInviteScene())){
				if(branchDb==null){
					return Result.error("branch-id-not-exists","企业不存在");
				}
				invite.setSendBranchName(branchDb.getBranchName());
				invite.setSendUserid(branchDb.getId());
				invite.setSendUsername(branchDb.getBranchName());
				invite.setInviteType(invite.getInviteType());
				invite.setJoinUsername(user.getUsername());
				invite.setJoinUserid(user.getUserid());
				invite.setInviteState("1");
				invite.setInviteScene(invite.getInviteScene());
				invite.setSendTime(new Date());
				invite.setInviteId(inviteId);
			}else{
				invite.setSendBranchId(user.getBranchId());
				invite.setSendBranchName(user.getBranchName());
				invite.setSendUserid(user.getUserid());
				invite.setSendUsername(user.getUsername());
				invite.setInviteType(invite.getInviteType());
				invite.setInviteState("1");
				invite.setInviteScene(invite.getInviteScene());
				invite.setSendTime(new Date());
				invite.setInviteId(inviteId);
			}

			userTpaInviteService.save(invite);
			inviteCacheService.setInvite(inviteId,invite);
		}else{
			invite=inviteDb;
			inviteCacheService.setInvite(invite.getInviteId(),invite);		}
		return Result.ok().setData(invite);
	}

}
