package com.mdp.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author chenyc
 * springboot2.0以上只配置全局转换器不生效，需要加以下代码：
 * @date 2019-03-22 16:04:49
 * @since 1.0
 */
@Component
public class SpringContextListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private Set<Converter<?, ?>> converters;
    @Autowired
    private ConversionService conversionService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        GenericConversionService gcs = (GenericConversionService) conversionService;
        for (Converter<?, ?> converter : converters) {
            gcs.addConverter(converter);
        }
    }
}