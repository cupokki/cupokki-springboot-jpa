package io.cupokki.jpa.controller;

import io.cupokki.jpa.dto.MemberCreateDto;
import io.cupokki.jpa.dto.MemberLoginDto;
import io.cupokki.jpa.dto.PostCreateDto;
import io.cupokki.jpa.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/login")
    public String login(){
        return "member/login";
    }

    //
    @GetMapping("/join")
    public String join(){
        return "member/login";
    }

    @PostMapping("/login")
    public String reqLogin(@RequestBody MemberLoginDto MemberLoginDto) {
        try {
            memberService.login(MemberLoginDto);
        } catch (Exception e) {
            return "login";
        }

        //성공
        return "redirect:/";
    }

}
