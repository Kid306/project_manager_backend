package com.mdp.mo.service;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.mdp.core.service.BaseService;
import com.mdp.core.utils.NumberUtil;
import com.mdp.menu.entity.MenuModule;
import com.mdp.menu.entity.MenuModuleBranch;
import com.mdp.menu.service.MenuModuleService;
import com.mdp.mo.entity.CreateOrderVo;
import com.mdp.mo.entity.MoOrder;
import com.mdp.mo.entity.MoOrderModule;
import com.mdp.mo.mapper.MoOrderMapper;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br>
 * 组织 com  顶级模块 mdp 大模块 mo 小模块 <br>
 * 实体 MoModule 表 mo_module 当前主键(包括多主键): id;
 ***/
@Service("mdp.mo.moOrderCreateService")
public class MoOrderCreateService extends BaseService<MoOrderMapper,MoOrder> {

    @Autowired
    private MenuModuleService menuModuleService;

    @Autowired
    private MoOrderModuleService moOrderModuleService;

    @Override
    public String getNameSpace() {
        return "com.mdp.mo.entity.MoOrder";
    }

    //免费模式
    private static String BILL_MODE_FREE = "0";
    //购买人数计费模式
    private static String BILL_MODE_PEOPLE = "1";
    //总包模式
    private static String BILL_MODE_GENERAL_CONTRACTING = "2";

    static Logger logger = LoggerFactory.getLogger(MoOrderCreateService.class);


    /**
     * 计算模块及订单价格
     * @param moOrder
     * @param moOrderModules
     */
    public void calcOrderAmount(MoOrder moOrder, List<MoOrderModule> moOrderModules) {

        //各个模块数量
        int modulesNum = moOrderModules.size();
        //总金额
        BigDecimal amount = new BigDecimal(BigInteger.ZERO);
        //折扣前金额
        BigDecimal orginAmount = new BigDecimal(BigInteger.ZERO);
        //其它费用
        BigDecimal othFee = new BigDecimal(BigInteger.ZERO);
        //模块合计总金额
        BigDecimal moFinalFee = new BigDecimal(BigInteger.ZERO);
        //模块优惠前金额
        BigDecimal moOrginFee = new BigDecimal(BigInteger.ZERO);

        Set<String> modulesTypeNum = new HashSet<>();

        //模块相关
        for (MoOrderModule menuModuleVo : moOrderModules) {
            modulesTypeNum.add(menuModuleVo.getMcate());

            //模块原始金额
            BigDecimal moduleOrginFee = new BigDecimal(BigInteger.ZERO);
            //模块最终金额
            BigDecimal moduleFinalFee = new BigDecimal(BigInteger.ZERO);

            //1.如果是免费，则为0元
            if(menuModuleVo.getBillMode().equals(BILL_MODE_FREE)) {
                amount = amount.add(new BigDecimal(BigInteger.ZERO));
                orginAmount = orginAmount.add(new BigDecimal(BigInteger.ZERO));
                moFinalFee = moFinalFee.add(new BigDecimal(BigInteger.ZERO));
                moOrginFee = moOrginFee.add(new BigDecimal(BigInteger.ZERO));
                moduleOrginFee = moduleOrginFee.add(new BigDecimal(BigInteger.ZERO));
                moduleFinalFee = moduleOrginFee.add(new BigDecimal(BigInteger.ZERO));
            }

            //2.购买人数计费模式
            if(menuModuleVo.getBillMode().equals(BILL_MODE_PEOPLE)) {
                //人数折扣率
                BigDecimal numDiscount = new BigDecimal(1);
                //月份折扣率
                BigDecimal daysDiscount = new BigDecimal(1);

                BigDecimal days=BigDecimal.valueOf(menuModuleVo.getDays());

                if(StringUtils.hasText(menuModuleVo.getDiscount())) {
                    //计算数量折扣优惠
                    numDiscount = calcUserNumDiscount(menuModuleVo.getDiscount(), menuModuleVo.getMusers());
                    //计算月份折扣优惠
                    daysDiscount = calcDaysDiscount(menuModuleVo.getDiscount(), menuModuleVo.getDays());
                }
                //人均费用 * 数量 * 数量折扣优惠 * 月份折扣优惠
                amount = amount.add(NumberUtil.getBigDecimal(menuModuleVo.getUniFee(), BigDecimal.ZERO)
                        .multiply(new BigDecimal(menuModuleVo.getMusers())).multiply(numDiscount).multiply(daysDiscount).multiply(days));
                //原始费用 人均费用 * 数量
                orginAmount = orginAmount.add(NumberUtil.getBigDecimal(menuModuleVo.getUniFee(), BigDecimal.ZERO)
                        .multiply(new BigDecimal(menuModuleVo.getMusers())).multiply(days));
                //单个模块最终费用
                moduleFinalFee =moduleFinalFee.add(NumberUtil.getBigDecimal(menuModuleVo.getUniFee(), BigDecimal.ZERO)
                        .multiply(new BigDecimal(menuModuleVo.getMusers())).multiply(numDiscount).multiply(daysDiscount).multiply(days));
                //单个模块原始费用
                moduleOrginFee = moduleOrginFee.add(NumberUtil.getBigDecimal(menuModuleVo.getUniFee(), BigDecimal.ZERO)
                        .multiply(new BigDecimal(menuModuleVo.getMusers())).multiply(days));

                //总模块最终费用
                moFinalFee = moFinalFee.add(NumberUtil.getBigDecimal(menuModuleVo.getUniFee(), BigDecimal.ZERO)
                        .multiply(new BigDecimal(menuModuleVo.getMusers())).multiply(numDiscount).multiply(daysDiscount).multiply(days));
                //总模块原始费用
                moOrginFee = moOrginFee.add(NumberUtil.getBigDecimal(menuModuleVo.getUniFee(), BigDecimal.ZERO)
                        .multiply(new BigDecimal(menuModuleVo.getMusers())).multiply(days));

            }

            //3.总包计费模式
            if(menuModuleVo.getBillMode().equals(BILL_MODE_GENERAL_CONTRACTING)) {
                amount = amount.add(NumberUtil.getBigDecimal(menuModuleVo.getFee(), BigDecimal.ZERO));
                orginAmount = orginAmount.add(NumberUtil.getBigDecimal(menuModuleVo.getFee(), BigDecimal.ZERO));
                //模块最终费用
                moduleFinalFee = moduleFinalFee.add(NumberUtil.getBigDecimal(menuModuleVo.getFee(), BigDecimal.ZERO));
                //模块原始费用
                moduleOrginFee = moduleOrginFee.add(NumberUtil.getBigDecimal(menuModuleVo.getFee(), BigDecimal.ZERO));
                //单个模块最终费用
                moFinalFee = moFinalFee.add(NumberUtil.getBigDecimal(menuModuleVo.getFee(), BigDecimal.ZERO));
                //单个模块原始费用
                moOrginFee = moOrginFee.add(NumberUtil.getBigDecimal(menuModuleVo.getFee(), BigDecimal.ZERO));
            }
            menuModuleVo.setOrginFee(moduleOrginFee);
            menuModuleVo.setFinalFee(moduleFinalFee);
        }
        moOrder.setOcates(modulesTypeNum.size());
        moOrder.setOmodules(modulesNum);
        moOrder.setOfinalFee(amount);
        moOrder.setMoOrginFee(NumberUtil.getBigDecimal(moOrginFee, BigDecimal.ZERO));
        moOrder.setMoFinalFee(NumberUtil.getBigDecimal(moFinalFee, BigDecimal.ZERO));
        moOrder.setOthFee(NumberUtil.getBigDecimal(othFee, BigDecimal.ZERO));
    }

