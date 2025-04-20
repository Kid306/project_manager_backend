package com.mdp.core.entity;
 
import java.util.Date;

/**
 * Dept entity.<br>
 * 组织 com.qqkj<br>
 * 顶级模块 mdp<br>
 * 大模块 sys<br>
 * 小模块 <br>
 * 表 ADMIN.sys_dept sys_dept<br>
 * 实体 Dept<br>
 * 表是指数据库结构中的表,实体是指java类型中的实体类<br>
 * 当前实体所有属性名:<br>
 *	deptid,deptname,pdeptid,depttype,regionId,regionName,address,contactPerson,areaCode,telephone,mobilephone,sfax,email,state,createPerson,createDate,loperatePerson,loperateDate,deptManager,inChargeManager,isactive,isSubcompany,deptShortname;<br>
 * 当前表的所有字段名:<br>
 *	deptid,deptname,pdeptid,depttype,region_id,region_name,address,contact_person,area_code,telephone,mobilephone,sfax,email,state,create_person,create_date,loperate_person,loperate_date,dept_manager,in_charge_manager,isactive,is_subcompany,dept_shortname;<br>
 * 当前主键(包括多主键):<br>
 *	deptid;<br>
 */

public class Dept  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**deptid**/
	String deptid;
	
	/**deptname**/
	String deptname;
	
	/**pdeptid**/
	String pdeptid;
	
	/**公司类型0.集团总部1公司2部门3.洗车服务站**/
	String depttype;
	
	/**地区编码**/
	String regionId;
	
	/**地区名称**/
	String regionName;
	
	/**地址**/
	String address;
	
	/**联系人**/
	String contactPerson;
	
	/**电话区号**/
	String areaCode;
	
	/**电话**/
	String telephone;
	
	/**手机**/
	String mobilephone;
	
	/**传真**/
	String sfax;
	
	/**EMAIL**/
	String email;
	
	/**"部门状态:A正常，E无效**/
	String state;
	
	/**创建人**/
	String createPerson;
	
	/**创建日期**/
	Date createDate;
	
	/**最后操作人(新增的时候=创建人，其他时候=修改人)**/
	String loperatePerson;
	
	/**最后操作日期(新增的时候=创建日期，其他时候=修改日期)**/
	Date loperateDate;
	
	/**部门负责人**/
	String deptManager;
	
	/**公司分管领导**/
	String inChargeManager;
	
	/**是否激活**/
	Integer isactive;
	
	/**是否子公司**/
	Integer isSubcompany;
	
	/**简称**/
	String deptShortname;


	public Dept() {
	}

	public Dept(String deptid ,String deptname ,String pdeptid ,String depttype ,String regionId ,String regionName ,String address ,String contactPerson ,String areaCode ,String telephone ,String mobilephone ,String sfax ,String email ,String state ,String createPerson ,Date createDate ,String loperatePerson ,Date loperateDate ,String deptManager ,String inChargeManager ,Integer isactive ,Integer isSubcompany ,String deptShortname){
		this.deptid=deptid;
		this.deptname=deptname;
		this.pdeptid=pdeptid;
		this.depttype=depttype;
		this.regionId=regionId;
		this.regionName=regionName;
		this.address=address;
		this.contactPerson=contactPerson;
		this.areaCode=areaCode;
		this.telephone=telephone;
		this.mobilephone=mobilephone;
		this.sfax=sfax;
		this.email=email;
		this.state=state;
		this.createPerson=createPerson;
		this.createDate=createDate;
		this.loperatePerson=loperatePerson;
		this.loperateDate=loperateDate;
		this.deptManager=deptManager;
		this.inChargeManager=inChargeManager;
		this.isactive=isactive;
		this.isSubcompany=isSubcompany;
		this.deptShortname=deptShortname;
	}
	
	/**deptid**/
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
	/**deptname**/
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	/**pdeptid**/
	public void setPdeptid(String pdeptid) {
		this.pdeptid = pdeptid;
	}
	/**公司类型0.集团总部1公司2部门3.洗车服务站**/
	public void setDepttype(String depttype) {
		this.depttype = depttype;
	}
	/**地区编码**/
	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
	/**地区名称**/
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	/**地址**/
	public void setAddress(String address) {
		this.address = address;
	}
	/**联系人**/
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	/**电话区号**/
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	/**电话**/
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	/**手机**/
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
	/**传真**/
	public void setSfax(String sfax) {
		this.sfax = sfax;
	}
	/**EMAIL**/
	public void setEmail(String email) {
		this.email = email;
	}
	/**"部门状态:A正常，E无效**/
	public void setState(String state) {
		this.state = state;
	}
	/**创建人**/
	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}
	/**创建日期**/
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**最后操作人(新增的时候=创建人，其他时候=修改人)**/
	public void setLoperatePerson(String loperatePerson) {
		this.loperatePerson = loperatePerson;
	}
	/**最后操作日期(新增的时候=创建日期，其他时候=修改日期)**/
	public void setLoperateDate(Date loperateDate) {
		this.loperateDate = loperateDate;
	}
	/**部门负责人**/
	public void setDeptManager(String deptManager) {
		this.deptManager = deptManager;
	}
	/**公司分管领导**/
	public void setInChargeManager(String inChargeManager) {
		this.inChargeManager = inChargeManager;
	}
	/**是否激活**/
	public void setIsactive(Integer isactive) {
		this.isactive = isactive;
	}
	/**是否子公司**/
	public void setIsSubcompany(Integer isSubcompany) {
		this.isSubcompany = isSubcompany;
	}
	/**简称**/
	public void setDeptShortname(String deptShortname) {
		this.deptShortname = deptShortname;
	}
	
	/** deptid**/
	public String getDeptid() {
		return this.deptid;
	}
	/** deptname**/
	public String getDeptname() {
		return this.deptname;
	}
	/** pdeptid**/
	public String getPdeptid() {
		return this.pdeptid;
	}
	/** 公司类型0.集团总部1公司2部门3.洗车服务站**/
	public String getDepttype() {
		return this.depttype;
	}
	/** 地区编码**/
	public String getRegionId() {
		return this.regionId;
	}
	/** 地区名称**/
	public String getRegionName() {
		return this.regionName;
	}
	/** 地址**/
	public String getAddress() {
		return this.address;
	}
	/** 联系人**/
	public String getContactPerson() {
		return this.contactPerson;
	}
	/** 电话区号**/
	public String getAreaCode() {
		return this.areaCode;
	}
	/** 电话**/
	public String getTelephone() {
		return this.telephone;
	}
	/** 手机**/
	public String getMobilephone() {
		return this.mobilephone;
	}
	/** 传真**/
	public String getSfax() {
		return this.sfax;
	}
	/** EMAIL**/
	public String getEmail() {
		return this.email;
	}
	/** "部门状态:A正常，E无效**/
	public String getState() {
		return this.state;
	}
	/** 创建人**/
	public String getCreatePerson() {
		return this.createPerson;
	}
	/** 创建日期**/
	public Date getCreateDate() {
		return this.createDate;
	}
	/** 最后操作人(新增的时候=创建人，其他时候=修改人)**/
	public String getLoperatePerson() {
		return this.loperatePerson;
	}
	/** 最后操作日期(新增的时候=创建日期，其他时候=修改日期)**/
	public Date getLoperateDate() {
		return this.loperateDate;
	}
	/** 部门负责人**/
	public String getDeptManager() {
		return this.deptManager;
	}
	/** 公司分管领导**/
	public String getInChargeManager() {
		return this.inChargeManager;
	}
	/** 是否激活**/
	public Integer getIsactive() {
		return this.isactive;
	}
	/** 是否子公司**/
	public Integer getIsSubcompany() {
		return this.isSubcompany;
	}
	/** 简称**/
	public String getDeptShortname() {
		return this.deptShortname;
	}

}