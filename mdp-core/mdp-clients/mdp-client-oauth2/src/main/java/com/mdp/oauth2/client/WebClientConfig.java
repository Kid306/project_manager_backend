/*
 * Copyright 2002-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mdp.oauth2.client;

import com.mdp.oauth2.client.response.MdpAuthorizationCodeTokenResponseClient;
import com.mdp.oauth2.client.response.MdpClientCredentialsTokenResponseClient;
import com.mdp.oauth2.client.response.MdpPasswordTokenResponseClient;
import com.mdp.oauth2.client.response.MdpRefreshTokenTokenResponseClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.*;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;


/**
 * @author Joe Grandja
 */
@Configuration
@ConditionalOnMissingClass({"org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction"})
public class WebClientConfig {

	//@Value("${spring.application.name:}")
	String defaultClientRegistrationId="def-client";

	@ConditionalOnMissingBean
	@Bean
	RestTemplate restTemplate(){
		RestTemplate restTemplate=new RestTemplate();
		return restTemplate;
	};
	@ConditionalOnMissingBean
	@Bean
	WebClient webClient(OAuth2AuthorizedClientManager authorizedClientManager) {
		ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2 =
				new ServletOAuth2AuthorizedClientExchangeFilterFunction(authorizedClientManager);
		oauth2.setDefaultOAuth2AuthorizedClient(true);
		oauth2.setDefaultClientRegistrationId(defaultClientRegistrationId);
			return WebClient.builder()
					.apply(oauth2.oauth2Configuration())
					.build();
	}
	@ConditionalOnMissingBean
	@Bean
	OAuth2AuthorizedClientManager authorizedClientManager(ClientRegistrationRepository clientRegistrationRepository,
														  OAuth2AuthorizedClientService oAuth2AuthorizedClientService) {

		RestTemplate restTemplate=restTemplate();
		MdpAuthorizationCodeTokenResponseClient mdpAuthorizationCodeTokenResponseClient=new MdpAuthorizationCodeTokenResponseClient();
		mdpAuthorizationCodeTokenResponseClient.setRestOperations(restTemplate);

		MdpClientCredentialsTokenResponseClient clientCredentialsTokenResponseClient= new MdpClientCredentialsTokenResponseClient();
		clientCredentialsTokenResponseClient.setRestOperations(restTemplate);//支持客户端负载均衡，支持通过http://微服务名称/方式访问

		MdpPasswordTokenResponseClient mdpPasswordTokenResponseClient=new MdpPasswordTokenResponseClient();
		mdpPasswordTokenResponseClient.setRestOperations(restTemplate);

		MdpRefreshTokenTokenResponseClient mdpRefreshTokenTokenResponseClient=new MdpRefreshTokenTokenResponseClient();
		mdpRefreshTokenTokenResponseClient.setRestOperations(restTemplate);

		OAuth2AuthorizedClientProvider authorizedClientProvider =
				OAuth2AuthorizedClientProviderBuilder.builder()
						.authorizationCode()
						.refreshToken(x->x.accessTokenResponseClient(mdpRefreshTokenTokenResponseClient))
						.clientCredentials(x->x.accessTokenResponseClient(clientCredentialsTokenResponseClient))
						.password(x->x.accessTokenResponseClient(mdpPasswordTokenResponseClient))
						.build();
		AuthorizedClientServiceOAuth2AuthorizedClientManager authorizedClientManager = new AuthorizedClientServiceOAuth2AuthorizedClientManager(
				clientRegistrationRepository, oAuth2AuthorizedClientService);
		authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);

		return authorizedClientManager;
	}
}