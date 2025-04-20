package com.mdp.sys.service;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.err.BizException;
import com.mdp.core.service.BaseService;
import com.mdp.sys.entity.GuardOrder;
import com.mdp.sys.entity.User;
import com.mdp.sys.entity.UserInterests;
import com.mdp.sys.mapper.GuardOrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br> 
 * 组织 com  顶级模块 mdp 大模块 sys 小模块 <br>
 * 实体 GuardOrder 表 sys_guard_order 当前主键(包括多主键): id; 
 ***/
@Service("mdp.sys.guardOrderService")
public class GuardOrderService extends BaseService<GuardOrderMapper,GuardOrder>{
	static Logger logger =LoggerFactory.getLogger(GuardOrderService.class);


	@Autowired
	UserInterestsService userInterestsService;

	@Autowired
	UserCreditRecordService creditRecordService;

	@Autowired
	UserService userService;
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

		if(!StringUtils.hasText(payId)){
			throw new BizException("payId-0","参数不正确，payId不能为空");
		}
		GuardOrder guardOrder = this.selectOneById(orderId);
		if(!payId.equals(guardOrder.getPayId())) {
			throw new BizException("payId-err", "参数不正确，payId与实际不匹配");
		}

		if("1".equals(guardOrder.getSstatus())) {
			throw new BizException("该订单已经结算");
		}
		if(!"2".equals(guardOrder.getStatus())) {
			throw new BizException("该订单状态出错");
		}

//        if(payAt.compareTo(guardOrder.getOfinalFee()) != 0) {
//            throw new BizException("该订单支付金额与实际金额不符");
//        }

		//设置第三方付款号
		guardOrder.setTranId(tranId);
		guardOrder.setPayAt(payAt);
		//设置结算状态为已结算
		guardOrder.setSstatus("1");
		//设置状态为已付款
		guardOrder.setStatus("3");
		//设置付款确认时间
		guardOrder.setPayCtime(new Date());

		//设置结算时间
		guardOrder.setSetTime(new Date());


		guardOrder.setStartTime(new Date());
		guardOrder.setEndTime(DateUtil.offsetDay(guardOrder.getStartTime(),360));
		if(guardOrder.getPayTime()==null){
			guardOrder.setPayTime(new Date());
		}
		guardOrder.setPayStatus("1");
		//保存订单信息
		this.updateSomeFieldByPk(guardOrder);

		UserInterests userInterests= userInterestsService.selectOneById(guardOrder.getOuserid());
		boolean isNull=userInterests==null;
		if(isNull){
			userInterests=this.userInterestsService.getDefaultInterests(guardOrder.getOuserid());
		}
			guardOrder.setOoper("2");
			if(userInterests.getGuardRtime()==null){
				userInterests.setGuardRtime(new Date());
			}
			guardOrder.setStartTime(userInterests.getGuardRtime());
			Calendar calendar=Calendar.getInstance();

			calendar.setTime(userInterests.getGuardRtime());
			calendar.set(Calendar.YEAR,calendar.get(Calendar.YEAR)+1);
			guardOrder.setEndTime(calendar.getTime());
			userInterests.setGuardName(guardOrder.getGuardName());
			userInterests.setGuardRtime(guardOrder.getEndTime());

			 User user=new User();
			 user.setUserid(guardOrder.getOuserid());
			 user.setGuardId(guardOrder.getGuardId());
			 this.userService.updateSomeFieldByPk(user);
		if(isNull){
			userInterestsService.insert(userInterests);
		}else {
			userInterestsService.updateSomeFieldByPk(userInterests);
		}

		creditRecordService.addCreditScore(userInterests.getUserid(),"10",guardOrder.getGuardId(),guardOrder.getName());


	}

    public void payCancel(String orderId, String payId, String remark) {
		GuardOrder guardOrder = this.selectOneById(orderId);
		if(StringUtils.isEmpty(guardOrder.getPayId()) ||guardOrder.getPayId().equals(payId)){
			GuardOrder orderUpdate=new GuardOrder();
			orderUpdate.setId(orderId);
			orderUpdate.setRemark(remark);
			orderUpdate.setPayStatus("2");
			orderUpdate.setStatus("5");
			orderUpdate.setCloseTime(new Date());
			super.updateSomeFieldByPk(orderUpdate);
		}
    }
}

