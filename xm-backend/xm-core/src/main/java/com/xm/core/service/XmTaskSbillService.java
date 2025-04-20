package com.xm.core.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.err.BizException;
import com.mdp.core.service.BaseService;
import com.mdp.core.utils.DateUtils;
import com.xm.core.entity.XmTaskSbill;
import com.xm.core.entity.XmTaskSbillDetail;
import com.xm.core.mapper.XmTaskSbillMapper;
import com.xm.core.service.client.MkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br> 
 * 组织 com  顶级模块 xm 大模块 core 小模块 <br>
 * 实体 XmTaskSbill 表 xm_task_sbill 当前主键(包括多主键): id; 
 ***/
@Service("xm.core.xmTaskSbillService")
public class XmTaskSbillService extends BaseService<XmTaskSbillMapper,XmTaskSbill> {
	static Logger logger =LoggerFactory.getLogger(XmTaskSbillService.class);
	@Lazy
	@Autowired
    XmWorkloadService xmWorkloadService;
	@Lazy
	@Autowired
	XmTaskSbillDetailService xmTaskSbillDetailService;

	@Autowired
	MkClient mkClient;
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
	@Transactional(rollbackFor = Exception.class)
	public void deleteByPkAndReturnWorkload(XmTaskSbill xmTaskSbill){

		xmWorkloadService.updateStatusBySbillIdBySbillDel(xmTaskSbill.getId());
		this.deleteByPk(xmTaskSbill);
	}

	/**
	 * 流程审批过程中回调该接口，更新业务数据
	 * 如果发起流程时上送了restUrl，则无论流程中是否配置了监听器都会在流程发生以下事件时推送数据过来
	 * eventName: PROCESS_STARTED 流程启动完成 全局
	 *            PROCESS_COMPLETED 流程正常结束 全局
	 *            PROCESS_CANCELLED 流程删除 全局
	 *            create 人工任务启动
	 *            complete 人工任务完成，更新表中流转内容
	 *            assignment 人工任务分配给了具体的人
	 *            delete 人工任务被删除
	 *            TASK_COMPLETED_FORM_DATA_UPDATE 人工任务提交完成后，智能表单数据更新
	 *
	 * 其中 create/complete/assignment/delete事件是需要在模型中人工节点上配置了委托代理表达式 ${taskBizCallListener}才会推送过来。
	 * 在人工任务节点上配置 任务监听器  建议事件为 complete,其它assignment/create/complete/delete也可以，一般建议在complete,委托代理表达式 ${taskBizCallListener}
	 *
	 * @param flowVars {flowBranchId,agree,procInstId,startUserid,assignee,actId,taskName,mainTitle,branchId,bizKey,commentMsg,eventName,modelKey} 等
	 **/
	@Transactional(rollbackFor = Exception.class)
	public void processApprova(Map<String, Object> flowVars) {

		String eventName=(String) flowVars.get("eventName");
		String agree=(String) flowVars.get("agree");
		String branchId=(String) flowVars.get("branchId");
		String bizKey=(String) flowVars.get("bizKey");
		if("xm_task_sbill".equals(bizKey) ) {
			String id= (String) flowVars.get("sbillId");
			XmTaskSbill xmTaskSbill = new XmTaskSbill();
			xmTaskSbill.setId(id);
			xmTaskSbill = this.selectOneObject(xmTaskSbill);

			if("complete".equals(eventName)) {
				if("1".equals(agree)) {
					this.updateFlowStateByProcInst("2",xmTaskSbill, flowVars);
				}else {
					this.updateFlowStateByProcInst("",xmTaskSbill, flowVars);
				}
			}else {
				//新增结算单审批流程
				if("PROCESS_STARTED".equals(eventName)) {
					this.updateFlowStateByProcInst("1", xmTaskSbill,flowVars);
				}else if("PROCESS_COMPLETED".equals(eventName)) {
					if(!"0".equals(agree)) {
						this.updateFlowStateByProcInst("2",xmTaskSbill, flowVars);
						this.pushWorkloadToMk(xmTaskSbill);
					}else {
						this.updateFlowStateByProcInst("3",xmTaskSbill,  flowVars);
					}
				}else if("PROCESS_CANCELLED".equals(eventName)) {
					this.updateFlowStateByProcInst("4", xmTaskSbill,flowVars);
				}
			}
		}else {
			throw new BizException("不支持的业务,请上送业务编码【bizKey】参数");
		}

	}

