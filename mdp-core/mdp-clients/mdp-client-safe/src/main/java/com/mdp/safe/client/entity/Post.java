package com.mdp.safe.client.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description="岗位管理")
public class Post  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	@Schema(description="主键,主键")
	String id;
	
	@Schema(description="岗位名称")
	String postName;
	
	@Schema(description="备注")
	String remark;
	
	@Schema(description="归属机构号")
	String branchId;
	
	@Schema(description="创建日期")
	Date cdate;
	
	@Schema(description="岗位级别引用字典")
	Integer postLvl;
	
	@Schema(description="岗位类型引用字典")
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