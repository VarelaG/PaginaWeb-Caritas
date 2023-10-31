package com.ppyl.Caritas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication (exclude = {SecurityAutoConfiguration.class})

public class CaritasApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaritasApplication.class, args);
	}

}
