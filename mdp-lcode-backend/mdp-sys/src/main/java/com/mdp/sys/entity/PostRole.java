package com.mdp.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mdp.core.dao.annotation.TableIds;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 组织 com  顶级模块 mdp 大模块 sys  小模块 post<br> 
 * 实体 PostRole所有属性名: <br>
 *	"postId","岗位编号","roleid","角色编号";<br>
 * 当前主键(包括多主键):<br>
 *	post_id,roleid;<br>
 */
 @Data
@TableName("sys_post_role")
@ApiModel(description="岗位角色关系表")
public class PostRole  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
    @TableIds
    @ApiModelProperty(notes="岗位编号,主键",allowEmptyValue=true,example="",allowableValues="")
    String postId;
    @TableIds
    @ApiModelProperty(notes="角色编号,主键",allowEmptyValue=true,example="",allowableValues="")
    String roleid;

	/**
	 *岗位编号,角色编号
	 **/
	public PostRole(String postId,String roleid) {
		this.postId = postId;
		this.roleid = roleid;
	}
    
    /**
     * 岗位角色关系表
     **/
	public PostRole() {
	}

}