package com.mdp.safe.client.qx;

import com.mdp.core.entity.Tips;
import com.mdp.core.err.QxException;
import com.mdp.core.utils.ObjectTools;
import com.mdp.core.utils.ReflectHelper;
import com.mdp.qx.DataLvl;
import com.mdp.qx.DeptFilter;
import com.mdp.qx.ResourceType;
import com.mdp.safe.client.cache.DeptRedisCacheService;
import com.mdp.safe.client.entity.Dept;
import com.mdp.safe.client.entity.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class DeptFilterCheckService {
	
	@Autowired
    DeptRedisCacheService deptMapService;
	
    public Tips around (ProceedingJoinPoint pjp, int myMaxDataLvl, User u, DeptFilter deptFilter, boolean branchOnly) throws Throwable{

		int ignoreLvl=deptFilter.ignore().getLvl();
		int minLvl=deptFilter.min().getLvl();
		if(minLvl>myMaxDataLvl) {
			throw new QxException("qxCode01",ResourceType.deptScope.name(),"权限不足,您的数据权限未达到最低要求");
		}
		if(myMaxDataLvl<ignoreLvl){
			myMaxDataLvl=ignoreLvl;
		}
		String userBranchId=u.getBranchId(); 
		if(StringUtils.isEmpty(userBranchId)){
			throw new QxException("qxCode02",ResourceType.deptScope.name(),"权限不足,您不在任何一个单位或部门中");
		}
			  
		Tips tips=new Tips("成功");
		//tips.setFailureMsg("数据访问权限不足");
		if( myMaxDataLvl>=DataLvl.allowAll.getLvl()){
			return tips;
		}
		if( myMaxDataLvl==DataLvl.forbiddenAll.getLvl()){
			throw new QxException("权限不足，您已经被禁止访问所有数据！");
		}
		String argBranchId=null;
		String argDeptid=null;
		Dept argDeptVo=null;

		//这里如果我的级别是机构级别的，只需要判断机构是不是我归属的机构即可，后续的判断就免了。
		if(myMaxDataLvl == DataLvl.branch.getLvl()||deptFilter.onlySameBranchCheck()){
			try {
				argBranchId=this.getArgBranchId(pjp, deptFilter);
			} catch (Exception e) {
 			}
			if(!StringUtils.hasText(argBranchId)){//通过参数获取不到机构号，则通过部门关联获取
				argDeptid=this.getArgDeptid(pjp, deptFilter);
				if(!StringUtils.hasText(argDeptid)){
					throw new QxException("上送的参数中未找到部门机构号字段["+deptFilter.branchFieldName()+"],也没找到部门编号字段["+deptFilter.deptFieldName()+"],请检查程序代码");
				}else{
					argDeptVo=deptMapService.getDept(argDeptid);
					if(argDeptVo==null) {
						throw new QxException("未能查到部门["+argDeptid+"]的明细信息，不允许操作");
					}
					argBranchId=argDeptVo.getBranchId();
				}
			}
			Set<String> branchIdSet=u.getBranchIds();
			for (String branchId : branchIdSet) {//只要有一个机构检测成功，则认为成功
				tips=this.branchQx(branchId,argBranchId);
				if(tips.isOk()){
					return tips;
				}
			}
			return tips;
		}else { //myMaxDataLvl < DataLvl.branch

			try {
				argDeptid=this.getArgDeptid(pjp, deptFilter);
			} catch (Exception e) {

			}
			if(!StringUtils.hasText(argDeptid)) {
				if(deptFilter.rejectOnDeptidIsNull()==true){
					throw new QxException("上送的参数中未找到部门编号字段["+deptFilter.deptFieldName()+"],请检查程序代码");
				}else{
					//tips.setOkMsg("成功");
					return tips;
				}

			}

			// 处理自己查自己，而且我的等级也允许，所以后面的判断就免了吧。省点力气。
			if(myMaxDataLvl >= DataLvl.myDept.getLvl()){// 如果我的级别大于 myDept级别

				if( u.hasDeptid(argDeptid) ){//自己查自己，而且我的等级也允许，所以后面的判断就免了吧。省点力气。
					tips.setOkMsg("成功");
					return tips;
				}
			}else{
				if( u.hasDeptid(argDeptid) ){//自己查自己，而且我的等级不允许，则权限检测不通过。
					argDeptVo=deptMapService.getDept(argDeptid);
					if(argDeptVo==null) {
						throw new QxException("未能查到部门["+argDeptid+"]的明细信息，不允许操作");
					}
					tips.setErrMsg("权限不足，您当前权限等级无法查询部门【"+argDeptVo.getDeptName()+"】数据");
					return tips;
				}
			}
			argDeptVo=deptMapService.getDept(argDeptid);
			if(argDeptVo==null) {
				throw new QxException("未能查到部门["+argDeptid+"]的明细信息，不允许操作");
			}
			Set<String> deptidSet=u.getDeptids();
			for (String userDeptid : deptidSet) {//只要有一个检查通过则认为通过
				tips.setErrMsg("部门权限检查不通过");
				Dept userDeptVo=deptMapService.getDept(userDeptid);
				if(userDeptVo==null){
					tips.setErrMsg("未能查到部门["+userDeptid+"]的明细信息，不允许操作");
					continue;
					//throw new QxException("未能查到部门["+userDeptid+"]的明细信息，不允许操作");
				}
				if(!argDeptVo.getBranchId().equals(userDeptVo.getBranchId())){
					tips.setErrMsg("不能跨机构查询数据");
					continue;
				}
				if(myMaxDataLvl>= DataLvl.p2.getLvl()){
					tips=checkDeptLvl123(userDeptVo,argDeptVo,2);
				}else if(myMaxDataLvl>= DataLvl.p1.getLvl()){
					tips=checkDeptLvl123(userDeptVo,argDeptVo,1);
				}else if(myMaxDataLvl>= DataLvl.subDept.getLvl()){
					tips=checkDeptLvl123(userDeptVo,argDeptVo,0);
				}else if(myMaxDataLvl>= DataLvl.myDept.getLvl()){
					if( !userDeptid.equals(argDeptid)){
						tips.setErrMsg("您无权限操作部门【"+argDeptVo.getDeptName()+"】的数据");
					}else {
						return tips;
					}
				}
				if(tips.isOk()){
					return tips;
				}
			}
			return tips;
		}
    }
    
    String getArgDeptid(ProceedingJoinPoint pjp,DeptFilter deptFilter){
    	Object[] args=pjp.getArgs();
		Object arg=args[deptFilter.index()];
		String deptidArg="";
		String deptFieldName=deptFilter.deptFieldName();
		String pdeptFieldName=deptFilter.pdeptFieldName();
		if(arg==null) {
			return null;
		}
		if(arg instanceof Map){
			Map argMap=(Map)arg;
			if(argMap.containsKey(deptFieldName)) {
				deptidArg=(String) argMap.get(deptFieldName);
			} 
			if( StringUtils.isEmpty(deptidArg) && deptFilter.checkPdept()  && argMap.containsKey(pdeptFieldName)) {
				deptidArg=(String) argMap.get(pdeptFieldName);
			} 
		}else if(arg instanceof String){
			deptidArg=(String) arg;
		}else if(arg instanceof List){
			List argList=(List)arg;
			if(argList.size()>0) {
				Object arg0=argList.get(0);
				if(arg0 instanceof Map){
					Map arg0Map=(Map)arg;
					if(arg0Map.containsKey(deptFieldName)) {
						deptidArg=(String) arg0Map.get(deptFieldName);
					} 
					if( StringUtils.isEmpty(deptidArg) && deptFilter.checkPdept()  && arg0Map.containsKey(pdeptFieldName)) {
						deptidArg=(String) arg0Map.get(pdeptFieldName);
					} 
				}else if(arg0 instanceof String){
					deptidArg=(String) arg0;
				}else{
					try {
						deptidArg=(String) ReflectHelper.getValueByFieldName(arg0, deptFieldName);
						if(StringUtils.isEmpty(deptidArg)) {
							if(deptFilter.checkPdept()) {
								deptidArg=(String) ReflectHelper.getValueByFieldName(arg0,pdeptFieldName); 
							}
						}
					} catch (Exception e) {
						throw new QxException("qxCode",ResourceType.deptScope.name(),"部门参数获取错误[deptFieldName:"+deptFieldName+",pdeptFieldName:"+pdeptFieldName+",index:"+deptFilter.index()+"]，请检查 HasLvl 中的deptFieldName,pdeptFieldName,index 配置是否正确，"); 
					}
					
				}
			}else {
				throw new QxException("qxCode",ResourceType.deptScope.name(),"部门参数获取错误[deptFieldName:"+deptFieldName+",pdeptFieldName:"+pdeptFieldName+",index:"+deptFilter.index()+"]，请检查 HasLvl 中的deptFieldName,pdeptFieldName,index 配置是否正确，"); 
			}
		}else{
			try {
				deptidArg=(String) ReflectHelper.getValueByFieldName(arg, deptFieldName); 
				if(StringUtils.isEmpty(deptidArg)) {
					if(deptFilter.checkPdept()) {
						deptidArg=(String) ReflectHelper.getValueByFieldName(arg,pdeptFieldName); 
					}
				}
			} catch (Exception e) {
				throw new QxException("qxCode",ResourceType.deptScope.name(),"部门参数获取错误[deptFieldName:"+deptFieldName+",pdeptFieldName:"+pdeptFieldName+",index:"+deptFilter.index()+"]，请检查 HasLvl 中的deptFieldName,pdeptFieldName,index 配置是否正确，"); 
			}
			
		}
		return deptidArg;
    }
    
    Tips branchQx(String userBranchId,String argBranchId) throws Throwable{
    	Tips tips=new Tips("验证权限成功");  
    	if(StringUtils.isEmpty(userBranchId)) {
    		 tips.setErrMsg("您不是任何机构用户，无权操作机构数据");
    		 return tips;
    	}
    	if(StringUtils.isEmpty(argBranchId)) {
    		tips.setErrMsg("机构参数为空，您无权操作");
    		return tips;
    	}
		if( userBranchId.equals(argBranchId)){
			return tips;
		}else{
			tips.setErrMsg("不能跨单位操作数据");
			return tips;
		}
    }

    /**
     * 
     * @param userDeptVo
     * @param argDeptVo
     * @param pNo p3=3,p2=2,p1=1,p0=0
     * @return
     */
    Tips checkDeptLvl123(Dept userDeptVo, Dept argDeptVo, int pNo){
    	Tips tips=new Tips("验证权限成功");
    	if(argDeptVo==null   ){
    		tips.setErrMsg("部门数据有误");
    		return tips;
    	}
    	String argDeptName=argDeptVo.getDeptName();
    	List<String> puserDeptids= ObjectTools.isNotEmpty(userDeptVo.getIdPath())?new ArrayList<>(): Arrays.asList(userDeptVo.getIdPath().split(","));
    	List<String> pargDeptids=  ObjectTools.isNotEmpty(argDeptVo.getIdPath())?new ArrayList<>(): Arrays.asList(argDeptVo.getIdPath().split(","));
    	int myParentlength=puserDeptids.size(); //我的上级个数
    	int parentIndex=myParentlength-pNo; // 要匹配的上级下标
		//如果< 0 ，说明我的权限达到巅峰，任何对手都是我的势力范围
		if(parentIndex<0){
			return tips;
		}
    	int pargLength=pargDeptids.size();//要查的部门的上级个数
    	if(pargLength < parentIndex ){// 代表要查的部门所在的下标在我的下标之上，而且超出了pNo 个位置。也就超出了我的权限范围。
    		tips.setErrMsg("您没有权限操作部门【"+argDeptName+"】的数据");
    		return tips;
    	}

    	//领导都不一样，说明咱不是一路人，领导一样，说明要么是下属，要么是兄弟部门
    	for (int i =0; i < parentIndex; i++) { //上级之间pk，如果存在不一样的，代表咱不是一路人
    		if(!puserDeptids.get(i).equals(pargDeptids.get(i))){
    			tips.setErrMsg("您没有权限操作部门【"+argDeptName+"】的数据");
    			return tips;
    		}
		}

		// 到这里，领导都一样的了，这里处理兄弟部门情况 表名他是我的兄弟部门的本级或者兄弟部门的下属单位，只能查我自己部门的数据的我没有权限查兄弟
		//他的领导比我多，或者跟我一样多。领导跟我一样多的话，他是我的兄弟部门。领导比我多的话，他有可能是我的下属部门或者我的兄弟部门
		//如果我在他的领导名单中，说明他是我的辖内单位
		boolean argDeptidIsMyChild=false;
		for (String pargDeptid : pargDeptids) {
			if(pargDeptid.equals(userDeptVo.getDeptid())){
				argDeptidIsMyChild=true;
				break;
			}
		}
		if( !argDeptidIsMyChild ){
			if(pNo==0){//我只能管辖内，兄弟单位不归我管
				tips.setErrMsg("您没有权限操作部门【"+argDeptName+"】的数据");
				return tips;
			}else{ //pNo=1,2,3我都是可以管兄弟单位的。
				return tips;
			}
		}else{
			//辖内 我的孩子，还能蹦跶么？。
			return tips;
		}

    }
    
    String getArgBranchId(ProceedingJoinPoint pjp,DeptFilter deptFilter ){
    	Object[] args=pjp.getArgs();
		Object arg=args[0];
		String branchIdArg="";  
		if(arg==null) {
			return null;
		}
		if(arg instanceof Map){
			Map argMap=(Map)arg;
			branchIdArg=(String) argMap.get(deptFilter.branchFieldName());
		}else if(arg instanceof String){
			branchIdArg=(String) arg;
		}else if(arg instanceof List){
			List argList=(List)arg;
			if(argList.size()>0) {
				Object arg0=argList.get(0);
				if(arg0 instanceof Map) {
					Map arg0Map=(Map)arg0;
					branchIdArg=(String) arg0Map.get(deptFilter.branchFieldName());
				}else if(arg0 instanceof String){
					branchIdArg=(String) arg0;
				}else {
					try {
						branchIdArg=(String) ReflectHelper.getValueByFieldName(arg0, deptFilter.branchFieldName()); 
					} catch (Exception e) {
						throw new QxException("qxCode2",ResourceType.deptScope.name(),"单位或部门参数获取错误[branchFieldName:"+deptFilter.branchFieldName()+",index:"+0+"]，请检查 HasLvl 中的branchFieldName 及index 配置是否正确，");
					}
				}
			}else {
				throw new QxException("qxCode3",ResourceType.deptScope.name(),"权限不足，机构编号为空，无法根据机构参数判断权限范围");
			}
		}else{
			try {
				branchIdArg=(String) ReflectHelper.getValueByFieldName(arg, deptFilter.branchFieldName()); 
			} catch (Exception e) {
				throw new QxException("qxCode2",ResourceType.deptScope.name(),"单位或部门参数获取错误[branchFieldName:"+deptFilter.branchFieldName()+",index:"+0+"]，请检查 HasLvl 中的branchFieldName 及index 配置是否正确，");
			}
			
		}
		return branchIdArg;
    }
}
