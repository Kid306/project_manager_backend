package com.mdp.sys.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description="购买服务保障订单下单")
@Data
public class GuardOrderCreateVo {


    @ApiModelProperty(notes="订单名称",allowEmptyValue=true,example="",allowableValues="")
    private String name;

    @ApiModelProperty(notes="服务保障编号",allowEmptyValue=true,example="",allowableValues="")
    private String guardId;

    @ApiModelProperty(notes="支付方式1-微信，2-支付宝，3-线下支付",allowEmptyValue=true,example="",allowableValues="")
    private String payType;

    @ApiModelProperty(notes="备注",allowEmptyValue=true,example="",allowableValues="")
    private String remark;

    @ApiModelProperty(notes="订单类型0-电商商城商品，1-应用模块使用购买，2-vip购买会员，3-任务相关的保证金、佣金、分享赚佣金、加急热搜金额等、4-服务商付款服务保障押金",allowEmptyValue=true,example="",allowableValues="")
    private String otype;

    //订单来源
    @ApiModelProperty(notes="订单来源1-前端客户下单，2-后台待客下单",allowEmptyValue=true,example="",allowableValues="")
    private String osource;

    //客户联系电话
    @ApiModelProperty(notes="客户联系电话",allowEmptyValue=true,example="",allowableValues="")
    private String phone;

    //客户联系地址
    @ApiModelProperty(notes="客户联系地址",allowEmptyValue=true,example="",allowableValues="")
    private String address;

    /**
     * 订单操作类型1-新购，2-续费
     */
    @ApiModelProperty(notes="订单操作类型1-新购，2-续费",allowEmptyValue=true,example="",allowableValues="")
    private String ooper;

    //是否为计算金额，如果是则不插入数据库
    @ApiModelProperty(notes="是否为计算金额，如果是则不插入数据库",allowEmptyValue=true,example="",allowableValues="")
    private boolean isCalc=false;

}
