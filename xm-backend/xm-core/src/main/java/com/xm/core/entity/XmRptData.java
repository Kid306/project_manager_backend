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
@TableName("xm_rpt_data")
@ApiModel(description="xm_rpt_data")
public class XmRptData  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	
	@ApiModelProperty(notes="报表编号,主键",allowEmptyValue=true,example="",allowableValues="")
	String id;

	
	@ApiModelProperty(notes="报表名称",allowEmptyValue=true,example="",allowableValues="")
	String rptName;

	
	@ApiModelProperty(notes="报表配置主键",allowEmptyValue=true,example="",allowableValues="")
	String cfgId;

	
	@ApiModelProperty(notes="报表数据json对象,比rptCfg.cfg多了rawDatas",allowEmptyValue=true,example="",allowableValues="")
	String rptData;

	
	@ApiModelProperty(notes="创建人编号",allowEmptyValue=true,example="",allowableValues="")
	String cuserid;

	
	@ApiModelProperty(notes="创建人机构号",allowEmptyValue=true,example="",allowableValues="")
	String cbranchId;

	
	@ApiModelProperty(notes="创建人名称",allowEmptyValue=true,example="",allowableValues="")
	String cusername;

	
	@ApiModelProperty(notes="创建日期",allowEmptyValue=true,example="",allowableValues="")
	Date ctime;

	
	@ApiModelProperty(notes="归属业务日期yyyy-MM-dd型",allowEmptyValue=true,example="",allowableValues="")
	String bizDate;

	
	@ApiModelProperty(notes="业务类型，同rpt_config.biz_type",allowEmptyValue=true,example="",allowableValues="")
	String bizType;

	
	@ApiModelProperty(notes="业务编号",allowEmptyValue=true,example="",allowableValues="")
	String bizId;

	/**
	 *报表编号
	 **/
	public XmRptData(String id) {
		this.id = id;
	}
    
    /**
     * xm_rpt_data
     **/
	public XmRptData() {
	}

}