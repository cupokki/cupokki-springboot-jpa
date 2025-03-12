package io.cupokki.webmvcboilerplate.service;

import io.cupokki.webmvcboilerplate.dto.MemberCreateDto;
import io.cupokki.webmvcboilerplate.dto.MemberDto;
import io.cupokki.webmvcboilerplate.dto.MemberLoginDto;
import io.cupokki.webmvcboilerplate.entity.Member;
import io.cupokki.webmvcboilerplate.entity.MemberDetails;
import io.cupokki.webmvcboilerplate.repository.MemberRepository;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final MemberDetailsService memberDetailsService;
    private final MemberRepository memberRepository;

    public AuthService(PasswordEncoder passwordEncoder, MemberDetailsService memberDetailsService, MemberRepository memberRepository) {
        this.passwordEncoder = passwordEncoder;
        this.memberDetailsService = memberDetailsService;
        this.memberRepository = memberRepository;
    }

    public MemberDto join(MemberCreateDto memberCreateDto) throws Exception{
        // 이메일 중복체크
        // 요청을 보내는 중에 중복된 이메일이 생성될 수 있잖아?
        // 실무에선 이게 그렇게 부담되는 동작이 아니다.?
        if (!isDuplicateEmail(memberCreateDto.getEmail())) {
            throw new Exception("사용할 수 없는 이메일입니다.");
        }
        // 비밀번호 확인 검사

        if (!memberCreateDto.getPassword().equals(memberCreateDto.getConfirmPassword())) {
            throw new Exception("확인 비밀번호가 일치하지 않습니다.");
        }

        // encrypted pw
//            PasswordUtil.encode(memberLoginDto.getMemberPw());

        String hashed = passwordEncoder.encode(memberCreateDto.getPassword());
        Member member = Member.builder()
                .email(hashed)
                .memberPw(memberCreateDto.getPassword())
                .build();

        Member saved = memberRepository.save(member);

        return MemberDto.builder()
                .memberSeq(saved.getMemberSeq())
                .email(saved.getEmail())
                .build();
    }

//    public MemberDto login(MemberLoginDto memberLoginDto)  {
//        MemberDetails memberDetails = memberDetailsService.loadUserByUsername(memberLoginDto.getEmail());
//        try {
//            Member member = memberRepository.findByEmail(memberLoginDto.getEmail());
//
//
//            // authentification
//            passwordEncoder.matches(memberLoginDto.getMemberPw(), member.getMemberPw());
//
//            if (member.getMemberPw() == memberLoginDto.getMemberPw())
//                return MemberDto.builder()
//                        .memberSeq(member.getMemberSeq())
//                        .username(member.getUsername())
//                        .regDate(member.getRegDate())
//                        .build();
//        } catch (Exception e) {
//
//        }
//        return MemberDto.builder()
//                .build();
//    }
//
    public Boolean isDuplicateEmail(String email) {
//        if(memberRepository.findByEmail(email) == null){
        if(memberRepository.existsByEmail(email)){
            return true;
        }
        return false;
    }

    public boolean isDuplicateUsername(String username) {

        if (memberRepository.existsByUsername(username))
            return true;
        return false;
    }
}
