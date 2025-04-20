package com.mdp.login.integration.authenticator.webchat.miniapp;


import com.mdp.core.err.BizException;
import com.mdp.login.integration.IntegrationParams;
import com.mdp.login.integration.authenticator.AuthenticatorAdapter;
import com.mdp.login.service.SysUserQueryService;
import com.mdp.safe.client.cache.TpaCodeRedisCacheService;
import com.mdp.safe.client.dict.AuthType;
import com.mdp.safe.client.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 小程序集成认证
 *
 * 格式 url: http://${domain}/api/m1/oauth2/oauth/token?grant_type=password&username=${从微信服务器获得的openid}&password=${从微信服务器获取到的sessionKey}&scope=all&auth_type=wechat_mini_app&userid=${userid}&phoneno=${phoneno}
 * @author chenyc
 * @date 2021-01-12
 **/
@Service
public class MiniAppAuthenticator extends AuthenticatorAdapter {

    @Autowired
    SysUserQueryService userBaseInfoQueryService;


    @Autowired
    TpaCodeRedisCacheService tpaCodeRedisCacheService;

    @Override
    public User authenticate(IntegrationParams integrationParams) {
        String tpOpenid=integrationParams.getUserloginid();
        User   user= loadUserByOpenid(tpOpenid,integrationParams);
        user.setTpaOpenid(integrationParams.getUserloginid());
        user.setAuthId(integrationParams.getAuthParameter("authId"));
        /**
         * 将sessionKey当作密码，加一次密，否则上层密码匹配不通过
         */
        user.setPassword(this.passwordEncoder.encode(integrationParams.getAuthParameter("password")));
        return user;
    }

    /**
     * 查找用户基础信息
     * @param openid 从微信服务器获取的openid
     * @return
     */
    public User loadUserByOpenid(String openid ,IntegrationParams integrationParams){
        String userid=integrationParams.getAuthParameter("userid");
        if(StringUtils.hasText(userid)){
            User user=userBaseInfoQueryService.getUserByUserid(userid, integrationParams.getDatas());
            return user;
        }
        List<User> users= userBaseInfoQueryService.queryByUserloginid(openid,"tpaOpenid",integrationParams.getDatas());
        if(users!=null && users.size()>0){
            if(users.size()==1){
                return users.get(0);
            }else{
                for (User user : users) {
                    if("0".equals(user.getMemType())){
                        return user;
                    }
                }
            }
            return users.get(0);
        }else{
            return null;
        }
    }
    @Override
    public void prepare(IntegrationParams integrationParams) {
        String code=integrationParams.getAuthParameter("password");
        if(!StringUtils.hasText(code)){
            throw new BizException("从微信服务器获取的密码串为空");
        }
        String redisCode=tpaCodeRedisCacheService.get(code);
        if(!StringUtils.hasText(redisCode) || !code.equals(redisCode)){
            throw new BizException("未验证通过");
        }
    }

    @Override
    public boolean supportAuthType(IntegrationParams integrationParams) {
        return AuthType.wechat_mini_app.name().equals(integrationParams.getAuthType());
    }
}
