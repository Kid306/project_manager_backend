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

import com.xm.core.service.XmKpiHisService;
import com.xm.core.entity.XmKpiHis;
/**
 * @author 唛盟开源 code-gen
 * @since 2025-4-4
 */
@RestController
@RequestMapping(value="/xm/core/xmKpiHis")
@Api(tags={"项目或任务关键指标考核-操作接口"})
public class XmKpiHisController {
	
	static Logger logger =LoggerFactory.getLogger(XmKpiHisController.class);
	
	@Autowired
	private XmKpiHisService xmKpiHisService;

	@Operation( summary =  "项目或任务关键指标考核-查询列表")
	@ApiEntityParams(XmKpiHis.class)
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listXmKpiHis(  @RequestParam Map<String,Object> params){
			User user=LoginUtils.getCurrentUserInfo();
			QueryWrapper<XmKpiHis> qw = QueryTools.initQueryWrapper(XmKpiHis.class , params);
			IPage page=QueryTools.initPage(params);
			List<Map<String,Object>> datas = xmKpiHisService.selectListMapByWhere(page,qw,params);
			return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());
	}
	

	@Operation( summary =  "项目或任务关键指标考核-新增")
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addXmKpiHis(@RequestBody XmKpiHis xmKpiHis) {
		 xmKpiHisService.save(xmKpiHis);
         return Result.ok("add-ok","添加成功！").setData(xmKpiHis);
	}

	@Operation( summary =  "项目或任务关键指标考核-删除")
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delXmKpiHis(@RequestBody XmKpiHis xmKpiHis){
		xmKpiHisService.removeById(xmKpiHis);
        return Result.ok("del-ok","删除成功！");
	}

	@Operation( summary =  "项目或任务关键指标考核-修改")
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editXmKpiHis(@RequestBody XmKpiHis xmKpiHis) {
		xmKpiHisService.updateById(xmKpiHis);
        return Result.ok("edit-ok","修改成功！");
	}

    @Operation( summary =  "项目或任务关键指标考核-批量修改某些字段" )
    @ApiEntityParams( value = XmKpiHis.class, props={ }, remark = "项目或任务关键指标考核", paramType = "body" )
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields(   @RequestBody Map<String,Object> params) {
            User user= LoginUtils.getCurrentUserInfo();
            xmKpiHisService.editSomeFields(params);
            return Result.ok("edit-ok","更新成功");
	}

	@Operation( summary =  "项目或任务关键指标考核-批量删除")
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelXmKpiHis(@RequestBody List<XmKpiHis> xmKpiHiss) {
	    User user= LoginUtils.getCurrentUserInfo();
        if(xmKpiHiss.size()<=0){
            return Result.error("batchDel-data-err-0","请上送待删除数据列表");
        }
         List<XmKpiHis> datasDb=xmKpiHisService.listByIds(xmKpiHiss.stream().map(i->map( "id",i.getId() ,  "scoreDate",i.getScoreDate() )).collect(Collectors.toList()));

        List<XmKpiHis> can=new ArrayList<>();
        List<XmKpiHis> no=new ArrayList<>();
        for (XmKpiHis data : datasDb) {
            if(true){
                can.add(data);
            }else{
                no.add(data);
            }
        }
        List<String> msgs=new ArrayList<>();
        if(can.size()>0){
            xmKpiHisService.removeByIds(can);
            msgs.add(LangTips.transMsg("del-ok-num","成功删除%s条数据.",can.size()));
        }

        if(no.size()>0){
            msgs.add(LangTips.transMsg("not-allow-del-num","以下%s条数据不能删除:【%s】",no.size(),no.stream().map(i-> i.getId() +" "+ i.getScoreDate() ).collect(Collectors.joining(","))));
        }
        if(can.size()>0){
             return Result.ok(msgs.stream().collect(Collectors.joining()));
        }else {
            return Result.error(msgs.stream().collect(Collectors.joining()));
        }
	} 

	@Operation( summary =  "项目或任务关键指标考核-根据主键查询一条数据")
    @RequestMapping(value="/queryById",method=RequestMethod.GET)
    public Result queryById(XmKpiHis xmKpiHis) {
        XmKpiHis data = (XmKpiHis) xmKpiHisService.getById(xmKpiHis);
        return Result.ok().setData(data);
    }

}
