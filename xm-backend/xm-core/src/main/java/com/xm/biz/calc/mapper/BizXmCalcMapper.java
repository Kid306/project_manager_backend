package com.xm.biz.calc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface BizXmCalcMapper extends BaseMapper {

    void xmMenuSumParents(String id);

    void xmMenuBatchSumParents(List<String> ids);

    void xmTaskSumParents(String id);

    void xmTaskBatchSumParents(List<String> ids);


    void calcXmTestPlan(String id);
}
