package com.mdp.sys.listener;

import com.mdp.core.utils.NumberUtil;
import com.mdp.mq.queue.QueueListener;
import com.mdp.sys.service.UserGradeRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Map;

/**
 * 任务投标成功后，更新会员相关数据
 */
@Service
public class XmTaskBidForPersonMessageListener extends QueueListener<Map<String,Object>> {


    private static final Logger logger = LoggerFactory.getLogger(XmTaskBidForPersonMessageListener.class);


    @Autowired
    UserGradeRecordService userGradeRecordService;

    @Override
    public Object getQueueKey() {
        return "xm_task_bid_for_person";
    }

    @Override
    @Transactional
    public void handleMessage(Map<String, Object> userInterests) {
        logger.debug("=============更新用户投标后的投标数据=============");
        String userid= (String) userInterests.get("userid");
        if(!StringUtils.hasText(userid)){
            return;
        }
        Integer bids= NumberUtil.getInteger(userInterests.get("bids"),0);
        BigDecimal exp=NumberUtil.getBigDecimal(userInterests.get("exp"),BigDecimal.ZERO);
        BigDecimal at=NumberUtil.getBigDecimal(userInterests.get("at"),BigDecimal.ZERO);
        if(bids==0 && exp.compareTo(BigDecimal.ZERO)==0 && at.compareTo(BigDecimal.ZERO)==0){
            return;
        }

        String bizId= (String) userInterests.get("bizId");

        userGradeRecordService.addRecord(userid,"1",bizId,exp,at,"投标获得经验值");


        logger.debug("=============更新用户投标后的投标数据=============");
    }

    @PostConstruct
    public void init(){
        super.popMessage();
    }
}
