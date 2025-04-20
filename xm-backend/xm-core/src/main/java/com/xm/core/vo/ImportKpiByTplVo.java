package com.xm.core.vo;

import com.xm.core.entity.XmKpi;
import com.xm.core.entity.XmTask;
import lombok.Data;

import java.util.List;

@Data
public class ImportKpiByTplVo {
    List<XmKpi> xmKpis;// 待导入的指标列表 sourceType=some时必填
    String sourceType;// projectAll-项目所有指标,some-选中的指标
    String sourceProjectId;//源项目组，sourceType=projectAll时必填
    String parentId; //导入到哪个指标下，为空或者为0则代表为0
    String projectId;//导入到哪个项目下

}
