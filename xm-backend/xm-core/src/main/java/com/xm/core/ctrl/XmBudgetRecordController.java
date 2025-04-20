package com.xm.core.ctrl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.LangTips;
import com.mdp.core.entity.Result;
import com.mdp.core.query.QueryTools;
import com.mdp.core.utils.ObjectTools;
import com.mdp.core.valid.ValidUtil;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.swagger.ApiEntityParams;
import com.xm.core.entity.XmBudgetRecord;
import com.xm.core.service.XmBudgetRecordService;
import com.xm.core.service.XmCostRecordService;
import com.xm.core.service.XmGroupService;
import com.xm.core.vo.SplitBudgetVo;
import com.xm.tools.XmUtils;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 唛盟开源 code-gen
 * @since 2024-7-6
 */
@RestController
@RequestMapping(value="/xm/core/xmBudgetRecord")
@Api(tags={"项目人力成本预算-操作接口"})
public class XmBudgetRecordController {
	
	static Logger logger =LoggerFactory.getLogger(XmBudgetRecordController.class);


    @Autowired
    XmGroupService groupService;

	@Autowired
	private XmBudgetRecordService xmBudgetRecordService;

    @Autowired
    private XmCostRecordService xmCostRecordService;

	@Operation( summary =  "项目人力成本预算-查询列表")
	@ApiEntityParams(XmBudgetRecord.class)
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listXmBudgetRecord(  @RequestParam Map<String,Object> params){
			User user=LoginUtils.getCurrentUserInfo();
			QueryWrapper<XmBudgetRecord> qw = QueryTools.initQueryWrapper(XmBudgetRecord.class , params);
			IPage page=QueryTools.initPage(params);
			List<Map<String,Object>> datas = xmBudgetRecordService.selectListMapByWhere(page,qw,params);
			return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());
	}
	

	@Operation( summary =  "项目人力成本预算-新增")
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addXmBudgetRecord(@RequestBody XmBudgetRecord xmBudgetRecord) {
        User user=LoginUtils.getCurrentUserInfo();
        if(ObjectTools.isEmpty(xmBudgetRecord.getBizMonth())){
            xmBudgetRecord.setBizMonth(DateUtil.format(new Date(),"YYYY-MM"));
        }
        if(ObjectTools.isEmpty(xmBudgetRecord.getBizEdate())){
            xmBudgetRecord.setBizEdate(new Date());
        }
        xmBudgetRecord.setBranchId(user.getBranchId());

        if(ObjectTools.isEmpty(xmBudgetRecord.getCostCenterId())){
            xmBudgetRecord.setCostCenterId(user.getDeptid());
        }
        xmBudgetRecord.setCuserid(user.getUserid());
        xmBudgetRecord.setCusername(user.getUsername());
        xmBudgetRecord.setCtime(new Date());
        xmBudgetRecord.setLuserid(user.getUserid());
        xmBudgetRecord.setLusername(user.getUsername());
        xmBudgetRecord.setLtime(new Date());
		 xmBudgetRecordService.save(xmBudgetRecord);
         return Result.ok("add-ok","添加成功！");
	}
    @Operation( summary =  "项目人力成本预算-新增")
    @RequestMapping(value="/batchAdd",method=RequestMethod.POST)
    public Result addXmBudgetRecord(@RequestBody List<XmBudgetRecord> xmBudgetRecords) {
        User user=LoginUtils.getCurrentUserInfo();
        boolean isAdm=this.groupService.checkUserIsProjectAdm(xmBudgetRecords.get(0).getProjectId(),user.getUserid());
        if(!isAdm){
            return Result.error("not-pm","您不是项目管理员，无权限对预算进行更改");
        }
        for (XmBudgetRecord xmBudgetRecord : xmBudgetRecords) {
            xmBudgetRecord.setId(this.xmBudgetRecordService.createKey("id"));
            xmBudgetRecord.setBranchId(user.getBranchId());
        }
        xmBudgetRecordService.saveBatch(xmBudgetRecords);
        return Result.ok("add-ok","添加成功！");
    }
    @Operation( summary =  "拆分预算")
    @RequestMapping(value="/split",method=RequestMethod.POST)
    public Result splitXmBudgetLabor(@RequestBody SplitBudgetVo splitVo) {
        User user=LoginUtils.getCurrentUserInfo();
        XmBudgetRecord parentDb=this.xmBudgetRecordService.getById(splitVo.getParentId());
        if(parentDb==null){
            return Result.error("data-0","数据不存在");
        }

        boolean isAdm=this.groupService.checkUserIsProjectAdm(parentDb.getProjectId(),user.getUserid());
        if(!isAdm){
            return Result.error("not-pm","您不是项目管理员，无权限对预算进行拆分");
        }
        if(parentDb.getBudgetAt().compareTo(BigDecimal.ZERO)<=0){
            return Result.error("budget-at-zero-0","预算金额为0，不允许再拆分");
        }
        BigDecimal totalBudgetAt=splitVo.getSplits().stream().map(XmBudgetRecord::getBudgetAt).reduce(BigDecimal.ZERO,BigDecimal::add);

        if(XmUtils.gt(totalBudgetAt,parentDb.getBudgetAt(),2)){
            return Result.error("split-total-at-big-then-orign","拆分金额不能比原金额大");
        }
        for (XmBudgetRecord split : splitVo.getSplits()) {
            split.setBranchId(user.getBranchId());

            if(ObjectTools.isEmpty(split.getCostCenterId())){
                split.setCostCenterId(user.getDeptid());
            }
            split.setCuserid(user.getUserid());
            split.setCusername(user.getUsername());
            split.setCtime(new Date());
            split.setLuserid(user.getUserid());
            split.setLusername(user.getUsername());
            split.setLtime(new Date());
        }
        this.xmBudgetRecordService.split(parentDb,splitVo.getSplits());
        return Result.ok();

    }

	@Operation( summary =  "项目人力成本预算-删除")
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delXmBudgetRecord(@RequestBody XmBudgetRecord xmBudgetRecord){
		xmBudgetRecordService.removeById(xmBudgetRecord);
        return Result.ok("del-ok","删除成功！");
	}

	@Operation( summary =  "项目人力成本预算-修改")
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editXmBudgetRecord(@RequestBody XmBudgetRecord xmBudgetRecord) {
		xmBudgetRecordService.updateById(xmBudgetRecord);
        return Result.ok("edit-ok","修改成功！");
	}

    @Operation( summary =  "项目人力成本预算-批量修改某些字段" )
    @ApiEntityParams( value = XmBudgetRecord.class, props={ }, remark = "项目人力成本预算", paramType = "body" )
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields(   @RequestBody Map<String,Object> params) {
            User user= LoginUtils.getCurrentUserInfo();
            params.put("luserid",user.getUserid());
            params.put("lusername",user.getUsername());
            params.put("ltime",new Date());
            xmBudgetRecordService.editSomeFields(params);
            return Result.ok("edit-ok","更新成功");
	}

	@Operation( summary =  "项目人力成本预算-批量删除")
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelXmBudgetRecord(@RequestBody List<XmBudgetRecord> xmBudgetRecords) {
	    User user= LoginUtils.getCurrentUserInfo();
        if(xmBudgetRecords.size()<=0){
            return Result.error("batchDel-data-err-0","请上送待删除数据列表");
        }
         List<XmBudgetRecord> datasDb=xmBudgetRecordService.listByIds(xmBudgetRecords.stream().map(i-> i.getId() ).collect(Collectors.toList()));

        List<XmBudgetRecord> can=new ArrayList<>();
        List<XmBudgetRecord> no=new ArrayList<>();
        for (XmBudgetRecord data : datasDb) {
            if(true){
                can.add(data);
            }else{
                no.add(data);
            }
        }
        List<String> msgs=new ArrayList<>();
        if(can.size()>0){
            xmBudgetRecordService.removeByIds(can);
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

	@Operation( summary =  "项目人力成本预算-根据主键查询一条数据")
    @RequestMapping(value="/queryById",method=RequestMethod.GET)
    public Result queryById(XmBudgetRecord xmBudgetRecord) {
        XmBudgetRecord data = (XmBudgetRecord) xmBudgetRecordService.getById(xmBudgetRecord);
        return Result.ok().setData(data);
    }

    @RequestMapping(value="/listSum",method=RequestMethod.GET)
    public Result listSum(@ApiIgnore @RequestParam Map<String,Object> params){

        IPage page=QueryTools.initPage(params);
        params.put("branchId",LoginUtils.getCurrentUserInfo().getBranchId());
        ValidUtil.isRequired("groupBy","分组",params.get("groupBy"));
        ValidUtil.isRequired("startBizMonth","开始月份",params.get("startBizMonth"));
        ValidUtil.isRequired("endBizMonth","结束月份",params.get("endBizMonth"));
        List<Map<String,Object>>	data = this.xmBudgetRecordService.listSum(params);	//列出xmProjectMCostNouser列表
        String budgetCost= (String) params.get("budgetCost");
        Result result=Result.ok("query-ok","查询成功").setData(data);
        if("1".equals(budgetCost)){
            List<Map<String,Object>> costData=xmCostRecordService.listSum(params);
            result.put("costData",costData);
        }
        return result;
     }
}
