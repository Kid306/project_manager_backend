package com.mdp.sys.listener;

import com.mdp.core.utils.BaseUtils;
import com.mdp.mq.queue.QueueListener;
import com.mdp.sys.entity.UserCreditRecord;
import com.mdp.sys.service.UserCreditRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;

@Service
public class UserCreditRecordListener extends QueueListener<Map<String,Object>> {

    @Autowired
    UserCreditRecordService service;

    @Override
    public Object getQueueKey() {
        return "sys-user-credit-record";
    }

    @Override
    public void handleMessage(Map<String, Object> message) {
        if(message==null || message.isEmpty() || !message.containsKey("userid")){
            return;
        }
        UserCreditRecord record=BaseUtils.fromMap(message,UserCreditRecord.class);
        service.addCreditScore(record.getUserid(),record.getBizType(),record.getBizId(),record.getRemark());
    }
    @PostConstruct
    public void start() {
        this.popMessage();
    }
}
