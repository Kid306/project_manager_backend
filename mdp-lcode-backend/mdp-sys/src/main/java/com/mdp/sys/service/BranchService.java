package com.mdp.sys.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mdp.core.err.BizException;
import com.mdp.core.service.BaseService;
import com.mdp.core.utils.BaseUtils;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.pwd.SafePasswordEncoder;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.sys.entity.*;
import com.mdp.sys.mapper.BranchMapper;
import com.mdp.sys.queue.SysUserPushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br> 
 * 组织 com.qqkj  顶级模块 mdp 大模块 sys 小模块 <br>
 * 实体 Branch 表 ADMIN.sys_branch 当前主键(包括多主键): id; 
 ***/
@Service("mdp.sys.branchService")
public class BranchService extends BaseService<BranchMapper, Branch> {
	
	@Autowired
	DeptService deptService;
	
	@Autowired
	private UserDeptService userDeptService;

	PasswordEncoder passwordEncoder=new SafePasswordEncoder();
	
	@Autowired
	UserService userService;
	
	@Autowired
	PostService postService;
	
	@Autowired
	PostRoleService postRoleService;
	
	@Autowired
	DeptPostService deptPostService;
	 
	@Autowired
	DeptPostUserService deptPostUserService;


	@Autowired
	SysUserPushService sysUserPushService;

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
	 
