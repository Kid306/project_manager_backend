package com.mdp.login.integration.authenticator.email;

import com.mdp.login.integration.IntegrationParams;
import com.mdp.login.integration.authenticator.AuthenticatorAdapter;
import com.mdp.login.service.SysUserQueryService;
import com.mdp.safe.client.dict.AuthType;
import com.mdp.safe.client.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;


/**
 * 短信验证码登录，也可以认为是密码登录的一种扩展
 * 格式 url: http://${domain}/api/m1/oauth2/oauth/token?grant_type=password&username=${手机号码}&password=${用户收到的短信验证码明文}&scope=all&auth_type=sms
 * @author chenyc
 * @date 2021-01-12
 **/
@Component
public class EmailAuthenticator extends AuthenticatorAdapter {



    @Autowired
    SysUserQueryService userBaseInfoQueryService;

    /**
     * 查找用户基础信息
     * @param userloginid 登录账户
     * @return
     */
    public User getByUserloginid(String userloginid, IntegrationParams integrationParams){
        String userid=integrationParams.getAuthParameter("userid");
        if(StringUtils.hasText(userid)){
            User user=userBaseInfoQueryService.getUserByUserid(userid, integrationParams.getDatas());
            if(user!=null && userloginid.equals(user.getEmail())){
                return user;
            }
            return null;
        }
        List<User> users= userBaseInfoQueryService.queryByUserloginid(userloginid,"email",integrationParams.getDatas());
        if(users!=null && users.size()>0){
            if(users.size()==1){
                return users.get(0);
            }else{
                for (User user : users) {
                    if(userloginid.equals(user.getEmail())){
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

    }

    @Override
    public User authenticate(IntegrationParams integrationParams) {
        User user= this.getByUserloginid(integrationParams.getUserloginid(),integrationParams);
        if(user==null){
            return null;
        }
        /**
         * 将短信验证码当作密码，加一次密，否则上层密码匹配不通过
         */
        user.setPassword(this.passwordEncoder.encode(integrationParams.getAuthParameter("password")));
        return user;
    }

    @Override
    public boolean supportAuthType(IntegrationParams integrationParams) {
        return AuthType.email.name().equals(integrationParams.getAuthType());
    }

    @Override
    public void complete(IntegrationParams integrationParams) {

    }
}
