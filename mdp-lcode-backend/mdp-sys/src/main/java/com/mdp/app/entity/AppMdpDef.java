package  com.mdp.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 组织 com  顶级模块 mdp 大模块 app  小模块 <br> 
 * 实体 AppMdpDef所有属性名: <br>
 *	"mdpAppid","应用编号","name","应用名称","logoUrl","应用logo","remark","应用描述","bizType","应用分类","deptid","归属部门","branchId","归属机构","cdate","创建日期","cuserid","创建人","enabled","应用状态0下架1上架","url","首页跳转";<br>
 * 当前主键(包括多主键):<br>
 *	mdp_appid;<br>
 */
 @Data
@TableName("app_mdp_def")
@ApiModel(description="MDP平台应用定义表")
public class AppMdpDef  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(notes="应用编号,主键",allowEmptyValue=true,example="",allowableValues="")
	String mdpAppid;
	
	@ApiModelProperty(notes="应用名称",allowEmptyValue=true,example="",allowableValues="")
	String name;
	
	@ApiModelProperty(notes="应用logo",allowEmptyValue=true,example="",allowableValues="")
	String logoUrl;
	
	@ApiModelProperty(notes="应用描述",allowEmptyValue=true,example="",allowableValues="")
	String remark;
	
	@ApiModelProperty(notes="应用分类",allowEmptyValue=true,example="",allowableValues="")
	String bizType;
	
	@ApiModelProperty(notes="归属部门",allowEmptyValue=true,example="",allowableValues="")
	String deptid;
	
	@ApiModelProperty(notes="归属机构",allowEmptyValue=true,example="",allowableValues="")
	String branchId;
	
	@ApiModelProperty(notes="创建日期",allowEmptyValue=true,example="",allowableValues="")
	Date cdate;
	
	@ApiModelProperty(notes="创建人",allowEmptyValue=true,example="",allowableValues="")
	String cuserid;
	
	@ApiModelProperty(notes="应用状态0下架1上架",allowEmptyValue=true,example="",allowableValues="")
	String enabled;
	
	@ApiModelProperty(notes="首页跳转",allowEmptyValue=true,example="",allowableValues="")
	String url;

	/**
	 *应用编号
	 **/
	public AppMdpDef(String mdpAppid) {
		this.mdpAppid = mdpAppid;
	}
    
    /**
     * MDP平台应用定义表
     **/
	public AppMdpDef() {
	}

}