package  com.mdp.arc.img.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author code-gen
 * @since 2023-9-20
 */
@Data
@TableName("arc_image_category")
@ApiModel(description="图片分类")
public class ImageCategory  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	
	@ApiModelProperty(notes="主键,主键",allowEmptyValue=true,example="",allowableValues="")
	String id;

	
	@ApiModelProperty(notes="分类名称",allowEmptyValue=true,example="",allowableValues="")
	String categoryName;

	
	@ApiModelProperty(notes="机构编号",allowEmptyValue=true,example="",allowableValues="")
	String branchId;

	
	@ApiModelProperty(notes="上一级",allowEmptyValue=true,example="",allowableValues="")
	String pid;

	
	@ApiModelProperty(notes="是否是公共分类的图片",allowEmptyValue=true,example="",allowableValues="")
	String isPub;

	
	@ApiModelProperty(notes="扩展字段",allowEmptyValue=true,example="",allowableValues="")
	String extInfos;

	
	@ApiModelProperty(notes="参考类型",allowEmptyValue=true,example="",allowableValues="")
	String crelyType;

	
	@ApiModelProperty(notes="参考类型编号",allowEmptyValue=true,example="",allowableValues="")
	String crelyId;

	
	@ApiModelProperty(notes="子参考类型",allowEmptyValue=true,example="",allowableValues="")
	String crelyStype;

	
	@ApiModelProperty(notes="子参考类型编号",allowEmptyValue=true,example="",allowableValues="")
	String crelySid;

	@ApiModelProperty(notes="图标",allowEmptyValue=true,example="",allowableValues="")
	String icon;


	/**
	 *主键
	 **/
	public ImageCategory(String id) {
		this.id = id;
	}
    
    /**
     * 图片分类
     **/
	public ImageCategory() {
	}

}