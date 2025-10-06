package com.neocare.api.infrastructure.config.springdoc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfigurations {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Neocare API")
                        .description("API Rest da aplicação Neocare")
                        .contact(new Contact()
                                .name("TRNT Team")
                        ));
    }
}
