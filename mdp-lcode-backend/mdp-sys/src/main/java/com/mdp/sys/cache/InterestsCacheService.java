package com.mdp.sys.cache;


import com.mdp.core.api.CacheHKVService;
import com.mdp.core.utils.BaseUtils;
import com.mdp.meta.client.entity.ExtInfo;
import com.mdp.meta.client.entity.ItemVo;
import com.mdp.meta.client.service.ItemService;
import com.mdp.safe.client.cache.SafeRedisCacheService;
import com.mdp.sys.entity.Interests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InterestsCacheService extends SafeRedisCacheService<List<Interests>> {

    @Autowired
    CacheHKVService cacheHKVService;

    @Autowired
    ItemService itemService;

    Interests defaultInterests=null;

    @Override
    public String getCacheKey() {
        return "sys-interests";
    }

    /**
     * 等级分类(1-唛盟众包,2-电商平台)
     * @param itype 等级分类(1-唛盟众包,2-电商平台)
     * @return
     */
    public List<Interests> getInterestss(String itype){
        return super.get(itype);
    }


    public Interests getDefaultInterests(String itype){
        if(defaultInterests!=null){
            return defaultInterests;
        }
        ItemVo itemVo=itemService.getDict("sysParam","mkInterests");
        Map<String,Object> map=new HashMap<>();
        for (ExtInfo extInfo : itemVo.getExtInfoList()) {
            map.put(extInfo.getId(),extInfo.getValue());
        }
        defaultInterests=BaseUtils.fromMap(map,Interests.class);
        return defaultInterests;
    }
    /**
     * 等级分类(1-唛盟众包,2-电商平台)
     * @param itype 等级分类(1-唛盟众包,2-电商平台)
     * @return
     */
    public void putInterestss(String itype,List<Interests> interestss){
        super.put(itype,interestss);

    }

    @Scheduled(cron = "0 0/30 * * * ?")
    public void clearDefault(){
        this.defaultInterests=null;
    }


}
