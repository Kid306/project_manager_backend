package com.mdp.sms.client;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
	/**
	 * 验证手机号码是否正确
	 * @param mobileNumber
	 * @return
	 */
	public static boolean checkMobileNumber(String mobileNumber) {  
	        boolean flag = false;  
	        try {  
	            Pattern regex = Pattern.compile("^1[0-9]{10}$");  
	            Matcher matcher = regex.matcher(mobileNumber);  
	            flag = matcher.matches();  
	        } catch (Exception e) {  
	            flag = false;  
	        }  
	        return flag;  
	}
}

