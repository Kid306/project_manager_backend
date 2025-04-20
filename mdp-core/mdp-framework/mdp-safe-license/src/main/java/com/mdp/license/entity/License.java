package com.mdp.license.entity;

public class License {
	
	/*开始时间*/
	String startDate;
	
	/*结束时间*/
	String endDate;
	
	/*最大注册用户数*/
	Integer maxUser;
	
	/*同时在线人数*/
	Integer maxSession;
	
	
	/*服务器网卡物理地址*/
	String mac;
	
	/*秘钥*/
	String key;

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getMaxUser() {
		return maxUser;
	}

	public void setMaxUser(Integer maxUser) {
		this.maxUser = maxUser;
	}

	public Integer getMaxSession() {
		return maxSession;
	}

	public void setMaxSession(Integer maxSession) {
		this.maxSession = maxSession;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
