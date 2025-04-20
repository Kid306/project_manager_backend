package com.mdp.dev.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author code-gen
 * @since 2023-9-20
 */
@Data
@TableName("arc_attachment")
@Schema(description="档案附件表")
public class DevFile implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	
	@Schema(description="主键,主键")
	String id;

	
	@Schema(description="附件名称")
	String name;

	
	@Schema(description="访问路径")
	String url;

	
	@Schema(description="硬盘存放路径")
	String relativePath;

	
	@Schema(description="后缀名")
	String fileSuffix;

	
	@Schema(description="内容加速器访问路径")
	String cdnUrl;

	
	@Schema(description="是否图片")
	String isImg;

	
	@Schema(description="档案主编号")
	String archiveId;

	
	@Schema(description="是否使用CDN")
	String isCdn;

	
	@Schema(description="根目录")
	String rootPath;

	
	@Schema(description="存入时间")
	Date createDate;

	
	@Schema(description="是否可下载")
	String canDown;

	
	@Schema(description="是否可删除")
	String canDel;

	
	@Schema(description="是否可读")
	String canRead;

	
	@Schema(description="业务编号、产品编号、商品编号等")
	String bizId;

	
	@Schema(description="业务名称、产品名称、商品名称等")
	String remark;

	
	@Schema(description="存储名字")
	String storeName;

	
	@Schema(description="文件大小")
	BigDecimal fileSize;

	
	@Schema(description="云用户机构编号")
	String branchId;

	
	@Schema(description="部门编号")
	String deptid;

	
	@Schema(description="0|知识库")
	String archiveType;

	
	@Schema(description="分类编号")
	String categoryId;

	
	@Schema(description="参考类型")
	String relyType;

	
	@Schema(description="参考编号")
	String relyId;

	
	@Schema(description="参考子类型")
	String relyStype;

	
	@Schema(description="参考子编号")
	String relySid;

	
	@Schema(description="创建人编号")
	String cuserid;

	
	@Schema(description="创建人姓名")
	String cusername;

	
	@Schema(description="创建时间")
	Date cdate;

	
	@Schema(description="扩展字段")
	String extInfos;

	
	@Schema(description="标签")
	String tagIds;

	/**
	 *主键
	 **/
	public DevFile(String id) {
		this.id = id;
	}
    
    /**
     * 档案附件表
     **/
	public DevFile() {
	}

}