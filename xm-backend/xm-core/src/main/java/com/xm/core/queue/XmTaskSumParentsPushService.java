package com.xm.core.queue;

import com.mdp.mq.queue.Push;
import com.xm.core.entity.XmTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class XmTaskSumParentsPushService {

    @Autowired
    Push push;

    public static String queueName="xm-task-sum-parents";

    public void pushXmTask(XmTask xmTask){
        if(xmTask==null || !StringUtils.hasText(xmTask.getId())){
            return;
        }
        push.leftPush(queueName,xmTask);
    }

    public void pushXmTasks(List<XmTask> xmTasks){
        if(xmTasks==null ||xmTasks.size()==0){
            return;
        }
        for (XmTask xmTask : xmTasks) {
            push.leftPush(queueName,xmTask);
        }

    }
}
