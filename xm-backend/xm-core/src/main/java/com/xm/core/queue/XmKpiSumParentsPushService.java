package com.xm.core.queue;

import com.mdp.mq.queue.Push;
import com.xm.core.entity.XmKpi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class XmKpiSumParentsPushService {

    @Autowired
    Push push;

    public static String queueName="xm-kpi-sum-parents";

    public void pushXmKpi(XmKpi xmKpi){
        if(xmKpi==null || !StringUtils.hasText(xmKpi.getId())){
            return;
        }
        push.leftPush(queueName,xmKpi);
    }

    public void pushXmKpis(List<XmKpi> xmKpis){
        if(xmKpis==null ||xmKpis.size()==0){
            return;
        }
        for (XmKpi xmKpi : xmKpis) {
            push.leftPush(queueName,xmKpi);
        }

    }
}
