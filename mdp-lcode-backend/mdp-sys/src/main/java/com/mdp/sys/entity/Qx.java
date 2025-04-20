package  com.mdp.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 组织 com  顶级模块 mdp 大模块 sys  小模块 <br> 
 * 实体 Qx所有属性名: <br>
 *	"qxId","权限编号","qxName","权限名称","moduleId","权限归属模块编号";<br>
 * 当前主键(包括多主键):<br>
 *	qx_id;<br>
 */
 @Data
@TableName("sys_qx")
@ApiModel(description="权限定义")
public class Qx  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(notes="权限编号,主键",allowEmptyValue=true,example="",allowableValues="")
	String qxId;
	
	@ApiModelProperty(notes="权限名称",allowEmptyValue=true,example="",allowableValues="")
	String qxName;
	
	@ApiModelProperty(notes="权限归属模块编号",allowEmptyValue=true,example="",allowableValues="")
	String moduleId;


	@ApiModelProperty(notes="权限类型",allowEmptyValue=true,example="",allowableValues="")
	String qxType;


	@ApiModelProperty(notes="权限sql片段",allowEmptyValue=true,example="",allowableValues="")
	String qxSql;


	@ApiModelProperty(notes="权限表达式",allowEmptyValue=true,example="",allowableValues="")
	String qxRegex;

	/**
	 *权限编号
	 **/
	public Qx(String qxId) {
		this.qxId = qxId;
	}
    
    /**
     * 权限定义
     **/
	public Qx() {
	}

}