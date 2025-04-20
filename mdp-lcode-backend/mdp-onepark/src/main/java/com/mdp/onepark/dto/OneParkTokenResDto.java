package com.mdp.onepark.dto;

import lombok.Data;
/***
 * @description onepark 当前登录token
 *
 */
@Data
public class OneParkTokenResDto {
    private String access_token;
    private String token_type;
    private Integer expires_in;
}
