package com.mdp.sys.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mdp.core.entity.Result;
import com.mdp.core.entity.Tips;
import com.mdp.core.err.BizException;
import com.mdp.core.service.BaseService;
import com.mdp.core.utils.Const;
import com.mdp.safe.client.cache.DeptRedisCacheService;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.sys.entity.Dept;
import com.mdp.sys.entity.UserDept;
import com.mdp.sys.mapper.DeptMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;
/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br>
 * 
 * 组织 com.qqkj<br>
 * 顶级模块 mdp<br>
 * 大模块 sys<br>
 * 小模块 <br>
 * 表 ADMIN.sys_dept sys_dept<br>
 * 实体 Dept<br>
 * 表是指数据库结构中的表,实体是指java类型中的实体类<br>
 * 当前实体所有属性名:<br>
 *	deptid,deptName,pdeptid,deptType,state,manager,leader,shortName,displayDeptid,orgType,managerName,leaderName;<br>
 * 当前表的所有字段名:<br>
 *	deptid,dept_name,pdeptid,dept_type,state,manager,leader,short_name,display_deptid,org_type,manager_name,leader_name;<br>
 * 当前主键(包括多主键):<br>
 *	deptid;<br>
 ***/
@Service("mdp.sys.deptService")
public class DeptService extends BaseService<DeptMapper,Dept> {

	
	@Value("${mdp.top-deptid:A0}")
	String topDeptid=Const.topDeptid;

	
	@Autowired
	private UserDeptService userDeptService;


	@Autowired
	private DeptRedisCacheService deptCacheService;

	static Log logger=LogFactory.getLog(DeptService.class);

	/**
	 * 自定义查询，支持多表关联
	 * @param page 分页条件
	 * @param ew 一定要，并且必须加@Param("ew")注解
	 * @param ext 如果xml中需要根据某些值进行特殊处理，可以通过这个进行传递，非必须，注解也可以不加
	 * @return
	 */
	public List<Map<String,Object>> selectListMapByWhere(IPage page, QueryWrapper ew, Map<String,Object> ext){
		return baseMapper.selectListMapByWhere(page,ew,ext);
	}

	
	/**
	 * 
	 * @param deptid
	 * @return
	 */
	public List<Dept> listSubDept(String deptid) {
		if(deptid.equals(Const.topDeptid)){
			Dept dept=new Dept();
			return this.selectListByWhere(dept);
		}
		return baseMapper.listSubDeptObjects(deptid );
	}
	
	/** 请在此类添加自定义函数 */
	
	
	
	 
	public int insert(Dept dept) { 
		String pdeptid=topDeptid;
		if(StringUtils.isEmpty(dept.getPdeptid())){
			dept.setPdeptid(pdeptid);
		}
		if(!StringUtils.hasText(dept.getDeptid())){
			if(StringUtils.hasText(dept.getDisplayDeptid())){
				dept.setDeptid(dept.getDisplayDeptid());
			}else{
				dept.setDeptid(this.createKey("deptid"));
			}
		}
		if(!StringUtils.hasText(dept.getDisplayDeptid())){
			dept.setDisplayDeptid(dept.getDeptid());
		}
		if(StringUtils.isEmpty(dept.getShortName())){
			dept.setShortName(dept.getDeptName());
		}
		if(StringUtils.isEmpty(dept.getState())){
			dept.setState("A");
		}
		int i= super.insert(dept);
		return i;
	}
	 
	public   int updateDeptByPk(Dept dept) { 
		String pdeptid=dept.getPdeptid();
		Dept deptdb=this.selectOneObject(dept);
		if(pdeptid==null){
			pdeptid=topDeptid;
		}
		dept.setPdeptid(pdeptid);
		this.parentIdPathsCalcBeforeSave(dept);
		int i= super.updateByPk(dept);
		if(i>0){
			//如果名字改变，进行刷新缓存
			boolean needFlush=false;
			if(StringUtils.isEmpty(deptdb.getDeptName()) && !StringUtils.isEmpty( dept.getDeptName())) {
				needFlush=true;
			}else if( !StringUtils.isEmpty(deptdb.getDeptName()) && StringUtils.isEmpty( dept.getDeptName())) {
				needFlush=true;
			}else if(StringUtils.isEmpty(deptdb.getDeptName()) && StringUtils.isEmpty( dept.getDeptName())) {
				needFlush=false;
			}else if(!deptdb.getDeptName().equals(dept.getDeptName())) {
				needFlush=true;
			}
			if(needFlush) {
				deptCacheService.clear(deptdb.getDeptid());
			}
		}
		return i;
		 
		
	}  
	
