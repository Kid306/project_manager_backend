package com.mdp.sys.listener;

import com.mdp.core.utils.BaseUtils;
import com.mdp.mq.queue.QueueListener;
import com.mdp.msg.client.PushRecordService;
import com.mdp.sys.entity.Record;
import com.mdp.sys.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.Map;

@Service
public class RecordListener extends QueueListener<Map<String,Object>> {

    @Autowired
    RecordService service;

    @Override
    public Object getQueueKey() {
        return PushRecordService.queueName;
    }

    @Override
    public void handleMessage(Map<String, Object> message) {
        if(message==null || message.isEmpty() || !message.containsKey("opUserid")){
            return;
        }
        Record record=BaseUtils.fromMap(message,Record.class);
        if(!StringUtils.hasText(record.getId())){
            record.setId(service.createKey("id"));
        }
        record.setOperTime(new Date());
        service.insert(record);
    }
    @PostConstruct
    public void start() {
        this.popMessage();
    }
}
