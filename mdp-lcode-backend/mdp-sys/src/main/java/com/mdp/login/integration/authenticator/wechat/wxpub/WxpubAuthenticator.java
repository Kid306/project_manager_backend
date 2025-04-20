package com.mdp.login.integration.authenticator.wechat.wxpub;


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
 * 微信公众号集成认证
 * @author chenyc
 * @date 2021-01-12
 **/
@Service
public class WxpubAuthenticator extends AuthenticatorAdapter {

    @Autowired
    SysUserQueryService userBaseInfoQueryService;

    @Autowired
    TpaCodeRedisCacheService tpaCodeRedisCacheService;

    @Override
    public User authenticate(IntegrationParams integrationParams) {
        String tpaOpenid=integrationParams.getUserloginid();
        User   user= loadUserByOpenid(tpaOpenid,integrationParams);
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
        return AuthType.wechat_wxpub.name().equals(integrationParams.getAuthType());
    }
}
