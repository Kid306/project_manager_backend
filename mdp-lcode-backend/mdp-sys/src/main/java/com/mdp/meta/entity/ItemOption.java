package  com.mdp.meta.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mdp.core.dao.annotation.TableIds;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 组织 com  顶级模块 mdp 大模块 meta  小模块 <br> 
 * 实体 ItemOption所有属性名: <br>
 *	"id","编号","name","名称","relyType","关联类型逗号分隔多个","relyId","关联类型的编号，逗号分隔多个","relyStype","关联子类型逗号分隔多个","relySid","关联子类型对应的编号，逗号分隔多个","color","显示颜色，参考https://element-plus.gitee.io/zh-CN/component/color.html","unTargets","不能跳转到哪些状态,逗号分隔多个","disabled","前台是否可选1-不可选，0或者空可选","itemId","指向meta_item.id","seqOrder","排位从0-999";<br>
 * 当前主键(包括多主键):<br>
 *	id,item_id;<br>
 */
 @Data
@TableName("meta_item_option")
@ApiModel(description="字典选项值")
public class ItemOption  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
    @TableIds
    @ApiModelProperty(notes="编号,主键",allowEmptyValue=true,example="",allowableValues="")
    String id;
    @TableIds
    @ApiModelProperty(notes="指向meta_item.id,主键",allowEmptyValue=true,example="",allowableValues="")
    String itemId;
	
	@ApiModelProperty(notes="名称",allowEmptyValue=true,example="",allowableValues="")
	String name;
	
	@ApiModelProperty(notes="关联类型逗号分隔多个",allowEmptyValue=true,example="",allowableValues="")
	String relyType;
	
	@ApiModelProperty(notes="关联类型的编号，逗号分隔多个",allowEmptyValue=true,example="",allowableValues="")
	String relyId;
	
	@ApiModelProperty(notes="关联子类型逗号分隔多个",allowEmptyValue=true,example="",allowableValues="")
	String relyStype;
	
	@ApiModelProperty(notes="关联子类型对应的编号，逗号分隔多个",allowEmptyValue=true,example="",allowableValues="")
	String relySid;
	
	@ApiModelProperty(notes="显示颜色，参考https://element-plus.gitee.io/zh-CN/component/color.html",allowEmptyValue=true,example="",allowableValues="")
	String color;


	@ApiModelProperty(notes="能跳转到哪些状态,逗号分隔多个",allowEmptyValue=true,example="",allowableValues="")
	String targets;
	
	@ApiModelProperty(notes="不能跳转到哪些状态,逗号分隔多个",allowEmptyValue=true,example="",allowableValues="")
	String unTargets;
	
	@ApiModelProperty(notes="前台是否可选1-不可选，0或者空可选",allowEmptyValue=true,example="",allowableValues="")
	String disabled;
	
	@ApiModelProperty(notes="排位从0-999",allowEmptyValue=true,example="",allowableValues="")
	Integer seqOrder;


	@ApiModelProperty(notes="自定义变量及值",allowEmptyValue=true,example="",allowableValues="")
	String extVals;

	@ApiModelProperty(notes="图标",allowEmptyValue=true,example="",allowableValues="")
	String icon;

	/**
	 *编号,指向meta_item.id
	 **/
	public ItemOption(String id,String itemId) {
		this.id = id;
		this.itemId = itemId;
	}
    
    /**
     * 字典选项值
     **/
	public ItemOption() {
	}

}