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
@TableName("xm_product_version")
@ApiModel(description="产品版本编号")
public class XmProductVersion  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
    @TableIds
	
    @ApiModelProperty(notes="版本号,主键",allowEmptyValue=true,example="",allowableValues="")
    String id;
    @TableIds
	
    @ApiModelProperty(notes="产品编号,主键",allowEmptyValue=true,example="",allowableValues="")
    String productId;

	
	@ApiModelProperty(notes="版本描述",allowEmptyValue=true,example="",allowableValues="")
	String remark;

	
	@ApiModelProperty(notes="版本状态0-未开始，1-进行中，2-已发布",allowEmptyValue=true,example="",allowableValues="")
	String vstatus;

	
	@ApiModelProperty(notes="进度0-100之间",allowEmptyValue=true,example="",allowableValues="")
	Integer vrate;

	
	@ApiModelProperty(notes="发布时间",allowEmptyValue=true,example="",allowableValues="")
	Date ptime;

	
	@ApiModelProperty(notes="开始时间",allowEmptyValue=true,example="",allowableValues="")
	Date startTime;

	
	@ApiModelProperty(notes="结束时间",allowEmptyValue=true,example="",allowableValues="")
	Date endTime;

	
	@ApiModelProperty(notes="负责人",allowEmptyValue=true,example="",allowableValues="")
	String admUserid;

	
	@ApiModelProperty(notes="负责人姓名",allowEmptyValue=true,example="",allowableValues="")
	String admUsername;

	
	@ApiModelProperty(notes="创建时间",allowEmptyValue=true,example="",allowableValues="")
	Date ctime;

	
	@ApiModelProperty(notes="版本名称",allowEmptyValue=true,example="",allowableValues="")
	String name;

	/**
	 *版本号,产品编号
	 **/
	public XmProductVersion(String id,String productId) {
		this.id = id;
		this.productId = productId;
	}
    
    /**
     * 产品版本编号
     **/
	public XmProductVersion() {
	}

}