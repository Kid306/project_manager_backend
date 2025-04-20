package  com.mdp.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mdp.core.dao.annotation.TableIds;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 组织 com  顶级模块 mdp 大模块 sys  小模块 <br> 
 * 实体 UserFocus所有属性名: <br>
 *	"userid","我的账户","bizId","关注对象的主键","pbizId","关注对象的父主键","username","名称","ftime","关注时间","ubranchId","用户归属机构号","pbizName","上级名称","bizName","关注对象的名称","focusType","关注对象类型对象类型:项目-1/任务-2/产品-3/需求-4/bug-5/迭代-6/团队-7/组织-8/个人-9/文章-W/合同-H/客户-K";<br>
 * 当前主键(包括多主键):<br>
 *	userid,biz_id,pbiz_id;<br>
 */
 @Data
@TableName("sys_user_focus")
@ApiModel(description="sys_user_focus")
public class UserFocus  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
    @TableIds
    @ApiModelProperty(notes="我的账户,主键",allowEmptyValue=true,example="",allowableValues="")
    String userid;
    @TableIds
    @ApiModelProperty(notes="关注对象的主键,主键",allowEmptyValue=true,example="",allowableValues="")
    String bizId;
    @TableIds
    @ApiModelProperty(notes="关注对象的父主键,主键",allowEmptyValue=true,example="",allowableValues="")
    String pbizId;
	
	@ApiModelProperty(notes="名称",allowEmptyValue=true,example="",allowableValues="")
	String username;
	
	@ApiModelProperty(notes="关注时间",allowEmptyValue=true,example="",allowableValues="")
	Date ftime;
	
	@ApiModelProperty(notes="用户归属机构号",allowEmptyValue=true,example="",allowableValues="")
	String ubranchId;
	
	@ApiModelProperty(notes="上级名称",allowEmptyValue=true,example="",allowableValues="")
	String pbizName;
	
	@ApiModelProperty(notes="关注对象的名称",allowEmptyValue=true,example="",allowableValues="")
	String bizName;
	
	@ApiModelProperty(notes="关注对象类型对象类型:项目-1/任务-2/产品-3/需求-4/bug-5/迭代-6/团队-7/组织-8/个人-9/文章-W/合同-H/客户-K",allowEmptyValue=true,example="",allowableValues="")
	String focusType;

	/**
	 *我的账户,关注对象的主键,关注对象的父主键
	 **/
	public UserFocus(String userid,String bizId,String pbizId) {
		this.userid = userid;
		this.bizId = bizId;
		this.pbizId = pbizId;
	}
    
    /**
     * sys_user_focus
     **/
	public UserFocus() {
	}

}