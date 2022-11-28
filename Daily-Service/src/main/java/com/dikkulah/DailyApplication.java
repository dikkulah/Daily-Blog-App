package com.dikkulah;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.TimeZone;

@EnableFeignClients
@OpenAPIDefinition(info =
@io.swagger.v3.oas.annotations.info.Info(title = "Daily API", version = "1.0.0", description = "Documentation Daily API v1.0")
)
@SpringBootApplication
public class DailyApplication {

    public static void main(String[] args) {
        System.setProperty("spring.devtools.restart.enabled", "true");
        System.setProperty("java.awt.headless", "false");
        SpringApplication.run(DailyApplication.class, args);
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Customer accounts API").version("1.0.0")
                        .license(new License().name("Ufuk Dikkülah").url("https://github.com/dikkulah")));
    }

    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("IST"));
    }
}