    /**
     * 订单模块初始化
     * @param createOrderVo
     * @param moOrder
     * @param moModuleVoList
     * @return
     */
    public List<MoOrderModule> initOrderModule(CreateOrderVo createOrderVo, MoOrder moOrder,
                                               List<MenuModule> moModuleVoList) {
        //添加订单模块关联表
        List<MoOrderModule>  moOrderModule = new ArrayList<>();
        for (MenuModule menuModuleVo : moModuleVoList) {
            MoOrderModule module = new MoOrderModule();
            BeanUtils.copyProperties(menuModuleVo, module);
            module.setOrderId( moOrder.getId()  );
            module.setModuleId(menuModuleVo.getId());
            module.setDays(createOrderVo.getOdays());
            module.setOusers(createOrderVo.getOusers());
            if("1".equals(menuModuleVo.getUdis())){
                module.setMusers(createOrderVo.getOusers()*NumberUtil.getInteger(menuModuleVo.getUdisRate(),100)/100);
            }else {
                module.setMusers(createOrderVo.getOusers());
            }
            moOrderModule.add(module);
        }
        return moOrderModule;
    }

    public MoOrder initOrder(CreateOrderVo createOrderVo) {
        User cuser = LoginUtils.getCurrentUserInfo();
        MoOrder moOrder=new MoOrder();
        moOrder.setId(IdUtil.getSnowflakeNextIdStr());
        moOrder.setName(createOrderVo.getName());
        moOrder.setObranchId(cuser.getBranchId());
        moOrder.setObranchName(cuser.getBranchName());
        moOrder.setOuserid(cuser.getUserid());
        moOrder.setOusername(cuser.getUsername());
        moOrder.setOusers(createOrderVo.getOusers());
        moOrder.setOdays(createOrderVo.getOdays());
        moOrder.setOoper(createOrderVo.getOoper());
        //订单状态 订单状态0-初始，1-待确认，2-待付款，3-已付款，4-已完成，5-已取消-未付款前可取消，取消后可删除，6-退单-退单后变为已取消，8已关闭-售后完成后可以关闭订单
        moOrder.setStatus("2");
        //结算状态 结算状态0-待结算，1-已结算
        moOrder.setSstatus("0");
        //创建时间
        moOrder.setCtime(new Date());
        //备注
        moOrder.setRemark(createOrderVo.getRemark());
        //订单类型 1-新购，2-续购
        moOrder.setOtype(createOrderVo.getOtype());
        //订单来源 1-前端客户下单，2-后台待客下单
        moOrder.setOsource(createOrderVo.getOsource());
        //客户类型
        moOrder.setMemType(cuser.getMemType());
        //账户类型
        moOrder.setAtype(cuser.getAtype());
        //客户联系电话
        moOrder.setCustPhone(createOrderVo.getPhone());
        //客户联系地址
        moOrder.setCustAddress(createOrderVo.getAddress());
        return moOrder;
    }

