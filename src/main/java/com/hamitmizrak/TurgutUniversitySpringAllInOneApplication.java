package com.hamitmizrak;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Collections;
import java.util.TimeZone;

// auditorAware icin
@EnableJpaAuditing(auditorAwareRef = "auditorAwareMethod")

//SCAN
@EntityScan(basePackages = "com.hamitmizrak.data.entity") //Entity bulamadığı zaman
@EnableJpaRepositories(basePackages ="com.hamitmizrak.data.repository" ) //Repository bulamadığı zaman

// Spring Security
/*
@SpringBootApplication(exclude = {
        //SecurityAutoConfiguration.class,
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
        org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration.class
}
)
*/
@SpringBootApplication
public class TurgutUniversitySpringAllInOneApplication {

    @PostConstruct
    public void init(){
        TimeZone.setDefault(TimeZone.getTimeZone("IST"));
    }

    public static void main(String[] args) {
        // devtools active isActive
        // System.setProperty("spring.devtools.restart.enabled","true");

        // PORT Ayarlamak
        /*
        SpringApplication app = new SpringApplication(TurgutUniversitySpringAllInOneApplication.class);
        app.setDefaultProperties(Collections
                .singletonMap("server.port", "8083"));
        app.run(args);
         */

        //Disables headless JOptionPane
        System.setProperty("java.awt.headless", "false");

        SpringApplication.run(TurgutUniversitySpringAllInOneApplication.class, args);
    } //end PSVM
} // end TurgutUniversitySpringAllInOneApplication
