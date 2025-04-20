package com.xm.core.listener;

import com.mdp.mq.queue.QueueListener;
import com.xm.core.entity.XmKpi;
import com.xm.core.queue.XmKpiSumParentsPushService;
import com.xm.core.service.XmKpiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class XmKpiSumParentsListener extends QueueListener<XmKpi> {

        Map<String,Map<String,XmKpi>> kpisAllMap=new HashMap<>();


    @Autowired
    XmKpiService xmKpiService;


    @Override
    public Object getQueueKey() {
        return XmKpiSumParentsPushService.queueName;
    }

    @Override
    public Long timeOut() {
        return 30000L;
    }

    @Override
    public void handleMessage(XmKpi message) {
         synchronized (kpisAllMap){
             Map<String,XmKpi> my=kpisAllMap.get(message.getDeptid());
             if(my==null){
                 my=new HashMap<>();
                 kpisAllMap.put(message.getDeptid(),my);
             }
             my.put(message.getId(),message);
         }
    }

    /**
     * 每隔一段时间更新一次数据库
     */
    @Scheduled(cron = "*/30 * * * * ?")
    public void autoUpdateToDb(){
       Map<String,Map<String,XmKpi>> myKpisAllMap=new HashMap<>();
        synchronized (this.kpisAllMap){
            myKpisAllMap.putAll(this.kpisAllMap);
            this.kpisAllMap.clear();
        }
        if(myKpisAllMap.size()>0){

            List<XmKpi> kpis=new ArrayList<>();
             myKpisAllMap.forEach((deptid,kpisMap)->{
                 kpis.addAll(kpisMap.values());
                 if(kpis.size()>100){
                     new Thread(){
                         @Override
                         public void run() {
                             List<XmKpi> myKpis=new ArrayList<>();
                             synchronized (kpis){
                                 myKpis.addAll(kpis);
                                 kpis.clear();
                             }
                             try {
                                 xmKpiService.batchSumParents(myKpis);
                             }catch (Exception e){
                                 xmKpiService.batchSumParents(myKpis);
                             }
                         }
                     }.start();

                 }
             });
             if(kpis.size()>0){
                 try {
                     xmKpiService.batchSumParents(kpis);
                     kpis.clear();
                 }catch (Exception e){

                 }
             }

        }

    }

    @PostConstruct
    public void init(){
        super.popMessage();
    }
}
