package com.xm.core.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.LangTips;
import com.mdp.core.entity.Result;
import com.mdp.core.query.QueryTools;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.swagger.ApiEntityParams;
import com.xm.core.entity.XmGroupStateHis;
import com.xm.core.service.XmGroupStateHisService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.mdp.core.utils.BaseUtils.map;
/**
 * @author 唛盟开源 code-gen
 * @since 2024-7-6
 */
@RestController
@RequestMapping(value="/xm/core/xmGroupStateHis")
@Api(tags={"项目指标日统计表-操作接口"})
public class XmGroupStateHisController {
	
	static Logger logger =LoggerFactory.getLogger(XmGroupStateHisController.class);
	
	@Autowired
	private XmGroupStateHisService xmGroupStateHisService;

	@Operation( summary =  "项目指标日统计表-查询列表")
	@ApiEntityParams(XmGroupStateHis.class)
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listXmGroupStateHis(  @RequestParam Map<String,Object> params){
			User user=LoginUtils.getCurrentUserInfo();
			QueryWrapper<XmGroupStateHis> qw = QueryTools.initQueryWrapper(XmGroupStateHis.class , params);
			IPage page=QueryTools.initPage(params);
			List<Map<String,Object>> datas = xmGroupStateHisService.selectListMapByWhere(page,qw,params);
			return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());
	}
	

	@Operation( summary =  "项目指标日统计表-新增")
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addXmGroupStateHis(@RequestBody XmGroupStateHis xmGroupStateHis) {
		 xmGroupStateHisService.save(xmGroupStateHis);
         return Result.ok("add-ok","添加成功！");
	}

	@Operation( summary =  "项目指标日统计表-删除")
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delXmGroupStateHis(@RequestBody XmGroupStateHis xmGroupStateHis){
		xmGroupStateHisService.removeById(xmGroupStateHis);
        return Result.ok("del-ok","删除成功！");
	}

	@Operation( summary =  "项目指标日统计表-修改")
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editXmGroupStateHis(@RequestBody XmGroupStateHis xmGroupStateHis) {
		xmGroupStateHisService.updateById(xmGroupStateHis);
        return Result.ok("edit-ok","修改成功！");
	}

    @Operation( summary =  "项目指标日统计表-批量修改某些字段" )
    @ApiEntityParams( value = XmGroupStateHis.class, props={ }, remark = "项目指标日统计表", paramType = "body" )
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields(   @RequestBody Map<String,Object> params) {
            User user= LoginUtils.getCurrentUserInfo();
            xmGroupStateHisService.editSomeFields(params);
            return Result.ok("edit-ok","更新成功");
	}

	@Operation( summary =  "项目指标日统计表-批量删除")
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelXmGroupStateHis(@RequestBody List<XmGroupStateHis> xmGroupStateHiss) {
	    User user= LoginUtils.getCurrentUserInfo();
        if(xmGroupStateHiss.size()<=0){
            return Result.error("batchDel-data-err-0","请上送待删除数据列表");
        }
         List<XmGroupStateHis> datasDb=xmGroupStateHisService.listByIds(xmGroupStateHiss.stream().map(i->map( "bizDate",i.getBizDate() ,  "groupId",i.getGroupId() )).collect(Collectors.toList()));

        List<XmGroupStateHis> can=new ArrayList<>();
        List<XmGroupStateHis> no=new ArrayList<>();
        for (XmGroupStateHis data : datasDb) {
            if(true){
                can.add(data);
            }else{
                no.add(data);
            }
        }
        List<String> msgs=new ArrayList<>();
        if(can.size()>0){
            xmGroupStateHisService.removeByIds(can);
            msgs.add(LangTips.transMsg("del-ok-num","成功删除%s条数据.",can.size()));
        }

        if(no.size()>0){
            msgs.add(LangTips.transMsg("not-allow-del-num","以下%s条数据不能删除:【%s】",no.size(),no.stream().map(i-> i.getBizDate() +" "+ i.getGroupId() ).collect(Collectors.joining(","))));
        }
        if(can.size()>0){
             return Result.ok(msgs.stream().collect(Collectors.joining()));
        }else {
            return Result.error(msgs.stream().collect(Collectors.joining()));
        }
	} 

	@Operation( summary =  "项目指标日统计表-根据主键查询一条数据")
    @RequestMapping(value="/queryById",method=RequestMethod.GET)
    public Result queryById(XmGroupStateHis xmGroupStateHis) {
        XmGroupStateHis data = (XmGroupStateHis) xmGroupStateHisService.getById(xmGroupStateHis);
        return Result.ok().setData(data);
    }

}
