package com.mdp.login.client;

import com.mdp.mq.queue.Push;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.mdp.core.utils.BaseUtils.map;

/**
 * 对sys接口调用
 */
@Service
public class SysClient {



    @Autowired
    Push push;

    /**
     * 登记行用积分流水，并更新用户的信用等级及信用分
     * 引起信用等级变化的业务分类 bizType
     * 1-个人实名认证，10
     * 2-企业认证，10
     * 3-银行卡认证，5
     * 4-手机号认证，5
     * 5-邮箱认证，5
     * 6-微信认证，5
     * 10-加入三保服务保障，20,40,60
     * 11-完成平台任务，每个任务3分(舍弃、不再支持)
     * 12-邀请用户实名注册，每个注册用户得2分(舍弃、不再支持)
     * 13-邀请用户完成任务。每个任务完成得3分(舍弃、不再支持)
     * 14-其它
     * 如果是
     * @param userid
     * @param bizType
     * @param bizId
     */
    public void pushUserCreditScore(String userid,String bizType,String bizId,String remark){
        String queueKey = "sys-user-credit-record";
        push.leftPush(queueKey,map("userid",userid,"bizType",bizType,"bizId",bizId,"remark",remark));

    }
}
