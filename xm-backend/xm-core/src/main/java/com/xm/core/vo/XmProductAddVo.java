package com.xm.core.vo;

import com.xm.core.entity.XmProduct;
import com.xm.core.entity.XmProductProjectLink;

import java.util.List;

public class XmProductAddVo extends XmProduct {

    List<XmProductProjectLink> links;

    public List<XmProductProjectLink> getLinks() {
        return links;
    }

    public void setLinks(List<XmProductProjectLink> links) {
        this.links = links;
    }
}
