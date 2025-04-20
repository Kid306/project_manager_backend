package com.mdp.safe.client.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.mdp.core.utils.NumberUtil;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


/**

 */

public class User extends HashMap<String,Object> implements java.io.Serializable ,Cloneable {
	  
	private static final long serialVersionUID = 2645434826345664058L;

	@TableId(value = "userid")
	String userid;

	public User() {
	}

	/**
	 * 客户归属的机构,机构会员才有
	 * @param orgId
	 */
 	public void setOrgId(String orgId){
		super.put("orgId",orgId);
	}
	/**
	 * 客户归属的机构,机构会员才有
	 * @param orgName
	 */
	public void setOrgName(String orgName){
		super.put("orgName",orgName);
	}
	/**
	 * 客户归属的机构,机构会员才有
	 */
	public String getOrgName(){
		return (String) super.get("orgName");
	}
	/**
	 * 客户归属的机构,机构会员才有
	 */
	public String getOrgId(){
		return (String) super.get("orgId");
	}

	public void setMemType(String memType){
		super.put("memType",memType);
	}

	public String getMemType(){
		return (String) super.get("memType");
	}

	public void setUserType(String userType){
		super.put("userType",userType);
	}

	public String getUserType(){
		return (String) super.get("userType");
	}

	public String getTpaOpenid() {
		return (String) super.get("tpaOpenid");
	}

	public void setTpaOpenid(String tpaOpenid) {
		super.put("tpaOpenid",tpaOpenid);
	}
	public String getDisplayUserid() {
		return (String) super.get("displayUserid");
	}

	public void setDisplayUserid(String displayUserid) {
		super.put("displayUserid",displayUserid);
	}

	public String getUserid() {
		return (String) super.get("userid");
	}

	public void setUserid(String userid) {
		super.put("userid",userid);
		this.userid=userid;
	}

	public String getNickname() {
		return (String) super.get("nickname");
	}

	public void setNickname(String nickname) {
		super.put("nickname",nickname);
	}

	public String getUsername() {
		return (String) super.get("username");
	}

	public void setUsername(String username) {
		super.put("username",username);
	}

	public String getDeptid() {
		return (String) super.get("deptid");
	}

	public void setDeptid(String deptid) {
		Set<String> deptids=new HashSet<>();
		deptids.add(deptid);
		this.setDeptids(deptids);
		super.put("deptid",deptid);
	}

	public String getPdeptid() {
		return (String) super.get("pdeptid");
	}

	public void setPdeptid(String pdeptid) {
		super.put("pdeptid",pdeptid);
	}

	public String getPhoneno() {
		return (String) super.get("phoneno");
	}

	public void setPhoneno(String phoneno) {
		super.put("phoneno",phoneno);
	}

	public String getPassword() {
		return (String) super.get("password");
	}

	public void setPassword(String password) {
		super.put("password",password);
	}

	/**
	 * 密码强度
	 * 1-高风险，2-中风险，3-低风险
	 * @return
	 */
	public String getPwdStrong() {
		return (String) super.get("pwdStrong");
	}

	/**
	 * 密码强度
	 * 1-高风险，2-中风险，3-低风险
	 * @return
	 */
	public void setPwdStrong(String pwdStrong) {
		super.put("pwdStrong",pwdStrong);
	}

	public String getIdCardNo() {
		return (String) super.get("idCardNo");
	}

	public void setIdCardNo(String idCardNo) {
		super.put("idCardNo",idCardNo);
	}

	public String getHeadimgurl() {
		return (String) super.get("headimgurl");
	}

	public void setHeadimgurl(String headimgurl) {
		super.put("headimgurl",headimgurl);
	}

	public String getBranchId() {
		return (String) super.get("branchId");
	}

	public void setBranchId(String branchId) {
		Set<String> branchIds=new HashSet<>();
		branchIds.add(branchId);
		this.setBranchIds(branchIds);
		super.put("branchId",branchId);
	}

	public String getAtype() {
		return (String) super.get("atype");
	}

