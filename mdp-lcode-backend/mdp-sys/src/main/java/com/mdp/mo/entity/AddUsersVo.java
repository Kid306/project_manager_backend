package com.mdp.mo.entity;

import lombok.Data;

@Data
public class AddUsersVo {


    //订单名称
    private String name;

    /**
     * 支付方式1-微信，2-支付宝，3-线下支付
     */
    private String payType;

    //备注
    private String remark;

    //客户联系电话
    private String phone;

    //客户联系地址
    private String address;

    //企业人数=购买人数
    private int ousers;

    //是否为计算金额，如果是则不插入数据库
    private boolean isCalc;

}
