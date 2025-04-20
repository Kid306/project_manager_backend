package com.mdp.dm.base.service;

import java.util.List;

/**
 * 操作数据库的DDL服务
 */
public interface DdlBaseService {

    /**
     * 执行ddl语句
     * @param ds
     * @param ddlSql
     * @return
     */
    boolean createTable(String ds, String ddlSql);

    /**
     * 执行ddl 修改表的语句
     * @param ds
     * @param ddlSql
     * @return
     */
    boolean editTable(String ds,String ddlSql);

    /**
     * 执行删除表的语句
     * @param ds
     * @param ddlSql
     * @return
     */
    boolean dropTable(String ds,String ddlSql);

    /**
     * 执行任意语句
     * @param ds
     * @param ddlSql
     * @return
     */
    boolean execute(String ds,String ddlSql);


    /**
     * 执行任意多语句
     * @param ds
     * @param ddlSqls
     * @return
     */
    boolean execute(String ds, List<String> ddlSqls);


}
