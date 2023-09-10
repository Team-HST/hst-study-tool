package com.hst.studytool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class StudyToolApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudyToolApplication.class, args);
	}



}
