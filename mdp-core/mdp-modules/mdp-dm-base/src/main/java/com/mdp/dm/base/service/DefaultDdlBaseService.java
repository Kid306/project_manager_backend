package com.mdp.dm.base.service;

import com.mdp.dm.base.VersionUtil;

import java.util.List;

public class DefaultDdlBaseService implements DdlBaseService {

    public boolean checkVersion() {
        return VersionUtil.supportVersion(false);
    }

    @Override
    public boolean createTable(String ds, String ddlSql) {
        return VersionUtil.supportVersion(false);
    }

    @Override
    public boolean editTable(String ds, String ddlSql) {
        return VersionUtil.supportVersion(false);
    }

    @Override
    public boolean dropTable(String ds, String ddlSql) {
        return VersionUtil.supportVersion(false);
    }

    @Override
    public boolean execute(String ds, String ddlSql) {
        return VersionUtil.supportVersion(false);
    }

    @Override
    public boolean execute(String ds, List<String> ddlSqls) {
        return VersionUtil.supportVersion(false);
    }
}