	public void setAtype(String atype) {
		super.put("atype",atype);
	}
	public String getBranchName() {
		return (String) super.get("branchName");
	}

	public void setBranchName(String branchName) {
		super.put("branchName",branchName);
	}

	public String getDeptName() {
		return (String) super.get("deptName");
	}

	public void setDeptName(String deptName) {
		super.put("deptName",deptName);
	}

	public String getShopId() {
		return (String) super.get("shopId");
	}

	public void setShopId(String shopId) {
		super.put("shopId",shopId);
	}

	public String getShopName() {
		return (String) super.get("shopName");
	}

	public void setShopName(String shopName) {
		super.put("shopName",shopName);
	}

	public String getLocationId() {
		return (String) super.get("locationId");
	}

	public void setLocationId(String locationId) {
		super.put("locationId",locationId);
	}

	public String getLocationName() {
		return (String) super.get("locationName");
	}

	public void setLocationName(String locationName) {
		super.put("locationName",locationName);
	}


	public String getCpaType() {
		return (String) super.get("cpaType");
	}

	public void setCpaType(String cpaType) {
		super.put("cpaType",cpaType);
	}

	public String getCpaOrg() {
		return (String) super.get("cpaOrg");
	}

	public void setCpaOrg(String cpaOrg) {
		super.put("cpaOrg",cpaOrg);
	}


	public void setCpaUserid(String cpaUserid) {
		super.put("cpaUserid",cpaUserid);
	}


	public String getCpaUserid() {
		return (String) super.get("cpaUserid");
	}

	public String getSex() {

		return (String) super.get("sex");
	}

	public void setSex(String sex) {
		super.put("sex",sex);
	}

	public String getCity() {

		return (String) super.get("city");
	}

	public void setCity(String city) {
		super.put("city",city);
	}

	public String getCountry() {

		return (String) super.get("country");
	}

	public void setCountry(String country) {
		super.put("country",country);
	}

	public String getUnionid() {
		return (String) super.get("unionid");
	}

	public void setUnionid(String unionid) {
		super.put("unionid",unionid);
	}

	public String getMdpAppid() {
		return (String) super.get("mdpAppid");
	}

	public void setMdpAppid(String mdpAppid) {
		super.put("mdpAppid",mdpAppid);
	}

	public String getEmail() {
		return (String) super.get("email");
	}

	public void setEmail(String email) {
		super.put("email",email);
	}


	public Set<String> getDeptids() {
		return (Set<String>) super.get("deptids");
	}

	public void setDeptids(Set<String> deptids) {
		super.put("deptids",deptids);
	}

	public Set<String> getBranchIds() {
		return (Set<String>) super.get("branchIds");
	}

	public void setBranchIds(Set<String> branchIds) {
		super.put("branchIds",branchIds);
	}

	public boolean hasBranchId(String branchId){
		Set<String> branchIdSet=this.getBranchIds();
		if(branchIdSet==null || branchIdSet.isEmpty()){
			return false;
		}
		return branchIdSet.contains(branchId);
	}

	public boolean hasDeptid(String deptid){
		Set<String> deptidSet=this.getDeptids();
		if(deptidSet==null || deptidSet.isEmpty()){
			return false;
		}
		return deptidSet.contains(deptid);
	}

	public String getGradeId() {
		return (String) super.get("gradeId");
	}

	public void setGradeId(String gradeId) {
		this.put("gradeId",gradeId);
	}

	public String getGradeName() {
		return (String) super.get("gradeName");
	}

	public void setGradeName(String gradeName) {
		this.put("gradeName",gradeName);
	}

	public String getIlvlId() {
		return (String) super.get("ilvlId");
	}

	public void setIlvlId(String ilvlId) {
		this.put("ilvlId",ilvlId);
	}

	public String getIlvlName() {
		return (String) super.get("ilvlName");
	}

	public void setIlvlName(String ilvlName) {
		this.put("ilvlName",ilvlName);
	}

	public String getIstatus() {
		return (String) super.get("istatus");
	}

	public void setIstatus(String istatus) {
		this.put("istatus",istatus);
	}

