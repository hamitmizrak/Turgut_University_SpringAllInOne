package com.hamitmizrak.bean;

import com.hamitmizrak.business.services.ICustomerServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// LOMBOK
@RequiredArgsConstructor // Injection
@Log4j2

@Configuration
public class CustomerCommandLineRunner {

    private final ICustomerServices customerServices;

    // proje ayağa kalkar kalmaz otomatik veri eklesin
    @Bean
    public CommandLineRunner projectDataProcess(){ // parametre olarak verebilirsin==> ICustomerServices customerServices
        return args -> {
            customerServices.speedData();
        };
    }
}
