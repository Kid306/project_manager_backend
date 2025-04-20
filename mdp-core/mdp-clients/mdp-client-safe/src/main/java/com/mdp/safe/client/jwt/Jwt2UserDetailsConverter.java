package com.mdp.safe.client.jwt;

import com.mdp.safe.client.entity.CommonUserDetails;
import com.mdp.safe.client.entity.SafeAuthority;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.UserDetailsConverter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.HashSet;

@Service
public class Jwt2UserDetailsConverter implements UserDetailsConverter {


    /**
     * 增加ext 扩展字段，userInfo=username|tpaOpenid|maxDataLvl|deptids|deptname|branchIds|branchName|authId 分别为 用户中文名称|第三方登录编号|最高数据访问等级|部门编号|部门名称|机构编号|机构名称|授权码
     * @param token
     * @return
     */
    @Override
    public CommonUserDetails convert(Authentication token) {
        String udk="userDetails";
        CommonUserDetails userDetails=new CommonUserDetails();
        Jwt jwt=(Jwt) token.getPrincipal();
        User user=new User();
        user.setUserid(jwt.getSubject());
        String userInfo=jwt.getClaim("userInfo");
        if(StringUtils.hasText(userInfo)){
            user.initByString(userInfo);
        }

        userDetails=CommonUserDetails.fromUser(user);
        String roles=jwt.getClaim("roles");
        Collection<GrantedAuthority> tokenAus= (Collection<GrantedAuthority>) token.getAuthorities();
        boolean hadConver=false;
        if(tokenAus!=null && tokenAus.size()>0){
            hadConver=tokenAus.stream().findFirst().get() instanceof SafeAuthority;
        }
        if(hadConver){
            userDetails.setAuthorities(tokenAus);
        }else {
            Collection<GrantedAuthority> authoritiesAdd=new HashSet<>();
            if(tokenAus!=null && tokenAus.size()>0){
                for (GrantedAuthority tokenAu : tokenAus) {
                    GrantedAuthority sa=new SafeAuthority(tokenAu.getAuthority());
                    authoritiesAdd.add(sa);
                }
            }
            if(StringUtils.hasText(roles)){
                Collection<GrantedAuthority> authorities = SafeAuthority.toGasFromRolesString(roles);
                if(authorities!=null){
                    authoritiesAdd.addAll(authorities);
                }
            }
            userDetails.setAuthorities(authoritiesAdd);
        }
        return userDetails;
    }

    @Override
    public boolean supportToken(Authentication token) {
        if(token==null){
            return false;
        }
        return token.getPrincipal() instanceof Jwt;
    }


}
