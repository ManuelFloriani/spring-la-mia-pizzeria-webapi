package com.experis.course.pizzeria.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    // configurazione su come avere uno userDetailService
    @Bean
    public DatabaseUserDetailService userDetailService(){
        return new DatabaseUserDetailService();
    }

    // configurazione su come avere un passwordEncoder
    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    // configurazione su come avere un authenticationProvider
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        // creo un dao authentication provider
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        // gli assegno lo user detail service
        provider.setUserDetailsService(userDetailService());
        // gli assegno il password encoder
        provider.setPasswordEncoder(passwordEncoder());
        // restituisco il provider
        return provider;
    }

    // SecurityFilterChain
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // Con questo metodo configuro le regole di sicurezza per rendere tutte le pagine private,
        // ovvero vincolo alla login l'accesso a TUTTE le pagine del sito

        /* http
            .authorizeHttpRequests()
            .anyRequest()
            .authenticated()
            .and().formLogin()
            .and().logout();
        return http.build(); */
        http.authorizeHttpRequests()
                .requestMatchers("/pizzas", "/pizzas/show/**").hasAnyAuthority("ADMIN", "USER")              // Index/Show   PIZZA
                .requestMatchers("/offer", "/users").hasAuthority("ADMIN")                             // Index        INGREDIENTI/OFFERTE/UTENTI
                .requestMatchers("/pizzas/create", "/offer/create/**").hasAuthority("ADMIN")        // Create       PIZZA/OFFERTE/INGREDIENTI
                .requestMatchers("/pizzas/edit/**", "/offer/edit/**").hasAuthority("ADMIN")                            // Edit         PIZZA/OFFERTE
                .requestMatchers("/pizzas/delete/**").hasAuthority("ADMIN")                   // Delete       PIZZA/INGREDIENTI
                .requestMatchers(HttpMethod.POST, "/pizzas/**", "/offer/**").hasAuthority("ADMIN")  // Metodi POST  PIZZA/INGREDIENTI/OFFERTE
                .requestMatchers("/**").permitAll()
                .and().formLogin()
                .and().logout();
        // Disabilito il CSRF per poter utilizzare Postman
        http.csrf().disable();
        return http.build();
    }
}