	private void pushWorkloadToMk(XmTaskSbill xmTaskSbill) {
		XmTaskSbillDetail detailQ=new XmTaskSbillDetail();
		detailQ.setSbillId(xmTaskSbill.getId());
		List<XmTaskSbillDetail> detailsDb=this.xmTaskSbillDetailService.selectListByWhere(detailQ);
		if(detailsDb!=null && detailsDb.size()>0){
			return;
		}
		for (XmTaskSbillDetail t : detailsDb) {
			mkClient.pushSbillDetail(t);
		}
	}

	/**
	 * update流程表信息
	 * 工时表：结算状态0-无需结算，1-待结算2-已提交3-已通过4-已结算
	 * 此处工时表只有两种状态：2-已提交3-已通过，提交结算时候初始为2
	 */
	public void updateFlowStateByProcInst(String bizFlowState,XmTaskSbill xmTaskSbill,Map<String, Object> flowVars) {
		xmTaskSbill.setBizFlowState(bizFlowState);
		String bizProcInstId = (String) flowVars.get("procInstId");
		xmTaskSbill.setBizProcInstId(bizProcInstId);
		Date toDay = new Date();
		xmTaskSbill.setLtime(toDay);
		xmTaskSbill.setBizMonth(DateUtils.format(toDay,"yyyy-MM"));
		xmTaskSbill.setBizDate(DateUtils.format(toDay,"yyyy-MM-dd"));
		if("1".equals(bizFlowState)){
			//发起审核，更新sbill表状态，工时登记表状态无需更新
			xmTaskSbill.setStatus("1");
			xmWorkloadService.updateStatusBySbillIdByFlowState(xmTaskSbill.getId(),"2");
		}else if("2".equals(bizFlowState)){
			//审核通过，工时登记表更新为已通过-3
			xmTaskSbill.setStatus("2");//结算单状态-已通过
			xmWorkloadService.updateStatusBySbillIdByFlowState(xmTaskSbill.getId(),"4");
		}else if("3".equals(bizFlowState)){
			//3为审批不通过，退回发起人，可继续向上提交。工时表无需修改
			xmTaskSbill.setStatus("1");//结算单状态-已提交
		}else if("4".equals(bizFlowState)){
			//4为流程删除或者取消，sbill可重新发起审批/删除。工时表无需修改
			xmTaskSbill.setStatus("0");//结算单状态-待提交
			xmWorkloadService.updateStatusBySbillIdByFlowState(xmTaskSbill.getId(),"1");
		}

		this.updateSomeFieldByPk(xmTaskSbill);
	}

	public void updateBySbillDetailList(List<String> sbillIds) {
		baseMapper.updateBySbillDetailList(sbillIds);
	}

	@Transactional
    public void batchJoinToSbill(List<String> workloadIds,List<XmTaskSbillDetail> canAdd, List<XmTaskSbillDetail> details) {

		if(canAdd.size()>0){
			xmTaskSbillDetailService.batchInsert(canAdd);
		}
		//需要更新工时明细表detailId..
		if(details.size()>0){
			this.xmTaskSbillDetailService.batchUpdate(details);
		}
		List<XmTaskSbillDetail> detailsAll=new ArrayList<>();
		detailsAll.addAll(canAdd);
		detailsAll.addAll(details);
		if(detailsAll.size()>0){
			this.xmWorkloadService.updateStatusAfterJoinSbill(map("ids",workloadIds,"sbillId",detailsAll.get(0).getSbillId(),"detailId",detailsAll.get(0).getId()));
			this.updateBySbillDetailList(detailsAll.stream().map(i->i.getSbillId()).collect(Collectors.toSet()).stream().collect(Collectors.toList()));
		}



    }
}

