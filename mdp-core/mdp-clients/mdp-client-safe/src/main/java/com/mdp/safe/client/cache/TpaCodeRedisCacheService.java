package com.mdp.safe.client.cache;

import com.mdp.core.api.CacheHKVService;
import com.mdp.core.api.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 用于缓存第三方登陆时，验证通过的code，便于服务器验证时，可以根据code判断是否已经通过验证，防止跨站攻击
 */
@Service
public class TpaCodeRedisCacheService implements CacheService<String> {
    @Autowired
    CacheHKVService<String> cacheHKVService;

    public TpaCodeRedisCacheService() {
    }

    String geTpaCodeCacheKey() {
        return TpaCodeRedisCacheService.class.getName();
    }

    public void put(String key, String code) {
        this.cacheHKVService.setValue(this.geTpaCodeCacheKey() + ":" + key, code, 5L, TimeUnit.MINUTES);
    }

    public String get(String key) {
        return (String)this.cacheHKVService.getValue(this.geTpaCodeCacheKey() + ":" + key);
    }

    public void remove(String key) {
        this.cacheHKVService.setValue(this.geTpaCodeCacheKey() + ":" + key, (String) null);
    }

    public boolean containsKey(String key) {
        return this.cacheHKVService.getValue(this.geTpaCodeCacheKey() + ":" + key) != null;
    }


}
