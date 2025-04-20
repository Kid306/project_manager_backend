package com.xm.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br> 
 * 组织 com.mdp  顶级模块 arc 大模块 archive 小模块 <br>
 * 实体 ArchiveUp 表 arc_archive_up 当前主键(包括多主键): archive_id,userid; 
 ***/
@Service("xm.xmMenu.xmMenuCalcService")
public class XmMenuCalcService {
	static Logger logger =LoggerFactory.getLogger(XmMenuCalcService.class);

	public static Set<String> upSet =new HashSet<>();
	public static  Set<String> commentsSet =new HashSet<>();

	private static Map<String,Integer> readMap=new HashMap<>();

	@Autowired
	XmMenuService xmMenuService;


	public static void putReads(String menuId,int nums){
		Integer c=readMap.get(menuId);
		if(c==null){
			readMap.put(menuId,new Integer(nums));
		}else{
			readMap.put(menuId,new Integer(c+nums));
		}
	}

	@Scheduled(cron = "* */4 * * * ?")
	private void calcUp(){
		if(upSet.isEmpty()){
			return;
		}
		List<String> menuIds= upSet.stream().collect(Collectors.toList());
		upSet.clear();

		xmMenuService.updateUps(menuIds);

	}

	@Scheduled(cron = "* */5 * * * ?")
	private void calcComments(){
		if(commentsSet.isEmpty()){
			return;
		}
		List<String> menuIds= commentsSet.stream().collect(Collectors.toList());
		commentsSet.clear();

		xmMenuService.updateComments(menuIds);

	}

	@Scheduled(cron = "* */2 * * * ?")
	private void calcRead(){
		Map<String,Integer> map=new HashMap<>();
		map.putAll(readMap);
		readMap.clear();
		if(map.isEmpty()){
			return;
		}
 		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			 xmMenuService.upReads(entry.getKey(),entry.getValue());
		}
	}
}

