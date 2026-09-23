package com.portofolio.LMS;

import org.springframework.boot.SpringApplication;

public class TestLmsApplication {

	public static void main(String[] args) {
		SpringApplication.from(LmsApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
