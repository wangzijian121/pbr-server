package com.zlht.pose.management.api.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket createManagementRestApi() {
        return new Docket(DocumentationType.OAS_30) // v2 不同
                .groupName("management")
                .apiInfo(Management())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zlht.pose.management.api.controller")) // 设置扫描路径
                .build();
    }

    private ApiInfo Management() {
        return new ApiInfoBuilder()
                .title("AI体态识别系统-算法商家后台管理接口") // 设置文档标题
                .description("AI body recognition system-algorithm business background management interface") // 设置文档描述
                .version("1.0.0") // 设置文档版本
                .build();
    }

    @Bean
    public Docket createDeveloperRestApi() {
        return new Docket(DocumentationType.OAS_30) // v2 不同
                .groupName("developer")
                .apiInfo(Developer())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zlht.pose.management.api.controller.developer")) // 设置扫描路径
                .build();
    }

    private ApiInfo Developer() {
        return new ApiInfoBuilder()
                .title("AI体态识别系统-算法开发者") // 设置文档标题
                .description("AI body recognition system-algorithm- developer interface") // 设置文档描述
                .version("1.0.0") // 设置文档版本
                .build();
    }
}