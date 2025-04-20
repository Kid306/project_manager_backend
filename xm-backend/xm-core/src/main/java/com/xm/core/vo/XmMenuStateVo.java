package com.xm.core.vo;

import com.xm.core.entity.XmMenu;

import java.util.List;

public class XmMenuStateVo {
	String productId;
	String productName;
	List<XmMenu> xmMenus;
	
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public List<XmMenu> getXmMenus() {
		return xmMenus;
	}
	public void setXmMenus(List<XmMenu> xmMenus) {
		this.xmMenus = xmMenus;
	}
}
