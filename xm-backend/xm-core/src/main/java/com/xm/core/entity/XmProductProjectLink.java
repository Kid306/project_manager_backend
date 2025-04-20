package  com.xm.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mdp.core.dao.annotation.TableIds;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author code-gen
 * @since 2023-10-3
 */
@Data
@TableName("xm_product_project_link")
@ApiModel(description="产品与项目的关联关系表，一般由产品经理挂接项目到产品上")
public class XmProductProjectLink  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
    @TableIds
	
    @ApiModelProperty(notes="项目表中的主键,主键",allowEmptyValue=true,example="",allowableValues="")
    String projectId;
    @TableIds
	
    @ApiModelProperty(notes="产品表中的主键,主键",allowEmptyValue=true,example="",allowableValues="")
    String productId;

	
	@ApiModelProperty(notes="创建时间",allowEmptyValue=true,example="",allowableValues="")
	Date ctime;

	
	@ApiModelProperty(notes="创建人编号",allowEmptyValue=true,example="",allowableValues="")
	String cuserid;

	
	@ApiModelProperty(notes="创建人姓名",allowEmptyValue=true,example="",allowableValues="")
	String cusername;

	
	@ApiModelProperty(notes="关联状态1关联0取消关联",allowEmptyValue=true,example="",allowableValues="")
	String linkStatus;

	
	@ApiModelProperty(notes="显示顺序0-999,从小到大排序",allowEmptyValue=true,example="",allowableValues="")
	Integer seq;

	/**
	 *项目表中的主键,产品表中的主键
	 **/
	public XmProductProjectLink(String projectId,String productId) {
		this.projectId = projectId;
		this.productId = productId;
	}
    
    /**
     * 产品与项目的关联关系表，一般由产品经理挂接项目到产品上
     **/
	public XmProductProjectLink() {
	}

}