package com.mdp.login.integration.authenticator;

import com.mdp.core.err.BizException;
import com.mdp.core.utils.ObjectTools;
import com.mdp.login.integration.IntegrationParams;
import com.mdp.login.util.LockUtil;
import com.mdp.safe.client.dict.AuthType;
import com.mdp.safe.client.entity.User;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;
import java.util.Map;

/**
 * 默认登录处理,密码方式
 * 格式 url: http://${domain}/api/m1/oauth2/oauth/token?grant_type=password&username=${username}&password=${前端密码MD5加密后的密码}&scope=all
 * @author chenyc
 * @date 2021-01-12
 **/
@Component
public class PasswordDisplayUseridAuthenticator extends AuthenticatorAdapter{



    @Override
    public User authenticate(IntegrationParams integrationParams) {
        User user= getByUserloginid(integrationParams.getUserloginid(),integrationParams);
        if(user==null){
            return null;
        }
        if(user!=null && "1".equals(user.get("locked"))){
            String lockType= (String) user.get("lockType");
            String lockRemark= (String) user.get("lockRemark");
            String statusText= LockUtil.getLockMsgByLockType(lockType);
            if(!StringUtils.hasText(statusText)){
                statusText="lock-type-9 账户已锁定";
            }
            HttpHeaders httpHeaders=new HttpHeaders();
            throw  new WebClientResponseException(401,statusText, httpHeaders,statusText.getBytes() ,null,null);
            //throw new OAuth2AuthenticationException(new OAuth2Error("user-locked","账户锁定，请检查是否已验证邮箱",null));
        }else if(user!=null && !"1".equals(user.get("locked"))){
            if(("1".equals(user.getMemType())  || "2".equals(user.getMemType()) ) && StringUtils.hasText(user.getOrgId()) && "1".equals(user.getCpaOrg())){//如果是机构会员，检查机构是否可用
               Map<String,Object> org= getByUserloginid(integrationParams.getUserloginid(),integrationParams);
               String lockType="";
               if(org==null || org.isEmpty() ){
                   lockType="7";
               }else if(!"1".equals(org.get("orgStatus"))){
                   lockType="8";
               }
               if(StringUtils.hasText(lockType)){
                   String statusText= LockUtil.getLockMsgByLockType(lockType);
                   if(!StringUtils.hasText(statusText)){
                       statusText="lock-type-L 账户已锁定 ";
                   }
                   HttpHeaders httpHeaders=new HttpHeaders();
                   throw  new WebClientResponseException(401,statusText, httpHeaders,statusText.getBytes() ,null,null);
               }

            }
        }
        String password= (String) integrationParams.getDatas().get("password");
        if(ObjectTools.isEmpty(password)){
            throw new BizException("password-required","密码不能为空");
        }
        if(!passwordEncoder.matches(password,user.getPassword())){
            throw new BizException("password-error","密码不正确");
        }
        return user;
    }


    /**
     * 查找用户基础信息
     * @param displayUserid 前台登陆编号
     * @return
     */
    public User getByUserloginid(String displayUserid,IntegrationParams integrationParams){
        List<User> users= userBaseInfoQueryService.queryByUserloginid(displayUserid,"all",integrationParams.getDatas());
        //为了测试方便，将密码设置为与前端上送的密码一致
        //user.setPassword(passwordEncoder.encode(integrationParams.getAuthParameter("password")));
        return users!=null && users.size()>0?users.get(0):null;
    }

    @Override
    public boolean supportAuthType(IntegrationParams integrationParams) {
        return AuthType.password_display_userid.name().equals(integrationParams.getAuthType());
    }
}
