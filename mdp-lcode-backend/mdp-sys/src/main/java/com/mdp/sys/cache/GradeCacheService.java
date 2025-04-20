package com.mdp.sys.cache;


import com.mdp.safe.client.cache.SafeRedisCacheService;
import com.mdp.sys.entity.Grade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeCacheService extends SafeRedisCacheService<List<Grade>> {

    @Override
    public String getCacheKey() {
        return "sys-grade";
    }

    /**
     * 等级分类(1-唛盟众包,2-电商平台)
     * @param gtype 等级分类(1-唛盟众包,2-电商平台)
     * @return
     */
    public List<Grade> getGrades(String gtype ){
        return this.get(gtype);
    }
    /**
     * 等级分类(1-唛盟众包,2-电商平台)
     * @param gtype 等级分类(1-唛盟众包,2-电商平台)
     * @return
     */
    public void putGrades( String gtype,List<Grade> grades){
        this.put(gtype,grades);
    }


}
