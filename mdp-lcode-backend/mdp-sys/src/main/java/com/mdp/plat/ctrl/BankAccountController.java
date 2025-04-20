package com.mdp.plat.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.LangTips;
import com.mdp.core.entity.Result;
import com.mdp.core.query.QueryTools;
import com.mdp.plat.entity.BankAccount;
import com.mdp.plat.service.BankAccountService;
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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.mdp.core.utils.BaseUtils.map;
/**
 * @author maimeng-mdp code-gen
 * @since 2024-5-15
 */
@RestController
@RequestMapping(value="/mdp/plat/bankAccount")
@Api(tags={"平台收付款账户-操作接口"})
public class BankAccountController {
	
	static Logger logger =LoggerFactory.getLogger(BankAccountController.class);
	
	@Autowired
	private BankAccountService bankAccountService;

	@ApiOperation( value = "平台收付款账户-查询列表",notes=" ")
	@ApiEntityParams(BankAccount.class)
	@ApiResponses({
		@ApiResponse(code = 200,response=BankAccount.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listBankAccount(@ApiIgnore @RequestParam Map<String,Object> params){
			User user=LoginUtils.getCurrentUserInfo();
			QueryWrapper<BankAccount> qw = QueryTools.initQueryWrapper(BankAccount.class , params);
			IPage page=QueryTools.initPage(params);
			List<Map<String,Object>> datas = bankAccountService.selectListMapByWhere(page,qw,params);
			return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());
	}
	

	@ApiOperation( value = "平台收付款账户-新增",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=BankAccount.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addBankAccount(@RequestBody BankAccount bankAccount) {
		 bankAccountService.save(bankAccount);
         return Result.ok("add-ok","添加成功！");
	}

	@ApiOperation( value = "平台收付款账户-删除",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delBankAccount(@RequestBody BankAccount bankAccount){
		bankAccountService.removeById(bankAccount);
        return Result.ok("del-ok","删除成功！");
	}

	@ApiOperation( value = "平台收付款账户-修改",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=BankAccount.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editBankAccount(@RequestBody BankAccount bankAccount) {
		bankAccountService.updateById(bankAccount);
        return Result.ok("edit-ok","修改成功！");
	}

    @ApiOperation( value = "平台收付款账户-批量修改某些字段",notes="")
    @ApiEntityParams( value = BankAccount.class, props={ }, remark = "平台收付款账户", paramType = "body" )
	@ApiResponses({
			@ApiResponse(code = 200,response=BankAccount.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields( @ApiIgnore @RequestBody Map<String,Object> params) {
            User user= LoginUtils.getCurrentUserInfo();
            bankAccountService.editSomeFields(params);
            return Result.ok("edit-ok","更新成功");
	}

	@ApiOperation( value = "平台收付款账户-批量删除",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelBankAccount(@RequestBody List<BankAccount> bankAccounts) {
	    User user= LoginUtils.getCurrentUserInfo();
        if(bankAccounts.size()<=0){
            return Result.error("batchDel-data-err-0","请上送待删除数据列表");
        }
         List<BankAccount> datasDb=bankAccountService.listByIds(bankAccounts.stream().map(i->map( "cardAccountId",i.getCardAccountId() ,  "platformId",i.getPlatformId() )).collect(Collectors.toList()));

        List<BankAccount> can=new ArrayList<>();
        List<BankAccount> no=new ArrayList<>();
        for (BankAccount data : datasDb) {
            if(true){
                can.add(data);
            }else{
                no.add(data);
            }
        }
        List<String> msgs=new ArrayList<>();
        if(can.size()>0){
            bankAccountService.removeByIds(can);
            msgs.add(LangTips.transMsg("del-ok-num","成功删除%s条数据.",can.size()));
        }

        if(no.size()>0){
            msgs.add(LangTips.transMsg("not-allow-del-num","以下%s条数据不能删除:【%s】",no.size(),no.stream().map(i-> i.getCardAccountId() +" "+ i.getPlatformId() ).collect(Collectors.joining(","))));
        }
        if(can.size()>0){
             return Result.ok(msgs.stream().collect(Collectors.joining()));
        }else {
            return Result.error(msgs.stream().collect(Collectors.joining()));
        }
	} 

	@ApiOperation( value = "平台收付款账户-根据主键查询一条数据",notes=" ")
     @ApiResponses({
            @ApiResponse(code = 200,response=BankAccount.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
    })
    @RequestMapping(value="/queryById",method=RequestMethod.GET)
    public Result queryById(BankAccount bankAccount) {
        BankAccount data = (BankAccount) bankAccountService.getById(bankAccount);
        return Result.ok().setData(data);
    }

}
