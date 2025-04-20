package  com.mdp.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mdp.core.dao.annotation.TableIds;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 组织 com  顶级模块 mdp 大模块 sys  小模块 <br> 
 * 实体 UserSkill所有属性名: <br>
 *	"userid","用户编号","skillId","技能编号","skillName","技能名称","categoryId","技能分类";<br>
 * 当前主键(包括多主键):<br>
 *	userid,skill_id;<br>
 */
 @Data
@TableName("sys_user_skill")
@ApiModel(description="该表属于oa.hr_user_skill的冗余表，避免跨库查询")
public class UserSkill  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
    @TableIds
    @ApiModelProperty(notes="用户编号,主键",allowEmptyValue=true,example="",allowableValues="")
    String userid;
    @TableIds
    @ApiModelProperty(notes="技能编号,主键",allowEmptyValue=true,example="",allowableValues="")
    String skillId;
	
	@ApiModelProperty(notes="技能名称",allowEmptyValue=true,example="",allowableValues="")
	String skillName;
	
	@ApiModelProperty(notes="技能分类",allowEmptyValue=true,example="",allowableValues="")
	String categoryId;

	/**
	 *用户编号,技能编号
	 **/
	public UserSkill(String userid,String skillId) {
		this.userid = userid;
		this.skillId = skillId;
	}
    
    /**
     * 该表属于oa.hr_user_skill的冗余表，避免跨库查询
     **/
	public UserSkill() {
	}

}