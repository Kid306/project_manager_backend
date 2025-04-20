package com.mdp.login.integration.authenticator;

import com.mdp.core.utils.Const;
import com.mdp.login.integration.IntegrationParams;
import com.mdp.login.service.SysUserQueryService;
import com.mdp.plat.client.PlatClientService;
import com.mdp.plat.client.entity.PlatformVo;
import com.mdp.qx.DataLvl;
import com.mdp.safe.client.cache.RoleRedisCacheService;
import com.mdp.safe.client.dict.AuthType;
import com.mdp.safe.client.dict.UserType;
import com.mdp.safe.client.entity.DeptPostRole;
import com.mdp.safe.client.entity.Role;
import com.mdp.safe.client.entity.SafeAuthority;
import com.mdp.safe.client.entity.User;
import com.mdp.tpa.client.entity.AppShopConfig;
import com.mdp.tpa.client.service.AppShopConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 默认登录处理,密码方式
 * 格式 url: http://${domain}/api/m1/oauth2/oauth/token?grant_type=password&username=${username}&password=${前端密码MD5加密后的密码}&scope=all
 * @author chenyc
 * @date 2021-01-12
 **/
public class AuthenticatorAdapter implements IntegrationAuthenticator{

    @Autowired
    SysUserQueryService userBaseInfoQueryService;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    AppShopConfigService appShopConfigService;

    @Autowired
    PlatClientService platClientService;

    @Autowired
    RoleRedisCacheService roleRedisCacheService;

    Logger logger= LoggerFactory.getLogger(AuthenticatorAdapter.class);


    @Override
    public User authenticate(IntegrationParams integrationParams) {
        User user= loadUserByUserid(integrationParams.getUserloginid(),integrationParams);
        return user;
    }

    /**
     * 查找用户基础信息
     * @param userid 前台登陆编号
     * @return
     */
    public User loadUserByUserid(String userid,IntegrationParams integrationParams){
       User user= userBaseInfoQueryService.getUserByUserid(userid,null);
        //todo: 为了测试方便，将密码设置为与前端上送的密码一致
        user.setPassword(passwordEncoder.encode(integrationParams.getAuthParameter("password")));
        return user;
    }

    @Override
    public void prepare(IntegrationParams integrationParams) {

    }

    @Override
    public boolean supportAuthType(IntegrationParams integrationParams) {
        return AuthType.password.name().equals(integrationParams.getAuthType());
    }

    @Override
    public void complete(IntegrationParams integrationParams) {

    }

    @Override
    public Collection<? extends GrantedAuthority> loadAuthorities(User user, IntegrationParams integrationParams) {
        if(user==null){
            Set<SafeAuthority> safeAuthorities=new HashSet<>();
            SafeAuthority safeAuthority=new SafeAuthority("guest","游客",DataLvl.myDept.getLvl());
            safeAuthorities.add(safeAuthority);
            return   safeAuthorities;
        }
        Collection<? extends GrantedAuthority> authorities=new HashSet<>();
        if(UserType.cust.name().equals(integrationParams.getUserType())){
            authorities=this.loadAuthoritiesForCust(user, integrationParams);
        }else{
            authorities=this.loadAuthoritiesForStaff(user, integrationParams);
        }
        return authorities;
    }


