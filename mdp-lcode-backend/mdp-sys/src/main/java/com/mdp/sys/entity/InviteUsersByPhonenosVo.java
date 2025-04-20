package com.mdp.sys.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class InviteUsersByPhonenosVo {
    @ApiModelProperty(notes="手机号码列表",allowEmptyValue=true,example="",allowableValues="")
    List<String> phonenos;
}
