package com.project.run;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.project.controller","com.project.config","com.project.service","com.project.filter","com.project.listener","com.project.util"})
@MapperScan("com.project.dao")
public class ApplicationDemo {
	public static void main(String[] args) {
		SpringApplication.run(ApplicationDemo.class, args);
	}
}
