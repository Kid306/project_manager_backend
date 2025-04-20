package com.mdp.login.integration.authenticator;

import com.mdp.login.integration.IntegrationParams;
import com.mdp.safe.client.entity.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author chenyc
 * @date 2021-01-12
 **/
public interface IntegrationAuthenticator {

    /**
     * 处理集成认证
     * @param integrationParams
     * @return
     */
    User authenticate(IntegrationParams integrationParams);


    /**
     * 进行预处理
     * @param integrationParams
     */
    void prepare(IntegrationParams integrationParams);

     /**
     * 判断是否支持集成认证类型
     * @param integrationParams
     * @return
     */
    boolean supportAuthType(IntegrationParams integrationParams);

    /** 认证结束后执行
     * @param integrationParams
     */
    void complete(IntegrationParams integrationParams);

    /**
     * 加载用户对应的角色
     */
    Collection<? extends GrantedAuthority> loadAuthorities(User user,IntegrationParams integrationParams) ;

}
