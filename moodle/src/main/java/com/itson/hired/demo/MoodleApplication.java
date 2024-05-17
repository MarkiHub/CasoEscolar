package com.itson.hired.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.itson.hired.demo.*")
@EnableAutoConfiguration
public class MoodleApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoodleApplication.class, args);
	}

}
