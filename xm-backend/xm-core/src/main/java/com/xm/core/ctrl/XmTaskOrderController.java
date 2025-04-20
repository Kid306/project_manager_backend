package com.xm.core.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.api.CacheHKVService;
import com.mdp.core.entity.Result;
import com.mdp.core.query.QueryTools;
import com.mdp.core.utils.NumberUtil;
import com.mdp.core.utils.RequestUtils;
import com.mdp.meta.client.entity.ItemVo;
import com.mdp.meta.client.service.ItemService;
import com.mdp.msg.client.PushNotifyMsgService;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.swagger.ApiEntityParams;
import com.xm.core.entity.XmTask;
import com.xm.core.entity.XmTaskOrder;
import com.xm.core.service.XmRecordService;
import com.xm.core.service.XmTaskOrderService;
import com.xm.core.service.XmTaskService;
import com.xm.core.vo.AddXmTaskOrderVo;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.mdp.core.utils.BaseUtils.toMap;

/**
 * url编制采用rest风格,如对xm_task_order 任务相关费用订单表的操作有增删改查,对应的url分别为:<br>
 * 组织 com  顶级模块 xm 大模块 core 小模块 <br>
 * 实体 XmTaskOrder 表 xm_task_order 当前主键(包括多主键): id; 
 ***/
@RestController("xm.core.xmTaskOrderController")
@RequestMapping(value="/xm/core/xmTaskOrder")
@Api(tags={"任务相关费用订单表操作接口"})
public class XmTaskOrderController {
	
	static Logger logger =LoggerFactory.getLogger(XmTaskOrderController.class);
	
	@Autowired
	private XmTaskOrderService xmTaskOrderService;

	@Autowired
	XmTaskService xmTaskService;

	@Autowired
	ItemService itemService;


	@Autowired
	PushNotifyMsgService msgService;

	@Autowired
	XmRecordService xmRecordService;

	@Autowired
	CacheHKVService cacheHKVService;
	 

