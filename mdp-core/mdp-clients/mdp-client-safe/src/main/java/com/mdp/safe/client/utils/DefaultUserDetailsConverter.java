package com.mdp.safe.client.utils;

import com.mdp.safe.client.entity.CommonUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserDetailsConverter implements UserDetailsConverter {


    @Override
    public CommonUserDetails convert(Authentication token) {
        return (CommonUserDetails) token.getPrincipal();
    }

    @Override
    public boolean supportToken(Authentication token) {
        if(token==null){
            return false;
        }
        return token.getPrincipal() instanceof CommonUserDetails;
    }
}
