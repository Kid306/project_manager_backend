package com.mdp.sys.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 组织 com  顶级模块 mdp 大模块 sys  小模块 <br> 
 * 实体 UserFans所有属性名: <br>
 *	"userid","我的账户","fuserid","我关注的用户","ftime","关注时间";<br>
 * 当前主键(包括多主键):<br>
 *	userid,fuserid;<br>
 */
 @Data
@ApiModel(description="fansCpdVo")
public class FansCpdVo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(notes="我的粉丝数目",allowEmptyValue=true,example="",allowableValues="")
	int myFansCnt;

	@ApiModelProperty(notes="我关注的用户数",allowEmptyValue=true,example="",allowableValues="")
	int myFocusCnt;



}