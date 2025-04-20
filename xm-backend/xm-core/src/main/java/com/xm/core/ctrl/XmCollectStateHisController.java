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

import com.xm.core.service.XmCollectStateHisService;
import com.xm.core.entity.XmCollectStateHis;
/**
 * @author 唛盟开源 code-gen
 * @since 2025-3-22
 */
@RestController
@RequestMapping(value="/xm/core/xmCollectStateHis")
@Api(tags={"项目集指标日统计历史表-操作接口"})
public class XmCollectStateHisController {
	
	static Logger logger =LoggerFactory.getLogger(XmCollectStateHisController.class);
	
	@Autowired
	private XmCollectStateHisService xmCollectStateHisService;

	@Operation( summary =  "项目集指标日统计历史表-查询列表")
	@ApiEntityParams(XmCollectStateHis.class)
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listXmCollectStateHis(  @RequestParam Map<String,Object> params){
			User user=LoginUtils.getCurrentUserInfo();
			QueryWrapper<XmCollectStateHis> qw = QueryTools.initQueryWrapper(XmCollectStateHis.class , params);
			IPage page=QueryTools.initPage(params);
			List<Map<String,Object>> datas = xmCollectStateHisService.selectListMapByWhere(page,qw,params);
			return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());
	}
	

	@Operation( summary =  "项目集指标日统计历史表-新增")
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addXmCollectStateHis(@RequestBody XmCollectStateHis xmCollectStateHis) {
		 xmCollectStateHisService.save(xmCollectStateHis);
         return Result.ok("add-ok","添加成功！").setData(xmCollectStateHis);
	}

	@Operation( summary =  "项目集指标日统计历史表-删除")
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delXmCollectStateHis(@RequestBody XmCollectStateHis xmCollectStateHis){
		xmCollectStateHisService.removeById(xmCollectStateHis);
        return Result.ok("del-ok","删除成功！");
	}

	@Operation( summary =  "项目集指标日统计历史表-修改")
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editXmCollectStateHis(@RequestBody XmCollectStateHis xmCollectStateHis) {
		xmCollectStateHisService.updateById(xmCollectStateHis);
        return Result.ok("edit-ok","修改成功！");
	}

    @Operation( summary =  "项目集指标日统计历史表-批量修改某些字段" )
    @ApiEntityParams( value = XmCollectStateHis.class, props={ }, remark = "项目集指标日统计历史表", paramType = "body" )
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields(   @RequestBody Map<String,Object> params) {
            User user= LoginUtils.getCurrentUserInfo();
            xmCollectStateHisService.editSomeFields(params);
            return Result.ok("edit-ok","更新成功");
	}

	@Operation( summary =  "项目集指标日统计历史表-批量删除")
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelXmCollectStateHis(@RequestBody List<XmCollectStateHis> xmCollectStateHiss) {
	    User user= LoginUtils.getCurrentUserInfo();
        if(xmCollectStateHiss.size()<=0){
            return Result.error("batchDel-data-err-0","请上送待删除数据列表");
        }
         List<XmCollectStateHis> datasDb=xmCollectStateHisService.listByIds(xmCollectStateHiss.stream().map(i->map( "collectId",i.getCollectId() ,  "bizDate",i.getBizDate() )).collect(Collectors.toList()));

        List<XmCollectStateHis> can=new ArrayList<>();
        List<XmCollectStateHis> no=new ArrayList<>();
        for (XmCollectStateHis data : datasDb) {
            if(true){
                can.add(data);
            }else{
                no.add(data);
            }
        }
        List<String> msgs=new ArrayList<>();
        if(can.size()>0){
            xmCollectStateHisService.removeByIds(can);
            msgs.add(LangTips.transMsg("del-ok-num","成功删除%s条数据.",can.size()));
        }

        if(no.size()>0){
            msgs.add(LangTips.transMsg("not-allow-del-num","以下%s条数据不能删除:【%s】",no.size(),no.stream().map(i-> i.getCollectId() +" "+ i.getBizDate() ).collect(Collectors.joining(","))));
        }
        if(can.size()>0){
             return Result.ok(msgs.stream().collect(Collectors.joining()));
        }else {
            return Result.error(msgs.stream().collect(Collectors.joining()));
        }
	} 

	@Operation( summary =  "项目集指标日统计历史表-根据主键查询一条数据")
    @RequestMapping(value="/queryById",method=RequestMethod.GET)
    public Result queryById(XmCollectStateHis xmCollectStateHis) {
        XmCollectStateHis data = (XmCollectStateHis) xmCollectStateHisService.getById(xmCollectStateHis);
        return Result.ok().setData(data);
    }

}
