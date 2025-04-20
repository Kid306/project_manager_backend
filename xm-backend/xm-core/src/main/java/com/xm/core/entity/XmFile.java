package  com.xm.core.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 组织 com  顶级模块 xm 大模块 core  小模块 <br> 
 * 实体 XmFile所有属性名: <br>
 *	"id","文档编号","name","文件名称","projectId","项目编号","projectName","项目名称","description","文件说明","createUserid","创建人编号","createUsername","创建人","createTime","创建时间","readQx","0-全部可看，1-同机构可看，2-同机构同项目可看，3-同项目上级可看，9-仅自己可看","writeQx","0-全部可写，1-同机构可写，2-同机构同项目可写，3-同项目上级可写，9-仅自己可修改","ltime","修改时间","luserid","修改人编号","lusername","修改人姓名";<br>
 * 当前主键(包括多主键):<br>
 *	id;<br>
 */
 @Data
@ApiModel(description="xm_file")
public class XmFile  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(notes="文档编号,主键",allowEmptyValue=true,example="",allowableValues="")
	String id;
  	
	
	@ApiModelProperty(notes="文件名称",allowEmptyValue=true,example="",allowableValues="")
	String name;
	
	@ApiModelProperty(notes="项目编号",allowEmptyValue=true,example="",allowableValues="")
	String projectId;
	
	@ApiModelProperty(notes="项目名称",allowEmptyValue=true,example="",allowableValues="")
	String projectName;
	
	@ApiModelProperty(notes="文件说明",allowEmptyValue=true,example="",allowableValues="")
	String description;
	
	@ApiModelProperty(notes="创建人编号",allowEmptyValue=true,example="",allowableValues="")
	String createUserid;
	
	@ApiModelProperty(notes="创建人",allowEmptyValue=true,example="",allowableValues="")
	String createUsername;
	
	@ApiModelProperty(notes="创建时间",allowEmptyValue=true,example="",allowableValues="")
	Date createTime;
	
	@ApiModelProperty(notes="0-全部可看，1-同机构可看，2-同机构同项目可看，3-同项目上级可看，9-仅自己可看",allowEmptyValue=true,example="",allowableValues="")
	String readQx;
	
	@ApiModelProperty(notes="0-全部可写，1-同机构可写，2-同机构同项目可写，3-同项目上级可写，9-仅自己可修改",allowEmptyValue=true,example="",allowableValues="")
	String writeQx;
	
	@ApiModelProperty(notes="修改时间",allowEmptyValue=true,example="",allowableValues="")
	Date ltime;
	
	@ApiModelProperty(notes="修改人编号",allowEmptyValue=true,example="",allowableValues="")
	String luserid;
	
	@ApiModelProperty(notes="修改人姓名",allowEmptyValue=true,example="",allowableValues="")
	String lusername;

	/**
	 *文档编号
	 **/
	public XmFile(String id) {
		this.id = id;
	}
    
    /**
     * xm_file
     **/
	public XmFile() {
	}

}