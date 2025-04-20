package com.mdp.safe.client.service.remote;

import com.mdp.core.entity.Tips;
import com.mdp.micro.client.CallBizService;
import com.mdp.safe.client.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;

public class DefaultUserBaseInfoRemoteOperService implements UserBaseInfoRemoteOperService {


    @Value("${mdp.user.base.info.uri.userRegister:/lcode/user/base/info/userRegister}")
    String userRegisterUri="";

    @Value("${mdp.user.base.info.uri.userUpdate:/lcode/user/base/info/userUpdate}")
    String userUpdateUri="";

    @Value("${mdp.user.base.info.uri.updatePassword:/lcode/user/base/info/updatePassword}")
    String updatePasswordUri="";

    @Value("${mdp.user.base.info.uri.resetPasswordByPhoneno:/lcode/user/base/info/resetPasswordByPhoneno}")
    String resetPasswordByPhonenoUri="";

    @Value("${mdp.user.base.info.uri.resetPasswordByEmail:/lcode/user/base/info/resetPasswordByEmail}")
    String resetPasswordByEmailUri="";


    @Autowired
    CallBizService callBizService;

    @Override
    public User userRegister(User user) {
        return null;
    }

    @Override
    public User userUpdate(User user) {
        return null;
    }

    @Override
    public Tips updatePassword(String userid, String oldPassword, String newPassword, Map<String,Object> extParams) {
        return null;
    }

    @Override
    public Tips resetPasswordByPhoneno(String userid,String phoneno, String code, String newPassword, Map<String,Object> extParams) {
        return null;
    }

    @Override
    public Tips resetPasswordByEmail(String userid,String email, String code, String newPassword, Map<String,Object> extParams) {
        return null;
    }

    public CallBizService getCallBizService() {
        return callBizService;
    }

    public void setCallBizService(CallBizService callBizService) {
        this.callBizService = callBizService;
    }
}
