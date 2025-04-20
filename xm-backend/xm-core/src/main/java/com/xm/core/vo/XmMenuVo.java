package com.xm.core.vo;

import com.xm.core.entity.XmMenu;

public class XmMenuVo extends XmMenu {
	private static final long serialVersionUID = 1534268338972176747L;
	
	/**
	 * addSub,edit,delete
	 */
	String opType="";

	public String getOpType() {
		return opType;
	}

	public void setOpType(String opType) {
		this.opType = opType;
	}

}
