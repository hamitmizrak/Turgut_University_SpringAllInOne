package com.hamitmizrak.bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableSwagger2
public class OpenApiConfigurationBean {

      @Bean
    public OpenAPI openAPIMethod() {
        return new OpenAPI().info(new Info()
                .title("title bilgisi")
                .description("description bilgisi")
                .version("V1.0")
                .contact(new Contact()
                        .name("Hamit")
                        .url("www.hamitmizrak.com.tr")
                        .email("hamitmizrak@gmail.com"))
                .termsOfService(" Software INC.")
                .license(new License()
                        .name("Licence ")
                        .url("www."))
        );
    }
}

//localhost:2222/swagger-ui.html
//swagger: API Document



