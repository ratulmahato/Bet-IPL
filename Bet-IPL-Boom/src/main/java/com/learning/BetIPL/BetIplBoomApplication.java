package com.learning.BetIPL;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.learning.BetIPL")
public class BetIplBoomApplication {

	public static void main(String[] args) {
		SpringApplication.run(BetIplBoomApplication.class, args);
	}
}
