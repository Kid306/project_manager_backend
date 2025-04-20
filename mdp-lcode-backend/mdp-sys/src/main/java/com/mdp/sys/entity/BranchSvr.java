package  com.mdp.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 组织 com  顶级模块 mdp 大模块 sys  小模块 <br> 
 * 实体 BranchSvr所有属性名: <br>
 *	"branchId","企业编号","id","服务编号","name","服务名称","remark","服务简介","price","服务价格","pimg","服务主图","times","服务时间范围","status","状态0初始1上架2下架","summary","简介";<br>
 * 当前主键(包括多主键):<br>
 *	id;<br>
 */
 @Data
@TableName("sys_branch_svr")
@ApiModel(description="企业服务列表")
public class BranchSvr  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(notes="服务编号,主键",allowEmptyValue=true,example="",allowableValues="")
	String id;
	
	@ApiModelProperty(notes="企业编号",allowEmptyValue=true,example="",allowableValues="")
	String branchId;
	
	@ApiModelProperty(notes="服务名称",allowEmptyValue=true,example="",allowableValues="")
	String name;
	
	@ApiModelProperty(notes="服务简介",allowEmptyValue=true,example="",allowableValues="")
	String remark;
	
	@ApiModelProperty(notes="服务价格",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal price;
	
	@ApiModelProperty(notes="服务主图",allowEmptyValue=true,example="",allowableValues="")
	String pimg;
	
	@ApiModelProperty(notes="服务时间范围",allowEmptyValue=true,example="",allowableValues="")
	String times;
	
	@ApiModelProperty(notes="状态0初始1上架2下架",allowEmptyValue=true,example="",allowableValues="")
	String status;
	
	@ApiModelProperty(notes="简介",allowEmptyValue=true,example="",allowableValues="")
	String summary;

	/**
	 *服务编号
	 **/
	public BranchSvr(String id) {
		this.id = id;
	}
    
    /**
     * 企业服务列表
     **/
	public BranchSvr() {
	}

}