package com.mdp.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mdp.core.dao.annotation.TableIds;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 组织 com  顶级模块 mdp 大模块 sys  小模块 post<br> 
 * 实体 DeptPost所有属性名: <br>
 *	"deptid","部门编号","postId","岗位编号","ldate","最后更新时间";<br>
 * 当前主键(包括多主键):<br>
 *	deptid,post_id;<br>
 */
 @Data
@TableName("sys_dept_post")
@ApiModel(description="部门岗位关系表")
public class DeptPost  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
    @TableIds
    @ApiModelProperty(notes="部门编号,主键",allowEmptyValue=true,example="",allowableValues="")
    String deptid;
    @TableIds
    @ApiModelProperty(notes="岗位编号,主键",allowEmptyValue=true,example="",allowableValues="")
    String postId;
	
	@ApiModelProperty(notes="最后更新时间",allowEmptyValue=true,example="",allowableValues="")
	Date ldate;

	/**
	 *部门编号,岗位编号
	 **/
	public DeptPost(String deptid,String postId) {
		this.deptid = deptid;
		this.postId = postId;
	}
    
    /**
     * 部门岗位关系表
     **/
	public DeptPost() {
	}

}