package io.cupokki.webmvcboilerplate.controller;

import io.cupokki.webmvcboilerplate.dto.MemberCreateDto;
import io.cupokki.webmvcboilerplate.dto.MemberDto;
import io.cupokki.webmvcboilerplate.dto.MemberLoginDto;
import io.cupokki.webmvcboilerplate.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }



//    @Secured("hasRole(USER)")
    @GetMapping("/profile")
    public String profileForm(Model model, HttpSession session) {
        Long memberSeq = (Long) session.getAttribute("memberSeq");
        MemberDto memberDto = memberService.getById(memberSeq);
        model.addAttribute("Member", memberDto);
        return "member/profile";
    }

    @GetMapping("/edit")
    public String updateForm(Model model, HttpSession session) {
        Long memberSeq = (Long) session.getAttribute("memberSeq");
        MemberDto memberDto = memberService.getById(memberSeq);
        model.addAttribute("Member", memberDto);
        return "member/edit";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute MemberDto memberDto) {
        memberService.update(memberDto);
        return "redirect:/members/profile";
    }


}
