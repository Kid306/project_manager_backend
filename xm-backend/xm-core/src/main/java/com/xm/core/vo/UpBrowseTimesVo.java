package com.xm.core.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description="任务浏览量登记")
public class UpBrowseTimesVo {

	@ApiModelProperty(notes="任务编号",allowEmptyValue=true,example="",allowableValues="")
	String taskId;

	@ApiModelProperty(notes="浏览次数",allowEmptyValue=true,example="",allowableValues="")
	int nums;

	 
	 
}