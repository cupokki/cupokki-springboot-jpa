package io.cupokki.webmvcboilerplate.service;

import io.cupokki.webmvcboilerplate.dto.MemberCreateDto;
import io.cupokki.webmvcboilerplate.dto.MemberDto;
import io.cupokki.webmvcboilerplate.dto.MemberLoginDto;

//@Service
public interface MemberService {

    public MemberDto join(MemberCreateDto memberCreateDto) throws Exception;

    public MemberDto login(MemberLoginDto memberLoginDto) throws Exception;

    public MemberDto update(MemberDto memberDto);

    // 파라미터, 반환타입 생각해볼 것
    public Boolean delete(MemberDto memberDto);

    public MemberDto getById(Long memberSeq);

    public Boolean isDuplicateEmail(String email);

    public boolean isDuplicateUsername(String username);
}
