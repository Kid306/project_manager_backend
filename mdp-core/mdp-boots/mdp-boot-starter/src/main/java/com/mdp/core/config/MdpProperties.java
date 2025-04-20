package com.mdp.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Properties;

@ConfigurationProperties(prefix = "mdp")
public class MdpProperties{
	  
	/**
	 * api网关 默认为http://gate
	 */
	public String apiGate;
	/*
	 * 顶级部门编号 mdp.top-deptid=A0
	 */
	public String topDeptid;

	/**
	 * 平台机构号 mdp.platform-branch-id = platform-branch-001
	 */
	public String platformBranchId="platform-branch-001";
	

	
	Properties settings=new Properties();
	
	public Properties getSettings() {
		return settings;
	}
	  

	public void setSettings(Properties settings) {
		this.settings = settings;
	}
 
	
	public String getProperty(String propertyName){
		return this.settings.getProperty(propertyName);
	}


	public String getApiGate() {
		return apiGate;
	}


	public void setApiGate(String apiGate) {
		this.apiGate = apiGate;
	}


	public String getTopDeptid() {
		return topDeptid;
	}


	public void setTopDeptid(String topDeptid) {
		this.topDeptid = topDeptid;
	}



	

 
}