    private BigDecimal calcUserNumDiscount(String discountJson, int num) {
        BigDecimal sale = new BigDecimal(1);
        if(!StringUtils.hasText(discountJson)){
            return sale;
        }
        if(num<=0){
            return sale;
        }
        try {
            Map<String, String> discount = JSON.parseObject(discountJson, Map.class);
            //用户数量优惠规则
            String userNum = discount.get("userNum");
            if(!StringUtils.hasText(userNum)) return sale;
            String rules[] = userNum.split("\n");
            Map<String,Object> map = new HashMap<>();
            for (int i = 0; i < rules.length; i++) {
                String[] t = rules[i].split(":");
                map.put(t[0], t[1]);
            }
            for(Map.Entry<String, Object> entry:map.entrySet()){
                String[] t = entry.getKey().split("-");
                if(num >= Integer.valueOf(t[0]) && num <= Integer.valueOf(t[1])) {
                    sale = new BigDecimal(Integer.valueOf((String) entry.getValue())).divide(new BigDecimal(100));
                    break;
                }
            }
            return sale;
        }catch (Exception e){
            log.error("计算人数折扣率报错,按不打折处理",e);
            return sale;
        }

    }
    private BigDecimal calcDaysDiscount(String discountJson, int days) {
        BigDecimal sale = new BigDecimal(1);
        if(!StringUtils.hasText(discountJson)){
            return sale;
        }
        if(days<=0){
            return sale;
        }
        try {
            Map<String, String> discount = JSON.parseObject(discountJson, Map.class);
            //用户数量优惠规则
            String disDays = discount.get("days");
            if(!StringUtils.hasText(disDays)) return sale;
            String rules[] = disDays.split("\n");
            Map<String,Object> map = new HashMap<>();
            for (int i = 0; i < rules.length; i++) {
                String[] t = rules[i].split(":");
                map.put(t[0], t[1]);
            }
            for(Map.Entry<String, Object> entry:map.entrySet()){
                String[] t = entry.getKey().split("-");
                if(days >= Integer.valueOf(t[0]) && days <= Integer.valueOf(t[1])) {
                    sale = new BigDecimal(Integer.valueOf((String) entry.getValue())).divide(new BigDecimal(100));
                    break;
                }
            }
            return sale;
        }catch (Exception e){
            log.error("计算天数折扣率报错,按不打折处理",e);
            return sale;
        }

    }

    public Boolean isHaveCloseModule(List<MenuModule> moduleList) {
         return moduleList.stream().filter(i->!"1".equals(i.getStatus())).findAny().isPresent();
    }

    @Transactional
    public void insertOrder(MoOrder moOrder, List<MoOrderModule> moOrderModules) {
        //插入数据库
        this.insert(moOrder);
        moOrderModuleService.batchInsert(moOrderModules);
    }

