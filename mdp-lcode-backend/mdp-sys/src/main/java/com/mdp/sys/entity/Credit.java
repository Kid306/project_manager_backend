package  com.mdp.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 组织 com  顶级模块 mdp 大模块 sys  小模块 <br> 
 * 实体 Credit所有属性名: <br>
 *	"id","信用等级编号","name","信用名称","startScore","开始信用分","endScore","结束信用分","remark","信用等级描述";<br>
 * 当前主键(包括多主键):<br>
 *	id;<br>
 */
 @Data
@TableName("sys_credit")
@ApiModel(description="用户信用等级定义")
public class Credit  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(notes="信用等级编号,主键",allowEmptyValue=true,example="",allowableValues="")
	String id;
	
	@ApiModelProperty(notes="信用名称",allowEmptyValue=true,example="",allowableValues="")
	String name;
	
	@ApiModelProperty(notes="开始信用分",allowEmptyValue=true,example="",allowableValues="")
	Integer startScore;
	
	@ApiModelProperty(notes="结束信用分",allowEmptyValue=true,example="",allowableValues="")
	Integer endScore;
	
	@ApiModelProperty(notes="信用等级描述",allowEmptyValue=true,example="",allowableValues="")
	String remark;

	/**
	 *信用等级编号
	 **/
	public Credit(String id) {
		this.id = id;
	}
    
    /**
     * 用户信用等级定义
     **/
	public Credit() {
	}

}