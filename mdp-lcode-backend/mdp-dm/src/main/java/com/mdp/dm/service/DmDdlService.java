package com.mdp.dm.service;

import com.mdp.dm.base.VersionUtil;
import com.mdp.dm.base.service.DdlBaseService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ConditionalOnMissingBean(DdlBaseService.class)
public class DmDdlService implements DdlBaseService {

    public boolean checkVersion() {
        return VersionUtil.supportVersion(false);
    }

    @Override
    public boolean createTable(String ds, String ddlSql) {
        return checkVersion();
    }

    @Override
    public boolean editTable(String ds, String ddlSql) {
        return checkVersion();
    }

    @Override
    public boolean dropTable(String ds, String ddlSql) {
        return checkVersion();
    }

    @Override
    public boolean execute(String ds, String ddlSql) {
        return checkVersion();
    }

    @Override
    public boolean execute(String ds, List<String> ddlSqls) {
        return checkVersion();
    }
}
