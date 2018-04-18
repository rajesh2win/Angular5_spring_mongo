package com.mla;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReactApplication extends SpringBootServletInitializer
{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder ReactApplication) {
		return ReactApplication.sources(ReactApplication.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(ReactApplication.class, args);
	}
}
