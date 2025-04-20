package  com.mdp.mo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 组织 com  顶级模块 mdp 大模块 mo  小模块 <br> 
 * 实体 MoOrderFligship所有属性名: <br>
 *	"id","id","contacts","联系人","usePeoples","使用人数","phone","电话","needs","需求","obranchId","下单机构","ouserid","下单用户编号","ousername","下单用户名称","createTime","创建时间","updateTime","更新时间";<br>
 * 当前主键(包括多主键):<br>
 *	id;<br>
 */
 @Data
@TableName("mo_order_fligship")
@ApiModel(description="订单联系人")
public class MoOrderFligship  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(notes="id,主键",allowEmptyValue=true,example="",allowableValues="")
	String id;
	
	@ApiModelProperty(notes="联系人",allowEmptyValue=true,example="",allowableValues="")
	String contacts;
	
	@ApiModelProperty(notes="使用人数",allowEmptyValue=true,example="",allowableValues="")
	String usePeoples;
	
	@ApiModelProperty(notes="电话",allowEmptyValue=true,example="",allowableValues="")
	String phone;
	
	@ApiModelProperty(notes="需求",allowEmptyValue=true,example="",allowableValues="")
	String needs;
	
	@ApiModelProperty(notes="下单机构",allowEmptyValue=true,example="",allowableValues="")
	String obranchId;
	
	@ApiModelProperty(notes="下单用户编号",allowEmptyValue=true,example="",allowableValues="")
	String ouserid;
	
	@ApiModelProperty(notes="下单用户名称",allowEmptyValue=true,example="",allowableValues="")
	String ousername;
	
	@ApiModelProperty(notes="创建时间",allowEmptyValue=true,example="",allowableValues="")
	Date createTime;
	
	@ApiModelProperty(notes="更新时间",allowEmptyValue=true,example="",allowableValues="")
	Date updateTime;

	/**
	 *id
	 **/
	public MoOrderFligship(String id) {
		this.id = id;
	}
    
    /**
     * 订单联系人
     **/
	public MoOrderFligship() {
	}

}