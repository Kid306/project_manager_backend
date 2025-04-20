package com.xm.core.ctrl;

import java.util.*;
import java.util.stream.Collectors;

import com.xm.core.entity.XmKpi;
import com.xm.core.service.ChangeLogService;
import com.xm.core.service.XmRecordService;
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

import com.xm.core.service.XmRiskService;
import com.xm.core.entity.XmRisk;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author 唛盟开源 code-gen
 * @since 2025-4-10
 */
@RestController
@RequestMapping(value="/xm/core/xmRisk")
@Api(tags={"项目风险管理-操作接口"})
public class XmRiskController {
	
	static Logger logger =LoggerFactory.getLogger(XmRiskController.class);
	
	@Autowired
	private XmRiskService xmRiskService;
    @Autowired
    private XmRecordService recordService;

	@Operation( summary =  "项目风险管理-查询列表")
	@ApiEntityParams(XmRisk.class)
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listXmRisk(  @RequestParam Map<String,Object> params){
			User user=LoginUtils.getCurrentUserInfo();
			QueryWrapper<XmRisk> qw = QueryTools.initQueryWrapper(XmRisk.class , params);
			IPage page=QueryTools.initPage(params);
			List<Map<String,Object>> datas = xmRiskService.selectListMapByWhere(page,qw,params);
			return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());
	}
	

	@Operation( summary =  "项目风险管理-新增")
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addXmRisk(@RequestBody XmRisk xmRisk) {
		 xmRiskService.save(xmRisk);
        recordService.addXmRiskRecord(xmRisk.getDeptid(),xmRisk.getProjectId(),xmRisk.getId(),"风险管理-新增",String.format("新增风险【%s】",xmRisk.getName()));

        return Result.ok("add-ok","添加成功！").setData(xmRisk);
	}

	@Operation( summary =  "项目风险管理-删除")
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delXmRisk(@RequestBody XmRisk xmRisk){
        xmRisk=xmRiskService.getById(xmRisk.getId());
		xmRiskService.removeById(xmRisk.getId());
        recordService.addXmRiskRecord(xmRisk.getDeptid(),xmRisk.getProjectId(),xmRisk.getId(),"风险管理-删除",String.format("删除风险【%s】",xmRisk.getName()));
        return Result.ok("del-ok","删除成功！");
	}

	@Operation( summary =  "项目风险管理-修改")
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editXmRisk(@RequestBody XmRisk xmRisk) {
		xmRiskService.updateById(xmRisk);
        return Result.ok("edit-ok","修改成功！");
	}

    @Operation( summary =  "项目风险管理-批量修改某些字段" )
    @ApiEntityParams( value = XmRisk.class, props={ }, remark = "项目风险管理", paramType = "body" )
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields(   @RequestBody Map<String,Object> params) {
            User user= LoginUtils.getCurrentUserInfo();
            List<String> ids= (List<String>) params.get("$pks");
            List<XmRisk> xmRisks=this.xmRiskService.listByIds(ids);
            xmRiskService.editSomeFields(params);
            for (XmRisk xmRisk : xmRisks) {
                recordService.addXmRiskRecord(xmRisk.getDeptid(),xmRisk.getProjectId(),xmRisk.getId(),"风险-批量修改", "修改风险:"+ChangeLogService.getChangeLogsString(params,xmRisk));
            }

            return Result.ok("edit-ok","更新成功");
	}

	@Operation( summary =  "项目风险管理-批量删除")
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelXmRisk(@RequestBody List<XmRisk> xmRisks) {
	    User user= LoginUtils.getCurrentUserInfo();
        if(xmRisks.size()<=0){
            return Result.error("batchDel-data-err-0","请上送待删除数据列表");
        }
         List<XmRisk> datasDb=xmRiskService.listByIds(xmRisks.stream().map(i-> i.getId() ).collect(Collectors.toList()));

        List<XmRisk> can=new ArrayList<>();
        List<XmRisk> no=new ArrayList<>();
        for (XmRisk data : datasDb) {
            if(true){
                can.add(data);
            }else{
                no.add(data);
            }
        }
        List<String> msgs=new ArrayList<>();
        if(can.size()>0){
            xmRiskService.removeByIds(can);
            msgs.add(LangTips.transMsg("del-ok-num","成功删除%s条数据.",can.size()));
        }

        if(no.size()>0){
            msgs.add(LangTips.transMsg("not-allow-del-num","以下%s条数据不能删除:【%s】",no.size(),no.stream().map(i-> i.getId() ).collect(Collectors.joining(","))));
        }
        if(can.size()>0){
            for (XmRisk xmRisk : can) {
                recordService.addXmRiskRecord(xmRisk.getDeptid(),xmRisk.getProjectId(),xmRisk.getId(),"风险-批量删除","删除风险");
            }
             return Result.ok(msgs.stream().collect(Collectors.joining()));
        }else {
            return Result.error(msgs.stream().collect(Collectors.joining()));
        }
	}


    @RequestMapping(value = "/getXmRiskAttDist", method = RequestMethod.GET)
    public Result getXmRiskAttDist(@ApiIgnore @RequestParam Map<String, Object> params) {
        IPage page = QueryTools.initPage(params);
        QueryWrapper<XmRisk> qw = QueryTools.initQueryWrapper(XmRisk.class, params);
        List<Map<String, Object>> datas = this.xmRiskService.getXmRiskAttDist(page, qw, params);
        return Result.ok("ok", "成功").setData(datas).setTotal(page.getTotal());
    }

	@Operation( summary =  "项目风险管理-根据主键查询一条数据")
    @RequestMapping(value="/queryById",method=RequestMethod.GET)
    public Result queryById(XmRisk xmRisk) {
        XmRisk data = (XmRisk) xmRiskService.getById(xmRisk);
        return Result.ok().setData(data);
    }

}
