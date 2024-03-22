package com.example.proptitendcoursepractice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityDetail {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

//        httpSecurity.formLogin(form -> {
//            form.loginPage("/login").permitAll();
//        });
        httpSecurity.authorizeHttpRequests(request -> {
            request.anyRequest().permitAll();
        });
        httpSecurity.csrf(csrf -> {csrf.disable();});
        return httpSecurity.build();
    }
}
