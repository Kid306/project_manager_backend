package com.mdp.safe.client.entity;

import com.mdp.core.dao.annotation.TableIds;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 组织 com  顶级模块 mdp 大模块 menu  小模块 <br> 
 * 实体 MenuModuleBranch所有属性名: <br>
 *	branchId,moduleName,moduleId,startTime,endTime,ctime,ltime,cuserid,cusername,luserid,lusername,status,musers,mbidNum,sfeeRate,ver,feeDate,crowd;<br>
 * 表 menu_module_branch 机构开通模块对应关系表的所有字段名: <br>
 *	branch_id,module_name,module_id,start_time,end_time,ctime,ltime,cuserid,cusername,luserid,lusername,status,musers,mbid_num,sfee_rate,ver,fee_date,crowd;<br>
 * 当前主键(包括多主键):<br>
 *	branch_id,module_id;<br>
 */
@Data
@Schema(description="机构开通模块对应关系表")
public class ModuleBranch implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@TableIds
	@Schema(description="机构编号,主键")
	String branchId;
	@TableIds
	@Schema(description="模块编号,主键")
	String moduleId;


	@Schema(description="模块名称")
	String moduleName;

	@Schema(description="启用日期")
	Date startTime;

	@Schema(description="结束日期")
	Date endTime;

	@Schema(description="创建日期")
	Date ctime;

	@Schema(description="更新日期")
	Date ltime;

	@Schema(description="创建人编号")
	String cuserid;

	@Schema(description="创建人姓名")
	String cusername;

	@Schema(description="修改人编号")
	String luserid;

	@Schema(description="修改人姓名")
	String lusername;

	@Schema(description="状态0停用1启用")
	String status;

	@Schema(description="购买用户数")
	Integer musers;

	@Schema(description="可投标次数-每月恢复为套餐数量，包含公司账户次数+个人以组织名义接单的次数，以上每接一单扣减此处")
	Integer mbidNum;

	@Schema(description="服务费率，15=15%")
	Integer sfeeRate;

	@Schema(description="企业购买的版本0-免费版，1-企业版")
	String ver;

	@Schema(description="开始计费日期")
	Date feeDate;

	@Schema(description="是否为众包")
	String crowd;

}