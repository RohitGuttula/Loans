package com.microservices.Loans;

import com.microservices.Loans.DTO.LoansContactInfoDTO;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(LoansContactInfoDTO.class)
@OpenAPIDefinition(
		info = @Info(
				title = "Loans Microservice Rest Api documentation",
				description = "Eazybank Loans Microservice Documentation",
				version = "V1",
				contact = @Contact(
						name = "Rohit Guttula",
						email="rohitguttula228@gmail.com",
						url = "www.rohit.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.eazybytes.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description =  "EazyBank Loans microservice REST API Documentation",
				url = "https://www.eazybytes.com/swagger-ui.html"
		)
)

public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}

}
