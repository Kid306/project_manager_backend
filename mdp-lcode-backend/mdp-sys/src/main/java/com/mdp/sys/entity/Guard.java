package  com.mdp.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 组织 com  顶级模块 mdp 大模块 sys  小模块 <br> 
 * 实体 Guard所有属性名: <br>
 *	"id","编号","name","名称","fee","价格","remark","备注","dayLimit","期限,单位天数，360天，付款成功当天算起";<br>
 * 当前主键(包括多主键):<br>
 *	id;<br>
 */
 @Data
@TableName("sys_guard")
@ApiModel(description="服务保障定义表")
public class Guard  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(notes="编号,主键",allowEmptyValue=true,example="",allowableValues="")
	String id;
	
	@ApiModelProperty(notes="名称",allowEmptyValue=true,example="",allowableValues="")
	String name;
	
	@ApiModelProperty(notes="价格",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal fee;
	
	@ApiModelProperty(notes="备注",allowEmptyValue=true,example="",allowableValues="")
	String remark;
	
	@ApiModelProperty(notes="期限,单位天数，360天，付款成功当天算起",allowEmptyValue=true,example="",allowableValues="")
	Integer dayLimit;

	/**
	 *编号
	 **/
	public Guard(String id) {
		this.id = id;
	}
    
    /**
     * 服务保障定义表
     **/
	public Guard() {
	}

}