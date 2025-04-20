package com.mdp.dev;

import com.mdp.dev.main.CodeBase;
import com.mdp.dev.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/** 
 *  @author cyc 20150820
 *  
 ****/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestUserService extends CodeBase {
	
	@Autowired
	UserService userService;
	 
	public TestUserService() {
		 
	} 
	/**
	 * 重置超级管理员密码
	 */
	@Test
	public void initSuperAdminPassword(){
		//userService.initSuperAdminPassword();
	} 
}
