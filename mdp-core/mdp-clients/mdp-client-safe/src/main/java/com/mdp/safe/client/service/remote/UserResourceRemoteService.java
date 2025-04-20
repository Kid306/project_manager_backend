package com.mdp.safe.client.service.remote;

import com.mdp.safe.client.entity.*;

import java.util.List;
import java.util.Map;

/**
 * 远程获取资源的接口
 * 通过容器自动化注入，如果没有被初始化，将使用默认值
 * @see DefaultUserResourceRemoteService
 *
 */
public interface UserResourceRemoteService {




	 /**
	  * 查询用户拥有的权限
	  * @param roleid
	  * @return  Map<String, Qx>
	  * @see Map<String,  Qx >
	  */
	 public Map<String, Qx> getRoleQxsFromRemote(String roleid);


	
	public Role getRoleFromRemote(String roleid);


    public Dept getDeptFromRemote(String deptid);

	public Branch getBranchFromRemote(String branchId);

	public Map<String, Menu> getRoleMenusFromRemote(String roleid);


	public List<Post> getPostsFromRemote(String userid);

}
