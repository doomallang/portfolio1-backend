package com.doomole.portfolio.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("INFO-SAFE-BOX")
                        .version("v1")
                        .description("인포세이프박스 Swagger UI"))
                .servers(List.of(
                                new Server().url("http://localhost:8080").description("로컬")
                        )
                );
    }
}
