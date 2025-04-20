package com.xm.core.vo;

import com.xm.core.entity.XmTask;
import lombok.Data;

import java.util.List;
@Data
public class ImportTaskByTplVo {
    List<XmTask> xmTasks;// 待导入的任务列表 sourceType=some时必填
    String sourceType;// all-所有任务,some-选中的任务
    String sourceProjectId;//源项目组，sourceType=all时必填
    String parentTaskid; //导入到哪个目标计划下，为空或者为0则代表为0
    String projectId;//导入到哪个项目下
    int days; //日期顺延天数，所有任务日期按原有日期顺延天数。0代表不顺延

}
