package com.xm.core.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.err.BizException;
import com.mdp.core.service.BaseService;
import com.xm.core.entity.XmMyFocus;
import com.xm.core.mapper.XmMyFocusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br> 
 * 组织 com.qqkj  顶级模块 oa 大模块 xm 小模块 <br>
 * 实体 XmMyFocus 表 XM.xm_my_focus 当前主键(包括多主键): id; 
 ***/
@Service("xm.core.xmMyFocusService")
public class XmMyFocusService extends BaseService<XmMyFocusMapper,XmMyFocus> {

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

	@Autowired
	XmRecordService xmRecordService;
	/**
	 * 取消关注
	 * @param xmMyFocus
	 */
	public void unfocus(XmMyFocus xmMyFocus) {
		this.deleteByPk(xmMyFocus);
		if("1".equals(xmMyFocus.getFocusType())) {
			xmRecordService.addXmProjectRecord(xmMyFocus.getBizId(), "项目-取消关注", xmMyFocus.getUsername()+"取消关注了项目"+xmMyFocus.getBizName());
		}else if("2".equals(xmMyFocus.getFocusType())) {
			xmRecordService.addXmTaskRecord(xmMyFocus.getPbizId(), xmMyFocus.getBizId(),"任务-取消关注", xmMyFocus.getUsername()+"取消关注了任务"+xmMyFocus.getBizName());

		}else if("3".equals(xmMyFocus.getFocusType())) {
			xmRecordService.addXmProductRecord(xmMyFocus.getBizId(),  "产品-取消关注", xmMyFocus.getUsername()+"取消关注了产品"+xmMyFocus.getBizName());

		}else if("4".equals(xmMyFocus.getFocusType())) {
			xmRecordService.addXmMenuRecord(xmMyFocus.getPbizId(), xmMyFocus.getBizId(),xmMyFocus.getPbizId(), "需求-取消关注", xmMyFocus.getUsername()+"取消关注了需求"+xmMyFocus.getBizName());

		}else if("5".equals(xmMyFocus.getFocusType())) {
			xmRecordService.addXmBugRecord(null,xmMyFocus.getPbizId(), xmMyFocus.getBizId(),null,"缺陷-取消关注", xmMyFocus.getUsername()+"取消关注了缺陷"+xmMyFocus.getBizName());

		}else if("6".equals(xmMyFocus.getFocusType())) {
			xmRecordService.addXmIterationRecord( xmMyFocus.getPbizId(),xmMyFocus.getBizId(), "迭代-取消关注", xmMyFocus.getUsername()+"取消关注迭代"+xmMyFocus.getBizName());

		}else {
			throw new BizException("focusType参数必须上传，取值project/task");
		}


	}
	
	/**
	 * 关注项目或者任务
	 * @param xmMyFocus
	 */
	public void focus(XmMyFocus xmMyFocus) {
		xmMyFocus.setFtime(new Date());
		this.insert(xmMyFocus);
		if("1".equals(xmMyFocus.getFocusType())) {
				 xmRecordService.addXmProjectRecord(xmMyFocus.getBizId(), "项目-关注项目", xmMyFocus.getUsername()+"关注了项目"+xmMyFocus.getBizName());
		 }else if("2".equals(xmMyFocus.getFocusType())) {
				 xmRecordService.addXmTaskRecord(xmMyFocus.getPbizId(), xmMyFocus.getBizId(), "任务-关注任务", xmMyFocus.getUsername()+"关注了任务"+xmMyFocus.getBizName());

		 }else if("3".equals(xmMyFocus.getFocusType())) {
			xmRecordService.addXmProductRecord(xmMyFocus.getBizId(),  "产品-关注", xmMyFocus.getUsername()+"关注了产品"+xmMyFocus.getBizName());

		}else if("4".equals(xmMyFocus.getFocusType())) {
			xmRecordService.addXmMenuRecord(xmMyFocus.getPbizId(), xmMyFocus.getBizId(), xmMyFocus.getPbizId(),"需求-关注需求", xmMyFocus.getUsername()+"关注了需求"+xmMyFocus.getBizName());

		}else if("5".equals(xmMyFocus.getFocusType())) {
			xmRecordService.addXmBugRecord(null,xmMyFocus.getPbizId(), xmMyFocus.getBizId(),null,"缺陷-关注缺陷", xmMyFocus.getUsername()+"关注了缺陷"+xmMyFocus.getBizName());

		}else if("6".equals(xmMyFocus.getFocusType())) {
			xmRecordService.addXmIterationRecord( xmMyFocus.getPbizId(),xmMyFocus.getBizId(), "迭代-关注", xmMyFocus.getUsername()+"关注了迭代"+xmMyFocus.getBizName());

		}else {
			 throw new BizException("focusType参数必须上传，取值1、2、3、4、5、6");
		 } 
		
	}

	public List<Map<String, Object>> myFocusForIndex(String userid) {
		return baseMapper.myFocusForIndex(userid);
	}

	/** 请在此类添加自定义函数 */

}

