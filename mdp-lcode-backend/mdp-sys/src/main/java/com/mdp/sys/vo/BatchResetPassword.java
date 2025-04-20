package com.mdp.sys.vo;

import lombok.Data;

import java.util.List;

@Data
public class BatchResetPassword {

    List<String> userids;
    String password;
}
