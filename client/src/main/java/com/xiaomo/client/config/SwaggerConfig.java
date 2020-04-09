///*
//package com.xiaomo.client.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//@EnableSwagger2
//public class SwaggerConfig implements WebMvcConfigurer {
//
//    @Value("${swagger.host}")
//    private String host;
//
//    //swagger2的配置文件，这里可以配置swagger2的一些基本的内容，比如扫描的包等等
//    @Bean
//    public Docket createRestApi() {
//        return new Docket(DocumentationType.SWAGGER_2).host(host).apiInfo(apiInfo()).select()
//                //为当前包路径
//                .apis(RequestHandlerSelectors.basePackage("com.deepexi.domain.marketing.controller")).paths(PathSelectors.any()).build();
//    }
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//    }
//
//    //构建 api文档的详细信息函数,注意这里的注解引用的是哪个
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                //页面标题
//                .title("Spring cloud 脚手架使用 Swagger2 构建RESTful API")
//                //创建人
//                .contact(new Contact("deepexi", "http://www.deepexi.com", "hudong@deepexi.com"))
//                //版本号
//                .version("1.0")
//                //描述
//                .description("api管理").build();
//    }
//
//}*/
