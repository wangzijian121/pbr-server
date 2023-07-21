package com.zlht.pose.management.api;


import com.zlht.pose.management.tools.service.EnvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.oas.annotations.EnableOpenApi;

@ComponentScan("com.zlht.pose.management")
@SpringBootApplication
@EnableOpenApi
public class PoseManagementApi implements ApplicationRunner {

    @Autowired
    private EnvService envService;

    public static void main(String[] args) {
        SpringApplication.run(PoseManagementApi.class, args);

    }

    @Override
    public void run(ApplicationArguments args) {
        envService.printSwaggerAddress();
    }
}