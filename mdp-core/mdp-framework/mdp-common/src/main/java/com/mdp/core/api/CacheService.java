package com.mdp.core.api;

import java.util.concurrent.TimeUnit;

/**
 * @param <T>
 */
public interface CacheService<T> {

	/**
	 * 提供自定义缓存独立空间
	 */
	public default String getSpaceKey(){
		return "mdp_#@#";
	};

	public     void put(String hkey,T value);
	 
	public   T get(String hkey);
	
	default void remove(String hkey){

	};


	default public void delete(String hkey){
		this.remove(hkey);
	};

	default boolean containsKey(String hkey){
		return false;
	};
	
	default void refresh(){

	};

	
	default boolean containsValue(T value){
		return false;
	};
	
	default boolean expire(long milliseconds){
		return expire(milliseconds,TimeUnit.MILLISECONDS);
	};

	default boolean expire(long time,TimeUnit timeUnit){
		return false;
	};
 
	default void setValue(String key, T value){

	};
 
	default void setValue(String key, T value, long time, TimeUnit timeUnit){

	};

	default T getValue(String key){
		return null;
	};
	default boolean setIfAbsent(String key, T value, long time, TimeUnit timeUnit){
		return false;
	};

}
