package io.cupokki.jpa.config;

import io.cupokki.jpa.repository.MemberRepository;
import io.cupokki.jpa.repository.impl.MemberJpqlRepository;
import io.cupokki.jpa.service.Impl.MemberServiceImpl;
import io.cupokki.jpa.service.Impl.PostServiceImpl;
import io.cupokki.jpa.service.MemberService;
import io.cupokki.jpa.service.PostService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public PostService postService() {
        return new PostServiceImpl();
    }

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl();
    }


//    @Bean// 레포지토리, 서비스 변경
//    public MemberRepository MemberRepository(){
//        return new MemberJpqlRepository(em);
//    };

}
