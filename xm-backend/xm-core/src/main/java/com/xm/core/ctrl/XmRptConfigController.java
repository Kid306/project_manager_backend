package com.xm.core.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.entity.Tips;
import com.mdp.core.query.QueryTools;
import com.mdp.core.utils.RequestUtils;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.swagger.ApiEntityParams;
import com.xm.core.entity.XmRptConfig;
import com.xm.core.service.XmRptConfigService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;
import java.util.stream.Collectors;

import static com.mdp.core.utils.BaseUtils.fromMap;
import static com.mdp.core.utils.BaseUtils.toMap;

/**
 * url编制采用rest风格,如对xm_rpt_config 测试报告配置表的操作有增删改查,对应的url分别为:<br>
 * 组织 com  顶级模块 xm 大模块 core 小模块 <br>
 * 实体 XmRptConfig 表 xm_rpt_config 当前主键(包括多主键): id; 
 ***/
@RestController("xm.core.xmRptConfigController")
@RequestMapping(value="/xm/core/xmRptConfig")
@Api(tags={"测试报告配置表操作接口"})
public class XmRptConfigController {
	
	static Logger logger =LoggerFactory.getLogger(XmRptConfigController.class);
	
	@Autowired
	private XmRptConfigService xmRptConfigService;
	 

	Map<String,Object> fieldsMap = toMap(new XmRptConfig());
 
	
	@ApiOperation( value = "查询测试报告配置表信息列表",notes=" ")
	@ApiEntityParams( XmRptConfig.class )
	@ApiImplicitParams({
			@ApiImplicitParam(name="pageSize",value="每页大小，默认20条",required=false),
			@ApiImplicitParam(name="pageNum",value="当前页码,从1开始",required=false),
			@ApiImplicitParam(name="total",value="总记录数,服务器端收到0时，会自动计算总记录数，如果上传>0的不自动计算",required=false),
			@ApiImplicitParam(name="count",value="是否计算总记录条数，如果count=true,则计算计算总条数，如果count=false 则不计算",required=false),
			@ApiImplicitParam(name="orderBy",value="排序列 如性别、学生编号排序 orderBy = sex desc,student desc",required=false),
 	})
	@ApiResponses({
		@ApiResponse(code = 200,response=XmRptConfig.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listXmRptConfig(@ApiIgnore @RequestParam Map<String,Object> params){
		
		
		RequestUtils.transformArray(params, "ids");		
		IPage page=QueryTools.initPage(params);
		User user=LoginUtils.getCurrentUserInfo();
		params.put("cbranchId",user.getBranchId());
		QueryWrapper<XmRptConfig> qw = QueryTools.initQueryWrapper(XmRptConfig.class , params);
		List<Map<String,Object>> datas = xmRptConfigService.selectListMapByWhere(page,qw,params);
			return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());	//列出XmRptConfig列表

	}
	
 

	@ApiOperation( value = "新增一条测试报告配置表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmRptConfig.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addXmRptConfig(@RequestBody XmRptConfig xmRptConfig) {

			xmRptConfig.setId(xmRptConfigService.createKey("id"));
			User user= LoginUtils.getCurrentUserInfo();
			xmRptConfig.setCuserid(user.getUserid());
			xmRptConfig.setCusername(user.getUsername());
			xmRptConfig.setCbranchId(user.getBranchId());
			xmRptConfig.setCtime(new Date());
			xmRptConfigService.insert(xmRptConfig);
		return Result.ok().setData(xmRptConfig);
	}

	@ApiOperation( value = "删除一条测试报告配置表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delXmRptConfig(@RequestBody XmRptConfig xmRptConfig){

            if(!StringUtils.hasText(xmRptConfig.getId())) {
                 return Result.error("pk-not-exists","请上送主键参数id");
            }
            XmRptConfig xmRptConfigDb = xmRptConfigService.selectOneObject(xmRptConfig);
            if( xmRptConfigDb == null ){
                return Result.error("data-not-exists","数据不存在，无法删除");
            }
			xmRptConfigService.deleteByPk(xmRptConfig);
		return Result.ok();
		
	}

	@ApiOperation( value = "根据主键修改一条测试报告配置表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmRptConfig.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editXmRptConfig(@RequestBody XmRptConfig xmRptConfig) {

            if(!StringUtils.hasText(xmRptConfig.getId())) {
                 return Result.error("pk-not-exists","请上送主键参数id");
            }
            XmRptConfig xmRptConfigDb = xmRptConfigService.selectOneObject(xmRptConfig);
            if( xmRptConfigDb == null ){
                return Result.error("data-not-exists","数据不存在，无法修改");
            }
			xmRptConfigService.updateSomeFieldByPk(xmRptConfig);
		return Result.ok();
	}

    @ApiOperation( value = "批量修改某些字段",notes="")
    @ApiEntityParams( value = XmRptConfig.class, props={ }, remark = "测试报告配置表", paramType = "body" )
	@ApiResponses({
			@ApiResponse(code = 200,response=XmRptConfig.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields( @ApiIgnore @RequestBody Map<String,Object> xmRptConfigMap) {

            List<String> ids= (List<String>) xmRptConfigMap.get("ids");
			if(ids==null || ids.size()==0){
				return Result.error("ids-0","ids不能为空");
			}

			Set<String> fields=new HashSet<>();
            fields.add("id");
			for (String fieldName : xmRptConfigMap.keySet()) {
				if(fields.contains(fieldName)){
					return Result.error(fieldName+"-no-edit",fieldName+"不允许修改");
				}
			}
			Set<String> fieldKey=xmRptConfigMap.keySet().stream().filter(i-> fieldsMap.containsKey(i)).collect(Collectors.toSet());
			fieldKey=fieldKey.stream().filter(i->xmRptConfigMap.get(i)!=null).collect(Collectors.toSet());

			if(fieldKey.size()<=0) {
				return Result.error("fieldKey-0","没有需要更新的字段");
 			}
			XmRptConfig xmRptConfig = fromMap(xmRptConfigMap,XmRptConfig.class);
			List<XmRptConfig> xmRptConfigsDb=xmRptConfigService.selectListByIds(ids);
			if(xmRptConfigsDb==null ||xmRptConfigsDb.size()==0){
				return Result.error("data-0","记录已不存在");
			}
			List<XmRptConfig> can=new ArrayList<>();
			List<XmRptConfig> no=new ArrayList<>();
			User user = LoginUtils.getCurrentUserInfo();
			for (XmRptConfig xmRptConfigDb : xmRptConfigsDb) {
 				Tips tips2 = new Tips("检查通过"); 
				if(!tips2.isOk()){
				    no.add(xmRptConfigDb); 
				}else{
					can.add(xmRptConfigDb);
				}
			}
			if(can.size()>0){
                xmRptConfigMap.put("ids",can.stream().map(i->i.getId()).collect(Collectors.toList()));
			    xmRptConfigService.editSomeFields(xmRptConfigMap); 
			}
			List<String> msgs=new ArrayList<>();
			if(can.size()>0){
				msgs.add(String.format("成功更新以下%s条数据",can.size()));
			}
			if(no.size()>0){
				msgs.add(String.format("以下%s个数据无权限更新",no.size()));
			}
			if(can.size()>0){
				return Result.ok(msgs.stream().collect(Collectors.joining()));
			}else {
				return Result.error(msgs.stream().collect(Collectors.joining()));
			} 
		
	}

	@ApiOperation( value = "根据主键列表批量删除测试报告配置表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelXmRptConfig(@RequestBody List<XmRptConfig> xmRptConfigs) {
		
        
        
            if(xmRptConfigs.size()<=0){
                return Result.error("data-0","请上送待删除数据列表");
            }
             List<XmRptConfig> datasDb=xmRptConfigService.selectListByIds(xmRptConfigs.stream().map(i-> i.getId() ).collect(Collectors.toList()));

            List<XmRptConfig> can=new ArrayList<>();
            List<XmRptConfig> no=new ArrayList<>();
            for (XmRptConfig data : datasDb) {
                if(true){
                    can.add(data);
                }else{
                    no.add(data);
                } 
            }
            List<String> msgs=new ArrayList<>();
            if(can.size()>0){
                xmRptConfigService.batchDelete(can);
                msgs.add(String.format("成功删除%s条数据.",can.size()));
            }
    
            if(no.size()>0){ 
                msgs.add(String.format("以下%s条数据不能删除.【%s】",no.size(),no.stream().map(i-> i.getId() ).collect(Collectors.joining(","))));
            }
            if(can.size()>0){
                 return Result.ok(msgs.stream().collect(Collectors.joining()));
            }else {
                return Result.error(msgs.stream().collect(Collectors.joining()));
            } 
        
	} 

}
