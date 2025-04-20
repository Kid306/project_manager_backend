package com.xm.core.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.err.BizException;
import com.mdp.core.service.BaseService;
import com.mdp.msg.client.PushNotifyMsgService;
import com.xm.core.entity.XmTask;
import com.xm.core.entity.XmTaskOrder;
import com.xm.core.mapper.XmTaskOrderMapper;
import com.xm.core.service.client.AcClient;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br> 
 * 组织 com  顶级模块 xm 大模块 core 小模块 <br>
 * 实体 XmTaskOrder 表 xm_task_order 当前主键(包括多主键): id; 
 ***/
@Service("xm.core.xmTaskOrderService")
public class XmTaskOrderService extends BaseService<XmTaskOrderMapper,XmTaskOrder> {
	static Logger logger =LoggerFactory.getLogger(XmTaskOrderService.class);

	@Autowired
	XmTaskService xmTaskService;

	@Autowired
	PushNotifyMsgService msgService;

	@Autowired
	XmRecordService xmRecordService;



	@Autowired
	AcClient acClient;

	/**
	 * 自定义查询，支持多表关联
	 * @param page 分页条件
	 * @param ew 一定要，并且必须加@Param("ew")注解
	 * @param ext 如果xml中需要根据某些值进行特殊处理，可以通过这个进行传递，非必须，注解也可以不加
	 * @return
	 */
	public List<Map<String,Object>> selectListMapByWhere(IPage page, QueryWrapper ew, Map<String,Object> ext){
		return baseMapper.selectListMapByWhere(page,ew,ext);
	}
	@Transactional
	public void orderPaySuccess(String orderId, String payId, String prepayId, String tranId, BigDecimal payAt, String remarks) {
		//更新订单状态

		if (!StringUtils.hasText(payId)) {
			throw new BizException("payId-0", "参数不正确，payId不能为空");
		}
		XmTaskOrder taskOrderDb = this.selectOneById(orderId);
		XmTask xmTaskUpdate=new XmTask();
		xmTaskUpdate.setId(taskOrderDb.getTaskId());
		if (!payId.equals(taskOrderDb.getPayId())) {
			throw new BizException("payId-err", "参数不正确，payId与实际不匹配");
		}
		if (!"2".equals(taskOrderDb.getOstatus())) {
			throw new BizException("该订单状态出错");
		}
		XmTaskOrder order=new XmTaskOrder();
		//设置第三方付款号
		order.setTranId(tranId);
		order.setPayAt(payAt);
		//设置结算状态为已结算
		order.setPayStatus("1");
		//设置状态为已付款
		order.setOstatus("3");
		//设置付款确认时间
		order.setPayTime(new Date());

		List<String> marketNames=new ArrayList<>();
		order.setId(taskOrderDb.getId() );
		if("1".equals(taskOrderDb.getBizType())){
			if("1".equals(taskOrderDb.getEstate()) && taskOrderDb.getEfunds()!=null && taskOrderDb.getEfunds().compareTo(BigDecimal.ZERO)>0){
				order.setEstate("2");
				order.setEtoPlatTime(new Date());
			}
		}else if("2".equals(taskOrderDb.getBizType())){
			if("1".equals(taskOrderDb.getTop())){
				order.setTop("2");
				order.setTopStime(new Date());
				order.setTopEtime(DateUtils.addDays(new Date(),taskOrderDb.getTopDays()));
				marketNames.add("置顶");
			}
			if("1".equals(taskOrderDb.getHot())){
				order.setHot("2");
				order.setHotStime(new Date());
				order.setHotEtime(DateUtils.addDays(new Date(),taskOrderDb.getHotDays()));
				marketNames.add("上热门");
			}
			if("1".equals(taskOrderDb.getUrgent())){
				order.setUrgent("2");
				order.setUrgentStime(new Date());
				order.setUrgentEtime(DateUtils.addDays(new Date(),taskOrderDb.getUrgentDays()));
				marketNames.add("加急");
			}
			if("1".equals(taskOrderDb.getCrmSup())){
				order.setCrmSup("2");
				marketNames.add("客服包办");
			}

			if("1".equals(taskOrderDb.getOshare())&& taskOrderDb.getShareFee()!=null && taskOrderDb.getShareFee().compareTo(BigDecimal.ZERO)>0){
				order.setOshare("2");
				marketNames.add("分享赚");
			}
		}

		BeanUtils.copyProperties(order,xmTaskUpdate);
		xmTaskUpdate.setId(taskOrderDb.getTaskId());
		//托管资金后用户开始工作
		if("1".equals(taskOrderDb.getBizType()) && "2".equals(xmTaskUpdate.getEstate()) && "1".equals(taskOrderDb.getEstate())){
			xmTaskUpdate.setBidStep("5");
			xmTaskUpdate.setEfunds(payAt);
		}
		this.updateSomeFieldByPk(order);
		this.xmTaskService.updateSomeFieldByPkAfterPaySuccess(xmTaskUpdate);
		if("1".equals(taskOrderDb.getBizType()) && "5".equals(xmTaskUpdate.getBidStep())){
			XmTask xmTaskDb=this.xmTaskService.selectOneById(taskOrderDb.getTaskId());
			msgService.pushMsg(taskOrderDb.getObranchId(),taskOrderDb.getOuserid(),xmTaskDb.getExecutorUserid(),"2",xmTaskDb.getProjectId(),xmTaskDb.getId(),"雇主成功托管佣金【"+taskOrderDb.getEfunds()+"】，实际到账金额【"+payAt+"】，当前任务进入用户工作阶段，请尽快开展工作。");
			xmRecordService.addXmTaskRecord(taskOrderDb.getProjectId(),taskOrderDb.getTaskId(),"托管佣金","成功托管佣金【"+taskOrderDb.getEfunds()+"】，实际到账金额【"+payAt+"】");
		}else{

			xmRecordService.addXmTaskRecord(taskOrderDb.getProjectId(),taskOrderDb.getTaskId(),"营销活动","成功缴纳活动费用金额【"+taskOrderDb.getFinalFee()+"】，实际到账金额【"+payAt+"】。参加的活动为【"+marketNames.stream().collect(Collectors.joining(","))+"】");
		}
	}
	public void payCancel(String orderId, String payId, String remark) {
		XmTaskOrder orderDb = this.selectOneById(orderId);
		if(StringUtils.isEmpty(orderDb.getPayId()) ||orderDb.getPayId().equals(payId)){
			XmTaskOrder orderUpdate=new XmTaskOrder();
			orderUpdate.setId(orderId);
			orderUpdate.setRemark(remark);
			orderUpdate.setPayStatus("2");
			orderUpdate.setLtime(new Date());
			super.updateSomeFieldByPk(orderUpdate);
		}
	}

}

