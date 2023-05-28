package com.justeat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails adminUser = User.withUsername("username")
                .password(passwordEncoder().encode("password"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(adminUser);

    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/adminlogin")
                .authenticated()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/adminregister")
                .authenticated()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/admindashboard")
                .authenticated()
                .anyRequest().permitAll()
                .and().formLogin().and().logout().logoutSuccessUrl("/");

        return httpSecurity.build();
    }
}
