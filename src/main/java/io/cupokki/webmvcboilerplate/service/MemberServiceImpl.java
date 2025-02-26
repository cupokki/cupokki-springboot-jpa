package io.cupokki.webmvcboilerplate.service;

import io.cupokki.webmvcboilerplate.dto.MemberCreateDto;
import io.cupokki.webmvcboilerplate.dto.MemberDto;
import io.cupokki.webmvcboilerplate.dto.MemberLoginDto;
import io.cupokki.webmvcboilerplate.entity.Member;
import io.cupokki.webmvcboilerplate.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
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
        if (!isDuplicateEmail(memberCreateDto.getEmail())) {
            throw new Exception("사용할 수 없는 이메일입니다.");
        }
        // 비밀번호 확인 검사

        if (!memberCreateDto.getPassword().equals(memberCreateDto.getConfirmPassword())) {
            throw new Exception("확인 비밀번호가 일치하지 않습니다.");
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
        if(memberRepository.findByEmail(email) == null){
            return true;
        }
        return false;
    }
}
