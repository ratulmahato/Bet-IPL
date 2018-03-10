package com.learning.BetIPL;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class BetIplBoomApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(BetIplBoomApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

		return application.sources(BetIplBoomApplication.class);
	}
}
