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
import com.mdp.sys.entity.UserValidCode;
import com.mdp.sys.service.UserValidCodeService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.mdp.core.utils.BaseUtils.toMap;

@RestController
@RequestMapping(value="/*/sys/userValidCode")
@Api(tags={"会员表（前端商城-操作接口"})
public class UserValidCodeController {
	
	static Logger logger =LoggerFactory.getLogger(UserValidCodeController.class);
	
	@Autowired
	private UserValidCodeService userValidCodeService;
	 

	Map<String,Object> fieldsMap = toMap(new UserValidCode());
 
	
	@ApiOperation( value = "会员表（前端商城-查询列表",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=UserValidCode.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listUserValidCode(@ApiIgnore @RequestParam Map<String,Object>  params){
		try {
			User user=LoginUtils.getCurrentUserInfo();
			QueryWrapper<UserValidCode> qw = QueryTools.initQueryWrapper(UserValidCode.class , params);
			IPage page = QueryTools.initPage(params);
			Map<String,Object> ext=new HashMap<>();
			List<Map<String,Object>> datas = userValidCodeService.selectListMapByWhere(page,qw,ext);
			return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());
		}catch (BizException e) {
			return Result.error(e);
		}catch (Exception e) {
			return Result.error(e);
		}
	}
	

	@ApiOperation( value = "会员表（前端商城-新增",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=UserValidCode.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addUserValidCode(@RequestBody UserValidCode userValidCode) {
		 userValidCodeService.save(userValidCode);
         return Result.ok("add-ok","添加成功！");
	}

	@ApiOperation( value = "会员表（前端商城-删除",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delUserValidCode(@RequestBody UserValidCode userValidCode){
		userValidCodeService.removeById(userValidCode);
        return Result.ok("del-ok","删除成功！");
	}

	@ApiOperation( value = "会员表（前端商城-修改",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=UserValidCode.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editUserValidCode(@RequestBody UserValidCode userValidCode) {
		userValidCodeService.updateById(userValidCode);
        return Result.ok("edit-ok","修改成功！");
	}

    @ApiOperation( value = "会员表（前端商城-批量修改某些字段",notes="")
    @ApiEntityParams( value = UserValidCode.class, props={ }, remark = "会员表（前端商城", paramType = "body" )
	@ApiResponses({
			@ApiResponse(code = 200,response=UserValidCode.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields( @ApiIgnore @RequestBody Map<String,Object> userValidCodeMap) {
        try{
            User user= LoginUtils.getCurrentUserInfo();
            userValidCodeService.editSomeFields(userValidCodeMap);
            return Result.ok("edit-ok","更新成功");
        }catch (BizException e) {
            logger.error("",e);
            return Result.error(e);
        }catch (Exception e) {
            logger.error("",e);
            return Result.error(e);
        }
	}

	@ApiOperation( value = "会员表（前端商城-批量删除",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelUserValidCode(@RequestBody List<UserValidCode> userValidCodes) {
	    User user= LoginUtils.getCurrentUserInfo();
        try{ 
            if(userValidCodes.size()<=0){
                return Result.error("userValidCode-batchDel-data-err-0","请上送待删除数据列表");
            }
             List<UserValidCode> datasDb=userValidCodeService.listByIds(userValidCodes.stream().map(i-> i.getId() ).collect(Collectors.toList()));

            List<UserValidCode> can=new ArrayList<>();
            List<UserValidCode> no=new ArrayList<>();
            for (UserValidCode data : datasDb) {
                if(true){
                    can.add(data);
                }else{
                    no.add(data);
                } 
            }
            List<String> msgs=new ArrayList<>();
            if(can.size()>0){
                userValidCodeService.removeByIds(can);
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
        }catch (BizException e) { 
           return Result.error(e);
        }catch (Exception e) {
            return Result.error(e);
        }


	} 

	@ApiOperation( value = "会员表（前端商城-根据主键查询一条数据",notes=" ")
     @ApiResponses({
            @ApiResponse(code = 200,response=UserValidCode.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
    })
    @RequestMapping(value="/queryById",method=RequestMethod.GET)
    public Result queryById(UserValidCode userValidCode) {
        UserValidCode data = (UserValidCode) userValidCodeService.getById(userValidCode);
        return Result.ok().setData(data);
    }

}
