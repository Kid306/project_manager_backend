package com.xm.core.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.LangTips;
import com.mdp.core.entity.Result;
import com.mdp.core.query.QueryTools;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.swagger.ApiEntityParams;
import com.xm.core.entity.XmMenuExchange;
import com.xm.core.service.XmMenuExchangeService;
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
/**
 * @author maimeng-mdp code-gen
 * @since 2023-10-3
 */
@RestController
@RequestMapping(value="/xm/core/xmMenuExchange")
@Api(tags={"功能表-操作接口"})
public class XmMenuExchangeController {
	
	static Logger logger =LoggerFactory.getLogger(XmMenuExchangeController.class);
	
	@Autowired
	private XmMenuExchangeService xmMenuExchangeService;

	@ApiOperation( value = "功能表-查询列表",notes=" ")
	@ApiEntityParams(XmMenuExchange.class)
	@ApiResponses({
		@ApiResponse(code = 200,response=XmMenuExchange.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listXmMenuExchange(@ApiIgnore @RequestParam Map<String,Object> params){
			User user=LoginUtils.getCurrentUserInfo();
			QueryWrapper<XmMenuExchange> qw = QueryTools.initQueryWrapper(XmMenuExchange.class , params);
			IPage page=QueryTools.initPage(params);
			List<Map<String,Object>> datas = xmMenuExchangeService.selectListMapByWhere(page,qw,params);
			return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());
	}
	

	@ApiOperation( value = "功能表-新增",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmMenuExchange.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addXmMenuExchange(@RequestBody XmMenuExchange xmMenuExchange) {
		 xmMenuExchangeService.save(xmMenuExchange);
         return Result.ok("add-ok","添加成功！");
	}

	@ApiOperation( value = "功能表-删除",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delXmMenuExchange(@RequestBody XmMenuExchange xmMenuExchange){
		xmMenuExchangeService.removeById(xmMenuExchange);
        return Result.ok("del-ok","删除成功！");
	}

	@ApiOperation( value = "功能表-修改",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmMenuExchange.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editXmMenuExchange(@RequestBody XmMenuExchange xmMenuExchange) {
		xmMenuExchangeService.updateById(xmMenuExchange);
        return Result.ok("edit-ok","修改成功！");
	}

    @ApiOperation( value = "功能表-批量修改某些字段",notes="")
    @ApiEntityParams( value = XmMenuExchange.class, props={ }, remark = "功能表", paramType = "body" )
	@ApiResponses({
			@ApiResponse(code = 200,response=XmMenuExchange.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields( @ApiIgnore @RequestBody Map<String,Object> params) {
            User user= LoginUtils.getCurrentUserInfo();
            xmMenuExchangeService.editSomeFields(params);
            return Result.ok("edit-ok","更新成功");
	}

	@ApiOperation( value = "功能表-批量删除",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelXmMenuExchange(@RequestBody List<XmMenuExchange> xmMenuExchanges) {
	    User user= LoginUtils.getCurrentUserInfo();
        if(xmMenuExchanges.size()<=0){
            return Result.error("batchDel-data-err-0","请上送待删除数据列表");
        }
         List<XmMenuExchange> datasDb=xmMenuExchangeService.listByIds(xmMenuExchanges.stream().map(i-> i.getId() ).collect(Collectors.toList()));

        List<XmMenuExchange> can=new ArrayList<>();
        List<XmMenuExchange> no=new ArrayList<>();
        for (XmMenuExchange data : datasDb) {
            if(true){
                can.add(data);
            }else{
                no.add(data);
            }
        }
        List<String> msgs=new ArrayList<>();
        if(can.size()>0){
            xmMenuExchangeService.removeByIds(can);
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

	@ApiOperation( value = "功能表-根据主键查询一条数据",notes=" ")
     @ApiResponses({
            @ApiResponse(code = 200,response=XmMenuExchange.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
    })
    @RequestMapping(value="/queryById",method=RequestMethod.GET)
    public Result queryById(XmMenuExchange xmMenuExchange) {
        XmMenuExchange data = (XmMenuExchange) xmMenuExchangeService.getById(xmMenuExchange);
        return Result.ok().setData(data);
    }

}
