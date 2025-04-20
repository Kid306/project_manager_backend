package com.xm.core.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.service.BaseService;
import com.xm.core.entity.XmTestCase;
import com.xm.core.mapper.XmTestCaseMapper;
import com.xm.rpt.XmRptQueryServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br> 
 * 组织 com.qqkj  顶级模块 xm 大模块 core 小模块 <br>
 * 实体 XmTestCase 表 XM.xm_test_case 当前主键(包括多主键): id; 
 ***/
@Service("xm.core.xmTestCaseService")
public class XmTestCaseService extends BaseService<XmTestCaseMapper, XmTestCase> {

    @Autowired
    XmRptQueryServiceApi rptServiceApi;
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
    public List<Map<String, Object>> getXmTestCaseSort(IPage page, QueryWrapper ew, Map<String,Object> ext) {
        return rptServiceApi.getXmTestCaseSort(page,ew,ext);
    }

    public void calcWorkloadByRecord(List<String> ids) {
        if(ids==null || ids.size()<=0){
            return;
        }
        ids=ids.stream().collect(Collectors.toSet()).stream().collect(Collectors.toList());
        baseMapper.calcWorkloadByRecord(ids);
    }

    public void batchUpdateBudgetWorkloadAndRate(List<String> ids,BigDecimal budgetWorkload) {
        baseMapper.batchUpdateBudgetWorkloadAndRate(map("ids",ids,"budgetWorkload",budgetWorkload));
    }

    /** 请在此类添加自定义函数 */

}

