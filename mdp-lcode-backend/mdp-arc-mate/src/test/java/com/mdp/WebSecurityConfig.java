package com.mdp;

import com.mdp.safe.client.jwt.JwtAuthenticationConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.jwt.JwtDecoder;

/**
 * com.qqkj.WebSecurityConfig
 *
 * @author chenyc
 * @date 2019/10/10
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    JwtAuthenticationConverter jwtConverter;


    @Autowired
    JwtDecoder jwtDecoder;


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/webjars/**","/*/arc/image/*/**.*");
    }

    /**
     * 允许匿名访问所有接口 主要是 oauth 接口
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/*/arc/image/**","/*/arc/archive/showArchive","/*/arc/file/**","/*/arc/archive/listNews",
        "/*/arc/category/list/tree").permitAll().anyRequest().authenticated().and().oauth2Client().and().logout().disable();
        http.oauth2Client().and().logout().disable();
        http.formLogin().usernameParameter("userloginid");
        http.oauth2Login();
        http.oauth2ResourceServer().jwt().decoder(jwtDecoder).jwtAuthenticationConverter(jwtConverter);
        http.csrf().disable();
    }

}
