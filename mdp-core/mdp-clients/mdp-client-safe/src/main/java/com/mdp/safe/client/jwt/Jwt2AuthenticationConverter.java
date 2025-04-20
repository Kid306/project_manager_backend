package com.mdp.safe.client.jwt;

import com.mdp.safe.client.entity.SafeAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.HashSet;

/**
 * 修改 extractAuthorities 陈裕财
 * 需要通过如下配置生效
 *
 * @Autowired
 * MdpJwtAuthenticationConverter jwtConverter;
 * http.oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtConverter);
 */
@Service
public class Jwt2AuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    private Converter<Jwt, Collection<GrantedAuthority>> jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
    private String principalClaimName;

    @Autowired
    public Jwt2AuthenticationConverter() {
    }

    public final AbstractAuthenticationToken convert(Jwt jwt) {
        Collection<GrantedAuthority> authorities = this.extractAuthorities(jwt);
        if (this.principalClaimName == null) {
            return new JwtAuthenticationToken(jwt, authorities);
        } else {
            String name = (String)jwt.getClaim(this.principalClaimName);
            AbstractAuthenticationToken token= new JwtAuthenticationToken(jwt, authorities, name);
            return token;

        }
    }

    /** @deprecated */
    @Deprecated
    protected Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {
        String roles=jwt.getClaim("roles");
        Collection<GrantedAuthority> authorities2=(Collection)this.jwtGrantedAuthoritiesConverter.convert(jwt);
        Collection<GrantedAuthority> sas2=new HashSet<>();
        if(authorities2!=null){
            for (GrantedAuthority grantedAuthority : authorities2) {
                SafeAuthority sa=new SafeAuthority();
                sa.setAuthority(grantedAuthority.getAuthority());
                sas2.add(sa);
            }
        }
        if(StringUtils.hasText(roles)){
            Collection<GrantedAuthority> authorities = SafeAuthority.toGasFromRolesString(roles);
             if(authorities!=null){
                 authorities.addAll(sas2);
                 return authorities;
             }
        }
        return sas2;

    }

    public void setJwtGrantedAuthoritiesConverter(Converter<Jwt, Collection<GrantedAuthority>> jwtGrantedAuthoritiesConverter) {
        Assert.notNull(jwtGrantedAuthoritiesConverter, "jwtGrantedAuthoritiesConverter cannot be null");
        this.jwtGrantedAuthoritiesConverter = jwtGrantedAuthoritiesConverter;
    }

    public void setPrincipalClaimName(String principalClaimName) {
        Assert.hasText(principalClaimName, "principalClaimName cannot be empty");
        this.principalClaimName = principalClaimName;
    }
}
