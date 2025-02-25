package io.cupokki.webmvcboilerplate.config;

import io.cupokki.webmvcboilerplate.repository.MemberRepository;
import io.cupokki.webmvcboilerplate.repository.PostRepository;
import io.cupokki.webmvcboilerplate.service.MemberServiceImpl;
import io.cupokki.webmvcboilerplate.service.PostServiceImpl;
import io.cupokki.webmvcboilerplate.service.MemberService;
import io.cupokki.webmvcboilerplate.service.PostService;
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
