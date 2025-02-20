package io.cupokki.jpa.service;

import io.cupokki.jpa.dto.MemberCreateDto;
import io.cupokki.jpa.dto.MemberDto;
import io.cupokki.jpa.dto.PostResponseDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

//@Service
public interface MemberService {

    public Optional<MemberCreateDto> join(MemberCreateDto memberCreateDto);

    public Optional<MemberDto> login(MemberDto Dto);

    // 파라미터, 반환타입 생각해볼 것
    public Boolean deleteMember(MemberDto Dto);

    public Optional<MemberDto> findById();
}
