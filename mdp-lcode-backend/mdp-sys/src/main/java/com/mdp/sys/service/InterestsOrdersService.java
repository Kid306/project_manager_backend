package com.mdp.sys.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.service.BaseService;
import com.mdp.core.utils.DateUtils;
import com.mdp.sys.entity.InterestsOrders;
import com.mdp.sys.entity.User;
import com.mdp.sys.entity.UserInterests;
import com.mdp.sys.mapper.InterestsOrdersMapper;
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
 * 组织 com  顶级模块 mk 大模块 mem 小模块 <br>
 * 实体 InterestsOrders 表 mem_interests_orders 当前主键(包括多主键): userid; 
 ***/
@Service("sys.interestsOrdersService")
public class InterestsOrdersService extends BaseService<InterestsOrdersMapper,InterestsOrders>{
	static Logger logger =LoggerFactory.getLogger(InterestsOrdersService.class);

	@Autowired
	UserInterestsService memberInterestsService;

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
    public void payConfirmForPerson(InterestsOrders interestsOrders, InterestsOrders interestsOrdersDb) {
        InterestsOrders interestsOrdersUpdate=new InterestsOrders();
        interestsOrdersUpdate.setId(interestsOrders.getId());
        interestsOrdersUpdate.setPayStatus("1");
        interestsOrdersUpdate.setPayTime(new Date());
        interestsOrdersUpdate.setTranId(interestsOrders.getTranId());
        super.updateSomeFieldByPk(interestsOrdersUpdate);
        boolean isAdd=false;
        UserInterests memberInterests=memberInterestsService.selectOneById(interestsOrdersDb.getUserid());
        if(memberInterests==null){
            memberInterests=new UserInterests();
            memberInterests.setUserid(interestsOrdersDb.getUserid());
            memberInterests.setCtime(new Date());
            memberInterests.setCtotalExp(BigDecimal.ZERO);
            memberInterests.setCtotalBids(0);
            memberInterests.setCtotalAt(BigDecimal.ZERO);
            memberInterests.setCmonthBids(0);
            isAdd=true;
        }
        memberInterests.setBids(interestsOrdersDb.getBids());
        memberInterests.setIsFree(interestsOrdersDb.getIsFree());
        memberInterests.setMfee(interestsOrdersDb.getMfee());
        memberInterests.setMtype(interestsOrdersDb.getMtype());
        memberInterests.setItype(interestsOrdersDb.getItype());
        memberInterests.setIlevel(interestsOrdersDb.getIlevel());
        memberInterests.setBids(interestsOrdersDb.getBids());
        memberInterests.setSmaxAt(interestsOrdersDb.getSmaxAt());
        memberInterests.setSmaxExp(interestsOrdersDb.getSmaxExp());
        memberInterests.setSfeeRate(interestsOrdersDb.getSfeeRate());
        memberInterests.setIlevel(interestsOrdersDb.getIlevel());
        memberInterests.setRtimeType("2");
        memberInterests.setRtimeRule("1");
        if("1".equals(interestsOrdersDb.getOoper()) ||"3".equals(interestsOrdersDb.getOoper())){//新购，升级立即生效

            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            c.add(Calendar.MONTH, interestsOrdersDb.getMonths());
            memberInterests.setRtime(DateUtils.format(c.getTime(),"yyyy-MM-dd"));
            if(isAdd) {
                this.memberInterestsService.insert(memberInterests);
            }else {
                this.memberInterestsService.updateSomeFieldByPk(memberInterests);
            }
            User memberUpdate=new User();
            memberUpdate.setUserid(memberInterests.getUserid());
            memberUpdate.setIlvlId(interestsOrdersDb.getIlvlId());
            memberUpdate.setIlvlName(interestsOrdersDb.getIlvlName());
            memberUpdate.setIetime(DateUtils.parse(memberInterests.getRtime(),"yyyy-MM-dd"));
            memberUpdate.setIstime(new Date());
            memberUpdate.setIstatus("1");
            this.userService.updateSomeFieldByPk(memberUpdate);
        }else  if("2".equals(interestsOrdersDb.getOoper())){//续费,如果原来的已过期，则立即生效，如果未过期，则需要等到期日定时任务执行
            Date rtime=DateUtils.parse(memberInterests.getRtime(),"yyyy-MM-dd");
            String oldRtime=memberInterests.getRtime();
             Date now=new Date();
            int days = (int) ((rtime.getTime() - now.getTime()) / (1000*3600*24));
            if(days<=0) {//已过期
                Calendar c = Calendar.getInstance();
                c.setTime(rtime);
                c.add(Calendar.MONTH, interestsOrdersDb.getMonths());
                memberInterests.setRtime(DateUtils.format(c.getTime(),"yyyy-MM-dd"));
                this.memberInterestsService.updateSomeFieldByPk(memberInterests);
                User memberUpdate=new User();
                memberUpdate.setUserid(memberInterests.getUserid());
                memberUpdate.setIlvlId(interestsOrdersDb.getIlvlId());
                memberUpdate.setIlvlName(interestsOrdersDb.getIlvlName());
                memberUpdate.setIetime(DateUtils.parse(memberInterests.getRtime(),"yyyy-MM-dd"));
                memberUpdate.setIstime(DateUtils.parse(oldRtime,"yyyy-MM-dd"));
                memberUpdate.setIstatus("1");
                this.userService.updateSomeFieldByPk(memberUpdate);
            }else{
                //等待定时任务处理未过期的，不用动
            }


        }


	}

    @Transactional
    public void payConfirmForBranch(InterestsOrders interestsOrders, InterestsOrders interestsOrdersDb) {
        this.payConfirmForPerson(interestsOrders,interestsOrdersDb);
    }
	@Transactional
    public void createOrders(InterestsOrders interestsOrders) {
        super.insert(interestsOrders);
	    if("1".equals(interestsOrders.getPayStatus()) && ("1".equals(interestsOrders.getVer())|| !StringUtils.hasText(interestsOrders.getVer()))){
	        this.payConfirmForPerson(interestsOrders,interestsOrders);
        }else if("1".equals(interestsOrders.getPayStatus()) && "2".equals(interestsOrders.getVer())){
            this.payConfirmForBranch(interestsOrders,interestsOrders);
        }
    }

    public void payCancel(String orderId, String payId, String remark) {
        InterestsOrders moOrder = this.selectOneById(orderId);
        if(StringUtils.isEmpty(moOrder.getPayId()) ||moOrder.getPayId().equals(payId)){
            InterestsOrders orderUpdate=new InterestsOrders();
            orderUpdate.setId(orderId);
            orderUpdate.setRemark(remark);
            orderUpdate.setPayStatus("2");
            orderUpdate.setLtime(new Date());  
            super.updateSomeFieldByPk(orderUpdate);
        }
    }

    public InterestsOrders selectLastOrders(String userid) {
	    return baseMapper.selectLastOrders(userid);
    }

    public List<InterestsOrders> selectListNeedCalc() {
	    return baseMapper.selectListNeedCalc(map());
    }
}

