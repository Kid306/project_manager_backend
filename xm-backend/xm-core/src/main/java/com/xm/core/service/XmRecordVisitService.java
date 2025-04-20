package com.xm.core.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.service.BaseService;
import com.xm.core.entity.XmRecordVisit;
import com.xm.core.mapper.XmRecordVisitMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br> 
 * 组织 com  顶级模块 xm 大模块 core 小模块 <br>
 * 实体 XmRecordVisit 表 xm_record_visit 当前主键(包括多主键): id; 
 ***/
@Service("xm.core.xmRecordVisitService")
public class XmRecordVisitService extends BaseService<XmRecordVisitMapper,XmRecordVisit> {
	static Logger logger =LoggerFactory.getLogger(XmRecordVisitService.class);

	/**
	 * 自定义查询，支持多表关联
	 * @param page 分页条件
	 * @param ew 一定要，并且必须加@Param("ew")注解
	 * @param ext 如果xml中需要根据某些值进行特殊处理，可以通过这个进行传递，非必须，注解也可以不加
	 * @return
	 */
	public List<Map<String,Object>> selectListMapByWhere(IPage page, QueryWrapper ew, Map<String,Object> ext){
		return baseMapper.selectListMapByWhere(page,ew,ext);
	}

	public void batchAddAndCalc(List<XmRecordVisit> datas) {
		Map<String, Integer> timesMap=new HashMap<>();
		Map<String,Set<String> > usersMap=new HashMap<>();
		for (XmRecordVisit data : datas) {
			String key=data.getBizId();
			Set<String> users=usersMap.get(key);
			if(users==null){
				users=new HashSet<>();
			}
			users.add(data.getOperUserid());
			usersMap.put(key,users);
		}
		for (XmRecordVisit data : datas) {
			String key=data.getBizId();
			Integer times=timesMap.get(key);
			if(times==null){
				times=0;
			}
			times=times+1;
			timesMap.put(key,times);
		}
		List<Map<String,Object>> datasToUpdate=new ArrayList<>();
		for (String key : timesMap.keySet()) {
			Map<String,Object> data=new HashMap<>();
			data.put("id",key);
			data.put("browseTimes",timesMap.get(key));
			data.put("browseUsers",usersMap.get(key).size());
			datasToUpdate.add(data);
		}
		this.batchInsert(datas);
		if(datasToUpdate.size()>0){
			super.baseMapper.updateTaskBrowseTimesAndBrowseUsers(datasToUpdate);
		}
	}
}

