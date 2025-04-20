package com.xm.core.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.err.BizException;
import com.mdp.core.service.BaseService;
import com.mdp.msg.client.PushNotifyMsgService;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.xm.core.entity.XmTask;
import com.xm.core.entity.XmTaskExecuser;
import com.xm.core.mapper.XmTaskExecuserMapper;
import com.xm.core.service.client.MkClient;
import com.xm.core.service.push.XmPushMsgService;
import com.xm.core.vo.XmGroupVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br>
 * 组织 com.qqkj  顶级模块 oa 大模块 xm 小模块 <br>
 * 实体 XmTaskExecuser 表 XM.xm_task_execuser 当前主键(包括多主键): id;
 ***/
@Service("xm.core.xmTaskExecuserService")
public class XmTaskExecuserService extends BaseService<XmTaskExecuserMapper,XmTaskExecuser> {

	/** 请在此类添加自定义函数 */

	@Autowired
	XmRecordService xmRecordService;

	@Autowired
	XmTaskService xmTaskService;

	@Autowired
	XmGroupService groupService;


	@Autowired
	XmPushMsgService pushMsgService ;


	@Autowired
	PushNotifyMsgService notifyMsgService;


	@Autowired
	MkClient mkClient;


	/**
	 *
	 * @param xmTaskExecuser
	 * @param sendMsg 草稿不提醒
	 */
	public void addExecuser(XmTaskExecuser xmTaskExecuser,boolean sendMsg){
		User user = LoginUtils.getCurrentUserInfo();
		XmTaskExecuser xmTaskExecuserQuery=new XmTaskExecuser();
		xmTaskExecuserQuery.setTaskId(xmTaskExecuser.getTaskId());
		xmTaskExecuserQuery.setBidUserid(xmTaskExecuser.getBidUserid());
		List<XmTaskExecuser> userDb=this.selectListByWhere(xmTaskExecuserQuery);
		if(userDb.size()>0) {
			throw new BizException(xmTaskExecuser.getBidUsername()+"已在任务中，不允许再添加");
		}
		xmTaskExecuser.setCreateUserid(user.getUserid());
		xmTaskExecuser.setCreateUsername(user.getUsername());
		xmTaskExecuser.setCreateTime(new Date());
		xmTaskExecuser.setStartTime(new Date());
		if(!StringUtils.hasText(xmTaskExecuser.getStatus())){
			xmTaskExecuser.setStatus("0");
		}
		this.insert(xmTaskExecuser);

		String imMsg="";
		String notifyMsg="";
		updateXmTaskExeUseridsAndUsernamesByTaskId(xmTaskExecuser.getTaskId());
		if(sendMsg){//草稿任务不要提醒
			if("0".equals(xmTaskExecuser.getStatus())){
				imMsg=xmTaskExecuser.getBidUsername()+"成为任务【"+xmTaskExecuser.getTaskId()+"-"+xmTaskExecuser.getTaskName()+"】的候选人，待雇主选标。";
				notifyMsg="您成为任务【"+xmTaskExecuser.getTaskId()+"-"+xmTaskExecuser.getTaskName()+"】的候选人，请等待雇主选标，在雇主选标前，您还可以修改报价，合理的报价更容易获得雇主的喜欢哦！";
			}else {
				imMsg=xmTaskExecuser.getBidUsername()+"成为任务【"+xmTaskExecuser.getTaskId()+"-"+xmTaskExecuser.getTaskName()+"】的执行人，请及时跟进任务！";
				notifyMsg="您成为任务【"+xmTaskExecuser.getTaskId()+"-"+xmTaskExecuser.getTaskName()+"】的执行人，请及时跟进任务！";
			}
			this.pushMsgService.pushPrichatMsgToIm(user.getBranchId(),user.getUserid(),user.getUsername(), xmTaskExecuser.getBidUserid(), xmTaskExecuser.getBidUsername(),imMsg);
			this.pushMsgService.pushCreateCssGroupMsg(user.getBranchId(), xmTaskExecuser.getBidUserid(), xmTaskExecuser.getBidUsername(), imMsg);
			notifyMsgService.pushMsg(user.getBranchId(),user.getUserid(),user.getUsername(), xmTaskExecuser.getBidUserid(), xmTaskExecuser.getBidUsername(), notifyMsg,null);

		}
		xmRecordService.addXmTaskRecord(xmTaskExecuser.getProjectId(), xmTaskExecuser.getTaskId(), "任务-增加候选人", "任务增加候选人【"+xmTaskExecuser.getBidUsername()+"】");
	}
	/**
	 * 本人或者组长可以变更
	 * @param xmTaskExecuserList
	 */
	public void batchLeave(List<XmTaskExecuser> xmTaskExecuserList){
		String projectId=xmTaskExecuserList.get(0).getProjectId();
		String taskId="";
		List<String> usernames=new ArrayList<>();

		User user=LoginUtils.getCurrentUserInfo();
		List<XmGroupVo> pgroups=groupService.getProjectGroupVoList(projectId);
		for (XmTaskExecuser xmTaskExecuser : xmTaskExecuserList) {
			List<XmGroupVo> userGroups=groupService.getUserGroups(pgroups, xmTaskExecuser.getBidUserid());
			XmTaskExecuser xmTaskExecuser2=new XmTaskExecuser();
			xmTaskExecuser2.setTaskId(xmTaskExecuser.getTaskId());
			xmTaskExecuser2.setBidUserid(xmTaskExecuser.getBidUserid());
			xmTaskExecuser2.setStatus("7");
			this.updateSomeFieldByPk(xmTaskExecuser2);
			projectId=xmTaskExecuser.getProjectId();
			taskId=xmTaskExecuser.getTaskId();
			usernames.add(xmTaskExecuser.getBidUsername());

			/**
			 * 下面为推送任务执行人变更im通知消息
			 */
			List<Map<String,Object>> users=new ArrayList<>();
			Map<String,Object> userMap=new HashMap<>();
			userMap.put("userid", xmTaskExecuser.getBidUserid());
			userMap.put("username", xmTaskExecuser.getBidUsername());
			users.add(userMap);
			String imMsg=xmTaskExecuser.getBidUsername()+"放弃任务【"+xmTaskExecuser.getTaskId()+"-"+xmTaskExecuser.getTaskName()+"】";
			notifyMsgService.pushMsg(user.getBranchId(),user.getUserid(),user.getUsername(),xmTaskExecuser.getBidUserid(),xmTaskExecuser.getBidUsername(),"您已离开任务【"+xmTaskExecuser.getTaskId()+"-"+xmTaskExecuser.getTaskName()+"】！",null);

			for (XmGroupVo g : userGroups) {
				this.pushMsgService.pushGroupMsg(user.getBranchId(), g.getId(), xmTaskExecuser.getBidUserid(), xmTaskExecuser.getBidUsername(),  imMsg);
				this.pushMsgService.pushLeaveChannelGroupMsg(user.getBranchId(), g.getId(), users);

			}
			this.pushMsgService.pushCssMsg(user.getBranchId(), xmTaskExecuser.getBidUserid(), xmTaskExecuser.getBidUsername(), imMsg);

		}
		updateXmTaskExeUseridsAndUsernamesByTaskId(taskId);
		String usernamestr=StringUtils.arrayToDelimitedString(usernames.toArray(), ",");
		xmRecordService.addXmTaskRecord(projectId, taskId, "任务-执行人离开", "【"+usernamestr+"】离开任务");



	}

