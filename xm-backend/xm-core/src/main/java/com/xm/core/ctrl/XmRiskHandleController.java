package com.xm.core.ctrl;

import java.util.*;
import java.util.stream.Collectors;

import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.query.QueryTools;
import static com.mdp.core.utils.BaseUtils.*;
import com.mdp.core.entity.LangTips;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.swagger.ApiEntityParams;

import com.xm.core.service.XmRiskHandleService;
import com.xm.core.entity.XmRiskHandle;
/**
 * @author 唛盟开源 code-gen
 * @since 2025-4-10
 */
@RestController
@RequestMapping(value="/xm/core/xmRiskHandle")
@Api(tags={"风险处理进展情况-操作接口"})
public class XmRiskHandleController {
	
	static Logger logger =LoggerFactory.getLogger(XmRiskHandleController.class);
	
	@Autowired
	private XmRiskHandleService xmRiskHandleService;

	@Operation( summary =  "风险处理进展情况-查询列表")
	@ApiEntityParams(XmRiskHandle.class)
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listXmRiskHandle(  @RequestParam Map<String,Object> params){
			User user=LoginUtils.getCurrentUserInfo();
			QueryWrapper<XmRiskHandle> qw = QueryTools.initQueryWrapper(XmRiskHandle.class , params);
			IPage page=QueryTools.initPage(params);
			List<Map<String,Object>> datas = xmRiskHandleService.selectListMapByWhere(page,qw,params);
			return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());
	}
	

	@Operation( summary =  "风险处理进展情况-新增")
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addXmRiskHandle(@RequestBody XmRiskHandle xmRiskHandle) {
		 xmRiskHandleService.save(xmRiskHandle);
         return Result.ok("add-ok","添加成功！").setData(xmRiskHandle);
	}

	@Operation( summary =  "风险处理进展情况-删除")
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delXmRiskHandle(@RequestBody XmRiskHandle xmRiskHandle){
		xmRiskHandleService.removeById(xmRiskHandle);
        return Result.ok("del-ok","删除成功！");
	}

	@Operation( summary =  "风险处理进展情况-修改")
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editXmRiskHandle(@RequestBody XmRiskHandle xmRiskHandle) {
		xmRiskHandleService.updateById(xmRiskHandle);
        return Result.ok("edit-ok","修改成功！");
	}

    @Operation( summary =  "风险处理进展情况-批量修改某些字段" )
    @ApiEntityParams( value = XmRiskHandle.class, props={ }, remark = "风险处理进展情况", paramType = "body" )
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields(   @RequestBody Map<String,Object> params) {
            User user= LoginUtils.getCurrentUserInfo();
            xmRiskHandleService.editSomeFields(params);
            return Result.ok("edit-ok","更新成功");
	}

	@Operation( summary =  "风险处理进展情况-批量删除")
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelXmRiskHandle(@RequestBody List<XmRiskHandle> xmRiskHandles) {
	    User user= LoginUtils.getCurrentUserInfo();
        if(xmRiskHandles.size()<=0){
            return Result.error("batchDel-data-err-0","请上送待删除数据列表");
        }
         List<XmRiskHandle> datasDb=xmRiskHandleService.listByIds(xmRiskHandles.stream().map(i-> i.getId() ).collect(Collectors.toList()));

        List<XmRiskHandle> can=new ArrayList<>();
        List<XmRiskHandle> no=new ArrayList<>();
        for (XmRiskHandle data : datasDb) {
            if(true){
                can.add(data);
            }else{
                no.add(data);
            }
        }
        List<String> msgs=new ArrayList<>();
        if(can.size()>0){
            xmRiskHandleService.removeByIds(can);
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

	@Operation( summary =  "风险处理进展情况-根据主键查询一条数据")
    @RequestMapping(value="/queryById",method=RequestMethod.GET)
    public Result queryById(XmRiskHandle xmRiskHandle) {
        XmRiskHandle data = (XmRiskHandle) xmRiskHandleService.getById(xmRiskHandle);
        return Result.ok().setData(data);
    }

}
