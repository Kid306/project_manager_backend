package com.xm.core.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.service.BaseService;
import com.xm.core.entity.XmTestPlanCase;
import com.xm.core.mapper.XmTestPlanCaseMapper;
import com.xm.rpt.XmRptQueryServiceApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br> 
 * 组织 com  顶级模块 xm 大模块 core 小模块 <br>
 * 实体 XmTestPlanCase 表 xm_test_plan_case 当前主键(包括多主键): case_id,plan_id; 
 ***/
@Service("xm.core.xmTestPlanCaseService")
public class XmTestPlanCaseService extends BaseService<XmTestPlanCaseMapper,XmTestPlanCase> {
    static Logger logger =LoggerFactory.getLogger(XmTestPlanCaseService.class);

    @Autowired
    XmRptQueryServiceApi rptService;

    /**
     * 自定义查询，支持多表关联
     * @param page 分页条件
     * @param ew 一定要，并且必须加@Param("ew")注解
     * @param ext 如果xml中需要根据某些值进行特殊处理，可以通过这个进行传递，非必须，注解也可以不加
     * @return
     */
    public List<Map<String,Object>> selectListMapByWhere(IPage page, QueryWrapper ew, Map<String,Object> ext){
        return baseMapper.selectListMapByWhere(page,ew,ext);
    }

    public List<XmTestPlanCase> selectListByCaseIdsAndPlanId(String planId, List<String> caseIds) {
        return baseMapper.selectListByCaseIdsAndPlanId(map("planId",planId,"caseIds",caseIds));
    }

    public List<Map<String, Object>> getXmTestPlanCaseExecStatusDist(Map<String, Object> xmTestPlanCase) {
        return rptService.getXmTestPlanCaseExecStatusDist(xmTestPlanCase);
    }

    public List<Map<String, Object>> getXmTestPlanCaseUserDist(Map<String, Object> xmTestPlanCase) {
        return rptService.getXmTestPlanCaseUserDist(xmTestPlanCase);
    }

    public List<Map<String, Object>> getXmTestDayTimesList(Map<String, Object> xmTestPlanCase) {
        return rptService.getXmTestDayTimesList(xmTestPlanCase);

    }
    public List<Map<String, Object>> getXmTestCaseToPlanCalcList(Map<String, Object> xmTestPlanCase) {
        return rptService.getXmTestCaseToPlanCalcList(xmTestPlanCase);

    }



    public void calcWorkloadByRecord(List<Map<String, Object>> ids) {
        if(ids==null || ids.size()<=0){
            return;
        }
        baseMapper.calcWorkloadByRecord(ids);
    }

    public void batchUpdateBudgetWorkloadAndRate(List<Map<String, Object>> ids,BigDecimal budgetWorkload) {
        baseMapper.batchUpdateBudgetWorkloadAndRate(map("ids",ids,"budgetWorkload",budgetWorkload));
    }
}

