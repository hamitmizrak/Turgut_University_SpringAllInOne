package com.hamitmizrak.security;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

// LOMBOK
@RequiredArgsConstructor

// WebSecurityConfig
@Configuration
@EnableWebSecurity

//h2-console
@ConditionalOnProperty(
        value="spring.h2.console.enabled",
        havingValue = "true",
        matchIfMissing = false)
public class WebSecurityConfig {

    // Global Variable ==> application.properties
    @Value("${spring.security.user.name}")
    private String username;

    @Value("${spring.security.user.password}")
    private String password;

    @Value("${spring.security.user.roles}")
    private String roles;


    // database olmadan kullanım : InMemoryUserDetailsManager
    // Hashsiz hali
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(PasswordEncoder passwordEncoder) {
        //admin 2 rolü var
        UserDetails admin = User
                .withDefaultPasswordEncoder() //.withUserDetails(username)
                .username(username)
                //.password(passwordEncoder.encode(password))
                .password(password)
                .roles(roles, "ROLES_USER")
                .build();

        //user 1 rolü var
        UserDetails user = User
                .withDefaultPasswordEncoder() //.withUserDetails(username)
                .username("root2")
                .password("root2")
                .roles("ROLES_USER2")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

    // 2.YOL
    // Bunu yazarsak;  InMemoryUserDetailsManager yukarıdaki yazdığımız kabul etmedi.
    //injection
   /*
   @Autowired
    private  PasswordEncoderBean passwordEncoderBean;

    @SneakyThrows
    @Autowired // injection
    // {noop} ==> maskelemeden sakla
    public void myUserPasswordRoles(AuthenticationManagerBuilder authenticationManagerBuilder){
        authenticationManagerBuilder
                .inMemoryAuthentication()
                .withUser("root3")
                //.password("{noop}root3")
                .password(passwordEncoderBean.passwordEncoderMethod().encode("root3"))
                .roles("ROLES_USER3");
    }
    */

    // securityFilterChain
    @Bean
    @SneakyThrows // throws Exception
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                .requestMatchers("/login").permitAll()
                .requestMatchers("/home","/").permitAll()
                .requestMatchers("/logout").permitAll()
                .requestMatchers("/h2-console/**").permitAll()
                .anyRequest()
                .authenticated()
                .and().csrf().ignoringRequestMatchers("/h2-console/**")
                .and().headers().frameOptions().sameOrigin()
                .and()
                //.httpBasic();
                .formLogin()
                .and()
                .logout().logoutUrl("/logout44").invalidateHttpSession(true);// var olan sessionları kapatıyor.
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return passwordEncoder;
    }

}//end class
