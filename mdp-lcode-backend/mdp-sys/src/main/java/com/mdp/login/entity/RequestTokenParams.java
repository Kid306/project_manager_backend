package com.mdp.login.entity;

import lombok.Data;

@Data
public class RequestTokenParams {
    String userid;//如果已知道明确的用户编号，比如同一个手机号下，有多个账户的情况下，前端让客户选完一个账户再登录
    String userloginid;
    String password;
    String clientId;
    String clientSecret;
    String registrationId;
    String authType;
    String grantType;
    String redirectUri;

    String deptid;//部门号，可空

    String branchId;//机构号，可空

}
