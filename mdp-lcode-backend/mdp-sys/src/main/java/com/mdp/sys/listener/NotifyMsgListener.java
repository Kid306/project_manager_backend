package com.mdp.sys.listener;

import com.mdp.core.utils.BaseUtils;
import com.mdp.mq.queue.QueueListener;
import com.mdp.mq.sp.Publish;
import com.mdp.msg.client.PushNotifyMsgService;
import com.mdp.sys.entity.NotifyMsg;
import com.mdp.sys.service.NotifyMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.Map;

@Service
public class NotifyMsgListener extends QueueListener<Map<String,Object>> {

    @Autowired
    NotifyMsgService service;

    @Autowired
    Publish publish;

    @Override
    public Object getQueueKey() {
        return PushNotifyMsgService.queueName;
    }

    @Override
    public void handleMessage(Map<String, Object> message) {
        if(message==null || message.isEmpty() || !message.containsKey("toUserid")){
            return;
        }
        NotifyMsg record=BaseUtils.fromMap(message,NotifyMsg.class);
        if(!StringUtils.hasText(record.getId())){
            record.setId(service.createKey("id"));
        }
        record.setOperTime(new Date());
        record.setHadRead("0");
        service.insert(record);
        message.put("id",record.getId());
        publish.push((String) getQueueKey(),message);
    }
    @PostConstruct
    public void start() {
        this.popMessage();
    }
}
