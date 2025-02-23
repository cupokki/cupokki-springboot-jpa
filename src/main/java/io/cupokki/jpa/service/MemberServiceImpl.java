package io.cupokki.jpa.service;

import io.cupokki.jpa.dto.MemberCreateDto;
import io.cupokki.jpa.dto.MemberDto;
import io.cupokki.jpa.dto.MemberLoginDto;
import io.cupokki.jpa.entity.Member;
import io.cupokki.jpa.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public MemberDto join(MemberCreateDto memberCreateDto) throws Exception{
        // 이메일 중복체크
        // 요청을 보내는 중에 중복된 이메일이 생성될 수 있잖아?
        if(!isDuplicateEmail(memberCreateDto.getEmail())) {
            throw new Exception("");
        }
        // 비밀번호 확인 검사
        if(memberCreateDto.getPassword()!= memberCreateDto.getPasswordCheck()) {
            throw new Exception("");
        }

        Member member = Member.builder()
                .email(memberCreateDto.getEmail())
                .memberPw(memberCreateDto.getPassword())
                .build();

        Member saved = memberRepository.save(member);

        return MemberDto.builder()
                .memberSeq(saved.getMemberSeq())
                .email(saved.getEmail())
                .build();
    }

    @Override
    public MemberDto login(MemberLoginDto memberLoginDto)  {
        try {
            Member member = memberRepository.findByEmail(memberLoginDto.getEmail());
            // encrypted pw
//            encode(memberLoginDto.getPassword());

            if (member.getMemberPw() == memberLoginDto.getMemberPw())
                return MemberDto.builder()
                        .memberSeq(member.getMemberSeq())
                        .username(member.getUsername())
                        .regDate(member.getRegDate())
                        .build();
        } catch (Exception e) {

        }
        return MemberDto.builder()
                .build();
    }

    @Override
    public Boolean deleteMember(MemberDto Dto) {
        return null;
    }

    @Override
    public MemberDto getById(Long memberSeq) {
        return MemberDto.builder()
                .build();
    }

    @Override
    public Boolean isDuplicateEmail(String email) {

        return null;
    }
}
