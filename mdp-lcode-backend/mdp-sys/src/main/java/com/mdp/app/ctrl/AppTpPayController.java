package com.mdp.app.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.app.entity.AppTpPay;
import com.mdp.app.service.AppTpPayService;
import com.mdp.core.entity.LangTips;
import com.mdp.core.entity.Result;
import com.mdp.core.err.BizException;
import com.mdp.core.query.QueryTools;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.swagger.ApiEntityParams;
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
@RequestMapping(value="/*/app/appTpPay")
@Api(tags={"MDP平台应用与第三方支付关系表-操作接口"})
public class AppTpPayController {
	
	static Logger logger =LoggerFactory.getLogger(AppTpPayController.class);
	
	@Autowired
	private AppTpPayService appTpPayService;
	 

	Map<String,Object> fieldsMap = toMap(new AppTpPay());
 
	
	@ApiOperation( value = "MDP平台应用与第三方支付关系表-查询列表",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=AppTpPay.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listAppTpPay(@ApiIgnore @RequestParam Map<String,Object>  appTpPay){
		try {
			User user=LoginUtils.getCurrentUserInfo();
			QueryWrapper<AppTpPay> qw = QueryTools.initQueryWrapper(AppTpPay.class , appTpPay);
			IPage page = QueryTools.initPage(appTpPay);
			Map<String,Object> ext=new HashMap<>();
			List<Map<String,Object>> datas = appTpPayService.selectListMapByWhere(page,qw,ext);
			return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());
		}catch (BizException e) {
			return Result.error(e);
		}catch (Exception e) {
			return Result.error(e);
		}
	}
	

	@ApiOperation( value = "MDP平台应用与第三方支付关系表-新增",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=AppTpPay.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addAppTpPay(@RequestBody AppTpPay appTpPay) {
		 appTpPayService.save(appTpPay);
         return Result.ok("add-ok","添加成功！");
	}

	@ApiOperation( value = "MDP平台应用与第三方支付关系表-删除",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delAppTpPay(@RequestBody AppTpPay appTpPay){
		appTpPayService.removeById(appTpPay);
        return Result.ok("del-ok","删除成功！");
	}

	@ApiOperation( value = "MDP平台应用与第三方支付关系表-修改",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=AppTpPay.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editAppTpPay(@RequestBody AppTpPay appTpPay) {
		appTpPayService.updateById(appTpPay);
        return Result.ok("edit-ok","修改成功！");
	}

    @ApiOperation( value = "MDP平台应用与第三方支付关系表-批量修改某些字段",notes="")
    @ApiEntityParams( value = AppTpPay.class, props={ }, remark = "MDP平台应用与第三方支付关系表", paramType = "body" )
	@ApiResponses({
			@ApiResponse(code = 200,response=AppTpPay.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields( @ApiIgnore @RequestBody Map<String,Object> appTpPayMap) {
        try{
            User user= LoginUtils.getCurrentUserInfo();
            appTpPayService.editSomeFields(appTpPayMap);
            return Result.ok("edit-ok","更新成功");
        }catch (BizException e) {
            logger.error("",e);
            return Result.error(e);
        }catch (Exception e) {
            logger.error("",e);
            return Result.error(e);
        }
	}

	@ApiOperation( value = "MDP平台应用与第三方支付关系表-批量删除",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelAppTpPay(@RequestBody List<AppTpPay> appTpPays) {
	    User user= LoginUtils.getCurrentUserInfo();
        try{ 
            if(appTpPays.size()<=0){
                return Result.error("appTpPay-batchDel-data-err-0","请上送待删除数据列表");
            }
             List<AppTpPay> datasDb=appTpPayService.listByIds(appTpPays.stream().map(i-> i.getPayAuthId() ).collect(Collectors.toList()));

            List<AppTpPay> can=new ArrayList<>();
            List<AppTpPay> no=new ArrayList<>();
            for (AppTpPay data : datasDb) {
                if(true){
                    can.add(data);
                }else{
                    no.add(data);
                } 
            }
            List<String> msgs=new ArrayList<>();
            if(can.size()>0){
                appTpPayService.removeByIds(can);
                msgs.add(LangTips.transMsg("del-ok-num","成功删除%s条数据.",can.size()));
            }
    
            if(no.size()>0){ 
                msgs.add(LangTips.transMsg("not-allow-del-num","以下%s条数据不能删除:【%s】",no.size(),no.stream().map(i-> i.getPayAuthId() ).collect(Collectors.joining(","))));
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

	@ApiOperation( value = "MDP平台应用与第三方支付关系表-根据主键查询一条数据",notes=" ")
     @ApiResponses({
            @ApiResponse(code = 200,response=AppTpPay.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
    })
    @RequestMapping(value="/queryById",method=RequestMethod.GET)
    public Result queryById(AppTpPay appTpPay) {
        AppTpPay data = (AppTpPay) appTpPayService.getById(appTpPay);
        return Result.ok().setData(data);
    }

}
