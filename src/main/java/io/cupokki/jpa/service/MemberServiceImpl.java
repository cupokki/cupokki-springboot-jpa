package io.cupokki.jpa.service;

import io.cupokki.jpa.dto.MemberCreateDto;
import io.cupokki.jpa.dto.MemberDto;
import io.cupokki.jpa.service.MemberService;

import java.util.Optional;

public class MemberServiceImpl implements MemberService {
    @Override
    public Optional<MemberCreateDto> join(MemberCreateDto memberCreateDto) {
        return Optional.empty();
    }

    @Override
    public Optional<MemberDto> login(MemberDto Dto) {
        return Optional.empty();
    }

    @Override
    public Boolean deleteMember(MemberDto Dto) {
        return null;
    }

    @Override
    public Optional<MemberDto> findById() {
        return Optional.empty();
    }
}
