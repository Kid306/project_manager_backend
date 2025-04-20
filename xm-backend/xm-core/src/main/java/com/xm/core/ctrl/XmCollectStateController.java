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

import com.xm.core.service.XmCollectStateService;
import com.xm.core.entity.XmCollectState;
/**
 * @author 唛盟开源 code-gen
 * @since 2025-3-22
 */
@RestController
@RequestMapping(value="/xm/core/xmCollectState")
@Api(tags={"项目集指标日统计表-操作接口"})
public class XmCollectStateController {
	
	static Logger logger =LoggerFactory.getLogger(XmCollectStateController.class);
	
	@Autowired
	private XmCollectStateService xmCollectStateService;

	@Operation( summary =  "项目集指标日统计表-查询列表")
	@ApiEntityParams(XmCollectState.class)
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listXmCollectState(  @RequestParam Map<String,Object> params){
			User user=LoginUtils.getCurrentUserInfo();
			QueryWrapper<XmCollectState> qw = QueryTools.initQueryWrapper(XmCollectState.class , params);
			IPage page=QueryTools.initPage(params);
			List<Map<String,Object>> datas = xmCollectStateService.selectListMapByWhere(page,qw,params);
			return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());
	}
	

	@Operation( summary =  "项目集指标日统计表-新增")
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addXmCollectState(@RequestBody XmCollectState xmCollectState) {
		 xmCollectStateService.save(xmCollectState);
         return Result.ok("add-ok","添加成功！").setData(xmCollectState);
	}

	@Operation( summary =  "项目集指标日统计表-删除")
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delXmCollectState(@RequestBody XmCollectState xmCollectState){
		xmCollectStateService.removeById(xmCollectState);
        return Result.ok("del-ok","删除成功！");
	}

	@Operation( summary =  "项目集指标日统计表-修改")
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editXmCollectState(@RequestBody XmCollectState xmCollectState) {
		xmCollectStateService.updateById(xmCollectState);
        return Result.ok("edit-ok","修改成功！");
	}

    @Operation( summary =  "项目集指标日统计表-批量修改某些字段" )
    @ApiEntityParams( value = XmCollectState.class, props={ }, remark = "项目集指标日统计表", paramType = "body" )
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields(   @RequestBody Map<String,Object> params) {
            User user= LoginUtils.getCurrentUserInfo();
            xmCollectStateService.editSomeFields(params);
            return Result.ok("edit-ok","更新成功");
	}

	@Operation( summary =  "项目集指标日统计表-批量删除")
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelXmCollectState(@RequestBody List<XmCollectState> xmCollectStates) {
	    User user= LoginUtils.getCurrentUserInfo();
        if(xmCollectStates.size()<=0){
            return Result.error("batchDel-data-err-0","请上送待删除数据列表");
        }
         List<XmCollectState> datasDb=xmCollectStateService.listByIds(xmCollectStates.stream().map(i-> i.getCollectId() ).collect(Collectors.toList()));

        List<XmCollectState> can=new ArrayList<>();
        List<XmCollectState> no=new ArrayList<>();
        for (XmCollectState data : datasDb) {
            if(true){
                can.add(data);
            }else{
                no.add(data);
            }
        }
        List<String> msgs=new ArrayList<>();
        if(can.size()>0){
            xmCollectStateService.removeByIds(can);
            msgs.add(LangTips.transMsg("del-ok-num","成功删除%s条数据.",can.size()));
        }

        if(no.size()>0){
            msgs.add(LangTips.transMsg("not-allow-del-num","以下%s条数据不能删除:【%s】",no.size(),no.stream().map(i-> i.getCollectId() ).collect(Collectors.joining(","))));
        }
        if(can.size()>0){
             return Result.ok(msgs.stream().collect(Collectors.joining()));
        }else {
            return Result.error(msgs.stream().collect(Collectors.joining()));
        }
	} 

	@Operation( summary =  "项目集指标日统计表-根据主键查询一条数据")
    @RequestMapping(value="/queryById",method=RequestMethod.GET)
    public Result queryById(XmCollectState xmCollectState) {
        XmCollectState data = (XmCollectState) xmCollectStateService.getById(xmCollectState);
        return Result.ok().setData(data);
    }

}
