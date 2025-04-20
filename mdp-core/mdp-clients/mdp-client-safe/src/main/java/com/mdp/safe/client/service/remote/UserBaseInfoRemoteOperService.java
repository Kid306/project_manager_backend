package com.mdp.safe.client.service.remote;

import com.mdp.core.entity.Tips;
import com.mdp.safe.client.entity.User;

import java.util.Map;

/**
 * 更新、注册、重置密码、修改密码等远程访问接口
 * 如果没有自定义实现类，将使用默认的实现类
 * @see DefaultUserBaseInfoRemoteOperService
 */
public interface UserBaseInfoRemoteOperService {


    User userRegister(User user);

    User userUpdate(User user);

    Tips updatePassword(String userid, String oldPassword, String newPassword, Map<String,Object> extParams);

    Tips resetPasswordByPhoneno(String userid,String phoneno,String smsCode,String newPassword, Map<String,Object> extParams);

    Tips resetPasswordByEmail(String userid,String email,String emailCode,String newPassword, Map<String,Object> extParams);


}
