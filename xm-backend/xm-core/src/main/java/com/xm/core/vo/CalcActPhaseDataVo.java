package com.xm.core.vo;

import java.util.List;

public class CalcActPhaseDataVo {

	String projectId;
	List<String> phaseIds;
	
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public List<String> getPhaseIds() {
		return phaseIds;
	}
	public void setPhaseIds(List<String> phaseIds) {
		this.phaseIds = phaseIds;
	}
}
