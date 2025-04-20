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
 * 实体 UserSvr所有属性名: <br>
 *	"id","服务编号","name","服务名称","remark","服务简介","price","服务价格","pimg","服务主图","times","服务时间范围","status","状态0初始1上架2下架","summary","简介","userid","用户编号","ctime","创建时间","ltime","最后更新时间";<br>
 * 当前主键(包括多主键):<br>
 *	id;<br>
 */
 @Data
@TableName("sys_user_svr")
@ApiModel(description="企业服务列表")
public class UserSvr  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(notes="服务编号,主键",allowEmptyValue=true,example="",allowableValues="")
	String id;
	
	@ApiModelProperty(notes="服务名称",allowEmptyValue=true,example="",allowableValues="")
	String name;
	
	@ApiModelProperty(notes="服务简介",allowEmptyValue=true,example="",allowableValues="")
	String remark;
	
	@ApiModelProperty(notes="服务价格",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal price;
	
	@ApiModelProperty(notes="服务主图",allowEmptyValue=true,example="",allowableValues="")
	String pimg;
	
	@ApiModelProperty(notes="服务时间范围",allowEmptyValue=true,example="",allowableValues="")
	String times;
	
	@ApiModelProperty(notes="状态0初始1上架2下架",allowEmptyValue=true,example="",allowableValues="")
	String status;
	
	@ApiModelProperty(notes="简介",allowEmptyValue=true,example="",allowableValues="")
	String summary;
	
	@ApiModelProperty(notes="用户编号",allowEmptyValue=true,example="",allowableValues="")
	String userid;
	
	@ApiModelProperty(notes="创建时间",allowEmptyValue=true,example="",allowableValues="")
	Date ctime;
	
	@ApiModelProperty(notes="最后更新时间",allowEmptyValue=true,example="",allowableValues="")
	Date ltime;

	/**
	 *服务编号
	 **/
	public UserSvr(String id) {
		this.id = id;
	}
    
    /**
     * 企业服务列表
     **/
	public UserSvr() {
	}

}