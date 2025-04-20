package com.mdp.micro.client;

import com.mdp.core.entity.Tips;
import com.mdp.core.utils.BaseUtils;

import java.util.HashMap;
import java.util.Map;

public class ResultWrapper {

    /**
     * 调用微服务后返回的结果转换成tips
     * @param result
     * @return Tips { isOk:true/false,tipscode:'xxx',msg:'xxxxx' }
     */
    public static Tips resultToTips(Map<String,Object> result){
        Tips tips= BaseUtils.mapToTips(result);
        return tips;
    }

    /**
     * 调用微服务后返回的结果转换成tips
     * @param result
     * @return Map { tips:{ isOk:true/false,tipscode:'xxx',msg:'xxxxx' },data:Object }
     */
    public static Map<String,Object> resultToMap(Map<String,Object> result){
        Tips tips=BaseUtils.mapToTips(result);
        if(result==null){
            tips = new Tips();
            tips.setErrMsg("no-result","返回值为空");
            Map<String,Object> re=new HashMap<>();
            re.put("tips",tips);
            return re;
        }else{
            if(tips==null){
                tips=new Tips();
                tips.setErrMsg("no-tips","没有返回tips");
                result.put("tips",tips);
                return result;
            }else{
                result.put("tips",tips);
                return result;
            }
        }
    }
}
