package com.blog;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogapplicationApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogapplicationApplication.class, args);
		System.out.println("Run Blog.....");
		
	}
// this is commet
         
	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
		
	}
	
	
}
