package com.xm.biz.calc.service;

import com.mdp.core.utils.ObjectTools;
import com.xm.biz.calc.mapper.BizXmCalcMapper;
import com.xm.calc.service.DefaultXmCalcService;
import com.xm.calc.service.XmCalcServiceApi;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BizXmCalcService extends DefaultXmCalcService implements XmCalcServiceApi  {

    @Autowired
    BizXmCalcMapper xmCalcMapper;

    @Override
    public void xmMenuSumParents(List<String> pidPathsList) {
        for (String menuId : pidPathsList) {
            if("0".equals(menuId)|| ObjectTools.isEmpty(menuId)){
                continue;
            }
            xmCalcMapper.xmMenuSumParents(menuId);
        }
    }

    @Override
    public void xmMenuBatchSumParents(List<String> ids) {
        xmCalcMapper.xmMenuBatchSumParents(ids);
    }

    @Override
    public void xmTaskSumParents(List<String> pidPathsList) {
        for (String id : pidPathsList) {
            if("0".equals(id)|| ObjectTools.isEmpty(id)){
                continue;
            }
            xmCalcMapper.xmTaskSumParents(id);
        }

    }

    @Override
    public void xmTaskBatchSumParents(List<String> ids) {
        xmCalcMapper.xmTaskBatchSumParents(ids);
    }

    @Override
    public void calcXmTestPlan(String id) {
        xmCalcMapper.calcXmTestPlan(id);
    }
}