	public String getAuthId(){
		return (String) super.get("authId");
	}
	public void setAuthId(String authId){
		super.put("authId",authId);
	}

	/**
	 * 人工验证结果，当审核状态为2时，同步到sys_user表同一个字段，或者sys_branch同一个字段
	 * 验证级别列表逗号分割多个，0-验证不通过，1-验证通过 ，2待审核。按顺序位置分别代表1-实名（身份证），2-手机号码，3-邮箱，4-营业执照，5-法人实名
	 * 1,2,3,4,5
	 * 比如0,0,0,0,0所有验证都不通过。
	 * 比如1,1,1,1,1所有验证通过，
	 * 比如0,1,1,0,0代表实名身份证验证不通过，法人实名认证不通过
	 * 比如0,0,0,1,2代表实名认证待审核，企业法人实名认证待审核
	 * @return
	 */
	public void setValidLvls(String validLvls){
		super.put("validLvls",validLvls);
	}
	/**
	 * 人工验证结果，当审核状态为2时，同步到sys_user表同一个字段，或者sys_branch同一个字段
	 * 验证级别列表逗号分割多个，0-验证不通过，1-验证通过 ，2待审核。按顺序位置分别代表1-实名（身份证），2-手机号码，3-邮箱，4-营业执照，5-法人实名
	 * 1,2,3,4,5
	 * 比如0,0,0,0,0所有验证都不通过。
	 * 比如1,1,1,1,1所有验证通过，
	 * 比如0,1,1,0,0代表实名身份证验证不通过，法人实名认证不通过
	 * 比如0,0,0,1,2代表实名认证待审核，企业法人实名认证待审核
	 * @return
	 */
	public String getValidLvls(){
		return (String) super.get("validLvls");
	}

	/**
	 * 信用等级 D-C-B-A-S-SS-SSS
	 * @return
	 */
	public void setCreditId(String creditId){
		super.put("creditId",creditId);
	}
	/**
	 * 信用等级 D-C-B-A-S-SS-SSS
	 * @return
	 */
	public String getCreditId(){
		return (String) super.get("creditId");
	}

	/**
	 * 信用等级分数 D-C-B-A-S-SS-SSS每个等级对应不同的分数
	 * @return
	 */
	public void setCreditScore(Integer creditScore){
		super.put("creditScore",creditScore);
	}
	/**
	 * 信用等级分数 D-C-B-A-S-SS-SSS每个等级对应不同的分数
	 * @return
	 */
	public Integer getCreditScore(){
		return (Integer) super.get("creditScore");
	}

	/**
	 * 服务保障等级0-初始，1-金，2-银，3-铜
	 * @return
	 */
	public void setGuardId(String guardId){
		super.put("guardId",guardId);
	}
	/**
	 * 服务保障等级0-初始，1-金，2-银，3-铜
	 * @return
	 */
	public String getGuardId(){
		return (String) super.get("guardId");
	}

