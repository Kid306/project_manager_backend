package com.mdp.mo.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CreateOrderVo {

    private List<String> moduleIds = new ArrayList<>();

    //订单名称
    private String name;

    //购买的天数
    private int odays;


    /**
     * 支付方式1-微信，2-支付宝，3-线下支付
     */
    private String payType;

    //备注
    private String remark;

    //订单类型
    private String otype;

    //订单来源
    private String osource;

    //客户联系电话
    private String phone;

    //客户联系地址
    private String address;

    //企业人数=购买人数
    private int ousers;

    /**
     * 订单操作类型1-新购，2-续费，3-新增人数
     */
    private String ooper;

    //是否为计算金额，如果是则不插入数据库
    private boolean isCalc;

}
