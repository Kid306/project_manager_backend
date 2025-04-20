package com.mdp.onepark.dto;

import lombok.Data;
/***
* @description onepark响应对象
*/

@Data
public class OneParkResDto<T> {
    private T data;
    private Integer code;
    private String message;
    private String localeMessage;
}
