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
 * 实体 Post所有属性名: <br>
 *	"id","主键","postName","岗位名称","remark","备注","branchId","归属机构号","cdate","创建日期","postLvl","岗位级别引用字典","postType","岗位类型引用字典";<br>
 * 当前主键(包括多主键):<br>
 *	id;<br>
 */
 @Data
@TableName("sys_post")
@ApiModel(description="岗位管理")
public class Post  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(notes="主键,主键",allowEmptyValue=true,example="",allowableValues="")
	String id;
	
	@ApiModelProperty(notes="岗位名称",allowEmptyValue=true,example="",allowableValues="")
	String postName;
	
	@ApiModelProperty(notes="备注",allowEmptyValue=true,example="",allowableValues="")
	String remark;
	
	@ApiModelProperty(notes="归属机构号",allowEmptyValue=true,example="",allowableValues="")
	String branchId;
	
	@ApiModelProperty(notes="创建日期",allowEmptyValue=true,example="",allowableValues="")
	Date cdate;
	
	@ApiModelProperty(notes="岗位级别引用字典",allowEmptyValue=true,example="",allowableValues="")
	Integer postLvl;
	
	@ApiModelProperty(notes="岗位类型引用字典",allowEmptyValue=true,example="",allowableValues="")
	String postType;

	/**
	 *主键
	 **/
	public Post(String id) {
		this.id = id;
	}
    
    /**
     * 岗位管理
     **/
	public Post() {
	}

}