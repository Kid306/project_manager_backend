package com.xm.core.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("从用例库导入测试用例到测试计划")
public class ImportFromTestCaseVo {

    @ApiModelProperty(notes="需要导入的用例编号列表",allowEmptyValue=true,example="",allowableValues="")
    List<String> caseIds;

    @ApiModelProperty(notes="测试计划编号",allowEmptyValue=true,example="",allowableValues="")
    String planId;
}
