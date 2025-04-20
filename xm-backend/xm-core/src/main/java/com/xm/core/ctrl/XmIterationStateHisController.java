package com.xm.core.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.query.QueryTools;
import com.mdp.core.utils.RequestUtils;
import com.xm.core.entity.XmIterationStateHis;
import com.xm.core.service.XmIterationStateHisService;
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
 * url编制采用rest风格,如对xm_iteration_state_his 迭代定义的操作有增删改查,对应的url分别为:<br>
 *  新增: core/xmIterationStateHis/add <br>
 *  查询: core/xmIterationStateHis/list<br>
 *  模糊查询: core/xmIterationStateHis/listKey<br>
 *  修改: core/xmIterationStateHis/edit <br>
 *  删除: core/xmIterationStateHis/del<br>
 *  批量删除: core/xmIterationStateHis/batchDel<br>
 * 组织 com  顶级模块 xm 大模块 core 小模块 <br>
 * 实体 XmIterationStateHis 表 xm_iteration_state_his 当前主键(包括多主键): iteration_id,biz_date; 
 ***/
@RestController("xm.core.xmIterationStateHisController")
@RequestMapping(value="/xm/core/xmIterationStateHis")
@Api(tags={"迭代定义操作接口"})
public class XmIterationStateHisController {
	
	static Logger logger =LoggerFactory.getLogger(XmIterationStateHisController.class);
	
	@Autowired
	private XmIterationStateHisService xmIterationStateHisService;
	 
		
 
	
	@ApiOperation( value = "查询迭代定义信息列表",notes=" ") 
	@ApiResponses({
		@ApiResponse(code = 200,response=XmIterationStateHis.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listXmIterationStateHis(@ApiIgnore @RequestParam Map<String,Object> params){
		
		
		RequestUtils.transformArray(params, "$pks");		
		IPage page=QueryTools.initPage(params);
		QueryWrapper<XmIterationStateHis> qw = QueryTools.initQueryWrapper(XmIterationStateHis.class , params);
		List<Map<String,Object>> datas = xmIterationStateHisService.selectListMapByWhere(page,qw,params);
			return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());	//列出XmIterationStateHis列表

	}
	
 
	
	/**
	@ApiOperation( value = "新增一条迭代定义信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmIterationStateHis.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addXmIterationStateHis(@RequestBody XmIterationStateHis xmIterationStateHis) {

		    boolean createPk=false;
			if(!StringUtils.hasText(xmIterationStateHis.getIterationId())) {
			    createPk=true;
				xmIterationStateHis.setIterationId(xmIterationStateHisService.createKey("iterationId"));
			}
			if(!StringUtils.hasText(xmIterationStateHis.getBizDate())) {
			    createPk=true;
				xmIterationStateHis.setBizDate(xmIterationStateHisService.createKey("bizDate"));
			}
			if(createPk==false){
                 if(xmIterationStateHisService.selectOneObject(xmIterationStateHis) !=null ){
                    return Result.error("pk-exists","编号重复，请修改编号再提交");
                }
            }
			xmIterationStateHisService.insert(xmIterationStateHis);
		
	}
	*/
	
	/**
	@ApiOperation( value = "删除一条迭代定义信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delXmIterationStateHis(@RequestBody XmIterationStateHis xmIterationStateHis){

            if(!StringUtils.hasText(xmIterationStateHis.getIterationId())) {
                 return Result.error("pk-not-exists","请上送主键参数iterationId");
            }
            if(!StringUtils.hasText(xmIterationStateHis.getBizDate())) {
                 return Result.error("pk-not-exists","请上送主键参数bizDate");
            }
            XmIterationStateHis xmIterationStateHisDb = xmIterationStateHisService.selectOneObject(xmIterationStateHis);
            if( xmIterationStateHisDb == null ){
                return Result.error("data-not-exists","数据不存在，无法删除");
            }
			xmIterationStateHisService.deleteByPk(xmIterationStateHis);
		return Result.ok();
		
	}
	 */
	
	/**
	@ApiOperation( value = "根据主键修改一条迭代定义信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmIterationStateHis.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editXmIterationStateHis(@RequestBody XmIterationStateHis xmIterationStateHis) {

            if(!StringUtils.hasText(xmIterationStateHis.getIterationId())) {
                 return Result.error("pk-not-exists","请上送主键参数iterationId");
            }
            if(!StringUtils.hasText(xmIterationStateHis.getBizDate())) {
                 return Result.error("pk-not-exists","请上送主键参数bizDate");
            }
            XmIterationStateHis xmIterationStateHisDb = xmIterationStateHisService.selectOneObject(xmIterationStateHis);
            if( xmIterationStateHisDb == null ){
                return Result.error("data-not-exists","数据不存在，无法修改");
            }
			xmIterationStateHisService.updateSomeFieldByPk(xmIterationStateHis);
		
	}
	*/
	

	
	/**
	@ApiOperation( value = "根据主键列表批量删除迭代定义信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelXmIterationStateHis(@RequestBody List<XmIterationStateHis> xmIterationStateHiss) {
		
		
		
			xmIterationStateHisService.batchDelete(xmIterationStateHiss);
		return Result.ok();
		
	} 
	*/
}
