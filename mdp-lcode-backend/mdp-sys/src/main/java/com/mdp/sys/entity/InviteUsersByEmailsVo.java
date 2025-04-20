package com.mdp.sys.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class InviteUsersByEmailsVo {
    @ApiModelProperty(notes="邮箱列表",allowEmptyValue=true,example="",allowableValues="")
    List<String> emails;

    @ApiModelProperty(notes="邮箱中点击跳转路径",allowEmptyValue=true,example="",allowableValues="")
    String callbackUri;
}
