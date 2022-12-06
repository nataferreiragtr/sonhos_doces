package com.rj.doceria.sonhosDoces;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

@EntityScan(basePackages = {"com.rj.doceria.sonhosDoces.domain.model"})
@SpringBootApplication
public class SonhosDocesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SonhosDocesApplication.class, args);
	}

	@Bean
	public OpenAPI openApiConfig() {
		return new OpenAPI().info(apiInfo());
	}

	private Info apiInfo() {
		return new Info()
				.title("Gestão de Doceria")
				.description("Sistema que gere a Doceria Sonhos Doces")
				.version("1.0.0");
	}
}
