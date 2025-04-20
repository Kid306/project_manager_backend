package com.mdp.login.ctrl;

import com.mdp.core.entity.Result;
import com.mdp.safe.client.entity.*;
import com.mdp.safe.client.service.UserResourceQueryService;
import com.mdp.safe.client.service.remote.UserResourceRemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserResourceController {

    @Autowired
    UserResourceQueryService userResourceService;


    @Autowired
    UserResourceRemoteService userResourceRemoteService;

    @GetMapping(value = "/user/resource/getRoleQxs")
    @ResponseBody public Result getRoleQxsFromRemote(@RequestParam String roleid) {
        Map<String,Qx> qxMap= userResourceService.loadRoleQxsByRoleids(roleid);
        return Result.ok().setData(qxMap);
    }

    @GetMapping(value = "/user/resource/getRole")
    @ResponseBody public Result getRoleFromRemote(@RequestParam String roleid) {
        Role role = userResourceRemoteService.getRoleFromRemote(roleid);
        return Result.ok().setData(role);
    }

    @GetMapping(value = "/user/resource/getDept")
    @ResponseBody public Result getDeptFromRemote(@RequestParam String deptid) {
        Map<String,Object> result=new HashMap<>();
        List<Dept> depts = userResourceService.loadDeptsByDeptids(deptid);
        return Result.ok().setData(depts==null||depts.size()==0?null:depts.get(0));
    }

    @GetMapping(value = "/user/resource/getBranch")
    @ResponseBody public Result getBranchFromRemote(@RequestParam String branchId) {
        Map<String,Object> result=new HashMap<>();
        List<Branch> branchs = userResourceService.loadBranchsByBranchIds(branchId);
        return Result.ok().setData(branchs==null||branchs.size()==0?null:branchs.get(0));
    }

    @GetMapping(value = "/user/resource/getRoleMenus")
    @ResponseBody public Result getRoleMenusFromRemote(@RequestParam String roleid) {
        Map<String,Object> result=new HashMap<>();
        Map<String,Menu> menuMap= userResourceService.loadRoleMenusByRoleids(roleid);
        return Result.ok().setData(menuMap);
    }
    @GetMapping(value = "/user/resource/getPosts")
    @ResponseBody public Result getPostsFromRemote(@RequestParam String userid) {  
        return Result.ok().setData(userResourceRemoteService.getPostsFromRemote(userid));
    }
}
