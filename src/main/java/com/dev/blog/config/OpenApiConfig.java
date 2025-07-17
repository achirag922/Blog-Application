package com.dev.blog.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "JWT";

        return new OpenAPI()
            .info(new Info()
                .title("Blogging Application : Backend Course")
                .description("This project is Developed by Chirag")
                .version("1.0")
                .termsOfService("Terms of Service")
                .contact(new Contact()
                    .name("Chirag")
                    .url("https://github.com/achirag922/")
                    .email("chiraga216@gmail.com"))
                .license(new License()
                    .name("License of API")
                    .url("API License URL")))
            .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
            .components(new Components()
                .addSecuritySchemes(securitySchemeName, 
                    new SecurityScheme()
                        .name("Authorization")
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")));
    }
}