    public static void main(String[] args) {
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR,3);
        calendar.getTime();
        System.out.println(calendar.getTime());
    }

    /**
     * 增购用户计算价格。
     * 1.计算每个模块剩余日期（精确到天），过期模块不计算。
     * 2.按每个模块的价格。
     * 3.合计价格作为整个订单价格。
     * @param moOrder
     * @param moOrderModules
     * @param hadModulesDb
     */
    public void calcOrderAmountForAddUsers(MoOrder moOrder, List<MoOrderModule> moOrderModules, List<MenuModuleBranch> hadModulesDb) {

        //各个模块数量
        int modulesNum = moOrderModules.size();
        //总金额
        BigDecimal amount = new BigDecimal(BigInteger.ZERO);
        //折扣前金额
        BigDecimal orginAmount = new BigDecimal(BigInteger.ZERO);
        //其它费用
        BigDecimal othFee = new BigDecimal(BigInteger.ZERO);
        //模块合计总金额
        BigDecimal moFinalFee = new BigDecimal(BigInteger.ZERO);
        //模块优惠前金额
        BigDecimal moOrginFee = new BigDecimal(BigInteger.ZERO);

        Set<String> modulesTypeNum = new HashSet<>();

        //模块相关
        for (MoOrderModule menuModuleVo : moOrderModules) {
            modulesTypeNum.add(menuModuleVo.getMcate());
            //模块原始金额
            BigDecimal moduleOrginFee = new BigDecimal(BigInteger.ZERO);
            //模块最终金额
            BigDecimal moduleFinalFee = new BigDecimal(BigInteger.ZERO);

            //原来的模块
            MenuModuleBranch menuModuleBranch=hadModulesDb.stream().filter(i->i.getModuleId().equals(menuModuleVo.getModuleId())).findAny().get();

            //人数折扣率
            BigDecimal numDiscount = new BigDecimal(1);
            //月份折扣率
            BigDecimal monthDiscount = new BigDecimal(1);

            Date date=new Date();
            Long daysL=DateUtil.between(date,menuModuleBranch.getEndTime(), DateUnit.DAY,false);
            if(daysL<=0){
                daysL=0L;
            }
            BigDecimal days=BigDecimal.valueOf(daysL);

            if(StringUtils.hasText(menuModuleVo.getDiscount())) {
                //计算数量折扣优惠
                numDiscount = calcUserNumDiscount(menuModuleVo.getDiscount(), menuModuleVo.getMusers());
                //计算月份折扣优惠
                monthDiscount = calcDaysDiscount(menuModuleVo.getDiscount(), menuModuleVo.getDays());
            }
            //人均费用 * 数量 * 数量折扣优惠 * 月份折扣优惠
            amount = amount.add(NumberUtil.getBigDecimal(menuModuleVo.getUniFee(), BigDecimal.ZERO)
                    .multiply(new BigDecimal(menuModuleVo.getMusers())).multiply(numDiscount).multiply(monthDiscount).multiply(days));
            //原始费用 人均费用 * 数量
            orginAmount = orginAmount.add(NumberUtil.getBigDecimal(menuModuleVo.getUniFee(), BigDecimal.ZERO)
                    .multiply(new BigDecimal(menuModuleVo.getMusers())).multiply(days));
            //单个模块最终费用
            moduleFinalFee =moduleFinalFee.add(NumberUtil.getBigDecimal(menuModuleVo.getUniFee(), BigDecimal.ZERO)
                    .multiply(new BigDecimal(menuModuleVo.getMusers())).multiply(numDiscount).multiply(monthDiscount).multiply(days));
            //单个模块原始费用
            moduleOrginFee = moduleOrginFee.add(NumberUtil.getBigDecimal(menuModuleVo.getUniFee(), BigDecimal.ZERO)
                    .multiply(new BigDecimal(menuModuleVo.getMusers())).multiply(days));

            //总模块最终费用
            moFinalFee = moFinalFee.add(NumberUtil.getBigDecimal(menuModuleVo.getUniFee(), BigDecimal.ZERO)
                    .multiply(new BigDecimal(menuModuleVo.getMusers())).multiply(numDiscount).multiply(monthDiscount).multiply(days));
            //总模块原始费用
            moOrginFee = moOrginFee.add(NumberUtil.getBigDecimal(menuModuleVo.getUniFee(), BigDecimal.ZERO)
                    .multiply(new BigDecimal(menuModuleVo.getMusers())).multiply(days));

            menuModuleVo.setOrginFee(moduleOrginFee);
            menuModuleVo.setFinalFee(moduleFinalFee);
        }
        moOrder.setOcates(modulesTypeNum.size());
        moOrder.setOmodules(modulesNum);
        moOrder.setOfinalFee(amount);
        moOrder.setMoOrginFee(NumberUtil.getBigDecimal(moOrginFee, BigDecimal.ZERO));
        moOrder.setMoFinalFee(NumberUtil.getBigDecimal(moFinalFee, BigDecimal.ZERO));
        moOrder.setOthFee(NumberUtil.getBigDecimal(othFee, BigDecimal.ZERO));
    }
}
