package com.mdp.sys.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.LangTips;
import com.mdp.core.entity.Result;
import com.mdp.core.err.BizException;
import com.mdp.core.query.QueryTools;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.swagger.ApiEntityParams;
import com.mdp.sys.entity.UserTpaInviteLink;
import com.mdp.sys.service.UserTpaInviteLinkService;
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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.mdp.core.utils.BaseUtils.map;
/**
 * @author maimeng-mdp code-gen
 * @since 2023-9-22
 */
@RestController
@RequestMapping(value="/mdp/sys/userTpaInviteLink")
@Api(tags={"邀请对象响应流水表-操作接口"})
public class UserTpaInviteLinkController {
	
	static Logger logger =LoggerFactory.getLogger(UserTpaInviteLinkController.class);
	
	@Autowired
	private UserTpaInviteLinkService userTpaInviteLinkService;

	@ApiOperation( value = "邀请对象响应流水表-查询列表",notes=" ")
	@ApiEntityParams(UserTpaInviteLink.class)
	@ApiResponses({
		@ApiResponse(code = 200,response=UserTpaInviteLink.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listUserTpaInviteLink(@ApiIgnore @RequestParam Map<String,Object> params){
		try {
			User user=LoginUtils.getCurrentUserInfo();
			QueryWrapper<UserTpaInviteLink> qw = QueryTools.initQueryWrapper(UserTpaInviteLink.class , params);
			IPage page=QueryTools.initPage(params);
			List<Map<String,Object>> datas = userTpaInviteLinkService.selectListMapByWhere(page,qw,params);
			return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());
		}catch (BizException e) {
			return Result.error(e);
		}catch (Exception e) {
			return Result.error(e);
		}
	}
	

	@ApiOperation( value = "邀请对象响应流水表-新增",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=UserTpaInviteLink.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addUserTpaInviteLink(@RequestBody UserTpaInviteLink userTpaInviteLink) {
		 userTpaInviteLinkService.save(userTpaInviteLink);
         return Result.ok("add-ok","添加成功！");
	}

	@ApiOperation( value = "邀请对象响应流水表-删除",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delUserTpaInviteLink(@RequestBody UserTpaInviteLink userTpaInviteLink){
		userTpaInviteLinkService.removeById(userTpaInviteLink);
        return Result.ok("del-ok","删除成功！");
	}

	@ApiOperation( value = "邀请对象响应流水表-修改",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=UserTpaInviteLink.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editUserTpaInviteLink(@RequestBody UserTpaInviteLink userTpaInviteLink) {
		userTpaInviteLinkService.updateById(userTpaInviteLink);
        return Result.ok("edit-ok","修改成功！");
	}

    @ApiOperation( value = "邀请对象响应流水表-批量修改某些字段",notes="")
    @ApiEntityParams( value = UserTpaInviteLink.class, props={ }, remark = "邀请对象响应流水表", paramType = "body" )
	@ApiResponses({
			@ApiResponse(code = 200,response=UserTpaInviteLink.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields( @ApiIgnore @RequestBody Map<String,Object> params) {
        try{
            User user= LoginUtils.getCurrentUserInfo();
            userTpaInviteLinkService.editSomeFields(params);
            return Result.ok("edit-ok","更新成功");
        }catch (BizException e) {
            logger.error("",e);
            return Result.error(e);
        }catch (Exception e) {
            logger.error("",e);
            return Result.error(e);
        }
	}

	@ApiOperation( value = "邀请对象响应流水表-批量删除",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelUserTpaInviteLink(@RequestBody List<UserTpaInviteLink> userTpaInviteLinks) {
	    User user= LoginUtils.getCurrentUserInfo();
        try{ 
            if(userTpaInviteLinks.size()<=0){
                return Result.error("userTpaInviteLink-batchDel-data-err-0","请上送待删除数据列表");
            }
             List<UserTpaInviteLink> datasDb=userTpaInviteLinkService.listByIds(userTpaInviteLinks.stream().map(i->map( "inviteId",i.getInviteId() ,  "joinUserid",i.getJoinUserid() ,  "joinTpaId",i.getJoinTpaId() )).collect(Collectors.toList()));

            List<UserTpaInviteLink> can=new ArrayList<>();
            List<UserTpaInviteLink> no=new ArrayList<>();
            for (UserTpaInviteLink data : datasDb) {
                if(true){
                    can.add(data);
                }else{
                    no.add(data);
                } 
            }
            List<String> msgs=new ArrayList<>();
            if(can.size()>0){
                userTpaInviteLinkService.removeByIds(can);
                msgs.add(LangTips.transMsg("del-ok-num","成功删除%s条数据.",can.size()));
            }
    
            if(no.size()>0){ 
                msgs.add(LangTips.transMsg("not-allow-del-num","以下%s条数据不能删除:【%s】",no.size(),no.stream().map(i-> i.getInviteId() +" "+ i.getJoinUserid() +" "+ i.getJoinTpaId() ).collect(Collectors.joining(","))));
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

	@ApiOperation( value = "邀请对象响应流水表-根据主键查询一条数据",notes=" ")
     @ApiResponses({
            @ApiResponse(code = 200,response=UserTpaInviteLink.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
    })
    @RequestMapping(value="/queryById",method=RequestMethod.GET)
    public Result queryById(UserTpaInviteLink userTpaInviteLink) {
        UserTpaInviteLink data = (UserTpaInviteLink) userTpaInviteLinkService.getById(userTpaInviteLink);
        return Result.ok().setData(data);
    }

}
