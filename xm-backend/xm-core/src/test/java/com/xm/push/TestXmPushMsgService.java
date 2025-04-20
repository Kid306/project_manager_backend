package com.xm.push;

import com.xm.core.service.push.XmPushMsgService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * XmProjectStateService的测试案例
 * 组织 com.qqkj<br>
 * 顶级模块 oa<br>
 * 大模块 xm<br>
 * 小模块 <br>
 * 表 XM.xm_project_state 项目指标日统计表<br>
 * 实体 XmProjectState<br>
 * 表是指数据库结构中的表,实体是指java类型中的实体类<br>
 * 当前实体所有属性名:<br>
 *	projectId,bizDate,totalFileCnt,totalBugCnt,totalTaskCnt,totalBudgetNouserAmount,projectName,id,totalStaffCnt,calCtime,calStatus,totalCostNouserAmount,totalClosedBugCnt,totalResolvedBugCnt,totalCompleteTaskCnt,totalPhaseCnt,totalCompletePhaseCnt,totalNeedPayAmount,totalFinishPayAmount,totalNeedColAmount,totalFinishColAmount,totalCostUserAmount,totalBudgetIuserAmount,totalPlanWorkload,totalRiskCnt,totalCompleteRiskCnt,branchId,branchName,totalBudgetOuserAmount,totalCompleteWorkload,todayNewBugCnt,todayResolvedBugCnt,todayClosedBugCnt,todayNewTaskCnt,todayCompleteTaskCnt,todayNewPhaseCnt,todayCompletePhaseCnt,todayNewStaffCnt,todaySubStaffCnt,todayNewPlanWorkload,todayNewActWorkload,todayNeedColAmount,todayFinishColAmount,todayCostUserAmount,todayCostIuserAmount,todayCostOuserAmount,todayCostNouserAmount,totalCostIuserAmount,totalCostOuserAmount,todayNeedPayAmount,todayFinishPayAmount,todayNewRiskCnt,todayCompleteRiskCnt,todayNewFileCnt,totalProgress,totalActiveBugCnt,totalConfirmedBugCnt,projectStatus,totalActWorkload,totalActOutWorkload,totalActInnerWorkload,totalTaskBudgetCostAt,totalTaskOutCnt,totalNeedPayCnt,totalFinishPayCnt,todayConfirmedBugCnt,todayActiveBugCnt,totalFinishPayUserCnt,totalNeedPayUserCnt,todayNeedPayUserCnt,todayFinishPayUserCnt;<br>
 * 当前表的所有字段名:<br>
 *	project_id,biz_date,total_file_cnt,total_bug_cnt,total_task_cnt,total_budget_nouser_amount,project_name,id,total_staff_cnt,cal_ctime,cal_status,total_cost_nouser_amount,total_closed_bug_cnt,total_resolved_bug_cnt,total_complete_task_cnt,total_phase_cnt,total_complete_phase_cnt,total_need_pay_amount,total_finish_pay_amount,total_need_col_amount,total_finish_col_amount,total_cost_user_amount,total_budget_iuser_amount,total_plan_workload,total_risk_cnt,total_complete_risk_cnt,branch_id,branch_name,total_budget_ouser_amount,total_complete_workload,today_new_bug_cnt,today_resolved_bug_cnt,today_closed_bug_cnt,today_new_task_cnt,today_complete_task_cnt,today_new_phase_cnt,today_complete_phase_cnt,today_new_staff_cnt,today_sub_staff_cnt,today_new_plan_workload,today_new_act_workload,today_need_col_amount,today_finish_col_amount,today_cost_user_amount,today_cost_iuser_amount,today_cost_ouser_amount,today_cost_nouser_amount,total_cost_iuser_amount,total_cost_ouser_amount,today_need_pay_amount,today_finish_pay_amount,today_new_risk_cnt,today_complete_risk_cnt,today_new_file_cnt,total_progress,total_active_bug_cnt,total_confirmed_bug_cnt,project_status,total_act_workload,total_act_out_workload,total_act_inner_workload,total_task_budget_cost_at,total_task_out_cnt,total_need_pay_cnt,total_finish_pay_cnt,today_confirmed_bug_cnt,today_active_bug_cnt,total_finish_pay_user_cnt,total_need_pay_user_cnt,today_need_pay_user_cnt,today_finish_pay_user_cnt;<br>
 * 当前主键(包括多主键):<br>
 *	id;<br>
 ***/

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestXmPushMsgService  {

	@Autowired
	XmPushMsgService pushService;  
	
	@Test
	public void pushChannelGroupCreateMsg( ) {
		String branchId="qqkj_001";
		String groupId="qqkj-test-001-003";
		String groupName="测试群组";
		String cuserid="4hinb8m16";
		String cusername="陈裕财"; 
		List<Map<String,Object>> users=new ArrayList<>();
		Map<String,Object> user1=new HashMap<>();
		user1.put("userid", "4hinb8m16"); 
		user1.put("username", "陈裕财");
		users.add(user1);
		Map<String,Object> user2=new HashMap<>();
		user2.put("userid", "4puma2k22"); 
		user2.put("username","谢家豪");
		users.add(user2);
		this.pushService.pushChannelGroupCreateMsg(branchId, groupId,groupId,groupId, groupName, cuserid, cusername, users,"创建群测试");
		this.pushService.pushGroupMsg(branchId, groupId,  "4puma2k22", "谢家豪", "家豪发消息");
	} 
	@Test
	public void pushGroupMsg() {
		
		String branchId="qqkj_001";
		String groupId="qqkj-test-001-003";
 		String sendUserid="4hinb8m16";
		String sendUsername="陈裕财";
		String msg="测试以下挺好";
		
		Map<String,Object> message=new HashMap<>();
 		message.put("groupId", groupId); 
		message.put("action", "newMessage"); 
		message.put("msgType", "group"); 
		message.put("branchId", branchId); 
		message.put("sendUserid", sendUserid);
 		message.put("sendUsername", sendUsername); 
 		message.put("sendContent", msg);  
 		message.put("store", "1"); 
		this.pushService.pushMsgToIm(message);

	}
	@Test
	public void pushLeaveChannelGroupMsg( ) {
		String branchId="qqkj_001";
		String groupId="qqkj-test-001-003"; 

		List<Map<String,Object>> users=new ArrayList<>();
		Map<String,Object> user1=new HashMap<>();
		user1.put("userid", "4hinb8m16"); 
		user1.put("username", "陈裕财");
		users.add(user1);
		
		
		Map<String,Object> map=new HashMap<>();
		map.put("groupId", groupId); 
		map.put("action", "leaveChannelGroup");   
		map.put("msgType", "group");  
		map.put("users",users); 
		map.put("branchId", branchId); 
		this.pushService.pushMsgToIm(map);
	}
	@Test
	public void pushJoinChannelGroupMsg( ) {
		String branchId="qqkj_001";
		String groupId="qqkj-test-001-003";  
		
		List<Map<String,Object>> users=new ArrayList<>();
		Map<String,Object> user1=new HashMap<>();
		user1.put("userid", "4hinb8m16"); 
		user1.put("username", "陈裕财");
		users.add(user1);
		
		Map<String,Object> map=new HashMap<>();
		map.put("groupId", groupId); 
		map.put("action", "joinChannelGroup");   
		map.put("msgType", "group");  
		map.put("groupName","测试群组");  
		map.put("users",users); 
		map.put("branchId", branchId); 
		this.pushService.pushMsgToIm(map);
	}
	@Test
	public void pushChannelGroupRemoveMsg( ) {
		String branchId="qqkj_001";
		String groupId="qqkj-test-001-003"; 
 		
		Map<String,Object> map=new HashMap<>();
		map.put("groupId", groupId); 
		map.put("action", "channelGroupRemove");  
		map.put("msgType", "group"); 
		map.put("branchId", branchId); 
		this.pushService.pushMsgToIm(map);
	} 
}