	/**
	 * 一个任务只能有一个执行人，如果要把候选人变成执行人，必须把其它执行人变更为候选人
	 * 本人或者组长可以变更
	 * @param xmTaskDb
	 * @param xmTaskExecuser
	 */
	public void becomeExecute(XmTask xmTaskDb,XmTaskExecuser xmTaskExecuser){
		String projectId=xmTaskDb.getProjectId();
		String taskId=xmTaskExecuser.getTaskId();
		List<XmGroupVo> pgroups=groupService.getProjectGroupVoList(projectId);
		User user=LoginUtils.getCurrentUserInfo();

		List<XmGroupVo> userGroups=groupService.getUserGroups(pgroups, xmTaskExecuser.getBidUserid());
		XmTaskExecuser query=new XmTaskExecuser();
		query.setTaskId(taskId);
		XmTaskExecuser xmTaskExecuserDb=null;
		List<XmTaskExecuser> xmTaskExecusersDb=this.selectListByWhere(query);
		if(xmTaskExecusersDb !=null && xmTaskExecusersDb.size()>0) {
			for (XmTaskExecuser exe : xmTaskExecusersDb) {
				if(!xmTaskExecuser.getBidUserid().equals(exe.getBidUserid())) {
					if(!"0".equals(exe.getStatus()) && !"7".equals(exe.getStatus())) {
						throw new BizException(exe.getBidUsername()+"是当前执行人，不允许再添加其它执行人");
					}
				}else {
					if(!"0".equals(exe.getStatus()) && !"7".equals(exe.getStatus())) {
						throw new BizException(exe.getBidUsername()+"不是候选人，不允许变更为执行人");
					}
					xmTaskExecuserDb=exe;
				}
			}
		}

		if(xmTaskExecuserDb==null){
			throw new BizException(xmTaskExecuser.getBidUsername()+"不是候选人，不允许变更为执行人");
		}
		if( "1".equals(xmTaskDb.getCrowd()) && "1".equals(xmTaskDb.getTaskOut()) ){
			if(xmTaskExecuserDb.getQuoteAmount()==null){
				throw new BizException(xmTaskExecuserDb.getBidUsername()+"没有填写报价金额，不允许变更为执行人。");
			}
		}
		XmTaskExecuser xmTaskExecuser2=new XmTaskExecuser();
		xmTaskExecuser2.setTaskId(xmTaskExecuser.getTaskId());
		xmTaskExecuser2.setBidUserid(xmTaskExecuser.getBidUserid());
		xmTaskExecuser2.setStatus("1");
		this.updateSomeFieldByPk(xmTaskExecuser2);
		/**
		 * 下面为推送任务执行人变更im通知消息
		 */
		String imMsg=xmTaskExecuser.getBidUsername()+"变更为任务["+xmTaskDb.getId()+"-"+xmTaskDb.getName()+"]执行人";
		for (XmGroupVo g : userGroups) {
			this.pushMsgService.pushGroupMsg(user.getBranchId(),g.getId(),  xmTaskExecuser.getBidUserid(), xmTaskExecuser.getBidUsername(),imMsg );
			this.pushMsgService.pushPrichatMsgToIm(user.getBranchId(), user.getUserid(),user.getUsername(),xmTaskExecuser.getBidUserid(), xmTaskExecuser.getBidUsername(),imMsg);
		}
		this.pushMsgService.pushCssMsg(user.getBranchId(), xmTaskExecuser.getBidUserid(), xmTaskExecuser.getBidUsername(), imMsg);

		notifyMsgService.pushMsg(user.getBranchId(),user.getUserid(),user.getUsername(), xmTaskExecuser.getBidUserid(), xmTaskExecuser.getBidUsername(), "恭喜您被雇主选为任务【" + xmTaskExecuser.getTaskId() + "-" + xmTaskDb.getName() + "】的中标人,请尽快开展工作。",null);

		updateXmTaskExeUseridsAndUsernamesByTaskId(taskId);
		if("2".equals(xmTaskDb.getOshare()) && xmTaskDb.getShareFee()!=null && xmTaskDb.getShareFee().compareTo(BigDecimal.ZERO)>0){
			mkClient.pushAfterTaskExecSuccess(xmTaskExecuserDb.getBidUserid(),xmTaskExecuserDb.getBidUsername(),xmTaskDb.getProjectId(),xmTaskDb.getId(),xmTaskDb.getShareFee());
		}

		xmRecordService.addXmTaskRecord(projectId, taskId,"任务-变更为执行人", "【"+xmTaskExecuser.getBidUsername()+"】变更为任务执行人");
	}





