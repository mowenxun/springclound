package com.xiaomo.client.extension;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

/**
 * MVC配置类
 *
 * @author ffq
 * @version 1.0
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Autowired
    private DateConverter dateConverter;

    @Autowired
    private RequestMappingHandlerAdapter handlerAdapter;

    /**
     * 配置全局日期转换器
     */
    @Bean
    public ConversionService getConversionService(DateConverter dateConverter) {
        FormattingConversionServiceFactoryBean factoryBean = new FormattingConversionServiceFactoryBean();

        Set<Converter> converters = new HashSet<>();

        converters.add(dateConverter);

        factoryBean.setConverters(converters);

        return factoryBean.getObject();
    }


    @PostConstruct
    public void initEditableAvalidation() {

        ConfigurableWebBindingInitializer initializer = (ConfigurableWebBindingInitializer)handlerAdapter.getWebBindingInitializer();
        if(initializer.getConversionService()!=null) {
            GenericConversionService genericConversionService = (GenericConversionService)initializer.getConversionService();

            genericConversionService.addConverter(dateConverter);

        }

    }
}
