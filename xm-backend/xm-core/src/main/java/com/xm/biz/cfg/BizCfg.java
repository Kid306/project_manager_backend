package com.xm.biz.cfg;

import com.xm.biz.calc.service.BizXmCalcService;
import com.xm.calc.service.XmCalcServiceApi;
import com.xm.core.service.DefaultXmRecordService;
import com.xm.core.service.XmRecordServiceApi;
import com.xm.rpt.DefaultXmRptQueryService;
import com.xm.rpt.XmRptQueryServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BizCfg {

    @ConditionalOnMissingBean(XmCalcServiceApi.class)
    @Autowired
    @Bean
    XmCalcServiceApi xmCalcServiceApi(){
        XmCalcServiceApi bizXmCalcService=new BizXmCalcService();
        return bizXmCalcService;
    }
    @ConditionalOnMissingBean(XmRecordServiceApi.class)
    @Autowired
    @Bean
    XmRecordServiceApi xmRecordServiceApi(){
        XmRecordServiceApi xmRecordServiceApi=new DefaultXmRecordService();
        return xmRecordServiceApi;
    }
    @ConditionalOnMissingBean(XmRptQueryServiceApi.class)
    @Autowired
    @Bean
    XmRptQueryServiceApi xmRptQueryServiceApi(){
        XmRptQueryServiceApi xmRptQueryServiceApi=new DefaultXmRptQueryService();
        return xmRptQueryServiceApi;
    }
}
