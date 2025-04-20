package com.mdp.sys.service;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.service.BaseService;
import com.mdp.core.utils.NumberUtil;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.sys.entity.Guard;
import com.mdp.sys.entity.GuardOrder;
import com.mdp.sys.entity.GuardOrderCreateVo;
import com.mdp.sys.mapper.GuardOrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br>
 * 组织 com  顶级模块 mdp 大模块 mo 小模块 <br>
 * 实体 MoModule 表 mo_module 当前主键(包括多主键): id;
 ***/
@Service("mdp.sys.guardOrderCreateService")
public class GuardOrderCreateService extends BaseService<GuardOrderMapper,GuardOrder>{

    @Override
    public String getNameSpace() {
        return "com.mdp.sys.entity.GuardOrder";
    }
 

    static Logger logger = LoggerFactory.getLogger(GuardOrderCreateService.class);


    @Autowired
    GuardDamageService damageService;
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

    /**
     * 计算模块及订单价格
     * @param guardOrder
     * @param guardDb
     */
    public void calcOrderAmount(GuardOrder guardOrder, Guard guardDb) {

        guardOrder.setGuardId(guardDb.getId());
        guardOrder.setGuardName(guardDb.getName());

        guardOrder.setOrginFee(guardDb.getFee());
        guardOrder.setFinalFee(guardDb.getFee());
        guardOrder.setOdisRate(100);
        guardOrder.setOthFee(BigDecimal.ZERO);
        guardOrder.setOfinalFee(guardOrder.getFinalFee().multiply(NumberUtil.getBigDecimal(guardOrder.getOdisRate()).divide(NumberUtil.getBigDecimal(100))).add(guardOrder.getOthFee()));

    }


    public GuardOrder initOrder(GuardOrderCreateVo createOrderVo) {
        User cuser = LoginUtils.getCurrentUserInfo();
        GuardOrder guardOrder=new GuardOrder();
        guardOrder.setId(IdUtil.getSnowflakeNextIdStr());
        guardOrder.setName(createOrderVo.getName());
        guardOrder.setObranchId(cuser.getBranchId());
        guardOrder.setObranchName(cuser.getBranchName());
        guardOrder.setOuserid(cuser.getUserid());
        guardOrder.setOusername(cuser.getUsername());
        //订单状态 订单状态0-初始，1-待确认，2-待付款，3-已付款，4-已完成，5-已取消-未付款前可取消，取消后可删除，6-退单-退单后变为已取消，8已关闭-售后完成后可以关闭订单
        guardOrder.setStatus("2");
        //结算状态 结算状态0-待结算，1-已结算
        guardOrder.setSstatus("0");
        //创建时间
        guardOrder.setCtime(new Date());
        //备注
        guardOrder.setRemark(createOrderVo.getRemark());
        //订单类型 1-新购，2-续购
        guardOrder.setOtype(createOrderVo.getOtype());
        //订单来源 1-前端客户下单，2-后台待客下单
        guardOrder.setOsource(createOrderVo.getOsource());
        //客户类型
        guardOrder.setMemType(cuser.getMemType());
        //账户类型
        guardOrder.setAtype(cuser.getAtype());
        //客户联系电话
        guardOrder.setCustPhone(createOrderVo.getPhone());
        //客户联系地址
        guardOrder.setCustAddress(createOrderVo.getAddress());

        guardOrder.setPayCtime(new Date());

        guardOrder.setPayStatus("0");
        return guardOrder;
    }


    @Transactional
    public void insertOrder(GuardOrder guardOrder) {
        //插入数据库
        this.insert(guardOrder);
    }

    public void subHisFee(GuardOrder guardOrder, Guard guardDb) {
        BigDecimal hisFee=this.sumGuardOrdersHisFee(guardOrder.getOuserid());
        BigDecimal hisDamage=this.damageService.sumGuardDamageHisFee(guardOrder.getOuserid());
        if(hisFee==null){
            hisFee=BigDecimal.ZERO;
        }
        if(hisDamage==null){
            hisDamage=BigDecimal.ZERO;
        }
        guardOrder.setOthFee(BigDecimal.ZERO.subtract(hisFee).add(hisDamage));
        guardOrder.setOfinalFee(guardOrder.getOfinalFee().add(guardOrder.getOthFee()));
    }

    private BigDecimal sumGuardOrdersHisFee(String ouserid) {
        return baseMapper.sumGuardOrdersHisFee(ouserid);
    }
}
