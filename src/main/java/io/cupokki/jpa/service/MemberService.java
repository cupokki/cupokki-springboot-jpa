package io.cupokki.jpa.service;

import io.cupokki.jpa.dto.MemberCreateDto;
import io.cupokki.jpa.dto.MemberDto;
import io.cupokki.jpa.dto.MemberLoginDto;
import io.cupokki.jpa.dto.PostResponseDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

//@Service
public interface MemberService {

    public MemberDto join(MemberCreateDto memberCreateDto) throws Exception;

    public MemberDto login(MemberLoginDto memberLoginDto) throws Exception;

    // 파라미터, 반환타입 생각해볼 것
    public Boolean deleteMember(MemberDto memberDto);

    public MemberDto getById(Long memberSeq);

    public Boolean isDuplicateEmail(String email);
}
