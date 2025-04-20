package com.mdp.micro;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * BranchService的测试案例
 * 组织 com.qqkj<br>
 * 顶级模块 mdp<br>
 * 大模块 sys<br>
 * 小模块 <br>
 * 表 ADMIN.sys_branch 管理端机构表（机构下面若干部门）<br>
 * 实体 Branch<br>
 * 表是指数据库结构中的表,实体是指java类型中的实体类<br>
 * 当前实体所有属性名:<br>
 *	id,branchName,enabled,industryCategory,cuserid,cdate,cusername,phoneNo,emaill;<br>
 * 当前表的所有字段名:<br>
 *	id,branch_name,enabled,industry_category,cuserid,cdate,cusername,phone_no,emaill;<br>
 * 当前主键(包括多主键):<br>
 *	id;<br>
 ***/

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestCallBizService {
	@Autowired
	SysClientService clientService;

	@Test
	public void getClientService() {
		clientService.getSysUserTpaByOpenid("xxx");
	}
}

