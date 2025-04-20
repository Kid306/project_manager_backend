package com.xm.core.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.query.QueryTools;
import com.mdp.core.utils.RequestUtils;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.swagger.ApiEntityParams;
import com.xm.core.entity.XmRecordVisit;
import com.xm.core.service.XmRecordVisitService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.mdp.core.utils.BaseUtils.toMap;

/**
 * url编制采用rest风格,如对xm_record_visit 重要页面访问记录的操作有增删改查,对应的url分别为:<br>
 * 组织 com  顶级模块 xm 大模块 core 小模块 <br>
 * 实体 XmRecordVisit 表 xm_record_visit 当前主键(包括多主键): id; 
 ***/
@RestController("xm.core.xmRecordVisitController")
@RequestMapping(value="/xm/core/xmRecordVisit")
@Api(tags={"重要页面访问记录操作接口"})
public class XmRecordVisitController {
	
	static Logger logger =LoggerFactory.getLogger(XmRecordVisitController.class);
	
	@Autowired
	private XmRecordVisitService xmRecordVisitService;

	List<XmRecordVisit> datas=new ArrayList<>();
	 

	Map<String,Object> fieldsMap = toMap(new XmRecordVisit());
 
	
	@ApiOperation( value = "查询重要页面访问记录信息列表",notes=" ")
	@ApiEntityParams( XmRecordVisit.class )
	@ApiImplicitParams({
			@ApiImplicitParam(name="pageSize",value="每页大小，默认20条",required=false),
			@ApiImplicitParam(name="pageNum",value="当前页码,从1开始",required=false),
			@ApiImplicitParam(name="total",value="总记录数,服务器端收到0时，会自动计算总记录数，如果上传>0的不自动计算",required=false),
			@ApiImplicitParam(name="count",value="是否计算总记录条数，如果count=true,则计算计算总条数，如果count=false 则不计算",required=false),
			@ApiImplicitParam(name="orderBy",value="排序列 如性别、学生编号排序 orderBy = sex desc,student desc",required=false),
 	})
	@ApiResponses({
		@ApiResponse(code = 200,response=XmRecordVisit.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listXmRecordVisit(@ApiIgnore @RequestParam Map<String,Object> params){
		
		
		RequestUtils.transformArray(params, "ids");		
		IPage page=QueryTools.initPage(params);
		QueryWrapper<XmRecordVisit> qw = QueryTools.initQueryWrapper(XmRecordVisit.class , params);
		List<Map<String,Object>> datas = xmRecordVisitService.selectListMapByWhere(page,qw,params);
			return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());	//列出XmRecordVisit列表

	}
	
 

	@ApiOperation( value = "新增一条重要页面访问记录信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmRecordVisit.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addXmRecordVisit(@RequestBody XmRecordVisit xmRecordVisit) {

			if(!StringUtils.hasText(xmRecordVisit.getBizId())){
				return Result.error("bizId-0","bizId不能为空");
			}
			if(!StringUtils.hasText(xmRecordVisit.getPbizId())){
				return Result.error("pbizId-0","pbizId不能为空");
			}
			if(!StringUtils.hasText(xmRecordVisit.getObjType())){
				return Result.error("objType-0","objType不能为空");
			}
			User user= LoginUtils.getCurrentUserInfo();
			xmRecordVisit.setId(this.xmRecordVisitService.createKey("id"));
			xmRecordVisit.setGloNo(MDC.get("gloNo"));
			xmRecordVisit.setOperTime(new Date());
			xmRecordVisit.setOperUserid(user.getUserid());
			xmRecordVisit.setOperUsername(user.getUsername());
			xmRecordVisit.setBranchId(user.getBranchId());
			xmRecordVisit.setIp(RequestUtils.getIpAddr(RequestUtils.getRequest()));

			this.datas.add(xmRecordVisit);
			if(this.datas.size()>100){
				List<XmRecordVisit> newDatas=new ArrayList<>();
				newDatas.addAll(this.datas);
				this.datas.clear();
				xmRecordVisitService.batchAddAndCalc(newDatas);

			}
			//
		return Result.ok();  
		
	}

	@Scheduled(cron = "0 0 */3 * * ?")
	public void batchAddAndCalc(){
		if(this.datas.size()>0){
			List<XmRecordVisit> newDatas=new ArrayList<>();
			newDatas.addAll(this.datas);
			this.datas.clear();
			xmRecordVisitService.batchAddAndCalc(newDatas);
		}
	}

	
	/**
	@ApiOperation( value = "删除一条重要页面访问记录信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delXmRecordVisit(@RequestBody XmRecordVisit xmRecordVisit){

            if(!StringUtils.hasText(xmRecordVisit.getId())) {
                 return Result.error("pk-not-exists","请上送主键参数id");
            }
            XmRecordVisit xmRecordVisitDb = xmRecordVisitService.selectOneObject(xmRecordVisit);
            if( xmRecordVisitDb == null ){
                return Result.error("data-not-exists","数据不存在，无法删除");
            }
			xmRecordVisitService.deleteByPk(xmRecordVisit);
		return Result.ok();
		
	}
	 */
	
	/**
	@ApiOperation( value = "根据主键修改一条重要页面访问记录信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmRecordVisit.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editXmRecordVisit(@RequestBody XmRecordVisit xmRecordVisit) {

            if(!StringUtils.hasText(xmRecordVisit.getId())) {
                 return Result.error("pk-not-exists","请上送主键参数id");
            }
            XmRecordVisit xmRecordVisitDb = xmRecordVisitService.selectOneObject(xmRecordVisit);
            if( xmRecordVisitDb == null ){
                return Result.error("data-not-exists","数据不存在，无法修改");
            }
			xmRecordVisitService.updateSomeFieldByPk(xmRecordVisit);
		
	}
	*/

	/**
    @ApiOperation( value = "批量修改某些字段",notes="")
    @ApiEntityParams( value = XmRecordVisit.class, props={ }, remark = "重要页面访问记录", paramType = "body" )
	@ApiResponses({
			@ApiResponse(code = 200,response=XmRecordVisit.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields( @ApiIgnore @RequestBody Map<String,Object> xmRecordVisitMap) {

            List<String> ids= (List<String>) xmRecordVisitMap.get("ids");
			if(ids==null || ids.size()==0){
				return Result.error("ids-0","ids不能为空");
			}

			Set<String> fields=new HashSet<>();
            fields.add("id");
			for (String fieldName : xmRecordVisitMap.keySet()) {
				if(fields.contains(fieldName)){
					return Result.error(fieldName+"-no-edit",fieldName+"不允许修改");
				}
			}
			Set<String> fieldKey=xmRecordVisitMap.keySet().stream().filter(i-> fieldsMap.containsKey(i)).collect(Collectors.toSet());
			fieldKey=fieldKey.stream().filter(i->xmRecordVisitMap.get(i)!=null).collect(Collectors.toSet());

			if(fieldKey.size()<=0) {
				return Result.error("fieldKey-0","没有需要更新的字段");
 			}
			XmRecordVisit xmRecordVisit = fromMap(xmRecordVisitMap,XmRecordVisit.class);
			List<XmRecordVisit> xmRecordVisitsDb=xmRecordVisitService.selectListByIds(ids);
			if(xmRecordVisitsDb==null ||xmRecordVisitsDb.size()==0){
				return Result.error("data-0","记录已不存在");
			}
			List<XmRecordVisit> can=new ArrayList<>();
			List<XmRecordVisit> no=new ArrayList<>();
			User user = LoginUtils.getCurrentUserInfo();
			for (XmRecordVisit xmRecordVisitDb : xmRecordVisitsDb) {
				Tips tips2 = new Tips("检查通过"); 
				if(!tips2.isOk()){
				    no.add(xmRecordVisitDb); 
				}else{
					can.add(xmRecordVisitDb);
				}
			}
			if(can.size()>0){
                xmRecordVisitMap.put("ids",can.stream().map(i->i.getId()).collect(Collectors.toList()));
			    xmRecordVisitService.editSomeFields(xmRecordVisitMap); 
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
	*/

	/**
	@ApiOperation( value = "根据主键列表批量删除重要页面访问记录信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelXmRecordVisit(@RequestBody List<XmRecordVisit> xmRecordVisits) {
		
        
        
            if(xmRecordVisits.size()<=0){
                return Result.error("data-0","请上送待删除数据列表");
            }
             List<XmRecordVisit> datasDb=xmRecordVisitService.selectListByIds(xmRecordVisits.stream().map(i-> i.getId() ).collect(Collectors.toList()));

            List<XmRecordVisit> can=new ArrayList<>();
            List<XmRecordVisit> no=new ArrayList<>();
            for (XmRecordVisit data : datasDb) {
                if(true){
                    can.add(data);
                }else{
                    no.add(data);
                } 
            }
            List<String> msgs=new ArrayList<>();
            if(can.size()>0){
                xmRecordVisitService.batchDelete(can);
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
	*/
}
