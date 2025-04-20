package com.xm.core.ctrl;

import com.alibaba.fastjson.JSON;
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
import com.xm.core.entity.XmTaskBidOrder;
import com.xm.core.service.XmTaskBidOrderService;
import com.xm.core.service.XmTaskService;
import com.xm.core.service.client.SysClient;
import com.xm.core.vo.AddXmTaskBidOrderVo;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.mdp.core.utils.BaseUtils.toMap;

/**
 * url编制采用rest风格,如对xm_task_bid_order 任务相关费用订单表的操作有增删改查,对应的url分别为:<br>
 * 组织 com  顶级模块 xm 大模块 core 小模块 <br>
 * 实体 XmTaskBidOrder 表 xm_task_bid_order 当前主键(包括多主键): id; 
 ***/
@RestController("xm.core.xmTaskBidOrderController")
@RequestMapping(value="/xm/core/xmTaskBidOrder")
@Api(tags={"任务相关费用订单表操作接口"})
public class XmTaskBidOrderController {
	
	static Logger logger =LoggerFactory.getLogger(XmTaskBidOrderController.class);
	
	@Autowired
	private XmTaskBidOrderService xmTaskBidOrderService;
	
	@Autowired
	XmTaskService xmTaskService;
	
	@Autowired
	ItemService itemService;
	
	@Autowired
	PushNotifyMsgService msgService;

	@Autowired
    CacheHKVService cacheHKVService;

	@Autowired
	SysClient sysClient;
	 

