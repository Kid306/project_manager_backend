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
@TableName("arc_tag_category")
@ApiModel(description="arc_tag_category")
public class TagCategory  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
    @TableIds
	
    @ApiModelProperty(notes="分组编号,主键",allowEmptyValue=true,example="",allowableValues="")
    String id;
    @TableIds
	
    @ApiModelProperty(notes="机构号,主键",allowEmptyValue=true,example="",allowableValues="")
    String branchId;

	
	@ApiModelProperty(notes="商户编号",allowEmptyValue=true,example="",allowableValues="")
	String shopId;

	
	@ApiModelProperty(notes="分组名称",allowEmptyValue=true,example="",allowableValues="")
	String categoryName;

	
	@ApiModelProperty(notes="是否公共0否1是",allowEmptyValue=true,example="",allowableValues="")
	String isPub;

	
	@ApiModelProperty(notes="扩展字段",allowEmptyValue=true,example="",allowableValues="")
	String extInfos;

	/**
	 *分组编号,机构号
	 **/
	public TagCategory(String id,String branchId) {
		this.id = id;
		this.branchId = branchId;
	}
    
    /**
     * arc_tag_category
     **/
	public TagCategory() {
	}

}