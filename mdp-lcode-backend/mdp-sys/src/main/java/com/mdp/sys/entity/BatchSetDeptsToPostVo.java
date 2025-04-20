package com.mdp.sys.entity;

import lombok.Data;

import java.util.List;

@Data
public class BatchSetDeptsToPostVo {

    String branchId;

    String postId;


    List<String> deptids;


}
