package com.mdp.onepark.dto;

import lombok.Data;

import java.util.List;

/***
 * @description onepark 分页数据响应对象
 */
@Data
public class OneParkPageResDto<T> {
    private List<T> data;
    private Integer code;
    private Integer count;
    private Integer totalPage;
}
