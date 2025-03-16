package io.cupokki.webmvcboilerplate.config;

import io.cupokki.webmvcboilerplate.repository.MemberRepository;
import io.cupokki.webmvcboilerplate.security.EmailPasswordAuthenticationProvider;
import io.cupokki.webmvcboilerplate.service.MemberDetailsService;
import io.cupokki.webmvcboilerplate.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

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
    public UserDetailsService userDetailsService(MemberRepository memberRepository) {
        return new MemberDetailsService(memberRepository);
    }

    @Bean
    public EmailPasswordAuthenticationProvider emailPasswordAuthenticationProvider(
            MemberDetailsService memberDetailsService,
            PasswordEncoder passwordEncoder
    ) {
        return new EmailPasswordAuthenticationProvider(memberDetailsService, passwordEncoder);
    }

    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity httpSecurity,
            EmailPasswordAuthenticationProvider emailPasswordAuthenticationProvider
    ) throws Exception {
        httpSecurity
                .authenticationProvider(emailPasswordAuthenticationProvider)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login", "/join").permitAll()
                        .requestMatchers("/images/**").permitAll()
                        .requestMatchers("/h2-console/**").permitAll() // H2 Console 접근 허용
                        .requestMatchers(HttpMethod.POST, "/join").permitAll()
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf
                        .disable())  // CSRF 보호 비활성화

                .formLogin(login->login
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .usernameParameter("email")
                        .failureUrl("/login?error=true")
                        .defaultSuccessUrl("/", false) // 자동으로 리디렉션
                        .permitAll()
                )
//                .cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer
//                        .disable()
//                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // 로그아웃 엔드포인트
                        .logoutSuccessUrl("/?logout") // 로그아웃 성공 후 이동할 페이지 ?logout은 클라이언트에 로그아웃했다고 알리기 위함
                        .invalidateHttpSession(true) // 세션 무효화
                        .deleteCookies("JSESSIONID") // 쿠키 삭제
                        .permitAll()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // 세션 정책 설정
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(true)
                )
                // h2-console용
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()));  // ✅ 최신 방식 (iframe 허용)


        return httpSecurity.build();
    }
}
