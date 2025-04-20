package com.mdp.dev;


import com.mdp.dev.utils.SafePasswordEncoder;

/**
 *  @author cyc 20150820
 *  
 ****/
public class PasswordService {

	SafePasswordEncoder safePasswordEncoder=new SafePasswordEncoder();
	public PasswordService(){

	}

	/**
	 * 重置超级管理员密码
	 * @return
	 */
	public String createPassword(String rawPassword){
		return safePasswordEncoder.encode(rawPassword);
	}

	public static void main(String[] args) {
		PasswordService passwordService=new PasswordService();
		String rawPassword="21218cca77804d2ba1922c33e0151105";
		String password=passwordService.createPassword(rawPassword);
		System.out.printf(" 密码：%s ", password);
		boolean isMatch=passwordService.safePasswordEncoder.matches(rawPassword,password);
		System.out.printf(" 密码是否匹配： %s ", isMatch);

	}
}
