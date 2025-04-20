package com.xm.core.vo;

import com.xm.core.entity.XmGroup;
import com.xm.core.entity.XmGroupUser;
import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel(description="xm_group")
public class XmGroupVo extends XmGroup {
	List<XmGroupUser> groupUsers;

	public List<XmGroupUser> getGroupUsers() { return groupUsers; }

	public void setGroupUsers(List<XmGroupUser> groupUsers) { this.groupUsers = groupUsers; }
}