	@Transactional
	public  int insert(Branch branch) {  
		 User user= LoginUtils.getCurrentUserInfo();
		 if(StringUtils.isEmpty(branch.getEnabled())) {
			 branch.setEnabled("1");
		 }

		branch.setAdmUserid(branch.getId());
		 branch.setAdmUsername(branch.getBranchName()+"");
		 int i=super.insert(branch);
		 Dept dept =new Dept();
		 dept.setDeptid(deptService.createKey("deptid"));
		 dept.setBranchId(branch.getId());
		 dept.setDeptName(branch.getBranchName()+"-总部");
		 dept.setPdeptid("A0");
		 dept.setLevelType("L0");
		 dept.setShortName(branch.getBranchName()+"-总部");
		 dept.setDisplayDeptid(dept.getDeptid());
		 dept.setIdPath("A0,"+dept.getDeptid());
		dept.setManager(branch.getAdmUserid());
		dept.setLevelType("L1");
		dept.setManagerName(branch.getAdmUsername());
		 //dept.setState("1");
		 dept.setState("1");
		 deptService.insert(dept);
		 /**
		 if(!StringUtils.isEmpty(branch.getCuserid()) && branch.getCuserid().equals(user.getUserid())) {
			 deptService.joinDept(branch.getId(),dept.getDeptid(),branch.getCuserid());
		 }
		 **/
		 com.mdp.sys.entity.User user2=new com.mdp.sys.entity.User();
		 user2.setUserid(branch.getId());
		 user2.setDisplayUserid(branch.getId());
		 user2.setUsername(branch.getBranchName());
		 user2.setLocked("0");
		 user2.setAtype("0");
		 user2.setMemType("1");
		 user2.setPwdStrong("1");
		 user2.setEmail(branch.getEmaill());
		 user2.setBizFlowState("0");
		 user2.setLtime(new Date());
		 user2.setBranchId(branch.getId());
		 user2.setPhoneno(branch.getLphoneNo());
		 user2.setOfficePhoneno(branch.getLphoneNo());
		 user2.setNickname(branch.getLusername());
		 user2.setCpaType("0");
		 user2.setUnionid(user.getUnionid());
		 String passwordMd5=DigestUtils.md5DigestAsHex("888888".getBytes());
		 user2.setPassword(passwordMd5 );
		 com.mdp.sys.entity.User user2Db=userService.selectOneObject(user2);
		 if(user2Db!=null){
			 com.mdp.sys.entity.User userUpdate=new com.mdp.sys.entity.User();
			 userUpdate.setUserid(user2.getUserid());
			 userUpdate.setBranchId(branch.getId());
			 userUpdate.setMemType("1");
			 userUpdate.setCpaType("0");
			 if(!StringUtils.hasText(user2Db.getUnionid())){
				 userUpdate.setUnionid(user.getUnionid());
			 }
			 userUpdate.setUsername(user2.getUsername());
			 user2.setAtype("0");
			 userService.updateSomeFieldByPk(userUpdate);
			 sysUserPushService.pushUserInfoAfterChange(BaseUtils.toMap(userUpdate));
			 UserDept userDept=new UserDept();
			 userDept.setDeptid(dept.getDeptid());
			 userDept.setUserid(user2.getUserid());
			 userDeptService.insert(userDept);
		 }else{
			 UserVo userVo=new UserVo();
			 userVo.setUser(user2);
			 UserDept userDept=new UserDept();
			 userDept.setDeptid(dept.getDeptid());
			 userDept.setUserid(user2.getUserid());
			 userVo.setUserDept(userDept);
			 userService.insert(userVo);
			 sysUserPushService.pushUserInfoAfterAdd(BaseUtils.toMap(user2));
		 }

		 if("0".equals(user.getMemType()) && !user.getUserid().equals(user2.getUserid())){
			 com.mdp.sys.entity.User userUpdate=new com.mdp.sys.entity.User();
			 userUpdate.setUserid(user.getUserid());
			 userUpdate.setBranchId(branch.getId());
			 userUpdate.setMemType("1");
			 userUpdate.setCpaType("0");
			 userService.updateSomeFieldByPk(userUpdate);
			 sysUserPushService.pushUserInfoAfterChange(BaseUtils.toMap(userUpdate));

		 }

		 
		 List<Post> posts=new ArrayList<>();
		 Post p1=new Post();
		 p1.setId(postService.createKey("id"));
		 p1.setBranchId(branch.getId());
		 p1.setCdate(new Date());
		 p1.setPostLvl(1);
		 p1.setPostName("部门经理");
		 p1.setPostType("1");
		 posts.add(p1);
		 Post p2=new Post();
		 p2.setId(postService.createKey("id"));
		 p2.setBranchId(branch.getId());
		 p2.setCdate(new Date());
		 p2.setPostLvl(1);
		 p2.setPostName("人力资源-招聘专员");
		 p2.setPostType("1");
		 posts.add(p2);
		 Post p3=new Post();
		 p3.setId(postService.createKey("id"));
		 p3.setBranchId(branch.getId());
		 p3.setCdate(new Date());
		 p3.setPostLvl(1);
		 p3.setPostName("总经理");
		 p3.setPostType("1");
		 posts.add(p3);
		 postService.batchInsert(posts);
		 
		 
		 List<PostRole> postRoles=new ArrayList<>();
		 PostRole pr1=new PostRole();
		 pr1.setRoleid("ceo");
		 pr1.setPostId(p1.getId());
		 postRoles.add(pr1);
		 PostRole pr2=new PostRole();
		 pr2.setRoleid("hrrs");
		 pr2.setPostId(p2.getId());
		 postRoles.add(pr2);
		 PostRole pr3=new PostRole();
		 pr3.setRoleid("dept-manager");
		 pr3.setPostId(p3.getId());
		 postRoles.add(pr3);
		 postRoleService.batchAdd(postRoles);
		 
		 List<DeptPost> deptPosts=new ArrayList<>();
		 DeptPost deptPost1=new DeptPost();
		 deptPost1.setDeptid(dept.getDeptid());
		 deptPost1.setPostId(p1.getId());
		 deptPost1.setLdate(new Date());
		 deptPosts.add(deptPost1);
		 DeptPost deptPost2=new DeptPost();
		 deptPost2.setDeptid(dept.getDeptid());
		 deptPost2.setPostId(p2.getId());
		 deptPost2.setLdate(new Date());
		 deptPosts.add(deptPost2);
		 DeptPost deptPost3=new DeptPost();
		 deptPost3.setDeptid(dept.getDeptid());
		 deptPost3.setPostId(p3.getId());
		 deptPost3.setLdate(new Date());
		 deptPosts.add(deptPost3);
		 deptPostService.batchInsert(deptPosts);
		 
		 List<DeptPostUser> dpus=new ArrayList<>();
		 DeptPostUser dpu1=new DeptPostUser();
		 dpu1.setUserid(user2.getUserid());
		 dpu1.setDeptid(dept.getDeptid());
		 dpu1.setEnabled("1");
		 dpu1.setPostId(p1.getId());
		 dpu1.setLastDate(new Date());
		 dpu1.setStartDate(new Date());
		 dpu1.setId(deptPostUserService.createKey("id"));
		 dpus.add(dpu1);
		 DeptPostUser dpu2=new DeptPostUser();
		 dpu2.setUserid(user2.getUserid());
		 dpu2.setDeptid(dept.getDeptid());
		 dpu2.setEnabled("1");
		 dpu2.setPostId(p2.getId());
		 dpu2.setLastDate(new Date());
		 dpu2.setStartDate(new Date());
		 dpu2.setId(deptPostUserService.createKey("id"));
		 dpus.add(dpu2);
		 DeptPostUser dpu3=new DeptPostUser();
		 dpu3.setUserid(user2.getUserid());
		 dpu3.setDeptid(dept.getDeptid());
		 dpu3.setEnabled("1");
		 dpu3.setPostId(p3.getId());
		 dpu3.setLastDate(new Date());
		 dpu3.setStartDate(new Date());
		 dpu3.setId(deptPostUserService.createKey("id"));
		 dpus.add(dpu3);
		 if(user!=null && StringUtils.hasText(user.getUserid()) && "0".equals(user.getMemType()) && !user.getUserid().equals(user2.getUserid())){
			 DeptPostUser dpu4=new DeptPostUser();
			 dpu4.setUserid(user.getUserid());
			 dpu4.setDeptid(dept.getDeptid());
			 dpu4.setEnabled("1");
			 dpu4.setPostId(p1.getId());
			 dpu4.setLastDate(new Date());
			 dpu4.setStartDate(new Date());
			 dpu4.setId(deptPostUserService.createKey("id"));
			 dpus.add(dpu4);
		 }
		 deptPostUserService.batchInsert(dpus);
		 

		 return i;
	}
	 
