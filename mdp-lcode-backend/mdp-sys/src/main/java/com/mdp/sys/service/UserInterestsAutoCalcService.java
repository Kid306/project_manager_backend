package com.mdp.sys.service;

import com.mdp.core.api.CacheHKVService;
import com.mdp.core.utils.DateUtils;
import com.mdp.sys.entity.User;
import com.mdp.sys.entity.UserInterests;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br> 
 * 组织 com  顶级模块 mk 大模块 mem 小模块 <br>
 * 实体 InterestsOrders 表 mem_interests_orders 当前主键(包括多主键): userid; 
 ***/
@Service("sys.userInterestsAutoCalcService")
public class UserInterestsAutoCalcService {
	static Logger logger =LoggerFactory.getLogger(UserInterestsAutoCalcService.class);



	@Autowired
    CacheHKVService cacheHKVService;

	@Autowired
    UserService userService;

    @Autowired
	UserInterestsService memberInterestsService;

    @Autowired
    InterestsService interestsService;



    /**
     * 定时计算待恢复普通会员的订单
     * 每5分钟执行一次
     */
    @Scheduled(cron = "0 0/5 * * * ?")
    public void autoCalcOver(){
        List<UserInterests> userInterestsList= memberInterestsService.selectNeedOverUser();
        if(userInterestsList==null || userInterestsList.size()<=0){
            return;
        }
        for (UserInterests interests : userInterestsList) {
            try{
                boolean exists=cacheHKVService.setIfAbsent(UserInterestsAutoCalcService.class.getSimpleName()+"-"+interests.getUserid(),"1",3, TimeUnit.HOURS);
                if(exists){
                    continue;
                }
                this.toExecOver(interests);
            }catch (Exception e){
                logger.error("定时计算待执行会员续费订单出错",e);
            }

        }
    }

    /**
     * 将用户会员状态取消，恢复到普通状态
     * @param interests
     */
    private void toExecOver(UserInterests interests) {
        if(!StringUtils.hasText(interests.getRtime())){
            return;
        }
        Date rtime=DateUtils.parse(interests.getRtime());
        Date now=new Date();
        now=DateUtils.parse(DateUtils.format(now,"yyyy-MM-dd"),"yyyy-MM-dd");
        if(rtime.after(now)){
            return;
        }
        UserInterests interestsDef= memberInterestsService.getDefaultUserInterests(interests.getUserid());
        User userUpdate=new User();
        userUpdate.setUserid(interests.getUserid());
        userUpdate.setIlvlId("1");
        userUpdate.setIlvlName("普通会员");
        userUpdate.setIstatus("1");
        userUpdate.setIetime(null);
        userUpdate.setIstime(null);
        userUpdate.setLtime(new Date());
        userService.interestsOverUpdate(userUpdate);

        UserInterests userInterestsUpdate=new UserInterests();
        userInterestsUpdate.setUserid(interests.getUserid());
        userInterestsUpdate.setSmaxExp(interestsDef.getSmaxExp());
        userInterestsUpdate.setIlevel(1);
        userInterestsUpdate.setMfee(interestsDef.getMfee());
        userInterestsUpdate.setSfeeRate(interests.getSfeeRate());
        userInterestsUpdate.setBids(interestsDef.getBids());
        userInterestsUpdate.setSmaxAt(interestsDef.getSmaxAt());
        userInterestsUpdate.setRtime(null);
        userInterestsUpdate.setLtime(new Date());
        memberInterestsService.interestsOverUpdate(userInterestsUpdate);
    }

}

