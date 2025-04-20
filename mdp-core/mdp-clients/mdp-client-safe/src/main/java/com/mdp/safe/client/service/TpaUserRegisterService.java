package com.mdp.safe.client.service;

import com.mdp.safe.client.dict.TpaType;
import com.mdp.safe.client.entity.User;

/**
 * 用于第三方账户登录后，将用户注册到sys_user中
 */
public interface TpaUserRegisterService {

    boolean supportTpa(TpaType tpaType);

    User register(User user);
}
