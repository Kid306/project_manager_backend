package com.xm.core.ctrl.push;


import io.swagger.annotations.Api;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * url编制采用rest风格,如对XM.xm_branch_state 机构内所有项目指标汇总的操作有增删改查,对应的url分别为:<br>
 *  新增: core/xmBranchState/add <br>
 *  查询: core/xmBranchState/list<br>
 *  模糊查询: core/xmBranchState/listKey<br>
 *  修改: core/xmBranchState/edit <br>
 *  删除: core/xmBranchState/del<br>
 *  批量删除: core/xmBranchState/batchDel<br>
 * 组织 com.qqkj  顶级模块 xm 大模块 core 小模块 <br>
 * 实体 XmBranchState 表 XM.xm_branch_state 当前主键(包括多主键): id; 
 ***/
@RestController("xm.core.push.xmPushController")
@RequestMapping(value="/xm/core/push")
@Api(tags={"消息推送接口"})
public class XmPushController {
	
	static Log logger=LogFactory.getLog(XmPushController.class); 
	 
	
		 
}
