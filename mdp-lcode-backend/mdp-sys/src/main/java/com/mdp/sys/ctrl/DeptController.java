package com.mdp.sys.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.entity.Tips;
import com.mdp.core.err.BizException;
import com.mdp.core.query.QueryTools;
import com.mdp.core.service.SequenceService;
import com.mdp.core.utils.BaseUtils;
import com.mdp.core.utils.NumberUtil;
import com.mdp.core.utils.ObjectTools;
import com.mdp.meta.client.service.ItemService;
import com.mdp.qx.DeptFilter;
import com.mdp.qx.HasQx;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.swagger.ApiEntityParams;
import com.mdp.sys.entity.Branch;
import com.mdp.sys.entity.Dept;
import com.mdp.sys.service.BranchService;
import com.mdp.sys.service.DeptPostUserService;
import com.mdp.sys.service.DeptService;
import com.mdp.sys.service.UserDeptService;
import com.mdp.sys.vo.BatchChangeParentDeptVo;
import io.swagger.annotations.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;
import java.util.stream.Collectors;

import static com.mdp.core.utils.BaseUtils.fromMap;
import static com.mdp.core.utils.BaseUtils.toMap;

/**
 * url编制采用rest风格,如对ADMIN.sys_dept sys_dept的操作有增删改查,对应的url分别为:<br>
 *  新增: sys/dept/add <br>
 *  查询: sys/dept/list<br>
 *  模糊查询: sys/dept/listKey<br>
 *  修改: sys/dept/edit <br>
 *  删除: sys/dept/del<br>
 *  批量删除: sys/dept/batchDel<br>
 * 组织 com.qqkj  顶级模块 mdp 大模块 sys 小模块 <br>
 * 实体 Dept 表 ADMIN.sys_dept 当前主键(包括多主键): deptid; 
 ***/
@RestController("mdp.sys.deptController")
@RequestMapping(value="/*/sys/dept")
@Api(tags={"sys_dept操作接口"})
public class DeptController {
	
	static Log logger=LogFactory.getLog(DeptController.class);
	
	@Autowired
	private DeptService deptService;

	@Autowired
	UserDeptService userDeptService;
	
    @Autowired
    private SequenceService seqService;
	@Autowired
	private BranchService branchService;

	DeptPostUserService  deptPostUserService;

	@Autowired
	ItemService itemService;


	@ApiOperation( value = "查询sys_dept信息列表",notes="listDept,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")

	@ApiResponses({
		@ApiResponse(code = 200,response=Dept.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},pageInfo:{total:总记录数},data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/socseclist",method=RequestMethod.GET)
	public Result listSocsec( @ApiIgnore @RequestParam Map<String,Object>  params){
		 
		IPage page=QueryTools.initPage(params);
		QueryTools.alias(params,"* res.");
		QueryWrapper<Dept> qw= QueryTools.initQueryWrapper(Dept.class,params);
		List<Map<String,Object>>	socsecList = deptService.selectSocsec(toMap(params));	//列出Dept列表
		
		return Result.ok().setData(socsecList);
		
		
	}
	@ApiResponses({
		@ApiResponse(code = 200,response=Dept.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},pageInfo:{total:总记录数},data:[数据对象1,数据对象2,...]}")
	})
	@DeptFilter(rejectOnDeptidIsNull = false)
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listDept(@ApiIgnore @RequestParam Map<String,Object> params){

		User user=LoginUtils.getCurrentUserInfo();

			IPage page=QueryTools.initPage(params);
			QueryTools.alias(params,"* res.");
		QueryWrapper<Dept> qw= QueryTools.initQueryWrapper(Dept.class,params);
		qw.eq("res.branch_id",user.getBranchId());
		if( (StringUtils.isEmpty(params.get("deptid")) && StringUtils.isEmpty("pdeptid")) ||  params.containsKey("autoDetectParentDeptid")){
			Set<String> autoDetectParentDeptids=LoginUtils.getCurrentUserInfo().getDeptids();
			params.put("autoDetectParentDeptids",autoDetectParentDeptids);
		}
		List<Map<String,Object>>	deptList = deptService.selectListMapByWhere(page,qw,params);

		return Result.ok().setData(deptList).setTotal(page.getTotal());
	}

	@ApiResponses({
			@ApiResponse(code = 200,response=Dept.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},pageInfo:{total:总记录数},data:[数据对象1,数据对象2,...]}")
	})
 	@RequestMapping(value="/getDept",method=RequestMethod.GET)
	public Result getDept( @RequestParam String deptid){
		  return Result.ok("","成功").setData(this.deptService.selectOneById(deptid));
	}

