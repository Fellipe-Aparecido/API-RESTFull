package br.com.rest.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("RESTful API with Java 19 and Spring Voot 3")
                        .version("v1")
                        .description("Some description about API")
                        .termsOfService("https://url.dos.termosdeservico")
                        .license(
                                new License()
                                        .name("Apache 2.0")
                                        .url("https://urldalicensa.com.br")));
    }
}
