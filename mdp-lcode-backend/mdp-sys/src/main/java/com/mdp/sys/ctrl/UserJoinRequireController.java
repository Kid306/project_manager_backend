package com.mdp.sys.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.LangTips;
import com.mdp.core.entity.Result;
import com.mdp.core.query.QueryTools;
import com.mdp.core.valid.ValidUtil;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.swagger.ApiEntityParams;
import com.mdp.sys.entity.UserJoinRequire;
import com.mdp.sys.service.UserJoinRequireService;
import com.mdp.sys.service.UserService;
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
 * @since 2024-5-13
 */
@RestController
@RequestMapping(value="/mdp/sys/userJoinRequire")
@Api(tags={"企业入驻审核流程-操作接口"})
public class UserJoinRequireController {
	
	static Logger logger =LoggerFactory.getLogger(UserJoinRequireController.class);
	
	@Autowired
	private UserJoinRequireService userJoinRequireService;

	@Autowired
	private UserService userService;

	@ApiOperation( value = "企业入驻审核流程-查询列表",notes=" ")
	@ApiEntityParams(UserJoinRequire.class)
	@ApiResponses({
		@ApiResponse(code = 200,response=UserJoinRequire.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listUserJoinRequire(@ApiIgnore @RequestParam Map<String,Object> params){
			User user=LoginUtils.getCurrentUserInfo();
			QueryWrapper<UserJoinRequire> qw = QueryTools.initQueryWrapper(UserJoinRequire.class , params);
			IPage page=QueryTools.initPage(params);
			qw.and(true,qw2->{
				qw2.eq("join_branch_id",user.getBranchId());
				qw2.or().eq("join_userid",user.getUserid());
			});
			List<Map<String,Object>> datas = userJoinRequireService.selectListMapByWhere(page,qw,params);
			return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());
	}
	

	@ApiOperation( value = "企业入驻审核流程-新增",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=UserJoinRequire.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addUserJoinRequire(@RequestBody UserJoinRequire userJoinRequire) {
		ValidUtil.isRequired("joinBranchId","目标机构",userJoinRequire.getJoinBranchId());
		User user=LoginUtils.getCurrentUserInfo();
		UserJoinRequire cp=new UserJoinRequire();
		cp.setJoinUserid(user.getUserid());
		cp.setJoinBranchId(userJoinRequire.getJoinBranchId());
		if(this.userJoinRequireService.countByWhere(cp)>0){
			return Result.error("join-req-exists","申请已存在，请耐心等待目标机构管理员审核");
		}
		userJoinRequire.setJoinUserid(user.getUserid());
		userJoinRequire.setJoinUsername(user.getUsername());
		userJoinRequire.setId(this.userJoinRequireService.createKey("id"));
		userJoinRequire.setAgree("0");
		userJoinRequire.setJoinStatus("0");
		userJoinRequire.setBizFlowState("0");
		userJoinRequire.setCreateDate(new Date());
		userJoinRequireService.save(userJoinRequire);
         return Result.ok("add-ok","添加成功！");
	}

	@ApiOperation( value = "企业入驻审核流程-删除",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delUserJoinRequire(@RequestBody UserJoinRequire userJoinRequire){
		User user= LoginUtils.getCurrentUserInfo();
		UserJoinRequire data=this.userJoinRequireService.getById(userJoinRequire.getId());
		if(user.getUserid().equalsIgnoreCase(data.getJoinUserid())||LoginUtils.isBranchAdmin(data.getJoinBranchId())){
		}else{
			return Result.error("branch-adm-or-user-self","只有机构管理员或者申请者本人可以修改数据");
		}
		userJoinRequireService.removeById(userJoinRequire);
        return Result.ok("del-ok","删除成功！");
	}


    @ApiOperation( value = "企业入驻审核流程-批量修改某些字段",notes="")
    @ApiEntityParams( value = UserJoinRequire.class, props={ }, remark = "企业入驻审核流程", paramType = "body" )
	@ApiResponses({
			@ApiResponse(code = 200,response=UserJoinRequire.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields( @ApiIgnore @RequestBody Map<String,Object> params) {
            User user= LoginUtils.getCurrentUserInfo();
			List<String> pks= (List<String>) params.get("$pks");
			if(pks.size()<=0){
				return Result.error("$pks-required","请上送主键列表");
			}
			List<UserJoinRequire> datasDb=userJoinRequireService.listByIds(pks);
			String bizFlowState= (String) params.get("bizFlowState");
			List<UserJoinRequire> can=new ArrayList<>();
			List<UserJoinRequire> no=new ArrayList<>();
			for (UserJoinRequire data : datasDb) {
				if(user.getUserid().equalsIgnoreCase(data.getJoinUserid())||LoginUtils.isBranchAdmin(data.getJoinBranchId())){
					if("2".equalsIgnoreCase(bizFlowState)){
						if(data.getJoinUserid().equalsIgnoreCase(user.getUserid())){
							return Result.error("no-qx-edit","不能自己审核自己");
						}
					}
					can.add(data);
				}else{
					return Result.error("branch-adm-or-user-self","只有机构管理员或者申请者本人可以修改数据");
				}
			}
			if("2".equalsIgnoreCase(bizFlowState)){
				for (UserJoinRequire data : can) {
					com.mdp.sys.entity.User user2=new com.mdp.sys.entity.User();
					user2.setUserid(data.getJoinUserid());
					user2.setBranchId(data.getJoinBranchId());
					user2.setCpaOrg(data.getJoinBranchId());
					userService.updateById(user2,true);
				}
				params.put("agree","1");
				params.put("agreeDate",new Date());
			}
            userJoinRequireService.editSomeFields(params);
            return Result.ok("edit-ok","更新成功");
	}

	@ApiOperation( value = "企业入驻审核流程-批量删除",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelUserJoinRequire(@RequestBody List<UserJoinRequire> userJoinRequires) {
	    User user= LoginUtils.getCurrentUserInfo();
        if(userJoinRequires.size()<=0){
            return Result.error("batchDel-data-err-0","请上送待删除数据列表");
        }
         List<UserJoinRequire> datasDb=userJoinRequireService.listByIds(userJoinRequires.stream().map(i-> i.getId() ).collect(Collectors.toList()));

        List<UserJoinRequire> can=new ArrayList<>();
        List<UserJoinRequire> no=new ArrayList<>();
        for (UserJoinRequire data : datasDb) {
            if(user.getUserid().equalsIgnoreCase(data.getJoinUserid())||LoginUtils.isBranchAdmin(data.getJoinBranchId())){
                can.add(data);
            }else{
                no.add(data);

            }
        }
        List<String> msgs=new ArrayList<>();
        if(can.size()>0){
            userJoinRequireService.removeByIds(can);
            msgs.add(LangTips.transMsg("del-ok-num","成功删除%s条数据.",can.size()));
        }

        if(no.size()>0){
            msgs.add(LangTips.transMsg("not-allow-del-num","以下%s条数据不能删除:【%s】",no.size(),no.stream().map(i-> i.getId() ).collect(Collectors.joining(","))));
        }
        if(can.size()>0){
             return Result.ok(msgs.stream().collect(Collectors.joining()));
        }else {
            return Result.error(msgs.stream().collect(Collectors.joining()));
        }
	} 

	@ApiOperation( value = "企业入驻审核流程-根据主键查询一条数据",notes=" ")
     @ApiResponses({
            @ApiResponse(code = 200,response=UserJoinRequire.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
    })
    @RequestMapping(value="/queryById",method=RequestMethod.GET)
    public Result queryById(UserJoinRequire userJoinRequire) {
        UserJoinRequire data = (UserJoinRequire) userJoinRequireService.getById(userJoinRequire);
        return Result.ok().setData(data);
    }

}
