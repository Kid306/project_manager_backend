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
 * 实体 DeptLocation所有属性名: <br>
 *	"deptid","部门地址编号","longitude","经度","latitude","纬度","address","地址","province","省","city","市","district","区";<br>
 * 当前主键(包括多主键):<br>
 *	deptid;<br>
 */
 @Data
@TableName("sys_dept_location")
@ApiModel(description="sys_dept_location")
public class DeptLocation  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(notes="部门地址编号,主键",allowEmptyValue=true,example="",allowableValues="")
	String deptid;
	
	@ApiModelProperty(notes="经度",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal longitude;
	
	@ApiModelProperty(notes="纬度",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal latitude;
	
	@ApiModelProperty(notes="地址",allowEmptyValue=true,example="",allowableValues="")
	String address;
	
	@ApiModelProperty(notes="省",allowEmptyValue=true,example="",allowableValues="")
	String province;
	
	@ApiModelProperty(notes="市",allowEmptyValue=true,example="",allowableValues="")
	String city;
	
	@ApiModelProperty(notes="区",allowEmptyValue=true,example="",allowableValues="")
	String district;

	/**
	 *部门地址编号
	 **/
	public DeptLocation(String deptid) {
		this.deptid = deptid;
	}
    
    /**
     * sys_dept_location
     **/
	public DeptLocation() {
	}

}