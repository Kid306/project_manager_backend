package com.mdp.core.entity;

public class No implements Cloneable {
	
	private String id;
	
	private String noid;

	private String noName;

	private int noLength;
	/**
	 * 复位设置： 1: 不复位 2: 按年复位 3: 按月复位 4: 按日复位
	 */
	private String noType;

	private String prefix;

	private String postfix;
	
	private Integer year;
	
	private Integer month;
	
	private Integer day;
	
	long nextid;

	public No() {
	}

	public String getNoid() {
		return noid;
	}
 

	public long getNextid() {
		return nextid;
	}

	public void setNextid(long nextid) {
		this.nextid = nextid;
	}

	public String getNoName() {
		return noName;
	}

	public void setNoName(String noName) {
		this.noName = noName;
	}

	public int getNoLength() {
		return noLength;
	}

	public void setNoLength(int noLength) {
		this.noLength = noLength;
	}
	/**
	 * 复位设置： 1: 不复位 2: 按年复位 3: 按月复位 4: 按日复位
	 */
	public String getNoType() {
		return noType;
	}
	/**
	 * 复位设置： 1: 不复位 2: 按年复位 3: 按月复位 4: 按日复位
	 */
	public void setNoType(String noType) {
		this.noType = noType;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getPostfix() {
		return postfix;
	}

	public void setPostfix(String postfix) {
		this.postfix = postfix;
	}

	public void setNoid(String noid) {
		this.noid = noid;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
