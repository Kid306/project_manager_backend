package com.xm.core.entity;

import lombok.Data;

@Data
public class XmProductCopyVo {

    String id;//原产品编号

    String code;//新的产品编码
    String productName;//新的产品名称

    String isTpl;//是否拷贝为模板


    String tplType;//是否全网公开，1-是，2-否

    String copyMenu;//是否拷贝需求
    String copyPhase;//是否复制计划 0否1是
    String copyGroup;//是否复制组织架构 0否1是
    String copyGroupUser;//是否复制组织架构中用户 0否1是

}
