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
import com.xm.core.entity.XmCostRecord;
import com.xm.core.service.XmBudgetRecordService;
import com.xm.core.service.XmCostRecordService;
import com.xm.core.service.XmGroupService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

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
@RequestMapping(value="/xm/core/xmCostRecord")
@Api(tags={"项目实际成本费用-操作接口"})
public class XmCostRecordController {
	
	static Logger logger =LoggerFactory.getLogger(XmCostRecordController.class);


    @Autowired
    XmGroupService groupService;

	@Autowired
	private XmCostRecordService xmCostRecordService;

    @Autowired
    XmBudgetRecordService xmBudgetRecordService;

	@Operation( summary =  "项目实际成本费用-查询列表")
	@ApiEntityParams(XmCostRecord.class)
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listXmCostRecord(  @RequestParam Map<String,Object> params){
			User user=LoginUtils.getCurrentUserInfo();
			QueryWrapper<XmCostRecord> qw = QueryTools.initQueryWrapper(XmCostRecord.class , params);
			IPage page=QueryTools.initPage(params);
			List<Map<String,Object>> datas = xmCostRecordService.selectListMapByWhere(page,qw,params);
			return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());
	}
	

	@Operation( summary =  "项目实际成本费用-新增")
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addXmCostRecord(@RequestBody XmCostRecord xmCostRecord) {
        User user=LoginUtils.getCurrentUserInfo();
        xmCostRecord.setBranchId(LoginUtils.getCurrentUserInfo().getBranchId());
        xmCostRecord.setCtime(new Date());

        if(ObjectTools.isEmpty(xmCostRecord.getBizDate())){
            xmCostRecord.setBizDate(DateUtil.format(new Date(),"YYYY-MM-DD"));
        }
        if(ObjectTools.isEmpty(xmCostRecord.getBizMonth())){
            xmCostRecord.setBizMonth(DateUtil.format(new Date(),"YYYY-MM"));
        }
        if(ObjectTools.isEmpty(xmCostRecord.getBizEdate())){
            xmCostRecord.setBizEdate(new Date());
        }
        if(ObjectTools.isEmpty(xmCostRecord.getCostCenterId())){
            xmCostRecord.setCostCenterId(user.getDeptid());
        }
        xmCostRecord.setCuserid(user.getUserid());
        xmCostRecord.setCusername(user.getUsername());
        xmCostRecord.setCtime(new Date());
        xmCostRecord.setLuserid(user.getUserid());
        xmCostRecord.setLusername(user.getUsername());
        xmCostRecord.setLtime(new Date());
		 xmCostRecordService.save(xmCostRecord);
         return Result.ok("add-ok","添加成功！");
	}

	@Operation( summary =  "项目实际成本费用-删除")
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delXmCostRecord(@RequestBody XmCostRecord xmCostRecord){
		xmCostRecordService.removeById(xmCostRecord);
        return Result.ok("del-ok","删除成功！");
	}

	@Operation( summary =  "项目实际成本费用-修改")
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editXmCostRecord(@RequestBody XmCostRecord xmCostRecord) {
		xmCostRecordService.updateById(xmCostRecord);
        return Result.ok("edit-ok","修改成功！");
	}

    @Operation( summary =  "项目实际成本费用-批量修改某些字段" )
    @ApiEntityParams( value = XmCostRecord.class, props={ }, remark = "项目实际成本费用", paramType = "body" )
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields(   @RequestBody Map<String,Object> params) {
            User user= LoginUtils.getCurrentUserInfo();
            params.put("luserid",user.getUserid());
            params.put("lusername",user.getUsername());
            params.put("ltime",new Date());
            xmCostRecordService.editSomeFields(params);
            return Result.ok("edit-ok","更新成功");
	}

	@Operation( summary =  "项目实际成本费用-批量删除")
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelXmCostRecord(@RequestBody List<XmCostRecord> xmCostRecords) {
	    User user= LoginUtils.getCurrentUserInfo();
        if(xmCostRecords.size()<=0){
            return Result.error("batchDel-data-err-0","请上送待删除数据列表");
        }
         List<XmCostRecord> datasDb=xmCostRecordService.listByIds(xmCostRecords.stream().map(i-> i.getId() ).collect(Collectors.toList()));

        List<XmCostRecord> can=new ArrayList<>();
        List<XmCostRecord> no=new ArrayList<>();
        for (XmCostRecord data : datasDb) {
            if(true){
                can.add(data);
            }else{
                no.add(data);
            }
        }
        List<String> msgs=new ArrayList<>();
        if(can.size()>0){
            xmCostRecordService.removeByIds(can);
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

	@Operation( summary =  "项目实际成本费用-根据主键查询一条数据")
    @RequestMapping(value="/queryById",method=RequestMethod.GET)
    public Result queryById(XmCostRecord xmCostRecord) {
        XmCostRecord data = (XmCostRecord) xmCostRecordService.getById(xmCostRecord);
        return Result.ok().setData(data);
    }
    @RequestMapping(value="/listSum",method=RequestMethod.GET)
    public Result listSum(@ApiIgnore @RequestParam Map<String,Object> params){

        IPage page=QueryTools.initPage(params);
        params.put("branchId",LoginUtils.getCurrentUserInfo().getBranchId());
        ValidUtil.isRequired("groupBy","分组",params.get("groupBy"));
        ValidUtil.isRequired("startBizMonth","开始月份",params.get("startBizMonth"));
        ValidUtil.isRequired("endBizMonth","结束月份",params.get("endBizMonth"));
        List<Map<String,Object>>	data = this.xmCostRecordService.listSum(params);	//列出xmProjectMCostNouser列表
        String budgetCost= (String) params.get("budgetCost");
        Result result=Result.ok("query-ok","查询成功").setData(data);
        if("1".equals(budgetCost)){
            List<Map<String,Object>> budgetData=xmBudgetRecordService.listSum(params);
            result.put("budgetData",budgetData);
        }
        return result;
     }
}