	Map<String,Object> fieldsMap = toMap(new XmTaskOrder());
 
	
	@ApiOperation( value = "查询任务相关费用订单表信息列表",notes=" ")
	@ApiEntityParams( XmTaskOrder.class )
	@ApiImplicitParams({
			@ApiImplicitParam(name="pageSize",value="每页大小，默认20条",required=false),
			@ApiImplicitParam(name="pageNum",value="当前页码,从1开始",required=false),
			@ApiImplicitParam(name="total",value="总记录数,服务器端收到0时，会自动计算总记录数，如果上传>0的不自动计算",required=false),
			@ApiImplicitParam(name="count",value="是否计算总记录条数，如果count=true,则计算计算总条数，如果count=false 则不计算",required=false),
			@ApiImplicitParam(name="orderBy",value="排序列 如性别、学生编号排序 orderBy = sex desc,student desc",required=false),
 	})
	@ApiResponses({
		@ApiResponse(code = 200,response=XmTaskOrder.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listXmTaskOrder(@ApiIgnore @RequestParam Map<String,Object> params){
		
		
		RequestUtils.transformArray(params, "ids");		
		IPage page=QueryTools.initPage(params);
		QueryWrapper<XmTaskOrder> qw = QueryTools.initQueryWrapper(XmTaskOrder.class , params);
		List<Map<String,Object>> datas = xmTaskOrderService.selectListMapByWhere(page,qw,params);
			return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());	//列出XmTaskOrder列表

	}

	@ApiOperation( value = "计算订单金额",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200,response= AddXmTaskOrderVo.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/calcOrder",method= RequestMethod.GET)
	public Result calcOrder(  AddXmTaskOrderVo xmTaskOrder) {
		xmTaskOrder.setCalc(true);
		return  addXmTaskOrder(xmTaskOrder);
	}

	@ApiOperation( value = "新增一条任务相关费用订单表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmTaskOrder.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addXmTaskOrder(@RequestBody AddXmTaskOrderVo xmTaskOrder) {

			if(!StringUtils.hasText(xmTaskOrder.getTaskId())){
				return Result.error("taskId-0","任务编号不能为空");
			}
			XmTask xmTaskDb=this.xmTaskService.selectOneById(xmTaskOrder.getTaskId());
			if(xmTaskDb==null){
				return Result.error("data-0","任务已不存在");
			}
			if(!"1".equals(xmTaskDb.getTaskOut())){
				return Result.error("taskOut-0","不是外包任务，无须付款");
			}

			if(!"1".equals(xmTaskDb.getCrowd())){
				return Result.error("taskOut-0","不是众包任务，无须付款");
			}
			if(!StringUtils.hasText(xmTaskOrder.getBizType())){
				return Result.error("bizType-0","业务类型不能为空bizType:1-保证金，2-营销推广");
			}
			if("1".equals(xmTaskOrder.getBizType())){
				if(!"4".equals(xmTaskDb.getBidStep())){
					return Result.error("bidStep-not-4","当前任务未到缴纳保证金步骤");
				}
				if(!"1".equals(xmTaskDb.getEstate()) && !"0".equals(xmTaskDb.getEstate()) && !StringUtils.hasText(xmTaskDb.getEstate())){
					return Result.error("estate-not-1","当前任务不是待缴纳保证金状态");
				}
				if(xmTaskDb.getQuoteFinalAt()==null || xmTaskDb.getQuoteFinalAt().compareTo(BigDecimal.ZERO)<=0){
					return Result.error("quoteFinalAt-0","最终任务价格不能为空");
				}
			}else if("2".equals(xmTaskOrder.getBizType())){
				if(!"1".equals(xmTaskDb.getTop()) && !"1".equals(xmTaskDb.getOshare()) && !"1".equals(xmTaskDb.getUrgent()) && !"1".equals(xmTaskDb.getCrmSup()) && !"1".equals(xmTaskDb.getHot()) ) {
					return Result.error("no-need-pay", "没有选中任何推广活动，无须缴款");
				}
			}else{
				return Result.error("bizType-0", "业务类型错误bizType:1-保证金，2-营销推广");
			}
			User user= LoginUtils.getCurrentUserInfo();
			XmTaskOrder order=new XmTaskOrder();
			order.setId(this.xmTaskOrderService.createKey("id"));
			order.setTaskId(xmTaskDb.getId());
			order.setOuserid(user.getUserid());
			order.setObranchId(user.getBranchId());
			order.setProjectId(xmTaskDb.getProjectId());
			order.setName(xmTaskDb.getName());
			order.setBizType(xmTaskOrder.getBizType());
			BigDecimal originFee=BigDecimal.ZERO;
			if("1".equals(xmTaskOrder.getBizType())){
				if("1".equals(xmTaskDb.getEstate())||"0".equals(xmTaskDb.getEstate())||"4".equals(xmTaskDb.getEstate())){
					order.setEfunds(xmTaskDb.getQuoteFinalAt());
					order.setEstate("1");
					originFee=originFee.add(order.getEfunds());
					if(xmTaskDb.getQuoteFinalAt()==null || xmTaskDb.getQuoteFinalAt().compareTo(BigDecimal.ZERO)<=0){
						return Result.error("quoteFinalAt-0","保证金金额计算错误，原因为中标人报价金额为空。");
					}
				}else if("2".equals(xmTaskDb.getEstate()) ){
					if(xmTaskDb.getQuoteFinalAt()==null || xmTaskDb.getQuoteFinalAt().compareTo(BigDecimal.ZERO)<=0){
						return Result.error("quoteFinalAt-0","保证金金额计算错误，原因为中标人报价金额为空。");
					}
					if(xmTaskDb.getEfunds().compareTo(xmTaskDb.getQuoteFinalAt())>=0){
						return Result.error("estate-not-2-3","保证金已支付过，不能重复缴纳");
					}else{
						order.setEfunds(xmTaskDb.getQuoteFinalAt().subtract(xmTaskDb.getEfunds()));
						order.setEstate("1");
						originFee=originFee.add(order.getEfunds());
					}
				}else{
					return Result.error("estate-not-2-3","保证金已支付过，不能重复缴纳");
				}

				order.setName("托管任务赏金【"+xmTaskDb.getName()+"】");
				order.setRemark(order.getName());
			}else if("2".equals(xmTaskOrder.getBizType())){
				ItemVo itemVo=itemService.getDict("sysParam","crowd_task_market");
				List<String> marketNames=new ArrayList<>();
				if("1".equals(xmTaskDb.getTop())){
					marketNames.add("置顶");
					order.setTop("1");
					order.setTopFee(NumberUtil.getBigDecimal(itemVo.getExtInfo("topFee").getValue(),BigDecimal.ZERO));
					order.setTopDays(NumberUtil.getInteger(itemVo.getExtInfo("topDays").getValue(),3));
					originFee=originFee.add(order.getTopFee());
				}
				if("1".equals(xmTaskDb.getHot())){

					marketNames.add("火热");
					order.setHot("1");
					order.setHotFee(NumberUtil.getBigDecimal(itemVo.getExtInfo("hotFee").getValue(),BigDecimal.ZERO));
					order.setHotDays(NumberUtil.getInteger(itemVo.getExtInfo("hotDays").getValue(),3));
					originFee=originFee.add(order.getTopFee());
				}
				if("1".equals(xmTaskDb.getUrgent())){

					marketNames.add("加急");
					order.setUrgent("1");
					order.setUrgentFee(NumberUtil.getBigDecimal(itemVo.getExtInfo("urgentFee").getValue(),BigDecimal.ZERO));
					order.setUrgentDays(NumberUtil.getInteger(itemVo.getExtInfo("urgentDays").getValue(),3));
					originFee=originFee.add(order.getUrgentFee());
				}
				if("1".equals(xmTaskDb.getCrmSup())){

					marketNames.add("客服包办");
					order.setCrmSup("1");
					order.setCrmSupFee(NumberUtil.getBigDecimal(itemVo.getExtInfo("crmSupFee").getValue(),BigDecimal.ZERO));
					originFee=originFee.add(order.getCrmSupFee());
				}

				if("1".equals(xmTaskDb.getOshare())){

					marketNames.add("分享赚");
					order.setOshare("1");
					order.setShareFee(xmTaskDb.getShareFee());
					if(order.getShareFee()==null || order.getShareFee().compareTo(BigDecimal.ZERO)<0){
						return Result.error("shareFee-0","分享佣金不能为空");
					}
					originFee=originFee.add(order.getShareFee());
				}
				order.setName("参加任务推广活动【"+marketNames.stream().collect(Collectors.joining(","))+"】");
				order.setRemark(order.getName());
			}


			if(order.getOthFee()==null){
				order.setOthFee(BigDecimal.ZERO);
			}
			order.setOriginFee(originFee);
			order.setDiscount(100);
			order.setFinalFee(originFee.add(order.getOthFee()));
			order.setPayType(xmTaskOrder.getPayType());
			order.setOstatus("2");
			order.setPayStatus("0");
			order.setBizType(xmTaskOrder.getBizType());
			order.setCtime(new Date());
			order.setLtime(new Date());
			if(!xmTaskOrder.isCalc()){
				xmTaskOrderService.insert(order);
				String remark="任务保证金";
				if("1".equals(xmTaskOrder.getBizType())){
					remark="任务保证金";
				}else{
					remark="任务推广佣金";
				}
				msgService.pushMsg(user,user.getUserid(),user.getUsername(),"您为任务支付"+remark+order.getFinalFee()+"元订单提交成功，请及时付款",null);
			}
		return Result.ok();
	}
	
	/**
	@ApiOperation( value = "删除一条任务相关费用订单表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delXmTaskOrder(@RequestBody XmTaskOrder xmTaskOrder){

            if(!StringUtils.hasText(xmTaskOrder.getId())) {
                 return Result.error("pk-not-exists","请上送主键参数id");
            }
            XmTaskOrder xmTaskOrderDb = xmTaskOrderService.selectOneObject(xmTaskOrder);
            if( xmTaskOrderDb == null ){
                return Result.error("data-not-exists","数据不存在，无法删除");
            }
			xmTaskOrderService.deleteByPk(xmTaskOrder);
		return Result.ok();
		
	}
	 */
	
	/**
	@ApiOperation( value = "根据主键修改一条任务相关费用订单表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmTaskOrder.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editXmTaskOrder(@RequestBody XmTaskOrder xmTaskOrder) {

            if(!StringUtils.hasText(xmTaskOrder.getId())) {
                 return Result.error("pk-not-exists","请上送主键参数id");
            }
            XmTaskOrder xmTaskOrderDb = xmTaskOrderService.selectOneObject(xmTaskOrder);
            if( xmTaskOrderDb == null ){
                return Result.error("data-not-exists","数据不存在，无法修改");
            }
			xmTaskOrderService.updateSomeFieldByPk(xmTaskOrder);
		
	}
	*/

	/**
    @ApiOperation( value = "批量修改某些字段",notes="")
    @ApiEntityParams( value = XmTaskOrder.class, props={ }, remark = "任务相关费用订单表", paramType = "body" )
	@ApiResponses({
			@ApiResponse(code = 200,response=XmTaskOrder.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields( @ApiIgnore @RequestBody Map<String,Object> xmTaskOrderMap) {

            List<String> ids= (List<String>) xmTaskOrderMap.get("ids");
			if(ids==null || ids.size()==0){
				return Result.error("ids-0","ids不能为空");
			}

			Set<String> fields=new HashSet<>();
            fields.add("id");
			for (String fieldName : xmTaskOrderMap.keySet()) {
				if(fields.contains(fieldName)){
					return Result.error(fieldName+"-no-edit",fieldName+"不允许修改");
				}
			}
			Set<String> fieldKey=xmTaskOrderMap.keySet().stream().filter(i-> fieldsMap.containsKey(i)).collect(Collectors.toSet());
			fieldKey=fieldKey.stream().filter(i->xmTaskOrderMap.get(i)!=null).collect(Collectors.toSet());

			if(fieldKey.size()<=0) {
				return Result.error("fieldKey-0","没有需要更新的字段");
 			}
			XmTaskOrder xmTaskOrder = fromMap(xmTaskOrderMap,XmTaskOrder.class);
			List<XmTaskOrder> xmTaskOrdersDb=xmTaskOrderService.selectListByIds(ids);
			if(xmTaskOrdersDb==null ||xmTaskOrdersDb.size()==0){
				return Result.error("data-0","记录已不存在");
			}
			List<XmTaskOrder> can=new ArrayList<>();
			List<XmTaskOrder> no=new ArrayList<>();
			User user = LoginUtils.getCurrentUserInfo();
			for (XmTaskOrder xmTaskOrderDb : xmTaskOrdersDb) {
 				Tips tips2 = new Tips("检查通过"); 
				if(!tips2.isOk()){
				    no.add(xmTaskOrderDb); 
				}else{
					can.add(xmTaskOrderDb);
				}
			}
			if(can.size()>0){
                xmTaskOrderMap.put("ids",can.stream().map(i->i.getId()).collect(Collectors.toList()));
			    xmTaskOrderService.editSomeFields(xmTaskOrderMap); 
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
	@ApiOperation( value = "根据主键列表批量删除任务相关费用订单表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelXmTaskOrder(@RequestBody List<XmTaskOrder> xmTaskOrders) {
		
        
        
            if(xmTaskOrders.size()<=0){
                return Result.error("data-0","请上送待删除数据列表");
            }
             List<XmTaskOrder> datasDb=xmTaskOrderService.selectListByIds(xmTaskOrders.stream().map(i-> i.getId() ).collect(Collectors.toList()));

            List<XmTaskOrder> can=new ArrayList<>();
            List<XmTaskOrder> no=new ArrayList<>();
            for (XmTaskOrder data : datasDb) {
                if(true){
                    can.add(data);
                }else{
                    no.add(data);
                } 
            }
            List<String> msgs=new ArrayList<>();
            if(can.size()>0){
                xmTaskOrderService.batchDelete(can);
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


	@ApiOperation( value = "通过Id获取订单",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	})
	@RequestMapping(value="/getOrderById",method=RequestMethod.GET)
	public Result getOrderById(String orderId) {
		
		
		if(!StringUtils.hasText(orderId)) {
			return Result.error("data-0","订单Id不能为空");
		}
		XmTaskOrder moOrder = xmTaskOrderService.selectOneById(orderId);
		return Result.ok().setData(moOrder);
	}

	@ApiOperation( value = "通过Id获取订单",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	})
	@RequestMapping(value="/orderPaySuccess",method=RequestMethod.POST)
	public Result orderPaySuccess(@RequestBody XmTaskOrder order) {

			if(!StringUtils.hasText(order.getId())) {
				return Result.error("data-0","订单Id不能为空");
			}
			String flag= (String) this.cacheHKVService.getValue("pay-notify-success-"+order.getPayId());
			if(!StringUtils.hasText(flag)|| !"1".equals(flag)){
				return Result.error("pay-notify-success-flag-0","验证码错误");
			}
			xmTaskOrderService.orderPaySuccess(order.getId(),order.getPayId(),order.getPrepayId(), order.getTranId(), order.getPayAt(), order.getRemark());
			return Result.ok();
	}

	@ApiOperation( value = "订单支付取消判断",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	})
	@RequestMapping(value="/payCancel",method=RequestMethod.POST)
	public Result payCancel(@RequestBody XmTaskOrder order) {

			if(!StringUtils.hasText(order.getId())) {
				return Result.error("data-0","订单Id不能为空");
			}
			String flag= (String) this.cacheHKVService.getValue("pay-notify-cancel-"+order.getPayId());
			if(!StringUtils.hasText(flag)|| !"1".equals(flag)){
				return Result.error("pay-notify-cancel-flag-0","验证码错误");
			}
			this.xmTaskOrderService.payCancel(order.getId(),order.getPayId(), order.getRemark());
 			return Result.ok();
	}
	@ApiOperation( value = "修改订单的第三方流水号",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	})
	@RequestMapping(value="/updatePrepayId",method=RequestMethod.POST)
	public Result updatePrepayId(@RequestBody  XmTaskOrder order) {
		
		
		if(!StringUtils.hasText(order.getId())) {
			return Result.error("data-0","订单Id不能为空");
		}
		XmTaskOrder moOrder = new XmTaskOrder();
		moOrder.setId(order.getId());
		moOrder.setPayId(order.getPayId());
		moOrder.setPrepayId(order.getPrepayId());
		moOrder.setPayTime(new Date());
		xmTaskOrderService.updateSomeFieldByPk(moOrder);
		return Result.ok();
	}
}
