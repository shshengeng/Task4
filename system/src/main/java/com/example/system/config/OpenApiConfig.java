package com.example.system.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("第四次任务文档")
                        .description("文档描述")
                        .contact(new Contact().name("brian").email("shshengeng@gamil.com"))
                        .version("v1.0"));
    }
}
