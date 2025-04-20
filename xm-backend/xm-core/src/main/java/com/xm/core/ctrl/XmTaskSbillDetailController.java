package com.xm.core.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.entity.Tips;
import com.mdp.core.query.QueryTools;
import com.mdp.core.utils.RequestUtils;
import com.mdp.core.valid.ValidUtil;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.xm.core.entity.XmTaskSbill;
import com.xm.core.entity.XmTaskSbillDetail;
import com.xm.core.service.XmTaskSbillDetailService;
import com.xm.core.service.XmTaskSbillService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
 * url编制采用rest风格,如对xm_task_sbill_detail 工时登记表的操作有增删改查,对应的url分别为:<br>
 *  新增: core/xmTaskSbillDetail/add <br>
 *  查询: core/xmTaskSbillDetail/list<br>
 *  模糊查询: core/xmTaskSbillDetail/listKey<br>
 *  修改: core/xmTaskSbillDetail/edit <br>
 *  删除: core/xmTaskSbillDetail/del<br>
 *  批量删除: core/xmTaskSbillDetail/batchDel<br>
 * 组织 com  顶级模块 xm 大模块 core 小模块 <br>
 * 实体 XmTaskSbillDetail 表 xm_task_sbill_detail 当前主键(包括多主键): id; 
 ***/
@RestController("xm.core.xmTaskSbillDetailController")
@RequestMapping(value="/xm/core/xmTaskSbillDetail")
@Api(tags={"工时登记表操作接口"})
public class XmTaskSbillDetailController {
	
	static Logger logger =LoggerFactory.getLogger(XmTaskSbillDetailController.class);
	
	@Autowired
	private XmTaskSbillDetailService xmTaskSbillDetailService;

	@Autowired
	private XmTaskSbillService xmTaskSbillService;

