package com.xm.calc.service;

import java.util.List;

public interface XmCalcServiceApi {
    public void loadTasksToXmMenuState(String productId);
    public void loadTasksToXmProductState(String productId);

    public void loadTasksToXmProjectState(String projectId);
    public void loadTasksSettleToXmProjectState(String projectId);
    public void loadTasksToXmIterationState(String iterationId);

    public void loadProjectStateToXmBranchState(String branchId);



    void xmMenuSumParents(List<String> pidPathsList);

    void xmMenuBatchSumParents(List<String> ids);

    void xmTaskSumParents(List<String> pidPathsList);

    void xmTaskBatchSumParents(List<String> ids);

    void xmKpiBatchSumParents(List<String> ids);


    void calcXmTestPlan(String id);


    void loadXmRecordToHis();

    void loadXmQuestionHandleToHis();

    void loadProjectStateToXmCollectState(String collectId);

    void xmKpiSumParents(List<String> collect);
}
