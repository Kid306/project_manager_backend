package com.mdp.onepark.dto;

import lombok.Data;
/***
 * @description onepark 当前登录用户信息
 *
 */
@Data
public class OneParkUserResDto {
    private Long id; //主键id
    private String username; //账号名
    private String nickname; //昵称
    private String mobile; //手机号
    private String headImgUrl; //头像地址
    private Integer sex; //性别
    private String registType; //注册方式
    private Boolean enabled; //启用状态
    private String createTime; //创建时间
    private String creator; //创建者
    private String updateTime; //更新时间
    private String updater; //更新者
    private String lastLoginTime; //最后登录时间
}
