package com.aequilibrium.conf;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<String>(Arrays.asList("application/json")); 


	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2)
								.select() 
								.apis(RequestHandlerSelectors.basePackage("com.aequilibrium.resource"))
								.paths(PathSelectors.regex("/transformer.*"))
								.build() 
								//.tags(new Tag("system","Operations available to CRM"), new Tag("customers", "Operations available to customers / container owners"))
								.apiInfo(getApiInfo())
								.produces(DEFAULT_PRODUCES_AND_CONSUMES)
								.consumes(DEFAULT_PRODUCES_AND_CONSUMES); 

	} 

	private ApiInfo getApiInfo() {
		return new ApiInfoBuilder().title("Transformer API")
								   .description("Spring Boot REST API for Transformer integration")
								   .version("1.0.0")
								   .license("Apache License Version 2.0")
								   .contact(new Contact("Lucas Lima","https://www.linkedin.com/in/lucaslimadev/", "luca.ds.lima@gmail.com"))
								   .build();
	}

}
