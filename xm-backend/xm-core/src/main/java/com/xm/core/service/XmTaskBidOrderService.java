package com.xm.core.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.api.CacheHKVService;
import com.mdp.core.err.BizException;
import com.mdp.core.service.BaseService;
import com.mdp.msg.client.PushNotifyMsgService;
import com.xm.core.entity.XmTask;
import com.xm.core.entity.XmTaskBidOrder;
import com.xm.core.mapper.XmTaskBidOrderMapper;
import com.xm.core.service.client.AcClient;
import com.xm.core.vo.AddXmTaskBidOrderVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br> 
 * 组织 com  顶级模块 xm 大模块 core 小模块 <br>
 * 实体 XmTaskBidOrder 表 xm_task_bid_order 当前主键(包括多主键): id; 
 ***/
@Service("xm.core.xmTaskBidOrderService")
public class XmTaskBidOrderService extends BaseService<XmTaskBidOrderMapper,XmTaskBidOrder> {
	static Logger logger =LoggerFactory.getLogger(XmTaskBidOrderService.class);

	@Autowired
	XmTaskService xmTaskService;

	@Autowired
	PushNotifyMsgService msgService;

	@Autowired
	XmRecordService xmRecordService;

	@Autowired
	XmTaskExecuserService execuserService;

	@Autowired
    CacheHKVService cacheHKVService;



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
		XmTaskBidOrder taskOrderDb = this.selectOneById(orderId);
		XmTask xmTaskUpdate=new XmTask();
		xmTaskUpdate.setId(taskOrderDb.getTaskId());
		if (!payId.equals(taskOrderDb.getPayId())) {
			throw new BizException("payId-err", "参数不正确，payId与实际不匹配");
		}
		if (!"2".equals(taskOrderDb.getOstatus())) {
			throw new BizException("该订单状态出错");
		}
		XmTaskBidOrder order=new XmTaskBidOrder();
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


 		String json= (String) cacheHKVService.getValue(XmTaskBidOrder.class.getSimpleName()+"-"+orderId);
		AddXmTaskBidOrderVo bidOrderVo=JSON.parseObject(json,AddXmTaskBidOrderVo.class);
		execuserService.addExecuser(bidOrderVo,true);

		this.updateSomeFieldByPk(order);

	}
	public void payCancel(String orderId, String payId, String remark) {
		XmTaskBidOrder orderDb = this.selectOneById(orderId);
		if(StringUtils.isEmpty(orderDb.getPayId()) ||orderDb.getPayId().equals(payId)){
			XmTaskBidOrder orderUpdate=new XmTaskBidOrder();
			orderUpdate.setId(orderId);
			orderUpdate.setRemark(remark);
			orderUpdate.setPayStatus("2");
			orderUpdate.setLtime(new Date());
			super.updateSomeFieldByPk(orderUpdate);
		}
	}

}

