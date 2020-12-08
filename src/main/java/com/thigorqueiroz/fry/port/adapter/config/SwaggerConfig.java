package com.thigorqueiroz.fry.port.adapter.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.core.jackson.ModelResolver;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().components(
                new Components().addSecuritySchemes(
                        "basicScheme",
                        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")
                )
        ).info(
                new Info().title("Fry API").version("0.0.1")
        );
    }

    @Bean
    public ModelResolver modelResolver (ObjectMapper objectMapper) {
        return new ModelResolver(objectMapper);
    }

}