	@ApiOperation( value = "查询部门树",notes="listDeptTree")
	@ApiImplicitParams({  
		@ApiImplicitParam(name="deptid",value="部门编号,主键",required=false)
	})
	@ApiResponses({
		@ApiResponse(code = 200,response=Dept.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},pageInfo:{total:总记录数},data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list/tree",method=RequestMethod.GET)
	public Result listDeptTree( @RequestParam Map<String,Object> dept){
		return listDept(dept);
	}
	
	/**
	 * 根据机构编号查询部门以及子部门 无需权限和usertoken
	 * @cdate 2020/2/13
	 * @author LinYuKun
	 * @return
	 */
	@RequestMapping(value="/listDeptAndChidlDeptByBranchIdNoAuth",method=RequestMethod.GET)
	public Result listDeptAndChidlDeptByBranchIdNoAuth(@RequestParam Map<String,Object> params){
		
		
		List<Map<String,Object>>	rootDeptList =new ArrayList();
		Map<String,Object> rootDept=new HashMap();
		List<Map<String,Object>>	childMapArr =new ArrayList();
		try {
			String branchId=(String)params.get("branchId");
			String deptid=(String)params.get("deptid");
			if(StringUtils.isEmpty(branchId)) {
				throw new BizException("请传递机构编号");
			}
			if(StringUtils.isEmpty(deptid)) {
				params.put("pdeptid", "A0");//顶级
			}

			QueryTools.alias(params,"* res.");
			IPage page=QueryTools.initPage(params);
			QueryWrapper<Dept> qw=QueryTools.initQueryWrapper(Dept.class,params);
			rootDeptList = deptService.selectListMapByWhere(page,qw,params);
			if(!CollectionUtils.isEmpty(rootDeptList)) {
				rootDept=rootDeptList.get(0);
				String rdeptid=(String)rootDept.get("deptid");
				if(!StringUtils.isEmpty(rdeptid)) {
					Dept dept=new Dept();
					dept.setDeptid(rdeptid);
					childMapArr = deptService.selectChildrenByDeptid(dept);//查询子部门
				}
			}
			return Result.ok().put("rootDept",rootDept).put("childMapArr",childMapArr);
		}catch(Exception e) {
			logger.error("",e);
            return Result.error(e);
		}
	}
	
	/**
	 * 添加部门 无需权限和usertoken
	 * @author LinYuKun
	 * @cdate 2020/2/14 14:49
	 * 
	 */
	@RequestMapping(value="/addDeptNoAuth",method=RequestMethod.POST)
	public Result addDeptNoAuth( @RequestBody Dept dept){
		return Result.ok().setData(this.addDept(dept));
	}
	
	/***/
	@ApiOperation( value = "新增一条sys_dept信息",notes="addDept,主键如果为空，后台自动生成")
	@ApiResponses({
		@ApiResponse(code = 200,response=Dept.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 

	@HasQx(value = "sys_dept_add",name = "添加部门",moduleId = "mdp-sys",moduleName = "组织管理")
	@DeptFilter(deptFieldName = "pdeptid")
	@RequestMapping(value="/add",method=RequestMethod.POST)
    public Map<String, Object> addDept(@RequestBody Dept dept) {
        Map<String, Object> m = new HashMap<>();
        Tips tips = new Tips("成功新增一条数据");
			User user=LoginUtils.getCurrentUserInfo();
			if(ObjectTools.isEmpty(dept.getBranchId())){
				dept.setBranchId(user.getBranchId());
			}
        	Branch branch = this.branchService.getById(dept.getBranchId());
        	if(branch==null){
				return Result.error("branch-is-null","机构已不存在");
			}else if("0".equals(branch.getEnabled())){
				return Result.error("branch-is-disabled","机构已禁用");
			}
			dept.setBranchId(user.getBranchId());
        	if(StringUtils.isEmpty(dept.getDeptid())) {
        	dept.setDeptid(seqService.getTablePK("dept", "deptid"));
//			categoryService.insert(category);
        	Dept d = deptService.addDept(dept);
            return Result.ok().setData( d);
        	}  else {  
				Dept deptQuery=new Dept(dept.getDeptid());
				if(deptService.countByWhere(deptQuery)>0) {
					return Result.error("id-exists","该部门编号已存在");
				}else{
					Dept d = deptService.addDept(dept);
					 return Result.ok().setData( d);
				}
        	}

    }
	
	/***/
	@ApiOperation( value = "删除一条sys_dept信息",notes="delDept,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	})

	@HasQx(value = "sys_dept_del",name = "删除部门",moduleId = "mdp-sys",moduleName = "组织管理")
	@DeptFilter
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delDept(@RequestBody Dept dept){
		List<Dept> depts=new ArrayList<>();
		depts.add(dept);
		 return this.batchDelDept(depts);
	}
	 
	
	/***/
	@ApiOperation( value = "根据主键修改一条sys_dept信息",notes="editDept")
	@ApiResponses({
		@ApiResponse(code = 200,response=Dept.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})

	@HasQx(value = "sys_dept_edit",name = "修改部门",moduleId = "mdp-sys",moduleName = "组织管理")
	@DeptFilter
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editDept(@RequestBody Dept dept) {
		
		
		try{
			deptService.updateDeptByPk(dept);
			return Result.ok().setData(dept);
		}catch (BizException e) { 
			logger.error("执行异常",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}  
	}


	@ApiOperation( value = "批量修改某些字段",notes="")
	@ApiEntityParams(Dept.class)
	@ApiResponses({
			@ApiResponse(code = 200,response= Result.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields(@RequestBody Map<String,Object> params) {
		try{
			 this.deptService.editSomeFields(params);
			 return Result.ok();
		}catch (BizException e) {
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}
	}


	/**
	 * 批量修改上下级
	 */
	@RequestMapping(value="/batchChangeParent")
	public Result batchChangeParent(@RequestBody List<BatchChangeParentDeptVo> idLinks) {

		User user = LoginUtils.getCurrentUserInfo();
		if (idLinks == null || idLinks.size() == 0) {
			return Result.error("分类列表不能为空");

		}
		Set<String> childIds = new HashSet<>();
		Set<String> pids = new HashSet<>();
		Map<String, String> newIdLinks = new HashMap<>();
		for (BatchChangeParentDeptVo link : idLinks) {
			if (ObjectTools.isEmpty(link.getPdeptid()) || "0".equals(link.getPdeptid()) ||"A0".equals(link.getPdeptid())) {
				link.setPdeptid("A0");
			} else {
				pids.add(link.getPdeptid());
			}

			if (ObjectTools.isEmpty(link.getDeptid())) {
				return Result.error("分类编号不能为空");
			}
			childIds.add(link.getDeptid());
			newIdLinks.put(link.getDeptid(), link.getPdeptid());
		}


		List<Dept> childDepts = this.deptService.selectListByIds(childIds.stream().collect(Collectors.toList()));
		List<Dept> parentDepts = new ArrayList<>();
		if (!pids.isEmpty()) {
			parentDepts = this.deptService.selectListByIds(pids.stream().collect(Collectors.toList()));

		}
		if (childDepts == null || childDepts.size() == 0) {
			return Result.error("data-0", "数据不存在");
		}

		// 过滤掉新旧上级一致的分类
		childDepts = childDepts.stream().filter(k -> !k.getPdeptid().equals(newIdLinks.get(k.getDeptid()))).collect(Collectors.toList());
		if (childDepts == null || childDepts.size() == 0) {
			return Result.error("id-link-same-0", "新旧上级一致，无须保存");
		}

		String deptType = childDepts.get(0).getDeptType();
		Map<String, Dept> deptMap = new HashMap<>();
		for (Dept dept : childDepts) {
			deptMap.put(dept.getDeptid(), dept);
		}
		for (Dept dept : parentDepts) {
			deptMap.put(dept.getDeptid(), dept);
		}


		// 进行权限判断  
		if (!LoginUtils.isBranchAdmin(user.getBranchId())) {

		}


		/**
		 * 按上级进行分组
		 */
		Map<String, List<Dept>> xmMaps = new HashMap<>();
		for (Dept dept : childDepts) {
			String newPdeptid = newIdLinks.get(dept.getDeptid());
			Dept parent = null;
			String pidPaths = null;
			if (ObjectTools.isEmpty(newPdeptid) || "0".equals(newPdeptid)||"A0".equals(newPdeptid)) {//向根迁移
				pidPaths = "A0,";
			} else {
				parent = deptMap.get(newPdeptid);
				pidPaths = parent.getIdPath();
			}

			List<Dept> depts = xmMaps.get(pidPaths);
			if (depts == null) {
				depts = new ArrayList<>();
				depts.add(dept);
			} else {
				depts.add(dept);
			}
			xmMaps.put(pidPaths, depts);
		}

		// 分组后要进行降序排序，从底层往上更新
		List<String> pidPathsList = xmMaps.keySet().stream().sorted((x, y) -> {
			return y.split(",").length - x.split(",").length;
		}).collect(Collectors.toList());
		for (String pidPaths : pidPathsList) {
			List<Dept> depts = xmMaps.get(pidPaths);
			//上级为顶级的情况处理
			if ("0".equals(pidPaths) || "0,".equals(pidPaths) || ObjectTools.isEmpty(pidPaths)||"A0,".equals(pidPaths)) {
				this.deptService.batchChangeParent(depts, null);
			} else {
				String[] parentIds = pidPaths.split(",");
				String parentId = parentIds[parentIds.length - 1];
				//必须重新查询,因为可能上一次循环已经更新了数据库中的pidPaths
				Dept parentDept = this.deptService.getById(parentId);
				this.deptService.batchChangeParent(depts, parentDept);
			}

		}
		return Result.ok();
	}
	/***/
	@ApiOperation( value = "根据主键列表批量删除sys_dept信息",notes="batchDelDept,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	})

	@HasQx(value = "sys_dept_batchDel",name = "批量删除部门",moduleId = "mdp-sys",moduleName = "组织管理")
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelDept(@RequestBody List<Dept> depts) {
		

			List<Dept> canOpDepts=new ArrayList<>();
			User user=LoginUtils.getCurrentUserInfo();
			IPage page=QueryTools.initPage(1,1000,0L,false);
			QueryWrapper<Dept> qw=new QueryWrapper<>();
			qw.in("res.deptid",depts.stream().map(k->k.getDeptid()).collect(Collectors.toList()));
			List<Map<String,Object>> deptsDb = this.deptService.selectListMapByWhere(page,qw, BaseUtils.map());
 			if(deptsDb==null || deptsDb.size()==0){
				 return Result.ok();
			}
			List<Dept> hasChidren=new ArrayList<>();
			List<Dept> branchIdErr=new ArrayList<>();
			boolean isBranchAdm=LoginUtils.isBranchAdmin(user.getBranchId());
			List<Dept> notMyScopeDepts =new ArrayList<>();
			for (Map<String,Object> dept2 : deptsDb) {
				Dept dept=fromMap(dept2,Dept.class);
				if(user.getBranchId().equals(dept.getBranchId())){
					if(NumberUtil.getInteger(dept2.get("childNum"),0)<=0){
						if(!isBranchAdm && ! LoginUtils.checkIsMyDeptScope(dept.getDeptid())){
							notMyScopeDepts.add(dept);
						}else{
							canOpDepts.add(dept);
						}

					}else{
						hasChidren.add(dept);
					}
				}else{
					branchIdErr.add(dept);
				}

			}
			List<String> msgs=new ArrayList<>();
			if(canOpDepts.size()>0){
				deptService.batchDeleteDept(canOpDepts);
				msgs.add(String.format("成功删除%s个部门",canOpDepts.size()));
			}
			if(hasChidren.size()>0){
				msgs.add(String.format("以下%s个部门有子部门，请先删除子部门，【%s】",hasChidren.size(),hasChidren.stream().map(i->i.getDeptName()).collect(Collectors.joining(","))));
			}

			if(branchIdErr.size()>0){
				msgs.add(String.format("以下%s个部门不属于您所在机构，不能删除，【%s】",branchIdErr.size(),branchIdErr.stream().map(i->i.getDeptName()).collect(Collectors.joining(","))));
			}

			if(notMyScopeDepts.size()>0){
				msgs.add(String.format("以下%s个部门不属于您辖内部门，不能删除，【%s】", notMyScopeDepts.size(), notMyScopeDepts.stream().map(i->i.getDeptName()).collect(Collectors.joining(","))));
			}
			if(canOpDepts.size() > 0 ){
				return Result.ok(msgs.stream().collect(Collectors.joining()));
			}else{
				return Result.error(msgs.stream().collect(Collectors.joining()));
			}
			

	}

	/**
	 * 新增部门审批流程
	 * @param flowVars
	 * @return
	 */
	@RequestMapping(value="/processApprova",method=RequestMethod.POST)
	public Result processApprova( @RequestBody Map<String,Object> flowVars){
		
		

		try{

			deptService.processApprova(flowVars);
			logger.debug("procInstId====="+flowVars.get("procInstId"));
			return Result.ok();
		}catch (BizException e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}
	}
}
