package  com.mdp.arc.pub.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mdp.core.dao.annotation.TableIds;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author code-gen
 * @since 2023-9-15
 */
@Data
@TableName("arc_tag")
@ApiModel(description="arc_tag")
public class Tag  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
    @TableIds
	
    @ApiModelProperty(notes="机构号,主键",allowEmptyValue=true,example="",allowableValues="")
    String branchId;
    @TableIds
	
    @ApiModelProperty(notes="标签编号,主键",allowEmptyValue=true,example="",allowableValues="")
    String id;

	
	@ApiModelProperty(notes="标签名",allowEmptyValue=true,example="",allowableValues="")
	String tagName;

	
	@ApiModelProperty(notes="商户编号",allowEmptyValue=true,example="",allowableValues="")
	String shopId;

	
	@ApiModelProperty(notes="标签分组",allowEmptyValue=true,example="",allowableValues="")
	String categoryId;

	
	@ApiModelProperty(notes="是否公共0否1是",allowEmptyValue=true,example="",allowableValues="")
	String isPub;

	/**
	 *机构号,标签编号
	 **/
	public Tag(String branchId,String id) {
		this.branchId = branchId;
		this.id = id;
	}
    
    /**
     * arc_tag
     **/
	public Tag() {
	}

}