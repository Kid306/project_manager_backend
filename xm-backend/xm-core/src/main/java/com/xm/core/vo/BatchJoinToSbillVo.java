package com.xm.core.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
@Data
@ApiModel(description="批量将工时记录加入结算单")
public class BatchJoinToSbillVo {

    @ApiModelProperty(notes="工时单主键列表",allowEmptyValue=true,example="",allowableValues="")
    List<String> workloadIds;

    @ApiModelProperty(notes="结算单主键",allowEmptyValue=true,example="",allowableValues="")
    String sbillId;
}

