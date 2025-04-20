package com.mdp.login.service;

import com.mdp.core.utils.BaseUtils;
import com.mdp.login.mapper.SysUserMapper;
import com.mdp.safe.client.entity.*;
import com.mdp.safe.client.service.remote.DefaultUserResourceRemoteService;
import com.mdp.safe.client.service.remote.UserResourceRemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 覆盖默认的远程访问接口，由本地数据库提供相关数据
 * @see DefaultUserResourceRemoteService
 */
@Service
public class SysUserResourceService implements UserResourceRemoteService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Override
    public Map<String, Qx> getRoleQxsFromRemote(String roleid) {
        Map<String, Qx> qxMap=new HashMap<>();
        if( !StringUtils.hasText(roleid) || roleid.startsWith("SCOPE_")){
            return qxMap;
        }
        List<Qx> qxList=sysUserMapper.getRoleQxs(roleid);

        for (Qx qx : qxList) {
            qxMap.put(qx.getQxId(),qx);
        }
        return qxMap;
    }

    @Override
    public Role getRoleFromRemote(String roleid) {
        if( !StringUtils.hasText(roleid) || roleid.startsWith("SCOPE_")){
            return null;
        }
        Role role=sysUserMapper.getRole(roleid);
        return role;
    }

    @Override
    public Dept getDeptFromRemote(String deptid) {
        Map<String,Object> deptMap=sysUserMapper.getDept(deptid);
        if(deptMap==null||deptMap.isEmpty()){
            return null;
        }
        Dept dept= BaseUtils.fromMap(deptMap,Dept.class);
        return dept;
    }

    @Override
    public Branch getBranchFromRemote(String branchId) {
        Branch branch=sysUserMapper.getBranch(branchId);
        return branch;
    }

    @Override
    public Map<String, Menu> getRoleMenusFromRemote(String roleid) {
        Map<String, Menu> menuMap=new HashMap<>();
        if( !StringUtils.hasText(roleid) || roleid.startsWith("SCOPE_")){
            return menuMap;
        }
        List<Menu> menuList=sysUserMapper.getRoleMenus(roleid);
        for (Menu menu : menuList) {
            menuMap.put(menu.getId(),menu);
        }
        return menuMap;
    }

    @Override
    public List<Post> getPostsFromRemote(String userid) {
        return sysUserMapper.loadUserPostsByUserid(userid);
    }
}
