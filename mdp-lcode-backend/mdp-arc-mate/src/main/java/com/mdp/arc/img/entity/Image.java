package  com.mdp.arc.img.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author code-gen
 * @since 2023-9-15
 */
@Data
@TableName("arc_image")
@ApiModel(description="图片素材库")
public class Image  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	
	@ApiModelProperty(notes="主键,主键",allowEmptyValue=true,example="",allowableValues="")
	String id;

	
	@ApiModelProperty(notes="附件名称",allowEmptyValue=true,example="",allowableValues="")
	String name;

	
	@ApiModelProperty(notes="访问路径",allowEmptyValue=true,example="",allowableValues="")
	String url;

	
	@ApiModelProperty(notes="硬盘存放路径",allowEmptyValue=true,example="",allowableValues="")
	String relativePath;

	
	@ApiModelProperty(notes="后缀名",allowEmptyValue=true,example="",allowableValues="")
	String fileSuffix;

	
	@ApiModelProperty(notes="根目录",allowEmptyValue=true,example="",allowableValues="")
	String rootPath;

	
	@ApiModelProperty(notes="存入时间",allowEmptyValue=true,example="",allowableValues="")
	Date createDate;

	
	@ApiModelProperty(notes="文件大小",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal fileSize;

	
	@ApiModelProperty(notes="归属部门",allowEmptyValue=true,example="",allowableValues="")
	String deptid;

	
	@ApiModelProperty(notes="标签",allowEmptyValue=true,example="",allowableValues="")
	String tag;

	
	@ApiModelProperty(notes="备注信息",allowEmptyValue=true,example="",allowableValues="")
	String remark;

	
	@ApiModelProperty(notes="图片分类",allowEmptyValue=true,example="",allowableValues="")
	String categoryId;

	
	@ApiModelProperty(notes="硬盘存储名字(不带后缀)",allowEmptyValue=true,example="",allowableValues="")
	String storageName;

	
	@ApiModelProperty(notes="链接前缀",allowEmptyValue=true,example="",allowableValues="")
	String urlPrefix;

	
	@ApiModelProperty(notes="是否外部链接",allowEmptyValue=true,example="",allowableValues="")
	String isOutUrl;

	
	@ApiModelProperty(notes="外部链接",allowEmptyValue=true,example="",allowableValues="")
	String outUrl;

	
	@ApiModelProperty(notes="机构编号",allowEmptyValue=true,example="",allowableValues="")
	String branchId;

	
	@ApiModelProperty(notes="0|知识库",allowEmptyValue=true,example="",allowableValues="")
	String archiveType;

	
	@ApiModelProperty(notes="参考类型，开放式字段，1-开源社区，2-项目论坛,逗号",allowEmptyValue=true,example="",allowableValues="")
	String relyTypes;

	
	@ApiModelProperty(notes="参考编号,逗号",allowEmptyValue=true,example="",allowableValues="")
	String relyIds;

	
	@ApiModelProperty(notes="参考子类型,逗号",allowEmptyValue=true,example="",allowableValues="")
	String relySubTypes;

	
	@ApiModelProperty(notes="参考子编号,逗号",allowEmptyValue=true,example="",allowableValues="")
	String relySubIds;

	
	@ApiModelProperty(notes="创建人编号",allowEmptyValue=true,example="",allowableValues="")
	String cuserid;

	
	@ApiModelProperty(notes="创建人姓名",allowEmptyValue=true,example="",allowableValues="")
	String cusername;

	
	@ApiModelProperty(notes="创建时间",allowEmptyValue=true,example="",allowableValues="")
	Date cdate;

	/**
	 *主键
	 **/
	public Image(String id) {
		this.id = id;
	}
    
    /**
     * 图片素材库
     **/
	public Image() {
	}

}