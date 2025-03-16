package io.cupokki.webmvcboilerplate.controller;

import io.cupokki.webmvcboilerplate.dto.MemberCreateDto;
import io.cupokki.webmvcboilerplate.dto.MemberDto;
import io.cupokki.webmvcboilerplate.dto.MemberLoginDto;
import io.cupokki.webmvcboilerplate.repository.MemberRepository;
import io.cupokki.webmvcboilerplate.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("")
@RequiredArgsConstructor
public class AuthController {

    private final MemberRepository memberRepository;
    private final MemberService memberService;

    /** 로그인 뷰 */
    @GetMapping("/login")
    public String loginForm() { return "auth/login"; }

    /** 가입 뷰 */
    @GetMapping("/join")
    public String joinForm(){
        return "auth/join";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute MemberCreateDto memberCreateDto, Model model) {
        try {
            memberService.join(memberCreateDto);
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            return "auth/join";
        }

        return "redirect:/login";
    }

    // API-------------------------------------------------------------------------------
//    @ResponseBody
//    @GetMapping("/auth/username/duplicate")
//    public ResponseEntity<?> checkDuplicateUsername(@RequestParam("username") String username) {
//        if(!memberService.isDuplicateUsername(username))
//            return ResponseEntity.status(409).build();
//        return ResponseEntity.ok(true);
//    }
//
//    @ResponseBody
//    @GetMapping("/auth/email/duplicate")
//    public ResponseEntity<?> checkDuplicateEmail(@RequestParam("email") String email) {
//        if(!memberService.isDuplicateEmail(email))
//            return ResponseEntity.status(409).build();
//        return ResponseEntity.ok(true);
//    }
}
