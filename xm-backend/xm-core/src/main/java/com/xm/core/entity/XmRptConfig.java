package  com.xm.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author code-gen
 * @since 2023-10-3
 */
@Data
@TableName("xm_rpt_config")
@ApiModel(description="测试报告配置表")
public class XmRptConfig  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	
	@ApiModelProperty(notes="报告编号,主键",allowEmptyValue=true,example="",allowableValues="")
	String id;

	
	@ApiModelProperty(notes="业务编号",allowEmptyValue=true,example="",allowableValues="")
	String bizId;

	
	@ApiModelProperty(notes="报告名称",allowEmptyValue=true,example="",allowableValues="")
	String name;

	
	@ApiModelProperty(notes="创建人",allowEmptyValue=true,example="",allowableValues="")
	String cuserid;

	
	@ApiModelProperty(notes="创建人姓名",allowEmptyValue=true,example="",allowableValues="")
	String cusername;

	
	@ApiModelProperty(notes="创建时间",allowEmptyValue=true,example="",allowableValues="")
	Date ctime;

	
	@ApiModelProperty(notes="创建机构",allowEmptyValue=true,example="",allowableValues="")
	String cbranchId;

	
	@ApiModelProperty(notes="报告配置项",allowEmptyValue=true,example="",allowableValues="")
	String cfg;

	
	@ApiModelProperty(notes="业务类型1-产品报告，2-迭代报告，3-测试计划报告，4-项目报告，5-企业报告，6-测试库报告",allowEmptyValue=true,example="",allowableValues="")
	String bizType;

	/**
	 *报告编号
	 **/
	public XmRptConfig(String id) {
		this.id = id;
	}
    
    /**
     * 测试报告配置表
     **/
	public XmRptConfig() {
	}

}