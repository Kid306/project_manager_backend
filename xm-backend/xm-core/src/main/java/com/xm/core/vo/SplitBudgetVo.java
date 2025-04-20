package com.xm.core.vo;

import com.xm.core.entity.XmBudgetRecord;
import lombok.Data;

import java.util.List;

@Data
public class SplitBudgetVo {
    String parentId;
    List<XmBudgetRecord> splits;
}
