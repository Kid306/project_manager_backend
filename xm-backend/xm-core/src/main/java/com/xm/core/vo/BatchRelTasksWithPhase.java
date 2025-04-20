package com.xm.core.vo;

import java.util.List;

public class BatchRelTasksWithPhase {

    String phaseId;

    List<String> taskIds;

    public String getPhaseId() {
        return phaseId;
    }

    public void setPhaseId(String phaseId) {
        this.phaseId = phaseId;
    }

    public List<String> getTaskIds() {
        return taskIds;
    }

    public void setTaskIds(List<String> taskIds) {
        this.taskIds = taskIds;
    }
}
