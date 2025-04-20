package com.mdp.login.service;

import com.mdp.login.integration.IntegrationParams;
import com.mdp.login.integration.IntegrationParamsContext;
import com.mdp.login.integration.authenticator.IntegrationAuthenticator;
import com.mdp.qx.DataLvl;
import com.mdp.safe.client.entity.CommonUserDetails;
import com.mdp.safe.client.entity.SafeAuthority;
import com.mdp.safe.client.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * UserDetailsServiceImpl
 *
 * @author chenyc
 * @date 2021/01/11
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    Map<String,UserDetails> cache=new ConcurrentHashMap<>();

    private List<IntegrationAuthenticator> authenticators;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            IntegrationParams integrationParams = IntegrationParamsContext.get();
            if(!StringUtils.hasText(integrationParams.getUserloginid())){
                integrationParams.setUserloginid(username);
            }
            String password=integrationParams.getAuthParameter("password");

            String branchId=integrationParams.getAuthParameter("branchId");
            String deptid=integrationParams.getAuthParameter("deptid");

            String userType=integrationParams.getUserType();
            /**String cacheKey=integrationParams.getAuthType()+"_"+integrationParams.getUserloginid()+"_"+password+"_"+branchId+"_"+deptid+"_"+userType;
             CommonUserDetails ud= (CommonUserDetails) cache.get(cacheKey);
            if(ud!=null){
                return ud;
            }
             **/
            CommonUserDetails ud=null;
            IntegrationAuthenticator authenticator=this.findIntegrationAuthenticator(integrationParams);
            User user= authenticator.authenticate(integrationParams);
            if(user==null){
                return null;
            }
            user.setUserType(userType);
            ud=CommonUserDetails.fromUser(user);
            Set<GrantedAuthority> safeAuthorities= (Set<GrantedAuthority>) authenticator.loadAuthorities(user,integrationParams);
            if(safeAuthorities==null || safeAuthorities.isEmpty() || !safeAuthorities.stream().anyMatch(x->x.getAuthority().equals("user"))){
                SafeAuthority sa=new SafeAuthority();
                sa.setAuthority("user");
                sa.setRolename("普通用户");
                sa.setDataLvl(DataLvl.myDept.getLvl());
                if(safeAuthorities==null){
                    safeAuthorities=new HashSet<>();
                }
                safeAuthorities.add(sa);
            }
            ud.setAuthorities(safeAuthorities);
            //cache.put(cacheKey,ud);
           return ud;
    }



    public IntegrationAuthenticator findIntegrationAuthenticator(IntegrationParams integrationParams){
        if (this.authenticators != null) {
            for (IntegrationAuthenticator authenticator : authenticators) {
                if (authenticator.supportAuthType(integrationParams)) {
                    return authenticator;
                }
            }
        }
        return null;
    }
    @Autowired(required = false)
    public void setIntegrationAuthenticators(List<IntegrationAuthenticator> authenticators) {
        this.authenticators = authenticators;
    }

    @Scheduled(cron = "0 0/5 * * * ?")
    public void clearCache(){
        cache.clear();
    }
}
