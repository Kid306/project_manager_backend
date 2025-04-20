package com.mdp.login.service;

import com.mdp.core.err.BizException;
import com.mdp.login.mapper.SysUserMapper;
import com.mdp.plat.client.PlatClientService;
import com.mdp.plat.client.entity.PlatformVo;
import com.mdp.safe.client.entity.DeptPostRole;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.service.remote.UserBaseInfoRemoteQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysUserQueryService implements UserBaseInfoRemoteQueryService{

    @Autowired
    SysUserMapper sysUserMapper;


    @Autowired
    private PlatClientService platClientService;


    @Override
    public List<User> queryByUserloginid(String userloginid, String idType, Map<String, Object> extParams) {
        User user= new User();
        user.putAll(extParams);
        if(!StringUtils.hasText(idType)||"all".equals(idType)){
            user.put("userloginid",userloginid);
            user.put("idType","all");
        }else if("userid".equals(idType)){
            user.setUserid(userloginid);
        }else if("phoneno".equals(idType)){
            user.setPhoneno(userloginid);
        }else if("displayUserid".equals(idType)){
            user.setDisplayUserid(userloginid);
        }else if("email".equals(idType)){
            user.setEmail(userloginid);
        }else if("tpaOpenid".equals(idType)){
            user.setTpaOpenid(userloginid);
        }else if("unionid".equals(idType)){
            user.setUnionid(userloginid);
        }else{
            throw new BizException("idType-err","登录参数类型错误");
        }

        user.setUserType((String) extParams.get("userType"));
        return sysUserMapper.queryByUserloginid(user);
    }

    @Override
    public User getUserByUserid(String userid, Map<String, Object> extParams) {
        User user= new User();
        user.setUserid(userid);
        user.setUserType((String) extParams.get("userType"));
        List<User> users=sysUserMapper.queryByUserloginid(user);
        if(users!=null && users.size()>0){
            return users.get(0);
        }else{
            return null;
        }
    }

    @Override
    public List<DeptPostRole> getUserDeptPostRoles(String userid, Map<String, Object> extParams) {
        Map<String,Object> params = new HashMap<>();
        params.put("userid",userid);
        params.put("platformBranchId",getPlatformBranchId());
        if(extParams!=null && extParams.containsKey("deptid")){
            params.put("deptid",extParams.get("deptid"));
        }
        if(extParams!=null && extParams.containsKey("branchId")){
            params.put("branchId",extParams.get("branchId"));
        }
        return this.sysUserMapper.loadUserDeptPostRolesByUserid(params);
    }

    /**
     * 根据邮箱验证码查找用户信息
     *
     * @param valiCode
     * @param userType
     * @return
     */
    public List<User> getUserByEmailCode(String userid, String codeEmail, String valiCode,String userType) {

        User userQ= new User();
        userQ.setUserid(userid);
        userQ.setEmail(codeEmail);
        userQ.put("codeEmail",codeEmail);
        userQ.put("valiCode",valiCode);
        userQ.setUserType(userType);
        return sysUserMapper.queryByUserloginid(userQ);
    }

    public void setUserTypeToUser(String userType,User user){
        if(user==null){
            return;
        }
        if(StringUtils.hasText(userType) ){
            user.setUserType(userType);
        }else{
            user.setUserType("staff");
        }
    }
    public String getPlatformBranchId(){
        PlatformVo platformVo=platClientService.getPlatformVo();
        return platformVo.getBranchId();
    }

    public List<User> queryMyUsers(User cuser) {
        return this.sysUserMapper.queryMyUsers(cuser);
    }
}
