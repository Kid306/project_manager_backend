package com.xm.core.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.service.BaseService;
import com.xm.core.entity.XmWorkload;
import com.xm.core.mapper.XmWorkloadMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br> 
 * 组织 com  顶级模块 xm 大模块 core 小模块 <br>
 * 实体 XmWorkload 表 xm_workload 当前主键(包括多主键): id; 
 ***/
@Service("xm.core.xmWorkloadService")
public class XmWorkloadService extends BaseService<XmWorkloadMapper, XmWorkload> {
	static Logger logger =LoggerFactory.getLogger(XmWorkloadService.class);

	@Autowired
	XmTaskSbillService xmTaskSbillService;

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

	public List<Map<String, Object>> listProjectWorkloadSetDay(IPage page, QueryWrapper ew, Map<String,Object> ext) {
		return baseMapper.listProjectWorkloadSetDay(page,ew,ext);
	}
	public List<Map<String, Object>> listProjectWorkloadSetMonth(IPage page, QueryWrapper ew, Map<String,Object> ext) {
		return baseMapper.listProjectWorkloadSetMonth(page,ew,ext);
	}


	public List<Map<String,Object>> listTaskWorkloadBySbillIdGroupByUseridAndTaskId(String sbillId) {
		return baseMapper.listTaskWorkloadBySbillIdGroupByUseridAndTaskId(sbillId);
	}

	public void updateStatusBySbillIdBySbillDel(String sbillId) {
		baseMapper.updateStatusBySbillIdBySbillDel(sbillId);
	}


	@Transactional
	public void editSomeFieldsWithSbillIds(Map<String, Object> xmWorkloadMap, List<String> sbillIds) {
		super.editSomeFields(xmWorkloadMap);
	}

	public void updateStatusBySbillIdByFlowState(String sbillId,String sstatus) {
		baseMapper.updateStatusBySbillIdByFlowState(map("sbillId",sbillId,"sstatus",sstatus));
	}

	public List<Map<String, Object>> ListGroupByTaskIdAndUserid(Map<String, Object> xmWorkload) {
		return baseMapper.selectListMapGroupByTaskIdAndUserid(xmWorkload);
	}

	public List<Map<String, Object>> ListGroupByTaskIdAndUseridToSet(Map<String, Object> xmWorkload) {
		return baseMapper.listGroupByTaskIdAndUseridToSet(xmWorkload);
	}

    public void updateStatusAfterJoinSbill(Map<String,Object> details) {
		baseMapper.updateStatusAfterJoinSbill(details);
    }

	public void updateStatusAfterDetailDel(List<String> detailIds) {
		baseMapper.updateStatusAfterDetailDel(detailIds);
	}

	@Override
	public String createKey(String keyName) {
		return "TW"+getSequenceService().getCommonNo("{date62:yyyyMMddHHmmss}{rands:4}");
	}
}

