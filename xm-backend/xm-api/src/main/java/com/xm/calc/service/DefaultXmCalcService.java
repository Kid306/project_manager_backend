package com.xm.calc.service;

import com.mdp.core.err.BizException;

import java.util.List;

public class DefaultXmCalcService implements XmCalcServiceApi {
    @Override
    public void loadTasksToXmMenuState(String productId) {
        //checkVersion();
    }

    @Override
    public void loadTasksToXmProductState(String productId) {
        //checkVersion();
    }

    @Override
    public void loadTasksToXmProjectState(String projectId) {
        //checkVersion();
    }

    @Override
    public void loadTasksSettleToXmProjectState(String projectId) {
        //checkVersion();
    }

    @Override
    public void loadTasksToXmIterationState(String iterationId) {
        //checkVersion();
    }

    @Override
    public void loadProjectStateToXmBranchState(String branchId) {
        checkVersion();
    }

    @Override
    public void xmMenuSumParents(List<String> pidPathsList) {
        //checkVersion();
    }

    @Override
    public void xmMenuBatchSumParents(List<String> ids) {
        //checkVersion();
    }

    @Override
    public void xmTaskSumParents(List<String> pidPathsList) {
        //checkVersion();
    }

    @Override
    public void xmTaskBatchSumParents(List<String> ids) {
        //checkVersion();
    }

    @Override
    public void xmKpiBatchSumParents(List<String> ids) {

    }

    @Override
    public void calcXmTestPlan(String id) {

    }

    @Override
    public void loadXmRecordToHis() {

    }

    @Override
    public void loadXmQuestionHandleToHis() {

    }

    @Override
    public void loadProjectStateToXmCollectState(String collectId) {
        checkVersion();
    }

    @Override
    public void xmKpiSumParents(List<String> collect) {

    }


    public void checkVersion(){
        throw new BizException("not-support-version","请购买商业版");
    }
}
