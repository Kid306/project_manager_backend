package  com.mdp.dm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author code-gen
 * @since 2024-4-30
 */
@Data
@TableName("dm_data_set")
@ApiModel(description="数据集")
public class DmDataSet implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	
	@ApiModelProperty(notes="数据集编号,主键",allowEmptyValue=true,example="",allowableValues="")
	String id;

	
	@ApiModelProperty(notes="数据集sql",allowEmptyValue=true,example="",allowableValues="")
	String dsSql;

	
	@ApiModelProperty(notes="数据源-关联数据字典dm_data_source",allowEmptyValue=true,example="",allowableValues="")
	String dataSource;

	
	@ApiModelProperty(notes="数据模型-关联字典dm_data_model",allowEmptyValue=true,example="",allowableValues="")
	String dataModel;

	
	@ApiModelProperty(notes="数据集标题",allowEmptyValue=true,example="",allowableValues="")
	String title;

	
	@ApiModelProperty(notes="创建者",allowEmptyValue=true,example="",allowableValues="")
	String cuserid;

	
	@ApiModelProperty(notes="创建者机构号",allowEmptyValue=true,example="",allowableValues="")
	String cbranchId;

	
	@ApiModelProperty(notes="创建时间",allowEmptyValue=true,example="",allowableValues="")
	Date ctime;

	
	@ApiModelProperty(notes="最后修改人",allowEmptyValue=true,example="",allowableValues="")
	String euserid;

	
	@ApiModelProperty(notes="最后修改时间",allowEmptyValue=true,example="",allowableValues="")
	Date etime;

	
	@ApiModelProperty(notes="字段编号与title对照；[{id:'字段编号',label:'字段说明'}]",allowEmptyValue=true,example="",allowableValues="")
	String idTitleLinks;

	
	@ApiModelProperty(notes="数据集类型-字典dm_ds_type,1-sql,2-api",allowEmptyValue=true,example="",allowableValues="")
	String dsType;

	
	@ApiModelProperty(notes="api地址",allowEmptyValue=true,example="",allowableValues="")
	String apiUrl;

	
	@ApiModelProperty(notes="请求方法post/get",allowEmptyValue=true,example="",allowableValues="")
	String apiMethod;

	
	@ApiModelProperty(notes="请求头",allowEmptyValue=true,example="",allowableValues="")
	String apiHeader;

	/**
	 *数据集编号
	 **/
	public DmDataSet(String id) {
		this.id = id;
	}
    
    /**
     * 数据集
     **/
	public DmDataSet() {
	}

}