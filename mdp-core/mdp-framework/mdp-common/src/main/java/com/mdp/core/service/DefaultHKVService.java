package com.mdp.core.service;

import com.mdp.core.api.CacheHKVService;

import java.util.*;
import java.util.concurrent.TimeUnit;


public class DefaultHKVService<T> extends DefaultCacheService<T> implements CacheHKVService<T> {


	private Map<String,T> checkSpace(String spaceKey){
		Map<String,T> space=cache.get(spaceKey);
		if(space==null){
			space=new HashMap<>();
			cache.put(spaceKey,space);
		}
		return space;
	}

	@Override
	public void put(String spaceKey, String hkey, T value) {
		Map<String,T> space= checkSpace(spaceKey);
		space.put(hkey,value);
	}

	@Override
	public T get(String spaceKey, String hkey) {
		Map<String,T> space= checkSpace(spaceKey);
		return (T) space.get(hkey);
	}

	@Override
	public void remove(String spaceKey, String hkey) {
		Map<String,T> space= checkSpace(spaceKey);
		space.remove(hkey);
	}

	@Override
	public boolean containsKey(String spaceKey, String hkey) {
		Map<String,T> space= checkSpace(spaceKey);
		return space.containsKey(hkey);
	}

	@Override
	public void refresh() {
		cache.clear();
	}

	@Override
	public void refresh(String spaceKey) {
		cache.remove(spaceKey);
	}

	@Override
	public List<T> getValus(String spaceKey) {
		Map<String,T> space= checkSpace(spaceKey);
		return  new ArrayList<>(space.values());
	}

	@Override
	public Set<String> keys(String spaceKey) {
		Map<String,T> space=checkSpace(spaceKey);
		return space.keySet();
	}

	@Override
	public boolean containsValue(String spaceKey, T value) {
		Map<String,T> space= checkSpace(spaceKey);
		return space.containsValue(value);
	}

	@Override
	public boolean expire(String spaceKey, long milliseconds) {
		Map<String,T> space= checkSpace(spaceKey);
		return this.expire(milliseconds,TimeUnit.MILLISECONDS);
	}

	@Override
	public boolean expire(String spaceKey, long time, TimeUnit timeUnit) {
		if(taskMap.containsKey(spaceKey)){
			Timer timer=taskMap.get(spaceKey);
			timer.cancel();
		}
		Map<String,T> space= checkSpace(spaceKey);
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
 				space.clear();
			}
		};
		Timer timer = new Timer();
		// 第一个参数是 TimerTask，第二个参数是延迟多少毫秒开始执行，第三个参数是定时任务的执行间隔（以毫秒为单位）
		timer.scheduleAtFixedRate(task, 100, timeUnit.toMillis(time)); // 每秒执行一次
		return true;
	}

	@Override
	public long delete(String spaceKey, Object ...hkeys) {
		Map<String,T> space=checkSpace(spaceKey);
		long l=0;
		for (Object hkey : hkeys) {
			if(space.containsKey(hkey)){
				l=l+1;
				space.remove(hkey);
			}
		}
		return l;
	}

	public static void main(String[] args) {
		TimeUnit timeUnit=TimeUnit.MILLISECONDS;
		System.out.println(timeUnit.toMillis(1));
	}
}
