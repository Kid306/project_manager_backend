package com.mdp.sys.entity;

import lombok.Data;

import java.util.List;

@Data
public class RolesToPostVo {

    List<String> roleids;

    String postId;
}
