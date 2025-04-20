package com.mdp.safe.client.utils;

import com.mdp.safe.client.entity.CommonUserDetails;
import org.springframework.security.core.Authentication;

public interface UserDetailsConverter {

    CommonUserDetails convert(Authentication token);

    boolean supportToken(Authentication token);

}
