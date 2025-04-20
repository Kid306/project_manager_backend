package com.xm.core.entity;

import lombok.Data;

import java.util.List;

@Data
public class PhasePlanVO {
    String projectId;
    List<XmTask> tasks;

}
