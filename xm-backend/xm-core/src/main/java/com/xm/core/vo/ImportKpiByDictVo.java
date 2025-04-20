package com.xm.core.vo;

import com.xm.core.entity.XmKpi;
import com.xm.core.entity.XmTask;
import lombok.Data;

import java.util.List;

@Data
public class ImportKpiByDictVo {
    List<XmKpi> xmKpis;// 待导入的任务列表 sourceType=some时必填
    String parentId; //导入到哪个目标计划下，为空或者为0则代表为0
    String projectId;//导入到哪个项目下

}
