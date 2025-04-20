package com.mdp.login.service;

import com.mdp.core.api.Sequence;
import com.mdp.core.entity.Tips;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.service.remote.UserBaseInfoRemoteOperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Map;

@Service
public class UserOperService implements UserBaseInfoRemoteOperService {

    @Autowired
    SysUserService sysUserService;


    @Autowired
    Sequence sequence;

    @Override
    public User userRegister(User user) {
        if(!StringUtils.hasText(user.getUnionid())){
            if(StringUtils.hasText(user.getUserid())){
                user.setUnionid(user.getUserid());
            }else{
                user.setUserid(sequence.getUserid());
                user.setUnionid(user.getUnionid());
            }
        }
        String userType=(String) user.get("userType");
        user.put("startdate",new Date());

            this.sysUserService.userRegister(user);
        return user;
    }

    @Override
    public User userUpdate(User user) {
        user.setPassword(null); 
        String userType=(String) user.get("userType");

            this.sysUserService.userUpdate(user);
        return user;
    }
    @Override
    public Tips updatePassword(String userid, String oldPassword, String newPassword, Map<String,Object> extParams) {
        Tips tips = new Tips("修改密码成功");
        User user=new User();
        user.setPassword(newPassword);
        user.setUserid(userid);
        user.put("pwdStrong",extParams.get("pwdStrong"));
        String userType=(String) extParams.get("userType");
            this.sysUserService.updatePassword(user);
        return tips;
    }

    @Override
    public Tips resetPasswordByPhoneno(String userid,String phoneno, String smsCode, String newPassword, Map<String,Object> extParams) {
        Tips tips = new Tips("修改密码成功");
        User user=new User();

        user.setUserid(userid);
        user.setPassword(newPassword);
        user.setPhoneno(phoneno);
        user.put("pwdStrong",extParams.get("pwdStrong"));
        String userType=(String) extParams.get("userType");
            this.sysUserService.resetPasswordByPhoneno(user);
        return tips;
    }

    @Override
    public Tips resetPasswordByEmail(String userid,String email, String valiCode, String newPassword, Map<String,Object> extParams) {
        Tips tips = new Tips("修改密码成功");
        User user=new User();
        user.setPassword(newPassword);
        user.setUserid(userid);
        user.put("pwdStrong",extParams.get("pwdStrong"));
        String userType=(String) extParams.get("userType");
            this.sysUserService.resetPasswordByEmail(user);
        return tips;
    }


    public void userUpdatePhoneno(User params) {
            User user=new User();
            user.setUserid(params.getUserid());
            user.setPhoneno(params.getPhoneno());
            this.sysUserService.userUpdate(user);
    }

    public void userUnregister(User user) {
            this.sysUserService.userUnregister(user);

    }
}