	/**
	 * userInfo= username|tpaOpenid|maxDataLvl|unionid|deptid|deptname|atype|branchId|branchName|authId|phoneno|memType|userType|orgId|orgName|displayUserid|headimgurl|shopId|locationId|showName|locationName|cpaType|cpaOrg|gradeId|ilvlId|istatus|validLvls|pwdStrong|creditId|guardId
	 * 分别为
	 * @return userInfo 这部分数据存放在jwt.userInfo中，主要是因为jwt太长了，尽量简短，传输必要数据
	 */
	public String toSimpleString(){

		String userInfo="%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s";
		userInfo= String.format(userInfo,
				notNullStr(this.getUsername(),""),
				notNullStr(this.getTpaOpenid(),""),
				notNullInteger(this.getMaxDataLvl(),1),
				notNullStr(this.getUnionid(),""),
 				notNullStr(this.getDeptid(),""),
				notNullStr(this.getDeptName(),""),
				notNullStr(this.getAtype(),""),
 				notNullStr(this.getBranchId(),""),
				notNullStr(this.getBranchName(),""),
				notNullStr(this.getAuthId(),""),
				notNullStr(this.getPhoneno(),""),
				notNullStr(this.getMemType(),""),
				notNullStr(this.getUserType(),""),
				notNullStr(this.getOrgId(),""),
				notNullStr(this.getOrgName(),""),
				notNullStr(this.getDisplayUserid(),""),
				notNullStr(this.getHeadimgurl(),""),
				notNullStr(this.getShopId(),""),
				notNullStr(this.getLocationId(),""),
				notNullStr(this.getShopName(),""),
				notNullStr(this.getLocationName(),""),
				notNullStr(this.getCpaType(),""),
				notNullStr(this.getCpaOrg(),""),
				notNullStr(this.getGradeId(),""),
				notNullStr(this.getIlvlId(),""),
				notNullStr(this.getIstatus(),""),
				notNullStr(this.getValidLvls(),""),
				notNullStr(this.getPwdStrong(),""),
				notNullStr(this.getCreditId(),""),
				notNullInteger(this.getCreditScore(),0),
				notNullStr(this.getGuardId(),"0"),
				notNullStr(this.getCpaUserid(),"")


		);
		return userInfo;
	}
	/**
	 * @return userInfo 这部分数据存放在jwt.userInfo中，主要是因为jwt太长了，尽量简短，传输必要数据
	 */
	public  void initByString(String userInfo){
		if(StringUtils.hasText(userInfo)){
			String[] exts=userInfo.split("\\|",-1);
			String username=notNullStr(exts[0],"");
			String tpaOpenid=notNullStr(exts[1],"");
			Integer maxDataLvl=notNullInteger(exts[2],1);
			String unionid=notNullStr(exts[3],"");
			String deptid=notNullStr(exts[4],"");

			String deptName=notNullStr(exts[5],"");
			String atype=notNullStr(exts[6],"");
			String branchId=notNullStr(exts[7],"");
			String branchName=notNullStr(exts[8],"");
			String authId=notNullStr(exts[9],"");
			String phoneno="";
			String memType="";
			String userType="";
			String orgId="";
			String orgName="";
			String displayUserid="";
			String headimgurl="";
			String shopId="";
			String locationId="";
			String shopName="";
			String locationName="";
			String cpaType="";
			String cpaOrg="";
			String gradeId="";
			String ilvlId="";
			String istatus="";
			String validLvls="";
			String pwdStrong="";
			String creditId="";
			Integer creditScore=0;
			String guardId="";
			String cpaUserid="";
			if(exts.length>10){
				phoneno=notNullStr(exts[10],"");
			}

			if(exts.length>11){
				memType=notNullStr(exts[11],"");
			}

			if(exts.length>12){
				userType=notNullStr(exts[12],"");
			}
			if(exts.length>13){
				orgId=notNullStr(exts[13],"");
			}
			if(exts.length>14){
				orgName=notNullStr(exts[14],"");
			}
			if(exts.length>15){
				displayUserid=notNullStr(exts[15],"");
			}

			if(exts.length>16){
				headimgurl=notNullStr(exts[16],"");
			}
			if(exts.length>17){
				shopId=notNullStr(exts[17],"");
			}

			if(exts.length>18){
				locationId=notNullStr(exts[18],"");
			}
			if(exts.length>19){
				shopName=notNullStr(exts[19],"");
			}

			if(exts.length>20){
				locationName=notNullStr(exts[20],"");
			}

			if(exts.length>21){
				cpaType=notNullStr(exts[21],"");
			}

			if(exts.length>22){
				cpaOrg=notNullStr(exts[22],"");
			}
			if(exts.length>23){
				gradeId=notNullStr(exts[23],"");
			}

			if(exts.length>24){
				ilvlId=notNullStr(exts[24],"");
			}

			if(exts.length>25){
				istatus=notNullStr(exts[25],"");
			}

			if(exts.length>26){
				validLvls=notNullStr(exts[26],"");
			}
			if(exts.length>27){
				pwdStrong=notNullStr(exts[27],"");
			}

			if(exts.length>28){
				creditId=notNullStr(exts[28],"");
			}
			if(exts.length>29){
				creditScore=notNullInteger(exts[29],0);
			}
			if(exts.length>30){
				guardId=notNullStr(exts[30],"0");
			}

			if(exts.length>31){
				cpaUserid=notNullStr(exts[31],"");
			}
			this.setUsername(username);
			this.setTpaOpenid(tpaOpenid);
			this.setMaxDataLvl(maxDataLvl);
			this.setUnionid(unionid);
			this.setDeptid(deptid);
			this.setDeptName(deptName);
			this.setAtype(atype);
			this.setBranchId(branchId);
			this.setBranchName(branchName);
			this.setAuthId(authId);
			this.setPhoneno(phoneno);
			if(!StringUtils.hasText(this.getUserid())){
				this.setUserid("");
			}
			this.setMemType(memType);
			this.setUserType(userType);
			this.setOrgId(orgId);
			this.setOrgName(orgName);
			this.setDisplayUserid(displayUserid);
			this.setHeadimgurl(headimgurl);
			this.setShopId(shopId);
			this.setLocationId(locationId);
			this.setShopName(shopName);
			this.setLocationName(locationName);
			this.setCpaType(cpaType);
			this.setCpaOrg(cpaOrg);
			this.setGradeId(gradeId);
			this.setIlvlId(ilvlId);
			this.setIstatus(istatus);
			this.setValidLvls(validLvls);
			this.setPwdStrong(pwdStrong);
			this.setCreditId(creditId);
			this.setCreditScore(creditScore);
			this.setGuardId(guardId);
			this.setCpaUserid(cpaUserid);
			//设置默认值
			if(!StringUtils.hasText(this.getBranchId())){
				if(StringUtils.hasText(this.getUserid())){
					this.setBranchId(this.getUserid());
					this.setBranchName(this.getUsername());
				}else{
					this.setBranchId("guest");
					this.setBranchName("游客");
				}
			}
			if(!StringUtils.hasText(this.getUserid())){
				this.setUserid("guest");
				this.setUsername("guest");
				this.setMemType("0");
			}
		}
	}

