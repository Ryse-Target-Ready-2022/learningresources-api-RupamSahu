package com.tgt.rysetii.learningresourcesapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
public class LearningresourcesApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearningresourcesApiApplication.class, args);
		//System.out.println("Hello World!!!");
	}

}
