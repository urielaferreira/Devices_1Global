package com.uriel.Devices.config;

import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;

@Configuration
public class SwaggerConfig {

    //TODO: Create SecurityConfig for Oauth2 security
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Devices API")
                        .version("1.0")
                        .description("API for device management"));
    }
}