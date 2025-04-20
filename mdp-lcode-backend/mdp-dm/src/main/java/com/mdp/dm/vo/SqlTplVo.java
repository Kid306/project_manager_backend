package com.mdp.dm.vo;

import lombok.Data;

import java.util.List;

@Data
public class SqlTplVo {
    String sqlTpl;
    List<String> varNames;
    List<Object> varValues;
}
