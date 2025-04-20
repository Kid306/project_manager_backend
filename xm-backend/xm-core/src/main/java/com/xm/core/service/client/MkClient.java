package com.xm.core.service.client;

import com.mdp.core.entity.Tips;
import com.mdp.core.utils.BaseUtils;
import com.mdp.micro.client.CallBizService;
import com.mdp.mq.queue.Push;
import com.xm.core.entity.XmTaskSbillDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.mdp.core.utils.BaseUtils.map;

@Service
/**
 * 对mk接口调用
 */
public class MkClient {

    @Autowired
    CallBizService callBizService;

    @Autowired
    Push push;

    public Tips pushSbillDetail(XmTaskSbillDetail detail){
        Tips tips = new Tips("推送订单成功");
        push.leftPush("xm_task_settle",BaseUtils.toMap(detail));
         return tips;
    }

    /**
     * 任务验收后，推送任务金额到mk用于计算分享赚佣金
     * @return
     */
    public Tips pushAfterTaskAcceptanceSuccess(String userid, String username, String projectId, String taskId, BigDecimal shareFee){
        Tips tips = new Tips("推送订单成功");
        push.leftPush("xm_task_acceptance_success_for_person_share_earn",map("userid",userid  ,"username",username  ,"projectId",projectId  ,"taskId",taskId,"shareFee",shareFee));
        // strRedisTemplate.convertAndSend("xm_task_settle", JSON.toJSONString(params));
        return tips;
    }
    /**
     * 任务中标后，推送任务金额到mk用于计算分享赚佣金
     * @return
     */
    public Tips pushAfterTaskExecSuccess(String userid, String username, String projectId, String taskId, BigDecimal shareFee){
        Tips tips = new Tips("推送订单成功");
        push.leftPush("xm_task_exec_for_person_share_earn",map("userid",userid  ,"username",username  ,"projectId",projectId  ,"taskId",taskId,"shareFee",shareFee));
        // strRedisTemplate.convertAndSend("xm_task_settle", JSON.toJSONString(params));
        return tips;
    }



}
