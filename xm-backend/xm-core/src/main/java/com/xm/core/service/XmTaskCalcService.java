package com.xm.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br> 
 * 组织 com.mdp  顶级模块 arc 大模块 archive 小模块 <br>
 * 实体 ArchiveUp 表 arc_archive_up 当前主键(包括多主键): archive_id,userid; 
 ***/
@Service("xm.core.xmTaskCalcService")
public class XmTaskCalcService {
	static Logger logger =LoggerFactory.getLogger(XmTaskCalcService.class);


	private static Map<String,Integer> readMap=new HashMap<>();

	@Autowired
	XmTaskService xmTaskService;


	public static void putReadNum(String taskId,int nums){
		Integer c=readMap.get(taskId);
		if(c==null){
			readMap.put(taskId,new Integer(nums));
		}else{
			readMap.put(taskId,new Integer(c+nums));
		}
	}


	@Scheduled(cron = "* 0/2 * * * ?")
	private void calcRead(){
		Map<String,Integer> map=new HashMap<>();
		map.putAll(readMap);
		readMap.clear();
		if(map.isEmpty()){
			return;
		}
 		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			 xmTaskService.upBrowseTimes(entry.getKey(),entry.getValue());
		}
	}
}