    public Collection<? extends GrantedAuthority> loadAuthoritiesForCust(User user, IntegrationParams integrationParams) {
        Set<SafeAuthority> safeAuthorities=new HashSet<>();
        //默认用户
        SafeAuthority safeAuthority=new SafeAuthority("user","普通用户",DataLvl.myDept.getLvl());
        safeAuthorities.add(safeAuthority);
        if(user!=null){
            //机构会员
            if("1".equals(user.getMemType())){
                //机构角色
                SafeAuthority orgAuthority=new SafeAuthority("org","机构会员",DataLvl.myDept.getLvl());
                safeAuthorities.add(orgAuthority);
            }
            String roleids= (String) user.get("roleids");
            if(StringUtils.hasText(roleids)){
                String[] roleidss= roleids.split(",");
                for (String roleid : roleidss) {
                    if(safeAuthorities.stream().filter(i->roleid.equals(i.getAuthority())).findAny().isPresent()){
                        continue;
                    }
                    Role role=this.roleRedisCacheService.getRole(roleid);
                    SafeAuthority safeAuthority2=new SafeAuthority(role.getRoleid(),role.getRolename(),role.getDataLvl());
                    safeAuthorities.add(safeAuthority2);
                }
            }
            if("1".equals(user.getMemType())){
                SafeAuthority orgAuthority=new SafeAuthority("branchAdmin","机构管理员",DataLvl.branch.getLvl());
                safeAuthorities.add(orgAuthority);
            }
            if("superAdmin".equals(user.getUserid())||"superAdmin".equals(user.getDisplayUserid())){
                SafeAuthority orgAuthority=new SafeAuthority("superAdmin","超级管理员",DataLvl.allowAll.getLvl());
                safeAuthorities.add(orgAuthority);
            }
            setShopInfoToUser(user);
        }
        return safeAuthorities;
    }

    public Collection<? extends GrantedAuthority> loadAuthoritiesForStaff(User user, IntegrationParams integrationParams) {

        if(!"0".equals(user.getMemType())){
            List<DeptPostRole> deptPostRoles=loadUserRolesByUserid(user.getUserid(),integrationParams);
           return this.calcBranchAuthority(user,integrationParams,deptPostRoles);

        }else{
            return calcPersonAuthority(user, integrationParams);
        }
    }

    private Collection<? extends GrantedAuthority> calcPersonAuthority(User user, IntegrationParams integrationParams) {
        Set<SafeAuthority> safeAuthorities=new HashSet<>();
        String roleids= (String) user.get("roleids");
        if(StringUtils.hasText(roleids)){
            String[] roleidss= roleids.split(",");
            for (String roleid : roleidss) {
                if(safeAuthorities.stream().filter(i->roleid.equals(i.getAuthority())).findAny().isPresent()){
                    continue;
                }
                Role role=this.roleRedisCacheService.getRole(roleid);
                SafeAuthority safeAuthority=new SafeAuthority(role.getRoleid(),role.getRolename(),role.getDataLvl());
                safeAuthorities.add(safeAuthority);
            }
        }
        String branchAdmin="branchAdmin";
        String branchAdminName="机构管理员";

        if("superAdmin".equals(user.getDisplayUserid())||"superAdmin".equals(user.getUserid())){
            user.setMaxDataLvl(DataLvl.allowAll.getLvl());
            SafeAuthority safeAuthority=new SafeAuthority(Const.superAdminRole,"超级管理员",DataLvl.allowAll.getLvl());
            safeAuthorities.add(safeAuthority);
        }else {
            if("1".equals(user.getMemType())) {
                user.setMaxDataLvl(DataLvl.branch.getLvl());
                SafeAuthority safeAuthority=new SafeAuthority(branchAdmin,branchAdminName,DataLvl.branch.getLvl());
                safeAuthorities.add(safeAuthority);
            }
        }

        boolean isMainBranchAdm=false;
        if(user.getUserid().equals(user.getBranchId())){
            isMainBranchAdm=true;
        }
        if(!safeAuthorities.stream().filter(k->branchAdmin.equals(k.getAuthority())).findAny().isPresent()){
            if(isMainBranchAdm){
                SafeAuthority safeAuthority=new SafeAuthority(branchAdmin,branchAdminName,DataLvl.branch.getLvl());
                safeAuthorities.add(safeAuthority);
                user.setMaxDataLvl(DataLvl.branch.getLvl());
            }
        }
        return safeAuthorities;
    }

