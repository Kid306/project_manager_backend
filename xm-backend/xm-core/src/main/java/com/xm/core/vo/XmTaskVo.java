package com.xm.core.vo;

import com.xm.core.entity.XmTask;
import com.xm.core.entity.XmTaskSkill;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
@Data
@ApiModel(description="xm_task")
public class XmTaskVo extends XmTask {

	@ApiModelProperty(notes="任务的技能标签要求列表",allowEmptyValue=true,example="",allowableValues="")
	List<XmTaskSkill> skills;
	
	/**
	 * addSub,edit,delete
	 */
	@ApiModelProperty(notes="操作类型 addSub,edit,delete",allowEmptyValue=true,example="",allowableValues="")
	String opType="";

	 
	 
}