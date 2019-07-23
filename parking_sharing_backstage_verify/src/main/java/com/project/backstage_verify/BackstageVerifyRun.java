package com.project.backstage_verify;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.project.controller")
@ComponentScan("com.project.service")
@MapperScan("com.project.dao")
@EnableEurekaClient // 代表客户端
public class BackstageVerifyRun {

	public static void main(String[] args) {
		SpringApplication.run(BackstageVerifyRun.class, args);
	}

}
