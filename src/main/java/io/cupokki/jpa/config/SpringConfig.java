package io.cupokki.jpa.config;

import io.cupokki.jpa.repository.MemberRepository;
import io.cupokki.jpa.repository.PostRepository;
import io.cupokki.jpa.service.MemberServiceImpl;
import io.cupokki.jpa.service.PostServiceImpl;
import io.cupokki.jpa.service.MemberService;
import io.cupokki.jpa.service.PostService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final EntityManager em;

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(EntityManager em, PostRepository postRepository, MemberRepository memberRepository) {
        this.em = em;
        this.postRepository = postRepository;
        this.memberRepository = memberRepository;
    }

    @Bean
    public PostService postService() {
        return new PostServiceImpl(postRepository);
    }

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository);
    }


//    @Bean// 레포지토리, 서비스 변경
//    public MemberRepository MemberRepository(){
//        return new MemberJpqlRepository(em);
//    };

}
