package com.xm.core.vo;

import lombok.Data;

@Data
public class XmProjectCopyVo {
    String id;//原项目编号
    String code;//新项目编码
    String name;//新项目名称
    String isTpl;//是否复制为模板项目 0否1是
    String copyPhase;//是否复制计划 0否1是
    String copyTask;//是否复制任务  0否1是
    String copyGroup;//是否复制组织架构 0否1是
    String copyGroupUser;//是否复制组织架构中用户 0否1是
    String copyProduct;//是否复制关联的产品及需求明细
    String tplType;//模版公开范围 1-全网公开，2-本企业公开

}
