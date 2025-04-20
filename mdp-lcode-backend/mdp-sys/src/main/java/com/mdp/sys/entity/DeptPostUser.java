package com.mdp.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 组织 com  顶级模块 mdp 大模块 sys  小模块 post<br> 
 * 实体 DeptPostUser所有属性名: <br>
 *	"postId","岗位编号","deptid","部门编号","userid","用户编号","startDate","开始任职时间","endDate","结束任职时间","actEndDate","实际结束任职时间","enabled","状态0-无效1-有效","lastDate","最后更新时间","master","是否为主岗位0否1是","id","主键";<br>
 * 当前主键(包括多主键):<br>
 *	id;<br>
 */
 @Data
@TableName("sys_dept_post_user")
@ApiModel(description="部门岗位用户关系表（根据部门岗位关系,岗位用户关系自动冗余）")
public class DeptPostUser  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(notes="主键,主键",allowEmptyValue=true,example="",allowableValues="")
	String id;
	
	@ApiModelProperty(notes="岗位编号",allowEmptyValue=true,example="",allowableValues="")
	String postId;
	
	@ApiModelProperty(notes="部门编号",allowEmptyValue=true,example="",allowableValues="")
	String deptid;
	
	@ApiModelProperty(notes="用户编号",allowEmptyValue=true,example="",allowableValues="")
	String userid;
	
	@ApiModelProperty(notes="开始任职时间",allowEmptyValue=true,example="",allowableValues="")
	Date startDate;
	
	@ApiModelProperty(notes="结束任职时间",allowEmptyValue=true,example="",allowableValues="")
	Date endDate;
	
	@ApiModelProperty(notes="实际结束任职时间",allowEmptyValue=true,example="",allowableValues="")
	Date actEndDate;
	
	@ApiModelProperty(notes="状态0-无效1-有效",allowEmptyValue=true,example="",allowableValues="")
	String enabled;
	
	@ApiModelProperty(notes="最后更新时间",allowEmptyValue=true,example="",allowableValues="")
	Date lastDate;
	
	@ApiModelProperty(notes="是否为主岗位0否1是",allowEmptyValue=true,example="",allowableValues="")
	String master;

	/**
	 *主键
	 **/
	public DeptPostUser(String id) {
		this.id = id;
	}
    
    /**
     * 部门岗位用户关系表（根据部门岗位关系,岗位用户关系自动冗余）
     **/
	public DeptPostUser() {
	}

}