	Map<String,Object> fieldsMap = toMap(new XmTaskSbillDetail());
 
	
	@ApiOperation( value = "查询工时登记表信息列表",notes=" ") 
	@ApiResponses({
		@ApiResponse(code = 200,response=XmTaskSbillDetail.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listXmTaskSbillDetail(@ApiIgnore @RequestParam Map<String,Object> params){
		
		
		RequestUtils.transformArray(params, "ids");		
		IPage page=QueryTools.initPage(params);
		User user=LoginUtils.getCurrentUserInfo();
		params.put("linkBranchId",user.getBranchId());
		QueryTools.alias(params,"* res.");
		QueryWrapper<XmTaskSbillDetail> qw = QueryTools.initQueryWrapper(XmTaskSbillDetail.class , params);
		List<Map<String,Object>> datas = xmTaskSbillDetailService.selectListMapByWhere(page,qw,params);
			return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());	//列出XmTaskSbillDetail列表

	}

	@ApiOperation( value = "查询机构支出费用按月分布报表",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200,response=XmTaskSbillDetail.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/listSum",method=RequestMethod.GET)
	public Result listSum(@ApiIgnore @RequestParam Map<String,Object> params){
		
		
		String bizYear= (String) params.get("bizYear");
		if(!StringUtils.hasText(bizYear)){
			return Result.error("bizYear-0","年份不能为空");
		}
		ValidUtil.isRequired("groupBy","分组",params.get("groupBy"));
		User user=LoginUtils.getCurrentUserInfo();
		params.put("linkBranchId",user.getBranchId());
		List<Map<String,Object>>	datas = xmTaskSbillDetailService.listSum(params);	//列出XmTaskSbillDetail列表
 		return Result.ok().setData(datas);
	}
	/**
	@ApiOperation( value = "新增一条工时登记表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmTaskSbillDetail.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addXmTaskSbillDetail(@RequestBody XmTaskSbillDetail xmTaskSbillDetail) {

		    boolean createPk=false;
			if(!StringUtils.hasText(xmTaskSbillDetail.getId())) {
			    createPk=true;
				xmTaskSbillDetail.setId(xmTaskSbillDetailService.createKey("id"));
			}
			if(createPk==false){
                 if(xmTaskSbillDetailService.selectOneObject(xmTaskSbillDetail) !=null ){
                    return Result.error("pk-exists","编号重复，请修改编号再提交");
                }
            }
			xmTaskSbillDetailService.insert(xmTaskSbillDetail);
		
	}
	*/

	@ApiOperation( value = "删除一条工时登记表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delXmTaskSbillDetail(@RequestBody XmTaskSbillDetail xmTaskSbillDetail){
		 return batchDelXmTaskSbillDetail(Arrays.asList(xmTaskSbillDetail));
	}
	
	/**
	@ApiOperation( value = "根据主键修改一条工时登记表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmTaskSbillDetail.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editXmTaskSbillDetail(@RequestBody XmTaskSbillDetail xmTaskSbillDetail) {

            if(!StringUtils.hasText(xmTaskSbillDetail.getId())) {
                 return Result.error("pk-not-exists","请上送主键参数id");
            }
            XmTaskSbillDetail xmTaskSbillDetailDb = xmTaskSbillDetailService.selectOneObject(xmTaskSbillDetail);
            if( xmTaskSbillDetailDb == null ){
                return Result.error("data-not-exists","数据不存在，无法修改");
            }
			xmTaskSbillDetailService.updateSomeFieldByPk(xmTaskSbillDetail);
		
	}
	*/

    @ApiOperation( value = "批量修改某些字段",notes="")
	@ApiResponses({
			@ApiResponse(code = 200,response=XmTaskSbillDetail.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields(@RequestBody Map<String,Object> xmTaskSbillDetailMap) {

            List<String> ids= (List<String>) xmTaskSbillDetailMap.get("$pks");
			if(ids==null || ids.size()==0){
				return Result.error("ids-0","ids不能为空");
			}

			Set<String> fields=new HashSet<>();
            fields.add("id");
            fields.add("samt");
            fields.add("quoteAt");
            fields.add("tactAt");
            fields.add("userid");
            fields.add("taskId");
            fields.add("projectId");
			for (String fieldName : xmTaskSbillDetailMap.keySet()) {
				if(fields.contains(fieldName)){
					return Result.error(fieldName+"-no-edit",fieldName+"不允许修改");
				}
			}
			Set<String> fieldKey=xmTaskSbillDetailMap.keySet().stream().filter(i-> fieldsMap.containsKey(i)).collect(Collectors.toSet());
			fieldKey=fieldKey.stream().filter(i->xmTaskSbillDetailMap.get(i)!=null).collect(Collectors.toSet());

			if(fieldKey.size()<=0) {
				return Result.error("fieldKey-0","没有需要更新的字段");
 			}
			String sstatus= (String) xmTaskSbillDetailMap.get("sstatus");
			if(StringUtils.hasText(sstatus)){
				if(!"0".equals(sstatus) && !"1".equals(sstatus)){
					return Result.error("sstatus-not-01","只能修改为无需结算或者待结算");
				}
			}
			XmTaskSbillDetail xmTaskSbillDetail = fromMap(xmTaskSbillDetailMap,XmTaskSbillDetail.class);
			List<XmTaskSbillDetail> xmTaskSbillDetailsDb=xmTaskSbillDetailService.selectListByIds(ids);
			if(xmTaskSbillDetailsDb==null ||xmTaskSbillDetailsDb.size()==0){
				return Result.error("data-0","记录已不存在");
			}
			if(fieldKey.contains("othFee")){
				if(xmTaskSbillDetailsDb.size()>1){
					return Result.error("data-not-1","其他费用的修改只能一次修改一条记录，不能批量修改");
				}else{
					XmTaskSbillDetail detail=xmTaskSbillDetailsDb.get(0);
					this.xmTaskSbillDetailService.preCalcSamt(detail);
					this.xmTaskSbillDetailService.updateSomeFieldByPk(detail);
					this.xmTaskSbillService.updateBySbillDetailList(Arrays.asList(detail.getSbillId()));
					return Result.ok("成功");
				}
			}
			List<XmTaskSbillDetail> can=new ArrayList<>();
			List<XmTaskSbillDetail> no=new ArrayList<>();
			User user = LoginUtils.getCurrentUserInfo();
			for (XmTaskSbillDetail xmTaskSbillDetailDb : xmTaskSbillDetailsDb) {
 				Tips tips2 = new Tips("检查通过"); 
				if(!tips2.isOk()){
				    no.add(xmTaskSbillDetailDb); 
				}else{
					can.add(xmTaskSbillDetailDb);
				}
			}
			if(can.size()>0){
                xmTaskSbillDetailMap.put("$pks",can.stream().map(i->i.getId()).collect(Collectors.toList()));
			    xmTaskSbillDetailService.doEditSomeFields(xmTaskSbillDetailMap,can.stream().map(i->i.getSbillId()).collect(Collectors.toSet()).stream().collect(Collectors.toList()));
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

	@ApiOperation( value = "根据主键列表批量删除工时登记表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelXmTaskSbillDetail(@RequestBody List<XmTaskSbillDetail> xmTaskSbillDetails) {
		
        
        		
        	User user=LoginUtils.getCurrentUserInfo();
            if(xmTaskSbillDetails.size()<=0){
                return Result.error("data-0","请上送待删除数据列表");
            }
             List<XmTaskSbillDetail> datasDb=xmTaskSbillDetailService.selectListByIds(xmTaskSbillDetails.stream().map(i-> i.getId() ).collect(Collectors.toList()));
			String sbillId=datasDb.get(0).getSbillId();
			if(datasDb.stream().filter(i->!sbillId.equals(i.getSbillId())).findAny().isPresent()){
				return Result.error("sbillId-0","只能删除同一个结算单的清单");
			}
			XmTaskSbill xmTaskSbill=xmTaskSbillService.selectOneById(sbillId);
			if(!user.getUserid().equals(xmTaskSbill.getCuserid())){
				return Result.error("sbillId-0","该结算单不是您创建的，您不能删除其清单");
			}
			if(!"0".equals(xmTaskSbill.getStatus())){
				return Result.error("status-not-0","结算单已提交，不允许更改");
			}
            List<XmTaskSbillDetail> can=new ArrayList<>();
            List<XmTaskSbillDetail> no=new ArrayList<>();
            for (XmTaskSbillDetail data : datasDb) {
                if(true){
                    can.add(data);
                }else{
                    no.add(data);
                } 
            }
            List<String> msgs=new ArrayList<>();
            if(can.size()>0){
                xmTaskSbillDetailService.batchDoDelete(can);
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
