package  com.mdp.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 组织 com  顶级模块 mdp 大模块 sys  小模块 <br> 
 * 实体 Region所有属性名: <br>
 *	"id","行政区域ID","parentId","上级行政区域ID","name","行政区域名称","type","行政区域层级","agencyId","agency_id","branchId","云用户机构编号","cuserid","创建人","cdate","创建时间","lopuserid","最后操作人","lopcreate","最后操作时间";<br>
 * 当前主键(包括多主键):<br>
 *	id;<br>
 */
 @Data
@TableName("sys_region")
@ApiModel(description="sys_region")
public class Region  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(notes="行政区域ID,主键",allowEmptyValue=true,example="",allowableValues="")
	String id;
	
	@ApiModelProperty(notes="上级行政区域ID",allowEmptyValue=true,example="",allowableValues="")
	String parentId;
	
	@ApiModelProperty(notes="行政区域名称",allowEmptyValue=true,example="",allowableValues="")
	String name;
	
	@ApiModelProperty(notes="行政区域层级",allowEmptyValue=true,example="",allowableValues="")
	String type;
	
	@ApiModelProperty(notes="agency_id",allowEmptyValue=true,example="",allowableValues="")
	String agencyId;
	
	@ApiModelProperty(notes="云用户机构编号",allowEmptyValue=true,example="",allowableValues="")
	String branchId;
	
	@ApiModelProperty(notes="创建人",allowEmptyValue=true,example="",allowableValues="")
	String cuserid;
	
	@ApiModelProperty(notes="创建时间",allowEmptyValue=true,example="",allowableValues="")
	Date cdate;
	
	@ApiModelProperty(notes="最后操作人",allowEmptyValue=true,example="",allowableValues="")
	String lopuserid;
	
	@ApiModelProperty(notes="最后操作时间",allowEmptyValue=true,example="",allowableValues="")
	Date lopcreate;

	/**
	 *行政区域ID
	 **/
	public Region(String id) {
		this.id = id;
	}
    
    /**
     * sys_region
     **/
	public Region() {
	}

}