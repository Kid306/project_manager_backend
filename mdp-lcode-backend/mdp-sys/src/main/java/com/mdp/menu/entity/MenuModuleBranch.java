package  com.mdp.menu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mdp.core.dao.annotation.TableIds;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 组织 com  顶级模块 mdp 大模块 menu  小模块 <br> 
 * 实体 MenuModuleBranch所有属性名: <br>
 *	"branchId","机构编号","moduleName","模块名称","moduleId","模块编号","startTime","启用日期","endTime","结束日期","ctime","创建日期","ltime","更新日期","cuserid","创建人编号","cusername","创建人姓名","luserid","修改人编号","lusername","修改人姓名","status","状态0停用1启用2待续费","musers","购买用户数","mbidNum","可投标次数-每月恢复为套餐数量，包含公司账户次数+个人以组织名义接单的次数，以上每接一单扣减此处","sfeeRate","服务费率，15=15%","ver","购买版本0-免费版，1-企业版","feeDate","开始计费日期","crowd","是否为众包","ousers","企业总人数","udisRate","人数折算比例。企业总人数*折扣率为当前模块购买人数","udis","是否折算人数0否1是，按企业总人数*人数折算比例计算","mcate","分类1-协同、2-研发、3-电商";<br>
 * 当前主键(包括多主键):<br>
 *	branch_id,module_id;<br>
 */
 @Data
@TableName("menu_module_branch")
@ApiModel(description="机构开通模块对应关系表")
public class MenuModuleBranch  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
    @TableIds
    @ApiModelProperty(notes="机构编号,主键",allowEmptyValue=true,example="",allowableValues="")
    String branchId;
    @TableIds
    @ApiModelProperty(notes="模块编号,主键",allowEmptyValue=true,example="",allowableValues="")
    String moduleId;
	
	@ApiModelProperty(notes="模块名称",allowEmptyValue=true,example="",allowableValues="")
	String moduleName;
	
	@ApiModelProperty(notes="启用日期",allowEmptyValue=true,example="",allowableValues="")
	Date startTime;
	
	@ApiModelProperty(notes="结束日期",allowEmptyValue=true,example="",allowableValues="")
	Date endTime;
	
	@ApiModelProperty(notes="创建日期",allowEmptyValue=true,example="",allowableValues="")
	Date ctime;
	
	@ApiModelProperty(notes="更新日期",allowEmptyValue=true,example="",allowableValues="")
	Date ltime;
	
	@ApiModelProperty(notes="创建人编号",allowEmptyValue=true,example="",allowableValues="")
	String cuserid;
	
	@ApiModelProperty(notes="创建人姓名",allowEmptyValue=true,example="",allowableValues="")
	String cusername;
	
	@ApiModelProperty(notes="修改人编号",allowEmptyValue=true,example="",allowableValues="")
	String luserid;
	
	@ApiModelProperty(notes="修改人姓名",allowEmptyValue=true,example="",allowableValues="")
	String lusername;
	
	@ApiModelProperty(notes="状态0停用1启用2待续费",allowEmptyValue=true,example="",allowableValues="")
	String status;
	
	@ApiModelProperty(notes="购买用户数",allowEmptyValue=true,example="",allowableValues="")
	Integer musers;
	
	@ApiModelProperty(notes="可投标次数-每月恢复为套餐数量，包含公司账户次数+个人以组织名义接单的次数，以上每接一单扣减此处",allowEmptyValue=true,example="",allowableValues="")
	Integer mbidNum;
	
	@ApiModelProperty(notes="服务费率，15=15%",allowEmptyValue=true,example="",allowableValues="")
	Integer sfeeRate;
	
	@ApiModelProperty(notes="购买版本0-免费版，1-企业版",allowEmptyValue=true,example="",allowableValues="")
	String ver;
	
	@ApiModelProperty(notes="开始计费日期",allowEmptyValue=true,example="",allowableValues="")
	Date feeDate;
	
	@ApiModelProperty(notes="是否为众包",allowEmptyValue=true,example="",allowableValues="")
	String crowd;
	
	@ApiModelProperty(notes="企业总人数",allowEmptyValue=true,example="",allowableValues="")
	Integer ousers;
	
	@ApiModelProperty(notes="人数折算比例。企业总人数*折扣率为当前模块购买人数",allowEmptyValue=true,example="",allowableValues="")
	Integer udisRate;
	
	@ApiModelProperty(notes="是否折算人数0否1是，按企业总人数*人数折算比例计算",allowEmptyValue=true,example="",allowableValues="")
	String udis;
	
	@ApiModelProperty(notes="分类1-协同、2-研发、3-电商",allowEmptyValue=true,example="",allowableValues="")
	String mcate;

	/**
	 *机构编号,模块编号
	 **/
	public MenuModuleBranch(String branchId,String moduleId) {
		this.branchId = branchId;
		this.moduleId = moduleId;
	}
    
    /**
     * 机构开通模块对应关系表
     **/
	public MenuModuleBranch() {
	}

}