package com.xm.core.listener;

import com.mdp.mq.queue.QueueListener;
import com.xm.core.entity.XmMenu;
import com.xm.core.queue.XmMenuSumParentsPushService;
import com.xm.core.service.XmMenuStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class XmMenuSumParentsListener extends QueueListener<XmMenu> {

        Map<String,Map<String,XmMenu>> menusAllMap =new HashMap<>();


    @Autowired
    XmMenuStateService xmMenuStateService;


    @Override
    public Object getQueueKey() {
        return XmMenuSumParentsPushService.queueName;
    }

    @Override
    public Long timeOut() {
       return 30000L;
    }

    @Override
    public void handleMessage(XmMenu message) {
        synchronized (menusAllMap){
            Map<String,XmMenu> my=menusAllMap.get(message.getProductId());
            if(my==null){
                my=new HashMap<>();
                menusAllMap.put(message.getProductId(),my);
            }
            my.put(message.getMenuId(),message);
        }
    }

    /**
     * 每隔一段时间更新一次数据库
     */
    @Scheduled(cron = "*/30 * * * * ?")
    public void autoUpdateToDb(){
        Map<String,Map<String, XmMenu>> myMenusAllMap=new HashMap<>();
        synchronized (this.menusAllMap){
            myMenusAllMap.putAll(this.menusAllMap);
            this.menusAllMap.clear();
        }
        if(myMenusAllMap.size()>0){

            List<XmMenu> menus=new ArrayList<>();
             myMenusAllMap.forEach((productId,menusMap)->{
                menus.addAll(menusMap.values());
                if(menus.size()>100){
                    new Thread(){
                        @Override
                        public void run() {
                            List<XmMenu> myMenus=new ArrayList<>();
                            synchronized (menus){
                                myMenus.addAll(menus);
                                menus.clear();
                            }
                            try {
                                xmMenuStateService.batchSumParents(myMenus);
                            }catch (Exception e){
                                xmMenuStateService.batchSumParents(myMenus);
                            }
                        }
                    }.start();

                }
            });
            if(menus.size()>0){
                try {
                    xmMenuStateService.batchSumParents(menus);
                    menus.clear();
                }catch (Exception e){
                    xmMenuStateService.batchSumParents(menus);
                }
            }
        }

    }

    @PostConstruct
    public void init(){
        super.popMessage();
    }
}
