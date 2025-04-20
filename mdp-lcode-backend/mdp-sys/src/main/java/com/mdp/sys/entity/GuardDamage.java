package  com.mdp.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 组织 com  顶级模块 mdp 大模块 sys  小模块 <br> 
 * 实体 GuardDamage所有属性名: <br>
 *	"id","主键","payUserid","用户编号","payAt","赔付金额","remark","备注","reason","原因","payTime","赔付时间","bizId","业务编号","bizType","业务类型，随意暂时1-代表任务","toUserid","赔付对象用户编号","toUsername","赔付对象姓名","payUsername","付款人姓名","payStatus","支付状态";<br>
 * 当前主键(包括多主键):<br>
 *	id;<br>
 */
 @Data
@TableName("sys_guard_damage")
@ApiModel(description="保障金赔付表")
public class GuardDamage  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(notes="主键,主键",allowEmptyValue=true,example="",allowableValues="")
	String id;
	
	@ApiModelProperty(notes="用户编号",allowEmptyValue=true,example="",allowableValues="")
	String payUserid;
	
	@ApiModelProperty(notes="赔付金额",allowEmptyValue=true,example="",allowableValues="")
	String payAt;
	
	@ApiModelProperty(notes="备注",allowEmptyValue=true,example="",allowableValues="")
	String remark;
	
	@ApiModelProperty(notes="原因",allowEmptyValue=true,example="",allowableValues="")
	String reason;
	
	@ApiModelProperty(notes="赔付时间",allowEmptyValue=true,example="",allowableValues="")
	Date payTime;
	
	@ApiModelProperty(notes="业务编号",allowEmptyValue=true,example="",allowableValues="")
	String bizId;
	
	@ApiModelProperty(notes="业务类型，随意暂时1-代表任务",allowEmptyValue=true,example="",allowableValues="")
	String bizType;
	
	@ApiModelProperty(notes="赔付对象用户编号",allowEmptyValue=true,example="",allowableValues="")
	String toUserid;
	
	@ApiModelProperty(notes="赔付对象姓名",allowEmptyValue=true,example="",allowableValues="")
	String toUsername;
	
	@ApiModelProperty(notes="付款人姓名",allowEmptyValue=true,example="",allowableValues="")
	String payUsername;
	
	@ApiModelProperty(notes="支付状态",allowEmptyValue=true,example="",allowableValues="")
	String payStatus;

	/**
	 *主键
	 **/
	public GuardDamage(String id) {
		this.id = id;
	}
    
    /**
     * 保障金赔付表
     **/
	public GuardDamage() {
	}

}