	public   int[] batchDeleteDept(List<Dept> batchValues) {
		
		if(batchValues==null || batchValues.size()==0) {
			int[] i= {};
			return i;
		}
		int[] i= super.batchDelete(batchValues);
		for (Dept dept : batchValues) {
			deptCacheService.clear(dept.getDeptid());
		}
			return i;
	}
	
	public   int deleteByPk(Dept dept) {
		
		Dept p=new Dept();
		p.setPdeptid(dept.getDeptid());
		long count=super.countByWhere(p);
		if(count>0){
			throw new BizException("该机构有子机构,不能删除，如需要删除，请从子机构逐级向上删除");
		}
		int i= super.deleteByPk(dept);
		if(i>0){
			deptCacheService.clear(dept.getDeptid());
		}
		return i;
		 
	}

	/**
	 * 获取根部门，子部门及其员工信息
	 *
	 * @return
	 */
	public Map<String, Object> getDeptInfo(Dept dept) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> listMap = new ArrayList<>();

		Dept rootDept = new Dept();// 查询除了的部门
		if ("-1".equals(dept.getDeptid())) {
			User user = LoginUtils.getCurrentUserInfo();
			// 查询最顶级部门
			rootDept =baseMapper.selectTopDeptByUserid( user.getUserid());
		} else {
			rootDept = this.selectOneObject(dept);
		}
		if (rootDept!=null) {
			map.put("rootDept", rootDept);
		}else{
			return map;
		}
		// 查询该部门下的父节点，包括祖宗
		List<Dept> ancestorList = baseMapper.selectAncestorByDeptid(rootDept);
		map.put("ancestorList", ancestorList);