	public  int updateByPk(Branch branch) { 
		Branch branchDb=this.selectOneObject(branch);
		//同步更改部门表中顶级部门的名称为机构名称
		if(branchDb.getBranchName()!=null && !branchDb.getBranchName().equals(branch.getBranchName())) {
			Dept deptPara=new Dept();
			deptPara.setBranchId(branch.getId()); 
			deptPara.setPdeptid("A0");
			List<Dept> deptsDb=deptService.selectListByWhere(deptPara);
			if(deptsDb!=null && deptsDb.size()>0) {
				deptsDb.forEach(new Consumer<Dept>() {
					@Override
					public void accept(Dept t) {
						Dept nDept=new Dept();
						nDept.setDeptid(t.getDeptid());
						nDept.setDeptName(branch.getBranchName());
						if(t.getShortName()!=null && t.getShortName().equals(branchDb.getBranchName())) {
							nDept.setShortName(branch.getBranchName());
						}
						deptService.updateSomeFieldByPk(nDept); 
					}
				}); 
			} 
		}
		return super.updateByPk(branch);
	}
	
	public  int deleteByPk(Branch branch) {
		Dept d = new Dept();
		d.setBranchId(branch.getId());
		deptService.deleteByWhere(d);
		UserDept userDept = new UserDept();
		userDeptService.deleteByWhere(userDept);
		return super.deleteByPk(branch);
	} 

	/**
	 * 流程审批过程中回调该接口，更新业务数据
	 * 如果发起流程时上送了restUrl，则无论流程中是否配置了监听器都会在流程发生以下事件时推送数据过来
	 * eventName: PROCESS_STARTED 流程启动完成 全局
	 *            PROCESS_COMPLETED 流程正常结束 全局
	 *            PROCESS_CANCELLED 流程删除 全局
	 *            create 人工任务启动
	 *            complete 人工任务完成  
	 *            assignment 人工任务分配给了具体的人
	 *            delete 人工任务被删除
	 *            TASK_COMPLETED_FORM_DATA_UPDATE 人工任务提交完成后，智能表单数据更新
	 *            
	 * 其中 create/complete/assignment/delete事件是需要在模型中人工节点上配置了委托代理表达式 ${taskBizCallListener}才会推送过来。
	 * 在人工任务节点上配置 任务监听器  建议事件为 complete,其它assignment/create/complete/delete也可以，一般建议在complete,委托代理表达式 ${taskBizCallListener}
	 * 
	 * @param flowVars {flowBranchId,agree,procInstId,startUserid,assignee,actId,taskName,mainTitle,branchId,bizKey,commentMsg,eventName,modelKey} 等 
	 * @return 如果tips.isOk==false，将影响流程提交
	 **/
	public void processApprova(Map<String, Object> flowVars) { 
		String eventName=(String) flowVars.get("eventName"); 
		String agree=(String) flowVars.get("agree"); 
		String branchId=(String) flowVars.get("branchId");
		String bizKey=(String) flowVars.get("bizKey");
		if(!"company_register".equals(bizKey)) {
			throw new BizException("不支持的业务,请上送业务编码【bizKey】参数,机构注册审批业务编码为【company_register】");
		}
		if("complete".equals(eventName)) { 
			if("1".equals(agree)) {
				this.updateFlowStateByProcInst("2", flowVars);
			}else {
				this.updateFlowStateByProcInst("3", flowVars);
			}
		}else {
			if("PROCESS_STARTED".equals(eventName)) {
				IPage page=new Page(1,1000);
				QueryWrapper<Branch> qw=new QueryWrapper<>();
				qw.eq("id",branchId);
				List<Map<String,Object>> bizList=this.selectListMapByWhere(page,qw,flowVars);
				if(bizList==null || bizList.size()==0) {
					throw new BizException("没有找到对应机构，机构号【"+branchId+"】");
				}else {
					Map<String,Object> bizObject=bizList.get(0);
					if("1".equals(bizObject.get("bizFlowState"))) {
						throw new BizException("该机构正在审批中，不能再发起审批");
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

    public Map<String, Object> calcBranchUsers(String branchId) {
		return baseMapper.calcBranchUsers(map("branchId",branchId));
    }

    public boolean checkBranchNameExistsAndValid(String branchName,String excludeBranchId) {
		Long i=baseMapper.checkBranchNameExistsAndValid(map("branchName",branchName,"excludeBranchId",excludeBranchId));
		return i>0?true:false;
    }

	public boolean checkBcodeExistsAndValid(String bcode,String excludeBranchId) {
		Long i=baseMapper.checkBranchNameExistsAndValid(map("bcode",bcode,"excludeBranchId",excludeBranchId));
		return i>0?true:false;
	}
}
