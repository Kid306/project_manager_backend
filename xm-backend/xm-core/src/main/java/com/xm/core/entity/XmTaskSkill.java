package  com.xm.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mdp.core.dao.annotation.TableIds;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author code-gen
 * @since 2023-10-3
 */
@Data
@TableName("xm_task_skill")
@ApiModel(description="任务技能关联表")
public class XmTaskSkill  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
    @TableIds
	
    @ApiModelProperty(notes="任务编号,主键",allowEmptyValue=true,example="",allowableValues="")
    String taskId;
    @TableIds
	
    @ApiModelProperty(notes="技能要求,主键",allowEmptyValue=true,example="",allowableValues="")
    String skillId;

	
	@ApiModelProperty(notes="技能名称",allowEmptyValue=true,example="",allowableValues="")
	String skillName;

	
	@ApiModelProperty(notes="技能分类",allowEmptyValue=true,example="",allowableValues="")
	String categoryId;

	/**
	 *任务编号,技能要求
	 **/
	public XmTaskSkill(String taskId,String skillId) {
		this.taskId = taskId;
		this.skillId = skillId;
	}
    
    /**
     * 任务技能关联表
     **/
	public XmTaskSkill() {
	}

}