		List<Map<String,Object>> selectList =new ArrayList<>();
		selectList= baseMapper.selectDeptListAndCount(map("deptid",rootDept.getDeptid()));
		map.put("listMap", selectList);
		Map<String, Object> condition = new HashMap<>();
		// 查询该部门下的员工
		condition.put("deptid", rootDept.getDeptid());
		List<Map<String, Object>> rootMember =baseMapper.selectUsersByDeptid( condition);
		map.put("rootMember", rootMember);
		return map;
	}

	/**
	 * 获取该公司下的所有部门。用于添加子部门时，提供选择。
	 *
	 * @return
	 */
	public List<Dept> getAllDept(String deptid) {
		Dept dept = new Dept();
		dept.setDeptid(deptid);
		// 1.先查出根节点
		Dept rootDept =baseMapper.selectTopDept( dept);
		// 2.根据根节点查出所有后代节点
		return baseMapper.selectOffspringByDeptid(rootDept);
	}

	/**
	 * 获取某个部门的上级部门 用于设置部门时，显示上级部门
	 *
	 * @return
	 */
	public Dept getFatherDept(String deptid) {
		Dept dept = new Dept();
		dept.setDeptid(deptid);
		return baseMapper.selectFatherByDeptid( dept);
	}

	/**
	 * 判断该部门是否有成员。或者是否有子部门。 用于判断是否可以删除该部门
	 *
	 * @return
	 */
	public boolean isDeleteDept(String deptid) {
		Dept dept = new Dept();
		dept.setPdeptid(deptid);
		return this.countByWhere(dept)>0;
	}

	/**
	 * 获取该公司除了自己子代以外的所有部门。用于设置子部门时，提供选择。 设置子部门时，不能选自己的子代或者孙子
	 *
	 * @return
	 */
	public List<Dept> getDeptExceptSon(String deptid) {

		String companyid=this.selectOneObject(new Dept(deptid)).getBranchId();
		Dept company = new Dept();
		company.setDeptid(companyid);

		// 2.根据根节点查出所有后代节点
		List<Dept> allDepts = companyid.equals(deptid)?new ArrayList<>():baseMapper.selectOffspringByDeptid(company);
		List<Dept>  resultDepts=new ArrayList<>();

		if (allDepts!=null && allDepts.size()>0){
			for(int i =0; i<allDepts.size(); i++){
				 Dept d=allDepts.get(i);
				 if(!d.getDeptid().startsWith(deptid)){
					 resultDepts.add(d);
				 }
			}
		}

		return resultDepts;

	}

	/**
	 * 该部门的子部门
	 *
	 * @return
	 */
	public List<Dept> getChildrenDept(String deptid) {
		Dept dept = new Dept();
		dept.setPdeptid(deptid);
		List<Dept> deptList = this.selectListByWhere(dept);
		return deptList;
	}

	/**
	 * 根据deptid获取该部门下的员工
	 * 
	 * @return
	 */
	public List<Map<String, Object>> getDeptMember(String deptid) {
		Map<String, Object> condition = new HashMap<String, Object>();
		// 查询该部门下的员工
		condition.put("deptid", deptid);
		List<Map<String, Object>> rootMember = baseMapper.selectUsersByDeptid( condition);
		return rootMember;
	}
	 
	/**
	 * 获取根部门
	 * 
	 * @return
	 */
	public Dept getRootDeptInfo() { 
		Dept rootDept = new Dept();
		 
		User user = LoginUtils.getCurrentUserInfo();
		// 查询最顶级部门
		rootDept = baseMapper.selectTopDeptByUserid( user.getUserid());
		return rootDept;
	}

	/**
	 * 添加员工
	 * @return
	 */
	@Transactional
	public Result addMemberInDept(List<User> user) {
		//更新user的deptid为deptid
		Map<String,Object> map=new HashMap<>();
		
		Dept dept=new Dept();
		try {
			int update =baseMapper.updateUser( user);
			 return Result.ok();
		} catch (Exception e) { 
			logger.error("执行异常",e);
			return Result.error(e);
		}  
		
	}

	public List<Map<String, Object>> getMembersByDepts(List<Dept> deptList) {
		
		return baseMapper.selectUsersByDeptids(deptList);
	}
	/**
	 * 新增公司
	 * @return
	 */
	@Transactional
	public Result newDept(String deptName,String phoneno,String smsCode) {
		Map<String,Object> map=new HashMap<>();
		
		Dept dept=new Dept();
		String deptid=new String();
		try {
			
			dept.setPdeptid(topDeptid);
			dept.setDeptName(deptName);
			dept.setShortName(deptName);
			dept.setDeptType("0"); 
			this.insert(dept); 
			deptid=dept.getDeptid();
			//更新用户信息 
			//if(this.seqService.validateSmsCode(phoneno, smsCode)){
			if(true){
				//更新用户信息 
				User user = LoginUtils.getCurrentUserInfo(); 
			}else{
				return Result.error("smsCodeErr","短信验证码不正确");
			}
			return Result.ok().put("deptid",deptid);
		} catch (Exception e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}
		
	}
	
	/**
	 * 根据搜索内容查找公司
	 * 搜索内容：1.匹配公司名称。 2.公司编号
	 * @return
	 */
	public List<Dept> searchDeptList(String key) {
		Map<String,Object> map=new HashMap<>();
		map.put("key","%"+key+"%");
		List<Dept> selectList = baseMapper.searchDept( map);
		return selectList;
	}
	
	
	public Result joinDept(String branchId,String deptid,String userid) {
		Map<String,Object> map=new HashMap<>();
		 
		try {  
			UserDept ud=new UserDept();
			ud.setDeptid(deptid);
			ud.setEnabled("1");
			ud.setUserid(userid);
			userDeptService.insert(ud);
			return Result.ok().setData(ud);
		} catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}
	}
	
	/**
	 * 退出公司
	 * @return
	 */
	@Transactional
	public Result quitDept() {
		Map<String,Object> map=new HashMap<>();
		 
		try { 
			//更新用户信息 
		    User user =LoginUtils.getCurrentUserInfo();
		    UserDept userDept = new UserDept();
		    userDept.setUserid(user.getUserid());
		    userDeptService.deleteByWhere(userDept);
		    return Result.ok();
		} catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}
	}
	
	 
	public  int[] batchDeleteAndFlushCache(List<String> batchValues) {
		 
		boolean ok= super.removeByIds(batchValues);
		for (String deptid : batchValues) {
			deptCacheService.clear(deptid);
		}
		int[] is=new int[batchValues.size()];
		for (int i = 0; i < batchValues.size(); i++) {
			is[i]=ok?1:0;
		}
		return is;
	}
	/**
	 * 查找该员工所在的公司 ---------待修改
	 * @param userid
	 * @return
	 */
	public Dept selectTopDeptByUserid(String userid) {
		
		return baseMapper.selectTopDeptByUserid( userid);
	}




	public List<Map<String, Object>> selectCompanyUser(Map<String, Object> map) {
		
		return baseMapper.selectCompanyUser( map);
	}

  	  /**
  	   * 查询公众号下所有的部门信息
  	   * */
	public List<Map<String, Object>> selectSocsec(Map<String, Object> map) {
		
		return baseMapper.selectWeixinSocsec( map);
	}

	public Dept addDept(Dept dept) {
 		parentIdPathsCalcBeforeSave(dept);
        this.insert(dept);
        return dept;
	}

	public Tips parentIdPathsCalcBeforeSave(Dept currNode) {
		Tips tips = new Tips("成功");
		if (!StringUtils.hasText(currNode.getPdeptid()) || "A0".equals(currNode.getPdeptid())) {
			currNode.setIdPath("A0," + currNode.getDeptid() + ",");
			currNode.setLevelType("1");
			return tips;
		} else {
			List<Dept> parentList=this.getParentList(currNode);
			if(parentList==null ||parentList.size()==0){
				currNode.setIdPath("A0,"+currNode.getPdeptid()+","+currNode.getDeptid()+",");
				currNode.setLevelType("2");
				return tips;
			}
			String idPath="A0,";
			for (int i = parentList.size() - 1; i >= 0; i--) {
				idPath=idPath+parentList.get(i).getDeptid()+",";
			}
			currNode.setIdPath(idPath+currNode.getDeptid()+",");

			String idPaths=currNode.getIdPath();
			String[] idpss=idPaths.split(",");
			currNode.setLevelType("L"+(idpss.length-1)+"");
		}
		return tips;
	}

	private List<Dept> getParentList(Dept currNode){
		List<Dept> parentList=new ArrayList<>();
		Dept current=currNode;
		while (true){
			if(!StringUtils.hasText(current.getPdeptid()) || "A0".equals(current.getPdeptid()) || currNode.getDeptid().equals(current.getPdeptid())){
				return parentList;
			}
			current=this.getById(current.getPdeptid());
			if(current==null){
				return parentList;
			}
			parentList.add(current);
		}
	}

	private List<Dept> getParentList(Dept currNode,List<Dept> nodes){
		List<Dept> parentList=new ArrayList<>();
		Dept current=currNode;
		while (true){
			if(!StringUtils.hasText(current.getPdeptid()) || "A0".equals(current.getPdeptid()) ||currNode.getDeptid().equals(current.getPdeptid())){
				return parentList;
			}
			Dept query=new Dept();
			query.setDeptid(current.getPdeptid());
			Optional<Dept> optional=nodes.stream().filter(i->i.getDeptid().equals(query.getDeptid())).findFirst();
			if(optional.isPresent()){
				current=optional.get();
				parentList.add(current);
			}else{
				return parentList;
			}

		}
	}


	/**
	 * 新增部门审批流程
	 * @param flowVars
	 */
	public void processApprova(Map<String, Object> flowVars) {
		String eventName=(String) flowVars.get("eventName");
		String agree=(String) flowVars.get("agree");
		String deptid=(String) flowVars.get("deptid");
		String bizKey=(String) flowVars.get("bizKey");
		if(!"dept_register".equals(bizKey)) {
			throw new BizException("不支持的业务,请上送业务编码【bizKey】参数,部门注册审批业务编码为【dept_register】");
		}
		if("complete".equals(eventName)) {
			if("1".equals(agree)) {
				this.updateFlowStateByProcInst("2", flowVars);
			}else {
				this.updateFlowStateByProcInst("3", flowVars);
			}
		}else {
			if("PROCESS_STARTED".equals(eventName)) {
				QueryWrapper<Dept> qw=new QueryWrapper<>();
				qw.eq("deptid",deptid);
				IPage page=new Page(1,1000);
				List<Map<String,Object>> bizList=this.selectListMapByWhere(page,qw,flowVars);
				if(bizList==null || bizList.size()==0) {
					throw new BizException("没有找到对应部门，部门编号【"+deptid+"】");
				}else {
					Map<String,Object> bizObject=bizList.get(0);
					if("1".equals(bizObject.get("bizFlowState"))) {
						throw new BizException("该部门正在审批中，不能再发起审批");
					}
				}
				flowVars.put("id", this.createKey("id"));
				baseMapper.insertProcessApprova(flowVars);
				this.updateFlowStateByProcInst("1", flowVars);
			}else if("PROCESS_COMPLETED".equals(eventName)) {
				if("1".equals(agree)) {
					this.updateFlowStateByProcInst("2", flowVars);
				}else {
					this.updateFlowStateByProcInst("3", flowVars);
				}

			}else if("PROCESS_CANCELLED".equals(eventName)) {
				this.updateFlowStateByProcInst("4", flowVars);
			}
		}
	}
	public void updateFlowStateByProcInst(String flowState,Map<String, Object> flowVars) {
		flowVars.put("flowState", flowState);
		flowVars.put("bizFlowState", flowState);
		if("1".equals(flowState)) {
			flowVars.put("bizProcInstId", flowVars.get("procInstId"));
		}
		baseMapper.updateProcessApprova(flowVars);
	}

	public List<Map<String, Object>> selectChildrenByDeptid(Dept dept) {
		return baseMapper.selectChildrenByDeptid(dept);
	}
	@Transactional
	public void batchChangeParent(List<Dept> categories,Dept parent) {
		if(parent!=null){
			baseMapper.batchChangeParent(map("deptids",categories.stream().map(i->i.getDeptid()).collect(Collectors.toList()),"pdeptid",parent.getDeptid(),"parentPidPaths",parent.getIdPath()));
		}else {
			baseMapper.batchChangeParent(map("deptids",categories.stream().map(i->i.getDeptid()).collect(Collectors.toList()),"pdeptid","A0","parentPidPaths","A0,"));

		}
	}
}

