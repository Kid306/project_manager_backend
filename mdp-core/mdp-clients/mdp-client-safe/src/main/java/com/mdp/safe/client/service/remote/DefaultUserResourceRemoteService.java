package com.mdp.safe.client.service.remote;

import com.mdp.core.entity.Tips;
import com.mdp.core.utils.BaseUtils;
import com.mdp.micro.client.CallBizService;
import com.mdp.safe.client.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultUserResourceRemoteService implements UserResourceRemoteService {

	@Value("${mdp.user.resource.uri.getRoleQxs:/lcode/user/resource/getRoleQxs}")
	String getRoleQxsUri="";

	@Value("${mdp.user.resource.uri.getRole:/lcode/user/resource/getRole}")
	String getRoleUri="";

	@Value("${mdp.user.resource.uri.getDept:/lcode/user/resource/getDept}")
	String getDeptUri="";

	@Value("${mdp.user.resource.uri.getBranch:/lcode/user/resource/getBranch}")
	String getBranchUri="";

	@Value("${mdp.user.resource.uri.getRoleMenus:/lcode/user/resource/getRoleMenus}")
	String getRoleMenusUri="";

	@Value("${mdp.user.resource.uri.getPosts:/lcode/user/resource/getPosts}")
	String getPostsUri="";

	@Autowired
	CallBizService callBizService;




	 /**
	  * 查询用户拥有的权限
	  * @param roleid
	  * @return  Map<String, Qx>
	  * @see Map<String,  Qx >
	  */
	 public Map<String, Qx> getRoleQxsFromRemote(String roleid){
	 	Map<String,Object> params=new HashMap<>();
		 params.put("roleid",roleid);
		 Map<String,Object> result= callBizService.getForMap(getRoleQxsUri+"?roleid="+roleid );
		 Tips tips= (Tips) result.get("tips");
		 if(tips.isOk()){
		 	Map<String,Qx> qxs=new HashMap<>();
			 Map<String,Map<String,Object>> data = (Map<String,Map<String,Object>>) result.get("data");
			 for (Map<String, Object> datum : data.values()) {
			 	Qx qx= BaseUtils.fromMap(datum,Qx.class);
				 qxs.put(qx.getQxId(),qx);
			 }
			 return qxs;
		 }
		 return null;
	}


	
	public Role getRoleFromRemote(String roleid) {
		Map<String,Object> result= callBizService.getForMap(getRoleUri+"?roleid="+roleid );
		Tips tips= (Tips) result.get("tips");
		if(tips.isOk()){
			List<Role> roles=new ArrayList<>();
			Map<String, Object> data = (Map<String, Object>) result.get("data");
			if(data!=null){
				Role role=BaseUtils.fromMap(data,Role.class);
				return role;
			}
		}
		return null;

	}


    public Dept getDeptFromRemote(String deptid) {
		Map<String,Object> result= callBizService.getForMap(getDeptUri+"?deptid="+deptid );
		Tips tips= (Tips) result.get("tips");
		if(tips.isOk()){
			Map<String, Object> data = (Map<String, Object>) result.get("data");
			if(data!=null){
				Map<String,Object> deptMap=data;
				Dept dept= BaseUtils.fromMap(data,Dept.class);
				return dept;
			}
		}
		return null;
    }

	public Branch getBranchFromRemote(String branchId) {
		Map<String,Object> result= callBizService.getForMap(getBranchUri+"?branchId="+branchId );
		Tips tips= (Tips) result.get("tips");
		if(tips.isOk()){
			Map<String, Object> data = (Map<String, Object>) result.get("data");
			if(data!=null ){
				return BaseUtils.fromMap(data,Branch.class);
			}
		}
		return null;
	}

	public Map<String, Menu> getRoleMenusFromRemote(String roleid) {
		Map<String,Object> result= callBizService.getForMap(getRoleMenusUri+"?roleid="+roleid );
		Tips tips= (Tips) result.get("tips");
		if(tips.isOk()){
			Map<String,Menu> menus=new HashMap<>();
			Map<String, Map<String,Object>> data = (Map<String, Map<String,Object>>) result.get("data");
			for (Map<String, Object> datum : data.values()) {
				Menu menu=BaseUtils.fromMap(datum,Menu.class);
				menus.put(menu.getId(),menu);
			}
			return menus;
		}
		return null;
	}

	@Override
	public List<Post> getPostsFromRemote(String userid) {
		Map<String,Object> result= callBizService.getForMap(getPostsUri+"?userid="+userid );
		Tips tips= (Tips) result.get("tips");
		if(tips.isOk()){
			List<Post> posts=new ArrayList<>();
			List<Map<String,Object>> data = (List<Map<String,Object>>) result.get("data");
			for (Map<String, Object> datum : data) {
				Post post=BaseUtils.fromMap(datum,Post.class);
				posts.add(post);
			}
			return posts;
		}
		return null;
	}


	public CallBizService getCallBizService() {
		return callBizService;
	}

	public void setCallBizService(CallBizService callBizService) {
		this.callBizService = callBizService;
	}
}
