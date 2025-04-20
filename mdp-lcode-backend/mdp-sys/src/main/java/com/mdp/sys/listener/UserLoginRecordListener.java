package com.mdp.sys.listener;

import com.mdp.core.utils.BaseUtils;
import com.mdp.mq.queue.QueueListener;
import com.mdp.sys.entity.UserLoginRecord;
import com.mdp.sys.service.UserLoginRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.Map;

@Service
public class UserLoginRecordListener extends QueueListener<Map<String,Object>> {

    @Autowired
    UserLoginRecordService service;

    @Override
    public Object getQueueKey() {
        return "MdpLoginQueue";
    }

    @Override
    public void handleMessage(Map<String, Object> message) {
        if(message==null || message.isEmpty() || !message.containsKey("userid")){
            return;
        }
        UserLoginRecord record=BaseUtils.fromMap(message,UserLoginRecord.class);
        if(!StringUtils.hasText(record.getId())){
            record.setId(service.createKey("id"));
        }
        service.insert(record);
    }
    @PostConstruct
    public void start() {
        this.popMessage();
    }
}
