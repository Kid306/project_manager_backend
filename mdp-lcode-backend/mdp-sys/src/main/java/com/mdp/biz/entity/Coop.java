package  com.mdp.biz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 组织 com  顶级模块 mdp 大模块 biz  小模块 <br> 
 * 实体 Coop所有属性名: <br>
 *	"id","主键","userid","用户编号","username","用户名称","branchId","公司编号","branchName","公司名称","email","邮箱","phoneno","电话","remark","合作要求","status","状态0-初始，1-申请中，2-进行中，3-已结束","ctime","申请日期","replyUserid","根进人编号","replyUsername","跟进人姓名","replyTime","跟进时间","bizType","合作类型1-项目合作，2-广告合作，3-服务商入驻，4-校企合作";<br>
 * 当前主键(包括多主键):<br>
 *	id;<br>
 */
 @Data
@TableName("biz_coop")
@ApiModel(description="商务合作申请")
public class Coop  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(notes="主键,主键",allowEmptyValue=true,example="",allowableValues="")
	String id;
	
	@ApiModelProperty(notes="用户编号",allowEmptyValue=true,example="",allowableValues="")
	String userid;

	@ApiModelProperty(notes="用户名称",allowEmptyValue=true,example="",allowableValues="")
	String username;
	
	@ApiModelProperty(notes="公司编号",allowEmptyValue=true,example="",allowableValues="")
	String branchId;
	
	@ApiModelProperty(notes="公司名称",allowEmptyValue=true,example="",allowableValues="")
	String branchName;
	
	@ApiModelProperty(notes="邮箱",allowEmptyValue=true,example="",allowableValues="")
	String email;
	
	@ApiModelProperty(notes="电话",allowEmptyValue=true,example="",allowableValues="")
	String phoneno;
	
	@ApiModelProperty(notes="合作要求",allowEmptyValue=true,example="",allowableValues="")
	String remark;
	
	@ApiModelProperty(notes="状态0-初始，1-申请中，2-进行中，3-已结束",allowEmptyValue=true,example="",allowableValues="")
	String status;
	
	@ApiModelProperty(notes="申请日期",allowEmptyValue=true,example="",allowableValues="")
	Date ctime;
	
	@ApiModelProperty(notes="根进人编号",allowEmptyValue=true,example="",allowableValues="")
	String replyUserid;
	
	@ApiModelProperty(notes="跟进人姓名",allowEmptyValue=true,example="",allowableValues="")
	String replyUsername;
	
	@ApiModelProperty(notes="跟进时间",allowEmptyValue=true,example="",allowableValues="")
	Date replyTime;
	
	@ApiModelProperty(notes="合作类型1-项目合作，2-广告合作，3-服务商入驻，4-校企合作",allowEmptyValue=true,example="",allowableValues="")
	String bizType;

	/**
	 *主键
	 **/
	public Coop(String id) {
		this.id = id;
	}
    
    /**
     * 商务合作申请
     **/
	public Coop() {
	}

}