    private Collection<? extends GrantedAuthority> calcBranchAuthority(User user, IntegrationParams integrationParams, List<DeptPostRole> deptPostRoles) {
        Set<SafeAuthority> safeAuthorities=new HashSet<>();
        Set<String> deptids=new HashSet<>();
        Set<String> branchIds=new HashSet<>();
        Map<String,DeptPostRole> deptPostRoleMap=new HashMap<>();
        Map<String,DeptPostRole> masterMap=new HashMap<>();
        DataLvl maxDataLvl=DataLvl.myDept;
        DeptPostRole maxDataLvlDeptPostRole=null;
        if(deptPostRoles !=null && deptPostRoles.size()>0){
            for (DeptPostRole deptPostRole : deptPostRoles) {

                deptids.add(deptPostRole.getDeptid());
                branchIds.add(deptPostRole.getBranchId());
                if(deptPostRole.getDataLvl()==null){
                    deptPostRole.setDataLvl(DataLvl.myDept.getLvl());
                }
                deptPostRoleMap.put(deptPostRole.getRoleid(),deptPostRole);
                if("1".equals(deptPostRole.getMaster())){
                    masterMap.put(deptPostRole.getRoleid(),deptPostRole);
                }
                if(maxDataLvl.getLvl()<=DataLvl.getDataLvl(deptPostRole.getDataLvl()).getLvl()){
                    maxDataLvl=DataLvl.getDataLvl(deptPostRole.getDataLvl());
                    maxDataLvlDeptPostRole=deptPostRole;
                }
            }
            String roleids= (String) user.get("roleids");
            if(StringUtils.hasText(roleids)){
                String[] roleidss= roleids.split(",");
                for (String roleid : roleidss) {
                    if(deptPostRoleMap.containsKey(roleid)){
                        continue;
                    }
                    Role role=this.roleRedisCacheService.getRole(roleid);
                    SafeAuthority safeAuthority=new SafeAuthority(role.getRoleid(),role.getRolename(),role.getDataLvl());
                    safeAuthorities.add(safeAuthority);
                }
            }
        }else{
            String roleids= (String) user.get("roleids");
            if(StringUtils.hasText(roleids)){
                String[] roleidss= roleids.split(",");
                for (String roleid : roleidss) {
                    Role role=this.roleRedisCacheService.getRole(roleid);
                    SafeAuthority safeAuthority=new SafeAuthority(role.getRoleid(),role.getRolename(),role.getDataLvl());
                    safeAuthorities.add(safeAuthority);
                }
            }else{
                //默认用户
                SafeAuthority safeAuthority=new SafeAuthority("user","普通用户",DataLvl.myDept.getLvl());
                safeAuthorities.add(safeAuthority);
                maxDataLvl=DataLvl.myDept;
            }

        }
        if(!deptids.isEmpty()){
            user.setDeptids(deptids);
        }
        if (!branchIds.isEmpty()) {
            user.setBranchIds(branchIds);
        }
        for (DeptPostRole deptPostRole : deptPostRoleMap.values()) {
            SafeAuthority safeAuthority=new SafeAuthority(deptPostRole.getRoleid(),deptPostRole.getRolename(),deptPostRole.getDataLvl());
            safeAuthorities.add(safeAuthority);
        }


        DataLvl maxDataLvl2=null;
        DeptPostRole maxDataLvlDeptPostRole2=null;
        if( !masterMap.isEmpty() ){
            maxDataLvl2=DataLvl.myDept;
            if(masterMap.size()>1){
                //找出等级高的
                for (DeptPostRole deptPostRole : masterMap.values()) {
                    if(maxDataLvl2.getLvl()<=DataLvl.getDataLvl(deptPostRole.getDataLvl()).getLvl()){
                        maxDataLvl2=DataLvl.getDataLvl(deptPostRole.getDataLvl());
                        maxDataLvlDeptPostRole2=deptPostRole;
                    }
                }

            }else {
                maxDataLvlDeptPostRole2=masterMap.values().stream().findFirst().get();
                maxDataLvl2=DataLvl.getDataLvl(maxDataLvlDeptPostRole2.getDataLvl());
            }
        }else {
            maxDataLvl2=maxDataLvl;
            maxDataLvlDeptPostRole2=maxDataLvlDeptPostRole;
        }
        if(maxDataLvlDeptPostRole2==null){
            maxDataLvlDeptPostRole2=maxDataLvlDeptPostRole;
        }
        if(maxDataLvlDeptPostRole2!=null){
            user.setDeptid(maxDataLvlDeptPostRole2.getDeptid());
            user.setDeptName(maxDataLvlDeptPostRole2.getDeptName());
            user.setBranchId(maxDataLvlDeptPostRole2.getBranchId());
            user.setBranchName(maxDataLvlDeptPostRole2.getBranchName());
        }
        String branchAdmin="branchAdmin";
        String branchAdminName="机构管理员";
        if("superAdmin".equals(user.getDisplayUserid())||"superAdmin".equals(user.getUserid())){
            user.setMaxDataLvl(DataLvl.allowAll.getLvl());
            SafeAuthority safeAuthority=new SafeAuthority(Const.superAdminRole,"超级管理员",DataLvl.allowAll.getLvl());
            safeAuthorities.add(safeAuthority);
        }else {
             if("1".equals(user.getMemType())) {
                user.setMaxDataLvl(DataLvl.branch.getLvl());
                 SafeAuthority safeAuthority=new SafeAuthority(branchAdmin,branchAdminName,DataLvl.branch.getLvl());
                 safeAuthorities.add(safeAuthority);
            }else {
                user.setMaxDataLvl(maxDataLvl.getLvl());
            }
        }
        boolean isMainBranchAdm=false;
        if(!branchIds.isEmpty()){
            if(branchIds.contains(user.getUserid())){
                isMainBranchAdm=true;
            }
        }
        if(user.getUserid().equals(user.getBranchId())){
            isMainBranchAdm=true;
        }

        if(!safeAuthorities.stream().filter(k->branchAdmin.equals(k.getAuthority())).findAny().isPresent()){
            if(isMainBranchAdm){
                SafeAuthority safeAuthority=new SafeAuthority(branchAdmin,branchAdminName,DataLvl.branch.getLvl());
                safeAuthorities.add(safeAuthority);
                user.setMaxDataLvl(DataLvl.branch.getLvl());
            }
        }
        setShopInfoToUser(user);
        return safeAuthorities;
    }


