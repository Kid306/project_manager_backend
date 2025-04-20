package com.mdp.core.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mdp.core.api.CacheHKVService;
import com.mdp.core.api.ContextEnvService;
import com.mdp.core.api.Sequence;
import com.mdp.core.filter.SetCharacterEncodingFilter;
import com.mdp.core.filter.XssFilter;
import com.mdp.core.log.MdpLogFilter;
import com.mdp.core.resolver.BizExceptionResolver;
import com.mdp.core.service.DefaultHKVService;
import com.mdp.core.service.DefaultQueryEnvService;
import com.mdp.core.service.SequenceService;
import com.mdp.core.utils.BaseUtils;
import com.mdp.dm.base.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;
@ComponentScan(basePackages={"com.mdp"})
@Configuration
@EnableConfigurationProperties(MdpProperties.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MdpAutoConfiguration {

	Logger log= LoggerFactory.getLogger(MdpAutoConfiguration.class);

	private MdpProperties properties;

	public MdpProperties getProperties() {
		return properties;
	}
	@Autowired(required = false)
	public void setProperties(MdpProperties properties) {
		this.properties = properties;
	}


	@Bean
	BizExceptionResolver bizExceptionResolver(){
		BizExceptionResolver bizExceptionResolver=new BizExceptionResolver();
		return bizExceptionResolver;
	}

	@Bean
    public SessionLocaleResolver sessionLocaleResolver2() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        // 默认语言
        slr.setDefaultLocale(Locale.CHINESE);
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor2() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        // 参数名
        lci.setParamName("lang");
        return lci;
    }



	@ConditionalOnMissingBean(ContextEnvService.class)
	@Bean
	public ContextEnvService contextEnvService(){
		return new DefaultQueryEnvService();
	}

	@ConditionalOnMissingBean(SequenceService.class)
	@Bean
	public SequenceService sequenceService(){
		return new SequenceService();
	}

	@ConditionalOnMissingBean(Sequence.class)
	@Bean
	public Sequence sequence(){
		return new SequenceService();
	}

	@Bean
	@ConditionalOnMissingBean(DataMetaBaseService.class)
	public DataMetaBaseService dmDataMetaBaseService(){
		return new DefaultDataMetaBaseService();
	}

	@Bean
	@ConditionalOnMissingBean(TableDataBaseService.class)
	public TableDataBaseService dmTableDataBaseService(){
		return new DefaultTableDataBaseService();
	}
	@Bean
	@ConditionalOnMissingBean(DdlBaseService.class)
	public DdlBaseService dmDdlBaseService(){
		return new DefaultDdlBaseService();
	}

	@Bean
	@ConditionalOnMissingBean(CacheHKVService.class)
	public CacheHKVService cacheHKVService(){
		return new DefaultHKVService();
	}


	@Bean
	public FilterRegistrationBean mdpXssFilter() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new XssFilter());//添加过滤器
		registration.addUrlPatterns("/*");//设置过滤路径，/*所有路径
		registration.setName("mdpXssFilter");//设置优先级
		registration.setOrder(Ordered.HIGHEST_PRECEDENCE);//设置优先级
		return registration;
	}

    @Bean
    public FilterRegistrationBean mdpSetCharacterEncodingFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new SetCharacterEncodingFilter());//添加过滤器
        registration.addUrlPatterns("/*");//设置过滤路径，/*所有路径
        registration.setName("mdpSetCharacterEncodingFilter");//设置优先级
        registration.setOrder(1);//设置优先级
        return registration;
    }

	@Autowired
	@Bean
	public FilterRegistrationBean mdpLogFilter(ContextEnvService contextEnvService) {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		MdpLogFilter filter=new MdpLogFilter();
		filter.setContextEnvService(contextEnvService);
		registration.setFilter(filter);//添加过滤器
		registration.addUrlPatterns("/*");//设置过滤路径，/*所有路径
		registration.setName("mdpLogFilter");//设置优先级
		registration.setOrder(Ordered.HIGHEST_PRECEDENCE);//设置优先级
		return registration;
	}

	@Autowired
    public void setObjectMapperProperties(ObjectMapper objectMapper ) {
		BaseUtils.objectMapper=objectMapper;
    }
    @Bean
	@ConditionalOnMissingBean(RestTemplate.class)
    RestTemplate restTemplate(){
		RestTemplate restTemplate=new RestTemplate();
		return restTemplate;
	}
}
