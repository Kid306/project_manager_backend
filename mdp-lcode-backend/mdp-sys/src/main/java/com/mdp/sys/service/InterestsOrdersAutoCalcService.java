package com.mdp.sys.service;

import com.mdp.core.api.CacheHKVService;
import com.mdp.core.utils.DateUtils;
import com.mdp.sys.entity.InterestsOrders;
import com.mdp.sys.entity.User;
import com.mdp.sys.entity.UserInterests;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br> 
 * 组织 com  顶级模块 mk 大模块 mem 小模块 <br>
 * 实体 InterestsOrders 表 mem_interests_orders 当前主键(包括多主键): userid; 
 ***/
@Service("sys.interestsOrdersAutoCalcService")
public class InterestsOrdersAutoCalcService {
	static Logger logger =LoggerFactory.getLogger(InterestsOrdersAutoCalcService.class);

	@Autowired
	InterestsOrdersService interestsOrdersService;

	@Autowired
    CacheHKVService cacheHKVService;

	@Autowired
    UserService userService;

    @Autowired
	UserInterestsService memberInterestsService;

    /**
     * 定时计算待执行会员续费订单
     */
	@Transactional
    public void toExecOrders(InterestsOrders orderDb) {
         if(!"2".equals(orderDb.getCalcState())){
             return;
         }
         if(!"2".equals(orderDb.getCalcState())){
             return;
         }
         if(!"1".equals(orderDb.getPayStatus())){
             return;
         }
         if(StringUtils.isEmpty(orderDb.getRtime())){
             return;
         }

        UserInterests memberInterests=new UserInterests();
        memberInterests.setUserid(orderDb.getUserid());
        memberInterests.setBids(orderDb.getBids());
        memberInterests.setIsFree(orderDb.getIsFree());
        memberInterests.setMfee(orderDb.getMfee());
        memberInterests.setMtype(orderDb.getMtype());
        memberInterests.setItype(orderDb.getItype());
        memberInterests.setIlevel(orderDb.getIlevel());
        memberInterests.setBids(orderDb.getBids());
        memberInterests.setSmaxAt(orderDb.getSmaxAt());
        memberInterests.setSmaxExp(orderDb.getSmaxExp());
        memberInterests.setSfeeRate(orderDb.getSfeeRate());
        memberInterests.setIlevel(orderDb.getIlevel());
        memberInterests.setRtimeType("2");
        memberInterests.setRtimeRule("1");
        memberInterests.setRtime(orderDb.getRtime());
        this.memberInterestsService.updateSomeFieldByPk(memberInterests);
        User memberUpdate=new User();
        memberUpdate.setUserid(memberInterests.getUserid());
        memberUpdate.setIlvlId(orderDb.getIlvlId());
        memberUpdate.setIlvlName(orderDb.getIlvlName());
        memberUpdate.setIetime(DateUtils.parse(memberInterests.getRtime(),"yyyy-MM-dd"));
        memberUpdate.setIstime(new Date());
        memberUpdate.setIstatus("1");
        this.userService.updateSomeFieldByPk(memberUpdate);

        InterestsOrders update=new InterestsOrders();
        update.setId(orderDb.getId());
        update.setCalcStatus("1");
        update.setCalcExecTime(new Date());
        this.interestsOrdersService.updateSomeFieldByPk(update);

	}


    /**
     * 定时计算待执行会员续费订单
     * 每5分钟执行一次
     */
	@Scheduled(cron = "0 0/5 * * * ?")
	public void autoCalcXuFei(){
        List<InterestsOrders> ordersList= interestsOrdersService.selectListNeedCalc();
        if(ordersList==null || ordersList.size()<=0){
            return;
        }
        for (InterestsOrders order : ordersList) {
            try{
                boolean exists=cacheHKVService.setIfAbsent(InterestsOrdersAutoCalcService.class.getSimpleName()+"-"+order.getId(),"1",3, TimeUnit.HOURS);
                if(exists){
                    continue;
                }
                this.toExecOrders(order);
            }catch (Exception e){
                logger.error("定时计算待执行会员续费订单出错",e);
            }

        }
    }

}

