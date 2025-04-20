package com.xm.rpt;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface XmRptQueryServiceApi {

    List<Map<String, Object>> getXmTaskAttDist(IPage page,  QueryWrapper ew, Map<String,Object> ext);

    List<Map<String, Object>> getXmTaskAgeDist(QueryWrapper ew,Map<String,Object> ext);

    List<Map<String, Object>> getXmTaskSort(IPage page, QueryWrapper ew,Map<String,Object> ext);

    List<Map<String, Object>> getXmKpiAttDist(IPage page, QueryWrapper ew, Map<String,Object> ext);

    List<Map<String, Object>> getXmKpiHis(String id);

    List<Map<String, Object>> getXmMenuAttDist(IPage page, QueryWrapper ew, Map<String,Object> ext);

    List<Map<String, Object>> getXmMenuAgeDist(QueryWrapper ew,Map<String,Object> ext);

    List<Map<String, Object>> getXmMenuSort(IPage page, QueryWrapper ew,Map<String,Object> ext);

    List<Map<String, Object>> getXmTestCaseSort(IPage page, @Param("ew") QueryWrapper ew,@Param("ext") Map<String,Object> ext);

    List<Map<String, Object>> getXmTestPlanCaseExecStatusDist(Map<String, Object> xmTestPlanCase);

    List<Map<String, Object>> getXmTestPlanCaseUserDist(Map<String, Object> xmTestPlanCase);

    List<Map<String, Object>> getXmTestDayTimesList(Map<String, Object> xmTestPlanCase);

    List<Map<String, Object>> getXmTestCaseToPlanCalcList(Map<String, Object> xmTestPlanCase);

    List<Map<String, Object>> getXmQuestionAttDist(IPage page, @Param("ew") QueryWrapper ew,@Param("ext") Map<String,Object> ext);

    List<Map<String, Object>> getXmQuestionAgeDist(IPage page, @Param("ew") QueryWrapper ew,@Param("ext") Map<String,Object> ext);

    List<Map<String, Object>> getXmQuestionSort(IPage page, @Param("ew") QueryWrapper ew,@Param("ext") Map<String,Object> ext);

    List<Map<String, Object>> getXmQuestionRetestDist(IPage page, @Param("ew") QueryWrapper ew,@Param("ext") Map<String,Object> ext);
    List<Map<String, Object>> listProjectWorkloadSetDay(IPage page, @Param("ew") QueryWrapper ew,@Param("ext") Map<String,Object> ext);

    List<Map<String, Object>> listProjectWorkloadSetMonth(IPage page, @Param("ew") QueryWrapper ew,@Param("ext") Map<String,Object> ext);

    List<Map<String, Object>> listTaskWorkloadBySbillIdGroupByUseridAndTaskId(String sbillId);



    List<Map<String, Object>> selectListMapGroupByTaskIdAndUserid(Map<String, Object> xmWorkload);




    List<Map<String, Object>> listGroupByTaskIdAndUseridToSet(Map<String, Object> xmWorkload);


    List<Map<String, Object>> listSumWorkloadHumanEffectTrend(IPage page, @Param("ew") QueryWrapper ew,@Param("ext") Map<String,Object> ext);

    List<Map<String, Object>> listSumWorkloadHumanEffectSort(IPage page, @Param("ew") QueryWrapper ew,@Param("ext") Map<String,Object> ext);

    List<Map<String, Object>> getXmRiskAttDist(IPage page, QueryWrapper qw, Map<String, Object> ext);
}
