package com.product.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI openAPI() {
		OpenAPI openAPI = new OpenAPI();
		
//		Info:
		Info info = new Info();
		info.setTitle("Ecom API");
		info.setDescription("This is a simple ecommerce API project which have every functionality of an ecommerce website.");
	
		Contact contact = new Contact();
		contact.setEmail("mandalsahil253@gmail.com");
		contact.setName("Sahil Mandal");
		contact.setUrl("https://www.linkedin.com/in/sahil-mandal-588380245/");
		info.setContact(contact);
		info.setLicense(new License().name("Ecom API 1.0").url("https://www.linkedin.com/in/sahil-mandal-588380245/"));
		
//		List of Server:
		
//		SecurityScheme:
		SecurityScheme securityScheme = new SecurityScheme().name("Authorization")
				.scheme("Bearer")
				.type(Type.HTTP).bearerFormat("JWT").in(In.HEADER);
		
//		Component:
		Components components = new Components();
		components.addSecuritySchemes("Token", securityScheme);
		
		openAPI.setInfo(info);
		openAPI.setComponents(components);
		openAPI.setSecurity(List.of(new SecurityRequirement().addList("Token")));
		
		return openAPI;
	}
	
}
