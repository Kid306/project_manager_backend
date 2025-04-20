package com.mdp.core.api;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public interface CacheHKVService<T> extends CacheService<T> {


    public     void put(String spaceKey,String hkey,T value);

    public   T get(String spaceKey,String hkey);

    public void remove(String spaceKey,String hkey);

    boolean containsKey(String spaceKey,String hkey);

    void refresh(String spaceKey);

    List<T> getValus(String spaceKey);
    Set<String> keys(String spaceKey);
    default List<T> values(String spaceKey){
        return this.getValus(spaceKey);
    }

    boolean containsValue(String spaceKey,T value);

    boolean expire(String spaceKey,long milliseconds);

    boolean expire(String spaceKey, long time, TimeUnit timeUnit);

    long delete(String spaceKey, Object ...keys);

}
