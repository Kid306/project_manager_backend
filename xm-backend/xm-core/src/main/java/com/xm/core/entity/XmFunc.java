package  com.xm.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author code-gen
 * @since 2023-10-3
 */
@Data
@TableName("xm_func")
@ApiModel(description="功能模块表")
public class XmFunc  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	
	@ApiModelProperty(notes="主键,主键",allowEmptyValue=true,example="",allowableValues="")
	String id;

	
	@ApiModelProperty(notes="名称",allowEmptyValue=true,example="",allowableValues="")
	String name;

	
	@ApiModelProperty(notes="上级编号",allowEmptyValue=true,example="",allowableValues="")
	String pid;

	
	@ApiModelProperty(notes="上级名称",allowEmptyValue=true,example="",allowableValues="")
	String pname;

	
	@ApiModelProperty(notes="上级路径，直到自身，逗号分割，包含自身",allowEmptyValue=true,example="",allowableValues="")
	String pidPaths;

	
	@ApiModelProperty(notes="产品编号",allowEmptyValue=true,example="",allowableValues="")
	String productId;

	
	@ApiModelProperty(notes="菜单级别0-根，1，2，3，4，5依次类推",allowEmptyValue=true,example="",allowableValues="")
	Integer lvl;

	
	@ApiModelProperty(notes="产品归属企业",allowEmptyValue=true,example="",allowableValues="")
	String pbranchId;

	/**
	 *主键
	 **/
	public XmFunc(String id) {
		this.id = id;
	}
    
    /**
     * 功能模块表
     **/
	public XmFunc() {
	}

}