package io.cupokki.webmvcboilerplate.config;

import io.cupokki.webmvcboilerplate.repository.MemberRepository;
import io.cupokki.webmvcboilerplate.service.MemberDetailsService;
import io.cupokki.webmvcboilerplate.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final MemberRepository memberRepository;

    @Autowired
    public SecurityConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public UserDetailsService userDeatailsService(MemberRepository memberRepository) {
        return new MemberDetailsService(memberRepository);
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/members/login").permitAll()
                        .anyRequest().authenticated()
                )
//                .formLogin(form -> form.disable());
                .formLogin(login->login
                        .loginPage("/members/login")
                        .defaultSuccessUrl("/", false)
                        .permitAll()
                )
//                .cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer
//                        .disable()
//                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // 로그아웃 엔드포인트
                        .logoutSuccessUrl("/?logout") // 로그아웃 성공 후 이동할 페이지 ?logout은 클라이언트에 로그아웃했다고 알리기 위함
                        .permitAll()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // 세션 정책 설정
                );


        return httpSecurity.build();
    }
}
