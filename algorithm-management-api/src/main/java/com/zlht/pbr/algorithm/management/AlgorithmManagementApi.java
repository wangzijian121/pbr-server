package com.zlht.pbr.algorithm.management;


import com.zlht.pbr.algorithm.management.tools.service.EnvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author ziji Wang
 */
@SpringBootApplication
@EnableOpenApi
public class AlgorithmManagementApi implements ApplicationRunner {

    @Autowired
    private EnvService envService;

    public static void main(String[] args) {
        SpringApplication.run(AlgorithmManagementApi.class, args);

    }

    @Override
    public void run(ApplicationArguments args) {
        envService.printSwaggerAddress();
    }
}