package io.cupokki.jpa.service;

import io.cupokki.jpa.domain.dto.MemberCreateDto;
import io.cupokki.jpa.domain.dto.MemberDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface MemberService {

    public Optional<MemberCreateDto> join(MemberCreateDto memberCreateDto);

    public Optional<MemberDto> login(MemberDto Dto);

    // 반환타입 생각해볼 것
    public Boolean deleteMember();

    public Optional<MemberDto> findById();
}
