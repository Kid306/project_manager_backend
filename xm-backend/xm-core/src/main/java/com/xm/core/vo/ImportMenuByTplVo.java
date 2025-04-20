package com.xm.core.vo;

import com.xm.core.entity.XmMenu;
import lombok.Data;

import java.util.List;

@Data
public class ImportMenuByTplVo {
    List<XmMenu> xmMenus;// 待导入的需求列表 sourceType=some时必填
    String sourceType;// all-所有需求,some-选中的需求
    String sourceProductId;//源产品，sourceType=all时必填
    String pmenuId; //导入到哪个目标需求下，为空或者为0则代表为0
    String productId;//导入到哪个产品下
    int days; //日期顺延天数，所有需求日期按原有日期顺延天数。0代表不顺延

}
