package com.verificador.catalogos;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@SpringBootApplication
public class CatalogosApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalogosApplication.class, args);
	}
	
	@Bean
	public OpenAPI openAPIConfiguration(@Value("${spring.application.name}") String appName) {
		return new OpenAPI()
				.info(new Info().title(appName)
				.version("v1"));
	}

}
