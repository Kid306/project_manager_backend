package com.mdp.login.integration.authenticator.onepark;


import com.mdp.core.err.BizException;
import com.mdp.login.integration.IntegrationParams;
import com.mdp.login.integration.authenticator.AuthenticatorAdapter;
import com.mdp.login.service.SysUserQueryService;
import com.mdp.onepark.OneParkAuthUtil;
import com.mdp.onepark.dto.OneParkUserResDto;
import com.mdp.safe.client.cache.TpaCodeRedisCacheService;
import com.mdp.safe.client.dict.AuthType;
import com.mdp.safe.client.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * onepark集成认证
 * @author chenyc
 * @date 2021-01-12
 **/
@Service
public class OneparkAuthenticator extends AuthenticatorAdapter {

    @Autowired
    SysUserQueryService userBaseInfoQueryService;

    @Autowired
    TpaCodeRedisCacheService tpaCodeRedisCacheService;

    @Autowired
    OneParkAuthUtil oneParkAuthUtil;

    @Override
    public User authenticate(IntegrationParams integrationParams) {
        String code=integrationParams.getAuthParameter("password");
        OneParkUserResDto userResDto=oneParkAuthUtil.getUserInfoByCode(code);
        if(userResDto==null){
            throw new BizException("验证码已过期");
        }
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
            throw new BizException("从onepark获取的密码串为空");
        }
    }

    @Override
    public boolean supportAuthType(IntegrationParams integrationParams) {
        return "onepark_code".equals(integrationParams.getAuthType());
    }
}