    /**
     * 查找用户的角色列表
     */
    public List<DeptPostRole> loadUserRolesByUserid(String userid,IntegrationParams integrationParams){
        Map<String,Object> extParams=new HashMap<>();
        String deptid= (String) integrationParams.getAuthParameter("deptid");
        String branchId= (String) integrationParams.getAuthParameter("branchId");
        if(StringUtils.hasText(deptid)){
            extParams.put("deptid",deptid);
        }
        if(StringUtils.hasText(branchId)){
            extParams.put("branchId",branchId);
        }
        return userBaseInfoQueryService.getUserDeptPostRoles(userid,extParams);
    }

    /**
     * 设置用户归属商户
     * @param user
     */
    private void setShopInfoToUser(User user) {
        try {
            if(StringUtils.hasText(user.getBranchId())){
                AppShopConfig config= appShopConfigService.getShopConfigByBranchId(user.getBranchId());
                if(config==null){
                    user.setShopId(user.getBranchId());
                    user.setLocationId(user.getBranchId()+"-01");
                    return;
                }else{
                    user.setShopId(config.getShopId());
                    user.setLocationId(config.getHeadLocationId());
                    user.setShopName(config.getShopName());
                    user.setLocationName(config.getHeadLocationName());
                }
            }else{
                PlatformVo platformVo=platClientService.getPlatformVo();
                user.setShopId(platformVo.getShopId());
                user.setShopName(platformVo.getPlatformTitle());
                user.setLocationId(platformVo.getLocationId());
                user.setBranchId(platformVo.getBranchId());
                user.setBranchName(platformVo.getPlatformName());
            }
        }catch (Exception e){
            logger.error("",e);
        }


    }
}
