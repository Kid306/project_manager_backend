package com.xm.core.vo;

import com.xm.core.entity.XmMenu;
import lombok.Data;

import java.util.List;

@Data
public class BatchAddXmMenusVo {
    List<XmMenu> xmMenus;
    // 需要导入的目标上级，顶级未0
    String pmenuId;
    // 需要导入的目标产品库
    String productId;
}
