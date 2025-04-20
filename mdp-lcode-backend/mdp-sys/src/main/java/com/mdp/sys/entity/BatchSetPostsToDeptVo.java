package com.mdp.sys.entity;

import lombok.Data;

import java.util.List;

@Data
public class BatchSetPostsToDeptVo {

    String branchId;

    String deptid;

    List<String> postIds;

}
