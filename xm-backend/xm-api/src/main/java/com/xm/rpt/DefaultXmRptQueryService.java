package com.xm.rpt;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DefaultXmRptQueryService implements XmRptQueryServiceApi{
    @Override
    public List<Map<String, Object>> getXmTaskAttDist(IPage page, QueryWrapper ew, Map<String, Object> ext) {
        return Collections.emptyList();
    }

    @Override
    public List<Map<String, Object>> getXmTaskAgeDist(QueryWrapper ew, Map<String, Object> ext) {
        return Collections.emptyList();
    }

    @Override
    public List<Map<String, Object>> getXmTaskSort(IPage page, QueryWrapper ew, Map<String, Object> ext) {
        return Collections.emptyList();
    }

    @Override
    public List<Map<String, Object>> getXmKpiAttDist(IPage page, QueryWrapper ew, Map<String, Object> ext) {
        return Collections.emptyList();
    }

    @Override
    public List<Map<String, Object>> getXmKpiHis(String id) {
        return Collections.emptyList();
    }

    @Override
    public List<Map<String, Object>> getXmMenuAttDist(IPage page, QueryWrapper ew, Map<String, Object> ext) {
        return Collections.emptyList();
    }

    @Override
    public List<Map<String, Object>> getXmMenuAgeDist(QueryWrapper ew, Map<String, Object> ext) {
        return Collections.emptyList();
    }

    @Override
    public List<Map<String, Object>> getXmMenuSort(IPage page, QueryWrapper ew, Map<String, Object> ext) {
        return Collections.emptyList();
    }

    @Override
    public List<Map<String, Object>> getXmTestCaseSort(IPage page, QueryWrapper ew, Map<String, Object> ext) {
        return Collections.emptyList();
    }

    @Override
    public List<Map<String, Object>> getXmTestPlanCaseExecStatusDist(Map<String, Object> xmTestPlanCase) {
        return Collections.emptyList();
    }

    @Override
    public List<Map<String, Object>> getXmTestPlanCaseUserDist(Map<String, Object> xmTestPlanCase) {
        return Collections.emptyList();
    }

    @Override
    public List<Map<String, Object>> getXmTestDayTimesList(Map<String, Object> xmTestPlanCase) {
        return Collections.emptyList();
    }

    @Override
    public List<Map<String, Object>> getXmTestCaseToPlanCalcList(Map<String, Object> xmTestPlanCase) {
        return Collections.emptyList();
    }

    @Override
    public List<Map<String, Object>> getXmQuestionAttDist(IPage page, QueryWrapper ew, Map<String, Object> ext) {
        return Collections.emptyList();
    }

    @Override
    public List<Map<String, Object>> getXmQuestionAgeDist(IPage page, QueryWrapper ew, Map<String, Object> ext) {
        return Collections.emptyList();
    }

    @Override
    public List<Map<String, Object>> getXmQuestionSort(IPage page, QueryWrapper ew, Map<String, Object> ext) {
        return Collections.emptyList();
    }

    @Override
    public List<Map<String, Object>> getXmQuestionRetestDist(IPage page, QueryWrapper ew, Map<String, Object> ext) {
        return Collections.emptyList();
    }

    @Override
    public List<Map<String, Object>> listProjectWorkloadSetDay(IPage page, QueryWrapper ew, Map<String, Object> ext) {
        return Collections.emptyList();
    }

    @Override
    public List<Map<String, Object>> listProjectWorkloadSetMonth(IPage page, QueryWrapper ew, Map<String, Object> ext) {
        return Collections.emptyList();
    }

    @Override
    public List<Map<String, Object>> listTaskWorkloadBySbillIdGroupByUseridAndTaskId(String sbillId) {
        return Collections.emptyList();
    }

    @Override
    public List<Map<String, Object>> selectListMapGroupByTaskIdAndUserid(Map<String, Object> xmWorkload) {
        return Collections.emptyList();
    }

    @Override
    public List<Map<String, Object>> listGroupByTaskIdAndUseridToSet(Map<String, Object> xmWorkload) {
        return Collections.emptyList();
    }

    @Override
    public List<Map<String, Object>> listSumWorkloadHumanEffectTrend(IPage page, QueryWrapper ew, Map<String, Object> ext) {
        return Collections.emptyList();
    }

    @Override
    public List<Map<String, Object>> listSumWorkloadHumanEffectSort(IPage page, QueryWrapper ew, Map<String, Object> ext) {
        return Collections.emptyList();
    }

    @Override
    public List<Map<String, Object>> getXmRiskAttDist(IPage page, QueryWrapper qw, Map<String, Object> ext) {
        return Collections.emptyList();
    }

}