	public static String notNullStr(String value,String defaultValue){
		if(value==null){
			return defaultValue;
		}else return value;
	}
	public static Integer notNullInteger(Object value,Integer defaultValue){
		if(value==null){
			return defaultValue;
		}else {
			if(value instanceof Integer){
				return (Integer) value;
			}else {
				return Integer.parseInt(value.toString());
			}
		}
	}
	public static String join(Set<String> value,String defaultValue){
		if(value==null || value.isEmpty()){
			return defaultValue;
		}else {
			return String.join(",",value);
		}
	}



	public static void main(String[] args) {
		User user=new User();
		user.setUnionid("unionid");
		user.setUsername("unsername");
		user.setHeadimgurl("headimg");
		user.setDisplayUserid("displayUserid");
		user.setOrgName("orgName");
		user.setUserid("userid");
		user.setUserid("userid");
		user.setOrgId("orgId");
		user.setPhoneno("125555");
		user.setShopId("shopId001");
		user.setShopName("shopName002");
		user.setLocationId("locationId001");
		user.setLocationName("locationName");
		user.setAtype("atype001");
		user.setIlvlId("2");
		user.setValidLvls("0,0,0,0,1");
		user.setPwdStrong("1");
		user.setCreditId("A");
		user.setCreditScore(10);
		user.setGuardId("2");
		String userStr=user.toSimpleString();
		User user2=new User();
		user2.initByString(userStr);
		user2.setPwdStrong("2");
		user2.setValidLvls("1,1,0,0,1");
		String user2Str=user2.toSimpleString();
		User user3=new User();
		user3.initByString(user2Str);
		String user3Str=user3.toSimpleString();
		System.out.println(user3Str);

	}

	public Integer getMaxDataLvl() {
		return NumberUtil.getInteger(super.get("maxDataLvl"),0);
	}

	public void setMaxDataLvl(Integer maxDataLvl) {
		super.put("maxDataLvl",maxDataLvl);
	}
}