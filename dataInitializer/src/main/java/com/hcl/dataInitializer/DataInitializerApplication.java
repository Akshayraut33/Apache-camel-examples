package com.hcl.dataInitializer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.hcl.dataInitializer.model.RecommendedPost;
import com.hcl.dataInitializer.service.RecommendedPostService;

@SpringBootApplication
public class DataInitializerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataInitializerApplication.class, args);
		System.out.println("Working ....");
	}
	

}
