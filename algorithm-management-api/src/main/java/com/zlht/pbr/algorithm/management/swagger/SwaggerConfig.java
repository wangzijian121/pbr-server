package com.zlht.pbr.algorithm.management.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author zi jian Wang
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket createManagementRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .groupName("management")
                .apiInfo(management())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zlht.pbr.algorithm.management.api.management.controller"))
                .build();
    }

    private ApiInfo management() {
        return new ApiInfoBuilder()
                .title("AI体态识别系统-算法商家后台管理接口")
                .description("AI body recognition system-algorithm business background management interface")
                .version("1.0.0")
                .build();
    }

    @Bean
    public Docket createDeveloperRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .groupName("developer")
                .apiInfo(developer())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zlht.pbr.algorithm.management.api.developer.controller"))
                .build();
    }

    private ApiInfo developer() {
        return new ApiInfoBuilder()
                .title("AI体态识别系统-算法开发者")
                .description("AI body recognition system-algorithm- developer interface")
                .version("1.0.0")
                .build();
    }
}