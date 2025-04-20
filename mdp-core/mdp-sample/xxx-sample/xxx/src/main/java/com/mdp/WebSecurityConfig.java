package com.mdp;


import com.mdp.safe.client.jwt.JwtAuthenticationConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
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
        http.authorizeRequests().antMatchers("/actuator/**","/*/safe/app/auth**",
                "/*/sys/userTpaInvite/checkInviteId",
                "/*/sys/user/noauth/detail",
                "/*/sys/userFans/fansCpd",
                "/*/sys/userSvr/list",
                "/*/sys/user/crowd/list",
                "/*/sys/branch/listBranchNoAuth",
                "/*/sys/branch/add",
                "/*/sys/branch/list",
                "/*/sys/branch/listBranchNoAuth",
                "/*/sys/branch/addBranchNoAuth",
                "/*/sys/dept/listDeptAndChidlDeptByBranchIdNoAuth",
                "/*/sys/dept/addDeptNoAuth",
                "/*/sys/userDept/batchEditNoauth",
                "/*/list/byItemCode",
                "/*/list/byItemIds",
                "/*/item/dicts",
                "/*/list/sysParam","/druid/**",
                "/swagger-ui.html",
                "/webjars/**",
                "/swagger-ui/**",
                "/swagger-resources/**",
                "/v2/*",
                "/csrf",
                "/"
        ).permitAll();

        //http.oauth2Client().and().logout().disable();
       // http.oauth2Login();
        http.oauth2ResourceServer().jwt().decoder(jwtDecoder).jwtAuthenticationConverter(jwtConverter);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);//禁用session
        http.csrf().disable();
    }
}