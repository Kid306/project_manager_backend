package com.xm.core.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.query.QueryTools;
import com.mdp.core.utils.RequestUtils;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.swagger.ApiEntityParams;
import com.xm.core.entity.XmBranchStateHis;
import com.xm.core.service.XmBranchStateHisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;
/**
 * url编制采用rest风格,如对xm_branch_state_his 机构内所有项目指标汇总的操作有增删改查,对应的url分别为:<br>
 *  新增: core/xmBranchStateHis/add <br>
 *  查询: core/xmBranchStateHis/list<br>
 *  模糊查询: core/xmBranchStateHis/listKey<br>
 *  修改: core/xmBranchStateHis/edit <br>
 *  删除: core/xmBranchStateHis/del<br>
 *  批量删除: core/xmBranchStateHis/batchDel<br>
 * 组织 com  顶级模块 xm 大模块 core 小模块 <br>
 * 实体 XmBranchStateHis 表 xm_branch_state_his 当前主键(包括多主键): biz_date,branch_id; 
 ***/
@RestController("xm.core.xmBranchStateHisController")
@RequestMapping(value="/xm/core/xmBranchStateHis")
@Api(tags={"机构内所有项目指标汇总操作接口"})
public class XmBranchStateHisController {
	
	static Logger logger =LoggerFactory.getLogger(XmBranchStateHisController.class);
	
	@Autowired
	private XmBranchStateHisService xmBranchStateHisService;
	 
		
 
	
	@ApiOperation( value = "查询机构内所有项目指标汇总信息列表",notes=" ")
	@ApiEntityParams(XmBranchStateHis.class)
	@ApiResponses({
		@ApiResponse(code = 200,response=XmBranchStateHis.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listXmBranchStateHis(@ApiIgnore @RequestParam Map<String,Object> params){
		
		
		RequestUtils.transformArray(params, "$pks");
		IPage page=QueryTools.initPage(params);
		User user=LoginUtils.getCurrentUserInfo();
		params.put("branchId",user.getBranchId());
		QueryWrapper<XmBranchStateHis> qw = QueryTools.initQueryWrapper(XmBranchStateHis.class , params);
		List<Map<String,Object>> datas = xmBranchStateHisService.selectListMapByWhere(page,qw,params);
			return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());	//列出XmBranchStateHis列表

	}

	@ApiOperation( value = "查询机构内所有项目指标汇总信息列表5日内的",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200,response=XmBranchStateHis.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/listXmBranchFiveDayTaskCnt",method=RequestMethod.GET)
	public Result listXmBranchFiveDayTaskCnt(@ApiIgnore @RequestParam Map<String,Object> params){
		
		
		User user= LoginUtils.getCurrentUserInfo();
		params.put("branchId",user.getBranchId());
		List<Map<String,Object>>	datas = xmBranchStateHisService.listXmBranchFiveDayTaskCnt(params);	//列出XmBranchStateHis列表
		return Result.ok("query-ok","查询成功").setData(datas);	//列出XmBranchStateHis列表
	}

 
	
	/**
	@ApiOperation( value = "新增一条机构内所有项目指标汇总信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmBranchStateHis.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addXmBranchStateHis(@RequestBody XmBranchStateHis xmBranchStateHis) {

		    boolean createPk=false;
			if(!StringUtils.hasText(xmBranchStateHis.getBizDate())) {
			    createPk=true;
				xmBranchStateHis.setBizDate(xmBranchStateHisService.createKey("bizDate"));
			}
			if(!StringUtils.hasText(xmBranchStateHis.getBranchId())) {
			    createPk=true;
				xmBranchStateHis.setBranchId(xmBranchStateHisService.createKey("branchId"));
			}
			if(createPk==false){
                 if(xmBranchStateHisService.selectOneObject(xmBranchStateHis) !=null ){
                    return Result.error("pk-exists","编号重复，请修改编号再提交");
                }
            }
			xmBranchStateHisService.insert(xmBranchStateHis);
		
	}
	*/
	
	/**
	@ApiOperation( value = "删除一条机构内所有项目指标汇总信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delXmBranchStateHis(@RequestBody XmBranchStateHis xmBranchStateHis){

            if(!StringUtils.hasText(xmBranchStateHis.getBizDate())) {
                 return Result.error("pk-not-exists","请上送主键参数bizDate");
            }
            if(!StringUtils.hasText(xmBranchStateHis.getBranchId())) {
                 return Result.error("pk-not-exists","请上送主键参数branchId");
            }
            XmBranchStateHis xmBranchStateHisDb = xmBranchStateHisService.selectOneObject(xmBranchStateHis);
            if( xmBranchStateHisDb == null ){
                return Result.error("data-not-exists","数据不存在，无法删除");
            }
			xmBranchStateHisService.deleteByPk(xmBranchStateHis);
		return Result.ok();
		
	}
	 */
	
	/**
	@ApiOperation( value = "根据主键修改一条机构内所有项目指标汇总信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmBranchStateHis.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editXmBranchStateHis(@RequestBody XmBranchStateHis xmBranchStateHis) {

            if(!StringUtils.hasText(xmBranchStateHis.getBizDate())) {
                 return Result.error("pk-not-exists","请上送主键参数bizDate");
            }
            if(!StringUtils.hasText(xmBranchStateHis.getBranchId())) {
                 return Result.error("pk-not-exists","请上送主键参数branchId");
            }
            XmBranchStateHis xmBranchStateHisDb = xmBranchStateHisService.selectOneObject(xmBranchStateHis);
            if( xmBranchStateHisDb == null ){
                return Result.error("data-not-exists","数据不存在，无法修改");
            }
			xmBranchStateHisService.updateSomeFieldByPk(xmBranchStateHis);
		
	}
	*/
	

	
	/**
	@ApiOperation( value = "根据主键列表批量删除机构内所有项目指标汇总信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelXmBranchStateHis(@RequestBody List<XmBranchStateHis> xmBranchStateHiss) {
		
		
		
			xmBranchStateHisService.batchDelete(xmBranchStateHiss);
		return Result.ok();
		
	} 
	*/
}