	Map<String,Object> fieldsMap = toMap(new XmTaskBidOrder());
 
	
	@ApiOperation( value = "查询任务相关费用订单表信息列表",notes=" ")
	@ApiEntityParams( XmTaskBidOrder.class )
	@ApiImplicitParams({
			@ApiImplicitParam(name="pageSize",value="每页大小，默认20条",required=false),
			@ApiImplicitParam(name="pageNum",value="当前页码,从1开始",required=false),
			@ApiImplicitParam(name="total",value="总记录数,服务器端收到0时，会自动计算总记录数，如果上传>0的不自动计算",required=false),
			@ApiImplicitParam(name="count",value="是否计算总记录条数，如果count=true,则计算计算总条数，如果count=false 则不计算",required=false),
			@ApiImplicitParam(name="orderBy",value="排序列 如性别、学生编号排序 orderBy = sex desc,student desc",required=false),
 	})
	@ApiResponses({
		@ApiResponse(code = 200,response=XmTaskBidOrder.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listXmTaskBidOrder(@ApiIgnore @RequestParam Map<String,Object> params){
		
		
		RequestUtils.transformArray(params, "ids");		
		IPage page=QueryTools.initPage(params);
		QueryWrapper<XmTaskBidOrder> qw = QueryTools.initQueryWrapper(XmTaskBidOrder.class , params);
		List<Map<String,Object>> datas = xmTaskBidOrderService.selectListMapByWhere(page,qw,params);
			return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());	//列出XmTaskBidOrder列表

	}



	@ApiOperation( value = "计算订单金额",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200,response= AddXmTaskBidOrderVo.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/calcOrder",method= RequestMethod.GET)
	public Result calcOrder(  AddXmTaskBidOrderVo xmTaskBidOrder) {
		xmTaskBidOrder.setCalc(true);
		return  addXmTaskBidOrder(xmTaskBidOrder);
	}

	@ApiOperation( value = "新增一条任务相关费用订单表信息",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200,response= XmTaskBidOrder.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addXmTaskBidOrder(@RequestBody AddXmTaskBidOrderVo bidOrderVo) {

			if(!StringUtils.hasText(bidOrderVo.getTaskId())){
				return Result.error("taskId-0","任务编号不能为空");
			}
			XmTask xmTaskDb=this.xmTaskService.selectOneById(bidOrderVo.getTaskId());
			if(xmTaskDb==null){
				return Result.error("data-0","任务已不存在");
			}
			if(!"1".equals(xmTaskDb.getTaskOut())){
				return Result.error("taskOut-0","不是外包任务，无须付款");
			}

			if(!"1".equals(xmTaskDb.getCrowd())){
				return Result.error("taskOut-0","不是众包任务，无须付款");
			}
			if(!"2".equals(xmTaskDb.getBidStep()) ){
				return Result.error("bidStep-no-2","当前任务不是投标阶段，无须购买投标直通车");
			}
			User user= LoginUtils.getCurrentUserInfo();
			User userInterests=sysClient.getUserInterestsByUserid(user.getUserid());

			bidOrderVo.setBidBranchId(user.getBranchId());
			bidOrderVo.setBidUsername(user.getUsername());
			bidOrderVo.setBranchId(xmTaskDb.getCbranchId());
			bidOrderVo.setProjectId(xmTaskDb.getProjectId());
			bidOrderVo.setBidDirect("1");
			bidOrderVo.setCmonthBids(NumberUtil.getInteger(userInterests.get("cmonthBids"),0));
			bidOrderVo.setCmonthExp(NumberUtil.getBigDecimal(userInterests.get("cmonthExp"),BigDecimal.ZERO));
			bidOrderVo.setSrvTimes(NumberUtil.getInteger(userInterests.get("srvTimes"),0));
			bidOrderVo.setIlvlId(userInterests.getIlvlId());
			bidOrderVo.setGradeId(userInterests.getGradeId());
			bidOrderVo.setGuardId(userInterests.getGuardId());
			bidOrderVo.setCreditId(userInterests.getCreditId());

			XmTaskBidOrder order=new XmTaskBidOrder();
			order.setId(this.xmTaskBidOrderService.createKey("id"));
			order.setTaskId(xmTaskDb.getId());
			order.setOuserid(user.getUserid());
			order.setObranchId(user.getBranchId());
			order.setProjectId(xmTaskDb.getProjectId());
			order.setTaskBudgetAt(xmTaskDb.getBudgetAt());
			order.setName(xmTaskDb.getName());
			order.setBizType("1");
			order.setOtype("7");
			BigDecimal originFee=BigDecimal.ZERO; 
				ItemVo itemVo=itemService.getDict("sysParam","crowd_task_bid_sfee");
				BigDecimal bidFeeRate=BigDecimal.valueOf(0.1);
				BigDecimal maxFee=BigDecimal.valueOf(1000);
				BigDecimal minFee=BigDecimal.valueOf(0.1);
				if(itemVo!=null && itemVo.getExtInfos()!=null){
					bidFeeRate=NumberUtil.getBigDecimal(itemVo.getExtInfo("bidFeeRate").getValue(),BigDecimal.valueOf(0.1));
					maxFee=NumberUtil.getBigDecimal(itemVo.getExtInfo("maxFee").getValue(),BigDecimal.valueOf(1000));

					minFee=NumberUtil.getBigDecimal(itemVo.getExtInfo("minFee").getValue(),BigDecimal.valueOf(0.1));

				}

				originFee=originFee.add(order.getTaskBudgetAt().multiply( bidFeeRate ).divide(BigDecimal.valueOf(100)));
  				if(originFee.compareTo(maxFee)>0){
					originFee=maxFee;
				}
  				if(originFee.compareTo(minFee)<0){
  					originFee=minFee;
				}
			    originFee=originFee.setScale(2,BigDecimal.ROUND_HALF_UP);
				order.setName("购买投标直通车，任务【"+xmTaskDb.getName()+"】");
				order.setRemark(order.getName());
			 
			if(order.getOthFee()==null){
				order.setOthFee(BigDecimal.ZERO);
			}
			order.setOriginFee(originFee);
			order.setFinalFee(originFee.add(order.getOthFee()));
			order.setPayType(bidOrderVo.getPayType());
			order.setOstatus("2");
			order.setPayStatus("0");
			order.setBizType("1");
			order.setCtime(new Date());
			order.setLtime(new Date());
			if(!bidOrderVo.isCalc()){
				xmTaskBidOrderService.insert(order);
				cacheHKVService.setValue(XmTaskBidOrder.class.getSimpleName()+"-"+order.getId(), JSON.toJSONString(bidOrderVo),1, TimeUnit.HOURS);
				String remark="投标直通车费用";
				msgService.pushMsg(user,user.getUserid(),user.getUsername(),"您为任务支付"+remark+order.getFinalFee()+"元订单提交成功，请及时付款",null);
			}
			
		return Result.ok();
		
	}


	@ApiOperation( value = "通过Id获取订单",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	})
	@RequestMapping(value="/getOrderById",method=RequestMethod.GET)
	public Result getOrderById(String orderId) {
		
		
		if(!StringUtils.hasText(orderId)) {
			return Result.error("data-0","订单Id不能为空");
		}
		XmTaskBidOrder moOrder = xmTaskBidOrderService.selectOneById(orderId);
		return Result.ok().setData(moOrder);
	}

	@ApiOperation( value = "通过Id获取订单",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	})
	@RequestMapping(value="/orderPaySuccess",method=RequestMethod.POST)
	public Result orderPaySuccess(@RequestBody XmTaskBidOrder order) {

			
			if(!StringUtils.hasText(order.getId())) {
				return Result.error("data-0","订单Id不能为空");
			}
			String flag= (String) this.cacheHKVService.getValue("pay-notify-success-"+order.getPayId());
			if(!StringUtils.hasText(flag)|| !"1".equals(flag)){
				return Result.error("pay-notify-success-flag-0","验证码错误");
			}
			xmTaskBidOrderService.orderPaySuccess(order.getId(),order.getPayId(),order.getPrepayId(), order.getTranId(), order.getPayAt(), order.getRemark());
 			return Result.ok();
	}

	@ApiOperation( value = "订单支付取消判断",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	})
	@RequestMapping(value="/payCancel",method=RequestMethod.POST)
	public Result payCancel(@RequestBody XmTaskBidOrder order) {

			if(!StringUtils.hasText(order.getId())) {
				return Result.error("data-0","订单Id不能为空");
			}
			String flag= (String) this.cacheHKVService.getValue("pay-notify-cancel-"+order.getPayId());
			if(!StringUtils.hasText(flag)|| !"1".equals(flag)){
				return Result.error("pay-notify-cancel-flag-0","验证码错误");
			}
			this.xmTaskBidOrderService.payCancel(order.getId(),order.getPayId(), order.getRemark());
			return Result.ok();
	}
	@ApiOperation( value = "修改订单的第三方流水号",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	})
	@RequestMapping(value="/updatePrepayId",method=RequestMethod.POST)
	public Result updatePrepayId(@RequestBody XmTaskBidOrder order) {
		
		
		if(!StringUtils.hasText(order.getId())) {
			return Result.error("data-0","订单Id不能为空");
		}
		XmTaskBidOrder moOrder = new XmTaskBidOrder();
		moOrder.setId(order.getId());
		moOrder.setPayId(order.getPayId());
		moOrder.setPrepayId(order.getPrepayId());
		moOrder.setPayTime(new Date());
		xmTaskBidOrderService.updateSomeFieldByPk(moOrder);
		return Result.ok();
	}
}
