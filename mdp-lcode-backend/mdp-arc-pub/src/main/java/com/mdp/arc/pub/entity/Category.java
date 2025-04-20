package  com.mdp.arc.pub.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author code-gen
 * @since 2023-9-20
 */
@Data
@TableName("arc_category")
@ApiModel(description="档案类目")
public class Category  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	
	@ApiModelProperty(notes="分类主键,主键",allowEmptyValue=true,example="",allowableValues="")
	String id;

	
	@ApiModelProperty(notes="0知识库-1新闻类2企业内部通知公告类3平台通知公告4其它5论坛6公文7归档8网站栏目,关联基础数据categoryType",allowEmptyValue=true,example="",allowableValues="")
	String categoryType;

	
	@ApiModelProperty(notes="上级分类",allowEmptyValue=true,example="",allowableValues="")
	String pid;

	
	@ApiModelProperty(notes="分类名称",allowEmptyValue=true,example="",allowableValues="")
	String name;

	
	@ApiModelProperty(notes="分类排序",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal sortOrder;

	
	@ApiModelProperty(notes="是否显示",allowEmptyValue=true,example="",allowableValues="")
	String isShow;

	
	@ApiModelProperty(notes="机构编号",allowEmptyValue=true,example="",allowableValues="")
	String branchId;

	
	@ApiModelProperty(notes="图片列表逗号分割",allowEmptyValue=true,example="",allowableValues="")
	String imageUrls;

	
	@ApiModelProperty(notes="是否叶子节点0否1是，叶子节点不允许再建立下级，非叶子节点不允许挂文章",allowEmptyValue=true,example="",allowableValues="")
	String isLeaf;

	
	@ApiModelProperty(notes="文章限制,1-单篇文章，2-多篇文章",allowEmptyValue=true,example="",allowableValues="")
	String limitType;

	
	@ApiModelProperty(notes="文章是否需要审核0-否1是",allowEmptyValue=true,example="",allowableValues="")
	String isAuth;

	
	@ApiModelProperty(notes="上级分类路径，逗号分割，包括自身",allowEmptyValue=true,example="",allowableValues="")
	String paths;

	
	@ApiModelProperty(notes="参考类型",allowEmptyValue=true,example="",allowableValues="")
	String crelyType;

	
	@ApiModelProperty(notes="参考类型编号",allowEmptyValue=true,example="",allowableValues="")
	String crelyId;

	
	@ApiModelProperty(notes="子参考类型",allowEmptyValue=true,example="",allowableValues="")
	String crelyStype;

	
	@ApiModelProperty(notes="子参考类型编号",allowEmptyValue=true,example="",allowableValues="")
	String crelySid;

	
	@ApiModelProperty(notes="权限控制要求",allowEmptyValue=true,example="",allowableValues="")
	String qxLvl;

	
	@ApiModelProperty(notes="是否强制要求子类继承",allowEmptyValue=true,example="",allowableValues="")
	String pqx;

	
	@ApiModelProperty(notes="扩展字段",allowEmptyValue=true,example="",allowableValues="")
	String extInfos;

	
	@ApiModelProperty(notes="创建人编号",allowEmptyValue=true,example="",allowableValues="")
	String cuserid;

	
	@ApiModelProperty(notes="标签",allowEmptyValue=true,example="",allowableValues="")
	String tagIds;

	/**
	 *分类主键
	 **/
	public Category(String id) {
		this.id = id;
	}
    
    /**
     * 档案类目
     **/
	public Category() {
	}

}