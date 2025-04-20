package com.mdp.safe.client.service;


import com.mdp.safe.client.cache.BranchRedisCacheService;
import com.mdp.safe.client.cache.DeptRedisCacheService;
import com.mdp.safe.client.cache.RoleMenusRedisCacheService;
import com.mdp.safe.client.cache.RoleQxsRedisCacheService;
import com.mdp.safe.client.entity.*;
import com.mdp.safe.client.service.remote.UserResourceRemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 ***/
@Service
public class UserResourceQueryService {

	@Autowired
	RoleQxsRedisCacheService roleQxsRedisCacheService;

	@Autowired
    DeptRedisCacheService deptRedisCacheService;

	@Autowired
    RoleMenusRedisCacheService roleMenusRedisCacheService;


	@Autowired
    BranchRedisCacheService branchRedisCacheService;


	@Autowired
	UserResourceRemoteService userResourceRemoteService;


	public Map<String, Qx> loadRoleQxsByRoleids(String ...roleids){
		Map<String,Qx> qxsMap=new HashMap<>();
		for (String roleid : roleids) {
			Map<String,Qx> qxs = roleQxsRedisCacheService.get(roleid);
			if(qxs!=null) {
				qxsMap.putAll(qxs);
			}
		}
		return qxsMap;
	}

	public Map<String, Menu> loadRoleMenusByRoleids(String ...roleids){
		Map<String,Menu> menusMap=new HashMap<>();
		for (String roleid : roleids) {
			Map<String,Menu> menus = roleMenusRedisCacheService.get(roleid);
			if(menus!=null) {
				menusMap.putAll(menus);
			}
		}
		return menusMap;
	}
	public List<Dept> loadDeptsByDeptids(String ...deptids ){
		List<Dept> depts=new ArrayList<>();
		if(deptids==null||deptids.length==0){
			return depts;
		}
		for (String deptid : deptids) {
			Dept dept= deptRedisCacheService.getDept(deptid);
			if(dept!=null){
				depts.add(dept);
			}
		}

		return depts;
	}
	public List<Branch> loadBranchsByBranchIds(String ...branchIds ){
		List<Branch> branchs=new ArrayList<>();
		if(branchIds==null||branchIds.length==0){
			return branchs;
		}
		for (String branchId : branchIds) {
			Branch branch= branchRedisCacheService.getBranch(branchId);
			if(branch!=null){
				branchs.add(branch);
			}
		}

		return branchs;
	}
	public List<Post> loadPostsByUserid(String userid ){
		 return userResourceRemoteService.getPostsFromRemote(userid);
	}
}
