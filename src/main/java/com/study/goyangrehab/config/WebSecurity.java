package com.study.goyangrehab.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurity {


    @Bean
    protected SecurityFilterChain config(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable).cors(Customizer.withDefaults())
                .authorizeHttpRequests(request ->
                        request.requestMatchers("/api/**", "/error",
                                        "/favicon.ico",
                                        "/swagger-ui.html",
                                        "/swagger/**",
                                        "/swagger-resources/**").permitAll()
                                .anyRequest().authenticated()
                ).formLogin(login -> login.defaultSuccessUrl("/", true).permitAll()).logout(Customizer.withDefaults());

        return http.build();
    }

}
