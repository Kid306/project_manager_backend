package com.mdp;


import com.mdp.safe.client.jwt.JwtAuthenticationConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.jwt.JwtDecoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    JwtAuthenticationConverter jwtConverter;

    @Autowired
    JwtDecoder jwtDecoder;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/webjars/**");
    }

    /**
     * 允许匿名访问所有接口 主要是 oauth 接口
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(
                "/actuator/**",
                "/*/sys/userTpaInvite/checkInviteId",
                "/login/token",
                "/*/sys/user/noauth/detail",
                "/*/sys/userFans/fansCpd",
                "/*/sys/userSvr/list",
                "/*/sys/user/crowd/list",
                "/*/sys/branch/add",
                "/*/sys/branch/list",
                "/*/meta/itemOption/list/byItemCode",
                "/*/meta/itemOption/list/byItemIds",
                "/*/arc/img/image/**",
                "/*/arc/image/**",
                "/*/meta/item/dicts",
                "/*/meta/item/sysParam",
                "/*/plat/platform/detail",
                "/druid/**",
                "/swagger-ui.html",
                "/webjars/**",
                "/swagger-ui/**",
                "/swagger-resources/**",
                "/v2/*",
                "/csrf",
                "/").permitAll().anyRequest().authenticated();
        http.oauth2Client().and().logout().disable();
        http.oauth2Login();
        http.formLogin().disable();
        http.oauth2ResourceServer().jwt().decoder(jwtDecoder).jwtAuthenticationConverter(jwtConverter);
        http.csrf().disable();
    }
}