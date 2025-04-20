package com.mdp.sys.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;
@Data
@ApiModel(description="用户与技能关系表")
public class UserSkillsVo {

    @ApiModelProperty(notes="技能列表",allowEmptyValue=true,example="",allowableValues="")
    List<Map<String,Object>> skills;

    @ApiModelProperty(notes="用户编号,主键",allowEmptyValue=true,example="",allowableValues="")
    String userid;



}
