package com.mdp.client;

import com.mdp.core.entity.Tips;
import com.mdp.core.err.BizException;
import com.mdp.core.utils.NumberUtil;
import com.mdp.micro.client.CallBizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

import static com.mdp.core.utils.BaseUtils.map;

@Service
public class AcClient {

    @Autowired
    CallBizService callBizService;

    /**
     * 查询用户的历史总收入
     * @param userid
     * @return
     */
   public BigDecimal queryUserTaskReceiveAmount(String userid){
       String api="/accore/tpa/paymentSeq/queryUserTaskReceiveAmount?userid={userid}";
       Map<String,Object> map=callBizService.postForMap(api,map("userid",userid));
       Tips tips= (Tips) map.get("tips");
       if(tips.isOk()){
           return NumberUtil.getBigDecimal(map.get("data"),BigDecimal.ZERO);
       }else{
           throw new BizException(tips);
       }
    }
}