	public void becomeCandidate(XmTaskExecuser xmTaskExecuser) {
		XmTaskExecuser xmTaskExecuserNew=new XmTaskExecuser();
		xmTaskExecuserNew.setTaskId(xmTaskExecuser.getTaskId());
		xmTaskExecuserNew.setBidUserid(xmTaskExecuser.getBidUserid());
		xmTaskExecuserNew.setQuoteWeekday(xmTaskExecuser.getQuoteWeekday());
		xmTaskExecuserNew.setQuoteWorkload(xmTaskExecuser.getQuoteWorkload());
		xmTaskExecuserNew.setQuoteAmount(xmTaskExecuser.getQuoteAmount());
		xmTaskExecuserNew.setQuoteEndTime(xmTaskExecuser.getQuoteEndTime());
		xmTaskExecuserNew.setQuoteStartTime(xmTaskExecuser.getQuoteStartTime());
		xmTaskExecuserNew.setSkillRemark(xmTaskExecuser.getSkillRemark());
		xmTaskExecuserNew.setStatus("0");
		this.updateSomeFieldByPk(xmTaskExecuserNew);
		updateXmTaskExeUseridsAndUsernamesByTaskId(xmTaskExecuser.getTaskId());
		this.pushMsgService.pushCssMsg(xmTaskExecuser.getBranchId(), xmTaskExecuser.getBidUserid(), xmTaskExecuser.getBidUsername(),  xmTaskExecuser.getBidUsername()+"变更为候选人并提交关于任务【"+xmTaskExecuser.getTaskId()+"-"+xmTaskExecuser.getTaskName()+"】报价信息");
		User user=LoginUtils.getCurrentUserInfo();
		notifyMsgService.pushMsg(user.getBranchId(),user.getUserid(),user.getUsername(), xmTaskExecuser.getBidUserid(), xmTaskExecuser.getBidUsername(),  "您成为任务【" + xmTaskExecuser.getTaskId() + "-" + xmTaskExecuser.getTaskName() + "】的候选人，请等待雇主选标，在雇主选标前，您还可以修改报价，合理的报价更容易获得雇主的喜欢哦！",null);

		xmRecordService.addXmTaskRecord(xmTaskExecuser.getProjectId(), xmTaskExecuser.getTaskId(), "任务-候选人报价", "【"+xmTaskExecuser.getBidUsername()+"】变更为候选人并提交报价信息");
	}
	public void quotePrice(XmTaskExecuser xmTaskExecuser) {
		XmTaskExecuser xmTaskExecuserNew=new XmTaskExecuser();
		xmTaskExecuserNew.setTaskId(xmTaskExecuser.getTaskId());
		xmTaskExecuserNew.setBidUserid(xmTaskExecuser.getBidUserid());
		xmTaskExecuserNew.setQuoteWeekday(xmTaskExecuser.getQuoteWeekday());
		xmTaskExecuserNew.setQuoteWorkload(xmTaskExecuser.getQuoteWorkload());
		xmTaskExecuserNew.setQuoteAmount(xmTaskExecuser.getQuoteAmount());
		xmTaskExecuserNew.setQuoteEndTime(xmTaskExecuser.getQuoteEndTime());
		xmTaskExecuserNew.setQuoteStartTime(xmTaskExecuser.getQuoteStartTime());
		xmTaskExecuserNew.setSkillRemark(xmTaskExecuser.getSkillRemark());
		this.updateSomeFieldByPk(xmTaskExecuserNew);
		this.pushMsgService.pushCssMsg(xmTaskExecuser.getBranchId(), xmTaskExecuser.getBidUserid(), xmTaskExecuser.getBidUsername(),  xmTaskExecuser.getBidUsername()+"提交关于任务【"+xmTaskExecuser.getTaskId()+"-"+xmTaskExecuser.getTaskName()+"】报价信息");
		User user=LoginUtils.getCurrentUserInfo();
		notifyMsgService.pushMsg(user.getBranchId(),user.getUserid(),user.getUsername(), xmTaskExecuser.getBidUserid(), xmTaskExecuser.getBidUsername(), user.getUsername()+"成功修改了任务【" + xmTaskExecuser.getTaskId() + "-" + xmTaskExecuser.getTaskName() + "】的报价信息，请等待雇主选标，在雇主选标前，您还可以修改报价，合理的报价更容易获得雇主的喜欢哦！",null);

		xmRecordService.addXmTaskRecord(xmTaskExecuser.getProjectId(), xmTaskExecuser.getTaskId(), "任务-候选人报价", "【"+xmTaskExecuser.getBidUsername()+"】提交报价信息");
	}


