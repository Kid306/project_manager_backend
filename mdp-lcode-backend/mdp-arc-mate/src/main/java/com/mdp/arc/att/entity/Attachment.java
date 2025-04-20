package  com.mdp.arc.att.entity;

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
 * @since 2023-9-20
 */
@Data
@TableName("arc_attachment")
@ApiModel(description="档案附件表")
public class Attachment  implements java.io.Serializable {
	
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

	
	@ApiModelProperty(notes="内容加速器访问路径",allowEmptyValue=true,example="",allowableValues="")
	String cdnUrl;

	
	@ApiModelProperty(notes="是否图片",allowEmptyValue=true,example="",allowableValues="")
	String isImg;

	
	@ApiModelProperty(notes="档案主编号",allowEmptyValue=true,example="",allowableValues="")
	String archiveId;

	
	@ApiModelProperty(notes="是否使用CDN",allowEmptyValue=true,example="",allowableValues="")
	String isCdn;

	
	@ApiModelProperty(notes="根目录",allowEmptyValue=true,example="",allowableValues="")
	String rootPath;

	
	@ApiModelProperty(notes="存入时间",allowEmptyValue=true,example="",allowableValues="")
	Date createDate;

	
	@ApiModelProperty(notes="是否可下载",allowEmptyValue=true,example="",allowableValues="")
	String canDown;

	
	@ApiModelProperty(notes="是否可删除",allowEmptyValue=true,example="",allowableValues="")
	String canDel;

	
	@ApiModelProperty(notes="是否可读",allowEmptyValue=true,example="",allowableValues="")
	String canRead;

	
	@ApiModelProperty(notes="业务编号、产品编号、商品编号等",allowEmptyValue=true,example="",allowableValues="")
	String bizId;

	
	@ApiModelProperty(notes="业务名称、产品名称、商品名称等",allowEmptyValue=true,example="",allowableValues="")
	String remark;

	
	@ApiModelProperty(notes="存储名字",allowEmptyValue=true,example="",allowableValues="")
	String storeName;

	
	@ApiModelProperty(notes="文件大小",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal fileSize;

	
	@ApiModelProperty(notes="云用户机构编号",allowEmptyValue=true,example="",allowableValues="")
	String branchId;

	
	@ApiModelProperty(notes="部门编号",allowEmptyValue=true,example="",allowableValues="")
	String deptid;

	
	@ApiModelProperty(notes="0|知识库",allowEmptyValue=true,example="",allowableValues="")
	String archiveType;

	
	@ApiModelProperty(notes="分类编号",allowEmptyValue=true,example="",allowableValues="")
	String categoryId;

	
	@ApiModelProperty(notes="参考类型",allowEmptyValue=true,example="",allowableValues="")
	String relyType;

	
	@ApiModelProperty(notes="参考编号",allowEmptyValue=true,example="",allowableValues="")
	String relyId;

	
	@ApiModelProperty(notes="参考子类型",allowEmptyValue=true,example="",allowableValues="")
	String relyStype;

	
	@ApiModelProperty(notes="参考子编号",allowEmptyValue=true,example="",allowableValues="")
	String relySid;

	
	@ApiModelProperty(notes="创建人编号",allowEmptyValue=true,example="",allowableValues="")
	String cuserid;

	
	@ApiModelProperty(notes="创建人姓名",allowEmptyValue=true,example="",allowableValues="")
	String cusername;

	
	@ApiModelProperty(notes="创建时间",allowEmptyValue=true,example="",allowableValues="")
	Date cdate;

	
	@ApiModelProperty(notes="扩展字段",allowEmptyValue=true,example="",allowableValues="")
	String extInfos;

	
	@ApiModelProperty(notes="标签",allowEmptyValue=true,example="",allowableValues="")
	String tagIds;

	/**
	 *主键
	 **/
	public Attachment(String id) {
		this.id = id;
	}
    
    /**
     * 档案附件表
     **/
	public Attachment() {
	}

}