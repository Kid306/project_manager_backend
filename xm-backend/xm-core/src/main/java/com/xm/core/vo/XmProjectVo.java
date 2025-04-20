package com.xm.core.vo;

import com.xm.core.entity.XmProductProjectLink;
import com.xm.core.entity.XmProject;

import java.util.List;

public class XmProjectVo extends XmProject {

	List<XmProductProjectLink> links;

	public List<XmProductProjectLink> getLinks() {
		return links;
	}

	public void setLinks(List<XmProductProjectLink> links) {
		this.links = links;
	}
}