	public void delete(XmTaskExecuser xmTaskExecuser){
		super.deleteByPk(xmTaskExecuser);
		this.updateXmTaskExeUseridsAndUsernamesByTaskId(xmTaskExecuser.getTaskId());
	}
	/**
	 * 将执行人编号，姓名同步到task中的exe_userids,exe_usernames两个字段
	 * @param taskId
	 */
	public void updateXmTaskExeUseridsAndUsernamesByTaskId(String taskId) {
		baseMapper.updateXmTaskExeUseridsAndUsernamesByTaskId(taskId);
	}

	public List<Map<String,Object>> selectListMapByWhereWithTask(IPage page, QueryWrapper<XmTaskExecuser> qw, Map<String, Object> ext){
		return baseMapper.selectListMapByWhereWithTask(page,qw,ext);
	}

	public List<Map<String, Object>> selectListMapByWhere(IPage page, QueryWrapper<XmTaskExecuser> qw, Map<String, Object> params) {
		return baseMapper.selectListMapByWhere(page,qw,params);
	}

	@Transactional
	public void removeBatch(List<XmTaskExecuser> xmTaskExecusers,String taskId) {
		this.removeByIds(xmTaskExecusers);
		this.updateXmTaskExeUseridsAndUsernamesByTaskId(taskId);
	}
}

