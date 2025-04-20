package com.xm.core.listener;

import com.mdp.mq.queue.QueueListener;
import com.xm.core.entity.XmTask;
import com.xm.core.queue.XmTaskSumParentsPushService;
import com.xm.core.service.XmTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class XmTaskSumParentsListener extends QueueListener<XmTask> {

        Map<String,Map<String,XmTask>> tasksAllMap=new HashMap<>();


    @Autowired
    XmTaskService xmTaskService;


    @Override
    public Object getQueueKey() {
        return XmTaskSumParentsPushService.queueName;
    }

    @Override
    public Long timeOut() {
        return 30000L;
    }

    @Override
    public void handleMessage(XmTask message) {
         synchronized (tasksAllMap){
             Map<String,XmTask> my=tasksAllMap.get(message.getProjectId());
             if(my==null){
                 my=new HashMap<>();
                 tasksAllMap.put(message.getProjectId(),my);
             }
             my.put(message.getId(),message);
         }
    }

    /**
     * 每隔一段时间更新一次数据库
     */
    @Scheduled(cron = "*/30 * * * * ?")
    public void autoUpdateToDb(){
       Map<String,Map<String,XmTask>> myTasksAllMap=new HashMap<>();
        synchronized (this.tasksAllMap){
            myTasksAllMap.putAll(this.tasksAllMap);
            this.tasksAllMap.clear();
        }
        if(myTasksAllMap.size()>0){

            List<XmTask> tasks=new ArrayList<>();
             myTasksAllMap.forEach((projectId,tasksMap)->{
                 tasks.addAll(tasksMap.values());
                 if(tasks.size()>100){
                     new Thread(){
                         @Override
                         public void run() {
                             List<XmTask> myTasks=new ArrayList<>();
                             synchronized (tasks){
                                 myTasks.addAll(tasks);
                                 tasks.clear();
                             }
                             try {
                                 xmTaskService.batchSumParents(myTasks);
                             }catch (Exception e){
                                 xmTaskService.batchSumParents(myTasks);
                             }
                         }
                     }.start();

                 }
             });
             if(tasks.size()>0){
                 try {
                     xmTaskService.batchSumParents(tasks);
                     tasks.clear();
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
