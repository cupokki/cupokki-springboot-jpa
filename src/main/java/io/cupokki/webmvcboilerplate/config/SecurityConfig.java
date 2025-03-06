package io.cupokki.webmvcboilerplate.config;

import io.cupokki.webmvcboilerplate.util.PasswordUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public final PasswordEncoder passwordEncoder;

    public SecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(auth ->
                    auth.requestMatchers("/**").permitAll()
                )
                .csrf(csrf-> csrf.disable())
//                .cors(cors-> cors.)
                .formLogin(form -> form.disable());

        return httpSecurity.build();
    }
}
