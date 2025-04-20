package  com.mdp.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 组织 com  顶级模块 mdp 大模块 sys  小模块 <br> 
 * 实体 UserGradeRecord所有属性名: <br>
 *	"id","主键","userid","用户编号","score","能力得分","remark","能力得分变化说明","bizId","引起变化的业务主键，比如任务编号等","bizType","引起变化的业务分类","direct","变化方向0-减，1-增加","ctime","变化时间","bizDate","归属业务日期yyyy-MM-dd类型","at","金额","exp","经验","state","状态","bizMonth","业务月份";<br>
 * 当前主键(包括多主键):<br>
 *	id;<br>
 */
 @Data
@TableName("sys_user_grade_record")
@ApiModel(description="用户能力等级得分记录表")
public class UserGradeRecord  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(notes="主键,主键",allowEmptyValue=true,example="",allowableValues="")
	String id;
	
	@ApiModelProperty(notes="用户编号",allowEmptyValue=true,example="",allowableValues="")
	String userid;
	
	@ApiModelProperty(notes="能力得分",allowEmptyValue=true,example="",allowableValues="")
	Integer score;
	
	@ApiModelProperty(notes="能力得分变化说明",allowEmptyValue=true,example="",allowableValues="")
	String remark;
	
	@ApiModelProperty(notes="引起变化的业务主键，比如任务编号等",allowEmptyValue=true,example="",allowableValues="")
	String bizId;
	
	@ApiModelProperty(notes="引起变化的业务分类",allowEmptyValue=true,example="",allowableValues="")
	String bizType;
	
	@ApiModelProperty(notes="变化方向0-减，1-增加",allowEmptyValue=true,example="",allowableValues="")
	String direct;
	
	@ApiModelProperty(notes="变化时间",allowEmptyValue=true,example="",allowableValues="")
	Date ctime;
	
	@ApiModelProperty(notes="归属业务日期yyyy-MM-dd类型",allowEmptyValue=true,example="",allowableValues="")
	String bizDate;
	
	@ApiModelProperty(notes="金额",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal at;
	
	@ApiModelProperty(notes="经验",allowEmptyValue=true,example="",allowableValues="")
	Integer exp;
	
	@ApiModelProperty(notes="状态",allowEmptyValue=true,example="",allowableValues="")
	String state;
	
	@ApiModelProperty(notes="业务月份",allowEmptyValue=true,example="",allowableValues="")
	String bizMonth;

	/**
	 *主键
	 **/
	public UserGradeRecord(String id) {
		this.id = id;
	}
    
    /**
     * 用户能力等级得分记录表
     **/
	public UserGradeRecord() {
	}

}